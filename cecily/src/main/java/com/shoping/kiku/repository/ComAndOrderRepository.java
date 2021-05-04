package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ComAndOrderEntity;

@Repository
public interface ComAndOrderRepository extends JpaRepository<ComAndOrderEntity, Integer> {

	@Query(value = "select\r\n"
			+ "    a.user_id\r\n"
			+ "    , a.order_id\r\n"
			+ "    , a.payment_id\r\n"
			+ "    , b.product_id\r\n"
			+ "    , a.shipping_id\r\n"
			+ "    , c.order_status\r\n"
			+ "    , c.purchasing_price\r\n"
			+ "    , c.modify_time \r\n"
			+ "from\r\n"
			+ "    commerce a \r\n"
			+ "    left join order_item b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join myorder c \r\n"
			+ "        on b.order_id = c.order_id \r\n"
			+ "where\r\n"
			+ "    a.user_id = :userId \r\n"
			+ "    and b.product_id = :prouductId", nativeQuery = true)
	
	ComAndOrderEntity comAndOrder(int userId, int prouductId);

}
