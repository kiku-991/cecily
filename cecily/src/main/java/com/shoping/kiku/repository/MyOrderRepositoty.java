package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.MyOrderEntity;

@Repository
public interface MyOrderRepositoty extends JpaRepository<MyOrderEntity, Integer>{
	

	MyOrderEntity findByOrderId(String orderId);
}
