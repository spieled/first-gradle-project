[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 实名认证</title>
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
                <h4><i class="fa fa-table"></i>实名认证</h4>
                <div class="tools hidden-xs">
                    <a href="javascript:;" class="collapse">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="box-body">
                <form id="profileForm" class="form-horizontal" role="form" action="">
                    <input type="hidden" id="idPositive" name="idPositive" value="${profile.idPositive!'/img/img.png'}">
                    <input type="hidden" id="idNegtive" name="idNegtive" value="${profile.idNegtive!'/img/img.png'}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证正面</label>
                        <div class="col-sm-9">
                            <input style="display: none;" type="file" value="上传身份证正面" id="uploadInput" name="file" accept="image/*"
                                   multiple
                                   capture="camera"/>
                            <div class="upload-label" id="upload-label">
                                <label for="uploadInput" class="btn btn-primary" id="uploadInputDisplayBtn">&nbsp;点击选择图片&nbsp;</label>
                            </div>
                            <div id="uploadPreviewDiv" class="text-center form-group" style="margin-top:20px;">
                                <img src="${profile.idPositive!'/img/img.png'}" width="300" id="uploadPreview"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证反面</label>
                        <div class="col-sm-9">
                            <input style="display: none;" type="file" value="上传身份证反面" id="uploadInput2" name="file" accept="image/*"
                                   multiple
                                   capture="camera"/>
                            <div class="upload-label" id="upload-label2">
                                <label for="uploadInput2" class="btn btn-primary" id="uploadInputDisplayBtn2">&nbsp;点击选择图片&nbsp;</label>
                            </div>
                            <div id="uploadPreviewDiv2" class="text-center form-group" style="margin-top:20px;">
                                <img src="${profile.idNegtive!'/img/img.png'}" width="300" id="uploadPreview2"/>
                            </div>
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
    <script src="/js/compress/LocalResizeIMG.js" type="text/javascript"></script>
    <!-- mobileBUGFix.js 兼容修复移动设备 -->
    <script src="/js/compress/patch/mobileBUGFix.mini.js" type="text/javascript"></script>
    <script>

        $(function () {

            var compressedFile;
            /* ajax 提交表单 */
            $('#profileForm').on('submit', function (e) {
                e.preventDefault();
                var idPositive = $('#idPositive').val();
                var idNegtive = $('#idNegtive').val();
                $.ajax({
                    url: '/profile/idPic/update',
                    data: {idPositive: idPositive, idNegtive: idNegtive},
                    success: function (response) {
                        if (response.success) {
                            window.location.reload();
                        } else {
                            Confirm.show('操作提示', response.msg);
                        }
                    }
                });
                return false;
            });

            // 预览原始图，上传压缩图
            $('#uploadInput').localResizeIMG({
                width: 300,
                quality: 1,
                success: function (result) {

                    $('#uploadPreview').remove();
                    var img = new Image();
                    img.src = result.base64;
                    img.id = "uploadPreview";
                    img.width = 300;
                    compressedFile = result;
                    $('#uploadPreviewDiv').append(img);

                    $('#uploadSubmitBtn').remove();
                    $('#uploadInputDisplayBtn').html(' 不满意？重选 ');
                    $('<button class="btn btn-primary" id="uploadSubmitBtn"/>').text('  上   传  ')
                            .appendTo($('#upload-label'))
                            .click(function (e) {
                                e.preventDefault();
                                var token = $("#csrfToken").val();
                                $(this).replaceWith($('<button class="btn btn-primary" id="uploadSubmitBtn"/>').text('上 传 中 ...'));
                                $.ajax({
                                    url: '/common/upload',
                                    data: {
                                        _csrf: token,
                                        file: compressedFile.clearBase64,
                                        name: 'fake.jpg'
                                    },
                                    type: 'post',
                                    success: function (response) {
                                        if (response.success) {
                                            var url = response.content;
                                            $('#idPositive').val(url);
                                            $('#uploadSubmitBtn').remove();
                                            Confirm.show("操作提示", "上传完成！若要保存，请点击【保存】！");
                                        } else {
                                            Confirm.show("操作提示", response.msg);
                                        }
                                    },
                                    error: function (e) {
                                        console.log(e);
                                    }
                                });
                                return false;
                            });
                }
            });

            // 预览原始图，上传压缩图
            $('#uploadInput2').localResizeIMG({
                width: 300,
                quality: 1,
                success: function (result) {
                    $('#uploadPreview2').remove();
                    var img = new Image();
                    img.src = result.base64;
                    img.id = "uploadPreview2";
                    img.width = 300;
                    compressedFile = result;
                    $('#uploadPreviewDiv2').append(img);

                    $('#uploadSubmitBtn2').remove();
                    $('#uploadInputDisplayBtn2').html(' 不满意？重选 ');
                    $('<button class="btn btn-primary" id="uploadSubmitBtn2"/>').text('  上   传  ')
                            .appendTo($('#upload-label2'))
                            .click(function (e) {
                                e.preventDefault();
                                var token = $("#csrfToken").val();
                                $(this).replaceWith($('<button class="btn btn-primary" id="uploadSubmitBtn2"/>').text('上 传 中 ...'));
                                $.ajax({
                                    url: '/common/upload',
                                    data: {
                                        _csrf: token,
                                        file: compressedFile.clearBase64,
                                        name: 'fake.jpg'
                                    },
                                    type: 'post',
                                    success: function (response) {
                                        if (response.success) {
                                            var url = response.content;
                                            $('#idNegtive').val(url);
                                            $('#uploadSubmitBtn2').remove();
                                            Confirm.show("操作提示", "上传完成！若要保存，请点击【保存】！");
                                        } else {
                                            Confirm.show("操作提示", response.msg);
                                        }
                                    },
                                    error: function (e) {
                                        console.log(e);
                                    }
                                });
                                return false;
                            });
                }
            });


        })
    </script>
    </body>
</html>