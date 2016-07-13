[#ftl]
[#--"first":true,"last":true,"number":0,"numberOfElements":1,"size":15,
"sort":[{"ascending":true,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}],
"totalElements":1,"totalPages":1--]
[#macro pagination totalPages=1 number=0 first=true last=true]
    [#if totalPages?number>0]
    [#assign start][#if number?number-1 >0]${number?number-1}[#else]1[/#if][/#assign]
    [#assign end][#if number?number+3 >totalPages?number]${totalPages?number}[#else]${number?number+3}[/#if][/#assign]

    <div class="col-md-6">
        <ul class="pagination">
            <li [#if first=="true"]class="disabled" [/#if]><a href="javascript:;" data-page-number="0">首页</a></li>
            [#list start?number..end?number as n]
                [#if n_index == 0 && n > 1]
                    <li><a href="javascript:;" data-page-number="${n-2}">...</a></li>
                [/#if]
                <li [#if n-1==number?number]class="active" [/#if]><a href="javascript:;" data-page-number="${n-1}">${n}</a></li>
                [#if !n_has_next && n < totalPages?number]
                    <li><a href="javascript:;" data-page-number="${n}">...</a></li>
                [/#if]
            [/#list]
            <li [#if last=="true"]class="disabled" [/#if]><a href="javascript:;" data-page-number="${totalPages?number-1}">尾页</a></li>
        </ul>
    </div>
    [/#if]
[/#macro]

[#macro calcTable]
<table id="calcTable" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th rowspan="2">#</th>
        <th colspan="2">基本养老保险</th>
        <th colspan="2">基本医疗保险</th>
        <th colspan="2">失业保险</th>
        <th colspan="2">生育保险</th>
        <th colspan="2">工伤保险</th>
        <th colspan="2">大病医疗保险</th>
        <th rowspan="2">合计</th>
    </tr>
    <tr>
        <th>个人</th>
        <th>单位</th>
        <th>个人</th>
        <th>单位</th>
        <th>个人</th>
        <th>单位</th>
        <th>个人</th>
        <th>单位</th>
        <th>个人</th>
        <th>单位</th>
        <th>个人</th>
        <th>单位</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>比例</td>
        <td id="endowmentPercentCompany"></td>
        <td id="endowmentPercentPerson"></td>
        <td id="medicalPercentCompany"></td>
        <td id="medicalPercentPerson"></td>
        <td id="unemployedPercentCompany"></td>
        <td id="unemployedPercentPerson"></td>
        <td id="maternityPercentCompany"></td>
        <td id="maternityPercentPerson"></td>
        <td id="injuryPercentCompany"></td>
        <td id="injuryPercentPerson"></td>
        <td id="sickPercentCompany"></td>
        <td id="sickPercentPerson"></td>
        <td id="totalPercent"></td>
    </tr>
    <tr>
        <td>金额</td>
        <td id="endowmentCompany"></td>
        <td id="endowmentPerson"></td>
        <td id="medicalCompany"></td>
        <td id="medicalPerson"></td>
        <td id="unemployedCompany"></td>
        <td id="unemployedPerson"></td>
        <td id="maternityCompany"></td>
        <td id="maternityPerson"></td>
        <td id="injuryCompany"></td>
        <td id="injuryPerson"></td>
        <td id="sickCompany"></td>
        <td id="sickPerson"></td>
        <td id="total"></td>
    </tr>
    </tbody>
</table>
[/#macro]