package com.pxyph.controller;

import com.pxyph.model.User;
import com.pxyph.service.ADService;
import com.pxyph.util.common.ADConstants;
import com.pxyph.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理用户请求控制器
 * */
@Controller
public class UserController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	private ADService adService;

	/**
	 * 处理登录请求
	 * @param loginname  登录名
	 * @param password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session,
			 ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		User user = adService.login(loginname, password);
		if(user != null){
			// 将用户保存到HttpSession当中
			session.setAttribute(ADConstants.USER_SESSION, user);
			// 客户端跳转到main页面
			mv.setViewName("redirect:/main");
		}else{
			// 设置登录失败提示信息
			mv.addObject("message", "登录名或密码错误!请重新输入");
			// 服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;

	}

	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param user 模糊查询参数
	 * @param model
	 * */
	@RequestMapping(value="/user/searchUsersByKeys")
	public String getUsersByKeys(
			Model model,
			Integer pageIndex,
			@ModelAttribute User user,
			HttpServletRequest request,
			HttpSession session) {
		String username = request.getParameter("username");
		String userstatus = request.getParameter("userstatus");

		String username2 = (String) session.getAttribute("username");
		String userstatus2 = (String) session.getAttribute("userstatus");

		//判断是点击搜索还是点击下一页来到这个界面
		if (username == null && userstatus == null&&(username2 != null || userstatus2!=null)) {
			username = username2;
			userstatus =userstatus2;
		}
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		if ((username == null && userstatus == null) || (username.equals("")&&userstatus.equals(""))
		||(username == null && userstatus.equals(""))||(username.equals("") && userstatus == null)) {
			//将session的值设定为null
			session.setAttribute("username", null);
			session.setAttribute("userstatus", null);
			/** 查询配置信息     */
			List<User> users = adService.findUsersByKeys(user, pageModel);
			model.addAttribute("users", users);
			model.addAttribute("pageModel", pageModel);
			return "user/user";
		}

		System.out.println(username+"  "+userstatus);
		//将keys的值设定到session中
		session.setAttribute("username", username);
		session.setAttribute("userstatus", userstatus);

		List<User> users = new ArrayList<>();
		user.setUsername(username);
		if (userstatus!=null&&!userstatus.equals("")){
            user.setUserstatus(Integer.parseInt(userstatus));
        }
		users = adService.findUsersByKeys(user, pageModel);

		model.addAttribute("users", users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";

	}

	/**
	 * 处理删除用户请求
	 * @param ids 需要删除的id字符串
	 * @param mv
	 * */
	@RequestMapping(value="/user/removeUser")
	 public ModelAndView removeUser(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			adService.removeUserById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/searchUsersByKeys");
		// 返回ModelAndView
		return mv;
	}


	/**
	 * 处理修改用户请求
	 * @param flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param user  要修改用户的对象
	 * @param mv
	 * */
	@RequestMapping(value="/user/updateUser")
	 public ModelAndView updateUser(
			 String flag,
			 @ModelAttribute User user,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询用户
			User target = adService.findUserById(user.getId());
			// 设置Model数据
			mv.addObject("user", target);
			// 返回修改员工页面
			mv.setViewName("user/showUpdateUser");
		}else{
			// 执行修改操作
			adService.modifyUser(user);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/searchUsersByKeys");
		}
		// 返回
		return mv;
	}


	/**
	 * 处理添加请求
	 * @param flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param user  要添加用户的对象
	 * @param mv
	 * */
	@RequestMapping(value="/user/addUser")
	 public ModelAndView addUser(
			 String flag,
			 @ModelAttribute User user,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("user/showAddUser");
		}else{
			// 执行添加操作
			adService.addUser(user);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/searchUsersByKeys");
		}
		// 返回
		return mv;
	}

	/**
	 * 处理注销退出请求
	 * @param mv
	 * */
	@RequestMapping(value="/logout")
	 public ModelAndView logout(
			 ModelAndView mv,
			 HttpSession session) {
		// 注销session
		session.invalidate();
		// 跳转到登录页面
		mv.setViewName("redirect:/loginForm");
		return mv;
	}

}
