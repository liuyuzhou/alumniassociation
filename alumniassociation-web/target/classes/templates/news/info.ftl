<#--  lewp 2018-10-16 09:30:49-->

<html>
<head>
    <title>新闻资讯详情页面</title>
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
            <label class="layui-label-left">标题<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.title)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">图标<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.icon)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">新闻内容<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.content)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">新闻类型<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.type)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">发布时间<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.releasetime)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">创建人<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.createuserid)!"-"}</label>
        </div>
          </form>
</div>

</body>
</html>
