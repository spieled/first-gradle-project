[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 工作台</title>
        [#include 'base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include 'base/header.ftl'/]
    [#include 'base/nav.html'/]
    [#include 'base/content_header.html'/]

        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4>工作台</h4>
            </div>
            <div class="box-body">
                工作台，这里展示日常工作内容。类似电脑桌面功能。
            </div>
        </div>
        <!-- /BOX -->

    [#include 'base/content_footer.html'/]
    </section>

    [#include 'base/footer.html'/]
    </body>
</html>