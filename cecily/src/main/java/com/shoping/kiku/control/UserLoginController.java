package com.shoping.kiku.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserInfoService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Controller
public class UserLoginController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	UserInfoService userInfoService;

	/**
	 * ログイン画面
	 * @return
	 */
	@RequestMapping(Url.LOGIN)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	/**
	 * ユーザーログイン
	 * @param user
	 * @return
	 */
	@PostMapping(Url.LOGIN)
	public String userLogin(UserLoginDto user, Map<String, Object> map, HttpSession session) {

		//判断該当メールが登録されているかどうか
		String mail = userLoginService.mailCheck(user);
		if (mail.equals(MsgContents.MAILNO)) {
			//登録されてない場合
			map.put("mailcheck", MsgContents.REGISTRATENASHI);
			return "login";
		} else {
			//登録されてる場合 セッションに設定

			int status = userLoginService.getStatus(user.getUserMail());
			Session sessionDto = new Session();
			sessionDto.setUserId(userLoginService.getId(user.getUserMail()));
			sessionDto.setUserMail(user.getUserMail());
			sessionDto.setRole(userLoginService.getRole(user.getUserMail()));
			sessionDto.setStatus(status);

			if (status == 1) { //入力したメールアドレスとパスワードをチェック
				String mailPsCheck = userLoginService.loginProcess(user);
				//メールアドレスとパスワードがDBのと一致しない場合
				if (mailPsCheck.equals(MsgContents.CHECKFALSE)) {
					//エラーメッセージ提示
					map.put("mpcheck", MsgContents.LOGINFAILUER);
					return "login";
				} else {
					//ログイン成功 ホームページへ

					session.setAttribute("userLogin", sessionDto);

					return "redirect:/shopping";
				}

			} else {
				map.put("statusCheck", MsgContents.STATUSCHECK);
				return "login";
			}

		}

	}

	/**
	 * ログアウト 
	 * 
	 */
	@RequestMapping(Url.LOGINOUT)
	public String loginout(HttpSession session) {

		session.removeAttribute("userLogin");

		return "redirect:/shopping";

	}

	@RequestMapping(Url.FORGETPWD)
	public ModelAndView forgetPwd() {

		ModelAndView mv = new ModelAndView("center/forget");

		return mv;
	}

	@RequestMapping("/pwdreset")
	@ResponseBody
	public int forgetpwd(@RequestBody HashMap<String, String> map) {

		String name = map.get("userName");
		String mail = map.get("userMail");
		String pwd = map.get("userPwd");

		int check = userInfoService.pwdReset(name, mail, pwd);

		if (check == 1) {
			//成功

			return 1;
		} else {
			//失敗
			return 0;
		}

	}
}
