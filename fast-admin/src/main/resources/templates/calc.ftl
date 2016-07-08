[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 社保计算器</title>
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
                <h4><i class="fa fa-table"></i>社保计算器</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <form id="calcForm" class="form-horizontal" role="form" action="/common/calc">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">投保城市</label>
                        <div class="col-sm-9">
                            <select name="city" class="form-control" required>
                                <option value="成都">成都</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">户籍类型</label>
                        <div class="col-sm-9">
                            <select name="type" class="form-control" required>
                                <option value="本地城镇">本地城镇</option>
                                <option value="本地农村">本地农村</option>
                                <option value="外地城镇">外地城镇</option>
                                <option value="外地农村">外地农村</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">大病医疗</label>
                        <div class="col-sm-9">
                            <label><input type="checkbox" name="sick" value="1">大病医疗</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">投保基数</label>
                        <div class="col-sm-9">
                            <input type="number" name="insureBase" class="form-control" placeholder="投保基数" value="1791">
                        </div>
                    </div>
                    <div class="form-group center">
                        <button type="submit" class="btn btn-success">计算</button>
                    </div>
                </form>
                <div id="calcResult">
                    <table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">
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

                </div>
            </div>
        </div>
        <!-- /BOX -->

    [#include 'base/content_footer.html'/]
    </section>

    [#include 'base/footer.html'/]
    <script>

        $(function () {

            /* ajax 提交表单 */
            $('#calcForm').ajaxForm({
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


        })
    </script>
    </body>
</html>