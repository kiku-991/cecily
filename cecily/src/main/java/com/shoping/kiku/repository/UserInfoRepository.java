package com.shoping.kiku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.UserInfoEntity;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {
	
	UserInfoEntity findById(int id);
	
	@Query(value="select\r\n"
			+ "    * \r\n"
			+ "from\r\n"
			+ "    userinfo a join user_login b \r\n"
			+ "        on a.id = b.user_id \r\n"
			+ "where\r\n"
			+ "    role <> 'admin'",nativeQuery=true)
	List<UserInfoEntity> findAllByOrderByIdAsc();
				
}
