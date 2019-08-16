/**
 * 商品
 * @author lewp
 * @email kunda@qq.com
 * @date 2018-10-12 09:48:52
 */

/**数据渲染对象*/
var Render = {
    /**
     * 渲染状态列
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @param index
     * @param value      当前对象值
     */
    customState: function (rowdata,renderData, index, value) {
        if(value == "上线"){
            return '<span style="color:green">'+value+'</span>';
        }
        if(value == "下架"){
            return '<span style="color:red">'+value+'</span>';
        }
        return value;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      详情按钮渲染
     */
    info:function(rowdata,renderData){
        var btn=' <button  onclick="detailOne(\''+"/yzweb/product/info"+'\',\''+rowdata.id+'\')" class="layui-btn layui-btn-mini">详情</button>';
        return btn;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      修改按钮渲染
     */
    edit:function(rowdata,renderData){
    	if(rowdata.status=='9'){
    		return "";
    	}
        var btn=' <button  onclick="settingOne(\''+"修改"+'\',\''+"/yzweb/product/edit"+'\',\''+rowdata.id+'\',false,\'1200px\', \'880px\')" class="layui-btn layui-btn-mini">修改</button>';
        return btn;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      启用禁用按钮渲染
     */
    online:function(rowdata,renderData){
    	if(rowdata.status=='9'){
    		return "";
    	}
        if(rowdata.status=='0'){
            return' <button onclick="updateStateOne(\''+"上线"+'\',\''+"/yzweb/product/online"+'\',\''+rowdata.id+'\')"' +
                '  class="layui-btn layui-btn-mini layui-btn-green">上线</button>';
        }
        if(rowdata.status=='1'){
            return' <button  onclick="updateStateOne(\''+"下架"+'\',\''+"/yzweb/product/cancelOnline"+'\',\''+rowdata.id+'\')" ' +
                'class="layui-btn layui-btn-mini layui-btn-danger">下架</button>';
        }
        return "";
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      修改按钮渲染
     */
    componentConfig:function(rowdata,renderData){
        return' <button onclick="settingOne(\''+"安装项目配置"+'\',\''+"/yzweb/product/configInstallProject"+'\',\''+rowdata.id+'\',false,\'1000px\', \'600px\')"' +
            '  class="layui-btn layui-btn-mini layui-btn-green">安装项目配置</button>';
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      修改按钮渲染
     */
    specificationConfig:function(rowdata,renderData){
        return' <button onclick="productSpecificationConfig(\''+"商品规格配置"+'\',\''+"/yzweb/product/specificationPage"+'\',\''+rowdata.id+'\',\''+rowdata.category+'\',\''+rowdata.tags+'\',false,\'1600px\', \'800px\')"' +
            '  class="layui-btn layui-btn-mini layui-btn-green">商品规格配置</button>';
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      修改按钮渲染
     */
    attrConfig:function(rowdata,renderData){
    	if(rowdata.status=='9'){
    		return "";
    	}
    	return' <button onclick="settingOne(\''+"附加配置"+'\',\''+"/yzweb/product/attrConfig"+'\',\''+rowdata.id+'\',false,\'1000px\', \'600px\')"' +
         '  class="layui-btn layui-btn-mini layui-btn-green">附加配置</button>';  
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      启用禁用按钮渲染
     */
    setTop:function(rowdata, renderData){
    	if(rowdata.status=='9'){
    		return "";
    	}
        if(rowdata.isTop=='0'){
            return ' <button onclick="updateStateOne(\''+"置顶"+'\',\''+"/yzweb/product/setTop"+'\',\''+rowdata.id+'\')"' +
                '  class="layui-btn layui-btn-mini layui-btn-green">置顶</button>';
        }

        if(rowdata.isTop=='1'){
            return ' <button  onclick="updateStateOne(\''+"取消置顶"+'\',\''+"/yzweb/product/cacelTop"+'\',\''+rowdata.id+'\')" ' +
                'class="layui-btn layui-btn-mini layui-btn-danger">取消置顶</button>';
        }
        return "";
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      删除按钮渲染
     */
    delete:function(rowdata,renderData){
        var btn=' <button  onclick="deleteOne(\''+"删除"+'\',\''+"/yzweb/product/delete"+'\',\''+rowdata.id+'\')" class="layui-btn layui-btn-mini layui-btn-delete">删除</button>';
        return btn;
    }
};


/**
 * 修改
 * @param url 请求地址
 * @param id  选中的id
 */
function productSpecificationConfig(title, url, id, category, tags, max, width, height){
    parent.layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: max, //开启最大化最小化按钮
        area: [width || '893px', height || '600px'],
        content: url + "?id=" + id + "&category=" + category + "&tags=" +tags
    });
}