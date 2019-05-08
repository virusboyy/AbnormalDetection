package com.pxyph.controller;

import com.pxyph.model.StorageManager;
import com.pxyph.model.User;
import com.pxyph.model.VideoInfo;
import com.pxyph.service.ADService;
import com.pxyph.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static com.pxyph.util.common.ADConstants.USER_SESSION;
import static com.pxyph.util.common.ADConstants.VIDEOPATH;

/**
 * 系统配置控制器
 */
@Controller
public class StorageManagerController {
    @Autowired
    private ADService adService;


    /**
     * 查询存储管理信息
     *
     * @param model
     * @param pageIndex
     * @param storageManager
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/storageManager/selectStorageInfoByKeys")
    public String getStorageInfoByKeys(
            Model model,
            Integer pageIndex,
            @ModelAttribute StorageManager storageManager,
            HttpServletRequest request,
            HttpSession session) {
        String username = request.getParameter("username");
        String file_name = request.getParameter("file_name");

        String username2 = (String) session.getAttribute("username");
        String file_name2 = (String) session.getAttribute("file_name");

        //判断是点击搜索还是点击下一页来到这个界面
        if ((username == null || "".equals(username)) && (file_name == null||"".equals(file_name))
                && (username2 != null&&!"".equals(username2)) || (file_name2 != null&&!"".equals(file_name2))) {
            username = username2;
            file_name = file_name2;
        }
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        if ((username == null || "".equals(username)) && (file_name == null||"".equals(file_name))) {
            //将session的值设定为null
            session.setAttribute("username", null);
            session.setAttribute("file_name", null);
            /** 查询配置信息     */
            List<StorageManager> storageManagers = adService.findStorageInfoByKeys(storageManager, pageModel);
            model.addAttribute("storageManagers", storageManagers);
            model.addAttribute("pageModel", pageModel);
            return "storageManager/storageManager";
        }

        System.out.println(username + "  " + file_name);
        //将keys的值设定到session中
        session.setAttribute("username", username);
        session.setAttribute("file_name", file_name);

        storageManager.setUsername(username);
        storageManager.setFile_name(file_name);
        List<StorageManager> storageManagers = adService.findStorageInfoByKeys(storageManager, pageModel);

        model.addAttribute("storageManagers", storageManagers);
        model.addAttribute("pageModel", pageModel);
        return "storageManager/storageManager";

    }


    /**
     * 处理删除存储信息请求
     *
     * @param ids 需要删除的id字符串
     * @param mv
     */
    @RequestMapping(value = "/storageManager/removeStorageInfo")
    public ModelAndView removeStorageInfo(String ids, ModelAndView mv) {
        try {
            // 分解id字符串
            String[] idArray = ids.split(",");
            for (String id : idArray) {

                //StorageManager storageManager = adService.findStorageInfoById(Integer.parseInt(id));
                //File[] videoFiles = new File(VIDEOPATH).listFiles();
                //for (File file : videoFiles) {
                //    System.out.println(file.getAbsolutePath());
                //    System.out.println(file.getName());
                //    if (file.getName().equals(storageManager.getFile_name())) {
                //        file.getAbsoluteFile().delete();
                //    }
                //}

                // 根据id删除配置
                adService.removeStorageInfoById(Integer.parseInt(id));

            }
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/storageManager/selectStorageInfoByKeys");
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
     * @param storageManager 要添加的存储管理信息对象
     */
    @RequestMapping(value = "/storageManager/addStorageInfo")
    public String addStorageInfo(
            @ModelAttribute StorageManager storageManager,
            HttpSession session,
            HttpServletRequest request) throws Exception {
        String video_id = request.getParameter("video_id");
        VideoInfo videoInfo = adService.findVideoInfoById(Integer.parseInt(video_id));


        //上传路径
        String videoPath = videoInfo.getVideo_path();
        String file_name = videoInfo.getFile_name();

        //保存到一个目标文件中
        File file = new File(videoPath + file_name);
        MultipartFile file2 = new MockMultipartFile(file_name, file_name, "text/plain", new FileInputStream(file));
        file2.transferTo(new File(VIDEOPATH + File.separator + file_name));

        storageManager.setVideo_id(videoInfo.getVideo_id());
        storageManager.setFile_name(file_name);
        storageManager.setFile_size(videoInfo.getFile_size());
        storageManager.setVideo_width(videoInfo.getVideo_width());
        storageManager.setVideo_height(videoInfo.getVideo_height());
        storageManager.setFrame_num(videoInfo.getFrame_num());
        storageManager.setFps(videoInfo.getFps());

        storageManager.setCreate_time(new Date());
        //添加用户信息
        User user = (User) session.getAttribute(USER_SESSION);
        storageManager.setUsername(user.getUsername());

        // 执行添加操作
        adService.addStorageInfo(storageManager);

        // 返回
        return "ok";
    }


    /**
     * 处理修改存储信息请求
     *
     * @param flag           标记， 1表示跳转到修改页面，2表示执行修改操作
     * @param storageManager 要修改存储信息的对象
     * @param mv
     * @param session
     */
    @RequestMapping(value = "/storageManager/updateStorageInfo")
    public ModelAndView updateStorageInfo(
            String flag,
            @ModelAttribute StorageManager storageManager,
            ModelAndView mv,
            HttpSession session,
            HttpServletRequest request) throws Exception {
        if (flag.equals("1")) {
            // 根据id查询配置
            StorageManager target = adService.findStorageInfoById(storageManager.getId());
            mv.addObject("storageManager", target);
            // 设置跳转到修改页面
            mv.setViewName("storageManager/showUpdateStorageInfo");
        } else {
            storageManager.setFile_size(new BigInteger(request.getParameter("file_size")));
            storageManager.setVideo_width(Integer.parseInt(request.getParameter("video_width")));
            storageManager.setVideo_height(Integer.parseInt(request.getParameter("video_height")));
            storageManager.setFrame_num(Integer.parseInt(request.getParameter("frame_num")));
            storageManager.setFps(Integer.parseInt(request.getParameter("fps")));
            storageManager.setCreate_time(new Date());

            //添加用户信息
            User user = (User) session.getAttribute(USER_SESSION);
            storageManager.setUsername(user.getUsername());


            // 执行修改操作
            adService.modifyStorageInfo(storageManager);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/storageManager/selectStorageInfoByKeys");
        }
        // 返回
        return mv;
    }


}
