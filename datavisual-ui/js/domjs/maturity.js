//var url = "http://10.152.101.116:8090";
var token = "9385d137-a82f-439c-bbc6-fbb5979edf4e";

// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
var zhuzt = echarts.init(document.getElementById('zhuzt'));
var zify = echarts.init(document.getElementById('zify'));
var project = echarts.init(document.getElementById("project"));
var type = echarts.init(document.getElementById("type"));
var money = echarts.init(document.getElementById("money"));

$(function(){
    //FF下用JS实现自定义滚动条
    $(".scroll").niceScroll({cursorborder:"",cursorcolor:"rgba(0,0,0,0)",boxzoom:true});
})
//jquery
$(function () {
	// 表头标题的转换
    var liranking = $(".liranking");
    var limoney = $(".limoney");
    var lirankingth = $(".liranking-th");
    var limoneyth = $(".limoney-th");
    var litimes = $(".litimes");
    var litimesed = $(".litimesed");
    var litimesth = $(".litimes-th");
    var litimesedth = $(".litimesed-th");
	var type = 4;
    liranking.click(function () {
        lirankingth.css({
            display: 'block'
        });
        limoneyth.css({
            display: 'none'
        });
        initRankOfImportance(1);
    });
    limoney.click(function () {
        lirankingth.css({
            display: 'none'
        });
        limoneyth.css({
            display: 'block'
        });
        initRankOfImportance(2);
    });
    litimes.click(function () {
        litimesth.css({
            display: 'block'
        });
        litimesedth.css({
            display: 'none'
        });
        type = 4;
        getPieData_1(4);
    });
    litimesed.click(function () {
        litimesth.css({
            display: 'none'
        });
        litimesedth.css({
            display: 'block'
        });
        type = 5;
        getPieData_1(5);

    });
	// 时间区间选择器
    $('#date-range').dateRangePicker({
        endDate: new Date()
    });
    // 升序降序按钮
    $(".ascending").click(function () {
        // trim()删除在字符串前面和后面的所有空格
        var label = $.trim($(this).html());

        if (label == "降序") {
            $(this).html("升序");
            getPieData_1(1, 'desc');
        } else {
            $(this).html("降序");
            getPieData_1(1, 'asc');
        }
    });

    // 时间区间选择器
    function time() {
        var date = new Date();
        var timestart = date.getFullYear() + '-01-01';
        var month = date.getMonth() + 1;
        var dates = date.getDate() - 1;
        if (month < 10) {
            var timemonth = '0' + month;
        } else {
            var timemonth = month;
        }
        if (dates < 10) {
            var timedate = '0' + dates;
        } else {
            var timedate = dates;
        }
        var timeend = date.getFullYear() + '-' + timemonth + '-' + timedate;
        $('#date-range').val(timestart + ' to ' + timeend);
        var param = {
            startDate: timestart,
            endDate: timeend
        }
        return getBarData_1(param);
    }
    time();
});
function getBarData_1(param = null) {}
function initRankOfImportance(type) {
    $.ajax({
        type: "GET",
        url: url + "/eval/tableChart?type=" + type + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.code == '000000') {
                var data = res.data;
                var html = '';
                html += '<tr class="liranking-th">' +
                    '<td style="width: 1%;color:#36f8ff;">&nbsp;&nbsp;</td>' +
                    '<td style="width: 4%;color:#36f8ff;">二级单位名称</td>' +
                    '<td style="width: 4%;color:#36f8ff;">公司名称</td>' +
                    '<td style="width: 2%;color:#36f8ff;">评分</td>' +
                    '<td style="width: 3%;color:#36f8ff;">平分级别</td>  </tr>'
                $.each(data, function (commentIndex, item) {
                    html += '<tr><td style="width: 1%;">' + (commentIndex + 1) + '</td>' +
                        '<td style="width: 4%;">' + item.erjidanwei + '</td>' +
                        '<td style="width: 4%;">' + item.comname + '</td>' +
						'<td style="width: 2%;">' + item.score + '</td>' +
                        '<td style="width: 3%;">' + item.category  + '</td></tr>';
                });
                $(".t_r_content .hh").html(html);
            }
            //跳转登录页面
            else if (res.code == '100001') {
                location.href = "/";
            }
            // ....
            else {
                var html = '';
                var i = 0;
                for (i = 0; i < 6; i++) {
                    html += '<tr><td style="width: 1%;">' + (i + 1) + '</td>' +
                        '<td style="width: 7%;">' + '</td>' +
                        '<td style="width: 7%;">' + '</td>' +
                        '<td style="width: 2%;">' + '</td>' +
                        '<td style="width: 3%;">' + '</td></tr>';
                };
                $(".t_r_content .hh").html(html);
            }

        }
    });
}
initRankOfImportance(1);
// 指定图表的配置项和数据
var option = {
    title: {
        show: false
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    series: [
        {
        name: '成熟度等级',
        type: 'pie',
        radius: '80%',
        center: ['50%', '60%'],
        data: [],
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
myChart.setOption(option);
function getPieData_1(type,sort = 'asc') {
    $.ajax({
        type: "GET",
        url: url + "/eval/pieChart?type="+type+"&sort="+sort+"&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if (res.code == "000000") {
				var html = '';
                html += '<tr><td style="width: 10px;color:#36f8ff;">序号</td>' +
                '<td style="width: 40px;color:#36f8ff;">成熟度级别</td>'+
                '<td style="width: 20px;color:#36f8ff;">次数</td></tr>';
                $.each(data, function(commentIndex, item){
                    html += '<tr><td style="width: 15%;">' + (commentIndex + 1) + '</td>' +
                        '<td style="width: 60%;">' + item.name + '</td>'+
                        '<td style="width: 25%;">' + item.value + '</td></tr>';
                })
                $("#sjcs").html(html);
                option.series[0].data = data;
                myChart.setOption(option);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    })
}
getPieData_1(1,'asc');
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
var option2 = {
    color: ['#daa14f', '#2999d3', ],
    title: {
        text: "各二级单位成熟度评分",
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
        x: '60',
		bottom: '75'
    },

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    calculable: true,
    xAxis: [{
        type: 'category',
        axisTick: {
            show: false
        },
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
            formatter:function(params){
				provideNumber = 2;
				newParamsName = "";
				for (var p = 0; p < params.length; p++) {
					var tempStr = ""; // 表示每一次截取的字符串
					var start = p * provideNumber; // 开始截取的位置
					var end = start + provideNumber; // 结束截取的位置
					// 此处特殊处理最后一行的索引值
					if (start+3>params.length) {
						// 最后一次不换行
						tempStr = params.substring(start, params.length-1);
					} else {
						// 每一次拼接字符串并换行
						tempStr = params.substring(start, end) + "\n";
					}

					newParamsName += tempStr; // 最终拼成的字符串			
				}
				newParamsName = newParamsName.substring(0, 11);
				return newParamsName;
			}
        },
		data: []
    }],
    yAxis: [{
		type: 'value',
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
		}
	}],
    series: [{
		// name: '审计事件数量',
		type: 'bar',
		name: '成熟度评分',
		barGap: 0,
		// data: [320, 
		data: []
	}]
};
zhuzt.setOption(option2);
function getBarData_2(type) {
    $.ajax({
        type: "GET",
        url: url + "/eval/barChart?type=" + type + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if (res.code == '000000') {
				var data1 = [];
				var data2 = [];
				$.each(data,function(index,item){
					data1.push(item.score);
					data2.push(item.erjidanwei);
				});
				option2.xAxis[0].data = data2;
				option2.series[0].data = data1;
                zhuzt.setOption(option2);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}
getBarData_2(1)
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

var option3 = {
	tooltip: {
		show: true,
        formatter: "{a} <br/>{b}"
    },
    series: [{
        name: '重大缺陷',
        type: 'wordCloud',
        size: ['80%', '80%'],
        //rotationRange: [0, 0],
		//textRotation: [0, 0],
        textPadding: 0,
		rotationRange: [0, 0],
        textRotation : [0, 0],
        autoSize: {
            enable: true,
            minSize: 14
        },
        data: [{name:'深圳航天工业',value:0},{name:'航天云网科技',value:0},{name:'湖南航天',value:0},{name:'中国航天建设集团',value:0},{name:'航天精工股份',value:0}]
    }]
};
zify.setOption(option3);
/*function getBarData_3(type) {
    $.ajax({
        type: "GET",
        url: url + "/eval/wordCloud?type=" + type + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if (res.code == '000000') {
				var data2 = [];
				$.each(data,function(index,item){
					item["itemStyle"] = createRandomItemStyle();
				});
				option3.series[0].data = data;
                zify.setOption(option3);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}*/
//getBarData_3(1)
var option4 = {
	color: ['#a0d20f','#15d2a4'],
    tooltip: {
        trigger: 'item',
        axisPointer: {
            type: 'none'
        }
    },
    legend: {
        x: 'left',
        y: 'center',
        left: "225",
        top: '80',
        // itemWidth: '12',
        itemHeight: "12",
        orient: 'vertical',
        textStyle: {
            color: '#ffffff',
        },
		data: []
    },
    calculable: true,
    series: [{
        name: '缺陷类型分布',
        type: 'pie',
        radius: "58%",
        center: ['32%', '40%'],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        label: {
            normal: {
                show: false
            },
            emphasis: {
                show: false
            }
        },
		data: []

    }]
};
project.setOption(option4);
function getBarData_4(type) {
    $.ajax({
        type: "GET",
        url: url + "/eval/pieChart?type=" + type + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if (res.code == '000000') {
				var data1 = [];
				$.each(data,function(index,item){
					data1.push(item.name);
				});
				option4.series[0].data = data;
				option4.legend.data = data1;
                project.setOption(option4);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}
getBarData_4(2)
var option5 = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	legend: {
		type:'scroll',
		//x: 'left',
		//y: 'top',
		left: "220",
		top: '-5',
		bottom: '70',
		//itemHeight: "12",
		orient: 'vertical',
		textStyle: {
			color: '#ffffff',
		},
		// data: ['内控管理', '会计核算', '合同管理', '会计基础管理', '资金管理','存货管理','招投标管理']
		data:['风控体系','组织机构','战略管理','人力资源管理','营运资金管理','全年预算和财务报告管理','资产管理','销售管理','合同管理','采购管理','全资及控股公司管控','工程项目管理','技术创新和研发管理','股权投资管理','担保和金融衍生业务管理','目标保障能力']
	},
	calculable: true,
	series: [{
		name: '缺陷分布情况',
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
		data:[{name:'风控体系',value:150},{name:'组织机构',value:130},{name:'战略管理',value:110},{name:'人力资源管理',value:60},{name:'营运资金管理',value:60},{name:'全年预算和财务报告管理',value:50},{name:'资产管理',value:50},{name:'销售管理',value:50},{name:'合同管理',value:20},{name:'采购管理',value:15},{name:'全资及控股公司管控',value:20},{name:'工程项目管理',value:15},{name:'技术创新和研发管理',value:10},{name:'股权投资管理',value:15},{name:'担保和金融衍生业务管理',value:10},{name:'目标保障能力',value:1}]
	}]
};
var option6 = {
	color: ['#a0d20f','#15d2a4','#7628d2'],
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
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
		data: []
        // data: ['限期管理', '可能导致违法坐牢', '管理建议', '通报批评', '违法违纪处分','其他']
    },
    calculable: true,
    series: [{
        name: '重要缺陷分布',
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
money.setOption(option6);
function getBarData_6(type) {
    $.ajax({
        type: "GET",
        url: url + "/eval/pieChart?type=" + type + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            if (res.code == '000000') {
				var data1 = [];
				$.each(data,function(index,item){
					data1.push(item.name);
				});
				option6.series[0].data = data;
				option6.legend.data = data1;
                money.setOption(option6);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}
getBarData_6(3)

// 使用刚指定的配置项和数据显示图表。
type.setOption(option5);


$(function() {
    // 处理列表点击事件
    var listli = $(".maturity-content-list").find("li");
    listli.click(function(){
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });
    // 表格表头的点击效果切换
    var tabletheadli = $(".maturity-content-table").find(".li");
    tabletheadli.click(function(){
        $(".li").removeClass("active").addClass("noactive");
        $(this).removeClass("noactive").addClass("active");
    });
    //审计次数
    var numtheadli = $(".maturity-content-num").find(".li");
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
    // 路径
    console.log(document.referrer);
    console.log(document.URL);
    console.log(window.location.href);

    var urlhost = window.parent.document.referrer;
    console.log(urlhost);
    // alert(urlhost);

});
//成熟度趋势图
/*function getLineData_2() {
    $.ajax({
        type: "GET",
        url: url + "/faw/lineChart?type=2" + "&token=" + token,
        data: {},
        dataType: "json",
        success: function (res) {
            var data = res.data;
            var Ldata = [];
            var Xdata = [];
            var Sdata = [];
            if (res.code == '000000') {
                $.each(data, function (index) {
                    Ldata.push(data[index].title);
                    Xdata = data[index].name;
                    Sdata.push(data[index].data);
                })
                lineOption_1.legend.data = Ldata;
                lineOption_1.xAxis[0].data = Xdata;
                lineOption_1.series[0].name = Ldata[0];
                lineOption_1.series[0].data = Sdata[0];
                lineOption_1.series[1].name = Ldata[1];
                lineOption_1.series[1].data = Sdata[1];
				lineOption_1.series[2].name = Ldata[2];
                lineOption_1.series[2].data = Sdata[2];
                lineChart_2.setOption(lineOption_2);
            } else if (res.code == '100001') {
                location.href = "/";
            }
        }
    });
}
getLineData_2();*/

var lineChart_2 = echarts.init(document.getElementById('tide'));
var lineOption_2 = {
    color: ['#d2be2b','#30b6d2','#69d227'],
    tooltip: {
        trigger: 'axis',
        axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['中国航天建设集团','航天云网科技发展有限责任公司','航天工业发展股份有限公司'],
        textStyle: {
            color: '#ffffff',
        },
        // x: "right",
        right: '40'
    },
    grid: {
        x: '0',
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    calculable: true,
    xAxis: [{
        type: 'category',
        data: ['2018-06','2018-07','2018-08','2018-09','2018-10'],
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
            interval: 0,
            // showMinLabel:true,
            // showMaxLabel:true,
        },
    }],
    yAxis: [{
            type: 'value',
            position: 'left',
            name: '(件)',
            splitNumber: 4,
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
            //刻度不显示
            axisTick: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#1B7391',
                    type: 'dashed',
                }
            },
        }
    ],
    series: [{
            name: '中国航天建设集团',
            type: 'line',
            yAxis: 0,
            itemStyle: {
                normal: {
                    // 折点颜色
                    color: '#faab41',
                    // 折线颜色
                    lineStyle: {
                        color: '#faab41'
                    }
                }
            },
            data: [298.5,323.84,286.02,285.21,284.37]
        },
        {
            name: '航天云网科技发展有限责任公司',
            type: 'line',
            yAxisIndex: 0,
            itemStyle: {
                normal: {
                    color: '#34facc',
                    lineStyle: {
                        color: '#34facc'
                    }
                }
            },
            data: [305.32,304.11,345.21,321.89,342.32]
        },
        {
            name: '航天工业发展股份有限公司',
            type: 'line',
            yAxisIndex: 0,
            itemStyle: {
                normal: {
                    color: '#34facc',
                    lineStyle: {
                        color: '#34facc'
                    }
                }
            },
            data: [334.67,310.32,312.32,356.43,332.32]
        }

    ]
};
lineChart_2.setOption(lineOption_2);

