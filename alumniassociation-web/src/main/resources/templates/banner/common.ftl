<#--  首页公用页面-->
<#--  chenyi 2018-10-29 11:57:44-->

         <div class="layui-form-item" style="width: 500px;">
           <label class="layui-form-label">banner类型<span class="span_must">*</span></label>
           <div cyType="selectTool" cyProps="codeName:'banner_type'" name="type" lay-verify="required"
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
               <input type="hidden" name="adverturl" id="advertUrl" value="${(model.adverturl)!""}" lay-verify="required" maxlength="100">
            </div>  
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">跳转链接<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="url" maxlength="255" lay-verify="required"
                 value="${(model.url)!""}"  placeholder="请输入跳转链接"  class="layui-input">
            </div>
        </div>




        
<script>
layui.use('upload', function(){
   var $ = layui.jquery,upload = layui.upload, form = layui.form;
   var form = layui.form();
   form.render();
   
  // 显示宣传图
  var advertUrl = $("#advertUrl").val();
  console.log(advertUrl)
  if(advertUrl){
	  $('#iconDiv').attr('src', advertUrl);
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
      $('#advertUrl').val(res.url);
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
  
 
});
</script>