package com.shoping.kiku.until;

public class Status {
	
	
	
	
	//ユーザ正常
	
	public static final Integer USERNOMAL = 1;
	
	//ユーザブロック(ADMIN)
	
	public static final Integer USERBOLOC = 0;
	
	
	//出店申込(USER)
	
	public static final Integer SHOPOPAPP = 0;
	
	//店舗営業(出店申込同意　ADMIN)
	public static final Integer SHOPOPEN = 1;
	//店舗閉め(店舗ブロック　ADMIN)
	public static final Integer SHOPCLOSE = 2;
	
	
	//商品出品
	public static final Integer PRODUCTIN = 1;
	//商品中止
	public static final Integer PRODUCTSTOP = 0;
	
	

}
