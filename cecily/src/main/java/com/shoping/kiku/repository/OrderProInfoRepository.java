package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoping.kiku.entity.OrderProInfoEntity;

public interface OrderProInfoRepository extends JpaRepository<OrderProInfoEntity, Integer> {

	/**
	 * 我的订单List
	 * @param userId
	 * @return
	 */
	@Query(value = "select\r\n"
			+ "    a.user_id\r\n"
			+ "    , a.order_id\r\n"
			+ "    , a.createdate\r\n"
			+ "    , a.payment_id\r\n"
			+ "    , a.shipping_id\r\n"
			+ "    , b.product_id\r\n"
			+ "    , b.product_price\r\n"
			+ "    , b.product_name\r\n"
			+ "    , b.product_quantity\r\n"
			+ "    , c.product_img\r\n"
			+ "    , c.product_contents\r\n"
			+ "    , c.maker\r\n"
			+ "    , c.store_id\r\n"
			+ "    , d.store_name\r\n"
			+ "    , e.order_status\r\n"
			+ "    , e.purchasing_price\r\n"
			+ "    , e.modify_time\r\n"
			+ "    , f.pay_total\r\n"
			+ "    , f.pay_quantity\r\n"
			+ "    , f.pay_method\r\n"
			+ "    , f.pay_time \r\n"
			+ "from\r\n"
			+ "    commerce a \r\n"
			+ "    left join order_item b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join product c \r\n"
			+ "        on b.product_id = c.product_id \r\n"
			+ "    left join store d \r\n"
			+ "        on c.store_id = d.store_id \r\n"
			+ "    left join myorder e \r\n"
			+ "        on b.order_id = e.order_id \r\n"
			+ "    left join payment f \r\n"
			+ "        on a.payment_id = f.payment_id \r\n"
			+ "where\r\n"
			+ "    a.user_id = :userId", nativeQuery = true)
	List<OrderProInfoEntity> getMyOrderInfo(int userId);

	
}
