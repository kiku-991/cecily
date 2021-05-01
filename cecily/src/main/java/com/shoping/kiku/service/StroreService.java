package com.shoping.kiku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.StoreEntity;
import com.shoping.kiku.object.StoreDto;
import com.shoping.kiku.repository.StoreRepository;

@Service
public class StroreService {

	@Autowired
	StoreRepository storeRepository;

	//店舗作成
	public void creStore(int userId, StoreDto st) {
		StoreEntity store = new StoreEntity();
		store.setUserId(userId);
		store.setStoreName(st.getStoreName());
		store.setStorePhone(st.getStorePhone());
		store.setStorePostcode(st.getStorePostcode());
		store.setStoreTodoufuken(st.getStoreTodoufuken());
		store.setStoreShikucyouson(st.getStoreShikucyouson());
		store.setStoreCyomebanchi(st.getStoreCyomebanchi());
		store.setStoreStatus(1);
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
		store.setStoreStatus(1);
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

}
