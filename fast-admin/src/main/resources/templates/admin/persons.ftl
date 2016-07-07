[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 参保人员</title>
        [#include '../base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include '../base/macro.ftl'/]
    [#include '../base/header.html'/]
    [#include '../base/nav.html'/]
    [#include '../base/content_header.html'/]

        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4><i class="fa fa-table"></i>参保人员</h4>
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
                                [#if person.status="NEW"]
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
                var personName = $(tr).find('td[data-attr=name]').attr("data-attr-value");
                var personId = $(tr).find('td[data-attr=id]').attr("data-attr-value");
                Confirm.show('操作提示', '确认将参保人【'+personName+'】审核状态更新为【'+statusDisplay+'】吗？', {
                    '确认': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/admin/persons/check',
                                data: {id: personId, status: status},
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