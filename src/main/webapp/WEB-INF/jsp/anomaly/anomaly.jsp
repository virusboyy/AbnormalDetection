<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统 —— 异常检测</title>
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

        //自动加载视频
        function myfun() {
            var src = "${url}";
            //alert(${ctx}+src);
            document.getElementById("video1").src=src;
            document.getElementById("video2").src=src;
        }
        window.onload = myfun;


        $(function () {
            setInterval(aa,1000);
            function aa() {
                $.post("${ctx }/log/refreshLogs", function (data) {
                    $("#log_info_tr").siblings().empty();
                    for (var i = 0; i < data.length; i++) {
                        $("#log_info_tr2").before("" +
                            "<tr align='center' class='main_trbg'>" +
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].event_type + "</td>" +
                            "<td>" + data[i].create_time + "</td>" +
                            "<td>" + data[i].log_document + "</td>" +
                            "</tr>");
                    }
                });
            }


            //日志信息
            var myVideo = document.getElementById("video1");
            $("#startBut").click(function () {
                    $.post("${ctx }/log/play", {currentTime: myVideo.currentTime, tag: "开始"});
                    myVideo.play();
                }
            );


            $("#pauseBut").click(
                function () {

                    $.post("${ctx }/log/play", {currentTime: myVideo.currentTime, tag: "暂停"});
                    myVideo.pause();
                }
            )
            $("#stopBut").click(
                function () {

                    $.post("${ctx }/log/play", {currentTime: myVideo.currentTime, tag: "停止"});
                    myVideo.load();
                }
            )

            $("#continueBut").click(
                function () {

                    $.post("${ctx }/log/play", {currentTime: myVideo.currentTime, tag: "继续"});
                    myVideo.play();
                }
            )

        })
    </script>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：异常检测 &gt; 异常检测主页面</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="80%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <th width="60%">监视窗口</th>
        <th width="40%">最近一次报警信息</th>
    </tr>
    <tr valign="top">
        <td width="60%">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr class="main_trbg_tit" align="center" valign="top">
                    <th width="100%" height="80">
                        <div id="video-box">
                            <%--muted="true" 设置默认为静音--%>
                            <video width="600" height="400" id="video1" muted="true">
                                <source src="http://www.w3school.com.cn/example/html5/mov_bbb.mp4" type="video/mp4"/>
                                <%--<source src="" type="video/mp4"/>--%>
                            </video>

                            <%--<video style="object-fit: fill;width: 200px;height: 50px;" controls>--%>
                            <%--<source src="">--%>
                            <%--</video>--%>
                        </div>
                    </th>
                </tr>
                <tr valign="bottom">
                    <th height="30">
                        <button type="button" id="startBut">开始</button>
                        <button type="button" id="pauseBut">暂停</button>
                        <button type="button" id="stopBut">停止</button>
                        <button type="button" id="continueBut">继续</button>
                    </th>
                </tr>

            </table>
        </td>
        <td width="40%">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr class="main_trbg_tit" align="center">
                    <th height="100%">
                        <%--loop="true" autoplay="autoplay"  循环自动播放--%>
                        <video id="video2" width="400" height="400" muted="true" loop="true" autoplay="autoplay">
                            <%--<source src="" type="video/mp4"/>--%>
                            <source src="video/RoadAccidents022_x264.mp4" type="video/mp4"/>

                        </video>
                    </th>
                </tr>

            </table>
        </td>
    </tr>
</table>

<table width="100%" height="10%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 数据展示区 -->
    <tr>
        <td height="5%" width="100%">日志信息</td>
    </tr>
    <tr valign="bottom">
        <td height="10%">
            <table id="log_info_table" width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr id="log_info_tr" class="main_trbg_tit" align="center">
                    <td>日志id</td>
                    <td>事件类型</td>
                    <td>创建时间</td>
                    <td>说明文档</td>
                </tr>
                <c:forEach items="${requestScope.logInfos}" var="logInfo" varStatus="stat">
                    <tr id="log_info_tr2" align="center" class="main_trbg">
                        <td>${logInfo.id }</td>
                        <td>${logInfo.event_type }</td>
                        <td>${logInfo.create_time }</td>
                        <td>${logInfo.log_document }</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>