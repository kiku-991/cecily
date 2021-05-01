package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.service.UserInfoService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;

@Controller
public class UserCenterController {
	@Autowired
	UserLoginService userService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	UserDeliveryService userDeliveryService;
	
	@Autowired
	ProductService productService;
	/*
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	MyCartService myCartService;*/

	//以下すべて遷移画面になります

	//マイページ 

	@RequestMapping("/shopping/center")
	public ModelAndView center(HttpServletRequest request) throws Exception {

		UserInfoDto userInfo = userInfoService.getUserInfo(request);
		ModelAndView mv = new ModelAndView("center");
		mv.addObject("userInfo", userInfo);

		return mv;
	}

	//会員基本情報画面
	@RequestMapping("/center/userInfo")
	public ModelAndView userInfo(HttpServletRequest request) throws Exception {
		UserInfoDto userInfo = userInfoService.getUserInfo(request);
		ModelAndView mv = new ModelAndView("center/userInfo");
		mv.addObject("userInfo", userInfo);
		return mv;
	}

	//届け住所編集画面

	@RequestMapping("/center/userTodoke")
	public ModelAndView userTodoke(HttpServletRequest request) throws Exception {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		List<UserDeliveryDto> userdelivery = userDeliveryService.getUserDelivery(ss.getUserId());
		ModelAndView mv = new ModelAndView("center/userTodoke");
		mv.addObject("useradd", userdelivery);
		return mv;
	}

	//パスワード変更画面

	@RequestMapping("/center/pwdChange")
	public ModelAndView pwdChange(HttpSession session) {
		Session ss = (Session) session.getAttribute("userLogin");
		String pw = userService.getPwd(ss.getUserMail());

		ModelAndView mv = new ModelAndView("center/pwdChange");
		mv.addObject("pwd", pw);
		return mv;
	}

	//パスワード変更失敗画面
	@RequestMapping(value = "/center/passfail")
	public ModelAndView pwdfail(HttpSession session) {
		Session ss = (Session) session.getAttribute("userLogin");
		String pw = userService.getPwd(ss.getUserMail());

		ModelAndView mv = new ModelAndView("center/passfail");
		mv.addObject("pwcheck", MsgContents.CHECKPWD);
		mv.addObject("pwd", pw);
		return mv;

	}

	//お気に入り
	@RequestMapping("/center/favorite")
	public ModelAndView favorite(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("center/favorite");

		//List<FavoriteDto> favorites = favoriteService.getFavorite(request);

		//mv.addObject("favoritePros", favorites);
		return mv;
	}

	//買い物かご
	@RequestMapping("/center/shopcart")
	public ModelAndView shopcart() {

		ModelAndView mv = new ModelAndView("center/shopcart");
		return mv;
	}

	//マイ注文
	@RequestMapping("/center/mycyumon")
	public ModelAndView myCyoumon() {

		ModelAndView mv = new ModelAndView("center/myCyoumon");
		return mv;
	}

	//出店申込
	@RequestMapping("/center/moushikomi")
	public ModelAndView moushikomi() {

		ModelAndView mv = new ModelAndView("center/moushikomi");
		return mv;
	}

	//注文管理
	@RequestMapping("/center/cyumonManager")
	public ModelAndView cyumonManager() {

		ModelAndView mv = new ModelAndView("center/cyumonManager");
		return mv;
	}

	//商品管理(店舗)
	//	@RequestMapping(value = "/center/productmanger")
	//	public ModelAndView prouct() throws Exception {
	//		//該当ユーザのすべての商品取得
	//				List<ProductEntity> pro = productService.getPro(request);
	//				mv.addObject("products", pro);
	//
	//		ModelAndView mv = new ModelAndView("center/productmanger");
	//		List<ProductDto> pros = productService.getAllPro();
	//		mv.addObject("products", pros);
	//		return mv;
	//	}

	//店舗申込

	@RequestMapping(value = "/center/rolechange")
	public ModelAndView changeRole() {

		ModelAndView mv = new ModelAndView("center/rolechange");
		mv.addObject("tenpu", MsgContents.TENNPU);
		return mv;

	}

	/**
	 * ユーザID によって商品取得(店舗)
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/center/productmanger")
	public ModelAndView getProByid(HttpServletRequest request, ProductDto product) {

		ModelAndView mv = new ModelAndView("center/productmanger");
		List<ProductDto> pros = productService.getProByUseId(request);

		mv.addObject("products", pros);
		return mv;

	}

	//ユーザ一覧
	@RequestMapping("/center/userlist")
	public ModelAndView userlist() {

		List<UserLoginDto> users = userService.getAllUser();
		ModelAndView mv = new ModelAndView("center/userlist");
		mv.addObject("users", users);

		return mv;
	}

	//ユーザ情報(ADMIN)
	@RequestMapping("/center/userInfoList")
	public ModelAndView userInfoList() {
		List<UserInfoDto> usersInfo = userInfoService.serchAll();
		ModelAndView mv = new ModelAndView("center/userInfoList");
		mv.addObject("usersInfo", usersInfo);
		return mv;
	}

	//ユーザ届け住所連絡(ADMIN)
	@RequestMapping("/center/userTodokeList")
	public ModelAndView userTodokeList() {
		List<UserInfoDto> usersInfo = userInfoService.serchAll();
		ModelAndView mv = new ModelAndView("center/userTodokeList");
		mv.addObject("useradd", usersInfo);
		return mv;
	}

}
