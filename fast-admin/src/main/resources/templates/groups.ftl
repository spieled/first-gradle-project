[#ftl]
<!DOCTYPE html>
<html>
    <head>
        <title>快用工 | 权限管理 | 分组</title>
        [#include 'base/meta.html'/]
    </head>
    <body>
    <!-- PAGE -->
    <section id="page">
    [#include 'base/header.html'/]
    [#include 'base/nav.html'/]
    [#include 'base/content_header.html'/]

        <!-- BOX -->
        <div class="box border green">
            <div class="box-title">
                <h4><i class="fa fa-table"></i>分组列表</h4>
                <div class="tools hidden-xs">
                    <button id="createGroupBtn" class="btn btn-sm btn-danger">新建分组</button>
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
                        <th>分组名称</th>
                        <th>拥有权限</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    [#if groups?? && groups?size>0]
                        [#list groups as group]
                        <tr>
                            <td>${group_index+1}</td>
                            <td>${group.groupName}</td>
                            <td>
                                [#list group.authorities as auth]
                                    [#if auth_index == 0]
                                    ${auth}
                                    [#else]
                                    ,${auth}
                                    [/#if]
                                [/#list]
                            </td>
                            <td class="hidden-xs">
                                <button name="renameBtn" class="btn btn-sm  btn-success">更名</button>
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

    [#include 'base/content_footer.html'/]
    </section>

    [#-- 新增分组 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="createGroupModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="createGroupForm" class="form-horizontal" role="form" action="group/create">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="groupName" class="form-control" placeholder="分组名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组权限</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="3" name="authorities" placeholder="分组权限"></textarea>
                                </div>
                            </div>
                            <div class="form-group center">
                                [#--<a href="javascript:;" class="btn btn-success" onclick="ajaxCreateGroup()">创建</a>--]
                                <button type="submit" class="btn btn-success">创建</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#-- 更新分组权限 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="editGroupModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="editGroupForm" class="form-horizontal" role="form" action="group/authority/update">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="groupName" readonly="readonly" class="form-control" placeholder="分组名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组权限</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="3" name="authorities" placeholder="分组权限"></textarea>
                                </div>
                            </div>
                            <div class="form-group center">
                                <button type="submit" class="btn btn-success">修改</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    [#-- 分组更名 --]
    <div class="modal-open modal-soft">
        <div class="modal fade" id="renameGroupModal" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <form id="renameGroupForm" class="form-horizontal" role="form" action="group/rename">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组旧名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="groupName" class="form-control" readonly="readonly" placeholder="分组旧名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">分组新名称</label>
                                <div class="col-sm-9">
                                    <input type="text" name="newName" class="form-control" placeholder="分组新名称">
                                </div>
                            </div>
                            <div class="form-group center">
                                <button type="submit" class="btn btn-success">更名</button>
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
            $('#createGroupBtn').on('click', function () {
                $("#createGroupModal").modal('show');
            });
            /* ajax 提交分组表单 */
            $('#createGroupForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.href = '/groups';
                    }
                }
            });
            $('#renameGroupForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.href = '/groups';
                    }
                }
            });
            $('#editGroupForm').ajaxForm({
                success: function (response) {
                    if (response.success) {
                        window.location.href = '/groups';
                    }
                }
            });

            /* 更新分组权限 */
            $('button[name=editBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var groupName = $(tr).find('td:eq(1)').text();
                var authorities = $(tr).find('td:eq(2)').text();
                var oldAuth = '';
                $.each(authorities.split(","), function(index, item) {
                    if (index == 0) {
                        oldAuth += $.trim(item);
                    } else {
                        oldAuth += ',' + $.trim(item);
                    }
                }) ;
                $('#editGroupModal').modal('show');
                $('#editGroupForm').find('input[name=groupName]').val(groupName);
                $('#editGroupForm').find('textarea[name=authorities]').text(oldAuth);
            });

            /* 更名 */
            $('button[name=renameBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var groupName = $(tr).find('td:eq(1)').text();
                $('#renameGroupModal').modal('show');
                $('#renameGroupForm').find('input[name=groupName]').val(groupName);
            });

            /* 删除 */
            $('button[name=deleteBtn]').on('click', function() {
                var tr = $(this).parent().parent();
                var groupName = $(tr).find('td:eq(1)').text();
                Confirm.show('操作提示', '确认删除分组【'+groupName+'】吗？此操作不可恢复！', {
                    '确认删除': {
                        'primary': true,
                        'callback': function() {
                            $.ajax({
                                url: '/group/delete',
                                data: {groupName: groupName},
                                success: function(response) {
                                    if (response.success) {
                                        window.location.href = '/groups';
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