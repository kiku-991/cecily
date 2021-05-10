package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.FavoriteProEntity;

@Repository
public interface FavoriteProRepository extends JpaRepository<FavoriteProEntity, Integer>{

	@Query(value="select\r\n"
			+ "    a.product_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_img\r\n"
			+ "    , a.product_contents\r\n"
			+ "    , a.maker\r\n"
			+ "    , a.status\r\n"
			+ "    , b.user_id\r\n"
			+ "    , b.createdate \r\n"
			+ "from\r\n"
			+ "    product a \r\n"
			+ "    inner join favorite b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "        and a.status <> 0 \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "order by\r\n"
			+ "    a.product_id",nativeQuery=true)
	List<FavoriteProEntity> getFavoriteProByUserId(int userId);
	
	
	@Query(value="select\r\n"
			+ "    a.product_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_img\r\n"
			+ "    , a.product_contents\r\n"
			+ "    , a.maker\r\n"
			+ "    , a.status\r\n"
			+ "    , b.user_id\r\n"
			+ "    , b.createdate \r\n"
			+ "from\r\n"
			+ "    product a \r\n"
			+ "    left join favorite b \r\n"
			+ "        on a.product_id = b.product_id \r\n"
			+ "        and b.user_id = :userId \r\n"
			+ "    left join store c \r\n"
			+ "        on a.store_id = c.store_id \r\n"
			+ "where\r\n"
			+ "    a.status <> 0 \r\n"
			+ "    and c.store_status <> 2 \r\n"
			+ "order by\r\n"
			+ "    a.product_id",nativeQuery=true)
	List<FavoriteProEntity> getProsWithFavByUserId(int userId);
}
