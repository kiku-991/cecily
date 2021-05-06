package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.MyOrderItemEntity;

@Repository
public interface MyOrderItemRepository extends JpaRepository<MyOrderItemEntity, Integer> {

	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    order_item \r\n"
			+ "where\r\n"
			+ "    order_id = :orderId",nativeQuery=true)
	List<MyOrderItemEntity> findByOrderId(String orderId);
	
	
	@Query(value="select \r\n"
			+ "product_id,\r\n"
			+ "    product_quantity\r\n"
			+ "from\r\n"
			+ "    order_item \r\n"
			+ "where\r\n"
			+ "    order_id = :orderId",nativeQuery=true)
	List<MyOrderItemEntity> returnStock(String orderId);

}
