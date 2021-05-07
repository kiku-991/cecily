package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.OrderInfoByUserIdEntity;

@Repository
public interface OrderInfoByRepository extends JpaRepository<OrderInfoByUserIdEntity, Integer>{

	
	/*	@Query(value="select\r\n"
				+ "    a.order_id\r\n"
				+ "    , sum(a.product_price) as alltotal\r\n"
				+ "    , sum(a.product_quantity) as allquantity \r\n"
				+ "from\r\n"
				+ "    order_item a \r\n"
				+ "    left join product b \r\n"
				+ "        on a.product_id = b.product_id \r\n"
				+ "    left join store c \r\n"
				+ "        on b.store_id = c.store_id \r\n"
				+ "where\r\n"
				+ "    c.store_id = :storeId \r\n"
				+ "group by\r\n"
				+ "    a.order_id",nativeQuery=true)
		List<OrderInfoByUserIdEntity> getOrderInfoGroupByOrderIdInStoreId(int storeId);*/
	
	@Query(value="select distinct\r\n"
			+ "    a.order_id\r\n"
			+ "    , f.order_status\r\n"
			+ "    , f.purchasing_price\r\n"
			+ "    , f.create_time\r\n"
			+ "    , f.modify_time\r\n"
			+ "    , e.payment_id\r\n"
			+ "    , e.pay_total\r\n"
			+ "    , e.pay_quantity\r\n"
			+ "    , e.pay_method\r\n"
			+ "    , e.pay_time\r\n"
			+ "    , d.shipping_id\r\n"
			+ "    , d.courier_company\r\n"
			+ "    , d.tracking_number\r\n"
			+ "    , d.delivery_time\r\n"
			+ "    , d.receipt_time\r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join userinfo c \r\n"
			+ "        on b.user_id = c.id \r\n"
			+ "    left join payment e \r\n"
			+ "        on b.payment_id = e.payment_id \r\n"
			+ "    left join shipping d \r\n"
			+ "        on b.shipping_id = d.shipping_id \r\n"
			+ "    left join myorder f \r\n"
			+ "        on b.order_id = f.order_id \r\n"
			+ "where\r\n"
			+ "    b.user_id = :userId",nativeQuery=true)
	List<OrderInfoByUserIdEntity> getOrderInfoGroupByOrderIdByUserId(@Param(value="userId") int userId);
	
	
}
