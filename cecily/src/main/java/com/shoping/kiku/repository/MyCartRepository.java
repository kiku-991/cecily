package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.MyCartEntity;

@Repository
public interface MyCartRepository extends JpaRepository<MyCartEntity, Integer> {
	
	MyCartEntity findByUserIdAndProductId(int userId, int proId);

	List<MyCartEntity> findByUserId(int userId);
	
	@Query(value = "select\r\n"
			+ "    quantity \r\n"
			+ "from\r\n"
			+ "    mycart \r\n"
			+ "where\r\n"
			+ "    user_id = :userId \r\n"
			+ "    and product_id = :productId", nativeQuery = true )
	int getQuantity(int userId ,int productId);
	
	
	@Query(value="select\r\n"
			+ "    count(quantity) \r\n"
			+ "from\r\n"
			+ "    mycart \r\n"
			+ "where\r\n"
			+ "    user_id = :userId",nativeQuery=true)
	
	int getCountByUserId(int userId);
	
	@Query(value = "select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    mycart \r\n"
			+ "where\r\n"
			+ "    user_id = :userId \r\n"
			+ "    and checkstatus = 1", nativeQuery = true )
	List<MyCartEntity> getCheckedPro(int userId );
	
	
	MyCartEntity deleteByUserIdAndProductId(int userId,int productId);
}