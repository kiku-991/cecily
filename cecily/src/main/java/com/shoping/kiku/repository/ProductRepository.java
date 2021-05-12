package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	@Query(value = "select nextval('product_product_id_seq')", nativeQuery = true)
	int getID();

	//userId によって　商品を取得　(多店舗)
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product a \r\n"
			+ "    left join store b \r\n"
			+ "        on a.store_id = b.store_id \r\n"
			+ "where\r\n"
			+ "    b.user_id = :userId \r\n"
			+ "order by\r\n"
			+ "    a.product_id asc",nativeQuery=true)
	List<ProductEntity> findByStoreIdOrderByProductIdAsc(int userId);
	
	List<ProductEntity> findByStoreId(int storeId);

	ProductEntity findByProductId(int productid);

	List<ProductEntity> findAllByOrderByProductIdAsc();
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product_name like concat(concat('%', :key ),'%') ",nativeQuery=true)
	List<ProductEntity> getLikeProByProname(String key);
	
	

	
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product_name like concat(concat('%', :key ),'%') \r\n"
			+ "order by\r\n"
			+ "    product_price desc",nativeQuery=true)
	List<ProductEntity> getLikeProOrderPrice(String key);
	
	
	
	@Query(value="select\r\n"
			+ "    count(product_name) \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product_name like concat(concat('%', :key ),'%') ",nativeQuery=true)
	
	int getLikeCount(String key);
	
	
	
	
	
	
	
	
	
	
	
	@Query(value="select\r\n"
			+ "    a.product_id\r\n"
			+ "    , a.store_id\r\n"
			+ "    , a.product_name\r\n"
			+ "    , a.product_price\r\n"
			+ "    , a.product_img\r\n"
			+ "    , a.product_contents\r\n"
			+ "    , a.maker\r\n"
			+ "    , a.status\r\n"
			+ "    , a.stock \r\n"
			+ "from\r\n"
			+ "    product a \r\n"
			+ "    left join store b \r\n"
			+ "        on a.store_id = b.store_id \r\n"
			+ "where\r\n"
			+ "    a.status <> 0 \r\n"
			+ "    and b.store_status <> 2",nativeQuery=true)
	List<ProductEntity> getNormalPro();
	
	/**
	 * 
	 * 
	 * 
	 */
	
	
	
	
}
