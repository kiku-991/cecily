package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.PayPriceEntity;

@Repository
public interface PayPriceRepository extends JpaRepository<PayPriceEntity,Integer>{

	@Query(value="select\r\n"
			+ "    sum(a.product_price) as alltotal \r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join userinfo c \r\n"
			+ "        on b.user_id = c.id \r\n"
			+ "where\r\n"
			+ "    b.user_id = :userId \r\n"
			+ "    and a.order_id = :orderId",nativeQuery=true)
	
	int getpriceByUserIdAndOrdId (int userId,String orderId);
	
	@Query(value="select\r\n"
			+ "    sum(a.product_quantity) as allquantity \r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join userinfo c \r\n"
			+ "        on b.user_id = c.id \r\n"
			+ "where\r\n"
			+ "    b.user_id = :userId \r\n"
			+ "    and a.order_id = :orderId",nativeQuery=true)
	
	int getquantityByUserIdAndOrdId(int userId,String orderId);
	
	

	
}
