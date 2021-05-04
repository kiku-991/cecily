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
	
	@RequestMapping("/aripay/pay/{id}")
	public String ariPay(@PathVariable ("id")int productId,HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		payMentService.crePayForm(ss.getUserId(), productId);
		return "redirect:/center/myorder";
	}
	
	
	
}
