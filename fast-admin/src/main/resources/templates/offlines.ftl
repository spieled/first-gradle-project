[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 充值凭证</title>
        [#include 'base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include 'base/macro.ftl'/]
    [#include 'base/header.ftl'/]
    [#include 'base/nav.html'/]
    [#include 'base/content_header.html'/]

        <!-- summary -->
        <div class="margin-bottom-10">
            账户：${account.name}；余额：${account.balance}；
        </div>
        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4><i class="fa fa-table"></i>充值凭证</h4>
                <div class="tools hidden-xs">
                    <button id="uploadTicketBtn" class="btn btn-sm btn-danger" data-attr-id="${account.id}" data-attr-name="${account.name}">上传打款凭证</button>
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
                                [#if record.status="NEW" || record.status="CHECK_FAILED"]
                                    <button name="editTicketBtn" class="btn btn-sm  btn-grey">修改</button>
                                    <button name="deleteTicketBtn" class="btn btn-sm btn-danger">删除</button>
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
                                    <input type="hidden" name="ticket" class="form-control" placeholder="汇款凭证小票" required>
                                    <input style="display: none;" type="file" value="上传照片" id="uploadInput" accept="image/*"
                                           multiple
                                           capture="camera"/>
                                    <div class="upload-label">
                                        <label for="uploadInput" class="btn btn-primary" id="uploadInputDisplayBtn">&nbsp;点击选择图片&nbsp;</label>
                                    </div>
                                    <div id="uploadPreviewDiv" class="text-center form-group" style="margin-top:20px;">
                                        <img src="/img/img.png" width="300" id="uploadPreview"/>
                                    </div>
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

    [#-- 更新 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="editTicketModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="editTicketForm" class="form-horizontal" role="form" action="/profile/updateTicket">
                            <input type="hidden" name="id">
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
    <script type="text/javascript" src="/js/jQuery-Cookie/jquery.cookie.min.js"></script>
    <script src="/js/compress/LocalResizeIMG.js" type="text/javascript"></script>
    <!-- mobileBUGFix.js 兼容修复移动设备 -->
    <script src="/js/compress/patch/mobileBUGFix.mini.js" type="text/javascript"></script>
    <script>

        var compressedFile;
        $(function () {

            $('#uploadTicketBtn').on('click', function () {
                // 获取账户ID，账户名
                // get attribute
                var id = $(this).attr("data-attr-id");
                var name = $(this).attr("data-attr-name");
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
            $('#editTicketForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });

            // 更新
            $('button[name=editTicketBtn]').on('click', function () {
                // 获取账户ID，账户名
                var tr = $(this).parent().parent();

                // get attribute
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                var name = $(tr).find('td[data-attr=accountId]').text();
                var bank = $(tr).find('td[data-attr=bank]').attr("data-attr-value");
                var amount = $(tr).find('td[data-attr=amount]').attr("data-attr-value");
                var serialNumber = $(tr).find('td[data-attr=serialNumber]').attr("data-attr-value");
                var cardNumber = $(tr).find('td[data-attr=cardNumber]').attr("data-attr-value");
                var ticket = $(tr).find('td[data-attr=ticket]').attr("data-attr-value");

                $("#editTicketModal").modal({backdrop: 'static', keyboard: false});
                $("#editTicketForm").find("input[name=id]").val(id);
                $("#editTicketForm").find("input[name=name]").val(name);
                $("#editTicketForm").find("input[name=bank]").val(bank);
                $("#editTicketForm").find("input[name=amount]").val(amount);
                $("#editTicketForm").find("input[name=serialNumber]").val(serialNumber);
                $("#editTicketForm").find("input[name=cardNumber]").val(cardNumber);
                $("#editTicketForm").find("input[name=ticket]").val(ticket);
            });

            /* 删除 */
            $('button[name=deleteTicketBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var amount = $(tr).find('td[data-attr=amount]').attr("data-attr-value");
                var id = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认删除金额为【'+amount+'】汇款记录吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/profile/deleteTicket',
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

            // 预览原始图，上传压缩图
            $('#uploadInput').localResizeIMG({
                width: 300,
                quality: 1,
                success: function (result) {

                    $('#uploadPreview').remove();
                    var img = new Image();
                    img.src = result.base64;
                    img.id = "uploadPreview";
                    img.width = 300;
                    compressedFile = result;
                    $('#uploadPreviewDiv').append(img);

                    $('#uploadSubmitBtn').remove();
                    $('#uploadInputDisplayBtn').html(' 不满意？重选 ');
                    $('<button class="btn btn-primary" id="uploadSubmitBtn"/>').text('  上   传  ')
                            .appendTo($('.upload-label'))
                            .click(function (e) {
                                e.preventDefault();
                                var token = $("#csrfToken").val();
                                $(this).replaceWith($('<button class="btn btn-primary" id="uploadSubmitBtn"/>').text('上 传 中 ...'));
                                $.ajax({
                                    url: '/common/upload',
                                    data: {
                                        _csrf: token,
                                        file: compressedFile.clearBase64,
                                        name: 'fake.jpg'
                                    },
                                    type: 'post',
                                    success: function (response) {
                                        if (response.success) {
                                            var url = response.content;
                                            $('input[name=ticket]').val(url);
                                            $('#uploadSubmitBtn').remove();
                                        } else {
                                            Confirm.show("操作提示", response.msg);
                                        }
                                    },
                                    error: function (e) {
                                        console.log(e);
                                    }
                                });
                                return false;
                            });
                }
            });

        })
    </script>
    </body>
</html>