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

import com.shoping.kiku.object.OrderInfoByUserIdDto;
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

	/**
	 * マイ　オーダー
	 * @param res
	 * @return
	 */
	@RequestMapping("/center/myorder")
	public ModelAndView getmyorder(HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/myorder");
		//订单ID
		List<OrderInfoByUserIdDto> orderId = myOrderService.getOrderInfoByUserId(ss.getUserId());
		//届け住所
		UserDeliveryDto add = userDeliveryService.getDefaultadd(ss.getUserId());
		mv.addObject("orderId", orderId);
		mv.addObject("addresss", add);
		return mv;

	}

	//支払い（AJAX判断支払い方法）
	@RequestMapping("/getPaymethod")
	@ResponseBody
	public int paymethond(@RequestBody String value) {
		System.out.println(value);
		String method = value.replace("method=", "");

		int paymenthod = Integer.parseInt(method);
		return paymenthod;

	}

	//支付宝页面·
	@RequestMapping("/aripay/{id}")
	public ModelAndView ariPay(@PathVariable("id") String orderId, HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("paymethod/aripay");
		int price = myOrderService.getPayPrice(ss.getUserId(), orderId);
		mv.addObject("price", price);

		mv.addObject("productId", orderId);
		return mv;

	}

	//クレジットカード画面
	@RequestMapping("/creditpay/{id}")
	public ModelAndView creditPay(@PathVariable("id") String orderId, HttpServletRequest res) {
		ModelAndView mv = new ModelAndView("paymethod/creditpay");
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		int price = myOrderService.getPayPrice(ss.getUserId(), orderId);
		mv.addObject("price", price);
		mv.addObject("productId", orderId);
		return mv;

	}
	
	
	//取消オーダー
	@RequestMapping("/center/myorder/cancelorder/{id}")
	public String cancelOrder(@PathVariable("id") String orderId) {
		
		myOrderService.cancelOrder(orderId);
		
		return "redirect:/center/myorder";
	}
	
	
	

}
