var _category; // 全局缓存category
function getOrdersData() {  
		
		var daterange_orders_date = $("#daterange-orders-date").text();
		var startDate_orders = daterange_orders_date.substring(0, 10);
		var endDate_orders = daterange_orders_date.substring(13, 23);
		
		
		//获得图表的options对象  
		var options = myOrdersChart.getOption();
		//通过Ajax获取数据  
		$.ajax({
			type : 'POST',
			async : true,
			url : "/yzweb/index/ordersAnalysis",
			data : {
				"startDate_orders" : startDate_orders,
				"endDate_orders" : endDate_orders
			},
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					
					options.legend.data = result.legend;
					options.series[0].data = result.reservationCount;
					options.series[1].data = result.completedCount;
//					options.series[2].data = result.installationCount;
					options.xAxis[0].data = result.category;

					myOrdersChart.hideLoading();
					myOrdersChart.setOption(options);
					
					var this_month_reservation = result.orderReservation[0].this_month_reservation;
					var last_month_reservation = result.orderReservation[0].last_month_reservation;
					var this_month_deal = result.orderDeal[0].this_month_deal;
					var last_month_deal = result.orderDeal[0].last_month_deal;
					
					
					if(this_month_reservation == null){
						this_month_reservation = 0;
					}
					if(last_month_reservation == null){
						last_month_reservation = 0;
					}
					if(this_month_deal == null){
						this_month_deal = 0;
					}
					if(last_month_deal == null){
						last_month_deal = 0;
					}
					
					console.log("this_month_reservation = "+this_month_reservation)
					console.log("last_month_reservation = "+last_month_reservation)
					console.log("this_month_deal = "+this_month_deal)
					console.log("last_month_deal = "+last_month_deal)
					
					$("#this_month_reservationOrders").text(this_month_reservation);
					$("#reservationOrders_growth_month").text(Percentage(this_month_reservation,last_month_reservation));
					if(this_month_reservation >= last_month_reservation || last_month_reservation == 0 || (this_month_reservation == 0 && last_month_reservation == 0)){
						$("#reservationOrders_growth_month_icon").removeClass("icon-jiantou-down").addClass("icon-jiantou-up");
						$("#reservationOrders_growth_month").removeClass("icon-growth-down").addClass("icon-growth-up");
					}else{
						$("#reservationOrders_growth_month_icon").removeClass("icon-jiantou-up").addClass("icon-jiantou-down");
						$("#reservationOrders_growth_month").removeClass("icon-growth-up").addClass("icon-growth-down");
					}
					
					$("#this_month_dealOrders").text(this_month_deal);
					$("#dealOrders_growth_month").text(Percentage(this_month_deal,last_month_deal));
					if(this_month_deal >= last_month_deal || last_month_deal == 0 || (this_month_deal == 0 && last_month_deal == 0)){
						$("#dealOrders_growth_month_icon").removeClass("icon-jiantou-down").addClass("icon-jiantou-up");
						$("#dealOrders_growth_month").removeClass("icon-growth-down").addClass("icon-growth-up");
					}else{
						$("#dealOrders_growth_month_icon").removeClass("icon-jiantou-up").addClass("icon-jiantou-down");
						$("#dealOrders_growth_month").removeClass("icon-growth-up").addClass("icon-growth-down");
					}
					
					
				}
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				myOrdersChart.hideLoading();
			}
		});
		
	}  
	
	function getSalesData() {  
		
		var daterange_sales_date = $("#daterange-sales-date").text();
		var startDate_sales = daterange_sales_date.substring(0, 10);
		var endDate_sales = daterange_sales_date.substring(13, 23);
		

		//获得图表的options对象  
		var options = mySalesChart.getOption();
		//通过Ajax获取数据  
		$.ajax({
			type : 'POST',
			async : true,
			url : "/yzweb/index/salesAnalysis",
			data : {
				"startDate_sales" : startDate_sales,
				"endDate_sales" : endDate_sales
		
			},
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					
					options.legend.data = result.legend;
					options.series[0].data = result.series;
					options.xAxis[0].data = result.category;

					mySalesChart.hideLoading();
					mySalesChart.setOption(options);
					
					
					var this_month_sales = result.salesComparisonData[0].this_month_sales;
					var last_month_sales = result.salesComparisonData[0].last_month_sales;
					var this_week_sales = result.salesComparisonData[0].this_week_sales;
					var last_week_sales = result.salesComparisonData[0].last_week_sales;
					
					if(this_month_sales == null){
						this_month_sales = 0;
					}
					if(last_month_sales == null){
						last_month_sales = 0;
					}
					if(this_week_sales == null){
						this_week_sales = 0;
					}
					if(last_week_sales == null){
						last_week_sales = 0;
					}
					
					console.log("this_month_sales = "+this_month_sales)
					console.log("last_month_sales = "+last_month_sales)
					console.log("this_week_sales = "+this_week_sales)
					console.log("last_week_sales = "+last_week_sales)
					
					
					
					$("#this_month_sales").text(this_month_sales);
					$("#sales_growth_month").text(Percentage(this_month_sales,last_month_sales));
					if(this_month_sales >= last_month_sales || last_month_sales == 0 || (this_month_sales == 0 && last_month_sales == 0)){
						$("#sales_growth_month_icon").removeClass("icon-jiantou-down").addClass("icon-jiantou-up");
						$("#sales_growth_month").removeClass("icon-growth-down").addClass("icon-growth-up");
					}else{
						$("#sales_growth_month_icon").removeClass("icon-jiantou-up").addClass("icon-jiantou-down");
						$("#sales_growth_month").removeClass("icon-growth-up").addClass("icon-growth-down");
					}
					
					$("#this_week_sales").text(this_week_sales);
					$("#sales_growth_week").text(Percentage(this_week_sales,last_week_sales));
//					console.log("Percentage(this_week_sales,last_week_sales) = "+Percentage(this_week_sales,last_week_sales))
					if(this_week_sales >= last_week_sales || last_week_sales == 0 || (this_week_sales == 0 && last_week_sales == 0)){
						$("#sales_growth_week_icon").removeClass("icon-jiantou-down").addClass("icon-jiantou-up");
						$("#sales_growth_week").removeClass("icon-growth-down").addClass("icon-growth-up");
					}else{
						$("#sales_growth_week_icon").removeClass("icon-jiantou-up").addClass("icon-jiantou-down");
						$("#sales_growth_week").removeClass("icon-growth-up").addClass("icon-growth-down");
					}
				}
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				mySalesChart.hideLoading();
			}
		});
	}  
	//缓存数据变量
	var category =0;
	var cateory1  =1;
	var category2=2;
	var category3=3;
	var category4=4;
	var category5=5;
	var category6=6;
	var category7=7;
	var category8=8;
	//销售额排行
	function getSalesCharts() {  
		
	
		var options = mySalesLeaderboardChart.getOption();
		$.ajax({
			type : 'post',
			async : true,
			url : "/yzweb/index/salesCharts",
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					options.series[0].data =result.amounts;
					options.yAxis[0].data =result.realnames;
					category8=result.realnames;
					mySalesLeaderboardChart.hideLoading();
					mySalesLeaderboardChart.setOption(options);
				}
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				mySalesLeaderboardChart.hideLoading();
			}
		});
	}

	
	//销售单量排行
	function getsalesOrdersCharts() {  
		
	
		var options = mySalesLeaderboardChart.getOption();
		$.ajax({
			type : 'post',
			async : true,
			url : "/yzweb/index/salesOrdersCharts",
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					category6=result.realnames;
					category7=result.nums
					//options.yAxis[0].data =result.realnames;
					//options.series[0].data =result.nums
					//mySalesLeaderboardChart.hideLoading();
					//mySalesLeaderboardChart.setOption(options);
				}
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				mySalesLeaderboardChart.hideLoading();
			}
		});
	}
	//安张师傅排行
	function getTechnicianCharts() {  
		
		
		var options = myInstallerleaderboardChart.getOption();
		$.ajax({
			type : 'post',
			async : true,
			url : "/yzweb/index/technicianCharts",
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					options.series[0].data =result.nums;
					options.yAxis[0].data =result.installerNames;
					category1=result.installerNames;
					myInstallerleaderboardChart.hideLoading();
					myInstallerleaderboardChart.setOption(options);
				}
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				myInstallerleaderboardChart.hideLoading();
			}
		});
	}
	//安装师傅积分排行
    function getIntegralCharts() {  
		
		
		var options = myInstallerleaderboardChart.getOption();
		$.ajax({
			type : 'post',
			async : true,
			url : "/yzweb/index/integralCharts",
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					//options.series[1].data =result.points;
					//options.yAxis[0].data =result.realnames;
					category2=result.realnames;
					category4=result.points;
					//myInstallerleaderboardChart.hideLoading();
					//myInstallerleaderboardChart.setOption(options);
				}
				
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				myInstallerleaderboardChart.hideLoading();
			}
		});
	}
    //安装工安装费用排行
function getCostCharts() {  
		
		
		var options = myInstallerleaderboardChart.getOption();
		$.ajax({
			type : 'post',
			async : true,
			url : "/yzweb/index/costCharts",
			dataType : "json", //返回数据形式为json  
			success : function(result) {
				if (result) {
					//options.series[1].data =result.points;
					//options.yAxis[0].data =result.realnames;
					//category2=result.realnames;
					//category4=result.points;
					//myInstallerleaderboardChart.hideLoading();
					//myInstallerleaderboardChart.setOption(options);
					category3=result.realnames;
					category5=result.contractAmounts;
					
				}
				
			},
			error : function(errorMsg) {
				layer.msg("不好意思，图表请求数据失败啦!");
				myInstallerleaderboardChart.hideLoading();
			}
		});
	}
//DateRangePicker日期插件
function init() {
	// 定义locale汉化插件
	var locale = {
		"format" : 'YYYY-MM-DD',
		"separator" : " -222 ",
		"applyLabel" : "确定",
		"cancelLabel" : "取消",
		"fromLabel" : "起始时间",
		"toLabel" : "结束时间'",
		"customRangeLabel" : "自定义",
		"weekLabel" : "W",
		"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
		"monthNames" : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月",
				"十月", "十一月", "十二月" ],
		"firstDay" : 1
	};
	// 初始化显示当前时间
	$('#daterange-orders-btn span').html(
			moment().subtract('day', 6).format('YYYY-MM-DD') + ' - '
					+ moment().format('YYYY-MM-DD'));
	$('#daterange-sales-btn span').html(
			moment().subtract('day', 6).format('YYYY-MM-DD') + ' - '
					+ moment().format('YYYY-MM-DD'));
	// 日期控件初始化
	$('#daterange-orders-btn')
			.daterangepicker(
					{
						'locale' : locale,
						'maxSpan': {
					        'days': 31
					    },
						// 汉化按钮部分
						ranges : {
							'今日' : [ moment(), moment() ],
							'昨日' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'最近7日' : [ moment().subtract(6, 'days'), moment() ],
							'最近30日' : [ moment().subtract(29, 'days'), moment() ],
							'本月' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'上月' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						},
						startDate : moment().subtract(29, 'days'),
						endDate : moment()
					},
					function(start, end) {
						$('#daterange-orders-btn span').html(
								start.format('YYYY-MM-DD') + ' ~ '
										+ end.format('YYYY-MM-DD'));
					});
	
	
	//单击应用按钮时，或单击预定义范围时触发
	$('#daterange-sales-btn').on('apply.daterangepicker',function(ev, picker) {
		//do something, like clearing an input
		getSalesData();
		
	});
	$('#daterange-orders-btn').on('apply.daterangepicker',function(ev, picker) {
		//do something, like clearing an input
		getOrdersData();
		
	});
	
	
	$('#daterange-sales-btn')
	.daterangepicker(
			{
				'locale' : locale,
				'maxSpan': {
			        'days': 31
			    },
				// 汉化按钮部分
				ranges : {
					'今日' : [ moment(), moment() ],
					'昨日' : [ moment().subtract(1, 'days'),
							moment().subtract(1, 'days') ],
					'最近7日' : [ moment().subtract(6, 'days'), moment() ],
					'最近30日' : [ moment().subtract(29, 'days'), moment() ],
					'本月' : [ moment().startOf('month'),
							moment().endOf('month') ],
					'上月' : [
							moment().subtract(1, 'month').startOf(
									'month'),
							moment().subtract(1, 'month')
									.endOf('month') ]
				},
				startDate : moment().subtract(29, 'days'),
				endDate : moment()
			},
			function(start, end) {
				$('#daterange-sales-btn span').html(
						start.format('YYYY-MM-DD') + ' ~ '
								+ end.format('YYYY-MM-DD'));
			});
	
	/**********销售人员统计数据获取***************/	
	$.ajax({
        url: '/yzweb/statistics/memberNum',     // 请求地址
        data: {"roleNum":"2"},   // 需要传送的参数
        type: 'GET',   // 请求方式
        dataType: 'json', // 返回数据的格式, 通常为JSON          
        success: function (result) {
        	if(result.code==0){
          	 	var totalCustomer=result.result.CustomerNum;
          	 	var incrementCustomer=result.result.CustomerIncrementNum;
          	 	var display = totalCustomer +"/"+incrementCustomer;
             	$("#salesmanNum").text(display);
        	}
        }
	 });
	
/**********安装人员统计数据获取***************/	
	$.ajax({
        url: '/yzweb/statistics/memberNum',     // 请求地址
        data: {"roleNum":"1"},   // 需要传送的参数
        type: 'GET',   // 请求方式
        dataType: 'json', // 返回数据的格式, 通常为JSON          
        success: function (result) {
        	if(result.code==0){
          	 	var totalCustomer=result.result.CustomerNum;
          	 	var incrementCustomer=result.result.CustomerIncrementNum;
          	 	var display = totalCustomer +"/"+incrementCustomer;
             	$("#installerNum").text(display);
        	}
       	 	
        }
	 });
	
	/**********用户统计数据获取***************/	
	$.ajax({
        url: '/yzweb/statistics/memberNum',     // 请求地址
        data: {"roleNum":"3"},   // 需要传送的参数
        type: 'GET',   // 请求方式
        dataType: 'json', // 返回数据的格式, 通常为JSON          
        success: function (result) {
        	if(result.code==0){
          	 	var totalCustomer=result.result.CustomerNum;
	          	var incrementCustomer=result.result.CustomerIncrementNum;
	          	var display = totalCustomer +"/"+incrementCustomer;
             	$("#CustomerNum").text(display);
        	}
      	 	
        },
        error: function (result) {
            //alert(result.error);
        }
	 });
	
	
};

function Percentage(number1, number2) { 
	if(number2 != 0){
		return (Math.round((number1-number2) / number2 * 10000) / 100.00 + "%");// 小数点后两位百分比
	}else if(number1 == 0 && number2 == 0 ){
		return (Math.round(0 / 1 * 10000) / 100.00 + "%");
	}else if(number2 == 0 && number1 != 0 ){
		return (Math.round(1 / 1 * 10000) / 100.00 + "%");
	}else if(number1 == 0 && number2 != 0 ){
		return (Math.round((number1-number2) / number2 * 10000) / 100.00 + "%");
	}
}

