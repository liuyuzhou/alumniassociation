
<html>
<head>
<#include "../resource.ftl"/>
<script src="/yzweb/main/js/noPageGrid.js"></script>
</head>
<body>
 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>销售实时统计</legend>
</fieldset>
<blockquote class="layui-elem-quote">
    <div class="layui-form ">
    <table class="layui-table" id="saleStatisticsTable" cyType="noPageGrid"
           cyProps="url:'/yzweb/statistics/listData',checkbox:'false',page:'false',pageColor:'#2991d9'">
        <thead>
        <tr>       			
	          <th param="{name:'realName',totalRowText: '合计'}">销售姓名\数量</th>
		            			
	          <th param="{name:'status_1',render : function(row){if(row.status_1 == 0){return '0';}else{return row.status_1;}}}">待接单</th>
	          
	          <th param="{name:'status_2',render : function(row){if(row.status_2 == 0){return '0';}else{return row.status_2;}}}">待量尺</th>
	          
	          <th param="{name:'status_100',render : function(row){if(row.status_100 == 0){return '0';}else{return row.status_100;}}}">待跟进</th>
	          
	          <th param="{name:'status_101',render : function(row){if(row.status_101 == 0){return '0';}else{return row.status_101;}}}">跟进超时</th>
	          
	          <th param="{name:'status_3',render : function(row){if(row.status_3 == 0){return '0';}else{return row.status_3;}}}">量尺超时</th>
	          
	          <th param="{name:'status_4',render : function(row){if(row.status_4 == 0){return '0';}else{return row.status_4;}}}">待报价</th>
	          
	          <th param="{name:'status_5',render : function(row){if(row.status_5 == 0){return '0';}else{return row.status_5;}}}">待收款</th>
	          
	          <th param="{name:'status_91',render : function(row){if(row.status_91 == 0){return '0';}else{return row.status_91;}}}">待收货</th>
	          
	          <th param="{name:'status_69',render : function(row){if(row.status_69 == 0){return '0';}else{return row.status_69;}}}">待安装</th>
	          
	          <th param="{name:'contractAmount',render : function(row){if(row.contractAmount == null){return '0';}else{return row.contractAmount;}}}">合同总额</th>
	          
	          <th param="{name:'gathAmount',render : function(row){if(row.gathAmount == null){return '0';}else{return row.gathAmount;}}}">收款总额</th>
	          
	          <th param="{name:'status_99'}">查看历史订单</th>
	          
	          <th param="{name:'status_200'}">订单状态</th>
	          
        </tr>
        </thead>
    </table>
</div>
</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>安装工实时统计</legend>
</fieldset>
<blockquote class="layui-elem-quote">
    <div class="layui-form ">
    <table class="layui-table" id="installerStatisticsTable" cyType="noPageGrid"
           cyProps="url:'/yzweb/statistics/installerListData',checkbox:'false',page:'false',pageColor:'#2991d9'">
        <thead>
        <tr>       			
	          <th param="{name:'realName',totalRowText: '合计'}">安装工姓名\数量</th>
		            			
	          <th param="{name:'status_10',render : function(row){if(row.status_10 == 0){return '0';}else{return row.status_10;}}}">待预约用户</th>
	          
	           <th param="{name:'status_11',render : function(row){if(row.status_11 == 0){return '0';}else{return row.status_11;}}}">待上门安装</th>
	          
	          <th param="{name:'status_13',render : function(row){if(row.status_13 == 0){return '0';}else{return row.status_13;}}}">二次安装</th>
	          
	          <th param="{name:'status_14',render : function(row){if(row.status_14 == 0){return '0';}else{return row.status_14;}}}">安装超时</th>
	          
	          <th param="{name:'status_88',render : function(row){if(row.status_88 == 0){return '0';}else{return row.status_88;}}}">售后待处理</th>
	          
	          <th param="{name:'status_99',render : function(row){if(row.status_99 == 0){return '0';}else{return row.status_99;}}}">已完成订单</th>
	          
	          <th param="{name:'contractAmount',render : function(row){if(row.contractAmount == null){return '0';}else{return row.contractAmount;}}}">合同总额</th>
	          
	          <th param="{name:'totalAmount',render : function(row){if(row.totalAmount == null){return '0';}else{return row.totalAmount;}}}">个人账户总额</th>
	          
	          <th param="{name:'availableAmount',render : function(row){if(row.availableAmount == null){return '0';}else{return row.availableAmount;}}}">个人账户可用总额</th>
	         
	     </tr>
        </thead>
    </table>
</div>
</blockquote> -->

</body>
</html>