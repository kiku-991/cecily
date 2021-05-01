package com.shoping.kiku.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.service.ProductService;
@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	/*@Autowired
	FavoriteService favoriteService;*/

	/**
	 * 商品新規登録
	 * @param request
	 * @param product
	 * @return productmanger
	 */
	@RequestMapping(value = "/center/productmanger", method = RequestMethod.POST)
	public String creProduct(HttpServletRequest request, ProductDto product) {

		productService.createProduct(request, product);

		return "redirect:/center/productmanger";
	}


	/**
	 * 商品IDによって商品編集
	 * @param pro
	 * @return productmanger
	 */
	@PostMapping(value = "/center/product/edit/{id}")
	public String updateProById(@PathVariable("id") int id, HttpServletRequest request, ProductDto pro) {
		productService.updateProduct(request, pro, id);
		return "redirect:/center/productmanger";
	}

	/**
	 * 商品IDによって商品削除
	 * @param pro
	 * @return productmanger
	 */
	@RequestMapping(value = "/center/productmanger/deleteProduct/{id}")
	public String deleteProById(@PathVariable("id") int id) {
		productService.deletePro(id);
		return "redirect:/center/productmanger";
	}

	/**
	 * 商品IDによって商品を中止
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = "/center/productmanger/stop/{id}")
	public String stopStatusById(@PathVariable("id") int id) {
		productService.stopStatus(id);
		return "redirect:/center/productmanger";

	}

	/**
	 * 商品IDによって中止商品を回復
	 * @param id
	 * @return productmanger
	 */
	@RequestMapping(value = "/center/productmanger/recovery/{id}")
	public String recoveryProById(@PathVariable("id") int id) {
		productService.recovery(id);
		return "redirect:/center/productmanger";
	}

	/**
	 * 商品詳細画面に遷移
	 * @return 商品詳細画面
	 */
	/*@RequestMapping(value = "/productDetails/{id}")
	public ModelAndView proDetails(@PathVariable("id") int id, HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		ModelAndView mv = new ModelAndView("productdetails");
		//登録あり
		if (ss != null) {
			//登録あり 商品と気に入り情報取得
			FavoriteDto fap =favoriteService.getProByUserIdAndProId(ss.getId(),id);
			mv.addObject("product", fap);
			
		} else {
			//登録なし 商品情報取得
			ProductDto pros = productService.getProByProductId(id);
			mv.addObject("product", pros);
		}
		return mv;
	}*/


}
