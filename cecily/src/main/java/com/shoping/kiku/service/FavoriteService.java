package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.FavoriteEntity;
import com.shoping.kiku.entity.FavoriteProEntity;
import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.object.FavoProDto;
import com.shoping.kiku.object.FavoriteDto;
import com.shoping.kiku.repository.FavoriteProRepository;
import com.shoping.kiku.repository.FavoriteRepository;
import com.shoping.kiku.repository.ProductRepository;

@Service
public class FavoriteService {

	@Autowired
	FavoriteRepository favoriteRepository;
	@Autowired
	FavoriteProRepository favoriteProRepository;
	@Autowired
	ProductRepository productRepository;

	/**
	 * 気に入りを追加(ユーザIDと商品IDによる)
	 * @param userid
	 * @param productId
	 * @return
	 */
	public int creFavorite(int userid, int productId) {
		// DBに気に入りあるかどうかを検索
		FavoriteEntity check = favoriteRepository.findByUserIdAndProductId(
				userid, productId);

		FavoriteEntity favott = new FavoriteEntity();
		//DBに気に入りない場合　追加
		if (check == null) {
			favott.setUserId(userid);
			favott.setProductId(productId);
			favott.setCreatedate(new Timestamp(System.currentTimeMillis()));
			favoriteRepository.save(favott);

			return 1;
		} else {
			//DBに気に入りある場合 
			return 0;
		}
	}

	/**
	 * 気に入りをキャンセル(ユーザIDと商品IDによる削除)
	 * @param userid
	 * @param productId
	 */
	public void cancelFavorite(int userid, int productId) {
		FavoriteEntity favarite = favoriteRepository.findByUserIdAndProductId(userid, productId);
		favoriteRepository.delete(favarite);

	}

	/**
	 * ユーザIDによる気に入りを取得(気に入り一覧)
	 * @param userId
	 * @return
	 */
	public List<FavoProDto> getFavorite(int userId) {

		List<FavoriteProEntity> fp = favoriteProRepository.getFavoriteProByUserId(userId);
		List<FavoProDto> favo = new ArrayList<FavoProDto>();
		for (FavoriteProEntity f : fp) {
			FavoProDto fa = new FavoProDto();
			fa.setProductId(f.getProductId());
			fa.setUserId(userId);
			fa.setProductImg(f.getProductImg());
			fa.setProductName(f.getProductName());
			fa.setProductPrice(f.getProductPrice());
			fa.setProductContents(f.getProductContents());
			fa.setMaker(f.getMaker());
			fa.setCreatedate(f.getCreatedate());
			favo.add(fa);
		}

		return favo;

	}

	/**
	 * 商品詳細画面　気に入り状態あるかどうか
	 * @param userid
	 * @param proid
	 * @return
	 */
	public FavoriteDto getProByUserIdAndProId(int userid, int proid) {
		//気に入り判断
		FavoriteEntity fet = favoriteRepository.findByUserIdAndProductId(userid, proid);
		FavoriteDto fv = new FavoriteDto();
		ProductEntity pro = productRepository.findByProductId(proid);
		//気に入りなし
		if (fet != null) {
			fv.setUserId(fet.getUserId());
		}
		//商品情報

		fv.setProductId(pro.getProductId());
		fv.setProductImg(pro.getProductImg());
		fv.setProductName(pro.getProductName());
		fv.setProductPrice(pro.getProductPrice());
		fv.setProductContents(pro.getProductContents());
		fv.setMaker(pro.getMaker());
		fv.setStock(pro.getStock());
		fv.setStoreId(pro.getStoreId());
		return fv;

	}

}
