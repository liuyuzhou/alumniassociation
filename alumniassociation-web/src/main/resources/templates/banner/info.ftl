<#--  chenyi 2018-10-29 11:57:44-->

<html>
<head>
    <title>首页详情页面</title>
    <#include "../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
             <div class="layui-form-item">
            <label class="layui-label-left"><span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.id)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">宣传图<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.adverturl)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">跳转链接<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.url)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">创建人ID<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.createUserId)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">创建时间<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.createTime)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">更新人<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.updateUserId)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">更新时间<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.updateTime)!"-"}</label>
        </div>
          </form>
</div>

</body>
</html>
