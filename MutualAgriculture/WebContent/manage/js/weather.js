	var min=[];
	var max=[];
	var time=[];
	
	
	$.post("../jsonData/weather.json",function(data) {
		for(var i=0;i<data.length;i++){
			
			min[i]=data[i].hmin;
			max[i]=data[i].hmax;
			time[i]=data[i].date;
			console.log(data[i].date);
			create(min,max,time);	
		}	
	}, "json");

	function create(min,max,time){

	 var myChart = echarts.init(document.getElementById('profile'));	
		option = {
	    title: {
		text: '未来一个月气温变化',
		subtext: '网上数据'
	    },
	    tooltip: {
		trigger: 'axis'
	    },
	    legend: {
		data:['最高气温','最低气温']
	    },
	    toolbox: {
		show: true,
		feature: {
		    dataZoom: {
		        yAxisIndex: 'none'
		    },
		    dataView: {readOnly: false},
		    magicType: {type: ['line', 'bar']},
		    restore: {},
		    saveAsImage: {}
		}
	    },
	    xAxis:  {
		type: 'category',
		boundaryGap: false,
		data: time
	    },
	    yAxis: {
		type: 'value',
		axisLabel: {
		    formatter: '{value} °C'
		}
	    },
	    series: [
		{
		    name:'最高气温',
		    type:'line',
		    data:max,
		    markPoint: {
		        data: [
		            {type: 'max', name: '最大值'},
		            {type: 'min', name: '最小值'}
		        ]
		    },
		    markLine: {
		        data: [
		            {type: 'average', name: '平均值'}
		        ]
		    }
		},
		{
		    name:'最低气温',
		    type:'line',
		    data:min,
		    markPoint: {
		        data: [
		            {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
		        ]
		    },
		    markLine: {
		        data: [
		            {type: 'average', name: '平均值'},
		            [{
		                symbol: 'none',
		                x: '90%',
		                yAxis: 'max'
		            }, {
		                symbol: 'circle',
		                label: {
		                    normal: {
		                        position: 'start',
		                        formatter: '最大值'
		                    }
		                },
		                type: 'max',
		                name: '最高点'
		            }]
		        ]
		    }
		}
	    ]
	};

      // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);   
	} 

