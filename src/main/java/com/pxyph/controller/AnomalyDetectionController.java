package com.pxyph.controller;

import com.pxyph.model.*;
import com.pxyph.service.ADService;
import com.pxyph.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.*;

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

        //默认设置
        String ids = DEFAULTSETTINGID;

        String video = null;

        //从session中取出id
        if ((String) session.getAttribute(SETTINGID)!=null){
            ids = (String) session.getAttribute(SETTINGID);
            int id = Integer.parseInt(ids);
            SysSetting sysSetting = adService.findSysSettingById(id);
            video = sysSetting.getVideo();
        }else {
            DefaultSetting defaultSetting = adService.findDefaultSettingById(Integer.parseInt(ids));
            String video_id = defaultSetting.getVideo_id();
            VideoInfo videoInfo = adService.findVideoInfoByVideoId(video_id);
            video = videoInfo.getFile_name();
        }
        //String save_path = sysSetting.getSave_patrth();
        String save_path = VIDEO_REFERENCE;
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
    public String anomalyDetection(
            String currentTime,
            String tag,
            HttpSession session
    ) throws Exception {
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
        //从session中取出id
        String ids = null;
        SysSetting sysSetting = new SysSetting();
        if ((String) session.getAttribute(SETTINGID)!=null){
            ids = (String) session.getAttribute(SETTINGID);
            sysSetting = adService.findSysSettingById(Integer.parseInt(ids));
        }else {
            ids = DEFAULTSETTINGID;
            DefaultSetting defaultSetting = adService.findDefaultSettingById(Integer.parseInt(ids));
            sysSetting.setInput_type(defaultSetting.getInput_type());
            String video_id = defaultSetting.getVideo_id();
            sysSetting.setVideo_id(video_id);
            VideoInfo videoInfo = adService.findVideoInfoByVideoId(video_id);
            sysSetting.setVideo(videoInfo.getFile_name());
            sysSetting.setSave_path(defaultSetting.getSave_path());
            sysSetting.setModel(defaultSetting.getModel());
            sysSetting.setPlay_set(defaultSetting.getPlay_set());
            sysSetting.setUsername(username);
        }

        logInfo.setLog_document("用户" + username + "在视频" + sysSetting.getVideo() + "播放到" + currentTime + "秒时点击了" + tag + "按钮");
        //添加日志信息
        adService.addLogInfo(logInfo);

        //进行异常检测
        abnormalDetection(logInfo, sysSetting, tag);

        return "ok";
    }



    /**
     * 调用机器学习算法进行异常行为检测，并返回异常信息对象
     *
     * @param video_name
     * @return 异常信息对象
     */
    public AbnormalInfo anomalyDetectionModel(String video_name) {
        /**
         * 机器学习算法，进行异常行为检测
         */

        return null;
    }

    private void addStorageManager(SysSetting sysSetting, VideoInfo video) {
        StorageManager storageInfoByVideoId = adService.findStorageInfoByVideoId(video.getVideo_id());
        if (storageInfoByVideoId==null) {
            StorageManager storageManager = new StorageManager();
            storageManager.setVideo_id(video.getVideo_id());
            storageManager.setFile_name(video.getFile_name());
            storageManager.setFile_size(video.getFile_size());
            storageManager.setVideo_width(video.getVideo_width());
            storageManager.setVideo_height(video.getVideo_height());
            storageManager.setCreate_time(new Date());
            storageManager.setFrame_num(video.getFrame_num());
            storageManager.setFps(video.getFps());
            storageManager.setUsername(sysSetting.getUsername());
            adService.addStorageInfo(storageManager);
        }
    }

    private void addAbnormalInfo(SysSetting sysSetting, VideoInfo video, int start_time, int end_time) {
        AbnormalInfo abnormalInfo = new AbnormalInfo();
        abnormalInfo.setEvent_type(video.getEvent_type());
        abnormalInfo.setCreate_time(new Date());
        abnormalInfo.setAnomaly_document("视频" + video.getFile_name() + "在" + start_time + "微秒到" + end_time + "微秒出现了" + video.getEvent_type());
        abnormalInfo.setStart_time(new BigInteger(String.valueOf(start_time)));
        abnormalInfo.setEnd_time(new BigInteger(String.valueOf(end_time)));
        abnormalInfo.setVideo_id(video.getVideo_id());
        abnormalInfo.setVideo_name(video.getFile_name());
        abnormalInfo.setVideo_path(sysSetting.getSave_path());
        abnormalInfo.setUsername(sysSetting.getUsername());
        adService.addAbnormalInfo(abnormalInfo);
    }


    //刷新页面
    @RequestMapping(value = "/log/refreshLogs")
    @ResponseBody
    public List<LogInfo> getLog(
            PageModel pageModel,
            @ModelAttribute LogInfo logInfo,
            HttpSession session
    ) {
        //List<LogInfo> logInfos = adService.findLogInfoByKeys(logInfo, pageModel);
        User user = (User) session.getAttribute(USER_SESSION);
        List<LogInfo> logInfos = adService.findAllLogInfoByUsername(user.getUsername());
        return logInfos;
    }

    //刷新页面
    @RequestMapping(value = "/abnormal/video2")
    @ResponseBody
    public List<AbnormalInfo> getAbnormalVideo2(
            PageModel pageModel,
            @ModelAttribute AbnormalInfo abnormalInfo
    ) {
        List<AbnormalInfo> abnormalInfos = adService.findAbnormalInfoByKeys(abnormalInfo, pageModel);
        abnormalInfos.get(0).setVideo_path(abnormalInfos.get(0).getVideo_path()+"/"+abnormalInfos.get(0).getVideo_name());
        return abnormalInfos;
    }


    private void abnormalDetection(LogInfo logInfo, SysSetting sysSetting, String tag) throws InterruptedException {
        if (tag.equals("开始") || tag.equals("继续")) {
            String video_id = sysSetting.getVideo_id();
            VideoInfo videoInfo = adService.findVideoInfoByVideoId(video_id);
            int start_time = videoInfo.getStart_time();
            Thread.sleep(start_time / 1000);
            logInfo.setEvent_type(videoInfo.getEvent_type());
            logInfo.setCreate_time(new Date());
            logInfo.setLog_document("视频" + videoInfo.getFile_name() + "在" + start_time + "微秒中处出现了" + videoInfo.getEvent_type());
            adService.addLogInfo(logInfo);

            int end_time = videoInfo.getEnd_time();
            Thread.sleep((end_time - start_time) / 1000);
            logInfo.setCreate_time(new Date());
            logInfo.setLog_document("视频" + videoInfo.getFile_name() + "在" + end_time + "微秒中处结束了" + videoInfo.getEvent_type());
            adService.addLogInfo(logInfo);
            //VideoInfo video = adService.findVideoInfoByFileName(sysSetting.getVideo());

            //添加异常信息
            addAbnormalInfo(sysSetting, videoInfo, start_time, end_time);

            //添加存储信息
            addStorageManager(sysSetting, videoInfo);
        }
    }


}
