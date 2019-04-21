package com.pxyph.controller;

import com.pxyph.model.LogInfo;
import com.pxyph.model.User;
import com.pxyph.service.ADService;
import com.pxyph.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.USER_SESSION;

/**
 * 系统配置控制器
 */
@Controller
public class LogInfoController {
    @Autowired
    private ADService adService;

    /**
     * 查询日志信息表
     */
    @RequestMapping(value = "/log/selectLogInfoByKeys")
    public String getLogInfoByKeys(
            Model model,
            Integer pageIndex,
            @ModelAttribute LogInfo logInfo,
            HttpServletRequest request,
            HttpSession session) {
        String username = request.getParameter("username");
        String event_type = request.getParameter("event_type");

        String username2 = (String) session.getAttribute("username");
        String event_type2 = (String) session.getAttribute("event_type");

        //判断是点击搜索还是点击下一页来到这个界面
        if (username == null && event_type == null&&(username2 != null || event_type2!=null)) {
            username = username2;
            event_type =event_type2;
        }
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        if ((username == null && event_type == null) || (username.equals("")&&event_type.equals(""))
                ||(username == null && event_type.equals(""))||(username.equals("") && event_type == null)) {
            //将session的值设定为null
            session.setAttribute("username", null);
            session.setAttribute("event_type", null);
            /** 查询配置信息     */
            List<LogInfo> logInfos = adService.findLogInfoByKeys(logInfo, pageModel);
            model.addAttribute("logInfos", logInfos);
            model.addAttribute("pageModel", pageModel);
            return "log/loginfo";
        }

        System.out.println(username+"  "+event_type);
        //将keys的值设定到session中
        session.setAttribute("username", username);
        session.setAttribute("event_type", event_type);

        List<LogInfo> logInfos = new ArrayList<>();
        logInfo.setUsername(username);
        logInfo.setEvent_type(event_type);
        logInfos = adService.findLogInfoByKeys(logInfo, pageModel);

        model.addAttribute("logInfos", logInfos);
        model.addAttribute("pageModel", pageModel);
        return "log/loginfo";

    }


    /**
     * 处理删除日志请求
     *
     * @param ids 需要删除的id字符串
     * @param mv
     */
    @RequestMapping(value = "/log/removeLogInfo")
    public ModelAndView removeLogInfo(String ids, ModelAndView mv) {
        try {
            // 分解id字符串
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                // 根据id删除日志信息
                adService.removeLogInfoById(Integer.parseInt(id));

            }
            // 跳转到查询请求
            mv.setViewName("redirect:/log/selectLogInfoByKeys");
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
     * @param logInfo 要添加的日志信息对象
     * @param mv
     */
    @RequestMapping(value = "/log/addLogInfo")
    public ModelAndView addLogInfo(
            String flag,
            @ModelAttribute LogInfo logInfo,
            ModelAndView mv,
            HttpSession session) throws Exception {
        if (flag.equals("1")) {
            // 设置跳转到添加页面
            mv.setViewName("log/showAddLogInfo");
        } else {

            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            logInfo.setUsername(user.getUsername());

            // 执行添加操作
            adService.addLogInfo(logInfo);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/log/selectLogInfoByKeys");
        }
        // 返回
        return mv;
    }


    /**
     * 处理修改日志信息请求
     *
     * @param flag       标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param logInfo 要修改日志信息的对象
     * @param mv
     * @param session
     */
    @RequestMapping(value = "/log/updateLogInfo")
    public ModelAndView updateLogInfo(
            String flag,
            @ModelAttribute LogInfo logInfo,
            ModelAndView mv,
            HttpSession session) throws Exception {
        if (flag.equals("1")) {
            // 根据id查询配置
            LogInfo target = adService.findLogInfoById(logInfo.getId());
            // 设置Model数据
            mv.addObject("logInfo", target);
            // 设置跳转到修改页面
            mv.setViewName("log/showUpdateLogInfo");
        } else {
            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            logInfo.setUsername(user.getUsername());
            logInfo.setCreate_time(new Date());
            // 执行修改操作
            adService.modifyLogInfo(logInfo);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/log/selectLogInfoByKeys");
        }
        // 返回
        return mv;
    }


}
