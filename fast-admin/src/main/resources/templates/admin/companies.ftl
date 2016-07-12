[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 企业</title>
        [#include '../base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include '../base/macro.ftl'/]
    [#include '../base/header.ftl'/]
    [#include '../base/nav.html'/]
    [#include '../base/content_header.html'/]

        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4><i class="fa fa-table"></i>企业</h4>
                <div class="tools hidden-xs">
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
                                [#if company.status="NEW"]
                                    <button name="checkBtn" data-attr-value="CHECK_SUCCESS" data-attr-display="审核通过" class="btn btn-sm  btn-grey">审核通过</button>
                                    <button name="checkBtn" data-attr-value="CHECK_FAILED" data-attr-display="审核不通过" class="btn btn-sm btn-danger">审核不通过</button>
                                [/#if]
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

    [#include '../base/content_footer.html'/]
    </section>


    [#include '../base/footer.html'/]
    <script>

        $(function () {
            /* 审核通不通过 */
            $('button[name=checkBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var status = $(this).attr("data-attr-value");
                var statusDisplay = $(this).attr("data-attr-display");
                var companyName = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                var companyId = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认将企业【'+companyName+'】审核状态更新为【'+statusDisplay+'】吗？', {
                    '确认': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/admin/companies/check',
                                data: {id: companyId, status: status},
                                success: function(response) {
                                    if (response.success) {
                                        window.location.reload();
                                    } else {
                                        Confirm.show("操作提示", response.msg);
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