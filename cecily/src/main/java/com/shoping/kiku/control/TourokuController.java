package com.shoping.kiku.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.PwdHashing;


@Controller
public class TourokuController {

	@Autowired 
	UserLoginService userLoginService;
	
	
	/**
	 * 登録画面
	 * @return
	 */
	@RequestMapping("/touroku")
	public ModelAndView toroku() {
		ModelAndView mv = new ModelAndView("touroku");
		return mv;
	}
	
	/**
	 * ユーザー登録
	 * @param user
	 * @return
	 */
	@PostMapping("/touroku")
	public String createUser(UserLoginDto user, Model model) {

		//パスワード非表示設定
		String pwd = PwdHashing.pwdEnCode(user.getUserPassword());

		UserLoginDto use = new UserLoginDto();
		use.setUserMail(user.getUserMail());
		use.setUserPassword(pwd);
		//ユーザ作成
		int mailCheck = userLoginService.createUser(use);

		//登録失敗
		if (mailCheck == 1) {
			model.addAttribute("msg", MsgContents.REGISTRATEFAILUER);
			//登録画面に遷移
			return "touroku";
		} else {
			//ログイン画面に遷移
			return "login";
		}
	}
}
