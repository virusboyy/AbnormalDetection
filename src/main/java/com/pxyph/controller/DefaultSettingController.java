package com.pxyph.controller;

import com.pxyph.model.DefaultSetting;
import com.pxyph.service.ADService;
import com.pxyph.util.common.ADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DefaultSettingController {
    @Autowired
    private ADService adService;

    public ModelAndView defaultSetting(HttpSession session,ModelAndView mv){
        DefaultSetting defaultSetting = adService.findDefaultSettingById(1);
        if(defaultSetting == null){
            //将默认设置信息保存到HTTPSession当中
            session.setAttribute(ADConstants.DEFAULTSETTING_SESSION,defaultSetting);
            System.out.println(defaultSetting);
            //客户端跳转到main页面
            mv.setViewName("redirect:/setting/showUpdateSetting");
        }else {
            //设置默认信息提取失败提示信息
            mv.addObject("message","默认信息缺失");
            mv.setViewName("redirect:/setting/selectSetting");
        }
        return mv;
    }
}
