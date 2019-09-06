<!DOCTYPE html>
<html>
<head>
    <title>校友列表</title>
    <#include "../resource.ftl"/>
    <script type="text/javascript" src="/yzweb/userinfo/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名:</label>
        <div class="layui-input-inline">
            <input type="text" name="realName" placeholder="请输入用户名" class="layui-input">
        </div>
        <label class="layui-form-label">手机号:</label>
        <div class="layui-input-inline">
            <input type="text" name="phoneNum" placeholder="请输入手机号" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="userTable" lay-submit="" lay-filter="search"><i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
<@shiro.hasPermission name="sys:user:save">
    <button class="layui-btn" onclick="add('/yzweb/userinfo/add')"><i class="fa fa-plus">&nbsp;</i>增加</button>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:user:update">
    <button class="layui-btn" onclick="edit('userTable','/yzweb/userinfo/edit')" style="margin-left: 5px!important;">
        <i class="fa fa-pencil-square-o">&nbsp;</i>修改
    </button>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:user:delete">
    <button class="layui-btn" onclick="deleteBatch('userTable','/yzweb/userinfo/delete');" style="margin-left: 5px!important;">
        <i class="fa fa-trash-o">&nbsp;</i>删除
    </button>
</@shiro.hasPermission>

</div>
<div class="layui-form ">
    <table class="layui-table" id="userTable" cyType="pageGrid"
           cyProps="url:'/yzweb/sys/user/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'userId',isPrimary:'true',hide:'true'}">用户ID</th>
            <th width="10%" param="{name:'wechatImage', render : function(row){
		             return '<img src='+ row.wechatImage +'>'
		          }}">图片</th>
            <th width="10%" param="{name:'realName',sort:'true'}">姓名</th>
            <th width="10%" param="{name:'sex',sort:'true'}">性别</th>
            <th width="10%" param="{name:'college',sort:'true'}">学院</th>
            <th width="10%" param="{name:'major',sort:'true'}">专业名称</th>
            <th width="10%" param="{name:'entranceTime',sort:'true'}">入学时间</th>
            <th width="10%" param="{name:'graduationTime',sort:'true'}">毕业时间</th>
            <th width="10%" param="{name:'phoneNum',sort:'true'}">手机号</th>
            <th width="10%" param="{name:'hobby',sort:'true'}">兴趣爱好</th>
            <th width="10%" param="{name:'industrySkill',sort:'true'}">行业技能</th>
            <th width="10%" param="{name:'currLocation',sort:'false'}">所在地</th>
            <th width="10%" param="{name:'userStatus',sort:'true'}">状态</th>
            <th width="10%" param="{name:'createTime',sort:'true'}">创建时间</th>
        </tr>
        </thead>
    </table>
</div>

</body>
</html>