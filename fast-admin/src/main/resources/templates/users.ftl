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
                                            <button id="createGroupBtn" class="btn btn-sm btn-danger">新建用户</button>
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
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr class="gradeX">
                                                <td>1</td>
                                                <td>用户一</td>
                                                <td>READ_ALL,ADD_ALL</td>
                                                <td class="hidden-xs">
                                                    <button class="btn btn-sm  btn-success">更名</button>
                                                    <button class="btn btn-sm btn-danger">删除</button>
                                                </td>
                                            </tr>
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
    [#include 'base/footer.html'/]
    </body>
</html>