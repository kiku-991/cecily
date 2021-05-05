package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.OrderManagerEntity;


@Repository
public interface OrderManagerRepository extends JpaRepository<OrderManagerEntity, Integer>{
	
	@Query(value="select\r\n"
			+ "    a.order_id\r\n"
			+ "    , b.createdate\r\n"
			+ "    , b.payment_id\r\n"
			+ "    , b.shipping_id\r\n"
			+ "    , a.product_id\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_quantity\r\n"
			+ "    , b.user_id\r\n"
			+ "    , c.product_img\r\n"
			+ "    , c.store_id\r\n"
			+ "    , e.order_status\r\n"
			+ "    , e.purchasing_price\r\n"
			+ "    , e.modify_time\r\n"
			+ "    , f.pay_total\r\n"
			+ "    , f.pay_quantity\r\n"
			+ "    , f.pay_method\r\n"
			+ "    , f.pay_time\r\n"
			+ "    , g.dtodoufuken\r\n"
			+ "    , g.dshikucyouson\r\n"
			+ "    , g.dcyoumebanchi\r\n"
			+ "    , g.renrakuname\r\n"
			+ "    , g.renrakuphone \r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join product c \r\n"
			+ "        on a.product_id = c.product_id \r\n"
			+ "    left join store d \r\n"
			+ "        on c.store_id = d.store_id \r\n"
			+ "    left join myorder e \r\n"
			+ "        on b.order_id = e.order_id \r\n"
			+ "    left join payment f \r\n"
			+ "        on b.payment_id = f.payment_id \r\n"
			+ "    left join user_delivery g \r\n"
			+ "        on b.user_id = g.user_id \r\n"
			+ "where\r\n"
			+ "    c.store_id = :storeId \r\n"
			+ "    and g.defaultadd = 1",nativeQuery=true)
	
	List<OrderManagerEntity> getOrderInfoWithStoreId(int storeId);

}
