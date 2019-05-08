<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统——修改日志</title>
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
            /** 配置表单提交 */
            $("#logInfoForm").submit(function () {
                var event_type = $("#event_type");
                var log_document = $("#log_document");
                var msg = "";
                if ($.trim(event_type.val()) == "") {
                    msg = "输入类型不能为空！";
                    event_type.focus();
                } else if ($.trim(log_document.val()) == "") {
                    msg = "存储路径不能为空！";
                    log_document.focus();
                }
                if (msg != "") {
                    $.ligerDialog.error(msg);
                    return false;
                } else {
                    return true;
                }
                $("#logInfoForm").submit();
            });

            $("#query").click(function () {
                window.location = "${ctx}/log/selectLogInfoByKeys";
            });
        });


    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：日志管理 &gt; 修改日志</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form action="${ctx}/log/updateLogInfo" id="logInfoForm" enctype="multipart/form-data" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <input type="hidden" name="id" value="${logInfo.id }">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">
                            <table>
                                <tr>
                                    <td class="font3 fftd">事件类型：<input type="text" name="event_type" id="event_type"
                                                                       size="30" value="${logInfo.event_type }"/></td>
                                </tr>
                                <%--value='<fmt:formatDate value="${logInfo.create_time }" pattern="yyyy-MM-dd HH:mm:ss"/>'--%>
                                <%--<tr>--%>
                                    <%--<td class="font3 fftd">创建时间：<input type="text" name="create_time" id="create_time"--%>
                                                                       <%--value='<fmt:formatDate value="${logInfo.create_time }" type="both"/>'/>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                                <tr>
                                    <td class="font3 fftd">说明文档：<br/><textarea name="log_document" cols="88" rows="11"
                                                                               id="log_document">${logInfo.log_document }</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">用户信息：<input type="text" name="username" id="username"
                                                                       size="30" value="${logInfo.username }"/></td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                    <tr>
                        <td align="left" class="fftd">
                            <input type="submit" value="修改">&nbsp;&nbsp;
                            <input type="button" id="query" value="取消">
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>