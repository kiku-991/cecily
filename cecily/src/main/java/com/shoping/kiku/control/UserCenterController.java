package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.object.StoreDto;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.service.FavoriteService;
import com.shoping.kiku.service.MyOrderService;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.service.StoreService;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.service.UserInfoService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Status;

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

	@Autowired
	StoreService storeService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	MyOrderService myOrderService;
	/*
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
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/favorite");

		List<FavoProDto> favorites = favoriteService.getFavorite(ss.getUserId());

		mv.addObject("favoritePros", favorites);
		return mv;
	}

	//買い物かご
	@RequestMapping("/center/shopcart")
	public ModelAndView shopcart() {

		ModelAndView mv = new ModelAndView("center/shopcart");
		return mv;
	}

	//出店申込
	@RequestMapping("/center/myshop")
	public ModelAndView moushikomi(HttpServletRequest re) {
		Session ss = (Session) re.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/myshop");
		StoreDto store = storeService.getStoreInfo(ss.getUserId());
		mv.addObject("store", store);
		if (store != null) {
			if (store.getStoreStatus() == Status.SHOPOPAPP) {
				mv.addObject("apply", MsgContents.TENNPUAPP);
			} else if (store.getStoreStatus() == Status.SHOPOPEN) {
				mv.addObject("open", MsgContents.TENNPUOK);
			} else {
				mv.addObject("refuse", MsgContents.TENNPUREFUSE);
				mv.addObject("block", MsgContents.TENNPUBLOCK);
			}

		}

		return mv;
	}

	//注文管理
	/*	@RequestMapping("/center/cyumonManager")
		public ModelAndView cyumonManager(HttpServletRequest res) {
			Session ss = (Session) res.getSession().getAttribute("userLogin");
			int stId = storeService.getstoreIdByUserId(ss.getUserId());
			List<OrderMangerDto> orm = myOrderService.getOrderInfo(stId);
	
			List<OrderInfoByUserIdDto> xx = myOrderService.getOrderInfoByStoreId(stId);
			ModelAndView mv = new ModelAndView("center/cyumonManager");
			
			
			
			
			mv.addObject("orderInfo", orm);
			mv.addObject("orderxx", xx);
			return mv;
		}*/

	/*	//注文管理(Store)
		@RequestMapping("/center/cyumonManager")
		public ModelAndView orderManager(HttpServletRequest res) {
			Session ss = (Session) res.getSession().getAttribute("userLogin");
			int stId =storeService.getstoreIdByUserId(ss.getUserId());
			 orm = myOrderService(stId);
			ModelAndView mv = new ModelAndView("center/ordermanager");
			mv.addObject("orderInfo", orm);
			return mv;
		}*/

	/**
	 * ユーザID によって商品取得(店舗)
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/center/myproducts")
	public ModelAndView getProByid(HttpServletRequest request, ProductDto product) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("/center/myproducts");
		List<ProductDto> pros = productService.getProByUserId(ss.getUserId());
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

		List<UserDeliveryDto> usersadd = userDeliveryService.getAllUserDelivery();
		ModelAndView mv = new ModelAndView("center/userTodokeList");
		mv.addObject("useradd", usersadd);
		return mv;
	}

	//店舗管理(ADMIN)
	@RequestMapping("/center/storemanger")
	public ModelAndView storeList() {

		ModelAndView mv = new ModelAndView("center/storemanger");
		List<StoreDto> storeList = storeService.getAllStore();
		mv.addObject("storeList", storeList);
		return mv;
	}

	/**
	 * 商品管理(ADMIN)
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/center/productmanger")
	public ModelAndView getAllpro(HttpServletRequest request, ProductDto product) {
		ModelAndView mv = new ModelAndView("/center/productmanger");
		List<ProductDto> pros = productService.getAllPro();
		mv.addObject("products", pros);
		return mv;

	}
}
