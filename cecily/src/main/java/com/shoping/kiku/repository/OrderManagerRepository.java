package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.OrderManagerEntity;


@Repository
public interface OrderManagerRepository extends JpaRepository<OrderManagerEntity, Integer>{
	
	@Query(value="select distinct\r\n"
			+ "    a.order_id\r\n"
			+ "    , a.order_status\r\n"
			+ "    , b.payment_id\r\n"
			+ "    , b.shipping_id\r\n"
			+ "    , b.createdate\r\n"
			+ "    , e.dtodoufuken\r\n"
			+ "    , e.dshikucyouson\r\n"
			+ "    , e.dcyoumebanchi\r\n"
			+ "    , e.renrakuname\r\n"
			+ "    , e.renrakuphone\r\n"
			+ "    , f.pay_total\r\n"
			+ "    , f.pay_quantity\r\n"
			+ "    , f.pay_method\r\n"
			+ "    , f.pay_time\r\n"
			+ "    , g.courier_company\r\n"
			+ "    , g.tracking_number\r\n"
			+ "    , g.delivery_time\r\n"
			+ "    , g.receipt_time\r\n"
			+ "    , k.name \r\n"
			+ "from\r\n"
			+ "    myorder a \r\n"
			+ "    left join order_item p \r\n"
			+ "        on a.order_id = p.order_id \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join product c \r\n"
			+ "        on p.product_id = c.product_id \r\n"
			+ "    left join store d \r\n"
			+ "        on c.store_id = d.store_id \r\n"
			+ "    left join user_delivery e \r\n"
			+ "        on b.user_id = e.user_id \r\n"
			+ "    left join userinfo k \r\n"
			+ "        on e.user_id = k.id \r\n"
			+ "    left join payment f \r\n"
			+ "        on b.payment_id = f.payment_id \r\n"
			+ "    left join shipping g \r\n"
			+ "        on b.shipping_id = g.shipping_id \r\n"
			+ "    left join user_login u \r\n"
			+ "        on d.user_id = u.user_id \r\n"
			+ "where\r\n"
			+ "    u.user_id = :userId \r\n"
			+ "    and e.defaultadd = 1",nativeQuery=true)
	
	List<OrderManagerEntity> getOrderInfoWithStoreId(int userId);
	
	
	
	@Query(value="select\r\n"
			+ "    a.order_id\r\n"
			+ "    , a.order_status\r\n"
			+ "    , b.payment_id\r\n"
			+ "    , b.shipping_id\r\n"
			+ "    , b.createdate\r\n"
			+ "    , c.dtodoufuken\r\n"
			+ "    , c.dshikucyouson\r\n"
			+ "    , c.dcyoumebanchi\r\n"
			+ "    , c.renrakuname\r\n"
			+ "    , c.renrakuphone\r\n"
			+ "    , d.pay_total\r\n"
			+ "    , d.pay_quantity\r\n"
			+ "    , d.pay_method\r\n"
			+ "    , d.pay_time\r\n"
			+ "    , f.courier_company\r\n"
			+ "    , f.tracking_number\r\n"
			+ "    , f.delivery_time\r\n"
			+ "    , f.receipt_time\r\n"
			+ "    , g.name \r\n"
			+ "from\r\n"
			+ "    myorder a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join user_delivery c \r\n"
			+ "        on b.user_id = c.user_id \r\n"
			+ "    left join payment d \r\n"
			+ "        on b.payment_id = d.payment_id \r\n"
			+ "    left join shipping f \r\n"
			+ "        on b.shipping_id = f.shipping_id \r\n"
			+ "    left join userinfo g \r\n"
			+ "        on c.user_id = g.id \r\n"
			+ "where\r\n"
			+ "    c.defaultadd = 1",nativeQuery=true)
	
	List<OrderManagerEntity>getAllOrderInfo();
	
	
	
	
	
	
	
	
	
	
	
	
	

}
