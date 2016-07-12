[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 企业</title>
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
                <h4><i class="fa fa-table"></i>企业</h4>
                <div class="tools hidden-xs">
                    <button id="createCompanyBtn" class="btn btn-sm btn-danger">新建企业</button>
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
                        <th>名称</th>
                        <th>地址</th>
                        <th>法人</th>
                        <th>法人身份证号码</th>
                        <th>营业执照号码</th>
                        <th>组织机构代码</th>
                        <th>税务登记号</th>
                        <th>联系人</th>
                        <th>联系人手机号</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if pageData.content?? && pageData.content?size>0]
                        [#list pageData.content as company]
                        <tr value="${company.id}">
                            <td data-attr="id" data-attr-value="${company.id}">${company_index+1}</td>
                            <td data-attr="name" data-attr-value="${company.name}">${company.name}</td>
                            <td data-attr="address" data-attr-value="${company.address}">${company.address}</td>
                            <td data-attr="legalPerson" data-attr-value="${company.legalPerson}">${company.legalPerson}</td>
                            <td data-attr="legalPersonIdNumber" data-attr-value="${company.legalPersonIdNumber}">${company.legalPersonIdNumber}</td>
                            <td data-attr="license" data-attr-value="${company.license}">${company.license}</td>
                            <td data-attr="orgCode" data-attr-value="${company.orgCode}">${company.orgCode}</td>
                            <td data-attr="taxCode" data-attr-value="${company.taxCode}">${company.taxCode}</td>
                            <td data-attr="contactPerson" data-attr-value="${company.contactPerson}">${company.contactPerson}</td>
                            <td data-attr="mobile" data-attr-value="${company.mobile}">${company.mobile}</td>
                            <td data-attr="status" data-attr-value="${company.status}">
                                [#if company.status="NEW"]未审核
                                [#elseif company.status="CHECK_FAILED"]审核不通过
                                [#elseif company.status="CHECK_SUCCESS"]审核通过
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
        <div class="modal fade" id="createCompanyModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="createCompanyForm" class="form-horizontal" role="form" action="companies/create">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" class="form-control" placeholder="名称" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址</label>
                                <div class="col-sm-9">
                                    <input type="text" name="address" class="form-control" placeholder="地址" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">法人</label>
                                <div class="col-sm-9">
                                    <input type="text" name="legalPerson" class="form-control" placeholder="法人" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">法人身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="legalPersonIdNumber" class="form-control" placeholder="法人身份证号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">营业执照号码</label>
                                <div class="col-sm-9">
                                    <input type="text" name="license" class="form-control" placeholder="营业执照号码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">组织机构代码</label>
                                <div class="col-sm-9">
                                    <input type="text" name="orgCode" class="form-control" placeholder="组织机构代码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">税务登记号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="taxCode" class="form-control" placeholder="税务登记号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人</label>
                                <div class="col-sm-9">
                                    <input type="text" name="contactPerson" class="form-control" placeholder="联系人" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人手机号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="mobile" class="form-control" placeholder="联系人手机号" required>
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
        <div class="modal fade" id="editCompanyModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="editCompanyForm" class="form-horizontal" role="form" action="companies/update">
                            <input type="hidden" name="id"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" class="form-control" placeholder="名称" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址</label>
                                <div class="col-sm-9">
                                    <input type="text" name="address" class="form-control" placeholder="地址" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">法人</label>
                                <div class="col-sm-9">
                                    <input type="text" name="legalPerson" class="form-control" placeholder="法人" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">法人身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="legalPersonIdNumber" class="form-control" placeholder="法人身份证号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">营业执照号码</label>
                                <div class="col-sm-9">
                                    <input type="text" name="license" class="form-control" placeholder="营业执照号码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">组织机构代码</label>
                                <div class="col-sm-9">
                                    <input type="text" name="orgCode" class="form-control" placeholder="组织机构代码" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">税务登记号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="taxCode" class="form-control" placeholder="税务登记号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人</label>
                                <div class="col-sm-9">
                                    <input type="text" name="contactPerson" class="form-control" placeholder="联系人" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人手机号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="mobile" class="form-control" placeholder="联系人手机号" required>
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

            $('#createCompanyBtn').on('click', function () {
                $("#createCompanyModal").modal({backdrop: 'static', keyboard: false});
            });
            /* ajax 提交分组表单 */
            $('#createCompanyForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });
            $('#editCompanyForm').ajaxForm({
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
                var address = $(tr).find('td[data-attr=address]').attr("data-attr-value");
                var legalPerson = $(tr).find('td[data-attr=legalPerson]').attr("data-attr-value");
                var legalPersonIdNumber = $(tr).find('td[data-attr=legalPersonIdNumber]').attr("data-attr-value");
                var license = $(tr).find('td[data-attr=license]').attr("data-attr-value");
                var orgCode = $(tr).find('td[data-attr=orgCode]').attr("data-attr-value");
                var taxCode = $(tr).find('td[data-attr=taxCode]').attr("data-attr-value");
                var contactPerson = $(tr).find('td[data-attr=contactPerson]').attr("data-attr-value");
                var mobile = $(tr).find('td[data-attr=mobile]').attr("data-attr-value");

                // show model
                $('#editCompanyModal').modal({backdrop: 'static', keyboard: false});

                // set attribute
                $('#editCompanyForm').find('input[name=id]').val(id);
                $('#editCompanyForm').find('input[name=name]').val(name);
                $('#editCompanyForm').find('input[name=address]').val(address);
                $('#editCompanyForm').find('input[name=legalPerson]').val(legalPerson);
                $('#editCompanyForm').find('input[name=legalPersonIdNumber]').val(legalPersonIdNumber);
                $('#editCompanyForm').find('input[name=license]').val(license);
                $('#editCompanyForm').find('input[name=orgCode]').val(orgCode);
                $('#editCompanyForm').find('input[name=taxCode]').val(taxCode);
                $('#editCompanyForm').find('input[name=contactPerson]').val(contactPerson);
                $('#editCompanyForm').find('input[name=mobile]').val(mobile);


            });


            /* 删除 */
            $('button[name=deleteBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var companyName = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                var companyId = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认删除企业【'+companyName+'】吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/companies/delete',
                                data: {id: companyId},
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