[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 账户变更记录</title>
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
                <h4><i class="fa fa-table"></i>账户变更记录</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <div>
                    账户：${account.name}；余额：${account.balance}；
                </div>
                <table id="datatable1" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>账户</th>
                        <th>变更金额</th>
                        <th>支付渠道类型</th>
                        <th>备注</th>
                        <th>时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if pageData.content?? && pageData.content?size>0]
                        [#list pageData.content as record]
                        <tr value="${record.id}">
                            <td data-attr="id" data-attr-value="${record.id}">${record_index+1}</td>
                            <td data-attr="accountId" data-attr-value="${record.accountId}">[@name type="account" id="${record.accountId}"]${display}[/@name]</td>
                            <td data-attr="amount" data-attr-value="${record.amount}">${record.amount}</td>
                            <td data-attr="type" data-attr-value="${record.type}">[@name type="accountRecord.type" id="${record.type}"]${display}[/@name]</td>
                            <td data-attr="note" data-attr-value="${record.note}">${record.note}</td>
                            <td data-attr="createTime" data-attr-value="${record.createTime}">${record.createTime}</td>
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


    [#include 'base/footer.html'/]
    <script>

        $(function () {

        })
    </script>
    </body>
</html>