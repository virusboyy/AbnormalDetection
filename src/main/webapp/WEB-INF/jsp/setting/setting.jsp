<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统 —— 系统配置</title>
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
            /** 获取上一次选中的配置数据 */
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


            /** 删除配置绑定点击事件 */
            $("#delete").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1) {
                    $.ligerDialog.error("请选择一个需要删除的配置！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?", "删除配置", function (r) {
                        if (r) {
                            // alert("删除："+ids.get());
                            // 发送请求
                            window.location = "${ctx }/setting/removeSetting?ids=" + ids.get();
                        }
                    });
                }
            })
            /** 配置绑定点击事件 */
            $("#configure").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1 && checkedBoxs.length > 1) {
                    $.ligerDialog.error("请选择一个需要配置的选项！");
                } else {
                    /** 得到用户选中的所有的需要配置的ids */
                    var id = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要设置吗?", "选定配置", function (r) {
                        if (r) {
                            window.location = "${ctx }/setting/getSetting?id=" + id.get();
                        }
                    });
                }
            })

            /** 默认配置绑定点击事件 */
            $("#def_configure").click(function () {

                $.ligerDialog.confirm("确认要设置成默认配置吗?", "设置默认配置", function (r) {
                    if (r) {
                        window.location = "${ctx }/setting/defaultSetting?id=1";
                    }
                });

            })


            <%--var pageIndex = 0;--%>
            <%--$("#search_sysSetting").click(function () {--%>
                <%--var keys = document.getElementById('keys').value;--%>
                <%--alert(keys);--%>
                <%--$.post("${ctx }/setting/searchSysSettingsByKeys", {--%>
                    <%--keys: keys,--%>
                    <%--pageIndex: pageIndex--%>
                <%--}, function (dataType) {--%>
                    <%--alert(dataType.sysSettings[0].input_type);--%>
                    <%--alert(dataType.pageModel.recordCount);--%>
                    <%--$("#sysSetting_tr1").siblings().empty();--%>
                    <%--for (var i=0; i < dataType.sysSettings.length; i++) {--%>
                        <%--$("#data_").before(""+--%>
                            <%--"<tr id='data_' align='center' class='main_trbg' onMouseOver='move(this);' onMouseOut='out(this);'>" +--%>
                            <%--"<td>" + "<input type='checkbox' id='box_${stat.index}' value='${setting.id}'>" + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].input_type + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].video + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].save_path + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].model + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].play_set + "</td>" +--%>
                            <%--"<td>" + dataType.sysSettings[i].username + "</td>" +--%>
                            <%--"<td align='center' width='40px;'>"+"<a href='${ctx}/setting/updateSetting?flag=1&id='"+dataType.sysSettings[i].id+"'>"+"<img title='修改' src='${ctx}/images/update.gif'/>"+"</a>"+"</td>" +--%>
                            <%--"</tr>"--%>
                        <%--)--%>
                    <%--}--%>
                <%--})--%>

                <%--&lt;%&ndash;$.post("${ctx }/setting/searchSysSettingsByKeys", {&ndash;%&gt;--%>
                    <%--&lt;%&ndash;keys: keys,&ndash;%&gt;--%>
                    <%--&lt;%&ndash;pageIndex: pageIndex&ndash;%&gt;--%>
                <%--&lt;%&ndash;}, function (dataType) {&ndash;%&gt;--%>
                    <%--&lt;%&ndash;alert(dataType.sysSettings[0].input_type);&ndash;%&gt;--%>
                    <%--&lt;%&ndash;alert(dataType.pageModel.recordCount);&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$("#sysSetting_tr1").siblings().empty();&ndash;%&gt;--%>
                    <%--&lt;%&ndash;$("#paper_tr").before(&ndash;%&gt;--%>
                        <%--&lt;%&ndash;"<tr valign='top'>" +&ndash;%&gt;--%>
                        <%--&lt;%&ndash;"<td align='center' class='font3'>" + "<fkjava:pager pageIndex='"+ dataType.pageModel.pageIndex +"' pageSize='"+dataType.pageModel.pageSize+"' recordCount='"+dataType.pageModel.recordCount+"' style= 'digg' submitUrl='${ctx}/setting/searchSysSettingsByKeys?pageIndex={0}'/>"+ "</td>" +&ndash;%&gt;--%>
                        <%--&lt;%&ndash;"</tr>"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;);&ndash;%&gt;--%>
                <%--&lt;%&ndash;})&ndash;%&gt;--%>
            <%--})--%>
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
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：系统配置 &gt; 用户配置</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form name="deptform" method="post" id="deptform" action="${ctx }/setting/searchSysSettingsByKeys">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    搜索条件：<input id="keys" type="text" name="keys" placeholder="用户名、输入类型">
                                    <%--<input id="search_sysSetting" type="button" value="搜索"/>--%>
                                    <input type="submit" value="搜索"/>
                                    <input id="delete" type="button" value="删除"/>
                                    <input id="configure" type="button" value="配置"/>
                                    <input id="def_configure" type="button" value="默认配置"/>
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
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr id="sysSetting_tr1" class="main_trbg_tit" align="center">
                    <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                    <td>输入类型</td>
                    <td>视频id</td>
                    <td>存储路径</td>
                    <td>模型路径</td>
                    <td>播放设置</td>
                    <td>用户信息</td>
                    <td align="center">操作</td>
                </tr>
                <c:forEach items="${requestScope.sysSettings}" var="setting" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);"
                        onMouseOut="out(this);">
                        <td><input type="checkbox" id="box_${stat.index}" value="${setting.id}"></td>
                        <td>${setting.input_type }</td>
                        <td>${setting.video }</td>
                        <td>${setting.save_path }</td>
                        <td>${setting.model }</td>
                        <td>${setting.play_set }</td>
                        <td>${setting.username}</td>
                        <td align="center" width="40px;"><a id="settingAID" href="${ctx}/setting/updateSetting?flag=1&id=${setting.id}">
                            <img title="修改" src="${ctx}/images/update.gif"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <!-- 分页标签 -->
    <tr id="paper_tr" valign="top">
        <td align="center" class="font3">
            <fkjava:pager
                    pageIndex="${requestScope.pageModel.pageIndex}"
                    pageSize="${requestScope.pageModel.pageSize}"
                    recordCount="${requestScope.pageModel.recordCount}"
                    style="digg"
                    submitUrl="${ctx }/setting/searchSysSettingsByKeys?pageIndex={0}"/>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>