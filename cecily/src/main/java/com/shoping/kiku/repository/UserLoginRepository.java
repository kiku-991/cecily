package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.UserLoginEntity;
@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Integer>{
	@Query(value = "select nextval('user_login_user_id_seq')", nativeQuery = true)
	int getID();
	
	
	UserLoginEntity findByUserMail(String userMail);
	UserLoginEntity findByUserId(int id);

}
