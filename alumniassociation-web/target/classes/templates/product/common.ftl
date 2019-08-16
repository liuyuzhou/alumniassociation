<#--  商品公用页面-->
<#--  lewp 2018-10-12 09:48:52-->
 <script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/ueditor.all.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/yzweb/ueditor/lang/zh-cn/zh-cn.js"></script>
        <input type="hidden" name="id" value="${(model.id)!""}">
        
        <div class="layui-form-item" style="width: 100%;">
            <label class="layui-form-label">商品名称<span class="span_must">*</span></label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="name" maxlength="100" lay-verify="required"
                 value="${(model.name)!""}"  placeholder="请输入商品名称"  class="layui-input">
            </div>
            <label class="layui-form-label">商品标题<span class="span_must">*</span></label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="title" maxlength="100" lay-verify="required"
                 value="${(model.title)!""}"  placeholder="请输入商品标题"  class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
              <label class="layui-form-label">产品简介<span class="span_must">*</span></label>
               <div class="layui-input-block">
                  <textarea name="summary" class="layui-textarea" lay-verify="required" placeholder="请输入产品简介,最多250个字">${(model.summary)!""}</textarea>
              </div>
       </div>
       
       <div class="layui-form-item layui-form-text">
              <label class="layui-form-label">产品规格参数<span class="span_must">*</span></label>
              <div class="layui-input-block">
                  <textarea name="specification" class="layui-textarea" lay-verify="required" placeholder="请输入产品规格参数,最多250个字">${(model.specification)!""}</textarea>
              </div>
       </div>
       <div class="layui-form-item">
            <label class="layui-form-label">宣传图<span class="span_must">*</span></label>
            <div class="layui-upload">
               <button class="layui-btn" id="advertUrlBtn" type="button">上传图片</button>
               <div class="layui-upload-list">
                  <img class="layui-upload-img" id="advertUrlDiv">
                     <p id="demoText"></p>
               </div>
               <input type="hidden" name="advertUrl" id="advertUrl" value="${(model.advertUrl)!""}">
            </div>  
        </div>
       
        <div class="layui-form-item">
            <label class="layui-form-label">产品描述<span class="span_must">*</span></label>
            <div class="layui-input-block">
                  <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
              </div>
        </div>
        

       <div class="layui-form-item">
            <label class="layui-form-label">展示图片</label>
            <div class="layui-upload">
               <button class="layui-btn" id="showImagesBtn" type="button">上传多张展示图片</button> 
               <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                                            预览图：
               <div class="layui-upload-list" id="showImagesDiv"></div>
               <input type="hidden" id="showImages" name="showImages"  value="${(model.showImages)!""}">
               </blockquote>
           </div>
        </div>
        
        <div class="layui-form-item" style="width: 100%;">
           <label class="layui-form-label">产品分类<span class="span_must">*</span></label>
           <div cyType="selectTool" cyProps="codeName:'pro_category',filter:'category'" name="category"  lay-verify="required" style="width: 400px;"
                     value="${(model.category)!""}" class="layui-input-inline"></div>
            <label class="layui-form-label">产品子分类</label>
            <div class="layui-input-normal" id="tags"  style="width: 400px;"></div>
       </div>
       
       <div class="layui-form-item" style="width: 100%;">
            <label class="layui-form-label">型材厚度</label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="profileThicks" maxlength="5,2"
                 value="${(model.profileThicks)!""}"  placeholder="请输入型材厚度（mm）"  class="layui-input">
            </div>
            
            <label class="layui-form-label">起步计费单位</label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="minBillUnit" maxlength="20"
                 value="${(model.minBillUnit)!""}"  placeholder="起步计费单位（m²）"  class="layui-input">
            </div>
       </div>
       
       <div class="layui-form-item" style="width: 100%;">
             <label class="layui-form-label">计价方式<span class="span_must">*</span></label>
             <div cyType="selectTool" cyProps="codeName:'pricing_method',filter:'pricingMethod'" name="pricingMethod"  lay-verify="required"  style="width: 400px;"
                     value="${(model.pricingMethod)!""}" class="layui-input-inline"></div>
            
            <label class="layui-form-label">计价单位<span class="span_must">*</span></label>
            <div cyType="selectTool" cyProps="codeName:'unit',filter:'unit'" name="unit"  lay-verify="required" style="width: 400px;"
                     value="${(model.unit)!""}" class="layui-input-inline"></div>
       </div>
       
       <div class="layui-form-item" style="width: 100%;">
            <label class="layui-form-label">品牌<span class="span_must"></span></label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="brand" maxlength="100" lay-verify="required"
                 value="${(model.brand)!""}"  placeholder="请输入品牌"  class="layui-input">
                    <#--<div cyType="selectTool" cyProps="url:'/yzweb/productBrand/getBrandForSelect'" name="brand"  lay-verify="" style="width: 400px;"
                         value="${(model.brand)!""}" class="layui-input-inline"></div>-->
            </div>
            <label class="layui-form-label">建议零售价格<span class="span_must">*</span></label>
            <div class="layui-input-normal" style="width: 400px;">
                <input type="text"  name="suggestPrice" maxlength="10,2" lay-verify="required"
                 value="${(model.suggestPrice)!""}"  placeholder="请输入建议零售价格"  class="layui-input">
            </div>
        </div>
       
       <div class="layui-form-item" style="width: 100%;">
            <label class="layui-form-label">生产厂家</label>
            <div class="layui-input-normal" id="factoryIds"  style="width: 1000px;"></div>
       </div>
       

 <script>
 var base = new Base64();
 //覆盖UEditor中获取路径的方法
  UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
  UE.Editor.prototype.getActionUrl = function(action) {
      if (action == 'uploadimage' || action == 'uploadscrawl') {
          return '/yzweb/getData/ue_uploadImage'; //在这里返回我们实际的上传图片地址
      }else if(action == 'uploadvideo'){
      	return '/yzweb/getData/ue_uploadVideo'; //在这里返回我们实际的上传视频地址
      } 
      else {
          return this._bkGetActionUrl.call(this, action);
      }
}
    
 var ue = UE.getEditor('editor');
 ue.ready(function(){
	 var des = '${(model.description)!""}';
	 UE.getEditor('editor').setContent(base.decode(des), false);
});
 //删除上传的文件
var m = new Map();
function remove(el){
	  var index = $(el).attr('fileId')
	  $(el).next().remove();
	  $(el).remove();
	  m.delete(index);
	  var urls = '';
      for (var x of m) { // 遍历Map
    	  if(urls == ''){
    		  urls = urls + x[1];
    	  }else{
    		  urls = urls + ',' + x[1];
    	  }
      }
      $('#showImages').val(urls);
};

layui.config({
    base : ''
  }).extend({
    selectN: '/yzweb/common/js/layui_extends/selectN',
    selectM: '/yzweb/common/js/layui_extends/selectM'
  }).use(['upload','layer','form','jquery','selectN','selectM'], function(){
  var $ = layui.jquery,upload = layui.upload;
  var selectN = layui.selectN,selectM = layui.selectM;
  var form = layui.form();
  var category = '${(model.category)!""}';
  //如果是更新记录，则取当前分类下的子分类列表，新增记录则显示选项为空
  if(category){
	  $.get($s.getDataByCode, {codeName : category}, function (data) {
		  //定义子分类数据集合
		  var tagData= [];
          var $html = "";
          if(data.data != null){
              $.each(data.data, function (index, item) {
            	  tagData.push({"id" : item.code, "name" : item.value,"status":1});
              });
              //多选标签-基本配置
              var tagIns1 = selectM({
                //元素容器【必填】
                elem: '#tags'
                //候选数据【必填】
                ,data: tagData
                ,max:4
                //添加验证
                //,verify:'required'
              });
              // 加载子分类基础数据后， 将已经存在的值设置到输入框中
              var tags = '${(model.tags)!""}';
              if(tags){
            	  tagIns1.set(tags.split(','));
              }
          }
      });
  }else{
	//多选标签-基本配置
      var tagIns1 = selectM({
        //元素容器【必填】
        elem: '#tags'
        //候选数据【必填】
        ,data: []
        ,max:4
        //添加验证
        ,verify:'required'
      });
  }
  $.get("/yzweb/personelfactory/selectList", {}, function (data) {
	  //定义子分类数据集合
	  var facData= [];
      var $html = "";
      if(data.data != null){
          $.each(data.data, function (index, item) {
        	  facData.push({"id" : item.code, "name" : item.value,"status":1});
          });
          //多选标签-基本配置
          var facIns1 = selectM({
            //元素容器【必填】
            elem: '#factoryIds'
            //候选数据【必填】
            ,data: facData
            ,max:10
            //添加验证
            //,verify:'required'      
          });
          // 加载子分类基础数据后， 将已经存在的值设置到输入框中
          var factoryIds = '${(model.factoryIds)!""}';
          if(factoryIds){
        	  facIns1.set(factoryIds.split(','));
          }
         
      }
  });
  //分类监听事件
  form.on('select(category)', function(res){
	  $.get($s.getDataByCode, {codeName : res.value}, function (data) {
          if(data.data != null && data.data.length > 0){
        	  //定义子分类数据集合
        	  var tagData= [];
              $.each(data.data, function (index, item) {
            	  tagData.push({"id" : item.code, "name" : item.value,"status":1});
              });
              var tagIns1 = selectM({
            	    //元素容器【必填】
            	    elem: '#tags'
            	    //候选数据【必填】
            	    ,data: tagData
            	    ,max:4
              }); 
              tagIns1.render();
              form.render('select');
          }else{
        	  var tagIns1 = selectM({
          	    //元素容器【必填】
          	    elem: '#tags'
          	    //候选数据【必填】
          	    ,data: []
          	    ,max:4
            }); 
            tagIns1.render();
            form.render('select');
          }
      });
  });
  // 显示宣传图
  var advertUrl = $("#advertUrl").val();
  if(advertUrl){
	  $('#advertUrlDiv').attr('src', advertUrl);
  }
  
  var aloneInstall = $("select[name='aloneInstall']").val();
  if(!aloneInstall){//独立生产
	  $("select[name='aloneInstall']").val('1');//默认单独安装
  }else{
	  if(aloneInstall == '1'){
		  $('#installLablId').show();
		  $('#installDivId').show();
	  }else{
		  $('#installLablId').hide();
		  $('#installDivId').hide();
	  }
  }
  
  var showImages = $("#showImages").val();
  if(showImages){
	  var showImageArray = showImages.split(',');
	  for(var i = 0; i < showImageArray.length; i++){
		  var key = '1000' + i;
		  m.set(key, showImageArray[i]);
		  $('#showImagesDiv').append('<i class="fa fa-trash-o" fileId="' + key + '" onclick="remove(this)">&nbsp;</i>');
          $('#showImagesDiv').append('<img src="'+ showImageArray[i] +'" class="layui-upload-img">');
	  }
  }
  
 //监听提交
  form.on('submit(submit)', function (data) {
      var url=$(this).attr("data-url");
      data.field.description = base.encode(ue.getContent());
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
  
  //普通图片上传
  var uploadInst = upload.render({
    elem: '#advertUrlBtn'
    ,url: '/yzweb/getData/uploadImage'
    ,size: 512
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#advertUrlDiv').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      $('#advertUrlDiv').attr('src', res.url); //图片链接（base64）
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
  
  //多图片上传
  upload.render({
    elem: '#showImagesBtn'
    ,url: '/yzweb/getData/uploadImages'
    ,multiple: true
    ,number: 5
    ,size: 512
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      //将每次选择的文件追加到文件队列
      obj.preview(function(index, file, result){
          $('#showImagesDiv').append('<i class="fa fa-trash-o" fileId="' + index + '" onclick="remove(this)">&nbsp;</i>')
          $('#showImagesDiv').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
      });
    }
    ,done: function(res, index){
      //上传完毕
      if(res.code > 0){
         return layer.msg('上传失败');
      }
      m.set(index, res.url);
      var urls = '';
      for (var x of m) { // 遍历Map
    	  if(urls == ''){
    		  urls = urls + x[1];
    	  }else{
    		  urls = urls + ',' + x[1];
    	  }
      }
      $('#showImages').val(urls);
      return true;
    }
  });

});

</script>