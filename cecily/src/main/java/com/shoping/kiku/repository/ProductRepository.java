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

	ProductEntity findByProductId(int productid);

	List<ProductEntity> findAllByOrderByProductIdAsc();
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product_name like concat(concat('%', :key ),'%') ",nativeQuery=true)
	List<ProductEntity> getLikePro(String key);
	
	
	
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product.status <> 0 \r\n"
			+ "order by\r\n"
			+ "    product_id",nativeQuery=true)
	List<ProductEntity> getNormalPro();
	
	/**
	 * 
	 * 
	 * 
	 */
	
	
	
	
}
