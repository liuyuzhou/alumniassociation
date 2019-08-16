<#--  lewp 2018-10-12 09:48:52-->
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <#include "../resource.ftl"/>
    <script type="text/javascript" src="/yzweb/product/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">名称:</label>
        <div class="layui-input-inline">
            <input type="text" name="name"  placeholder="请输入名称" class="layui-input">
        </div>

        <div class="layui-input-normal">
            <button class="layui-btn layui-btn-green" lay-submit="" lay-filter="moreSearch">
                <i class="fa fa-chevron-down">&nbsp;</i>更多
            </button>
            <button class="layui-btn search-btn" table-id="productTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
    <div class="layui-form-item more-search">
       <#-- 更多条件-->
         <label class="layui-form-label">品牌:</label>
         <div class="layui-input-inline">
            <input type="text" name="brand"  placeholder="请输入品牌" class="layui-input">
         </div>
         <label class="layui-form-label">产品分类:</label>
         <div class="layui-input-inline">
            <div cyType="selectTool" cyProps="codeName:'pro_category'" name="category" class="layui-input-inline"></div>
         </div>
         <label class="layui-form-label">产品序列:</label>
         <div class="layui-input-inline">
            <div cyType="selectTool" cyProps="codeName:'pro_seq'" name="seq" class="layui-input-inline"></div>
         </div>
         <label class="layui-form-label">产品样式:</label>
         <div class="layui-input-inline">
            <div cyType="selectTool" cyProps="codeName:'pro_style'" name="style" class="layui-input-inline"></div>
         </div>
         <label class="layui-form-label">是否置顶:</label>
         <div class="layui-input-inline">
            <div cyType="selectTool" cyProps="codeName:'whether'" name="isTop" class="layui-input-inline"></div>
         </div>
    </div>
</form>
<div class="layui-form ">
    <table class="layui-table" id="productTable" cyType="pageGrid"
           cyProps="url:'/yzweb/product/listData?status=1' ,checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
                 <th width="5%" param="{name:'id',isPrimary:'true',hide:'true'}">商品ID</th>
            
		          <th width="5%" param="{name:'name'}">产品名称</th>
		          
		          <th width="5%" param="{name:'advertUrl', render : function(row){
		             return '<img src='+ row.advertUrl +'>'
		          }}">宣传图</th>
		          
		          <th width="5%" param="{name:'category',codeName : 'pro_category'}">产品分类</th>
		          
		          <th width="5%" param="{name:'seq',codeName : 'pro_seq'}">产品序列</th>
		          
		          <th width="5%" param="{name:'style',codeName : 'pro_style'}">产品样式</th>
        </tr>
        </thead>
    </table>
</div>
 </body>

<script>
    var selRow = null;

    layui.use('form', function () {
        var $ = layui.jquery;
        var form = layui.form();

        form.on('checkbox', function (obj) {
            //如果是选中的话，替换掉之前的选中值
            if (obj.elem.checked) {
                //当前元素
                var data = $(obj.elem);
                //遍历父级tr，取第一个，然后查找第二个td，取值
                // var id = getSelectedRow('personelSalesTable');
                var data = getSelectedRowData('productTable');
                localStorage.removeItem("productData");
                $t.setStorageItem('productData', data);
                if (selRow != null) {
                    selRow.checked = false;
                    form.render('checkbox');
                }
                selRow = obj.elem;
            } else {//如果是取消
                selRow = null;
                parent.selId = null;
            }
        });
    });

</script>
</html>