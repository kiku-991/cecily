package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.PayPriceEntity;

@Repository
public interface PayPriceRepository extends JpaRepository<PayPriceEntity,Integer>{

	@Query(value="select\r\n"
			+ "    b.product_price \r\n"
			+ "from\r\n"
			+ "    commerce a \r\n"
			+ "    left join order_item b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "where\r\n"
			+ "    a.user_id = :userId \r\n"
			+ "    and b.product_id = :productId",nativeQuery=true)
	
	int getpriceByUserIdAndProId (int userId,int productId);
	
	@Query(value="select\r\n"
			+ "    b.product_quantity \r\n"
			+ "from\r\n"
			+ "    commerce a \r\n"
			+ "    left join order_item b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "where\r\n"
			+ "    a.user_id = :userId \r\n"
			+ "    and b.product_id = :productId",nativeQuery=true)
	
	int getquantityByUserIdAndProId(int userId,int productId);
	
	

	
}
