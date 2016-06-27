[#ftl]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 权限管理 | 用户</title>
        [#include 'base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include 'base/header.html'/]
    [#include 'base/nav.html'/]
        <div id="main-content">
            <div class="container">
                <div class="row">
                    <div id="content" class="col-lg-12">
                        <!-- PAGE HEADER-->
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="page-header">
                                    <!-- BREADCRUMBS -->
                                    <ul class="breadcrumb">
                                        <li>
                                            <i class="fa fa-home"></i>
                                            <a href="index.html">首页</a>
                                        </li>
                                        <li>
                                            <a href="#">权限管理</a>
                                        </li>
                                        <li>用户</li>
                                    </ul>
                                    <!-- /BREADCRUMBS -->
                                    <div class="clearfix">
                                        <h3 class="content-title pull-left">用户</h3>
                                    </div>
                                    <div class="description">
                                    </div>
                                </div>

                                <!-- BOX -->
                                <div class="box border green">
                                    <div class="box-title">
                                        <h4><i class="fa fa-table"></i>用户列表</h4>
                                        <div class="tools hidden-xs">
                                            <button id="createUserBtn" class="btn btn-sm btn-danger">新建用户</button>
                                            <a href="javascript:;" class="reload">
                                                <i class="fa fa-refresh"></i>
                                            </a>
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
                                                <th>用户名</th>
                                                <th>拥有权限</th>
                                                <th>启用状态</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            [#if users?? && users?size>0]
                                                [#list users as user]
                                                <tr>
                                                    <td>${user_index+1}</td>
                                                    <td>${user.username}</td>
                                                    <td>
                                                        [#list user.authorities as auth]
                                                            [#if auth_index == 0]
                                                            ${auth}
                                                            [#else]
                                                                ,${auth}
                                                            [/#if]
                                                        [/#list]
                                                    </td>
                                                    <td>
                                                        [#if user.enabled]
                                                            启用
                                                        [#else]
                                                            禁用
                                                        [/#if]
                                                    </td>
                                                    <td class="hidden-xs">
                                                        <button name="editBtn" class="btn btn-sm  btn-grey">编辑</button>
                                                        <button name="deleteBtn" class="btn btn-sm btn-danger">删除</button>
                                                    </td>
                                                </tr>
                                                [/#list]
                                            [#else]
                                            <tr>
                                                <td colspan="4">没有数据！</td>
                                            </tr>
                                            [/#if]
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- /BOX -->

                            </div>
                        </div>
                        <!-- /PAGE HEADER -->
                    </div><!-- /CONTENT-->
                </div>
            </div>
        </div>
    </section>

    [#-- 新增用户 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="createUserModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="createUserForm" class="form-horizontal" role="form" action="user/create">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="username" class="form-control" placeholder="用户名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-9">
                                    <input type="text" name="password" class="form-control" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">启用状态</label>
                                <div class="col-sm-9 checkbox-inline">
                                    <label><input type="radio" id="enable_true" name="enabled" value="true">
                                    启用</label>
                                    <label><input type="radio" id="enable_false" name="enabled" value="false">
                                    禁用</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所属分组</label>
                                <div class="col-sm-9 radio-inline">
                                    [#if groups?? && groups?size>0]
                                        [#list groups as group]
                                        <label><input type="checkbox" name="group" value="${group}">
                                        ${group}</label>
                                        [/#list]
                                    [/#if]
                                </div>
                            </div>
                            <div class="form-group center">
                                <button type="submit" class="btn btn-success">创建</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#include 'base/footer.html'/]
    <script>

        $(function () {
            $('#createUserBtn').on('click', function () {
                $("#createUserModal").modal('show');
            });
            /* ajax 提交用户表单 */
            $('#createUserForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.href = '/users';
                    }
                }
            });

            /* 删除 */
            $('button[name=deleteBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var userName = $(tr).find('td:eq(1)').text();
                Confirm.show('操作提示', '确认删除用户【'+userName+'】吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/user/delete',
                                data: {userName: userName},
                                success: function(response) {
                                    if (response.success) {
                                        window.location.href = '/users';
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