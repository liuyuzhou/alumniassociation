<div id="orgType" class="layui-form-item">
           <label class="layui-form-label">部门类型<span class="span_must">*</span></label>
           <div cyType="selectTool" cyProps="codeName:'organize_type',filter:'orgType'" name="orgType" lay-verify="required"
                     value="${(model.orgType)!""}" class="layui-input-inline">
           </div>
</div>
<div class="layui-form-item">
    <label id="orgName" class="layui-form-label">部门名称<span class="span_must">*</span></label>
    <div class="layui-input-normal">
        <input type="text" name="orgName" maxlength="30" value="${(model.orgName)!""}" lay-verify="required"
               placeholder="请输入部门名称" class="layui-input">
    </div>
</div>

<div class="layui-form-item">
    <label class="layui-form-label">上级部门:</label>
    <div class="layui-input-normal">

        <input value="${(model.parentOrgId)!""}" id="demo"
               cyType="treeTool" cyProps="url:'/yzweb/organize/select',name:'parentOrgId'"
               placeholder="请选择上级菜单" lay-verify="required" class="layui-input"/>

    </div>
    
</div>

<div id="province_city_area" style="display:none">
    <div class="layui-form-item">
        <label class="layui-form-label">省<span class="span_must">*</span></label>
        <div class="layui-input-block" style="width:370px">
            <select lay-filter="province" id="province_code" name="provinceCode"  >
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">市<span class="span_must">*</span></label>
        <div id="province_city" cyType="selectTool" cyProps="filter:'city'" value=""
             name="cityCode" class="layui-input-normal"></div>
    </div>

    <div class="layui-form-item area_operator area_respository" hidden id="areaDIV">
        <label class="layui-form-label">
            <span id="areaText">经营区域</span>
            <span class="span_must">*</span></label>
        <div id="city_area" cyType="selectTool" cyProps="filter:'area',multiple:'true'" value=""
             name="areaCodes" class="layui-input-normal"></div>
    </div>
    <input id="areaCodeVal" type="hidden" name="areaCode" />
</div>


<div id="selectShow">
    <div id="operator" class="showOption" style="display:none">
<#--  运营商公用页面-->

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">简称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="shortname" maxlength="100" lay-verify="required"
                 value="${(model.shortname)!""}"  placeholder="请输入简称"  class="layui-input operator">
            </div>
        </div>

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">联系人<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="contact" maxlength="20" lay-verify="required"
                 value="${(model.contact)!""}"  placeholder="请输入联系人"  class="layui-input operator">
            </div>
        </div>

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">联系电话<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="contactNumber" maxlength="20" lay-verify="required"
                 value="${(model.contactNumber)!""}"  placeholder="请输入联系电话"  class="layui-input operator">
            </div>
        </div>

       <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">运营商地址<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="address" maxlength="255" lay-verify="required"
                 value="${(model.address)!""}"  placeholder="请输入运营商地址"  class="layui-input operator">
            </div>
        </div>

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">企业编号</label>
            <div class="layui-input-normal">
                <input type="text"  name="operatorNumber" maxlength="100"
                 value="${(model.operatorNumber)!""}"  placeholder="请输入企业编号"  class="layui-input operator">
            </div>
        </div>

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">营业执照编号</label>
            <div class="layui-input-normal">
                <input type="text"  name="buslicNumber" maxlength="255"
                 value="${(model.buslicNumber)!""}"  placeholder="请输入营业执照编号"  class="layui-input operator">
            </div>
        </div>
</div>

    <div id="factory" class="showOption" style="display:none">
 <#--  工厂公用页面-->
              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">地址<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="ftyAddress" maxlength="255" lay-verify="required"
                 value="${(model.ftyAddress)!""}"  placeholder="请输入工厂联系地址"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">工厂联系人<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="ftyContact" maxlength="50" lay-verify="required"
                 value="${(model.ftyContact)!""}"  placeholder="请输入工厂联系人"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">工厂联系电话<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="ftyContactTel" maxlength="20" lay-verify="required"
                 value="${(model.ftyContactTel)!""}"  placeholder="请输入工厂联系电话"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">工厂联系人移动电话<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="ftyContactMobile" maxlength="20" lay-verify="required"
                 value="${(model.ftyContactMobile)!""}"  placeholder="请输入工厂联系人移动电话"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">工厂法人姓名<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="legalPerson" maxlength="20" lay-verify="required"
                 value="${(model.legalPerson)!""}"  placeholder="请输入工厂法人姓名"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">工厂法人身份证号码<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="legalPersonid" maxlength="20" lay-verify="required"
                 value="${(model.legalPersonid)!""}"  placeholder="请输入工厂法人身份证号码"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">开户行名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="bankName" maxlength="50" lay-verify="required"
                 value="${(model.bankName)!""}"  placeholder="请输入开户行名称"  class="layui-input factory">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">开户行帐号<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="bankAccNo" maxlength="20" lay-verify="required"
                 value="${(model.bankAccNo)!""}"  placeholder="请输入开户行帐号"  class="layui-input factory">
            </div>
        </div>

</div>

    <div id="respository" class="showOption" style="display:none">
    <#--  仓库公用页面-->

    <div class="layui-form-item">${(model.bucket)!""}
        <label class="layui-form-label">仓库联系电话<span class="span_must">*</span></label>
        <div class="layui-input-normal">
            <input type="text"  name="telNo" maxlength="20" lay-verify="required"
                   value="${(model.telNo)!""}"  placeholder="请输入仓库联系电话"  class="layui-input respository">
        </div>
    </div>


    <div class="layui-form-item">${(model.bucket)!""}
        <label class="layui-form-label">仓库地址<span class="span_must">*</span></label>
        <div class="layui-input-normal">
            <input type="text"  name="wmsAddress" maxlength="255" lay-verify="required"
                   value="${(model.wmsAddress)!""}"  placeholder="请输入仓库地址"  class="layui-input respository">
        </div>
    </div>

    <#--<div class="layui-form-item">${(model.bucket)!""}-->
    <#--<label class="layui-form-label">经度<span class="span_must">*</span></label>-->
    <#--<div class="layui-input-normal">-->
    <#--<input type="text"  name="lng" maxlength="20" lay-verify="required"-->
    <#--value="${(model.lng)!""}"  placeholder="请输入经度"  class="layui-input respository">-->
    <#--</div>-->
    <#--</div>-->

    <#--<div class="layui-form-item">${(model.bucket)!""}-->
    <#--<label class="layui-form-label">纬度<span class="span_must">*</span></label>-->
    <#--<div class="layui-input-normal">-->
    <#--<input type="text"  name="lat" maxlength="20" lay-verify="required"-->
    <#--value="${(model.lat)!""}"  placeholder="请输入纬度"  class="layui-input respository">-->
    <#--</div>-->
    <#--</div>-->

    <div class="layui-form-item">${(model.bucket)!""}
        <label class="layui-form-label">负责人<span class="span_must">*</span></label>
        <div class="layui-input-normal">
            <input type="text"  name="principal" maxlength="20" lay-verify="required"
                   value="${(model.principal)!""}"  placeholder="请输入负责人"  class="layui-input respository">
        </div>
    </div>

    <div class="layui-form-item">${(model.bucket)!""}
        <label class="layui-form-label">负责人号码<span class="span_must">*</span></label>
        <div class="layui-input-normal">
            <input type="text"  name="principalPhone" maxlength="20" lay-verify="required"
                   value="${(model.principalPhone)!""}"  placeholder="请输入负责人号码"  class="layui-input respository">
        </div>
    </div>

</div>
</div>

<script>
  layui.config({
    base : ''
  }).extend({
    selectN: '/yzweb/common/js/layui_extends/selectN',
    selectM: '/yzweb/common/js/layui_extends/selectM'
  }).use(['layer','form','jquery','selectN','selectM'], function(){
  var $ = layui.jquery;
  var selectN = layui.selectN,selectM = layui.selectM;
  var form = layui.form();
  var orgType = '${(model.orgType)!""}';
  var selectType = '';
  var orgId = '${(model.orgId)!""}';
  if(orgId!=''){
	  $("#orgType").hide();
  }
  selectShow(orgType);


  form.on('select(orgType)', function(res){
      selectType = res.value;
      selectShow(res.value);
  });


      $.get("/yzweb/area/childrenList/0", {}, function (data) {
          var $html = "";
          if (data.data != null) {
              var province = null,city=null,area=null;
              //如果缓存中不存在省份,则初始化为原始数据
              var province ='${(model.provinceCode)!""}';
              var city ='${(model.cityCode)!""}';
              var area ='${(model.areaCode)!""}';

              $.each(data.data, function (index, item) {
                  if (province&&item.code==province) {
                      $html += "<option class='generate' value='" + item.code + "' selected>" + item.value + "</option>";
                      $("#province_city").attr("value",city);
                      getCity(province);
                      if('1'== '${(model.orgType)!""}' ||'3'== '${(model.orgType)!""}'){
                          $("#city_area").attr("value",area);
                          getArea(city);
                      }

                  } else {
                      $html += "<option value='" + item.code + "'>" + item.value + "</option>";
                  }
              });

              $("select[name='provinceCode']").append($html);

              //append后必须重新渲染
              form.render('select');
          }
      });

      function getCity(province){
          $.get('/yzweb/area/childrenList/' + province, function (data) {
              //当前表格对象
              var $grid = $("#province_city");
              //获取表格参数
              cyProps = $grid.attr("cyProps");
              if (!cyProps) {
                  return
              }
              cyProps = cyProps ? cyProps : "";
              //将表格参数转为json
              cyProps = eval("({" + cyProps + "})");

              renderData(data, $grid, cyProps, 'cityCode');

          });
      }

      function getArea(city){
          if (city) {
              $.get('/yzweb/area/childrenList/' + city, function (data) {
                  //当前表格对象
                  var $grid = $("#city_area");
                  //获取表格参数
                  cyProps = $grid.attr("cyProps");
                  if (!cyProps) {
                      return
                  }
                  cyProps = cyProps ? cyProps : "";
                  if (selectType == '3' || orgType == '3'){
                      cyProps = "filter:'area'"
                  } else {
                      cyProps = "filter:'area',multiple:'true'"
                  }
                  //将表格参数转为json
                  cyProps = eval("({" + cyProps + "})");

                  renderData(data, $grid, cyProps, 'areaCodes');

              });
          }
      }
      //省份监听事件
      form.on('select(province)', function(res){
          $.get('/yzweb/area/childrenList/'+res.value,function (data) {
              //当前表格对象
              var $grid = $("#province_city");
              //获取表格参数
              cyProps = $grid.attr("cyProps");
              if (!cyProps) {
                  return
              }
              cyProps = cyProps ? cyProps : "";
              //将表格参数转为json
              cyProps = eval("({" + cyProps + "})");

              renderData(data, $grid, cyProps,'cityCode');

          });
      });

      //市区监听事件
      form.on('select(city)', function(res){
          $.get('/yzweb/area/childrenList/'+res.value,function (data) {
              //当前表格对象
              var $grid = $("#city_area");
              //获取表格参数
              cyProps = $grid.attr("cyProps");
              if (!cyProps) {
                  return
              }
              cyProps = cyProps ? cyProps : "";
              if (selectType == '3' || orgType == '3'){
                  cyProps = "filter:'area'"
              } else {
                  cyProps = "filter:'area',multiple:'true'"
              }
              //将表格参数转为json
              cyProps = eval("({" + cyProps + "})");

              renderData(data, $grid, cyProps,'areaCodes');

          });
      });

      //市区监听事件
      form.on('select(area)', function(res){
          if (selectType == '3' || orgType == '3'){
              $("#areaCodeVal").val(res.value);
          } else {
              $("#areaCodeVal").val("");
          }
      })
  /*默认配置*/
  var cyProps = {};
  /**渲染下拉框数据 by chenyi 2017/6/21*/
  function renderData(R, $grid, cyProps,selectName) {
      var _grid = $grid;
      $(_grid).empty();
      //获取下拉控件的name
      var _name = $(_grid).attr("name");
      //获取下拉控件的默认值
      var _value = $(_grid).attr("value");
      //获取需要验证的参数
      var _verify = $(_grid).attr("lay-verify") || "";
      $(_grid).removeAttr("lay-verify");
      $(_grid).removeAttr("name");
      $(_grid).removeAttr("value");
      //是否是只读
      var _readonly=cyProps.readonly||"";
      //是否是下拉多选
      var _multiple=cyProps.multiple||"false";
      //获取是否有提示
      var _selectTip = cyProps.tips || "请选择";
      //获取监控标识
      var filter = cyProps.filter || "";
      //搜索功能参数
      var _search = cyProps.search || "true";
      //获取下拉框禁用的值
      var _disabled = cyProps.disabled || "";
      var _disableds = _disabled.split(",");
      var data = R.data;
      var _select = '<select name="' + selectName + '" ></select>';
      //是否是多选
      if (_multiple == "true") {
          _select = _select.replace('<select', '<select  multiple value="'+_value+'"');

      }
      //是否是只读
      if (_readonly == "true") {
          _select = _select.replace('<select', '<select disabled="disabled"  value="'+_value+'"');

      }
      //是否开启搜索功能
      if (_search == "true") {
          _select = _select.replace("<select", "<select  lay-search");

      }
      //添加监控标识
      if (filter != "") {
          _select = _select.replace("<select", "<select lay-filter=" + filter);
      }
      //验证值
      if (_verify != undefined && _verify != "") {
          _select = _select.replace("<select", "<select  lay-verify='" + _verify + "'");
      }
      $(_grid).append(_select);

      if(_selectTip!="false"){
          $(_grid).find("select").append('<option value="">' + _selectTip + '</option>');
      }

      if (data != undefined) {
          for (var i = 0; i < data.length; i++) {
              var _option = '<option value="' + data[i].code + '">' + data[i].value + '</option>';
              if(_multiple == "false"){
                  //设置默认值
                  if (_value == data[i].code) {
                      _option = _option.replace("<option", "<option selected ")
                  }
              }
               if(_multiple == "true"){
                   if(_value){
                       var _values=_value.split(",")||"";
                       if(_values){
                           for(var z=0;z<_values.length;z++){
                               //设置默认值
                               if (_values[z] == data[i].code) {
                                   _option = _option.replace("<option", "<option selected ")
                               }
                           }
                       }
                   }

              }
              //设置禁用
              if (_disableds.indexOf(data[i].code) != -1) {
                  _option = _option.replace("<option", "<option disabled ")
              }
              $(_grid).find("select").append(_option);
          }
      }
      //渲染下拉框
      layui.use('form', function () {
          var form = layui.form();
          form.render();
      });

  }


  // 显示指定type的区域
   function selectShow(selectType) {
      var showPart = null;
       if(selectType=='1'){
           $("#orgName").text("运营商名称");
           $("#areaText").text("经营区域");
           showPart = "operator"
       }
       if(selectType=='2'){
           $("#orgName").text("工厂名称");
           showPart = "factory"
       }
       if(selectType=='3'){
           $("#orgName").text("仓库名称");
           $("#areaText").text("区");
           showPart = "respository"
       }
       if(selectType=='0' || selectType==''){
           $("#orgName").text("部门名称");
       }
       var allShowdiv = $("#selectShow .showOption");

       allShowdiv.hide();
       $("#selectShow input").removeAttr("lay-verify");

       if (showPart) {
           $("#"+showPart).show();
           $("."+showPart).attr("lay-verify","required");
           $("#province_city_area").show();
           var area = $(".area_"+showPart)
           if (area.length == 0){
               $("#areaDIV").hide();
           } else {
               area.show();
               var citySelect = $("select[name='cityCode']").val();
               getArea(citySelect);
           }
       } else {
           $("#province_city_area").hide();
       }



   };


      //监听提交
      form.on('submit(submit)', function (data) {
          var url = $(this).attr("data-url");
          if(selectType=='1' || selectType=='2' || selectType=='3') {
              if (!data.field["provinceCode"] || data.field["provinceCode"] == '') {
                  parent.layer.msg('省份不能为空', {icon: 5});
                  return false;
              }
              if (!data.field["cityCode"] || data.field["cityCode"] == '') {
                  parent.layer.msg('市不能为空', {icon: 5});
                  return false;
              }
              if (!data.field["areaCodes"] || !$.isArray(data.field["areaCodes"])) {
                  data.field["areaCodes"] = []
              }
          }

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
