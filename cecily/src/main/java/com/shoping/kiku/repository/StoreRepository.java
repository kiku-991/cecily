package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{

	@Query(value = "select nextval('store_store_id_seq')", nativeQuery = true)
	Integer getStoreId();
	
	
	StoreEntity findByUserId(int userId);
	
	StoreEntity findByStoreId(int storeId);
}
