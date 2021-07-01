package com.shoping.kiku.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shoping.kiku.entity.ProductEntity;

public interface TestProductRepository extends JpaRepository<ProductEntity, Integer>{
	
	//page 分けテスト用
		Page<ProductEntity> findAll(Pageable page);

}
