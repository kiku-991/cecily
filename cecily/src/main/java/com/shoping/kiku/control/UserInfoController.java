package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.service.UserInfoService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;

@Controller
public class UserInfoController {
	@Autowired
	UserLoginService userLoginService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	UserDeliveryService userDeliveryService;

	/**
	 * ユーザ情報登録
	 * @param userDto
	 * @param userInfoDto
	 */
	@RequestMapping(value = "/center/userInfo/usercrete", method = RequestMethod.POST)
	public String createUserInfo(HttpServletRequest request, UserInfoDto userInfoDto) {

		userInfoService.creatUserInfo(request, userInfoDto);
		return "redirect:/center/userInfo";

	}

	/**
	 * 該当ユーザ情報更新
	 * @param session
	 * @param userInfo
	 * @return
	 */

	@RequestMapping(value = "/center/userInfo/edit", method = RequestMethod.POST)
	public String updateUserInfo(HttpServletRequest request, UserInfoDto userInfo) {

		userInfoService.updateUserInfo(request, userInfo);
		return "redirect:/center/userInfo";
	}

	/**
	 * 該当届け住所作成
	 * @param session
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/center/userTodoke/add", method = RequestMethod.POST)
	public String creAdd(HttpServletRequest request, UserDeliveryDto userInfo) {

		Session ss = (Session) request.getSession().getAttribute("userLogin");
		userDeliveryService.creUserDelivery(ss.getUserId(), userInfo);
		return "redirect:/center/userTodoke";

	}
	
	/**
	 * 該当届け住所編集
	 * @param session
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/center/userTodoke/edit/{id}", method = RequestMethod.POST)
	public String editAdd(@PathVariable("id")int id,HttpServletRequest request, UserDeliveryDto userInfo) {

		Session ss = (Session) request.getSession().getAttribute("userLogin");
		userDeliveryService.editUserDelivery(id,ss.getUserId(), userInfo);
		return "redirect:/center/userTodoke";

	}
	
	/**
	 * 該当届け住所編集
	 * @param session
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/center/userTodoke/delete/{id}", method = RequestMethod.POST)
	public String deleteAdd(@PathVariable("id")int id) {
		userDeliveryService.deleteUserTodoke(id);
		return "redirect:/center/userTodoke";

	}
	

	/**
	 * パスワード変更
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/pwdChange/edit", method = RequestMethod.POST)
	public String pwdChange(HttpSession session, UserLoginDto user, Model model) {

		boolean pc = userLoginService.passChange(session, user);

		if (pc == false) {
			//失敗
			model.addAttribute("pwcheck", MsgContents.CHECKPWD);
			return "redirect:/center/passfail";
		}else {
			//成功
			model.addAttribute("pwchange", MsgContents.PASSCHANGE);
			return "login";
		}

		
	}

	/**
	 * Role 変更 店舗申込
	 * @param request
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/center/moushikomi", method = RequestMethod.POST)
	public String storeChange(HttpServletRequest request) {
		//Role 変更
		userLoginService.roleChange(request);

		return "redirect:/center/rolechange";

	}

	/**
	 * ユーザ一覧(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/userlist", method = RequestMethod.GET)
	public String getAllUser(Model model) {

		List<UserLoginDto> users = userLoginService.getAllUser();
		model.addAttribute("users", users);
		return "center/userlist";

	}

	/**
	 * ユーザー操作(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/userlist/userEdit/{id}")
	public String changeUser(@PathVariable("id") int id, UserLoginDto userdto) {

		userLoginService.changerUser(id, userdto);

		return "redirect:/center/userlist";
	}

	/**
	 * ユーザIDによるユーザ削除(ADMIN)
	 * @param id
	 */
	@RequestMapping(value = "/center/userInfoList/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int id) {
		userLoginService.deleteUser(id);

		return "redirect:/center/userlist";
	}

	/**
	 * すべてのユーザー情報操作(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/userInfoList/{id}")
	public String changeAllUserInfo(@PathVariable("id") int userid, UserInfoDto userInfodto) {

		userInfoService.updateAllUserInfo(userid, userInfodto);
		return "redirect:/center/userInfoList";
	}

	/**
	 * すべてのユーザー届け住所情報操作(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/center/userTodokeList/edit/{id}")
	public String changeAllUserAdd(@PathVariable("id") int userid, UserInfoDto userInfodto) {
		userInfoService.updateAllUserAddInfo(userid, userInfodto);
		return "redirect:/center/userTodokeList";
	}
}
