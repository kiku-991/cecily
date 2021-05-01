package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_login")
public class UserLoginEntity {

	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_mail")
	private String userMail;
	@Column(name = "user_password")
	private String userPassword;
	@Column(name = "create_date")
	private Timestamp createDate;
	@Column(name = "update_date")
	private Timestamp updateDate;
	@Column(name = "role")
	private String role;
	@Column(name = "status")
	private Integer status;

}
