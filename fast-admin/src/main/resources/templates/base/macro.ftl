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