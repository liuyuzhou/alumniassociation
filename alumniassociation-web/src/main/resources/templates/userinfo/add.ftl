<!DOCTYPE html>
<html>
<head>
    <title>校友列表</title>
    <#include "../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
    
       <div class="layui-form-item">
            <label class="layui-form-label">姓名<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="realname" value="" lay-verify="required" placeholder="请输入姓名"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">学院名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="college" value="" lay-verify="required" placeholder="请输入学院名称"
                      autocomplete="off" class="layui-input">
            </div>
        </div>

       <div class="layui-form-item">
            <label class="layui-form-label">专业名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="major" value="" lay-verify="required" placeholder="请输入专业名称"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">入学时间<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="entranceTime" value="" lay-verify="required" placeholder="请输入入学时间"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item">
            <label class="layui-form-label">手机号<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="phoneNum" value="" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item">
            <label class="layui-form-label">毕业时间<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="graduationTime" value="" lay-verify="required" placeholder="请输入毕业时间"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">当前所在地<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="currLocation" value="" lay-verify="required" placeholder="请输入当前所在地"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">行业技能<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="industrySkill" value="" lay-verify="required" placeholder="请输入行业技能"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item">
            <label class="layui-form-label">兴趣爱好</label>
            <div class="layui-input-normal">
                <input type="text" name="hobby" value="" placeholder="请输入hobby"
                      autocomplete="off" class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item layui-form-text">
              <label class="layui-form-label">个人说明<span class="span_must">*</span></label>
               <div class="layui-input-block">
                  <textarea name="personalProfile" class="layui-textarea" lay-verify="required" placeholder="请输入个人说明,最多250个字"></textarea>
              </div>
       </div>
       
       
        <!--  
        <div class="layui-form-item">
          <label class="layui-form-label">部门名称<span class="span_must">*</label>
            <div class="layui-input-normal">
               <input value="${(model.parentOrgId)!""}" id="demo"
               cyType="treeTool" cyProps="url:'/yzweb/organize/select',name:'orgId',filter:'orgId'"
               placeholder="请选择部门" class="layui-input"/>
            </div>
        </div>-->

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div cyType="radioTool" cyProps="enumName:'StateEnum'" name="userStatus"
                 value="1" class="layui-input-inline"></div>
        </div>

        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/yzweb/userinfo/save"><i
                            class="fa fa-floppy-o">&nbsp;</i>保存
                    </button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>

