package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.OrderProInfoDto;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.service.MyOrderService;
import com.shoping.kiku.service.PayMentService;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.until.Session;

@Controller
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;

	@Autowired
	UserDeliveryService userDeliveryService;

	@Autowired
	PayMentService payMentService;

	//我的订单
	@RequestMapping("/center/myorder")
	public ModelAndView myorder(HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/myorder");
		//订单
		List<OrderProInfoDto> order = myOrderService.getOrderItemInfoByUserId(ss.getUserId());
		//届け住所
		UserDeliveryDto add = userDeliveryService.getDefaultadd(ss.getUserId());
		/*//支払い状態
		List<PayInfoDto> payInfo = payMentService.getPayInfoByUserId(ss.getUserId());*/
		mv.addObject("myoder", order);
		mv.addObject("addresss", add);
		
		return mv;

	}

	//支払い（AJAX判断支払い方法）
	@RequestMapping("/getPaymethod")
	@ResponseBody
	public int paymethond(@RequestBody String value) {
		System.out.println(value);
		String method = value.replace("method=", "");

		int y = Integer.parseInt(method);
		return y;

	}

	//支付宝页面·
	@RequestMapping("/aripay/{id}")
	public ModelAndView ariPay(@PathVariable("id") int productid, HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("paymethod/aripay");
		System.out.println(productid);
		int price = myOrderService.getPayPrice(ss.getUserId(), productid);
		mv.addObject("price", price);

		mv.addObject("productId", productid);
		return mv;

	}

	//クレジットカード画面
	@RequestMapping("/creditpay")
	public ModelAndView creditPay() {
		ModelAndView mv = new ModelAndView("paymethod/creditpay");
		return mv;

	}

}
