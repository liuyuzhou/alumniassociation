<#--  lewp 2018-10-16 09:30:49-->
<!DOCTYPE html>
<html>
<head>
    <title>新闻资讯列表</title>
    <#include "../resource.ftl"/>
    <script type="text/javascript" src="/yzweb/news/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">标题:</label>
        <div class="layui-input-inline">
            <input type="text" name="title"  placeholder="请输入标题" class="layui-input">
        </div>
        <label class="layui-form-label">新闻类型:</label>
        <div class="layui-input-inline">
            <div cyType="selectTool" cyProps="codeName:'news_type'" name="type" class="layui-input-inline"></div>
        </div>
        <div class="layui-input-normal">
            <button class="layui-btn search-btn" table-id="newsTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
        <@shiro.hasPermission name="news:save">
        <button class="layui-btn" onclick="addPage('/yzweb/news/add')">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
        </@shiro.hasPermission>
</div>
<div class="layui-form ">
    <table class="layui-table" id="newsTable" cyType="pageGrid"
           cyProps="url:'/yzweb/news/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
                 <!--复选框-->
                  <th width="1%" param="{type:'checkbox'}">
                     <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
                  </th>
            			            <!--isPrimary：是否是主键-->
                   <th param="{name:'id',isPrimary:'true',hide:'true'}"></th>
            
		           <th width="10%" param="{name:'icon', render : function(row){
		             return '<img src='+ row.icon +'>'
		            }}">图标</th>			
		          <th width="25%" param="{name:'title'}">标题</th>
			            			
			      <th width="10%" param="{name:'type',codeName:'news_type'}">新闻类型</th>
			      		
		          <th width="10%" param="{name:'readyCount'}">阅读量</th>
		          
		          <th width="10%" param="{name:'releaseTime', render : function(row){return Date_Format(row.releaseTime,'yyyy-MM-dd hh:mm:ss') }}">发布时间</th>
			            			
		          <th width="8%" param="{name:'createUserName',codeName:'user'}">创建人</th>
			                        <!--isPrimary：渲染列-->
                  <th width="8%" param="{name:'state',render:'Render.customState'}">状态</th>
                  
                  <!--isPrimary：渲染列-->
                  <th width="19%" param="{operate:'true',buttons:'Render.state,Render.edit'}">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>