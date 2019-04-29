package com.pxyph.controller;

import com.pxyph.model.DefaultSetting;
import com.pxyph.service.ADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.pxyph.util.common.ADConstants.SETTINGID;

@Controller
public class DefaultSettingController {
    @Autowired
    private ADService adService;


    /**
     * 设定默认设置配置
     * @param id
     * @param session
     * @param mv
     * @return
     */
    @RequestMapping(value = "/setting/defaultSetting")
    public ModelAndView defaultSetting(
            String id,
            HttpSession session,ModelAndView mv){
        DefaultSetting defaultSetting = adService.findDefaultSettingById(1);
        if(defaultSetting != null){
            //将默认设置信息保存到HTTPSession当中
            session.setAttribute(SETTINGID,id);
            System.out.println(defaultSetting);
            //客户端跳转到main页面
            mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        }else {
            //设置默认信息提取失败提示信息
            mv.addObject("message","默认信息缺失");
            mv.setViewName("redirect:/setting/searchSysSettingsByKeys");
        }
        return mv;
    }
}
