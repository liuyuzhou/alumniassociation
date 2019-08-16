<#--  lewp 2018-10-12 09:48:52-->
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <#include "../resource.ftl"/>
    <script type="text/javascript" src="/yzweb/product/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">名称:</label>
        <div class="layui-input-inline">
            <input type="text" name="name"  placeholder="请输入名称" class="layui-input">
        </div>
        <label class="layui-form-label">产品分类:</label>
        <div class="layui-input-inline">
               <div cyType="selectTool" cyProps="codeName:'pro_category'" name="category" class="layui-input-inline"></div>
        </div>
        <label class="layui-form-label">产品状态:</label>
        <div class="layui-input-inline">
             <div cyType="selectTool" cyProps="codeName:'pro_status'" name="status" class="layui-input-inline"></div>
        </div>
            
        <div class="layui-input-normal">
            <button class="layui-btn layui-btn-green" lay-submit="" lay-filter="moreSearch">
                <i class="fa fa-chevron-down">&nbsp;</i>更多
            </button>
            <button class="layui-btn search-btn" table-id="productTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
        <div class="layui-input-normal">
            <button class="layui-btn layui-btn-green" lay-submit="" lay-filter="refresh"><i class="fa fa-refresh">&nbsp;</i>同步到APP</button>
            <#--<button class="layui-btn layui-btn-green" lay-submit="" lay-filter="export"><i class="fa fa-export">&nbsp;</i>导出商品列表</button>-->
        </div>
    </div>
    <div class="layui-form-item more-search">
       <div class="layui-form-item" style="width: 100%;">
            <label class="layui-form-label">品牌:</label>
            <div class="layui-input-inline">
                <input type="text" name="brand"  placeholder="请输入品牌" class="layui-input">
            </div>
            <label class="layui-form-label">是否置顶:</label>
            <div class="layui-input-inline">
               <div cyType="selectTool" cyProps="codeName:'whether'" name="isTop" class="layui-input-inline"></div>
           </div>
       </div>
    </div>
</form>
<div class="layui-btn-group">
        <@shiro.hasPermission name="product:save">
        <button class="layui-btn" onclick="addMyPage('/yzweb/product/add', '1200px', '880px', false)">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="product:update">
        <button class="layui-btn" onclick="updateState('置顶','productTable','/yzweb/product/setTop')">
            <i class="fa fa-pencil-square-o">&nbsp;</i>置顶
        </button>
        <button class="layui-btn" onclick="updateState('取消置顶','productTable','/yzweb/product/cacelTop')">
            <i class="fa fa-pencil-square-o">&nbsp;</i>取消置顶
        </button>
         <button class="layui-btn layui-btn-green" onclick="updateState('批量上线','productTable','/yzweb/product/online')">
            <i class="fa fa-check-square-o">&nbsp;</i>上线
        </button>
        <button class="layui-btn layui-btn-danger" onclick="updateState('批量下架','productTable','/yzweb/product/cancelOnline')">
            <i class="fa fa-expeditedssl">&nbsp;</i>下架
        </button>
        </@shiro.hasPermission>
</div>
<div class="layui-form ">
    <table class="layui-table" id="productTable" cyType="pageGrid"
           cyProps="url:'/yzweb/product/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            			            <!--isPrimary：是否是主键-->
                 <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}">商品ID</th>
            
                 <th width="10%" param="{name:'advertUrl', render : function(row){
		             return '<img src='+ row.advertUrl +'>'
		          }}">图片</th>
		          
		          <th width="10%" param="{name:'name'}">产品名称</th>
		          
		          <th width="8%" param="{name:'category',codeName : 'pro_category'}">产品分类</th>
		          
		          <th width="8%" param="{name:'brand', render : function(row){
		                                                        if(row.brandName != null){
		                                                            return row.brandName;
		                                                                }else{
		                                                                    return row.brand;
		                                                                    }
		                                                                        }
		                                                                            }">品牌</th>
		          
		          <th width="8%" param="{name:'salesVolume'}">销量</th>
		          
		          <!--  <th width="10%" param="{name:'aloneProduce',codeName : 'whether'}">是否单独生产 </th>-->
		          
		          <th width="10%" param="{name:'suggestPrice',render : function(row){return Money_Format(row.suggestPrice, 2) + '元/' + row.unitStr}}">建议零售价格</th>
		          
		          <th width="17%" param="{name:'factoryIds',render : function(row){return row.factoryNames;}}">生产厂家</th>
			            			
		          <th width="5%" param="{name:'status',codeName : 'pro_status'}">产品状态 </th>
		          
		          <th width="4%" param="{name:'isTop',codeName : 'whether'}">是否置顶 </th>
		          
		          <th width="20%" param="{operate:'true',buttons:'Render.setTop,Render.online,Render.attrConfig,Render.componentConfig,Render.edit,Render.delete'}">操作</th>
		          
        </tr>
        </thead>
    </table>
</div>
 </body>
 <script type="text/javascript">
 
//更多查询条件监听事件
 layui.use(['form'], function () {
     var form = layui.form();
     //监听提交
     form.on('submit(refresh)', function (data) {
    	 $.ajax({
             type: "post",
             url: '/yzweb/product/refreshCache',
             contentType: "application/json",
             async: false,
             dataType:"json",
             success: function (R) {
                 if (R.code === 0) {
                     if(R.msg && R.msg != ""){
                         parent.layer.msg(R.msg, {icon: 1});
                     }else{
                         parent.layer.msg('刷新成功 !', {icon: 1});
                     }
                 } else {
                     parent.layer.msg(R.msg, {icon: 5});
                 }
             },
             error: function () {
                 parent.layer.msg("系统繁忙", {icon: 5});
             }
         });
    	 return false;
     });
     //监听提交
     form.on('submit(export)', function (data) {
    	 window.open("/yzweb/getData/exportExcel1", "商品列表打印")
    	 return false;
     });
 });

 
 </script>
</html>