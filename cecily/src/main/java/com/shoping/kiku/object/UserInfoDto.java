package com.shoping.kiku.object;



import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoDto extends UserLoginDto{
	
	private int id;
	//氏名
	private String name;
	//誕生日
	private Date birth;
	//性別
	private int sex;
	//郵便番号
	private String postcode;
	//都道府県
	private String todoufuken;
	//市区町村
	private String shikucyouson;
	//丁目番地
	private String cyoumebanchi;
	//電話番号
	private String phone;
	//ニックネーム
	private String nickname;
	//アイコン
	private String icon;
	

}
