[#ftl]
[#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#assign currentUsername][@security.authentication property="principal.username"/][/#assign]
[#assign currentAvatar][@name type='avatar' id='${currentUsername}']${display!'/img/avatars/avatar3.jpg'}[/@name][/#assign]
<!DOCTYPE html>
<html>
<head>
    <title>快用工 | 头像</title>
[#include '../base/meta.html'/]
    <style>
        .upload-label {
            background-color: white;
            z-index: 55;
            position: relative;
            text-align: center;
            padding:10px;
        }
        .upload-label label {
            display: inline-block;
        }
    </style>
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
            <h4><i class="fa fa-table"></i>头像</h4>
            <div class="tools hidden-xs">
                <a href="javascript:;" class="collapse">
                    <i class="fa fa-chevron-up"></i>
                </a>
            </div>
        </div>
        <div class="box-body">
            <form id="avatarForm" class="form-horizontal" role="form" action="/profile/avatar/update">
                <input type="hidden" name="avatar" id="avatar" value="${profile.avatar!'/img/img.png'}"/>
                <input style="display: none;" type="file" value="上传照片" id="uploadInput" name="file" accept="image/*"
                       multiple
                       capture="camera"/>
                <div class="upload-label">
                    <label for="uploadInput" class="btn btn-primary" id="uploadInputDisplayBtn">&nbsp;点击选择图片&nbsp;</label>
                </div>
                <div id="uploadPreviewDiv" class="text-center form-group" style="margin-top:20px;">
                    <img src="${profile.avatar!'/img/img.png'}" width="300" id="uploadPreview"/>
                </div>
                <div class="form-group center">
                    <button type="submit" class="btn btn-success">保存头像</button>
                </div>
            </form>
        </div>
    </div>
    <!-- /BOX -->

[#include '../base/content_footer.html'/]
</section>

[#include '../base/footer.html'/]
<script type="text/javascript" src="/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/js/compress/LocalResizeIMG.js" type="text/javascript"></script>
<!-- mobileBUGFix.js 兼容修复移动设备 -->
<script src="/js/compress/patch/mobileBUGFix.mini.js" type="text/javascript"></script>
<script>

    var compressedFile;

    $(function () {

        /* ajax 提交表单 */
        $('#avatarForm').on('submit', function (e) {
            e.preventDefault();
            var avatar = $('#avatar').val();
            $.ajax({
                url: '/profile/avatar/update',
                data: {avatar: avatar},
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
                        .appendTo($('.upload-label'))
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
                                        $('#avatar').val(url);
                                        $('#uploadSubmitBtn').remove();
                                        Confirm.show("操作提示", "上传完成！若要保存头像，请点击【保存头像】！");
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