package com.shoping.kiku.control;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.service.FavoriteService;
import com.shoping.kiku.service.MyCartService;
import com.shoping.kiku.service.ProductService;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Controller
public class ShoppingController {

	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	MyCartService myCartService;

	/**
	 * ホームページ
	 * @param request
	 * @return
	 */
	@RequestMapping({ Url.SHOPPING, Url.INDEX })
	public ModelAndView shopping(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopping");

		Session ss = (Session) request.getSession().getAttribute("userLogin");
		int count = 0;
		if (ss != null) {
			count = myCartService.getcountInCartByUserId(ss.getUserId());
		}
		List<FavoProDto> product = productService.getAllProductByStatusNormal(request);
		mv.addObject("products", product);
		mv.addObject("count", count);
		return mv;
	}

	/**
	 * 商品名検索
	 * @param productName
	 * @return
	 */

	@RequestMapping("/search")
	public ModelAndView keyword(String k, Pageable page) {
		ModelAndView mv = new ModelAndView("keywordsearch");
		int size = 10;
		List<ProductDto> prolike = productService.keyLike(k, 0, size);
		int count = productService.getCountByKey(k);
		int pageCount = productService.pageCount(k, 0, size);
		mv.addObject("prolike", prolike);
		mv.addObject("pageCount", pageCount);
		mv.addObject("total", count);
		mv.addObject("searchName", k);
		return mv;

	}
	/**
	 * ページ分け
	 * @param productName
	 * @param p
	 * @return
	 */
	@RequestMapping("/searchKeyword/{s}")
	public ModelAndView keywordN(@PathVariable("s") String productName, @RequestParam int p) {

		ModelAndView mv = new ModelAndView("keywordsearch");
		int size = 10;
		List<ProductDto> prolike = productService.keyLike(productName, p - 1, size);
		int count = productService.getCountByKey(productName);
		int pageCount = productService.pageCount(productName, p, size);
		mv.addObject("prolike", prolike);
		mv.addObject("pageCount", pageCount);
		mv.addObject("total", count);
		mv.addObject("searchName", productName);
		return mv;
	}

	/**
	 * 検索(区块)
	 * @param productName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(Url.KEYWORDPROPRICE)
	public ModelAndView keywordOrderByPrice(@RequestBody HashMap<String, String> map, Pageable page) {
		String proname = map.get("name");
		String id = map.get("id");
		int liid = Integer.parseInt(id);

		List<ProductDto> prolike = null;
		if (liid == 1) {
			//総合
			prolike = productService.keyLike(proname, page);
		} else if (liid == 2) {
			//価格
			prolike = productService.keyLikeOrderByPrice(proname);

		} else {
			//時間
			prolike = productService.keyLikeOrderByTime(proname);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("prolike", prolike);
		return mv;

	}

	/**
	 * 気に入り一覧
	 * @param request
	 * @return
	 */
	@RequestMapping(Url.SHOPFAVPRITE)
	public ModelAndView favorite(HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("center/favorite");
		List<FavoProDto> favorites = favoriteService.getFavorite(ss.getUserId());
		mv.addObject("favoritePros", favorites);
		return mv;
	}

}
