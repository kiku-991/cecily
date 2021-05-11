package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.PayPriceEntity;

@Repository
public interface PayPriceRepository extends JpaRepository<PayPriceEntity,Integer>{

	@Query(value="select\r\n"
			+ "    a.order_id,\r\n"
			+ "    a.product_id\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_quantity \r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join userinfo c \r\n"
			+ "        on b.user_id = c.id \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "where\r\n"
			+ "    b.order_id = :orderId",nativeQuery=true)
	
	List<PayPriceEntity> getpriceAndQuantityByUserIdAndOrdId (@Param(value="userId")int userId,@Param(value="orderId")String orderId);
	
	
	@Query(value="select\r\n"
			+ "    a.order_id\r\n"
			+ "    ,a.product_id\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_quantity \r\n"
			+ "from\r\n"
			+ "    order_item a \r\n"
			+ "    left join commerce b \r\n"
			+ "        on a.order_id = b.order_id \r\n"
			+ "    left join userinfo c \r\n"
			+ "        on b.user_id = c.id \r\n"
			+ "    left join product d \r\n"
			+ "        on a.product_id = d.product_id \r\n"
			+ "    left join store f \r\n"
			+ "        on f.store_id = d.store_id \r\n"
			+ "    left join user_login g \r\n"
			+ "        on g.user_id = f.user_id \r\n"
			+ "where\r\n"
			+ "    g.user_id = :userId \r\n"
			+ "    and a.order_id = :orderId",nativeQuery=true)
	
	List<PayPriceEntity> getUserTotalAndQuantity(@Param(value="userId")int userId,@Param(value="orderId")String orderId);

	
	
	@Query(value="select\r\n"
			+ "    order_id\r\n"
			+ "    , product_id\r\n"
			+ "    , product_price\r\n"
			+ "    , product_quantity \r\n"
			+ "from\r\n"
			+ "    order_item \r\n"
			+ "where\r\n"
			+ "    order_id = :orderId",nativeQuery=true)
	List<PayPriceEntity> getTotalAndQuantityByOrderId(@Param(value="orderId")String orderId);
	
}
