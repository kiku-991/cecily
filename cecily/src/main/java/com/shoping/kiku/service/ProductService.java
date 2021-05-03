package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.FavoriteProEntity;
import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.entity.StoreEntity;
import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.repository.FavoriteProRepository;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.repository.StoreRepository;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Status;
import com.shoping.kiku.until.Url;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	FavoriteProRepository favoriteProRepository;

	//homepage 

	public List<FavoProDto> getAllProductByStatusNormal(HttpServletRequest res) {
		Session session = (Session) res.getSession().getAttribute("userLogin");
		List<FavoProDto> pros = new ArrayList<>();

		//ユーザ登録なし
		if (session == null) {
			List<ProductEntity> normal = productRepository.getNormalPro();
			for (ProductEntity pro : normal) {
				FavoProDto pr = new FavoProDto();
				pr.setProductId(pro.getProductId());
				pr.setProductImg(pro.getProductImg());
				pr.setProductName(pro.getProductName());
				pr.setProductContents(pro.getProductContents());
				pr.setProductPrice(pro.getProductPrice());
				pr.setProductContents(pro.getProductContents());
				pr.setMaker(pro.getMaker());
				pr.setStatus(pro.getStatus());
				//pr.setStock(pro.getStock());
				//pr.setStoreId(pro.getStoreId());

				pros.add(pr);
			}
			return pros;

		} else {

			//すべての商品自分の気に入り商品を検索
			List<FavoriteProEntity> favorites = favoriteProRepository.getProsWithFavByUserId(session.getUserId());

			for (FavoriteProEntity fvp : favorites) {
				FavoProDto pr = new FavoProDto();
				if (fvp.getUserId() != null) {
					pr.setUserId(fvp.getUserId());
				}
				pr.setProductId(fvp.getProductId());
				pr.setProductImg(fvp.getProductImg());
				pr.setProductName(fvp.getProductName());
				pr.setProductContents(fvp.getProductContents());
				pr.setProductPrice(fvp.getProductPrice());
				pr.setProductContents(fvp.getProductContents());
				pr.setMaker(fvp.getMaker());
				pr.setStatus(fvp.getStatus());
				pros.add(pr);
			}
		}
		return pros;

	}

	/**
	 * 新規商品登録
	 * @param product
	 */
	public void createProduct(int userId, ProductDto product) {

		ProductEntity prott = new ProductEntity();
		StoreEntity store = storeRepository.findByUserId(userId);
		//商品id
		prott.setProductId(productRepository.getID());
		prott.setStoreId(store.getStoreId());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		prott.setProductImg(Url.SRC + product.getProductImg());
		prott.setProductContents(product.getProductContents());
		prott.setMaker(product.getMaker());
		prott.setStatus(Status.PRODUCTIN);
		prott.setStock(product.getStock());

		productRepository.save(prott);

	}

	/**
	 * ユーザIdによって商品情報を取得
	 * @param request
	 * @return products
	 */
	public List<ProductDto> getProByUserId(int userId) {

		List<ProductEntity> product = productRepository.findByStoreIdOrderByProductIdAsc(userId);
		List<ProductDto> products = new ArrayList<>();
		if (product != null) {
			for (ProductEntity pro : product) {
				ProductDto pr = new ProductDto();
				pr.setProductId(pro.getProductId());
				pr.setStoreId(pro.getStoreId());
				pr.setProductImg(pro.getProductImg());
				pr.setProductName(pro.getProductName());
				pr.setProductContents(pro.getProductContents());
				pr.setProductPrice(pro.getProductPrice());
				pr.setProductContents(pro.getProductContents());
				pr.setMaker(pro.getMaker());
				pr.setStatus(pro.getStatus());
				pr.setStock(pro.getStock());

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
				product.setStoreId(pro.getStoreId());
				product.setProductImg(pro.getProductImg());
				product.setProductName(pro.getProductName());
				product.setProductContents(pro.getProductContents());
				product.setProductPrice(pro.getProductPrice());
				product.setProductContents(pro.getProductContents());
				product.setStatus(pro.getStatus());
				product.setMaker(pro.getMaker());
				product.setStock(pro.getStock());

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
	public void updateProduct(ProductDto product, int id) {

		ProductEntity oldpro = productRepository.findByProductId(id);
		ProductEntity prott = new ProductEntity();

		//商品id
		prott.setProductId(oldpro.getProductId());
		prott.setStoreId(oldpro.getStoreId());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		if (product.getProductImg().equals("")) {
			prott.setProductImg(oldpro.getProductImg());
		} else {
			prott.setProductImg(Url.SRC + product.getProductImg());

		}
		prott.setProductContents(product.getProductContents());
		prott.setMaker(product.getMaker());
		prott.setStock(product.getStock());
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
		if (pro.getStatus() == Status.PRODUCTIN) {
			prott.setStatus(Status.PRODUCTSTOP);
			prott.setStoreId(pro.getStoreId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setMaker(pro.getMaker());
			prott.setProductContents(pro.getProductContents());
			prott.setProductId(pro.getProductId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setProductName(pro.getProductName());
			prott.setProductImg(pro.getProductImg());
			prott.setStock(pro.getStock());
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
		if (pro.getStatus() == Status.PRODUCTSTOP) {
			prott.setStatus(Status.PRODUCTIN);
			prott.setStoreId(pro.getStoreId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setMaker(pro.getMaker());
			prott.setProductContents(pro.getProductContents());
			prott.setProductId(pro.getProductId());
			prott.setProductPrice(pro.getProductPrice());
			prott.setProductName(pro.getProductName());
			prott.setProductImg(pro.getProductImg());
			prott.setStock(pro.getStock());
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
		prodto.setStoreId(productdtail.getStoreId());
		prodto.setProductImg(productdtail.getProductImg());
		prodto.setProductName(productdtail.getProductName());
		prodto.setProductPrice(productdtail.getProductPrice());
		prodto.setProductContents(productdtail.getProductContents());
		prodto.setMaker(productdtail.getMaker());
		prodto.setStock(productdtail.getStock());
		//気に入り
		prodto.setUserId(0);
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

	//get Stock 
	public int getStock(int proId) {
		int stock = productRepository.findByProductId(proId).getStock();
		return stock;
	}

	public int getTotal(int proId) {
		ProductEntity stopro = productRepository.findByProductId(proId);
		int total = stopro.getProductPrice() * stopro.getStock();
		return total;
	}

}
