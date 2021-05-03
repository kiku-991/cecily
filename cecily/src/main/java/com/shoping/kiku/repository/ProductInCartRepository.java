package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ProductInCartEntity;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCartEntity, Integer> {

	@Query(value = "select\r\n"
			+ "    a.product_id\r\n"
			+ "    , b.user_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_img\r\n"
			+ "    , a.product_contents\r\n"
			+ "    , b.quantity\r\n"
			+ "    , a.product_price * b.quantity as total \r\n"
			+ "from\r\n"
			+ "    product as a \r\n"
			+ "    inner join mycart as b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "order by\r\n"
			+ "    a.product_id", nativeQuery = true)

	List<ProductInCartEntity> getProCart(int userId);

	@Query(value = "select\r\n"
			+ "    a.product_id\r\n"
			+ "    , b.user_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_img\r\n"
			+ "    , a.product_contents\r\n"
			+ "    , a.maker\r\n"
			+ "    , b.quantity\r\n"
			+ "    , b.checkstatus \r\n"
			+ "    , a.product_price * b.quantity as total \r\n"
			+ "from\r\n"
			+ "    product as a \r\n"
			+ "    inner join mycart as b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "        and b.checkstatus = 1", nativeQuery = true)
	List<ProductInCartEntity> getcheckedpro(int userId);

	@Query(value = "select\r\n"
			+ "    a.product_price * b.quantity as total \r\n"
			+ "from\r\n"
			+ "    product as a \r\n"
			+ "    inner join mycart as b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "        and a.product_id = :productId", nativeQuery = true)
	int getTotalByUserIdAndProductId(int userId, int productId);

}
