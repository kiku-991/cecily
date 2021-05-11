package com.shoping.kiku.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.service.UserInfoService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

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
	@RequestMapping(value = Url.USERTCREATE, method = RequestMethod.POST)
	public String createUserInfo(@RequestParam("file") MultipartFile icon, HttpServletRequest res,
			UserInfoDto userInfoDto) throws IOException {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		String userIcon = icon.getOriginalFilename();
		try {
			icon.transferTo(new File(Url.SAVEPATH + userIcon));
			System.out.println(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String ic =Url.SRC + userIcon;
		userInfoService.creatUserInfo(ss.getUserId(),ic, userInfoDto);
		//userInfoService.updateUserUserUrl(user, ss.getUserId());
		return "redirect:/center/userInfo";

	}

	/**
	 * 該当ユーザ情報更新
	 * @param session
	 * @param userInfo
	 * @return
	 */

	@RequestMapping(value = Url.USERINFOEDIT, method = RequestMethod.POST)
	public String updateUserInfo(@RequestParam("file") MultipartFile icon, HttpServletRequest res,
			UserInfoDto userInfo) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		String ic = "";
		if (icon.isEmpty() == false) {
			String userIcon = icon.getOriginalFilename();
			try {
				icon.transferTo(
						new File(Url.SAVEPATH + userIcon));
				System.out.println(icon);
			} catch (Exception e) {
				e.printStackTrace();
			}

			ic = Url.SRC + userIcon;
		}
		userInfoService.updateUserInfo(ss.getUserId(), ic, userInfo);

		return "redirect:/center/userInfo";
	}

	/**
	 * 該当届け住所作成
	 * @param session
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = Url.USERTODOKEADD, method = RequestMethod.POST)
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
	@RequestMapping(value = Url.USERTODOKEEDIT, method = RequestMethod.POST)
	public String editAdd(@PathVariable("id") int id, HttpServletRequest request, UserDeliveryDto userInfo) {

		Session ss = (Session) request.getSession().getAttribute("userLogin");
		userDeliveryService.editUserDelivery(id, ss.getUserId(), userInfo);
		return "redirect:/center/userTodoke";

	}

	/**
	 * 該当届け住所削除
	 * @param session
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = Url.USERTODOKEEDELETE, method = RequestMethod.POST)
	public String deleteAdd(@PathVariable("id") int id) {
		userDeliveryService.deleteUserTodoke(id);
		return "redirect:/center/userTodoke";

	}

	/**
	 * パスワード変更
	 * @param model
	 * @return
	 */
	@RequestMapping(value = Url.PWDEDIT, method = RequestMethod.POST)
	public String pwdChange(HttpSession session, UserLoginDto user) {

		boolean pc = userLoginService.passChange(session, user);

		if (pc == false) {
			//失敗
			//model.addAttribute("pwcheck", MsgContents.CHECKPWD);
			return "redirect:/center/passfail";
		} else {
			//成功
			//model.addAttribute("pwchange", MsgContents.PASSCHANGE);
			return "redirect:/center/passwordchanged";
		}

	}

	/**
	 * 登录届け住所(buy画面)
	 * @param session
	 * @param userInfo
	 * @return
	 */

	@RequestMapping(value = Url.ADDTODOKRBUY, method = RequestMethod.POST)
	public String creUserTodoke(HttpServletRequest request, UserDeliveryDto userDeliver) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		userDeliveryService.creUserDelivery(ss.getUserId(), userDeliver);

		return "redirect:/mycart/buy";
	}

	/**
	 * 編集届け住所(buy画面)
	 * @param session
	 * @param userInfo
	 * @return
	 */

	@RequestMapping(value = "/buy/useradd/edit/{id}", method = RequestMethod.POST)
	public String upUserTodoke(@PathVariable("id") int id, HttpServletRequest request, UserDeliveryDto userDeliver) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		userDeliveryService.editUserDelivery(id, ss.getUserId(), userDeliver);

		return "redirect:/mycart/buy";
	}

	/**
	 * ユーザ一覧(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = Url.ALLUSER, method = RequestMethod.GET)
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
	@RequestMapping(value = Url.ALLUSEREDIT)
	public String changeUser(@PathVariable("id") int id, UserLoginDto userdto) {

		userLoginService.changerUser(id, userdto);

		return "redirect:/center/userlist";
	}

	/**
	 * ユーザIDによるユーザ削除(ADMIN)
	 * @param id
	 */
	@RequestMapping(value = Url.USERDELETEBYID)
	public String deleteUser(@PathVariable("userId") int id) {
		userLoginService.deleteUser(id);

		return "redirect:/center/userlist";
	}

	/**
	 * すべてのユーザー情報操作(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = Url.ALLUSERINFO)
	public String changeAllUserInfo(@PathVariable("id") int userid,  @RequestParam("file") MultipartFile icon, UserInfoDto userInfodto) {

		String ic = "";
		if (icon.isEmpty() == false) {
			String userIcon = icon.getOriginalFilename();
			try {
				icon.transferTo(
						new File(Url.SAVEPATH + userIcon));
				System.out.println(icon);
			} catch (Exception e) {
				e.printStackTrace();
			}

			ic = Url.SRC + userIcon;
		}
		userInfoService.updateAllUserInfo(userid, userInfodto,ic);
		return "redirect:/center/userInfoList";
	}

	/**
	 * すべてのユーザー届け住所情報操作(ADMIN)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = Url.ALLUSERTODOKEEDIT)
	public String changeAllUserAdd(@PathVariable("id") int userid, UserInfoDto userInfodto) {
		userInfoService.updateAllUserAddInfo(userid, userInfodto);
		return "redirect:/center/userTodokeList";
	}
}
