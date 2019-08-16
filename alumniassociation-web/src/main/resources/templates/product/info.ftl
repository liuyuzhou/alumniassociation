<#--  lewp 2018-10-12 09:48:52-->

<html>
<head>
    <title>商品详情页面</title>
    <#include "../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
          <div class="layui-form-item">
            <label class="layui-label-left">产品名称<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.name)!"-"}</label>
         </div>
        <div class="layui-form-item">
            <label class="layui-label-left">产品分类<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.classify)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">产品样式<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.style)!"-"}</label>
         </div>
         <div class="layui-form-item">
            <label class="layui-label-left">产品材质<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.material)!"-"}</label>
         </div>
        <div class="layui-form-item">
            <label class="layui-label-left">产品序列<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.seq)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">产品简介<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.summary)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">产品描述<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.description)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">宣传图<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.advertUrl)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">展示图片<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.showImages)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">建议零售价格<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.suggestPrice)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">品牌<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.brand)!"-"}</label>
        </div>
        <div class="layui-form-item">
            <label class="layui-label-left">商品状态<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.status)!"-"}</label>
        </div>
     </form>
</div>

</body>
</html>
