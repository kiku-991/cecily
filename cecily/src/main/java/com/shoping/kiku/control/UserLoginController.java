package com.shoping.kiku.control;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;

@Controller
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	
	//ログイン画面
		@RequestMapping("/login")
		public ModelAndView login() {
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}

		/**
		 * ユーザーログイン
		 * @param user
		 * @return
		 */
		@PostMapping("/login")
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

				if (status==1) { //入力したメールアドレスとパスワードをチェック
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

				}else {
					map.put("statusCheck", MsgContents.STATUSCHECK);
					return "login";
				}

			}

		}

		/**
		 * ログアウト 
		 * 
		 */
		@RequestMapping("/loginout")
		public ModelAndView loginout(HttpSession session) {

			session.removeAttribute("userLogin");

			ModelAndView mv = new ModelAndView("shopping");
			return mv;
		}

}
