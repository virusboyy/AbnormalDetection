<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统——日志信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        $(function () {
            /** 获取上一次选中的数据 */
            var boxs = $("input[type='checkbox'][id^='box_']");

            /** 给全选按钮绑定点击事件  */
            $("#checkAll").click(function () {
                // this是checkAll  this.checked是true
                // 所有数据行的选中状态与全选的状态一致
                boxs.attr("checked", this.checked);
            })

            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function () {
                $(this).css("backgroundColor", "#eeccff");
            }, function () {
                $(this).css("backgroundColor", "#ffffff");
            })


            /** 删除绑定点击事件 */
            $("#delete").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1) {
                    $.ligerDialog.error("请选择一个需要删除的日志！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?", "删除日志", function (r) {
                        if (r) {
                            // alert("删除："+ids.get());
                            // 发送请求
                            window.location = "${ctx }/log/removeLogInfo?ids=" + ids.get();
                        }
                    });
                }
            })
        })
    </script>

</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：日志管理 &gt; 日志管理主界面</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="10%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form name="deptform" method="post" id="deptform" action="${ctx}/log/selectLogInfoByKeys">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        用户名：<input type="text" name="username">
                                        事件类型：<input type="text" name="event_type">
                                        <input type="submit" value="搜索"/>
                                        <input id="delete" type="button" value="删除"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <!-- 数据展示区 -->
    <tr>
        <td height="5%" width="100%">日志信息</td>
    </tr>
    <tr valign="bottom">
        <td height="10%">
            <table width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">
                    <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                    <td>日志id</td>
                    <td>事件类型</td>
                    <td>创建时间</td>
                    <td>说明文档</td>
                    <td>用户信息</td>
                    <td align="center">操作</td>
                </tr>
                <c:forEach items="${requestScope.logInfos}" var="logInfo" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);"
                        onMouseOut="out(this);">
                        <td><input type="checkbox" id="box_${stat.index}" value="${logInfo.id}"></td>
                        <td>${logInfo.id }</td>
                        <td>${logInfo.event_type }</td>
                        <td>${logInfo.create_time }</td>
                        <td>${logInfo.log_document }</td>
                        <td>${logInfo.username}</td>
                        <td align="center" width="40px;"><a href="${ctx}/log/updateLogInfo?flag=1&id=${logInfo.id}">
                            <img title="修改" src="${ctx}/images/update.gif"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <!-- 分页标签 -->
    <tr valign="bottom">
        <td align="center" class="font3" height="5%">
            <fkjava:pager
                    pageIndex="${requestScope.pageModel.pageIndex}"
                    pageSize="${requestScope.pageModel.pageSize}"
                    recordCount="${requestScope.pageModel.recordCount}"
                    style="digg"
                    submitUrl="${ctx}/log/selectLogInfoByKeys?pageIndex={0}"/>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>