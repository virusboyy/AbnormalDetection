package com.pxyph.controller;

import com.pxyph.model.DataType;
import com.pxyph.model.SysSetting;
import com.pxyph.model.User;
import com.pxyph.model.VideoInfo;
import com.pxyph.service.ADService;
import com.pxyph.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.pxyph.util.common.ADConstants.*;

/**
 * 系统配置控制器
 */
@Controller
public class SysSettingController {
    @Autowired
    private ADService adService;

    /**
     * 处理删除配置请求
     *
     * @param ids 需要删除的id字符串
     * @param mv
     */
    @RequestMapping(value = "/setting/removeSetting")
    public ModelAndView removeSysSetting(String ids, ModelAndView mv) {
        try {
            // 分解id字符串
            String[] idArray = ids.split(",");
            for (String id : idArray) {

                //SysSetting sysSetting = adService.findSysSettingById(Integer.parseInt(id));
                //File[] videoFiles = new File(VIDEOPATH).listFiles();
                //for (File file : videoFiles) {
                //    System.out.println(file.getAbsolutePath());
                //    System.out.println(file.getName());
                //    if (file.getName().equals(sysSetting.getVideo())) {
                //        file.getAbsoluteFile().delete();
                //    }
                //}
                //File[] modelFiles = new File(MODELPATH).listFiles();
                //for (File file : modelFiles) {
                //    if (file.getName().equals(sysSetting.getModel())) {
                //        file.getAbsoluteFile().delete();
                //    }
                //}

                // 根据id删除配置
                adService.removeSysSettingById(Integer.parseInt(id));

            }
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        } catch (Exception e) {
            //出现异常直接抛出
            e.printStackTrace();
            System.out.println("删除出错");
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/404");
        }
        // 返回ModelAndView
        return mv;
    }

    /**
     * 处理添加请求
     *
     * @param flag       标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param sysSetting 要添加的配置信息对象
     * @param mv
     */
    @RequestMapping(value = "/setting/addSetting")
    public ModelAndView addSysSetting(
            String flag,
            @ModelAttribute SysSetting sysSetting,
            ModelAndView mv,
            HttpSession session) throws Exception {
        if (flag.equals("1")) {
            // 设置跳转到添加页面
            mv.setViewName("setting/showAddSetting");
        } else {
            //上传路径
            //String videoPath = session.getServletContext().getRealPath("/upload/");
            String videoPath = VIDEOPATH;
            //String modelPath = session.getServletContext().getRealPath("/model/");
            String modelPath = MODELPATH;
            System.out.println(videoPath);
            //上传的视频文件名、模型文件名
            String videoName = sysSetting.getVideofile().getOriginalFilename();
            VideoInfo videoInfo = adService.findVideoInfoByFileName(videoName);
            sysSetting.setVideo_id(videoInfo.getVideo_id());
            String weightsName = sysSetting.getWeightsFile().getOriginalFilename();
            String modelName = sysSetting.getModelfile().getOriginalFilename();

            //将上传路径保存到一个目标文件中
            //sysSetting.getVideofile().transferTo(new File(videoPath + File.separator + videoName));
            //sysSetting.getModelfile().transferTo(new File(modelPath + File.separator + modelName));

            //设置video_id和model
            sysSetting.setVideo(videoName);
            sysSetting.setWeights(weightsName);
            sysSetting.setModel(modelName);

            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            sysSetting.setUsername(user.getUsername());

            // 执行添加操作
            adService.addSysSetting(sysSetting);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        }
        // 返回
        return mv;
    }


    /**
     * 处理修改配置信息请求
     *
     * @param flag       标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param sysSetting 要修改配置信息的对象
     * @param mv
     * @param session
     */
    @RequestMapping(value = "/setting/updateSetting")
    public ModelAndView updateSysSetting(
            String flag,
            @ModelAttribute SysSetting sysSetting,
            ModelAndView mv,
            HttpSession session) throws Exception {
        if (flag.equals("1")) {
            // 根据id查询配置
            SysSetting target = adService.findSysSettingById(sysSetting.getId());
            // 设置Model数据
            mv.addObject("sysSetting", target);
            // 设置跳转到修改页面
            mv.setViewName("setting/showUpdateSetting");
        } else {
            //上传路径
            //String videoPath = session.getServletContext().getRealPath("/upload/");
            String videoPath = VIDEOPATH;
            //String modelPath = session.getServletContext().getRealPath("/model/");
            String modelPath = MODELPATH;
            System.out.println(videoPath);
            //上传的视频文件名、模型文件名
            String videoName = sysSetting.getVideofile().getOriginalFilename();
            String modelName = sysSetting.getModelfile().getOriginalFilename();


            //将上传路径保存到一个目标文件中
            sysSetting.getVideofile().transferTo(new File(videoPath + File.separator + videoName));
            sysSetting.getModelfile().transferTo(new File(modelPath + File.separator + modelName));

            //设置video_id和model
            sysSetting.setVideo(videoName);
            sysSetting.setModel(modelName);

            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            sysSetting.setUsername(user.getUsername());

            // 执行修改操作
            adService.modifySysSetting(sysSetting);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        }
        // 返回
        return mv;
    }


    /**
     * 设定系统配置
     *
     * @param id       需要的id字符串
     * @param mv
     * @param
     */
    @RequestMapping(value = "/setting/getSetting")
    public ModelAndView setSysSetting(
            String id,
            ModelAndView mv,
            HttpSession session) throws Exception {
        //创建Cookie对象
        //Cookie cookie = new Cookie(SETTINGID, id.toString());
        //Cookie cookie = new Cookie(URLEncoder.encode(SETTINGID,CharEncoding.UTF_8), URLEncoder.encode(id.toString(),CharEncoding.UTF_8));
        ////设定cookie的最大有效时间
        //cookie.setMaxAge(60 * 60 * 24);
        ////cookie.setHttpOnly(true);
        ////将cookie信息发送给浏览器
        //response.addCookie(cookie);

        session.setAttribute(SETTINGID,id);
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        // 返回ModelAndView
        return mv;
    }


    @RequestMapping(value = "/setting/searchSysSettingsByUserNameAndInputType")
    @ResponseBody
    public DataType getSysSettingsByKeys(
            Model model,
            String keys,
            Integer pageIndex,
            @ModelAttribute SysSetting sysSetting) {
        String[] keyList = keys.split("、");
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<SysSetting> sysSettings = new ArrayList<>();

        DataType dataType = new DataType();

        if (keyList.length == 1) {
            sysSetting.setInput_type(keyList[0]);
            sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
            if (sysSettings.size() > 0) {
                model.addAttribute("sysSettings", sysSettings);
                //params.put("sysSettingList",sysSettings);
                dataType.setSysSettings(sysSettings);
            } else {
                sysSetting.setInput_type(null);
                sysSetting.setUsername(keyList[0]);
                sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
                model.addAttribute("sysSettings", sysSettings);
                //params.put("sysSettings",sysSettings);
                dataType.setSysSettings(sysSettings);
            }
        }

        if (keyList.length == 2) {
            sysSetting.setUsername(keyList[0]);
            sysSetting.setInput_type(keyList[1]);
            sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
            //params.put("sysSettings",sysSettings);
            dataType.setSysSettings(sysSettings);
        }

        model.addAttribute("pageModel", pageModel);
        //params.put("pageModel",pageModel);
        dataType.setPageModel(pageModel);

        return dataType;
    }

    @RequestMapping(value = "/setting/searchSysSettingsByKeys")
    public String getSysSettingsByKeys(
            Model model,
            Integer pageIndex,
            @ModelAttribute SysSetting sysSetting,
            HttpServletRequest request,
            HttpSession session) {
        String keys = request.getParameter("keys");
        String keys2 = (String) session.getAttribute("keys");
        //判断是点击搜索还是点击下一页来到这个界面
        if ((keys == null||"".equals(keys)) && (keys2 != null&&!"".equals(keys2))) {
            keys = keys2;
        }
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        if (keys == null||"".equals(keys)) {
            //将session的值设定为null
            session.setAttribute("keys", null);
            /** 查询配置信息     */
            List<SysSetting> sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
            //String video_id = sysSettings.get(0).getVideo_id();
            //VideoInfo videoInfo = adService.findVideoInfoByVideoId(video_id);
            model.addAttribute("sysSettings", sysSettings);
            model.addAttribute("pageModel", pageModel);
            return "setting/setting";
        }

        System.out.println(keys);
        //将keys的值设定到session中
        session.setAttribute("keys", keys);

        String[] keyList = keys.split("、");
        List<SysSetting> sysSettings = new ArrayList<>();
        if (keyList.length == 1) {
            sysSetting.setInput_type(keyList[0]);
            sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
            if (sysSettings.size() <= 0) {
                sysSetting.setInput_type(null);
                sysSetting.setUsername(keyList[0]);
                sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
            }
        }
        if (keyList.length == 2) {
            sysSetting.setUsername(keyList[0]);
            sysSetting.setInput_type(keyList[1]);
            sysSettings = adService.findSysSettingByKeys(sysSetting, pageModel);
        }

        model.addAttribute("sysSettings", sysSettings);
        model.addAttribute("pageModel", pageModel);
        return "setting/setting";
    }
}
