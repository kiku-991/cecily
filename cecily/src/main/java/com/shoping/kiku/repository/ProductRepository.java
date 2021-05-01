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

	//userid によって　商品を取得　(多店舗)
	List<ProductEntity> findByUserIdOrderByProductIdAsc(int userid);

	ProductEntity findByProductId(int productid);

	List<ProductEntity> findAllByOrderByProductIdAsc();
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    product \r\n"
			+ "where\r\n"
			+ "    product_name like concat(concat('%', :key ),'%') ",nativeQuery=true)
	List<ProductEntity> getLikePro(String key);
}
