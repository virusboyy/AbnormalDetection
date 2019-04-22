package com.pxyph.controller;

import com.pxyph.model.AbnormalInfo;
import com.pxyph.model.LogInfo;
import com.pxyph.model.SysSetting;
import com.pxyph.model.User;
import com.pxyph.service.ADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.SETTINGID;
import static com.pxyph.util.common.ADConstants.USER_SESSION;
import static com.pxyph.util.common.ADConstants.VIDEO_REFERENCE;

/**
 * 系统配置控制器
 */
@Controller
public class AnomalyDetectionController {
    @Autowired
    private ADService adService;

    @RequestMapping(value = "/anomaly/anomalyDetection")
    public String selectAllLogInfo(Model model, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(USER_SESSION);

        /**
         * 异常检测开始接口
         */
        //从cookie中取出设置id
        //Cookie[] cookies = request.getCookies();
        //for (Cookie cookie:cookies){
        //    //if (cookie.getName().equals(SYSSETTING)){
        //    //    id = Integer.parseInt(cookie.getValue());
        //    //}
        //    if (URLDecoder.decode(cookie.getName()).equals(SETTINGID)){
        //        id = Integer.parseInt(cookie.getValue());
        //    }
        //}
        //int id = 1;//系统默认，当session中没有值，即没有进行设置时，自动选用系统设置

        //从session中取出id
        int id = Integer.parseInt((String) session.getAttribute(SETTINGID));
        SysSetting sysSetting = adService.findSysSettingById(id);
        //String save_path = sysSetting.getSave_path();
        String save_path = VIDEO_REFERENCE;
        String video = sysSetting.getVideo();
        String url = save_path + "/" + video;
        System.out.println(url);

        /** 查询配置信息     */
        List<LogInfo> logInfos = adService.findAllLogInfoByUsername(user.getUsername());
        model.addAttribute("url", url);
        model.addAttribute("logInfos", logInfos);
        return "anomaly/anomaly";

    }


    @RequestMapping(value = "/log/play")
    @ResponseBody
    public List<LogInfo> play(
            String currentTime,
            String tag,
            HttpSession session
    ) {
        System.out.println("-------------");
        System.out.println(currentTime);
        System.out.println(tag);
        System.out.println("-------------");
        LogInfo logInfo = new LogInfo();
        logInfo.setEvent_type(tag);
        System.out.println(new Date());
        logInfo.setCreate_time(new Date());

        //添加用户信息
        User user = (User) session.getAttribute(USER_SESSION);
        String username = user.getUsername();
        logInfo.setUsername(username);

        logInfo.setLog_document("用户" + username + "在视频播放到" + currentTime + "秒时点击了" + tag + "按钮");
        //添加日志信息
        adService.addLogInfo(logInfo);

        /** 查询用户信息     */
        List<LogInfo> logLnfos = adService.findAllLogInfoByUsername(username);
        return logLnfos;
    }

    /**
     * 调用机器学习算法进行异常行为检测，并返回异常信息对象
     * @param video_name
     * @return 异常信息对象
     */
    public AbnormalInfo anomalyDetect(String video_name){
        /**
         * 机器学习算法，进行异常行为检测
         */

        return null;
    }

}
