package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.CommerceEntity;

@Repository
public interface CommerceRepository extends JpaRepository<CommerceEntity, Integer>{

	
	List<CommerceEntity> findByUserId(int userId);
}
