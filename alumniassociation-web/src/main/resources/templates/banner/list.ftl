<#--  chenyi 2018-10-29 11:57:44-->
<!DOCTYPE html>
<html>
<head>
    <title>首页列表</title>
    <#include "../resource.ftl"/>
    <script type="text/javascript" src="/yzweb/banner/js/list.js"></script>
<style>

</style>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">名称:</label>
        <div cyType="selectTool" cyProps="codeName:'banner_type'" name="type"
                     value="${(model.type)!""}" class="layui-input-inline"></div>
        <div class="layui-input-normal">
            <button class="layui-btn search-btn" table-id="newsTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
        <button class="layui-btn" onclick="addPage('/yzweb/banner/add')">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
</div>
<div class="layui-form ">
    <table class="layui-table" id="bannerTable" cyType="pageGrid"
           cyProps="url:'/yzweb/banner/listData',checkbox:'true',pageColor:'#2991d9'" >
        <thead>
        <tr>
                 <!--复选框-->
                 <th width="1%" param="{type:'checkbox'}">
                     <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
                 </th>
            			            <!--isPrimary：是否是主键-->
                 <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}"></th>

                 <th width="10%" param="{name:'adverturl', render : function(row){
		             return '<img src='+ row.adverturl +'>'
		          }}">图标</th>
			            			
		          <th width="10%" param="{name:'url',render:'Render.urlState'}">跳转链接</th>
		          
		          <th width="10%" param="{name:'type',codeName:'banner_type'}">类型</th>
			                        <!--isPrimary：渲染列-->
            <th width="10%" param="{name:'state',enumName:'StateEnum',render:'Render.customState'}">状态</th>
            <th width="10%" param="{operate:'true',buttons:'Render.state,Render.edit'}">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
<script>
    function openWindow(url){
        window.open("http://"+url,"_blank")
    }
</script>
</html>