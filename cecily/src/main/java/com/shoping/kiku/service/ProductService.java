package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.FavoriteProEntity;
import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.entity.ProductImgEntity;
import com.shoping.kiku.entity.StoreEntity;
import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.object.ProductImgDto;
import com.shoping.kiku.repository.FavoriteProRepository;
import com.shoping.kiku.repository.ProductImgRepository;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.repository.StoreRepository;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Status;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	FavoriteProRepository favoriteProRepository;

	@Autowired
	ProductImgRepository productImgRepository;


	/**
	 * ホームページの商品表示(個人気に入り情報が含まれる)
	 * @param res
	 * @return
	 */
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
	 * @param userId
	 * @param product
	 * @param list
	 */
	public void createProduct(int userId, ProductDto product, List<String> list) {

		ProductEntity prott = new ProductEntity();
		StoreEntity store = storeRepository.findByUserId(userId);
		//商品id
		prott.setProductId(productRepository.getID());
		prott.setStoreId(store.getStoreId());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		prott.setProductContents(product.getProductContents());
		prott.setMaker(product.getMaker());
		prott.setStatus(Status.PRODUCTIN);
		prott.setStock(product.getStock());
		prott.setCreateTime(new Timestamp(System.currentTimeMillis()));
		prott.setProductImg(list.get(0));
		productRepository.save(prott);
		//商品写真
		for (int i = 0; i < list.size(); i++) {
			ProductImgEntity productImg = new ProductImgEntity();
			productImg.setProductId(prott.getProductId());
			productImg.setProductImg(list.get(i));
			productImgRepository.save(productImg);

		}

	}

	/**
	 * ユーザIdによって商品情報を取得
	 * @param request
	 * @return products
	 */
	public List<ProductDto> getProByUserId(int userId) {

		List<ProductEntity> product = productRepository.findByStoreIdOrderByProductIdAsc(userId);
		List<ProductDto> products = new ArrayList<>();
		try {
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
					pr.setCreateTime(pro.getCreateTime());

					products.add(pr);

				}
			} else {
				//products = null;
				throw new NullPointerException();
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
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
				product.setCreateTime(pro.getCreateTime());
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
	public void updateProduct(ProductDto product, int id, String img) {

		ProductEntity oldpro = productRepository.findByProductId(id);
		ProductEntity prott = new ProductEntity();

		//商品id
		prott.setProductId(oldpro.getProductId());
		prott.setStoreId(oldpro.getStoreId());
		prott.setCreateTime(oldpro.getCreateTime());
		prott.setProductName(product.getProductName());
		prott.setProductPrice(product.getProductPrice());
		if (img.equals("")) {
			prott.setProductImg(oldpro.getProductImg());
		} else {
			prott.setProductImg(img);

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
			prott.setCreateTime(pro.getCreateTime());

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
			prott.setCreateTime(pro.getCreateTime());
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
		List<ProductImgEntity> proImg = productImgRepository.findByProductId(id);
		List<ProductImgDto> imgs = new ArrayList<>();
		ProductDto pros = new ProductDto();
		pros.setProductId(id);
		pros.setStoreId(productdtail.getStoreId());
		pros.setProductImg(productdtail.getProductImg());
		pros.setProductName(productdtail.getProductName());
		pros.setProductPrice(productdtail.getProductPrice());
		pros.setProductContents(productdtail.getProductContents());
		pros.setMaker(productdtail.getMaker());
		pros.setStock(productdtail.getStock());
		pros.setCreateTime(productdtail.getCreateTime());
		//気に入り
		pros.setUserId(0);

		for (ProductImgEntity img : proImg) {
			ProductImgDto proImgDto = new ProductImgDto();
			proImgDto.setProductImgs(img.getProductImg());
			imgs.add(proImgDto);
		}
		pros.setImglist(imgs);
		return pros;

	}

	/**
	 * 商品名検索　総合検索
	 * @param keyword
	 * @return prolike 商品情報
	 */
	public List<ProductDto> keyLike(String proName, int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ProductEntity> likes = productRepository.getLikeProByProname(proName, paging);
		List<ProductDto> pro = new ArrayList<>();

		//method 1
		likes.forEach(ProductEntity -> {
			ProductDto product = new ProductDto();
			product.setProductName(ProductEntity.getProductName());
			product.setProductPrice(ProductEntity.getProductPrice());
			product.setMaker(ProductEntity.getMaker());
			product.setCreateTime(ProductEntity.getCreateTime());
			product.setProductContents(ProductEntity.getProductContents());
			product.setProductId(ProductEntity.getProductId());
			product.setProductImg(ProductEntity.getProductImg());
			product.setStatus(ProductEntity.getStatus());
			product.setStock(ProductEntity.getStock());
			product.setStoreId(ProductEntity.getStoreId());
			pro.add(product);

		});

		//method 2
		//
		//		List<ProductEntity> like = likes.getContent();
		//		for (ProductEntity pros : like) {
		//			ProductDto product = new ProductDto();
		//			product.setProductName(pros.getProductName());
		//			product.setProductPrice(pros.getProductPrice());
		//			product.setMaker(pros.getMaker());
		//			product.setCreateTime(pros.getCreateTime());
		//			product.setProductContents(pros.getProductContents());
		//			product.setProductId(pros.getProductId());
		//			product.setProductImg(pros.getProductImg());
		//			product.setStatus(pros.getStatus());
		//			product.setStock(pros.getStock());
		//			product.setStoreId(pros.getStoreId());
		//			pro.add(product);
		//
		//		}

		return pro;
	}

	public List<ProductDto> keyLike(String proName, Pageable page) {

		Page<ProductEntity> likes = productRepository.getLikeProByProname(proName, page);
		List<ProductDto> pro = new ArrayList<>();

		List<ProductEntity> like = likes.getContent();
		for (ProductEntity pros : like) {
			ProductDto product = new ProductDto();
			product.setProductName(pros.getProductName());
			product.setProductPrice(pros.getProductPrice());
			product.setMaker(pros.getMaker());
			product.setCreateTime(pros.getCreateTime());
			product.setProductContents(pros.getProductContents());
			product.setProductId(pros.getProductId());
			product.setProductImg(pros.getProductImg());
			product.setStatus(pros.getStatus());
			product.setStock(pros.getStock());
			product.setStoreId(pros.getStoreId());
			pro.add(product);

		}
		return pro;
	}

	/**
	 * 商品名検索　総合検索
	 * @param keyword
	 * @return prolike 商品情報
	 */
	@Transactional
	public Page<ProductDto> keyLikePage(String proname, Pageable page) {
		Page<ProductEntity> likes = productRepository.getLikeProByProname(proname, page);

		Page<ProductDto> prolike = likes.map(new Function<ProductEntity, ProductDto>() {
			@Override
			public ProductDto apply(ProductEntity entity) {
				ProductDto dto = new ProductDto();
				// Conversion logic
				dto.setProductName(entity.getProductName());
				dto.setProductPrice(entity.getProductPrice());
				dto.setMaker(entity.getMaker());
				dto.setCreateTime(entity.getCreateTime());
				dto.setProductContents(entity.getProductContents());
				dto.setProductId(entity.getProductId());
				dto.setProductImg(entity.getProductImg());
				dto.setStatus(entity.getStatus());
				dto.setStock(entity.getStock());
				dto.setStoreId(entity.getStoreId());

				return dto;
			}
		});
		return prolike;
	}

	/**
	 * get PageCount
	 * @param proname
	 * @param page
	 * @return
	 */
	public int pageCount(String proname, int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ProductEntity> likes = productRepository.getLikeProByProname(proname, paging);
		int totol = likes.getTotalPages();
		return totol;

	}

	/**
	 * 商品名検索OrderBy価格
	 * @param proname
	 * @return
	 */
	@Transactional
	public List<ProductDto> keyLikeOrderByPrice(String proname) {
		List<ProductEntity> likes = productRepository.getLikeProOrderPrice(proname);
		System.out.println(likes.size());
		List<ProductDto> prolike = new ArrayList<>();
		for (ProductEntity p : likes) {
			ProductDto pro = new ProductDto();
			pro.setProductId(p.getProductId());
			pro.setProductName(p.getProductName());
			pro.setProductImg(p.getProductImg());
			pro.setProductContents(p.getProductContents());
			pro.setProductPrice(p.getProductPrice());
			pro.setMaker(p.getMaker());
			pro.setCreateTime(p.getCreateTime());
			prolike.add(pro);

		}
		return prolike;
	}

	/**
	 * 商品名検索OrderBy時間
	 * @param proname
	 * @return
	 */
	@Transactional
	public List<ProductDto> keyLikeOrderByTime(String proname) {
		List<ProductEntity> likes = productRepository.getLikeProOrderTime(proname);
		System.out.println(likes.size());
		List<ProductDto> prolike = new ArrayList<>();
		for (ProductEntity p : likes) {
			ProductDto pro = new ProductDto();
			pro.setProductId(p.getProductId());
			pro.setProductName(p.getProductName());
			pro.setProductImg(p.getProductImg());
			pro.setProductContents(p.getProductContents());
			pro.setProductPrice(p.getProductPrice());
			pro.setMaker(p.getMaker());
			pro.setCreateTime(p.getCreateTime());
			prolike.add(pro);

		}
		return prolike;
	}

	/**
	 * キーワード検索
	 * @param keyword
	 * @return count 商品数量
	 */
	public int getCountByKey(String keyword) {
		int count = productRepository.getLikeCount(keyword);
		return count;
	}

	/**
	 * 該当商品のストックを取得
	 * @param proId
	 * @return
	 */
	public int getStock(int proId) {
		int stock = productRepository.findByProductId(proId).getStock();
		return stock;
	}

}
