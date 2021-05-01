package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.UserDeliveryEntity;
@Repository
public interface UserDeliveryRepository extends JpaRepository<UserDeliveryEntity, Integer>{
	@Query(value = "select nextval('user_delivery_id_seq')", nativeQuery = true)
	int getID();
	
	List<UserDeliveryEntity>findByUserId(int userId);

	UserDeliveryEntity findById(int id);
}


