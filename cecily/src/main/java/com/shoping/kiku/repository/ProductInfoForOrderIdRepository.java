package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ProductInfoForOrderIdEntity;

@Repository
public interface ProductInfoForOrderIdRepository extends JpaRepository<ProductInfoForOrderIdEntity, Integer>{

	@Query(value="select\r\n"
			+ "    a.order_id\r\n"
			+ "    , a.product_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_quantity\r\n"
			+ "    , b.product_img\r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join product b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "where\r\n"
			+ "    a.order_id = :orderId",nativeQuery=true)
	List<ProductInfoForOrderIdEntity> productInfoByOrderId(String orderId);
	
}
