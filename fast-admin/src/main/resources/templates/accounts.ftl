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
                            <td data-attr="type" data-attr-value="${account.type}">
                                [#if account.type=="PERSONEL"]个人[#elseif account.gender=="COMPANY"]企业[/#if]
                            </td>
                            <td data-attr="companyId" data-attr-value="${account.companyId}">
                                [@companyDirective id="${account.companyId}"/]
                            </td>
                            <td data-attr="balance" data-attr-value="${account.balance}">${account.balance}</td>
                            <td data-attr="frozenAmount" data-attr-value="${account.frozenAmount}">${account.frozenAmount}</td>
                            <td class="hidden-xs">
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

    [#include 'base/footer.html'/]
    <script>

        $(function () {

        })
    </script>
    </body>
</html>