package com.shoping.kiku.object;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class UserLoginDto {
	private int userId;
	private String userMail;
	private String userPassword;
	//パスワード変更用 現在のパスワード
	private String oldPassword;
	//確認用パスワード
	private String confirmPassword;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String role;
	private Integer status;

}
