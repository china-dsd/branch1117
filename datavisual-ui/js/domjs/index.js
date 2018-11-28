//var url = "http://10.14.34.170:8090";
//var url = "http://localhost:8090";
var token = "9385d137-a82f-439c-bbc6-fbb5979edf4e";




$(function(){
    //点击按钮增加选中框
    $('.yw').click(function () {
        $(this).addClass('active');
        $('.hx').removeClass('active');
    })
    $('.hx').click(function () {
        $(this).addClass('active');
        $('.yw').removeClass('active');
    });
})

//成熟度评分 （饼状图）
var lineChart_1 = echarts.init(document.getElementById("box1"));
var lineOption_1 = {
	color : ['#d25312','#d2163c','#4bd214','#2a78d2','#10d27d'],
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: '70%',
		top: '18%',
        data: [],
		textStyle:{
            color:'#ffffff',
        }
    },
    series : {
            name: '成熟度',
            type: 'pie',
            radius : '55%',
            center: ['40%', '55%'],
            data:[
                
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
};
function getLineData_1() {
    $.ajax({
        type:"GET",
        url: url+"/index/pieChart?type=3"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                var _name = [];
                $.each(data,function(index){
                    _name.push(data[index].name);
                })
                lineOption_1.legend.data = _name;
                lineOption_1.series.data = data;
                lineChart_1.setOption(lineOption_1);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getLineData_1();
function getTotalCount() {
    $.ajax({
        type:"GET",
        url: url+"/index/totalCount?type=1"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                $("#totalCount").html(data);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getTotalCount();
// 重大经营风险事件总体情况 （表格）
function getBarData_1() {
    $.ajax({
        type:"GET",
        url: url+"/index/barChart?type=1"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                var BarData_1= [];
                var _title = [];
                var _name = [];
                $.each(data,function(index){
                    _title.push(data[index].title);
                    _name = data[index].name;
                    var eChart = {};
                    eChart.name=data[index].title
                    eChart.type='bar'
                    eChart.barGap=0
                    eChart.data = data[index].data;
                    var moneyy=data[2].value;
                    if (index == 0) {
                        //

                    }else{
                        // eChart.yAxisIndex=1
                        $("#z1").html(data[0].name);
                        $("#z2").html(data[1].name);
                        $("#z3").html(data[2].name);

                        $("#amount1").html(data[0].value);
                        $("#amount2").html(data[1].value);



                        if(moneyy>=100000000){
                            document.getElementById('z5').innerText="万亿";
                            var num = moneyy/100000000;
                            var money = num.toFixed(2);
                            $("#money").html(money);
                        }else if(data[2].value>=10000){
                            //$("#z5").html("亿元");
                            document.getElementById('z5').innerText="亿元";
                            var mon =data[2].value;
                            var num = mon/10000;
                            var money = num.toFixed(2);
                            $("#money").html(money);
                        }else{
                            $("#money").html(moneyy);
                        }


                    }
                    // BarData_1.push(eChart);
                })

                /*barOption_1.xAxis[0].data = _name;
                barOption_1.legend.data = _title;
                barOption_1.series = BarData_1;
                barChart_1.setOption(barOption_1);*/


            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getBarData_1()

//风险评估 （饼状图）
var pieChart_1 = echarts.init(document.getElementById("box3_1"));
pieOption_1 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        right:80,
        y: 'center',
        data: [],
        textStyle:{
            color:'#ffffff',
        }
    },
    series: [
        {
            name:'风险事件数量图',
            type:'pie',
            radius: ['80%', '0%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '20',
                        fontWeight: 'bold'
                    }
                }
            },
            label:{            //饼图图形上的文本标签
                normal:{
                    show:true,
                    position:'inner', //标签的位置
                    textStyle : {
                        fontWeight : 300 ,
                        fontSize : 13    //文字的字体大小
                    },
                    formatter:'{d}%'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};
pieChart_1.setOption(pieOption_1);
//风险评估 （饼状图）数据对接
function getPieData_1(){
    $.ajax({
        type:'GET',
        url: url+"/index/pieChart?type=1"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == '000000'){
                var PieData_1 = data;
                var PieName_1 = [];
                $.each(data,function(index){
                    PieName_1.push(data[index].name);    
                })
                pieOption_1.legend.data = PieName_1;
                pieOption_1.series[0].data = PieData_1;
                pieChart_1.setOption(pieOption_1);
            }
            else if(res.code == '100001'){
                location.href="/";
            }
        }   
    })
}
getPieData_1();
//法律案件 （柱状图）
// var barChart_2 = echarts.init(document.getElementById("box4"));
barOption_2 = {
    color: ['yellow'],
    grid: {
        left: '6%',
        right:60,
        containLabel: true,
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data:{},
        right:10,
        top:5,
        textStyle:{
            color:'#ffffff',
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
    },
    calculable: true,
    yAxis: {
            type: 'category',
            data: [],
            //刻度不显示
            axisTick:{
                show:false
            },
            //坐标轴
            axisLine:{
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed'  //'dotted'虚线 'solid'实线
                },

            },
            //横坐标数值
            axisLabel: {
                color:'#ffffff',
                fontSize:'14px'
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
        },
	xAxis: [
        {
            type: 'value',
            scale: true,
			name: '个/万元',
            nameTextStyle:{
                color:'#ffffff',
            },
            min: 0,
            // 轴线
            axisLine : {
                show: true,
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed',
                }
            },
            // 轴线值
            axisLabel: {
                show:true,
                color:'#ffffff',
                fontSize:'14px'
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
        }
    ],
	series: {
		type: 'bar',
		data: []
    }
};
function getBarData_2() {
    $.ajax({
        type:"GET",
        url: url+"/index/barChart?type=2"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                var BarData_2= [];
                var _name = [];
				var y_AxisData = [];
                $.each(data,function(index){
                    _name = data[index].name;
					y_AxisData.push(data[index].name)
                    BarData_2.push(data[index].value);
                })
                $("#y1").html(y_AxisData[3]);
                $("#y2").html(y_AxisData[2]);
                $("#y3").html(y_AxisData[1]);
                $("#y4").html(y_AxisData[0]);

                $("#sum2").html(BarData_2[0]);
                $("#sum").html(BarData_2[2]);
                $("#figure").html(BarData_2[1]);
                $("#amount").html(BarData_2[3]);
                // barOption_2.yAxis.data = y_AxisData;
                // barOption_2.yAxis.type = "category";
                // barOption_2.legend.data = "2018年";
                // barOption_2.series.name="2018年";
                // barOption_2.series.data = BarData_2;
                /*barChart_2.setOption(barOption_2);*/
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getBarData_2()
//各单位全国分布图 (地图)
var mapChart_1 = echarts.init(document.getElementById('box5'))
//数据
var data = [];
var geoCoordMap = {};
var convertData = function(data) {
    var res = [];
    for(var i = 0;i < data.length;i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if(geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
}
//mapChart的配置
var mapOption_1 = {
    geo:{
        map:'china',
        // roam: true,                //定义是否可缩放和拖拽
        itemStyle: {                 //定义样式
            normal: {                //普通状态下的样式
                areaColor: '#A2E3F2',
                borderColor: '#111'
            },
            emphasis: {              //高亮状态下的样式
                areaColor: '#ffffff'
            }
        }
    },
    series: [
        {
            name: '销量',   //series名称
            type: 'scatter',  //series图标类型
            coordinateSystem: 'geo',  //series坐标系类型
            data: convertData(data),   //series数据内容
            symbolSize: function (val) {  //根据值的多少来确定散点的大小
                return val[2] / 10;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
         itemStyle: {
             normal: {
                 color: '#ddb926'
             }
         }
        },
        {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
            }).slice(0, 6)),
            symbolSize: function (val) {
                return val[2] / 10;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: false,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: 'purple',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
        }
    ],

    //视觉映射组件，是标识某一数据范围内数据及颜色对应关系的控件
    visualMap: {
        show:false,
        type: 'continuous', //连续型
        min: 0,             //值域最小，必须参数
        max: 200,           //值域最大，必须参数
        calculable: true,   //是否启用值域漫游，通过拖拽控件手柄选择不同数值范围，达到对图表数据的筛选显示
        inRange: {
            color:['#0A947D','#eac736','#993C2C']  //指定数值从低到高时的颜色变化
        },
        textStyle: {
            color: '#fff'   //值域控件的文本颜色
        }
    }
};
mapChart_1.setOption(mapOption_1);
//审计问题整改记录6_1 （饼图）
var pieChart_2 = echarts.init(document.getElementById("box6_1"));
var pieOption_2 = {
    color: ['yellow'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
        {
            name:'',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};
pieChart_2.setOption(pieOption_2);
//审计问题整改记录6_2 （饼图）
var pieChart_3 = echarts.init(document.getElementById("box6_2"));
var pieOption_3 = {
    color: ['blue'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
        {
            name:'',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};
pieChart_3.setOption(pieOption_3);
//审计问题整改记录6_3 （饼图）
var pieChart_4 = echarts.init(document.getElementById("box6_3"));
var pieOption_4 = {
    color: ['red'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
        {
            name:'',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};
pieChart_4.setOption(pieOption_4);
//审计问题整改记录6_4 （饼图）
var pieChart_5 = echarts.init(document.getElementById("box6_4"));
var pieOption_5 = {
    color: ['green'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    series: [
        {
            name:'',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[]
        }
    ]
};
pieChart_5.setOption(pieOption_5);
//审计问题整改记录（饼图）数据对接
// var PieData_2 = [];
function getPieData_sj(){
    $.ajax({
        type:"GET",
        url: url+"/index/pieChart?type=2"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == '000000'){
                // 审计问题整改饼图1
                var PieName_2 = [];
                var PieData_2 = [];
                PieData_2.push(data[0]);
                PieName_2.push(data[0].name);
                $("#pie_2").html(data[0].value);
                pieOption_2.series[0].data = PieData_2;
                pieOption_2.series[0].name = PieName_2;
                pieChart_2.setOption(pieOption_2);
                //审计问题整改饼图2
                var PieName_3 = [];
                var PieData_3 = [];
                PieData_3.push(data[1]);
                PieName_3.push(data[1].name);
                $("#pie_3").html(data[1].value);
                pieOption_3.series[0].data = PieData_3;
                pieOption_3.series[0].name = PieName_3;
                pieChart_3.setOption(pieOption_3);
                //审计问题整改饼图3
                var PieName_4 = [];
                var PieData_4 = [];
                PieData_4.push(data[2]);
                PieName_4.push(data[2].name);
                $("#pie_4").html(data[2].value);
                pieOption_4.series[0].data = PieData_4;
                pieOption_4.series[0].name = PieName_4;
                pieChart_4.setOption(pieOption_4); 
                //审计问题整改饼图4
                var PieName_5 = [];
                var PieData_5 = [];
                PieData_5.push(data[3]);
                PieName_5.push(data[3].name);
                $("#pie_5").html(data[3].value);
                pieOption_5.series[0].data = PieData_5;
                pieOption_5.series[0].name = PieName_5;
                pieChart_5.setOption(pieOption_5);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}

getPieData_sj();
//审计问题记录 (柱状图)
var huanhang = function (params) {
    var newParamsName = ""; // 最终拼接成的字符串
    var paramsNameNumber = params.length; // 实际标签的个数
    var provideNumber = 2; // 每行能显示的字的个数
    var rowNumber = Math.ceil(paramsNameNumber / provideNumber); // 换行的话，需要显示几行，向上取整
    /**
     * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
     */
    // 条件等同于rowNumber>1
    if (paramsNameNumber > provideNumber) {
        /** 循环每一行,p表示行 */
        for (var p = 0; p < rowNumber; p++) {
            var tempStr = ""; // 表示每一次截取的字符串
            var start = p * provideNumber; // 开始截取的位置
            var end = start + provideNumber; // 结束截取的位置
            // 此处特殊处理最后一行的索引值
            if (p == rowNumber - 1) {
                // 最后一次不换行
                tempStr = params.substring(start, paramsNameNumber);
            } else {
                // 每一次拼接字符串并换行
                tempStr = params.substring(start, end) + "\n";
            }
            newParamsName += tempStr; // 最终拼成的字符串
            newParamsName = newParamsName.substring(0,19)
        }


    } else {
        // 将旧标签的值赋给新标签
        newParamsName = params;
        newParamsName = newParamsName.substring(0,19)
    }
    //将最终的字符串返回
    return newParamsName;
}
var barChart_3 = echarts.init(document.getElementById("box7"));
barOption_3 = {
    color: ['#1D6B94', '#0B947B','#973A2C'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data:{},
        right:50,
        textStyle:{
            color:'#ffffff',
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            axisTick: {show: false},
            data: [],
            //刻度不显示
            axisTick:{
                show:false
            },
            //坐标轴
            axisLine:{
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed'  //'dotted'虚线 'solid'实线
                },

            },
            //横坐标数值
            axisLabel: {
                color:'#ffffff',
                fontSize:'14px',
                interval: 0 ,
                formatter:huanhang
            },
        }
    ],
    yAxis: [
        {
            type: 'value',
            //刻度不显示
            axisTick:{
                show:false
            },
            name: '(件)',
            nameTextStyle:{
                color:'#ffffff',
            },
            // 轴线
            axisLine : {
                show: true,
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed',
                }
            },
            // 轴线值
            axisLabel: {
                color:'#ffffff',
                fontSize:'14px'
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
        },
    ],
    series: []
};
function getBarData_3() {
    $.ajax({
        type:"GET",
        url: url+"/index/barChart?type=6"+"&token="+token,
        data:{},
        dataType:"json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                var BarData_3= [];
                var _title = [];
                var _name = [];
                $.each(data,function(index){
                    _title.push(data[index].title);
                    _name = data[index].name;
                    var eChart = {};
                    eChart.name=data[index].title
                    eChart.type='bar'
                    eChart.barGap=0
                    eChart.data = data[index].data;
                    BarData_3.push(eChart);
                })
                barOption_3.xAxis[0].data = _name;
                barOption_3.legend.data = _title;
                barOption_3.series = BarData_3;
                barChart_3.setOption(barOption_3);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getBarData_3()

//(风险事件挽回金额，柱状堆积)
var barChart_4 = echarts.init(document.getElementById("box10"));
barOption_4 = {
    color: ['#d2d011', '#13d2cf','#d20fc4'],
    tooltip : {
        trigger: 'axis',
		axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data:['挽回金额','初始金额','挽回率'],
		textStyle:{
            color:'#ffffff',
        }
    },
    calculable : true,
    xAxis : [
        {
			//坐标轴
            axisLine:{
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed'  //'dotted'虚线 'solid'实线
                },

            },
            axisLabel: {
                show: true,    //这行代码控制着坐标轴x轴的文字是否显示
                formatter:huanhang,
                textStyle: {
                    color: '#fff',   //x轴上的字体颜色
                    fontSize:'10'    // x轴字体大小

                }
            },
            axisTick:{
                show:false
            },
            type : 'category',
            name : '',
			data : []
        }
    ],
    yAxis : [
        {
			//坐标轴
            axisLine:{
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed'  //'dotted'虚线 'solid'实线
                },

            },
			position : 'left',
            axisTick:{
                show:false
            },
            name: '(万元)',
			nameTextStyle:{
                color:'#ffffff',
            },
            type : 'value',
            axisLabel: {
				textStyle: {
                    color: '#fff',   //x轴上的字体颜色
                    fontSize:'10'    // x轴字体大小
                },
                show:true
            },
        },{
			//坐标轴
            axisLine:{
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed'  //'dotted'虚线 'solid'实线
                },

            },
			position : 'right',
            axisTick:{
                show:false
            },
            name: '(1)',
			nameTextStyle:{
                color:'#ffffff',
            },
            type : 'value',
            axisLabel: {
				textStyle: {
                    color: '#fff',   //x轴上的字体颜色
                    fontSize:'10'    // x轴字体大小
                },
                show:true
            },
        }
    ],
    series : [
        {
            name:'挽回金额',
            type:'line',
            data:[],
			yAxisIndex : 0
        },
        {
            name:'初始金额',
            type:'line',
            data:[],
			yAxisIndex : 0
        },
        {
            name:'挽回率',
            type:'line',
            data:[],
			yAxisIndex : 1
        }
    ]
};
function getBarData_4(){
    $.ajax({
        type:"GET",
        url:url+"/index/barChart?type=5"+"&token="+token,
        data:{},
        dataType: "json",
        success: function(res){
            var data = res.data;
            if(res.code == "000000"){
                var saveAmount= [];
                var beginAmount = [];
                var saveRate = [];
				var nameV = [];
                $.each(data,function(index){
                    saveAmount.push(data[index].saveAmount);
                    beginAmount.push(data[index].startAmount);
					saveRate.push(data[index].saveRate);
					nameV.push(data[index].name);
                })
                barOption_4.series[0].data = saveAmount;
                barOption_4.series[1].data = beginAmount;
                barOption_4.series[2].data = saveRate;
				barOption_4.xAxis[0].data = nameV;
                barChart_4.setOption(barOption_4);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    })
}
getBarData_4();


//重大风险事件（字浮云）
var wordCloudChart = echarts.init(document.getElementById("box9"));
wordCloud = {
    tooltip: {
        show: true
    },
    series: [{
        textStyle : {
            normal : {
                color : '#36f8ff'
            },
        },
        name: '重大风险事件',
        type: 'wordCloud',
        gridSize: 20,
        sizeRange: [16, 70],
        size: ['100%', '100%'],
        // itemStyle:{
        //      color:'#33edf4',
        // },
        // 旋转最大和最小角度
        rotationRange: [0, 0],
        //rotationStep:30,//文字旋转单位
        // 不知道是啥
        textRotation: [0, 0],
        textPadding: 0,
        autoSize: {
            enable: true,
            minSize: 14
        },
        data: []
    }]
};
wordCloudChart.setOption(wordCloud);
function getwordCloudData(){
    $.ajax({
        type: 'GET',
        url: url+"/fengxsj/wordCloud?type=1&token="+token,
        data: {},
        dataType:'json',
        success: function(res){
            var data = res.data;
            var proj_title = data;
            if(res.code =="000000"){
                wordCloud.series[0].data = proj_title;
                wordCloudChart.setOption(wordCloud);
            }else if(res.code == "100001"){
                location.href = '/';
            }
        }
    });
}
setInterval(getwordCloudData(),'1000');
var wordCloudChart2 = echarts.init(document.getElementById('zify'));

// 字符颜色
function createRandomItemStyle() {
    return {
        normal: {
            color: 'rgb(' + [
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160)
            ].join(',') + ')'
        }
    };
}
var wordCloudOption2 = {
    tooltip: {
        show: true
    },
    series: [{
        name: '重大审计问题',
        type: 'wordCloud',
        size: ['100%', '100%'],
        // textRotation : [0, 45, 90, -45],
        rotationRange: [0, 0],
		textRotation: [0, 0],
        textPadding: 0,
        textStyle: {
            normal: {
                color: '#36f8ff'
            },
        },
        autoSize: {
            enable: true,
            minSize: 14
        },
        data: []
    }]
};
// 使用刚指定的配置项和数据显示图表。
wordCloudChart2.setOption(wordCloudOption2);
function getwordCloudData2(){
    $.ajax({
        type: 'GET',
        url: url+"/shenji/wordCloud?type=1&token="+token,
        data: {},
        dataType:'json',
        success: function(res){
            var data = res.data;
            var proj_title = data;
            if(res.code =="000000"){
                wordCloudOption2.series[0].data = proj_title;
                wordCloudChart2.setOption(wordCloudOption2);
            }else if(res.code == "100001"){
                location.href = '/';
            }
        }
    });
}
getwordCloudData2();

