[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 个人信息</title>
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
                <h4><i class="fa fa-table"></i>个人信息</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <form id="profileForm" class="form-horizontal" role="form" action="/profile/update">
                    <input type="hidden" name="id" value="${profile.id}">
                    <input type="hidden" name="username" value="${profile.username}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称</label>
                        <div class="col-sm-9">
                            <input type="text" name="nickName" class="form-control" placeholder="昵称" value="${profile.nickName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">真实姓名</label>
                        <div class="col-sm-9">
                            <input type="text" name="realName" class="form-control" placeholder="真实姓名" value="${profile.realName!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">地址</label>
                        <div class="col-sm-9">
                            <input type="text" name="address" class="form-control" placeholder="地址" value="${profile.address!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input type="email" name="email" class="form-control" placeholder="邮箱" value="${profile.email!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-9">
                            <input type="text" name="mobile" class="form-control" placeholder="手机号" value="${profile.mobile!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-9 checkbox-inline">
                            <label><input type="radio" id="gender_MALE" name="gender" value="MALE" [#if profile.gender=="MALE"]checked="checked"[/#if]>
                                男</label>
                            <label><input type="radio" id="gender_FEMALE" name="gender" value="FEMALE" [#if profile.gender=="FEMALE"]checked="checked"[/#if]>
                                女</label>
                            <label><input type="radio" id="gender_UNKNOWN" name="gender" value="UNKNOWN" [#if profile.gender=="UNKNOWN"]checked="checked"[/#if]>
                                保密</label>
                        </div>
                    </div>
                    <div class="form-group center">
                        <button type="submit" class="btn btn-success">保存</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /BOX -->

    [#include '../base/content_footer.html'/]
    </section>

    [#include '../base/footer.html'/]
    <script>

        $(function () {
            /* ajax 提交表单 */
            $('#profileForm').ajaxForm({
                success: function (response) {
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