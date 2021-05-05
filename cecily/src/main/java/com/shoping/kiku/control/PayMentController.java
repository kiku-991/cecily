package com.shoping.kiku.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoping.kiku.service.PayMentService;
import com.shoping.kiku.until.Session;

@Controller
public class PayMentController {

	@Autowired
	PayMentService payMentService;
	
	
	//AriPay 
	@RequestMapping("/aripay/pay/{id}")
	public String ariPay(@PathVariable ("id")String orderId,HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		payMentService.creAriPayForm(ss.getUserId(), orderId);
		return "redirect:/center/myorder";
	}
	
	
	
	//クレジットカード支払い
	@RequestMapping("/creditpay/pay/{id}")
	public String CreditPay(@PathVariable ("id")String orderId,HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		payMentService.creCreditPayForm(ss.getUserId(), orderId);
		return "redirect:/center/myorder";
	}
}
