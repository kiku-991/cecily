package com.shoping.kiku.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.service.FavoriteService;
import com.shoping.kiku.service.MyCartService;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.until.Session;

@Controller
public class ShoppingController {

	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	MyCartService myCartService;

	//ホームページ
	@RequestMapping({ "/shopping", "/" })
	public ModelAndView shopping(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopping");
		
		Session ss = (Session)request.getSession().getAttribute("userLogin");
		int count = 0;
		if(ss!=null) {
			count=myCartService.getcountInCartByUserId(ss.getUserId());
		}
		List<FavoProDto> product = productService.getAllProductByStatusNormal(request);
		mv.addObject("products", product);
		mv.addObject("count", count);
		return mv;
	}

	//キーワード検索
	@RequestMapping("/shopping/keywordSearch")
	public ModelAndView keyword(String productName) {
		ModelAndView mv = new ModelAndView("keywordsearch");
		List<ProductDto> prolike = productService.keyLike(productName);
		mv.addObject("prolike", prolike);
		return mv;

	}

	//気に入り
	@RequestMapping("/shopping/favorite")
	public ModelAndView favorite(HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/favorite");
		List<FavoProDto> favorites = favoriteService.getFavorite(ss.getUserId());
		mv.addObject("favoritePros", favorites);
		return mv;
	}

}
