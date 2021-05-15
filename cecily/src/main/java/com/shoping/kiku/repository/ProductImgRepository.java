package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ProductImgEntity;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Integer> {

	List<ProductImgEntity> findByProductId(int productId);
	
}
