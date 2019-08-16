/**
 * Created by
 */

$(document).ready(function () {
    //默认显示菜单
    createMenu("sys/menu/user?_" + $.now());

});
//生成菜单
function createMenu(url) {
    $(".sidebar-menu").html("");
    $.getJSON(url, function (r) {
        for (var i = 0; i < r.menuList.length; i++) {
            var _li;
            if (r.menuList[i].type === 0) {
                _li = ['<li ><a href="javascript:;" title="'+r.menuList[i].name+'">',
                    '<i class="' + r.menuList[i].icon + '"></i>',
                    '<span>' + r.menuList[i].name + '</span>',
                    '<i class="fa fa-angle-left pull-right"></i>' +
                    '</a></li>'].join("");
                //是否有下级菜单
                if (r.menuList[i].list) {
                    var $li=$(_li);
                    $li.find("a").after('<ul class="treeview-menu"></ul>');
                    var childNodes = addMenu(r.menuList[i].list);
                    if (childNodes != "") {
                        $li.find(".treeview-menu").append(childNodes);
                        _li=$li.prop("outerHTML");
                    }
                }
            }
            if (r.menuList[i].type === 1) {
                _li = '$(<li><a class="cy-page" href="javascript:;" data-url="' + r.menuList[i].url + '" title="'+r.menuList[i].name+'"><i class="' + r.menuList[i].icon + '"></i> ' + r.menuList[i].name + '</a></li>)';
            }
            $(".sidebar-menu").append(_li);

        }

    });
}
//递归显示菜单 支持多级
function addMenu(list) {
    if (list) {
        var lis="";
        for (var i = 0; i < list.length; i++) {
            var _li;
            if (list[i].type === 0) {
                _li = ['<li ><a href="javascript:;" title="'+list[i].name+'">',
                    '<i class="' + list[i].icon + '"></i>',
                    '<span>' + list[i].name + '</span>',
                    '<i class="fa fa-angle-left pull-right"></i>' +
                    '</a></li>'].join("");
                //是否有下级菜单
                if (list[i].list) {
                    var $li=$(_li);
                    $li.find("a").after('<ul class="treeview-menu"></ul>');
                    var childNodes = addMenu(list[i].list);
                    if (childNodes != "") {
                        $li.find(".treeview-menu").append(childNodes);
                    }
                }
                _li= $li.prop("outerHTML");
            }
            if (list[i].type === 1) {
                _li = $('<li><a class="cy-page" href="javascript:;" data-url="' + list[i].url + '" title="'+list[i].name+'"><i class="' + list[i].icon + '"></i> ' + list[i].name + '</a></li>');
            }
            lis+=$(_li).prop("outerHTML");
        }
        return lis;
    }
}


$(window).on('resize', function () {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    $content.find('iframe').each(function () {
        $(this).height($content.height());
    });
}).resize();

function clearStorage() {
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    localStorage.clear();
    layer.close(index);
    layer.msg('清除成功 !', {icon: 1});
}

function updatePassword (){
    //Alert.alert("测试账号不提供修改密码,请下载代码体验完整版");
   // 修改密码
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "修改密码",
        area: ['550px', '270px'],
        shadeClose: false,
        content: jQuery("#passwordLayer"),
        btn: ['修改','取消'],
        btn1: function (index) {
            var data = "password="+$("#password").val()+"&newPassword="+$("#newPassword").val();
            $.ajax({
                type: "POST",
                url: "sys/user/password",
                data: data,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        layer.close(index);
                        layer.alert('修改成功', function(index){
                            location.reload();
                        });
                    }else{
                        Msg.error(result.msg);
                    }
                }
            });
        }
    });
}

//打赏作者
/*function reward() {
    layer.open({
        title:'',
        type: 1,
        area: ['600px', '448px'], //宽高
        content: '<img src="/statics/img/cy/reward.png">'
    });
}*/
//公告
function notice(){
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '600px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,resize: false
        ,btn: ['确定', '取消']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: ['<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
            ,'1.<span style="color:#2991D9;" >待发布</span><br>'
            ,'2.<span style="color:#2991D9;" >待发布</span><br>'
            ,'3.<span style="color:#2991D9;" >待发布</span><br>'
            ,'4.<a href="http://id.tudou.com/i/UMTQ5MTY4MzM2MA" target="_blank" style="color:#2991D9;" >待发布</a></div>'].join("")
        ,success: function(layero){
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'javascript:reward();'
            });
        }
    });
}
$(document).ready(function () {
    $.getJSON("sys/user/info?_"+$.now(), function(r){
        $(".username").html(r.user.username) ;
        //notice();
    });


});