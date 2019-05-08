package com.pxyph.controller;

import com.pxyph.model.AbnormalInfo;
import com.pxyph.model.SysSetting;
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
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.SETTINGID;
import static com.pxyph.util.common.ADConstants.USER_SESSION;

/**
 * 异常行为检索控制器
 */
@Controller
public class AbnormalInfoController {
    @Autowired
    private ADService adService;


    /**
     * 查询异常信息
     *
     * @param model
     * @param pageIndex
     * @param abnormalInfo
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/abnormalInfo/selectAbnormalInfoByKeys")
    public String getAbnormalInfoByKeys(
            Model model,
            Integer pageIndex,
            @ModelAttribute AbnormalInfo abnormalInfo,
            HttpServletRequest request,
            HttpSession session) throws ParseException {
        String event_type = request.getParameter("event_type");

        String start = request.getParameter("start");
        String end = request.getParameter("end");

        String event_type2 = (String) session.getAttribute("event_type");
        String start2 = (String) session.getAttribute("start");
        String end2 = (String) session.getAttribute("end");

        //判断是点击搜索还是点击下一页来到这个界面
        if ((event_type==null||"".equals(event_type))&&(start==null||"".equals(start))&&(end==null||"".equals(end))
                && ((event_type2 != null&&!"".equals(event_type2)) || (start2 != null&&!"".equals(start2)) || (end2 != null&&!"".equals(end2)))) {
            event_type = event_type2;
            start = start2;
            end = end2;
        }
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }

        if ((event_type==null||"".equals(event_type))&&(start==null||"".equals(start))&&(end==null||"".equals(end))) {
            //将session的值设定为null
            session.setAttribute("event_type", null);
            session.setAttribute("start", null);
            session.setAttribute("end", null);
            /** 查询信息     */
            List<AbnormalInfo> abnormalInfos = adService.findAbnormalInfoByKeys(abnormalInfo, pageModel);
            model.addAttribute("abnormalInfos", abnormalInfos);
            model.addAttribute("pageModel", pageModel);
            return "/abnormalInfo/abnormalInfo";
        }

        System.out.println(event_type + "  " + start + "  " + end);
        //将keys的值设定到session中
        session.setAttribute("event_type", event_type);
        session.setAttribute("start", start);
        session.setAttribute("end", end);

        abnormalInfo.setEvent_type(event_type);
        //通过时间来限定查询结果，目前还没发实现分页，主要因为，不清楚怎么确定当前时间在指定时间段内
        List<AbnormalInfo> abnormalInfos = adService.findAbnormalInfoByKeys(abnormalInfo, pageModel);
        if (abnormalInfos.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (start != null && !"".equals(start) && end != null && !"".equals(end)) {
                Date start1 = sdf.parse(start);
                Date end1 = sdf.parse(end);
                for (int i = 0; i < abnormalInfos.size(); ) {
                    Date create_time = sdf.parse(abnormalInfos.get(i).getCreate_time());
                    if (!belongCalendar(create_time, start1, end1)) {
                        abnormalInfos.remove(i);
                    } else {
                        i++;
                    }
                }
            } else if (start != null && !"".equals(start) && (end == null || "".equals(end))) {
                Date start1 = sdf.parse(start);
                for (int i = 0; i < abnormalInfos.size(); ) {
                    Date create_time = sdf.parse(abnormalInfos.get(i).getCreate_time());
                    if (!belongCalendar(create_time, start1, null)) {
                        abnormalInfos.remove(i);
                    } else {
                        i++;
                    }
                }
            } else if ((start == null || "".equals(start)) && end != null && !"".equals(end)) {
                Date end1 = sdf.parse(end);
                for (int i = 0; i < abnormalInfos.size(); ) {
                    Date create_time = sdf.parse(abnormalInfos.get(i).getCreate_time());
                    if (!belongCalendar(create_time, null, end1)) {
                        abnormalInfos.remove(i);
                    } else {
                        i++;
                    }
                }
            }
        }

        model.addAttribute("abnormalInfos", abnormalInfos);
        model.addAttribute("pageModel", pageModel);
        return "/abnormalInfo/abnormalInfo";

    }


    /**
     * 判断一个时间是否在一个时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar begin = Calendar.getInstance();
        //设置结束时间
        Calendar end = Calendar.getInstance();
        if (beginTime != null && endTime != null) {
            begin.setTime(beginTime);
            end.setTime(endTime);
            //处于开始时间之后，和结束时间之前的判断
            if (date.after(begin) && date.before(end)) {
                return true;
            } else {
                return false;
            }
        } else if (beginTime == null && endTime != null) {
            end.setTime(endTime);
            if (date.before(end)) {
                return true;
            } else {
                return false;
            }
        } else if (beginTime != null && endTime == null) {
            begin.setTime(beginTime);
            if (date.after(begin)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * 处理删除存储信息请求
     *
     * @param ids 需要删除的id字符串
     * @param mv
     */
    @RequestMapping(value = "/abnormalInfo/removeAbnormalInfo")
    public ModelAndView removeStorageInfo(String ids, ModelAndView mv) {
        try {
            // 分解id字符串
            String[] idArray = ids.split(",");
            for (String id : idArray) {
                adService.removeAbnormalInfoById(Integer.parseInt(id));
            }
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/abnormalInfo/selectAbnormalInfoByKeys");
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
     * <p>
     * 标记， 1表示跳转到添加页面，2表示执行添加操作
     *
     * @param abnormalInfo 要添加的信息对象
     */
    @RequestMapping(value = "/abnormalInfo/addAbnormalInfo")
    public String addAbnormalInfo(
            @ModelAttribute AbnormalInfo abnormalInfo,
            HttpSession session,
            HttpServletRequest request) throws Exception {
        String video_id = request.getParameter("video_id");
        String event_type = request.getParameter("event_type");
        BigInteger start_time = new BigInteger(request.getParameter("start_time"));
        BigInteger end_time = new BigInteger(request.getParameter("end_time"));
        //String settingId = null;
        //Cookie[] cookies = request.getCookies();
        //for (Cookie cookie:cookies){
        //    if (cookie.getName().equals(SETTINGID)){
        //        settingId = cookie.getValue();
        //    }
        //}
        String settingId = (String) session.getAttribute(SETTINGID);
        SysSetting sysSetting = adService.findSysSettingById(Integer.parseInt(settingId));
        String video_path = sysSetting.getSave_path();
        String video_name = sysSetting.getVideo();

        //通过以上信息构建说明文档
        String anomaly_document = "视频" + video_name + "在" + start_time + "微秒到" + end_time + "微秒之间出现了" + event_type + "情况";

        //将信息添加到abnormalInfo中
        abnormalInfo.setEvent_type(event_type);
        abnormalInfo.setCreate_time(new Date());
        abnormalInfo.setAnomaly_document(anomaly_document);
        abnormalInfo.setStart_time(start_time);
        abnormalInfo.setEnd_time(end_time);
        abnormalInfo.setVideo_id(video_id);
        abnormalInfo.setVideo_name(video_name);
        abnormalInfo.setVideo_path(video_path);

        //添加用户信息
        User user = (User) session.getAttribute(USER_SESSION);
        abnormalInfo.setUsername(user.getUsername());

        // 执行添加操作
        adService.addAbnormalInfo(abnormalInfo);

        // 返回
        return "ok";
    }


    /**
     * 处理修改信息请求
     *
     * @param flag         标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param abnormalInfo 要修改信息的对象
     * @param mv
     * @param session
     */
    @RequestMapping(value = "/abnormalInfo/updateAbnormalInfo")
    public ModelAndView updateStorageInfo(
            String flag,
            @ModelAttribute AbnormalInfo abnormalInfo,
            ModelAndView mv,
            HttpSession session,
            HttpServletRequest request) throws Exception {
        if (flag.equals("1")) {
            // 根据id查询配置
            AbnormalInfo target = adService.findAbnormalInfoById(abnormalInfo.getId());
            mv.addObject("abnormalInfo", target);
            // 设置跳转到修改页面
            mv.setViewName("abnormalInfo/showUpdateAbnormalInfo");
        } else {
            abnormalInfo.setEvent_type(request.getParameter("event_type"));
            abnormalInfo.setAnomaly_document(request.getParameter("anomaly_document"));
            abnormalInfo.setStart_time(new BigInteger(request.getParameter("start_time")));
            abnormalInfo.setEnd_time(new BigInteger(request.getParameter("end_time")));
            abnormalInfo.setCreate_time(new Date());

            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            abnormalInfo.setUsername(user.getUsername());


            // 执行修改操作
            adService.modifyAbnormalInfo(abnormalInfo);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/abnormalInfo/selectAbnormalInfoByKeys");
        }
        // 返回
        return mv;
    }


}
