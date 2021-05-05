package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.StoreEntity;
import com.shoping.kiku.object.StoreDto;
import com.shoping.kiku.repository.StoreRepository;
import com.shoping.kiku.until.Status;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;

	//店舗作成
	public void creStore(int userId, StoreDto st) {
		StoreEntity store = new StoreEntity();
		store.setStoreId(storeRepository.getStoreId());
		store.setUserId(userId);
		store.setStoreName(st.getStoreName());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		//申込中
		store.setStoreStatus(Status.SHOPOPAPP);
		storeRepository.save(store);

	}

	//店舗編集
	public void updateStore(int userId, StoreDto st) {
		StoreEntity store = new StoreEntity();
		store.setUserId(userId);
		store.setStoreName(st.getStoreName());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		store.setStoreStatus(Status.SHOPOPAPP);
	}

	//店舗編集(ADMIN)
	public void editStore(int userId, StoreDto st) {
		StoreEntity store = new StoreEntity();
		store.setUserId(userId);
		store.setStoreName(st.getStoreName());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		store.setStoreStatus(st.getStoreStatus());
	}

	//店舗情報を取得(USer)

	public StoreDto getStoreInfo(int userId) {

		StoreEntity st = storeRepository.findByUserId(userId);
		StoreDto store = new StoreDto();
		if (st != null) {
			store.setStoreId(st.getStoreId());
			store.setUserId(userId);
			store.setStorePhone(st.getStorePhone());
			store.setStorePostcode(st.getStorePostcode());
			store.setStoreCyomebanchi(st.getStoreCyomebanchi());
			store.setStoreTodoufuken(st.getStoreTodoufuken());
			store.setStoreShikucyouson(st.getStoreShikucyouson());
			store.setStoreName(st.getStoreName());
			store.setStoreStatus(st.getStoreStatus());
		} else {
			store = null;
		}
		return store;

	}
	
	
	//店舗情報を取得(USer)

		public StoreDto findByStoreId(int storeId) {

			StoreEntity st = storeRepository.findByStoreId(storeId);
			StoreDto store = new StoreDto();
			if (st != null) {
				store.setStoreId(storeId);
				store.setUserId(st.getUserId());
				store.setStorePhone(st.getStorePhone());
				store.setStorePostcode(st.getStorePostcode());
				store.setStoreCyomebanchi(st.getStoreCyomebanchi());
				store.setStoreTodoufuken(st.getStoreTodoufuken());
				store.setStoreShikucyouson(st.getStoreShikucyouson());
				store.setStoreName(st.getStoreName());
				store.setStoreStatus(st.getStoreStatus());
			} else {
				store = null;
			}
			return store;

		}
	//すべての店舗を取得(ADMIN)

	public List<StoreDto> getAllStore() {
		List<StoreEntity> allStore = storeRepository.findAll();
		List<StoreDto> alls = new ArrayList<>();

		for (StoreEntity all : allStore) {
			StoreDto st = new StoreDto();
			st.setStoreId(all.getStoreId());
			st.setUserId(all.getUserId());
			st.setStorePhone(all.getStorePhone());
			st.setStorePostcode(all.getStorePostcode());
			st.setStoreCyomebanchi(all.getStoreCyomebanchi());
			st.setStoreTodoufuken(all.getStoreTodoufuken());
			st.setStoreShikucyouson(all.getStoreShikucyouson());
			st.setStoreName(all.getStoreName());
			st.setStoreStatus(all.getStoreStatus());
			alls.add(st);
		}

		return alls;
	}

	//申込同意 (回復)
	public void agreeStore(int storeId) {

		StoreEntity st = storeRepository.findByStoreId(storeId);
		StoreEntity store = new StoreEntity();
		store.setStoreId(st.getStoreId());
		store.setUserId(st.getUserId());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreName(st.getStoreName());
		store.setStoreStatus(Status.SHOPOPEN);
		storeRepository.save(store);

	}

	//店舗ブロック

	public void blockStore(int storeId) {

		StoreEntity st = storeRepository.findByStoreId(storeId);
		StoreEntity store = new StoreEntity();
		store.setStoreId(st.getStoreId());
		store.setUserId(st.getUserId());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreName(st.getStoreName());
		store.setStoreStatus(Status.SHOPCLOSE);
		storeRepository.save(store);
	}

	public int getstoreIdByUserId(int userId) {
		int storeId =storeRepository.findByUserId(userId).getStoreId();
		return storeId;
	}
}
