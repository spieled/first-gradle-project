[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 充值凭证</title>
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
                <h4><i class="fa fa-table"></i>充值凭证</h4>
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
                        <th>用户</th>
                        <th>账户</th>
                        <th>汇款银行</th>
                        <th>汇款金额</th>
                        <th>银行流水号</th>
                        <th>银行卡号</th>
                        <th>汇款凭证小票</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if pageData.content?? && pageData.content?size>0]
                        [#list pageData.content as record]
                        <tr value="${record.id}">
                            <td data-attr="id" data-attr-value="${record.id}">${record_index+1}</td>
                            <td data-attr="username" data-attr-value="${record.username}">[@name type="user" id="${record.username}"]${display}[/@name]</td>
                            <td data-attr="accountId" data-attr-value="${record.accountId}">[@name type="account" id="${record.accountId}"]${display}[/@name]</td>
                            <td data-attr="bank" data-attr-value="${record.bank}">${record.bank}</td>
                            <td data-attr="amount" data-attr-value="${record.amount}">${record.amount}</td>
                            <td data-attr="serialNumber" data-attr-value="${record.serialNumber}">${record.serialNumber}</td>
                            <td data-attr="cardNumber" data-attr-value="${record.cardNumber}">${record.cardNumber}</td>
                            <td data-attr="ticket" data-attr-value="${record.ticket}"><img src="${record.ticket}" height="50"> </td>
                            <td data-attr="status" data-attr-value="${record.status}">[@name type="offlinePayRecord.status" id="${record.status}"]${display}[/@name]</td>
                            <td class="hidden-xs">
                                [#if record.status="NEW"]
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
                var display = $(tr).find('td[data-attr=username]').text() + " - " + $(tr).find('td[data-attr=accountId]').text();
                var recordId = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认将打款凭证【'+display+'】审核状态更新为【'+statusDisplay+'】吗？', {
                    '确认': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/admin/offlinePayRecord/check',
                                data: {id: recordId, status: status},
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