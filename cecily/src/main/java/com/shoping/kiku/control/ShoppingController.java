package com.shoping.kiku.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingController {
	
	//ホームページ
		@RequestMapping({ "/shopping", "/" })
		public ModelAndView shopping(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("shopping");
			return mv;
		}

}
