[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 账户信息</title>
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
                <h4><i class="fa fa-table"></i>账户信息</h4>
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
                        <th>账户名称</th>
                        <th>类型</th>
                        <th>企业</th>
                        <th>余额</th>
                        <th>冻结资金</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if accounts?? && accounts?size>0]
                        [#list accounts as account]
                        <tr value="${account.id}">
                            <td data-attr="id" data-attr-value="${account.id}">${account_index+1}</td>
                            <td data-attr="name" data-attr-value="${account.name}">${account.name}</td>
                            <td data-attr="type" data-attr-value="${account.type}">
                                [#if account.type=="PERSONEL"]个人[#elseif account.type=="COMPANY"]企业[/#if]
                            </td>
                            <td data-attr="companyId" data-attr-value="${account.companyId}">
                                [@companyDirective id="${account.companyId}"]
                                ${name}
                                [/@companyDirective]
                            </td>
                            <td data-attr="balance" data-attr-value="${account.balance}">${account.balance}</td>
                            <td data-attr="frozenAmount" data-attr-value="${account.frozenAmount}">${account.frozenAmount}</td>
                            <td class="hidden-xs">
                                [#--<button name="uploadTicketBtn" class="btn btn-sm btn-primary">上传打款凭证</button>--]
                                <button name="ticketBtn" class="btn btn-sm btn-primary">汇款凭证</button>
                                <button name="recordBtn" class="btn btn-sm btn-primary">账户变更记录</button>
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
            </div>
        </div>
        <!-- /BOX -->

    [#include 'base/content_footer.html'/]
    </section>

    [#-- 新增 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="uploadTicketModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="uploadTicketForm" class="form-horizontal" role="form" action="/profile/uploadTicket">
                            <input type="hidden" name="accountId">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账户</label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" class="form-control" placeholder="账户" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">汇款银行</label>
                                <div class="col-sm-9">
                                    <input type="text" name="bank" class="form-control" placeholder="汇款银行" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">汇款金额</label>
                                <div class="col-sm-9">
                                    <input type="text" name="amount" class="form-control" placeholder="汇款金额" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">银行流水号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="serialNumber" class="form-control" placeholder="银行流水号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">银行卡号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="cardNumber" class="form-control" placeholder="银行卡号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">汇款凭证小票</label>
                                <div class="col-sm-9">
                                    <input type="text" name="ticket" class="form-control" placeholder="汇款凭证小票" required>
                                </div>
                            </div>
                            <div class="form-group center">
                                <button class="btn btn-light-grey" data-dismiss="modal">放弃</button>
                                <button type="submit" class="btn btn-success">提交</button>
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
            $('button[name=uploadTicketBtn]').on('click', function () {
                // 获取账户ID，账户名
                var tr = $(this).parent().parent();

                // get attribute
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                var name = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                $("#uploadTicketModal").modal({backdrop: 'static', keyboard: false});
                $("#uploadTicketForm").find("input[name=accountId]").val(id);
                $("#uploadTicketForm").find("input[name=name]").val(name);
            });
            /* ajax 提交分组表单 */
            $('#uploadTicketForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });

            $('button[name=recordBtn]').on('click', function () {
                // 获取账户ID，账户名
                var tr = $(this).parent().parent();
                // get attribute
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                window.location.href = "/profile/accounts?action=records&id="+id;
            });

            $('button[name=ticketBtn]').on('click', function () {
                // 获取账户ID，账户名
                var tr = $(this).parent().parent();
                // get attribute
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                window.location.href = "/profile/accounts?action=offlines&id="+id;
            });



        })
    </script>
    </body>
</html>