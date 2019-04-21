package com.pxyph.controller;

import com.pxyph.model.LogInfo;
import com.pxyph.model.User;
import com.pxyph.service.ADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.USER_SESSION;

/**
 * 系统配置控制器
 */
@Controller
public class AnomalyDetectionController {
    @Autowired
    private ADService adService;

    @RequestMapping(value = "/anomaly/anomalyDetection")
    public String selectAllLogInfo(Model model,HttpSession session) {
        User user = (User) session.getAttribute(USER_SESSION);

        /**
         * 异常检测开始接口
         */


        /** 查询配置信息     */
        List<LogInfo> logInfos = adService.findAllLogInfoByUsername(user.getUsername());
        model.addAttribute("logInfos", logInfos);
        return "anomaly/anomaly";

    }

    @RequestMapping(value="/log/play")
    @ResponseBody
    public List<LogInfo> play(
            String currentTime,
            String tag,
            HttpSession session
            ){
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

        logInfo.setLog_document("用户"+username+"在视频播放到"+currentTime+"秒时点击了"+tag+"按钮");
        //添加日志信息
        adService.addLogInfo(logInfo);

        /** 查询用户信息     */
        List<LogInfo> logLnfos = adService.findAllLogInfoByUsername(username);
        return logLnfos;
    }

}
