package com.shoping.kiku.entity;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "userinfo")
public class UserInfoEntity {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;
	private Date birth;
	private Integer sex;
	private String phone;
	private String nickname;
	private String icon;
	//郵便番号
	private String postcode;
	//都道府県
	private String todoufuken;
	//市区町村
	private String shikucyouson;
	//丁目番地
	private String cyoumebanchi;
}
