[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 修改密码</title>
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
                <h4><i class="fa fa-table"></i>修改密码</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <form id="updateForm" class="form-horizontal" role="form" action="/profile/password/update">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">旧密码</label>
                        <div class="col-sm-9">
                            <input type="password" name="oldPassword" class="form-control" placeholder="旧密码" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-9">
                            <input type="password" name="newPassword" class="form-control" placeholder="新密码" required minlength="6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认新密码</label>
                        <div class="col-sm-9">
                            <input type="password" name="newPassword2" class="form-control" placeholder="确认新密码" required minlength="6">
                        </div>
                    </div>
                    <div class="form-group center">
                        <button type="submit" class="btn btn-success">修改密码</button>
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

            $('#updateForm').on('submit', function(e) {
               e.preventDefault();
                var password = $('input[name=newPassword]').val();
                var password2 = $('input[name=newPassword2]').val();
                if (password != password2) {
                    Confirm.show('操作提示', '新密码与确认新密码不一致');
                    $('input[name=newPassword2]').focus();
                    return false;
                }
                /* ajax 提交表单 */
                $('#updateForm').ajaxSubmit({
                    success: function (response) {
                        if (response.success) {
                            Confirm.show('操作提示', '密码修改成功！请退出，重新登录！');
                            window.setTimeout(function () {
                                // 触发登出按钮的点击事件
                                $('#logoutBtn').trigger('click');
                            }, 1000);
                        } else {
                            Confirm.show('操作提示', response.msg);
                        }
                    }
                });
            });

        })
    </script>
    </body>
</html>