package com.shoping.kiku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoping.kiku.entity.PwdReSetEntity;

@Repository
public interface PwdReSetRepository extends JpaRepository<PwdReSetEntity, Integer>{

	@Query(value="select\r\n"
			+ "    a.user_mail\r\n"
			+ "    , b.name \r\n"
			+ "from\r\n"
			+ "    user_login a \r\n"
			+ "    left join userinfo b \r\n"
			+ "        on a.user_id = b.id \r\n"
			+ "where\r\n"
			+ "    a.user_mail = :userMail \r\n"
			+ "    and b.name = :name ",nativeQuery=true)
	
	PwdReSetEntity getUserInfoByUserMail(String userMail,String name);
}
