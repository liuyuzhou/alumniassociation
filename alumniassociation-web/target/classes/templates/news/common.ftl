<#--  新闻资讯公用页面-->
<#--  lewp 2018-10-16 09:30:49-->
<script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/ueditor.all.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/lang/zh-cn/zh-cn.js"></script>

       <input type="hidden"  name="id" value="${(model.id)!""}">

       <div class="layui-form-item">
            <label class="layui-form-label">标题<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="title" maxlength="100" lay-verify="required"
                 value="${(model.title)!""}"  placeholder="请输入标题"  class="layui-input">
            </div>
        </div>

       <div class="layui-form-item" style="width: 500px;">
           <label class="layui-form-label">新闻类型<span class="span_must">*</span></label>
           <div cyType="selectTool" cyProps="codeName:'news_type'" name="type" lay-verify="required"
                     value="${(model.type)!""}" class="layui-input-inline"></div>
       </div>
       
       <div class="layui-form-item">
            <label class="layui-form-label">图标<span class="span_must">*</span></label>
            <div class="layui-upload">
               <button class="layui-btn" id="iconBtn" type="button">上传图标</button>
               <div class="layui-upload-list">
                  <img class="layui-upload-img" id="iconDiv">
                     <p id="demoText"></p>
               </div>
               <input type="hidden" name="icon" id="icon" value="${(model.icon)!""}">
            </div>  
        </div>
       
       <div class="layui-form-item layui-form-text">
              <label class="layui-form-label">新闻简介<span class="span_must">*</span></label>
              <div class="layui-input-block">
                <textarea name="content" class="layui-textarea" lay-verify="required" placeholder="请输入新闻简介">${(model.content)!""}</textarea>
              </div>
       </div>
       
        <div class="layui-form-item layui-form-text">
              <label class="layui-form-label">新闻内容<span class="span_must">*</span></label>
              <div class="layui-input-block">
                  <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
              </div>
       </div>
        
<script>
var base = new Base64();

//覆盖UEditor中获取路径的方法

 UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
 UE.Editor.prototype.getActionUrl = function(action) {
     if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
         return '/yzweb/getData/ue_uploadImage';; //在这里返回我们实际的上传图片地址
     } else if(action == 'uploadvideo'){
     	return '/yzweb/getData/ue_uploadVideo'; //在这里返回我们实际的上传视频地址
     } else {
         return this._bkGetActionUrl.call(this, action);
     }
 }
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

ue.ready(function(){
	 var des = '${(model.descript)!""}';
	 UE.getEditor('editor').setContent(des, false);
});

layui.use('upload', function(){
   var $ = layui.jquery,upload = layui.upload, form = layui.form;
   var form = layui.form();
   
  // 显示宣传图
  var icon = $("#icon").val();
  if(icon){
	  $('#iconDiv').attr('src', icon);
  }
  //普通图片上传
  var uploadInst = upload.render({
    elem: '#iconBtn'
    ,url: '/yzweb/getData/uploadImage'
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#iconDiv').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      $('#iconDiv').attr('src', res.url); //图片链接（base64）
      $('#icon').val(res.url);
      return true;
      //上传成功
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });
  
//监听提交
  form.on('submit(submit)', function (data) {
      var url=$(this).attr("data-url");
      data.field.descript = base.encode(ue.getContent());
      data.field.editorValue = null;
      $.ajax({
          url: url,
          type: "post",
          contentType: "application/json",
          data: JSON.stringify(data.field),
          async: false,
          dataType: "json",
          success: function (R) {
              if (R.code == 0) {
                  $t.Refresh();
                  //刷新页面
                  parent.layer.msg('操作成功 !', {icon: 1});
              } else {
                  parent.layer.msg(R.msg, {icon: 5});
              }
          },
          error: function () {
              alert("系统繁忙");
          }
      });
      return false;
  });
  
 
});
</script>