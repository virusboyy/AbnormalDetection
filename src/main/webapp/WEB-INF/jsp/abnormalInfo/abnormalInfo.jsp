<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统——异常信息检索</title>
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
    <script src="${ctx}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
                    $.ligerDialog.error("请选择一个需要删除的异常信息！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?", "删除异常信息", function (r) {
                        if (r) {
                            // alert("删除："+ids.get());
                            // 发送请求
                            window.location = "${ctx }/abnormalInfo/removeAbnormalInfo?ids=" + ids.get();
                        }
                    });
                }
            })

            $("#preview_abnormal").click(function() {
                var filename12 = document.getElementById("preview_abnormal").value;
                var src = "/video/"+filename12;
                // alert(src);
                document.getElementById("video_abnomal").src=src;
            });
        })
    </script>

</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：异常信息检索 &gt; 异常信息检索主界面</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td width="60%">
            <table width="100%" height="10%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
                <!-- 查询区  -->
                <tr valign="top">
                    <td height="30">
                        <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                            <tr>
                                <td class="fftd">
                                    <form name="deptform" method="post" id="deptform"
                                          action="${ctx}/abnormalInfo/selectAbnormalInfoByKeys">
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td class="font3">
                                                    事件类型：<input type="text" name="event_type" id="event_type">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="font3">
                                                    时间：<input id="start" name="start" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                                    到<input id="end" name="end" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
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
                    <td height="5%" width="100%">异常信息列表</td>
                </tr>
                <tr valign="bottom">
                    <td height="10%">
                        <table width="100%" border="1" cellpadding="5" cellspacing="0"
                               style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                            <tr class="main_trbg_tit" align="center">
                                <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                                <td>异常id</td>
                                <td>事件类型</td>
                                <td>创建时间</td>
                                <td>说明文档</td>
                                <td>开始时间</td>
                                <td>结束时间</td>
                                <td>异常视频</td>
                                <%--<td>视频路径</td>--%>
                                <td>用户</td>
                                <td align="center">操作</td>
                            </tr>
                            <c:forEach items="${requestScope.abnormalInfos}" var="abnormalInfo" varStatus="stat">
                                <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);"
                                    onMouseOut="out(this);">
                                    <td><input type="checkbox" id="box_${stat.index}" value="${abnormalInfo.id}"></td>
                                    <td>${abnormalInfo.id }</td>
                                    <td>${abnormalInfo.event_type }</td>
                                    <td>${abnormalInfo.create_time }</td>
                                    <td>${abnormalInfo.anomaly_document }</td>
                                    <td>${abnormalInfo.start_time }微秒</td>
                                    <td>${abnormalInfo.end_time }微秒</td>
                                    <td><input id="preview_abnormal" type="button" value="${abnormalInfo.video_name }"/></td>
                                    <%--<td>${abnormalInfo.video_path }</td>--%>
                                    <td>${abnormalInfo.username}</td>
                                    <td align="center" width="40px;"><a
                                            href="${ctx}/abnormalInfo/updateAbnormalInfo?flag=1&id=${abnormalInfo.id}">
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
                                submitUrl="${ctx}/abnormalInfo/selectAbnormalInfoByKeys?pageIndex={0}"/>
                    </td>
                </tr>
            </table>
        </td>

        <td width="40%">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr valign="top">
                    <th width="40%">预览异常信息</th>
                </tr>
                <tr class="main_trbg_tit" align="center">
                    <th height="100%">
                        <%--loop="true" autoplay="autoplay"  循环自动播放--%>
                        <video id="video_abnomal" width="400" height="400" muted="true" loop="true" autoplay="autoplay">
                            <source src="/i/movie.ogg" type="video/ogg"/>
                            <%--<source src="http://www.w3school.com.cn/example/html5/mov_bbb.mp4" type="video/mp4"/>--%>
                            <source src="" type="video/mp4"/>
                        </video>
                    </th>
                </tr>

            </table>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>