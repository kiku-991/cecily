package com.shoping.kiku.until;

public class MsgContents {

	//入力したメールアドレスがすでに登録され
	public static final String REGISTRATEFAILUER = "該当メールアドレスがすでに登録されましたので、ほかのメールアドレスを使ってください";
	//入力したメールアドレスかパスワードが一致しない
	public static final String LOGINFAILUER = "正しいメールアドレスかパスワードを入力してください";
	//入力したメールアドレス登録されていません
	public static final String REGISTRATENASHI = "該当メールアドレスが登録されていません";
	//パスワード変更時
	public static final String CHECKPWD = "パスワードが正しくない、もう一度入力してください";
	
	public static final String PASSCHANGE ="パスワードが変更されましたので、もう一度ログインしてください";
	//権限がないので、ログインできません
	public static final String STATUSCHECK ="権限がないので、ログインできません";
	//商品中止中
	public static final String STATUS_STOP = "中止中";
	//商品出品中
	public static final String STATUS_SYUPPIN = "出品中";
	//店舗申込中
	public static final String TENNPUAPP = "店舗申込ありがとうございます。管理者の承認をお待ちください。";
	
	//店舗申込許可
	public static final String TENNPUOK = "出店申込を承認しました,これから営業よろしくお願いいたします。";
	//店舗申込不許可
	public static final String TENNPUREFUSE = "残念ながら、出店申込を拒否されました。";
	//店舗ブロック
	public static final String TENNPUBLOCK = "残念ながら、不適切な操作によって、店舗営業中止になります。";
	
	
	
	//入力したメールアドレス登録されてない
	public static final String MAILNO = "no";
	//入力したメールアドレス登録されてる
	public static final String MAILARI = "ok";
	//ユーザメールアドレスパスワード一致する
	public static final String CHECKTRUE ="true";
	//ユーザメールアドレスパスワード一致しない
	public static final String CHECKFALSE ="false";

}