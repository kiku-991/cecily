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
	
	
	//支払い方法 AriPay
	public static final Integer ARIPAY = 1;
	
	//支払い方法 CreditPay
	public static final Integer CREDITPAY = 2;
	//支払い方法 CashOndelivery
	public static final Integer CASHPAY = 3;
	
	
	//オーダー状態 0 待支付
	public static final Integer ORDERWAITPAY =0;
	
	public static final String ORDERWAIT ="待支付";
	
	
	//オーダー状態 1 待发货
	public static final Integer ORDERDELIVEY =1;
	
	public static final String ORDETOBEDELIVED ="待发货";
	
	//オーダー状態 2 待收货
	public static final Integer ORDERRECEIVE =2;
	public static final String ORDETOBERECEIVED ="待收货";
	
	//オーダー状態 3 已完成
	public static final Integer ORDERCOMPLETE =3;
	public static final String ORDETOBECOM ="已完成";
	

}
