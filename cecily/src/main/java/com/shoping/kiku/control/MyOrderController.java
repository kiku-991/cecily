package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.OrderProInfoDto;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.service.MyOrderService;
import com.shoping.kiku.service.UserDeliveryService;
import com.shoping.kiku.until.Session;

@Controller
public class MyOrderController {

	@Autowired
	MyOrderService myOrderService;

	@Autowired
	UserDeliveryService userDeliveryService;
	//我的订单
	@RequestMapping("/center/myorder")
	public ModelAndView myorder(HttpServletRequest res) {
		Session ss = (Session) res.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/myorder");
		List<OrderProInfoDto> order = myOrderService.getOrderItemInfoByUserId(ss.getUserId());
		UserDeliveryDto add = userDeliveryService.getDefaultadd(ss.getUserId());
		mv.addObject("myoder", order);
		mv.addObject("addresss",add);
		return mv;

	}

}
