package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * 新規商品登録
	 * @param product
	 */
	public void createProduct(HttpServletRequest request, ProductDto product) {

		Session session = (Session) request.getSession().getAttribute("userLogin");
		ProductEntity prott = new ProductEntity();

		//商品id
		prott.setProductId(productRepository.getID());
		prott.setUserId(session.getUserId());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		prott.setProductImg(Url.SRC + product.getProductImg());
		prott.setProductContents(product.getProductContents());
		prott.setMaker(product.getMaker());
		prott.setStatus(MsgContents.STATUS_SYUPPIN);
		productRepository.save(prott);

	}

	/**
	 * ユーザIdによって商品情報を取得
	 * @param request
	 * @return products
	 */
	public List<ProductDto> getProByUseId(HttpServletRequest request) {

		Session session = (Session) request.getSession().getAttribute("userLogin");
		List<ProductEntity> product = productRepository.findByUserIdOrderByProductIdAsc(session.getUserId());
		List<ProductDto> products = new ArrayList<>();
		if (product != null) {
			for (ProductEntity pro : product) {
				ProductDto pr = new ProductDto();
				pr.setProductId(pro.getProductId());
				pr.setProductImg(pro.getProductImg());
				pr.setProductName(pro.getProductName());
				pr.setProductContents(pro.getProductContents());
				pr.setProductPrice(pro.getProductPrice());
				pr.setProductContents(pro.getProductContents());
				pr.setMaker(pro.getMaker());
				pr.setStatus(pro.getStatus());
				products.add(pr);

			}
		} else {
			products = null;
		}
		return products;
	}

	/**
	 * すべての商品を取得(AMDIN)
	 * @return
	 */
	public List<ProductDto> getAllPro() {

		List<ProductEntity> prolist = productRepository.findAllByOrderByProductIdAsc();
		List<ProductDto> products = new ArrayList<ProductDto>();
		if (prolist != null) {
			for (ProductEntity pro : prolist) {
				ProductDto product = new ProductDto();
				product.setProductId(pro.getProductId());
				product.setProductImg(pro.getProductImg());
				product.setProductName(pro.getProductName());
				product.setProductContents(pro.getProductContents());
				product.setProductPrice(pro.getProductPrice());
				product.setProductContents(pro.getProductContents());
				product.setStatus(pro.getStatus());
				products.add(product);
			}
		} else {
			products = null;
		}
		return products;
	}

	/**
	 * 商品IDによって商品更新
	 * @param request
	 * @param product
	 */
	public void updateProduct(HttpServletRequest request, ProductDto product, int id) {

		Session session = (Session) request.getSession().getAttribute("userLogin");

		ProductEntity oldpro = productRepository.findByProductId(id);
		ProductEntity prott = new ProductEntity();

		//商品id
		prott.setProductId(oldpro.getProductId());
		prott.setUserId(session.getUserId());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		if (product.getProductImg().equals("")) {
			prott.setProductImg(oldpro.getProductImg());
		} else {
			prott.setProductImg(Url.SRC + product.getProductImg());

		}
		prott.setProductContents(product.getProductContents());
		prott.setMaker(product.getMaker());
		prott.setStatus(oldpro.getStatus());
		productRepository.save(prott);

	}

	/**
	 * 商品IDによって商品削除
	 * @param product
	 */
	public void deletePro(int productId) {

		productRepository.deleteById(productId);
	}

	/**
	 * 商品出品を停止
	 * @param proid
	 * @return
	 */
	public void stopStatus(int productId) {

		ProductEntity prott = new ProductEntity();
		ProductEntity pro = productRepository.findByProductId(productId);
		if (pro.getStatus().equals(MsgContents.STATUS_SYUPPIN)) {
			prott.setStatus(MsgContents.STATUS_STOP);
			prott.setUserId(pro.getUserId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setMaker(pro.getMaker());
			prott.setProductContents(pro.getProductContents());
			prott.setProductId(pro.getProductId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setProductName(pro.getProductName());
			prott.setProductImg(pro.getProductImg());
			productRepository.save(prott);
		}

	}

	/**
	 * 商品出品を回復
	 * @param proid
	 * @return
	 */
	public void recovery(int productId) {

		ProductEntity prott = new ProductEntity();
		ProductEntity pro = productRepository.findByProductId(productId);
		if (pro.getStatus().equals(MsgContents.STATUS_STOP)) {
			prott.setStatus(MsgContents.STATUS_SYUPPIN);
			prott.setUserId(pro.getUserId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setMaker(pro.getMaker());
			prott.setProductContents(pro.getProductContents());
			prott.setProductId(pro.getProductId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setProductName(pro.getProductName());
			prott.setProductImg(pro.getProductImg());

			productRepository.save(prott);

		}

	}

	/**
	 * 商品ＩＤによる商品詳細を取得する
	 * @param id
	 * @return 該当ＩＤの商品詳細
	 */
	public ProductDto getProByProductId(int id) {
		ProductEntity productdtail = productRepository.findByProductId(id);
		ProductDto prodto = new ProductDto();
		prodto.setProductId(id);
		prodto.setProductImg(productdtail.getProductImg());
		prodto.setProductName(productdtail.getProductName());
		prodto.setProductPrice(productdtail.getProductPrice());
		prodto.setProductContents(productdtail.getProductContents());
		prodto.setMaker(productdtail.getMaker());

		return prodto;

	}

	/**
	 * キーワード検索
	 * @param keyword
	 * @return prolike 商品情報
	 */
	public List<ProductDto> keyLike(String keyword) {
		List<ProductEntity> likes = productRepository.getLikePro(keyword);
		List<ProductDto> prolike = new ArrayList<>();
		for (ProductEntity p : likes) {
			ProductDto pro = new ProductDto();
			pro.setProductId(p.getProductId());
			pro.setProductName(p.getProductName());
			pro.setProductImg(p.getProductImg());
			pro.setProductContents(p.getProductContents());
			pro.setProductPrice(p.getProductPrice());
			pro.setMaker(p.getMaker());
			prolike.add(pro);

		}
		return prolike;
	}

}
