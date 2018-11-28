//var url = "http://10.14.34.170:8090";
//var url = "http://localhost:8090";
var token = "9385d137-a82f-439c-bbc6-fbb5979edf4e";

$(function(){
    //FF下用JS实现自定义滚动条
    $(".scroll").niceScroll({cursorborder:"",cursorcolor:"rgba(0,0,0,0)",boxzoom:true});
})
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));        // 族谱
var zhuzt = echarts.init(document.getElementById('zhuzt'));         // 二级单位信用评分   柱状图
var zify = echarts.init(document.getElementById('project'));        // 风险事件预警       字符云
var project = echarts.init(document.getElementById("myChart"));     // 族谱
var rating = echarts.init(document.getElementById("rating"));       // 负面信息分布
var money = echarts.init(document.getElementById("money"));         // 信用风险等级

/* 信用等级 */
// 指定图表的配置项和数据
var option = {
    title: {
        show: false
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        position: function(point, params, dom, rect, size){
            return [point[0]+10,point[1]];
        },
    },
    series: [{
        name: '信用等级',
        type: 'pie',
        radius: '80%',
        center: ['50%', '60%'],
   /*     data: [{
                value: 335,
                name: '13%'
            },
            {
                value: 310,
                name: '12%'
            },
            {
                value: 234,
                name: '9%'
            },
            {
                value: 135,
                name: '5%'
            },
            {
                value: 1548,
                name: '60%'
            }
        ],*/
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        label: {
            normal: {
                position: 'inner'
            }
        },
        labelLine: {
            normal: {
                show: false
            }
        }
    }]
};
                    
// x坐标数据换行的方法
var huanhang = function (params) {
    var newParamsName = ""; // 最终拼接成的字符串
    var paramsNameNumber = params.length; // 实际标签的个数
    var provideNumber = 4; // 每行能显示的字的个数
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
        }


    } else {
        // 将旧标签的值赋给新标签
        newParamsName = params;
    }
    //将最终的字符串返回
    return newParamsName;
}
// 饼图文字换行
var huanhangPie = function (params) {
    var newParamsName = ""; // 最终拼接成的字符串
    var paramsNameNumber = params.length; // 实际标签的个数
    var provideNumber = 3; // 每行能显示的字的个数
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
            // console.log(newParamsName.substring(0, 19))
        }


    } else {
        // 将旧标签的值赋给新标签
        newParamsName = params;
    }
    //将最终的字符串返回
    return newParamsName;
}
// 指定图表的配置项和数据

// 二级单位信用评分
var option2 = {
    color: ['#482ff6', '#2999d3', ],
    title: {
        text: "二级单位信用评分",
        textStyle: {
            color: "#fff",
            fontSize:20,
            fontWeight:400,
            fontStyle: 'normal',
        },
        left: "45",
        top: "8"
    },
    // 设置图的位置
    grid: {
        top: "75",
        x: '60'
    },

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
    },
    calculable: true,
    xAxis: [{
        type: 'category',
        axisTick: {
            show: false
        },
        data: [/*'中国航天建设集团有限公司', '中国花藤工业有限公司', '湖南航天有限责任公司', '湖南航天有限责任公司', '5湖南航天有限责任公司',
            '湖南航天有限责任公司', '中国航天建设集团有限公司', '中国花藤工业有限公司', '湖南航天有限责任公司', '湖南航天有限责任公司',
            '湖南航天有限责任公司', '湖南航天有限责任公司', '中国航天建设集团有限公司', '中国花藤工业有限公司', '湖南航天有限责任公司',
            '中国航天建设集团有限公司', '中国花藤工业有限公司', '湖南航天有限责任公司'*/
        ],
        //刻度不显示
        axisTick: {
            show: false
        },
        //坐标轴
        axisLine: {
            lineStyle: {
                color: '#ffffff',
                type: 'dashed' //'dotted'虚线 'solid'实线
            },

        },
        //横坐标数值
        axisLabel: {
            color: '#ffffff',
            fontSize: '14px',
            // 解决x坐标的数据显示不全
            interval: 0 ,
            showMinLabel:true,
            showMaxLabel:true,
            formatter:huanhang
        },
    }],
    yAxis: [{
            type: 'value',
            // 0刻度对齐
            // max:function(value){
            //     return -value.min;
            // },
            // min: 'dataMin',
            splitNumber: 4,
            //刻度不显示
            axisTick: {
                show: false
            },
            name: '(分)',
            nameTextStyle: {
                color: '#ffffff',
            },
            // 轴线
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#ffffff',
                    type: 'dashed',
                }
            },
            // 轴线值
            axisLabel: {
                color: '#ffffff',
                fontSize: '14px'
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
        },
        {
            scale: true,
            type: 'value',
            name: '',
            nameTextStyle: {
                color: '#ffffff',
            },
            min: 0,
            //刻度不显示
            axisTick: {
                show: false
            },
            // 轴线
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
            // 轴线值
            axisLabel: {
                color: '#ffffff',
                fontSize: '14px'
            },
            splitLine: {
                show: false,
            },
        }
    ],
    series: [{
            name: '信用评分',
            type: 'bar',
            barGap: 0,
            data: [/*320, 332, 301, 334, 390, 420, 220, 330, 440, 230, 125, 205, 334, 390,
                450, 220, 330, 256*/
            ]
        },
        

    ]
};
function getBarData_2() {
    $.ajax({
        type: "GET",
        url: url + "/xiny/line",
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if(res.code == "000000"){
                var BarData_2= data.score;
                // var BarTitle_2 = [];
                var BarName_2 = data.name;
                option2.xAxis[0].data = BarName_2;
                option2.series[0].data = BarData_2;
                zhuzt.setOption(option2);       // 二级单位信用评分
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    })
}
getBarData_2();


var itemStyle = function createRandomItemStyle() {
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

var option3 = {
    tooltip: {
        show: true,
        formatter: "{a} <br/>{b}"
    },
    series: [{
        name: '风险事件预警',
        type: 'wordCloud',
        size: ['100%', '100%'],
        gridSize: 10,
        size:40,
        center:['50%','50%'],
        left: 110,
        top: 1,
        below:20,
        // sizeRange:[20,70],
        rotationRange: [0, 0],
        textRotation : [0, 0],
        textPadding: 0,
        textStyle : {
            normal : {
                color :  '#36f8ff'
            },
        },
        autoSize: {
            enable: true,
            minSize: 14,
            color :  '#36f8ff'
        },
        data: []
    }]
};
// 风险事件预警
function getwordCloudData(){
    $.ajax({
        type: 'GET',
        url: url+"/xiny/cloudChart",
        data: {},
        dataType:'json',
        success: function(res){
            var data = res.data;
            var proj_title ;

            if(res.code =="000000"){
                $.each(data,function (index) {
                    var oCar = new Object;
                    oCar.name = data[index].orgname+''+data[index].warnname;
                    oCar.value = Math.floor(Math.random()*(88888-5+1)+5);
                    oCar.itemStyle = itemStyle;
                    option3.series[0].data[index] = oCar;
                })
                // option3.series[0].data = proj_title;
                zify.setOption(option3);        // 风险事件预警         字符云
                // wordCloudChart.setOption(option3);
            }else if(res.code == "100001"){
                location.href = '/';
            }
        }
    });
}
getwordCloudData();

//负面信息分布
var pieOption_3 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        position: function(point, params, dom, rect, size){
            return [point[0]+10,point[1]];
        },
    },
    legend: {
        x: 'left',
        y: 'center',
        left: "220",
        top: '20',
        type:'scroll',
        height:170,
        width:20,
        // itemWidth: '12',
        itemHeight: "12",
        orient: 'vertical',
        textStyle: {
            color: '#ffffff',
        },
        data: [],
        selected: {},
        formatter: function (name) {
            return echarts.format.truncateText(name, 90, '1px Microsoft Yahei', '…');
        },
        tooltip: {
            show: true
        },
    },
    calculable: true,
    series: [{
        name: '问题金额',
        type: 'pie',
        radius: "58%",
        center: ['32%', '40%'],
        label: {
            normal: {
                show: false,
                position: 'inner',
                formatter: '{b}:{c}:{d}%',
                textStyle: {
                    fontSize: 12,
                    fontWeight:'normal'
                }
            },
        },

        data: []

    }]
};
// pieChart_3.setOption(pieOption_3);

function getPieData_3() {
    $.ajax({
        type:"GET",
        url: url + "/xiny/pieWarn",
        data: {},
        dataType: "json",
        success: function(res){
            var data = res.data;
            var project_title = [];
            var _pieSelected3 = [];
            if(res.code =='000000'){
                project_title = data;
                var _pieTitle3 = [];
                $.each(data,function(index){
                    _pieTitle3.push(project_title[index].name);
                    if(index >= 5){
                        _pieSelected3.push(project_title[index].name);
                    }
                });
                // 默认选中5条数据
                var selectResult = {};
                for(var a=0; a<_pieSelected3.length; a++) {
                    selectResult[_pieSelected3[a]] = false;
                }
                pieOption_3.legend.selected = selectResult;

                pieOption_3.legend.data = _pieTitle3;
                pieOption_3.series[0].data = project_title;
                project.setOption(pieOption_3);
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    });
}
getPieData_3();

// 信用等级
var option5 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        position: function(point, params, dom, rect, size){
            return [point[0]+10,point[1]];
        },
    },
    legend: {
        x: 'left',
        y: 'center',
        left: "220",
        top: '20',
        itemHeight: "12",
        orient: 'vertical',
        textStyle: {
            color: '#ffffff',
        },
         data: []
    },
    calculable: true,
    series: [{
        name: '信用等级',
        type: 'pie',
        radius: "58%",
        center: ['32%', '40%'],
        label: {
            normal: {
                show: false
            },
            // 去掉高亮时的指示线和名称
            emphasis: {
                show: false
            }
        },
        data: [/*{
                value: 20,
                name: 'AA'
            },
            {
                value: 200,
                name: 'A+'
            },
            {
                value: 180,
                name: 'A'
            },
            {
                value: 120,
                name: 'BB'
            },
            {
                value: 111,
                name: 'B+'
            },
            {
                value: 110,
                name: 'B'
            },
            {
                value: 100,
                name: 'CC'
            },
            {
                value: 80,
                name: 'C+'
            },
            {
                value: 50,
                name: 'C'
            },*/
        ]

    }]
};
// 信用等级
function getPieData_5() {
    $.ajax({
        type: "GET",
        url: url + "/xiny/pieChart",
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            var project_title = [];
            var _pieSelected5 = [];
            if (res.code == '000000') {
                project_title = data;
                var _pieTitle5 = [];
                $.each(data, function (index) {
                    _pieTitle5.push(project_title[index].name);
                    if (index >= 5) {
                        _pieSelected5.push(project_title[index].name);
                    }
                });
                // 默认选中5条数据
                var selectResult = {};
                for (var a = 0; a < _pieSelected5.length; a++) {
                    selectResult[_pieSelected5[a]] = false;
                }
                option5.legend.selected = selectResult;
                option5.legend.data = _pieTitle5;
                option5.series[0].data = project_title;
                rating.setOption(option5);      // 信用等级
                // pieChart_5.setOption(pieOption_5);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}
getPieData_5();

var option6 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        position: function(point, params, dom, rect, size){
            return [point[0]+10,point[1]];
        },
    },
    legend: {
        x: 'left',
        y: 'center',
        left: "220",
        top: '20',
        // itemWidth: '12',
        itemHeight: "12",
        orient: 'vertical',
        textStyle: {
            color: '#ffffff',
        },
         data: ['1级', '2级', '3级', '4级', '5级']
    },
    calculable: true,
    series: [{
        name: '信用风险等级',
        type: 'pie',
        radius: "58%",
        center: ['32%', '40%'],
        label: {
            normal: {
                show: false,
                position: 'inner',
                formatter: '{b}:{c}:{d}%',
                textStyle: {
                    fontSize: 12,
                    fontWeight:'normal'
                }
            },
        },
        
        data: []

    }]
};
function getPieData_6() {
    $.ajax({
        type:"GET",
        url: url+"/xiny/pieChartWarn",
        data: {},
        dataType: "json",
        success: function(res){
            var proj_title = [];
            var data = res.data;
            var _pieSelected1 = [];
            if(res.code =='000000'){
                proj_title = data;
                var _pieTitle1 = [];
                $.each(data,function(index){
                    _pieTitle1.push(proj_title[index].name);
                    if(index >= 5){
                        _pieSelected1.push(proj_title[index].name);
                    }
                });
                // 默认选中5条数据
                var selectResult = {};
                for(var a=0; a<_pieSelected1.length; a++) {
                    selectResult[_pieSelected1[a]] = false;
                }
                option6.legend.selected = selectResult;

                option6.series[0].data = proj_title;
                option6.legend.data = _pieTitle1;
                money.setOption(option6);
                // money.setOption(option6);       // 信用风险等级
            }else if(res.code == '100001'){
                location.href="/";
            }
        }
    });
}
getPieData_6();

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);      // 族谱


$(function() {
    // 处理列表点击事件
    var listli = $(".credit-content-list").find("li");
    listli.click(function(){
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });
    // 表格表头的点击效果切换
    var tabletheadli = $(".credit-content-table").find(".li");
    tabletheadli.click(function(){
        $(".li").removeClass("active").addClass("noactive");
        $(this).removeClass("noactive").addClass("active");
    });
    //审计次数
    var numtheadli = $(".credit-content-num").find(".li");
    numtheadli.click(function(){
        console.log(1);
        $(".li").removeClass("active").addClass("noactive");
        $(this).removeClass("noactive").addClass("active");
    });
    // 翻页页码效果
    var pageli = $(".page").find("li");
    pageli.click(function(){
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });
    // 时间区间选择器
    $('#date-range').dateRangePicker({
        endDate: new Date()
    });
    // 升序降序按钮
    $(".ascending").click(function(){
        // trim()删除在字符串前面和后面的所有空格
        var label = $.trim($(this).html());
        
        if(label == "降序"){
            $(this).html("升序");

        }else{
            $(this).html("降序");
        }
    });
});

var sort =1;
//时间戳转化
function gettime(time){
    var date = new Date(time)
    return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate()+ '日'
}
// 升序降序按钮
$(".ascending").click(function(){
    // trim()删除在字符串前面和后面的所有空格
    var label = $.trim($(this).html());

    if(label == "降序"){
        $(this).html("升序");
        sort = 1;
        initRankOfImportance()
    }else{
        $(this).html("降序");
        sort = 2;
        initRankOfImportance()
    }
});

//  集团公司信用排名
function initRankOfImportance(type) {
    $.ajax({
        type: "GET",
        url: url+"/xiny/tableChart",
        data: {},
        dataType: "json",
        success: function(res){
            if (res.code == '000000') {
                var data = res.data;
                if(sort == 1){
                    var data = res.data
                }else {
                    var data = res.data.reverse()
                }
                var html = '';
                html += '<tr class="liranking-th">'+
                    '<td style="width: 1%;color:#36f8ff;">&nbsp;&nbsp;</td>' +
                    '<td style="width: 20%;color:#36f8ff;">单位名称</td>' +
                    '<td style="width: 10%;color:#36f8ff;">信用评分</td>' +
                    '<td style="width: 10%;color:#36f8ff;">信用等级</td>' +
                    '<td style="width: 11%;color:#36f8ff;">评分日期</td>' +
                    '<td style="width: 10%;color:#36f8ff;">践约评估</td>' +
                    '<td style="width: 10%;color:#36f8ff;">经营能力</td>' +
                    '<td style="width: 10%;color:#36f8ff;">企业特质</td>' +
                    '<td style="width: 8%;color:#36f8ff;">供应链</td>' +
                    '<td style="width: 8%;color:#36f8ff;">涉税信用</td>  </tr>'
                $.each(data, function(commentIndex, item){
                    html += '<tr><td style="width: 1%;">' + (commentIndex + 1) + '</td>' +
                        '<td style="width: 20%;">' + item.orgname + '</td>' +
                        '<td style="width: 10%;">' + item.creditscore + '</td>' +
                        '<td style="width: 10%;">' + item.creditlevel + '</td>' +
                        '<td style="width: 11%;">' + item.assesstime + '</td>' +
                        '<td style="width: 10%;">' + item.promisescore + '</td>' +
                        '<td style="width: 10%;">' + item.managescore + '</td>' +
                        '<td style="width: 10%;">' + item.specialityscore + '</td>' +
                        '<td style="width: 8%;">' + item.chainscore + '</td>' +
                        '<td style="width: 8%;">' + item.taxscore + '</td></tr>';
                });
                $(".t_r_content .hh").html(html);
            }
            //跳转登录页面
            else if(res.code == '100001'){
                location.href="/";
            }
            // ....
            else {
                var html = '';
                var i = 0;
                for(i = 0; i< 6; i++) {
                    html += '<tr><td style="width: 1%;">' + (i + 1) + '</td>' +
                        '<td style="width: 20%;">'  + '</td>'+
                        '<td style="width: 10%;">' + '</td>'+
                        '<td style="width: 10%;">'  + '</td>'+
                        '<td style="width: 10%;">' + '</td>'+
                        '<td style="width: 9%;">' + '</td>'+
                        '<td style="width: 9%;">' + '</td>'+
                        '<td style="width: 9%;">' + '</td>'+
                        '<td style="width: 9%;">' + '</td>'+
                        '<td style="width: 9%;">'+ '</td>'+
                        '<td style="width: 5%;">' + '</td>'+
                        '<td style="width: 5%;">' + '</td>'+
                        '<td style="width: 5%;">' + '</td></tr>';
                };
                $(".t_r_content .hh").html(html);
            }
        }
    });
}
initRankOfImportance(1);


//族谱
var settings="{\"msg\":\"success\",\"code\":200,\"data\":[{\"name\":\"李小强\",\"id\":\"1983076241\",\"category\":1},{\"name\":\"王强\",\"id\":\"2353017796\",\"category\":1},{\"name\":\"爱信诺征信有限公司江苏分公司\",\"id\":\"3121236098\",\"category\":0},{\"name\":\"陈仕俗\",\"id\":\"2247806292\",\"category\":1},{\"name\":\"爱信诺征信有限公司\",\"itemStyle\":{\"normal\":{\"color\":\"#13D1BE\"}},\"id\":\"2313797533\",\"category\":0},{\"name\":\"金端峰\",\"id\":\"2313797534\",\"category\":1},{\"name\":\"航天信息股份有限公司\",\"id\":\"12651711\",\"category\":0}],\"links\":[{\"lineStyle\":{\"normal\":{\"color\":\"#98CDDF\"}},\"id\":\"269246803\",\"source\":\"1983076241\",\"label\":{\"normal\":{\"formatter\":\"董事\",\"show\":true}},\"target\":\"2313797533\"},{\"lineStyle\":{\"normal\":{\"color\":\"#98CDDF\"}},\"id\":\"295028711\",\"source\":\"2353017796\",\"label\":{\"normal\":{\"formatter\":\"监事\",\"show\":true}},\"target\":\"2313797533\"},{\"lineStyle\":{\"normal\":{\"color\":\"#98CDDF\"}},\"id\":\"44464859\",\"source\":\"2247806292\",\"label\":{\"normal\":{\"formatter\":\"董事长|法人\",\"show\":true}},\"target\":\"2313797533\"},{\"lineStyle\":{\"normal\":{\"color\":\"#98CDDF\"}},\"id\":\"41552334\",\"source\":\"2313797533\",\"label\":{\"normal\":{\"formatter\":\"分支机构\",\"show\":true}},\"target\":\"3121236098\"},{\"lineStyle\":{\"normal\":{\"color\":\"#A2BC63\"}},\"id\":\"343726919\",\"source\":\"2313797534\",\"label\":{\"normal\":{\"formatter\":\"法人\",\"show\":true}},\"target\":\"3121236098\"},{\"lineStyle\":{\"normal\":{\"color\":\"#98CDDF\"}},\"id\":\"264334531\",\"source\":\"2313797534\",\"label\":{\"normal\":{\"formatter\":\"董事|经理\",\"show\":true}},\"target\":\"2313797533\"},{\"lineStyle\":{\"normal\":{\"color\":\"#F47A53\"}},\"id\":\"42837407\",\"source\":\"12651711\",\"label\":{\"normal\":{\"formatter\":\"参股\",\"show\":true}},\"target\":\"2313797533\"}],\"exitNodes\":[\"1983076241\",\"2353017796\",\"2247806292\",\"3121236098\",\"2313797533\",\"2313797534\",\"12651711\"]}";
function initgraph(){
    //var myChart = graph.init(_jqueryDom[0]);
    var myChart = echarts.init(document.getElementById('zupt'));
    var option =  {
        toolbox: {
            right: '2%',
            feature: {
                saveAsImage: {

                }
            }
        },
        tooltip: {
            show: false,
            tigger: 'item'
        },
        legend: {
            data: ['企业','自然人']
        },
        animation: false,
        series : [
            {
                type: 'graph',
                layout: 'force',
                draggable: true,
                symbolSize: 50,
                categories: [
                    {
                        name: '企业',
                        itemStyle: {
                            normal: {
                                color: '#2394CE'
                            }
                        }
                    },
                    {
                        name: '自然人',
                        itemStyle: {
                            normal: {
                                color: '#F25A29'
                            }
                        }
                    }
                ],
                focusNodeAdjacency: true,
                edgeSymbol: ['none', 'arrow'],
                data: [
                    {
                        "name": "李小强",
                        "id": "1983076241",
                        "category": 1
                    },
                    {
                        "name": "王强",
                        "id": "2353017796",
                        "category": 1
                    },
                    {
                        "name": "爱信诺征信有限公司江苏分公司",
                        "id": "3121236098",
                        "category": 0
                    },
                    {
                        "name": "陈仕俗",
                        "id": "2247806292",
                        "category": 1
                    },
                    {
                        "name": "爱信诺征信有限公司",
                        "itemStyle": {
                            "normal": {
                                "color": "#13D1BE"
                            }
                        },
                        "id": "2313797533",
                        "category": 0
                    },
                    {
                        "name": "金端峰",
                        "id": "2313797534",
                        "category": 1
                    },
                    {
                        "name": "航天信息股份有限公司",
                        "id": "12651711",
                        "category": 0
                    }
                ],
                links:[
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#98CDDF"
                            }
                        },
                        "id": "269246803",
                        "source": "1983076241",
                        "label": {
                            "normal": {
                                "formatter": "董事",
                                "show": true
                            }
                        },
                        "target": "2313797533"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#98CDDF"
                            }
                        },
                        "id": "295028711",
                        "source": "2353017796",
                        "label": {
                            "normal": {
                                "formatter": "监事",
                                "show": true
                            }
                        },
                        "target": "2313797533"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#98CDDF"
                            }
                        },
                        "id": "44464859",
                        "source": "2247806292",
                        "label": {
                            "normal": {
                                "formatter": "董事长|法人",
                                "show": true
                            }
                        },
                        "target": "2313797533"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#98CDDF"
                            }
                        },
                        "id": "41552334",
                        "source": "2313797533",
                        "label": {
                            "normal": {
                                "formatter": "分支机构",
                                "show": true
                            }
                        },
                        "target": "3121236098"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#A2BC63"
                            }
                        },
                        "id": "343726919",
                        "source": "2313797534",
                        "label": {
                            "normal": {
                                "formatter": "法人",
                                "show": true
                            }
                        },
                        "target": "3121236098"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#98CDDF"
                            }
                        },
                        "id": "264334531",
                        "source": "2313797534",
                        "label": {
                            "normal": {
                                "formatter": "董事|经理",
                                "show": true
                            }
                        },
                        "target": "2313797533"
                    },
                    {
                        "lineStyle": {
                            "normal": {
                                "color": "#F47A53"
                            }
                        },
                        "id": "42837407",
                        "source": "12651711",
                        "label": {
                            "normal": {
                                "formatter": "参股",
                                "show": true
                            }
                        },
                        "target": "2313797533"
                    }
                ],
                roam: true,
                label: {
                    normal: {
                        show: true,
                        position: 'bottom',
                    }
                },
                force: {
                    layoutAnimation: false,
                    repulsion: 400,	//斥力
                    edgeLength: 200
                },
                animationEasing: 'sinusoidalIn'
            }
        ]
    };
    myChart.setOption(option);
}
initgraph();
/*function getGraphData() {
    
}*/

