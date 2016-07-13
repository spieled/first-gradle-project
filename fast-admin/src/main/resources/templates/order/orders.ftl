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
                        <th>明显</th>
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
                            <td data-attr="status" data-attr-value="${order.status}">${order.status}</td>
                            <td class="hidden-xs">
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

    [#include '../base/content_footer.html'/]
    </section>


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
        })
    </script>
    </body>
</html>