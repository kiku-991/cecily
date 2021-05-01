package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
	

}
