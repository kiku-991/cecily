package com.shoping.kiku.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.object.ProductDto;
import com.shoping.kiku.repository.TestProductRepository;

@Service
public class TestService {

	@Autowired
	TestProductRepository testRepository;

	/**
	 * page 分けテスト
	 */

	public Page<ProductDto> findAll(Pageable pageable) {

		Page<ProductEntity> xx = testRepository.findAll(pageable);
		Page<ProductDto> entities = xx.map(new Function<ProductEntity, ProductDto>() {
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
		return entities;

	}
}
