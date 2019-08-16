
<html>
<head>
<#include "../resource.ftl"/>
</head>
<link rel="stylesheet" href="/yzweb/main/css/main.css">
<!-- 图标依赖，图标使用 iconfont库 -->
<link rel="stylesheet" href="/yzweb/main/css/iconfont.css">
<script type="text/javascript" src="/yzweb/main/js/main.js"></script>
<script type="text/javascript" src="/yzweb/main/js/iconfont.js"></script>

<!-- echaet图表库依赖 -->
<script type="text/javascript" src="/yzweb/main/js/echarts.common.min.js"></script>

<!-- 时间控件 daterangepicker 依赖-->
<link rel="stylesheet" href="/yzweb/main/css/bootstrap.css"/>
<link rel="stylesheet" href="/yzweb/main/css/daterangepicker.css">
<script type="text/javascript" src="/yzweb/main/js/moment.min.js"></script>
<script type="text/javascript" src="/yzweb/main/js/daterangepicker.js"></script>

<body>
<@shiro.hasPermission name="index:index">
<div id="" class="container">
	<div id="" class="" style="width: 100%;height: 160px;border: 1px solid gray;border-radius: 3px;background-color: ;">
		<div class="" style="width: 100%;height: 45px;background-color: #F3F3F3;;position: relative;">
			<span style="line-height:45px;vertical-align:middle;margin-left: 15px ">今日指标</span>
		</div>
		<div id="wrap" class="" style="width: 100%;height: 125px;">
			<div id="div1" class="wrap" style="">
				<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span  style="line-height:45px;vertical-align:middle;">待分配</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_0" style="line-height:50px;vertical-align:middle;"></span>
				</div>
			</div>
    		<div id="div2" class="wrap">
    			<div  style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待接单</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_1" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div3" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待量尺</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_2" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div4" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">量尺超时</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_3" class="" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div5" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待报价</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_4" class="" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div6" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待收款</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_5" class="" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<!-- <div id="div7" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span  style="line-height:45px;vertical-align:middle;">待下单</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_6" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div> -->
    		<div id="div8" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">跟进超时</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_101" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div9" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待收货</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_91" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div10" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待接安装单</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_10" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div11" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待预约</span>
				</div>
				<div  style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_20" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div12" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">待安装</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_30" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div13" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">已安装</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_40" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div14" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">异常单</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_50" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
    		<div id="div15" class="wrap">
    			<div style="width: 100%;height: 40px;background-color: ; border: 0px;text-align: center;">
					<span style="line-height:45px;vertical-align:middle;">已审核</span>
				</div>
				<div style="width: 100%;height: 70%;background-color:  ; border: 0px;text-align: center;">
					<span id="status_60" style="line-height:50px;vertical-align:middle;"></span>
				</div>
    		</div>
		</div>
	</div>
	<script type="text/javascript">
		$.ajax({
	        url: '/yzweb/statistics/indexStaForTime',     // 请求地址
	        type: 'GET',   // 请求方式
	        dataType: 'json', // 返回数据的格式, 通常为JSON          
	        success: function (result) {
	        	if(result.code==0){
	        		$("#status_0").text(result.status_0 || '0');
	        		$("#status_1").text(result.status_1 || '0');
	        		$("#status_2").text(result.status_2 || '0');
	        		$("#status_3").text(result.status_3 || '0');
	        		$("#status_4").text(result.status_4 || '0');
	        		$("#status_5").text(result.status_5 || '0');
	        		$("#status_6").text(result.status_6 || '0');
	        		$("#status_101").text(result.status_101 || '0');
	        		$("#status_91").text(result.status_91 || '0');
	        		$("#status_10").text(result.status_10 || '0');
	        		$("#status_20").text(result.status_20 || '0');
	        		$("#status_30").text(result.status_30 || '0');
	        		$("#status_40").text(result.status_40 || '0');
	        		$("#status_50").text(result.status_50 || '0');
	        		$("#status_60").text(result.status_60 || '0');
	        	}
	        }
		 });
	</script>
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
<!-- 	<div id="msg-notice" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;"> -->
<!-- 		<span id="" class="" style="margin-left: 15px">消息通知： </span> -->
<!-- 		<span id="" class="">金师傅 李平 于 2019-03-20 11:20:15 接单成功！ </span> -->
<!-- 	</div> -->

	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;">
		<span id="" class="" style="margin-left: 15px">团队信息</span>
	</div>
	
	<div id="" class="" style="margin-top: 15px;"></div>
		
	
	<div id="" class="" style="margin-top: 15px;"></div>
	<div id="wrap-sales" class="" style="width: 100%;height: 100px;background-color: ;border: none; ">
	<@shiro.hasPermission name="index:team">
		<div id="" class="wrap-sales" style="border: none;">
			<div id="" class="" style="line-height:45px;vertical-align:middle;">
				<span id="" class="" style="margin-left: 25px;">总用户/今日新增</span>
			</div>
			<div id="" class="" style="line-height:20px;vertical-align:middle;">
				<span>&nbsp&nbsp&nbsp</span></span><span id="CustomerNum" class="" style="margin-left: 18px;font-size: 26px;font-weight: 800;"></span>
			</div>
		</div>
	</@shiro.hasPermission>
		<div id="" class="wrap-sales" style="border: none;">
			<div id="" class="" style="line-height:45px;vertical-align:middle;">
				<span id="" class="" style="margin-left: 25px;">总销售人数/今日新增</span>
			</div>
			<div id="" class="" style="line-height:20px;vertical-align:middle;">
				<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span id="salesmanNum" class="" style="margin-left: 18px;font-size: 26px;font-weight: 800;"></span>
			</div>
		</div>
		<div id="" class="wrap-sales" style="border: none;">
			<div id="" class="" style="line-height:45px;vertical-align:middle;">
				<span id="" class="" style="margin-left: 25px;">总安装工人数/今日新增</span>
			</div>
			<div id="" class="" style="line-height:20px;vertical-align:middle;">
				<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span id="installerNum" class="" style="margin-left: 18px;font-size: 26px;font-weight: 800;"></span>
			</div>
		</div>
	</div>
	
	<script>
	
	</script>
	<!--人员统计end  -->
<!-- 	<div id="" class="" style="margin-top: 15px;"></div> -->
	
<!-- 	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;"> -->
<!-- 		<span id="" class="" style="margin-left: 15px">快捷入口 </span> -->
<!-- 	</div> -->
	
<!-- 	<div id="" class="" style="margin-top: 10px;"></div> -->
	
<!-- 	<div id="warp-quick-container" class="" style="width: 100%;height: 100px;border: none;background-color: ;"> -->
<!-- 		<div id="" class="warp-quick" style="border: none;text-align: center;"> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<i class="font iconfont" style="font-size: 36px;line-height:65px;vertical-align:middle;">&#xe612;</i> -->
<!-- 			</div> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<span id="" class="" style="line-height:15px;vertical-align:middle;">订单管理</span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div id="" class="warp-quick" style="border: none;text-align: center;background-color: ;"> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<i class="font iconfont" style="font-size: 36px;line-height:65px;vertical-align:middle;">&#xe659;</i> -->
<!-- 			</div> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<span id="" class="" style="line-height:15px;vertical-align:middle;">安装单</span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div id="" class="warp-quick" style="border: none;text-align: center;"> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<i class="font iconfont" style="font-size: 36px;line-height:65px;vertical-align:middle;">&#xe637;</i> -->
<!-- 			</div> -->
<!-- 			<div id="" class="" style=""> -->
<!-- 				<span id="" class="" style="line-height:15px;vertical-align:middle;">敬请期待...</span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	<!-- 订单	------------------------------------------------------------------- -->
	
	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;">
		<span id="" class="" style="margin-left: 15px">订单统计 </span>
	</div>
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	<div id="" class="" style="width: 100%;height: 320px;border: none;background-color: ;">
		<div id="" class="" style="width: 15%;height: 320px;border-right: 1px solid gray;float: left;background-color: ;">
			<div id="" class="" style="width: 100%;height:100px;margin-top: 10px;background-color: ;">
				<div id="" class="" style="text-align: center;">
					<span id="" class="" style="">本月预约单总数</span>
				</div>
				<div id="" class="" style="text-align: center;">
					<span id="this_month_reservationOrders" class="" style="font-size: 22px;font-weight: 600;"></span>
				</div>
				<div id="" class="" style="text-align: center;">
						<span id="reservationOrders_growth_month_icon" class="iconfont" style="color: ;"></span>
						<span id="reservationOrders_growth_month" class="iconfont" style="color: ;"></span>
					<span id="" class="">同比上月</span>
				</div>
			</div>
			
			<div id="" class="" style="width: 100%;height:100px;margin-top: 10px;background-color: ;">
				<div id="" class="" style="text-align: center;">
					<span id="" class="" style="">本月成交单总数</span>
				</div>
				<div id="" class="" style="text-align: center;">
					<span id="this_month_dealOrders" class="" style="font-size: 22px;font-weight: 600;"></span>
				</div>
				<div id="" class="" style="text-align: center;">
						<span id="dealOrders_growth_month_icon" class="iconfont" style="color: ;"></span>
						<span id="dealOrders_growth_month" class="iconfont" style="color: ;"></span>
					<span id="" class="">同比上月</span>
				</div>
			</div>
			
		</div>
		<div id="" class="" style="width: 84%;height: 320px;border: none ;float: right;background-color: ;">
			<div id="" class="date-bar" style="width: 100%;height: 35px;background-color: ;">
				<button type="button" class="btn btn-default pull-right" id="daterange-orders-btn">
		        	<i class="fa fa-calendar"></i>
		            <span id="daterange-orders-date">时间</span>
		            <i class="fa fa-caret-down"></i>
		        </button>
			</div>
			
			<div id="" class="" style="margin-top: 15px;"></div>
			
			<div id="orders" class="" style="width: 100%;height: 285px;border: none ;"></div>
		</div>
		<script type="text/javascript">
		var ordersDom = document.getElementById("orders");
		var myOrdersChart = echarts.init(ordersDom);
		var app = {};
		option = null;
		option = {
			    title : {
			        text: '近一周订单统计',
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
// 			        data:['预约单数','成交单数','安装单数']
			        data:['预约单数','成交单数']
			    },
			    toolbox: {
			        show : true,
			        feature : {
// 			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['bar', 'line']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : []
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'预约单数',
			            type:'line',
			            color: '#7FFFAA',
			            data:[],
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        },
			        {
			            name:'成交单数',
			            type:'line',
			            color: '#FF00FF',
			            data:[],
			            markLine : {
			                data : [
			                    {type : 'average', name : '平均值'}
			                ]
			            }
			        },
			        {
			            name:'安装单数',
			            type:'line',
			            color: '#FF4500',
			            data:[],
			            markLine : {
			                data : [
			                    {type : 'average', name : '平均值'}
			                ]
			            }
			        }
			    ]
			};
		if (option && typeof option === "object") {
		    myOrdersChart.setOption(option, true);
		}

		</script>
	</div>
	
<!-- 销售	------------------------------------------------------------------- -->
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;">
		<span id="" class="" style="margin-left: 15px">销售统计 </span>
	</div>
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	<div id="" class="" style="width: 100%;height: 320px;border: none;background-color: ;">
		<div id="" class="" style="width: 15%;height: 320px;border-right: 1px solid gray;float: left;background-color: ;">
			<div id="" class="" style="width: 100%;height:100px;margin-top: 10px;background-color: ;">
				<div id="" class="" style="text-align: center;">
					<span id="" class="" style="">本月销售额总数</span>
				</div>
				<div id="" class="" style="text-align: center;">
					<span id="this_month_sales" class="" style="font-size: 22px;font-weight: 600;"></span>
				</div>
				<div id="" class="" style="text-align: center;">
						<span id="sales_growth_month_icon" class="iconfont icon-jiantou-up" style="color: green;"></span>
						<span id="sales_growth_month" class="" style="color: ;"></span>
					<span id="" class="">同比上月</span>
				</div>
			</div>
			
			<div id="" class="" style="width: 100%;height:100px;margin-top: 10px;background-color: ;">
				<div id="" class="" style="text-align: center;">
					<span id="" class="" style="">本周销售额总数</span>
				</div>
				<div id="" class="" style="text-align: center;">
					<span id="this_week_sales" class="" style="font-size: 22px;font-weight: 600;"></span>
				</div>
				<div id="" class="" style="text-align: center;">
						<span id="sales_growth_week_icon" class="iconfont icon-jiantou-down" style="color: ;"></span>
						<span id="sales_growth_week" class="" style="color: ;"></span>
					<span id="" class="">同比上周</span>
				</div>
			</div>
			
		</div>
		<div id="" class="" style="width: 84%;height: 320px;border: none ;float: right;background-color: ;">
			<div id="" class="date-bar" style="width: 100%;height: 35px;background-color: ;">
				<button type="button" class="btn btn-default pull-right" id="daterange-sales-btn">
		        	<i class="fa fa-calendar"></i>
		            <span id="daterange-sales-date">时间</span>
		            <i class="fa fa-caret-down"></i>
		        </button>
			</div>
			
			<div id="" class="" style="margin-top: 15px;"></div>
			
			<div id="sales" class="" style="width: 100%;height: 285px;border: none ;"></div>
		</div>
		<script type="text/javascript">
		var salesDom = document.getElementById("sales");
		var mySalesChart = echarts.init(salesDom);
		var app = {};
		option = null;
		option = {
			title : {
				text: '近一周销售统计',
			    subtext: ''
			},
			tooltip : {
		        trigger: 'axis'
		    },
// 		    legend: {
// 		        data:['销售单数']
// 		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap : false,
		        data: []
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [],
		        type: 'line',
		        smooth: true,
                showSymbol: false,
                symbol: 'circle',
                symbolSize: 6,
		        name:'销售额',
		        smooth: true,
		        areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(16, 79, 193,1)'
                        }, {
                            offset: 1,
                            color: 'rgba(125, 178, 244,1)'
                        }], false)
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgba(16, 79, 193,1)'
                    }
                },
                lineStyle: {
                    normal: {
                        width: 0
                    }
                }
		    }]
		};
		
		if (option && typeof option === "object") {
			mySalesChart.setOption(option, true);
		}
		</script>
	</div>
	
	
	<!-- 排行榜------------------------------------------------------------------- -->
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;">
		<span id="" class="" style="margin-left: 15px">排行榜 </span>
	</div>
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
<!-- 	<div id="" class="" style="width: 100%;height: 320px;border: none;background-color: ;"> -->
	<div id="" class="" style="width: 100%;min-height: 280px;max-height:320px ;border: none;background-color: ;">
		<!-- 左侧销售额排行榜 -->
		<div id="salesLeaderboard" class="" style="width: 45%;min-height: 280px;max-height:320px ;border: none;float: left;margin: 0px 0px 0px 40px;background-color: ">
		
		</div>
		<script type="text/javascript">
		var salesLeaderboardDom = document.getElementById("salesLeaderboard");
		var mySalesLeaderboardChart = echarts.init(salesLeaderboardDom);
		var app = {};
		option = null;
		option = {
			    title : {
			        text: '销售排行榜',
// 			        subtext: '数据来自网络'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			   legend: {
			   selectedMode: 'single',
   					data: [ '销售额排行榜','成交单数排行榜'],
   					 selected: {
				    '成交单数排行榜' : false
				  }
  				},
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
// 			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'value'
// 			            boundaryGap : [0, 0.01]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'category',
			            data : ['王经理','李经理','张经理','刘经理','万经理','安经理','赵经理','何经理','金经理','贺经理']
			        }
			    ],
			    series : [
			        {
			            barMaxWidth:30,//最大宽度
 			            name: '销售额排行榜',
			            type: 'bar',
// 			            color: '#FF4500',
// 			            color: '#FFD700',
//			            color: '#FF7F50',
						color: '#00CED1',
			            data:[50, 80, 120, 150, 200, 300,320,350,380,390]
			        },
			        {
			        	barMaxWidth:30,//最大宽度
			            name:'成交单数排行榜',
 			            type:'bar',
		           data:[50, 80, 120, 150, 200, 300,320,350,380]
			        }
			    ]
			};
		if (option && typeof option === "object") {
			mySalesLeaderboardChart.setOption(option, true);
		}
		</script>
		<!-- 右侧安装工积分排行榜 -->
		<div id="installerleaderboard" class="" style="width: 45%;min-height: 280px;max-height:320px ;border: none;float: right;margin: 0px 40px 0px 0px;background-color: ">
		
		</div>
		<script type="text/javascript">
		var installerleaderboardDom = document.getElementById("installerleaderboard");
		var myInstallerleaderboardChart = echarts.init(installerleaderboardDom);
		var app = {};
		option = null;
		option = {
			    title : {
			        text: '安装师傅排行榜',
// 			        subtext: '数据来自网络'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    selectedMode: 'single',
 			         data: [ '单量','积分','安装费用'],
 			         selected: {
 			         '单量':true,
				    '积分' : false,
				    '安装费用':false	
				  }
				  
	
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
// 			            dataView : {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'value'
// 			            boundaryGap : [0, 0.01]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'category',
			            data : ['王师傅','李师傅','张师傅','刘师傅','万师傅','安师傅','赵师傅','何师傅','金师傅','贺师傅']
			        }
			    ],
			    series : [
			        {
			            barMaxWidth:30,//最大宽度
 			            name: '单量',
			            type: 'bar',
// 			            color: '#FF4500',
// 			            color: '#FFD700',
			            color: '#FF7F50',
			            data:[50, 80, 120, 150, 200, 300,320,350,380,390]
			        },
			        {
			        	barMaxWidth:30,//最大宽度
			            name:'积分',
 			            type:'bar',
		            data:[55, 90, 110, 160, 190, 320]
			        },
			        {
			        	barMaxWidth:30,//最大宽度
			            name:'安装费用',
 			            type:'bar',
		            data:[55, 90, 110, 160, 190, 320]
			        }
			    ]
			};
		window.onresize = function () {
			myOrdersChart.resize();
			mySalesChart.resize();
			myInstallerleaderboardChart.resize();
			mySalesLeaderboardChart.resize();
		};
		if (option && typeof option === "object") {
			myInstallerleaderboardChart.setOption(option, true);
		}
		</script>
		
		
	</div>
	
	<div id="" class="" style="margin-top: 15px;"></div>
	
	
	<!-- 留言公告 -->
	<div id="" class="" style="margin-top: 15px;"></div>
	
<!-- 	<div id="quick-entry" class="" style="width: 100%;height: 45px;border-radius:5px;background-color: #F3F3F3;line-height:45px;vertical-align:middle;"> -->
<!-- 		<span id="" class="" style="margin-left: 15px">公告列表 </span> -->
<!-- 	</div> -->
	
</div>

<script type="text/javascript">
$(function(){ 
	init();
	getOrdersData();
	getSalesData();
	getSalesCharts();
	getTechnicianCharts();
	getIntegralCharts();
	getCostCharts();
	getsalesOrdersCharts()
	
	// 右侧排行榜
	myInstallerleaderboardChart.on('legendselectchanged',function(obj){
	var name = obj.name;
	console.log('obj',obj);
	var options = myInstallerleaderboardChart.getOption();
	// 根据按钮名称，重新帮坐标轴赋值
    if (name === '单量') {
    	// 当ajax获取category的时候就全局保存起来，放在这里用
    	options.yAxis[0].data =category1;
    } else if (name ==='积分'){
    	options.yAxis[0].data =category2;
    	options.series[1].data=category4;
    } else if (name ==='安装费用'){
    	//options.xAxis[2].data = category;
    	options.yAxis[0].data =category3;
    	options.series[2].data=category5;
    }

    myInstallerleaderboardChart.setOption(options);
	});
	
	// 左侧排行榜
	mySalesLeaderboardChart.on('legendselectchanged',function(obj){
	var name = obj.name;
	console.log('obj',obj);
	var options = mySalesLeaderboardChart.getOption();
	// 根据按钮名称，重新帮坐标轴赋值
    if (name === '销售额排行榜') {
    	// 当ajax获取category的时候就全局保存起来，放在这里用
    	options.yAxis[0].data =category8;
    } else if (name ==='成交单数排行榜'){
    	options.yAxis[0].data =category6;
    	options.series[1].data=category7;
    } 

    mySalesLeaderboardChart.setOption(options);
	});
	
});
	
</script>
</@shiro.hasPermission>
</body>
</html>