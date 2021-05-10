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
import com.shoping.kiku.object.ShippingDto;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.service.MyOrderService;
import com.shoping.kiku.service.PayMentService;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

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
	@RequestMapping(Url.MYORDER)
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

	/**
	 * 支払い（AJAX判断支払い方法）
	 * @param value
	 * @return
	 */
	@RequestMapping(Url.PAYMETHOD)
	@ResponseBody
	public int paymethond(@RequestBody String value) {
		System.out.println(value);
		String method = value.replace("method=", "");

		int paymenthod = Integer.parseInt(method);
		return paymenthod;

	}

	/**
	 * 支付宝页面·
	 * @param orderId
	 * @param res
	 * @return
	 */
	@RequestMapping(Url.ARIPAYID)
	public ModelAndView ariPay(@PathVariable("id") String orderId, HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("paymethod/aripay");
		int price = myOrderService.getPayPrice(ss.getUserId(), orderId);
		mv.addObject("price", price);

		mv.addObject("productId", orderId);
		return mv;

	}

	/**
	 * クレジットカード画面
	 * @param orderId
	 * @param res
	 * @return
	 */
	@RequestMapping(Url.CREDITPAYID)
	public ModelAndView creditPay(@PathVariable("id") String orderId, HttpServletRequest res) {
		ModelAndView mv = new ModelAndView("paymethod/creditpay");
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		int price = myOrderService.getPayPrice(ss.getUserId(), orderId);
		mv.addObject("price", price);
		mv.addObject("productId", orderId);
		return mv;

	}

	
	/**
	 * 收货并付款
	 * CYAKUHARAI
	 */
	
	@RequestMapping(Url.RECEIPTANDPAY)
	public String receivedAndPay(@PathVariable ("id")String orderId) {
		myOrderService.receivedAndPay( orderId);
		return "redirect:/center/myorder";
	}
	
	
	
	/**
	 * オーダー取消
	 * @param orderId
	 * @return
	 */
	@RequestMapping(Url.ORDERCANCEL)
	public String cancelOrder(@PathVariable("id") String orderId) {

		myOrderService.cancelOrder(orderId);

		return "redirect:/center/myorder";
	}

	/**
	 * 商品発送(store)
	 * @param orderId
	 * @param ship
	 * @param res
	 * @return
	 */
	@RequestMapping(Url.SHIPPRODUCT)
	public String proShip(@PathVariable("id") String orderId, ShippingDto ship, HttpServletRequest res) {

		myOrderService.productShip(orderId, ship);

		return "redirect:/center/ordermanager";
	}

	/**
	 * 收貨 訂單已完成(user)
	 * @param orderId
	 * @return
	 */
	@RequestMapping(Url.RECEIPTPRODUCT)
	public String completeOrder(@PathVariable("id") String orderId) {
		myOrderService.completeOrder(orderId);
		return "redirect:/center/myorder";
	}

}
