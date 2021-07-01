package com.shoping.kiku.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.service.TestService;

@Controller
public class TestProductController {

	@Autowired
	TestService testService;
	
	 @GetMapping("/pages/players")
	    public String index(Pageable pageable, Model model) {

	        Page<ProductDto> productPage = testService.findAll(pageable);

	        model.addAttribute("page", productPage);
	        model.addAttribute("products", productPage.getContent());

	        return "page/index";
	    }
	
}
