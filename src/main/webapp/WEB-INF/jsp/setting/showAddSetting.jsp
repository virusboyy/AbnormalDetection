<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>行人异常行为检测系统——添加配置</title>
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
            $("#settingForm").submit(function () {
                var input_type = $("#input_type");
                var save_path = $("#save_path");
                var play_set = $("#play_set");

                var msg = "";
                if ($.trim(input_type.val()) == "") {
                    msg = "输入类型不能为空！";
                    input_type.focus();
                } else if ($.trim(save_path.val()) == "") {
                    msg = "存储路径不能为空！";
                    save_path.focus();
                } else if ($.trim(play_set.val()) == "") {
                    msg = "播放设置不能为空！";
                    play_set.focus();
                }
                if (msg != "") {
                    $.ligerDialog.error(msg);
                    return false;
                } else {
                    return true;
                }
                $("#settingForm").submit();
            });

            $("#query").click(function () {
                window.location = "${ctx}/setting/searchSysSettingsByKeys";
            })
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
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：系统配置 &gt; 添加配置</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form action="${ctx}/setting/addSetting" id="settingForm" enctype="multipart/form-data" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">
                            <table>
                                <tr>
                                    <td class="font3 fftd">输入类型：<input type="text" name="input_type" id="input_type"
                                                                       size="40"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">视频id：<input type="file" name="videofile" id="videofile"
                                                                       size="30"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">存储路径：<input type="text" name="save_path" id="save_path"
                                                                       size="40"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">所选权重：<input type="file" name="weightsFile" id="weightsFile"
                                                                       size="30"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">所选模型：<input type="file" name="modelfile" id="modelfile"
                                                                       size="30"/></td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">播放设置：<input type="text" name="play_set" id="play_set"
                                                                       size="40"/></td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                    <tr>
                        <td align="left" class="fftd">
                            <input type="submit" value="添加">&nbsp;&nbsp;
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