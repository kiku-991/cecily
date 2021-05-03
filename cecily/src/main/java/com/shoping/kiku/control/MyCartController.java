package com.shoping.kiku.control;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.MyOrderDto;
import com.shoping.kiku.object.ProductInCartDto;
import com.shoping.kiku.service.MyCartService;
import com.shoping.kiku.service.MyOrderService;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.until.Session;

@Controller
public class MyCartController {

	@Autowired
	MyCartService myCartService;
	
	@Autowired
	MyOrderService myOrderService;
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/shopping/myCart", method = RequestMethod.GET)
	public ModelAndView myCart(HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/mycart");

		List<ProductInCartDto> pros = myCartService.getAllProuctInCartByUserId(ss.getUserId());
		int count = myCartService.getcountInCartByUserId(ss.getUserId());

		int amount = myCartService.getCheckedPriceInCart(ss.getUserId());
		mv.addObject("proAndCart", pros);
		mv.addObject("count", count);
		mv.addObject("amount", amount);
		return mv;

	}

	@RequestMapping("/addMyCart")
	@ResponseBody
	public int add(@RequestBody String proudtId, HttpServletRequest request, Model model) {

		Session session = (Session) request.getSession().getAttribute("userLogin");
		String id = proudtId.replace("id=", "");

		int proId = Integer.valueOf(id);
		myCartService.addCart(proId, session.getUserId());
		int count = myCartService.getcountInCartByUserId(session.getUserId());
		model.addAttribute("count", count);

		return count;

	}

	@PostMapping("/myCart/deleteCart/{productId}")
	public String deleteSingleCart(@PathVariable("productId") int productId, HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		myCartService.deleteCart(ss.getUserId(), productId);
		return "redirect:/shopping/myCart";
	}

	/*//'+'ボタンを押下
	@RequestMapping("/increse")
	@ResponseBody
	public HashMap<String,Integer> incre(@RequestBody String proudtId, HttpServletRequest request) {
		String id = proudtId.replace("id=", "");
		int proId = Integer.valueOf(id);
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		int quantity =0;
		int total=0;
		int stock =productService.getStock(proId);
		HashMap<String,Integer> map = new HashMap<>();
		int stotal =productService.getTotal(proId);
		//ストック超えない
		if(quantity<=stock) {
			total = myCartService.getTotalByUserIdAndProductId(ss.getUserId(), proId);
			quantity =myCartService.updateInc(ss.getUserId(), proId);
			map.put("quantity", quantity);
			map.put("total", total);
		}else {
			total=stotal;
			quantity=stock;
			map.put("quantity", quantity);
			map.put("total", total);
		}
		return map;
	}*/
	
	//'+'ボタンを押下
	@RequestMapping("/increse")
	@ResponseBody
	public int incre(@RequestBody String proudtId, HttpServletRequest request) {
		String id = proudtId.replace("id=", "");
		int proId = Integer.valueOf(id);
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		myCartService.updateInc(ss.getUserId(), proId);
		int total = myCartService.getTotalByUserIdAndProductId(ss.getUserId(), proId);

		return total;
	}

	//'-'ボタンを押下
	@RequestMapping("/descre")
	@ResponseBody
	public int descre(@RequestBody String proudtId, HttpServletRequest request) {
		String id = proudtId.replace("id=", "");
		int proId = Integer.valueOf(id);
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		int i = myCartService.updateDec(ss.getUserId(), proId);
		int total =0;
		if(i==0) {
			total =0;
		}else {
			total= myCartService.getTotalByUserIdAndProductId(ss.getUserId(), proId);
		}
		return total;
	}

	//checkbox選択され処理
	@RequestMapping("/changeStatus")
	@ResponseBody
	public HashMap<String, Integer> statusChange(@RequestBody String proudtId, HttpServletRequest request) {
		String id = proudtId.replace("id=", "");
		int proId = Integer.valueOf(id);
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		myCartService.updateCheckStatus(ss.getUserId(), proId);
		int quantity = myCartService.getCheckedQuantityInCart(ss.getUserId());

		int amount = myCartService.getCheckedPriceInCart(ss.getUserId());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("quantity", quantity);
		map.put("amount", amount);

		return map;
	}

	//全選処理
	@RequestMapping("/changeAllStatus")
	@ResponseBody
	public HashMap<String, Integer> statusAllChange(@RequestBody String allcheck, HttpServletRequest request) {
		System.out.println(allcheck);
		String check = allcheck.replace("flg=", "");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		//全選処理
		if (check.equals("true")) {
			//該当ユーザ買い物かごの状態を１に設定
			myCartService.udateAllChecked(ss.getUserId());
			int quantity = myCartService.getCheckedQuantityInCart(ss.getUserId());
			int amount = myCartService.getCheckedPriceInCart(ss.getUserId());
			map.put("quantity", quantity);
			map.put("amount", amount);
		} else {
			//全選外す処理
			map.put("quantity", 0);
			map.put("amount", 0);
		}

		return map;
	}

	

	//買い物かご画面に決算ボタンを押下処理 buy 画面に遷移
	@RequestMapping("/mycart/buy")
	public ModelAndView buy(HttpServletRequest request) throws Exception {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/buy");
		List<ProductInCartDto> proBuy = myCartService.getCheckedProductInCart(ss.getUserId());

		int amount = myCartService.getCheckedPriceInCart(ss.getUserId());
		/*UserInfoDto userInfo = userInfoService.getUserInfo(request);*/
		// ユーザ状態を判断
		/*if (userInfo != null) {
			mv.addObject("userInfo", userInfo);
		
		} else {
			mv.addObject("userInfo", null);
		}*/
		mv.addObject("probuy", proBuy);
		mv.addObject("amount", amount);

		return mv;
	}
	
	//提交订单之后跳转页面
	@RequestMapping("/order/add")
	public ModelAndView buyAdd(MyOrderDto order, HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("orderfinish");
		myOrderService.createOrderForm(order, ss.getUserId());
		//提交订单时チェックされた商品を買い物かごから削除
		myCartService.deleteCheckedPro(ss.getUserId());
		return mv;
	}
}
