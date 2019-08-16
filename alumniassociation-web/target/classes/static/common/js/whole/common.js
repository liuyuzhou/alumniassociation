/**
 * Created by 陈熠 on 2017/6/21
 * email   :  228112142@qq.com
 * 公用模块
 */


//重写confirm式样框
window.confirm = function(msg, callback){
    parent.layer.confirm(msg, {btn: ['确定','取消']},
        function(){//确定事件
            if(typeof(callback) === "function"){
                callback("ok");
            }
        });
};

/**
 * 日期格式化
 * @param datetime
 * @param fmt
 * @returns
 */
function Date_Format(datetime,fmt) {
	 
	  if(datetime == 'null' || !datetime){
		return "";
	  }
	  if (parseInt(datetime)==datetime) {
	    if (datetime.length==10) {
	      datetime=parseInt(datetime)*1000;
	    } else if(datetime.length==13) {
	      datetime=parseInt(datetime);
	    }
	  }
	  datetime=new Date(datetime);
	  var o = {
	  "M+" : datetime.getMonth()+1,                 //月份   
	  "d+" : datetime.getDate(),                    //日   
	  "h+" : datetime.getHours(),                   //小时   
	  "m+" : datetime.getMinutes(),                 //分   
	  "s+" : datetime.getSeconds(),                 //秒   
	  "q+" : Math.floor((datetime.getMonth()+3)/3), //季度   
	  "S"  : datetime.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt)){
		  fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));  
	  }   
	  for(var k in o)  {
		  if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  } 
	  return fmt;
}

function Money_Format(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

//选择一条记录
function getSelectedRow(table_id) {
    var checked=$("#"+table_id+" tbody .layui-form-checked");
    if(checked.length==0){
        parent.layer.msg("请选择一条记录", {icon: 5});
        //alert("请选择一条记录");
        return ;
    }
    var selectedIDs = [];
    for(var i=0;i<checked.length;i++){
        var _this=$(checked[i]).prev();
        selectedIDs.push($(_this).attr("primary"));

    }
    if(selectedIDs.length > 1){
        parent.layer.msg("只能选择一条记录", {icon: 5});
        //alert("只能选择一条记录");
        return ;
    }
    return selectedIDs[0];
}

//选择一条记录
/**
 * @author chenzhicong
 * @param table_id
 * @returns {*}
 */
function getSelectedRowData(table_id) {
    var checked=$("#"+table_id+" tbody .layui-form-checked");
    if(checked.length==0){
        parent.layer.msg("请选择一条记录", {icon: 5});
        return ;
    }
    var rowData = [];
    for(var i=0;i<checked.length;i++){
        var cells=$(checked[i]).prev()[0].parentNode.parentNode.cells;
        var data={};
        for(var j=0,len=cells.length;j<len;j++){
            if(cells[j].attributes.length==0){
                continue;
            }
            var key = cells[j].attributes.name.nodeValue;
            var value = cells[j].innerText;
            data[key]=value;
        }
        rowData.push(data);
    }
    if(rowData.length > 1){
        parent.layer.msg("只能选择一条记录", {icon: 5});
        return ;
    }
    return rowData;
}

//选择一条记录
/**
 * @author chenzhicong
 * @param table_id
 * @returns {*}
 */
function getSelectedOneRowData(table_id) {
    var checked=$("#"+table_id+" tbody .layui-form-checked");
    if(checked.length==0){
        parent.layer.msg("请选择一条记录", {icon: 5});
        return ;
    }
    var rowData = [];
    for(var i=0;i<checked.length;i++){
        var cells=$(checked[i]).prev()[0].parentNode.parentNode.cells;
        var data={};
        for(var j=0,len=cells.length;j<len;j++){
            if(cells[j].attributes.length==0){
                continue;
            }
            var key = cells[j].attributes.name.nodeValue;
            var value = cells[j].innerText;
            data[key]=value;
        }
        rowData.push(data);
    }
    if(rowData.length > 1){
        parent.layer.msg("只能选择一条记录", {icon: 5});
        return ;
    }
    return data;
}

//选择多条记录
function getSelectedRowsData(table_id) {
    var checked=$("#"+table_id+" tbody .layui-form-checked");
    if(checked.length==0){
        parent.layer.msg("请选择一条记录", {icon: 5});
        return ;
    }
    var rowData = [];
    for(var i=0;i<checked.length;i++){
        var cells=$(checked[i]).prev()[0].parentNode.parentNode.cells;
        var data={};
        for(var j=0,len=cells.length;j<len;j++){
            if(cells[j].attributes.length==0){
                continue;
            }
            var key = cells[j].attributes[cells[j].attributes.length-1].nodeValue;
            var value = cells[j].innerText;
            data[key]=value;
        }
        rowData.push(data);
    }
    return rowData;
}

//选择多条记录
function getSelectedRows(table_id) {
    var checked=$("#"+table_id+" tbody .layui-form-checked");
    if(checked.length==0){
        parent.layer.msg("至少选择一条记录", {icon: 5});
        //alert("至少选择一条记录");
        return ;
    }
    var selectedIDs = [];
    for(var i=0;i<checked.length;i++){
        var _this=$(checked[i]).prev();
        selectedIDs.push($(_this).attr("primary"));

    }
    return selectedIDs;
}
//清空按钮
$(function () {
    $("[type='reset']").click(function () {
        $(this).parents(".layui-form").find("input").val("");
        $(this).parents(".layui-form").find("select").val("");
        $(this).prev().click();
    });

});

/**跳转到添加页面
 * @param table_id 表格id
 * @param url      请求地址
 */
function addPage(url){
    parent.layer.open({
        type: 2,
        title: '添加',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url
    });
}
/**跳转到添加页面
 * @param table_id 表格id
 * @param url      请求地址
 */
function addMyPage(url, width, height, isMax){
    parent.layer.open({
        type: 2,
        title: '添加',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: isMax, //开启最大化最小化按钮
        area: [width, height],
        content: url
    });
}
/**跳转到修改页面
 * @param table_id 表格id
 * @param url      请求地址
 */
function editPage(table_id,url){
    var id=getSelectedRow(table_id,url);
    if(id!=null){
        parent.layer.open({
            type: 2,
            title: '修改',
            shadeClose: false,
            shade: [0.3, '#000'],
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: url+"/"+id
        });
    }
}
/**
 * 修改
 * @param url 请求地址
 * @param id  选中的id
 */
function editOne(url,id){
    parent.layer.open({
        type: 2,
        title: '修改',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url+"/"+id
    });
}

/**
 * 修改
 * @param url 请求地址
 * @param id  选中的id
 */
function settingOne(title, url, id, max, width, height){
    parent.layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: max, //开启最大化最小化按钮
        area: [width || '893px', height || '600px'],
        content: url+"/"+id
    });
}

/**
 * 批量删除
 * @param table_id 表格id
 * @param url      请求地址
 */
function deleteBatch(msg,table_id,url){
    //获取选中的id
    var ids= getSelectedRows(table_id);
    if(ids!=null){
        confirm("确认"+msg+"？",function(){
            $.ajax({
                type: "post",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(ids),
                async: false,
                dataType:"json",
                success: function (R) {
                    if (R.code === 0) {

                        $(".search-btn").click();
                        if(R.msg&&R.msg!=""){
                            window.Alert.alert(R.msg);
                        }else{
                            parent.layer.msg('删除成功 !', {icon: 1});
                        }

                    } else {
                        window.Alert.alert(R.msg);
                    }
                },
                error: function () {
                    parent.layer.msg("系统繁忙", {icon: 5});
                }
            });
        });
    }

}
/**
 * 删除一条数据
 * @param url 请求地址
 * @param id  选中的id
 */
function deleteOne(msg,url,id){
    var ids=[];
    ids.push(id);
    //获取选中的id
    confirm("确认"+msg+"？",function(){
        $.ajax({
            type: "post",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(ids),
            async: false,
            dataType:"json",
            success: function (result) {

                if (result.code === 0) {
                    $(".search-btn").click();
                    parent.layer.msg('删除成功 !', {icon: 1});
                } else {

                    parent.layer.msg(result.msg, {icon: 5});
                }
            },
            error: function () {
                parent.layer.msg("系统繁忙", {icon: 5});
            }
        });
    });

}

/**
 * 启用或禁用多条数据
 * @param msg 提示信息
 * @param url 请求地址
 * @param id  选中的id
 */
function updateState(msg,table_id,url){
    var ids = getSelectedRows(table_id);
    confirm("确认"+msg+"？",function(){
        $.ajax({
            type: "post",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(ids),
            async: false,
            dataType:"json",
            success: function (result) {
                if (result.code == 0) {
                    $(".search-btn").click();
                    parent.layer.msg(msg+'成功 !', {icon: 1});
                } else {
                    parent.layer.msg(result.msg, {icon: 5});
                }
            },
            error: function () {
                parent.layer.msg("系统繁忙", {icon: 5});
            }
        });
    });

}

/**
 * 启用或禁用一条数据
 * @param msg 提示信息
 * @param url 请求地址
 * @param id  选中的id
 */
function updateStateOne(msg,url,id){
    var ids=[];
    ids.push(id);
    confirm("确认"+msg+"？",function(){
        $.ajax({
            type: "post",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(ids),
            async: false,
            dataType:"json",
            success: function (result) {
                if (result.code == 0) {
                    $(".search-btn").click();
                    parent.layer.msg(msg+'成功 !', {icon: 1});
                } else {
                    parent.layer.msg(result.msg, {icon: 5});
                    // parent.layer.msg(result.info, {icon: 5});
                }
            },
            error: function () {
                parent.layer.msg("系统繁忙", {icon: 5});
            }
        });
    });

}

/**
 * 详情
 * @param url
 * @param id
 */
function detailOne(url,id){
    parent.layer.open({
        type: 2,
        title: '详情',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['1000px', '700px'],
        content: url+"/"+id
    });

}

function openIframe(title,url){
    parent.layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['1000px', '700px'],
        content: url
    });
}


//保存或修改
layui.use(['form'], function () {
    var form = layui.form();
    //监听提交
    form.on('submit(submit)', function (data) {
        var url=$(this).attr("data-url");
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
//更多查询条件监听事件
layui.use(['form'], function () {
    var form = layui.form();
    //监听提交
    form.on('submit(moreSearch)', function (data) {
        if($(this).children().hasClass("fa-chevron-down")){
            //显示更多条件
            $(this).parents(".layui-form").find(".more-search").show();
            //修改更多按钮图标
            $(this).html('<i class="fa fa-chevron-up">&nbsp;</i>收起');
        }else{
            //显示更多条件
            $(this).parents(".layui-form").find(".more-search").hide();
            //修改更多按钮图标
            $(this).html('<i class="fa fa-chevron-down">&nbsp;</i>更多');
        }

        return false;
    });
});


$(function () {
    //数字过多时tips显示表格中数据
    $(".nowrap").on("mouseover","td",(function(){
        if($(this).text().length>25){
            layer.tips($(this).text(), $(this));
        }

    }));
     //隐藏右侧更多li
     $("body").on("click",function () {
       $(parent.document).contents().find(".tabsMoreList").hide();
     });
    //隐藏右键菜单
    $("body").on("click",function () {
        $(parent.document).contents().find("ul.dropdown-context").hide();
    })
});

function Base64() {

    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while ( i < utftext.length ) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}
