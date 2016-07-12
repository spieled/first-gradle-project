[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 参保人员</title>
        [#include 'base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include 'base/macro.ftl'/]
    [#include 'base/header.ftl'/]
    [#include 'base/nav.html'/]
    [#include 'base/content_header.html'/]

        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4><i class="fa fa-table"></i>参保人员</h4>
                <div class="tools hidden-xs">
                    <button id="createPersonBtn" class="btn btn-sm btn-danger">新建参保人员</button>
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <table id="datatable1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>户籍类型</th>
                        <th>身份证号</th>
                        <th>公司</th>
                        <th>城市</th>
                        <th>是否已投保</th>
                        <th>是否在职</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if pageData.content?? && pageData.content?size>0]
                        [#list pageData.content as person]
                        <tr value="${person.id}">
                            <td data-attr="id" data-attr-value="${person.id}">${person_index+1}</td>
                            <td data-attr="name" data-attr-value="${person.name}">${person.name}</td>
                            <td data-attr="gender" data-attr-value="${person.gender}">
                                [#if person.gender=="MALE"]男[#elseif person.gender=="FEMALE"]女[/#if]
                            </td>
                            <td data-attr="age" data-attr-value="${person.age}">${person.age}</td>
                            <td data-attr="type" data-attr-value="${person.type}">[#if person.type="CITY"]城镇[#elseif person.type="TOWN"]农村[/#if]</td>
                            <td data-attr="idNumber" data-attr-value="${person.idNumber}">${person.idNumber}</td>
                            <td data-attr="companyId" data-attr-value="${person.companyId}">
                                [@companyDirective id="${person.companyId}"]
                                    ${name}
                                [/@companyDirective]
                                [#--[#if person.companyId=0]无
                                [#elseif person.companyId=1]民工加
                                [/#if]--]
                            </td>
                            <td data-attr="cityName" data-attr-value="${person.cityName}">${person.cityName}</td>
                            <td data-attr="insured" data-attr-value="${person.insured?string}">${person.insured?string("已参保","从未参保")}</td>
                            <td data-attr="onStation" data-attr-value="${person.onStation?string}">${person.onStation?string("在职","离职")}</td>
                            <td data-attr="status" data-attr-value="${person.status}">
                                [#if person.status="NEW"]未审核
                                [#elseif person.status="CHECK_FAILED"]审核不通过
                                [#elseif person.status="CHECK_SUCCESS"]审核通过
                                [/#if]
                            </td>
                            <td class="hidden-xs">
                                <button name="editBtn" class="btn btn-sm  btn-grey">编辑</button>
                                <button name="deleteBtn" class="btn btn-sm btn-danger">删除</button>
                            </td>
                        </tr>
                        [/#list]
                    [#else]
                    <tr>
                        <td colspan="20">没有数据！</td>
                    </tr>
                    [/#if]
                    </tbody>

                </table>
            [@pagination totalPages="${pageData.totalPages}" number="${pageData.number}" first="${pageData.first?string('true', 'false')}" last="${pageData.last?string('true', 'false')}"/]
            </div>
        </div>
        <!-- /BOX -->

    [#include 'base/content_footer.html'/]
    </section>

    [#-- 新增 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="createPersonModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="createPersonForm" class="form-horizontal" role="form" action="persons/create">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" class="form-control" placeholder="姓名" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户籍类型</label>
                                <div class="col-sm-9">
                                    <select name="type" class="form-control" required>
                                        <option value="CITY">城镇</option>
                                        <option value="TOWN">农村</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="idNumber" class="form-control" placeholder="身份证号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司</label>
                                <div class="col-sm-9">
                                    <select name="companyId" class="form-control">
                                        <option value="0">无</option>
                                        [#--<option value="1">民工加</option>--]
                                        [@companyDirective username="${currentUsername?string}"]
                                        [#list companies as com]
                                        <option value="${com.id}">${com.name}</option>
                                        [/#list]
                                        [/@companyDirective]
                                    </select>
                                </div>
                            </div>
                            [#--<div class="form-group">
                                <label class="col-sm-3 control-label">城市</label>
                                <div class="col-sm-9">
                                    <input type="text" name="cityName" class="form-control" placeholder="城市">
                                </div>
                            </div>--]
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否已参保</label>
                                <div class="col-sm-9 checkbox-inline">
                                    <label><input type="radio" id="enable_true" name="insured" value="true">已参保</label>
                                    <label><input type="radio" id="enable_true" name="insured" value="false">从未参保</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否在职</label>
                                <div class="col-sm-9 checkbox-inline">
                                    <label><input type="radio" id="enable_true" name="onStation" value="true">在职</label>
                                    <label><input type="radio" id="enable_true" name="onStation" value="false">离职</label>
                                </div>
                            </div>
                            <div class="form-group center">
                                <button class="btn btn-light-grey" data-dismiss="modal">放弃</button>
                                <button type="submit" class="btn btn-success">创建</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#-- 更新 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="editPersonModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="editPersonForm" class="form-horizontal" role="form" action="persons/update">
                            <input type="hidden" name="id"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" class="form-control" placeholder="姓名" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">户籍类型</label>
                                <div class="col-sm-9">
                                    <select name="type" class="form-control" required>
                                        <option value="CITY">城镇</option>
                                        <option value="TOWN">农村</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="idNumber" class="form-control" placeholder="身份证号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司</label>
                                <div class="col-sm-9">
                                    <select name="companyId" class="form-control">
                                        <option value="0">无</option>
                                        [#--<option value="1">民工加</option>--]
                                        [@companyDirective username="${currentUsername?string}"]
                                            [#list companies as com]
                                                <option value="${com.id}">${com.name}</option>
                                            [/#list]
                                        [/@companyDirective]
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">城市</label>
                                <div class="col-sm-9">
                                    <input type="text" name="cityName" class="form-control" placeholder="城市" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否已参保</label>
                                <div class="col-sm-9 checkbox-inline">
                                    <label><input type="radio" id="enable_true" name="insured" value="true">已参保</label>
                                    <label><input type="radio" id="enable_true" name="insured" value="false">从未参保</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否在职</label>
                                <div class="col-sm-9 checkbox-inline">
                                    <label><input type="radio" id="enable_true" name="onStation" value="true">在职</label>
                                    <label><input type="radio" id="enable_true" name="onStation" value="false">离职</label>
                                </div>
                            </div>
                            <div class="form-group center">
                                <button class="btn btn-light-grey" data-dismiss="modal">放弃</button>
                                <button type="submit" class="btn btn-success">修改</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#include 'base/footer.html'/]
    <script>

        $(function () {

            $('#createPersonBtn').on('click', function () {
                $("#createPersonModal").modal({backdrop: 'static', keyboard: false});
            });
            /* ajax 提交分组表单 */
            $('#createPersonForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });
            $('#editPersonForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });

            /* 更新 */
            $('button[name=editBtn]').on('click', function() {
                var tr = $(this).parent().parent();

                // get attribute
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                var name = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                var type = $(tr).find('td[data-attr=type]').attr("data-attr-value");
                var cityName = $(tr).find('td[data-attr=cityName]').attr("data-attr-value");
                var companyId = $(tr).find('td[data-attr=companyId]').attr("data-attr-value");
                var idNumber = $(tr).find('td[data-attr=idNumber]').attr("data-attr-value");
                var insured = $(tr).find('td[data-attr=insured]').attr("data-attr-value");
                var onStation = $(tr).find('td[data-attr=onStation]').attr("data-attr-value");

                // show model
                $('#editPersonModal').modal({backdrop: 'static', keyboard: false});

                // set attribute
                $('#editPersonForm').find('input[name=id]').val(id);
                $('#editPersonForm').find('input[name=name]').val(name);
                $('#editPersonForm').find('select[name=type]').val(type);
                // $('#editPersonForm').find('select[name=type]').find('option[value='+type+']').attr("selected",true);
                $('#editPersonForm').find('input[name=cityName]').val(cityName);
                $('#editPersonForm').find('select[name=companyId]').val(companyId);
                // $('#editPersonForm').find('select[name=companyId]').find('option[value='+companyId+']').attr("selected", true);
                $('#editPersonForm').find('input[name=idNumber]').val(idNumber);
                $('#editPersonForm').find('input[name=insured][value='+insured+']').attr("checked", "checked");
                $('#editPersonForm').find('input[name=onStation][value='+onStation+']').attr("checked", "checked");


            });


            /* 删除 */
            $('button[name=deleteBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var personName = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                var personId = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认删除参保人【'+personName+'】吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/persons/delete',
                                data: {id: personId},
                                success: function(response) {
                                    if (response.success) {
                                        window.location.reload();
                                    }
                                }
                            });
                        }
                    }
                });

            });
        })
    </script>
    </body>
</html>