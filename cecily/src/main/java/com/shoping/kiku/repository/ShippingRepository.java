package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ShippingEntity;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, Integer> {

	@Query(value = "select\r\n"
			+ "    a.shipping_id\r\n"
			+ "    , a.courier_company\r\n"
			+ "    , a.tracking_number\r\n"
			+ "    , a.delivery_time\r\n"
			+ "    , a.receipt_time \r\n"
			+ "from\r\n"
			+ "    shipping a \r\n"
			+ "    inner join commerce b \r\n"
			+ "        on a.shipping_id = b.shipping_id \r\n"
			+ "where\r\n"
			+ "    b.order_id = :orderId", nativeQuery = true)
	ShippingEntity getShipInfoFindByShippingId(String orderId);
}
