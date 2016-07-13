[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 个人缴社保</title>
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
                <h4><i class="fa fa-table"></i>个人缴社保</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <form id="orderForm" class="form-horizontal" role="form" action="/orders/create">
                    <div id="chooseDiv">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">参保人</label>
                        <div class="col-sm-9">
                            <select name="personId" id="personId" class="form-control" onchange="choosePerson(this)">
                                <option value="">请选择</option>
                            [@persons username="${currentUsername}"]
                            [#if personList?? && personList?size>0]
                            [#list personList as person]
                            <option value="${person.id}" data-attr-idnumber="${person.idNumber}" data-attr-type="${person.type}">${person.name}</option>
                            [/#list]
                            [/#if]
                            [/@persons]
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" name="name" id="name" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input type="text" name="idNumber" id="idNumber" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">户籍类型</label>
                        <div class="col-sm-9">
                            <input type="text" id="type" name="type" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">大病医疗</label>
                        <div class="col-sm-9">
                            <label><input type="checkbox" id="sick" name="sick" value="1">大病医疗</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">投保基数</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="number" id="insureBase" name="insureBase" class="form-control" placeholder="投保基数" value="1791" min="1791">
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="lowestBaseBtn">
                                最低基数
                                </button>
                            </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">缴费月份</label>
                        <div class="col-sm-9">
                            <input type="number" name="insureYm" id="insureYm" class="form-control" placeholder="缴费月份（格式yyyyMM，如201607）" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">缴费城市</label>
                        <div class="col-sm-9">
                            <select name="city" class="form-control" required>
                                <option value="510100">成都</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否曾参保</label>
                        <div class="col-sm-9">
                            <label><input type="radio" name="insured" value="1">曾参保</label>
                            <label><input type="radio" name="insured" value="1">从未参保</label>
                            <label><input type="radio" name="insured" value="0">不清楚</label>
                        </div>
                    </div>
                    </div>
                    <div id="calcResultDiv" class="hidden">
                        [@calcTable /]
                    </div>
                    <div class="form-group center">
                        <button class="btn btn-success" id="calcBtn">计算费率</button>
                        <button class="btn btn-success hidden" id="reChooseBtn">返回重新选择</button>
                        <button type="submit" class="btn btn-success hidden" id="submitBtn">提交</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /BOX -->

    [#include '../base/content_footer.html'/]
    </section>

    [#include '../base/footer.html'/]
    <script src="/js/jquery-validate/jquery.validate.min.js"></script>
    <script>

        function choosePerson(e) {
            var personId = $(e).val();
            var option = $(e).find('option[value='+personId+']')[0];
            var name = $(option).text();
            var idNumber = $(option).attr('data-attr-idnumber');
            var type = $(option).attr('data-attr-type');
            var cityCode = $(option).attr('data-attr-city');
            var typeStr = getTypeStr(cityCode, type);
            $('#name').val(name);
            $('#idNumber').val(idNumber);
            $('#type').val(typeStr);
        }

        function getTypeStr(cityCode, type) {
            var typeStr;
            if (cityCode == 510100) {
                typeStr = "本地";
            } else {
                typeStr = "外地";
            }
            if (type=='CITY') {
                typeStr += '城镇';
            } else {
                typeStr += '农村';
            }
            return typeStr;
        }

        $(function () {
            $('#lowestBaseBtn').on('click', function () {
                $('#insureBase').val('1791');
            });

            $('#orderForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.reload();
                    } else {
                        Confirm.show('操作提示', response.msg);
                    }
                }
            });

            /* 计算费用 */
            $('#calcBtn').on('click', function(e) {
                e.preventDefault();
                // 验证form
                var personId = $('#personId').val();
                var insureBase = $('#insureBase').val();
                var insureYm = $('#insureYm').val();
                if (personId == undefined || personId == "") {
                    Confirm.show('表单验证', '参保人必须选择');
                    return false;
                }
                if (insureYm == undefined || insureYm == "") {
                    Confirm.show('表单验证', '缴费月份必须填写');
                    return false;
                }
                if (insureBase == undefined || insureBase == "") {
                    Confirm.show('表单验证', '缴费基数必须填写');
                    return false;
                }
                var type = $('#type').val();
                var sick = $('#sick').is(':checked') ? "true" : "false";
                var insureBase = $('#insureBase').val();
                $.ajax({
                    url: '/common/calc',
                    data: {type: type, sick: sick, insureBase: insureBase},
                    success: function (response) {
                        if (response.success) {
                            // TODO 更新页面UI
                            // $("#calcResult").html("缴费总金额：" + response.content.total + "<button class='detailBtn' onclick='showDetail()'>点击查看详情</button>");
                            $('#endowmentPercentCompany').text(100*response.content.endowmentPercentCompany+"%");
                            $('#endowmentPercentPerson').text(100*response.content.endowmentPercentPerson+"%");
                            $('#medicalPercentCompany').text(100*response.content.medicalPercentCompany+"%");
                            $('#medicalPercentPerson').text(100*response.content.medicalPercentPerson+"%");
                            $('#unemployedPercentCompany').text(100*response.content.unemployedPercentCompany+"%");
                            $('#unemployedPercentPerson').text(100*response.content.unemployedPercentPerson+"%");
                            $('#maternityPercentCompany').text(100*response.content.maternityPercentCompany+"%");
                            $('#maternityPercentPerson').text(100*response.content.maternityPercentPerson+"%");
                            $('#injuryPercentCompany').text(100*response.content.injuryPercentCompany+"%");
                            $('#injuryPercentPerson').text(100*response.content.injuryPercentPerson+"%");
                            $('#sickPercentCompany').text(100*response.content.sickPercentCompany+"%");
                            $('#sickPercentPerson').text(100*response.content.sickPercentPerson+"%");
                            $('#totalPercent').text((100*response.content.totalPercent).toFixed(2)+"%");

                            $('#endowmentCompany').text(response.content.endowmentCompany);
                            $('#endowmentPerson').text(response.content.endowmentPerson);
                            $('#medicalCompany').text(response.content.medicalCompany);
                            $('#medicalPerson').text(response.content.medicalPerson);
                            $('#unemployedCompany').text(response.content.unemployedCompany);
                            $('#unemployedPerson').text(response.content.unemployedPerson);
                            $('#maternityCompany').text(response.content.maternityCompany);
                            $('#maternityPerson').text(response.content.maternityPerson);
                            $('#injuryCompany').text(response.content.injuryCompany);
                            $('#injuryPerson').text(response.content.injuryPerson);
                            $('#sickCompany').text(response.content.sickCompany);
                            $('#sickPerson').text(response.content.sickPerson);
                            $('#total').text(response.content.total);

                        } else {
                            Confirm.show('操作提示', response.msg);
                        }
                    }
                });
                // 隐藏chooseDiv、calcBtn，calcResultDiv、reChooseBtn和submitBtn
                $('#chooseDiv').addClass("hidden");
                $('#calcBtn').addClass('hidden');
                $('#reChooseBtn').removeClass('hidden');
                $('#submitBtn').removeClass('hidden');
                $('#calcResultDiv').removeClass('hidden');

                return false;
            });

            $('#reChooseBtn').on('click', function(e) {
               e.preventDefault();
                // 展示chooseDiv、calcBtn，隐藏calcResultDiv、reChooseBtn和submitBtn
                $('#chooseDiv').removeClass("hidden");
                $('#calcBtn').removeClass('hidden');
                $('#reChooseBtn').addClass('hidden');
                $('#submitBtn').addClass('hidden');
                $('#calcResultDiv').addClass('hidden');
                return false;
            });

        })
    </script>
    </body>
</html>