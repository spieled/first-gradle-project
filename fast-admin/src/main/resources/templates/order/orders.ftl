[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 缴费记录</title>
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
                <h4><i class="fa fa-table"></i>缴费记录</h4>
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
                        <th>流水号</th>
                        <th>标题</th>
                        <th>明细</th>
                        <th>总价</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if pageData.content?? && pageData.content?size>0]
                        [#list pageData.content as order]
                        <tr value="${order.id}">
                            <td data-attr="id" data-attr-value="${order.id}">${order_index+1}</td>
                            <td data-attr="serialNumber" data-attr-value="${order.serialNumber}">${order.serialNumber}</td>
                            <td data-attr="title" data-attr-value="${order.title}">${order.title}</td>
                            <td data-attr="itemNumber" data-attr-value="${order.itemNumber}">${order.itemNumber}</td>
                            <td data-attr="totalPrice" data-attr-value="${order.totalPrice}">${order.totalPrice}</td>
                            <td data-attr="status" data-attr-value="${order.status}">[@name type="order.status" id="${order.status}"]${display}[/@name]</td>
                            <td class="hidden-xs">
                                [#if order.status=="WAIT_PAY"]
                                    <button name="deleteBtn" class="btn btn-sm btn-danger">删除</button>
                                    <button name="payBtn" class="btn btn-sm btn-danger">支付</button>
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

    [#-- 支付modal --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="payModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="payForm" class="form-horizontal" role="form" action="orders/pay">
                            <input type="hidden" name="orderId"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">流水号</label>
                                <div class="col-sm-9">
                                    <input type="text" name="serialNumber" class="form-control" placeholder="流水号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题</label>
                                <div class="col-sm-9">
                                    <input type="text" name="title" class="form-control" placeholder="流水号" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">金额</label>
                                <div class="col-sm-9">
                                    <input type="text" name="totalPrice" class="form-control" placeholder="金额" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账户余额</label>
                                <div class="col-sm-9">
                                    <input type="hidden" name="accountId">
                                    <input type="number" name="accountBalance" class="form-control" placeholder="账户余额" readonly>
                                </div>
                            </div>
                            <div class="form-group center">
                                <button class="btn btn-light-grey" data-dismiss="modal">放弃支付</button>
                                <button type="submit" class="btn btn-success">确认支付</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#include '../base/footer.html'/]
    <script>

        $(function () {

            /* 删除 */
            $('button[name=deleteBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var serialNumber = $(tr).find('td[data-attr=serialNumber]').attr("data-attr-value");
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认删除缴费记录【'+serialNumber+'】吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/orders/delete',
                                data: {id: id},
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

            /* 支付按钮 */
            $('button[name=payBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var id = $(tr).find('td[data-attr=id]').attr('data-attr-value');
                var serialNumber = $(tr).find('td[data-attr=serialNumber]').attr('data-attr-value');
                var title = $(tr).find('td[data-attr=title]').attr('data-attr-value');
                var totalPrice = $(tr).find('td[data-attr=totalPrice]').attr('data-attr-value');
                var companyId = 0;
                $.ajax({
                    url: '/profile/balance',
                    data: {companyId: companyId},
                    success: function(response) {
                        if (response.success) {
                            // show module and fill balance
                            $('#payForm').find('input[name=accountId]').val(response.content.id);
                            $('#payForm').find('input[name=accountBalance]').val(response.content.balance);
                            $('#payForm').find('input[name=orderId]').val(id);
                            $('#payForm').find('input[name=serialNumber]').val(serialNumber);
                            $('#payForm').find('input[name=title]').val(title);
                            $('#payForm').find('input[name=totalPrice]').val(totalPrice);

                            $('#payModal').modal({backdrop: 'static', keyboard: false});

                        } else {
                            Confirm.show('操作提示', response.msg);
                        }
                    }
                });
            });

            /* 确认支付 */
            $('#payForm').ajaxForm({
                success: function(response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });

        })
    </script>
    </body>
</html>