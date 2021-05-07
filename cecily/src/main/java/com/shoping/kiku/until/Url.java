package com.shoping.kiku.until;

public class Url {

	/**
	 * LoginController
	 */

	//ログイン画面
	public static final String LOGIN = "/login";

	//ログアウト 
	public static final String LOGINOUT = "/loginout";

	//imgフォルダに画像
	public static final String SRC = "../img/";

	/**
	 * UserInfoController
	 */

	//ユーザ情報登録
	public static final String USERTCREATE = "/center/userInfo/usercrete";

	//該当ユーザ情報更新
	public static final String USERINFOEDIT = "/center/userInfo/edit";

	//該当届け住所作成
	public static final String USERTODOKEADD = "/center/userTodoke/add";

	//該当届け住所編集
	public static final String USERTODOKEEDIT = "/center/userTodoke/edit/{id}";

	//該当届け住所削除
	public static final String USERTODOKEEDELETE = "/center/userTodoke/delete/{id}";

	//パスワード変更
	public static final String PWDEDIT = "/center/pwdChange/edit";

	//登录届け住所(buy画面)
	public static final String ADDTODOKRBUY = "/buy/useradd/add";

	//編集届け住所(buy画面)

	//ユーザ一覧(ADMIN)
	public static final String ALLUSER = "/center/userlist";

	//ユーザー操作(ADMIN)
	public static final String ALLUSEREDIT = "/center/userlist/userEdit/{id}";

	//ユーザIDによるユーザ削除(ADMIN)
	public static final String USERDELETEBYID = "/center/userInfoList/deleteUser/{userId}";

	//すべてのユーザー情報操作(ADMIN)
	public static final String ALLUSERINFO = "/center/userInfoList/{id}";

	//すべてのユーザー届け住所情報操作(ADMIN)
	public static final String ALLUSERTODOKEEDIT = "/center/userTodokeList/edit/{id}";

	//すべてのユーザー届け住所情報操作(ADMIN)
	//public static final String USERTODOKEEDITBYID = "/center/userTodokeList/edit/{userId}";

	/**
	 * TorokuController
	 */

	//登録画面
	public static final String TOUROKU = "/touroku";

	/**
	 * UserCenterController
	 */

	//マイページ 
	public static final String CENTER = "/shopping/center";

	//会員基本情報画面 
	public static final String USERINFO = "/center/userInfo";

	//届け住所編集画面
	public static final String USERTODOKE = "/center/userTodoke";

	//パスワード変更画面 
	public static final String PWDCHANGE = "/center/pwdChange";

	//パスワード変更失敗画面
	public static final String PWDCHANGEFAIL = "/center/passfail";

	//お気に入り
	public static final String FAVORITE = "/center/favorite";

	//買い物かご
	public static final String SHOPCART = "/center/shopcart";

	//マイ注文
	public static final String MYCYOUMON = "/center/mycyumon";

	//出店申込
	public static final String MOSHIKOMISHOP = "/center/myshop";

	//注文管理
	public static final String CYOMONMANAGER = "/center/ordermanager";
	
	//ユーザID によって商品取得(店舗)
	public static final String MYPRODUCTS = "/center/myproducts";

	//商品管理(店舗)
	public static final String PRODUCTMANGER = "/center/productmanger";

	//店舗管理(ADMIN)
	public static final String STOREMANAGER = "/center/storemanger";
	//ユーザ一覧
	public static final String USERLIST = "/center/userlist";

	//ユーザ情報
	public static final String USERINFOLIST = "/center/userInfoList";

	//ユーザ届け住所連絡

	public static final String USERTODOKELIST = "/center/userTodokeList";

	//Role 変更 店舗申込
	public static final String USERTEDIT = "/center/moushikomi";

	/**
	 * StoreController
	 */

	//出店申込
	public static final String SHOPAPPLY = "/center/myshop/shopApply";

	//申込同意(ADMIN)

	public static final String AGREEAPP = "/center/storemanger/agree/{id}";

	//店舗ブロック(ADMIN)
	public static final String STOPSHOP = "/center/storemanger/stop/{id}";

	//店舗回復(ADMIN)
	public static final String RECOVERYSHOP = "/center/storemanger/recovery/{id}";

	/**
	 * ShoppingController
	 */

	//ホームページ
	public static final String SHOPPING = "/shopping";

	//ホームページ
	public static final String INDEX = "/";

	//キーワード検索
	public static final String KEYWORD = "/shopping/keywordSearch";

	//気に入り一覧
	public static final String SHOPFAVPRITE = "/shopping/favorite";

	/**
	 * ProductController
	 */

	//商品新規登録
	public static final String CREATEPRODUCT = "/center/myproducts/create";
	//商品IDによって商品編集
	public static final String UPDATEPROBYID = "/center/myproducts/edit/{id}";
	//商品IDによって商品削除
	public static final String DELETEPROBYID = "/center/myproducts/deleteProduct/{id}";
	//商品IDによって商品を中止
	public static final String STOPSTATUSBYID = "/center/myproducts/stop/{id}";
	//商品IDによって中止商品を回復
	public static final String RECOVERYPROBYID = "/center/myproducts/recovery/{id}";

	//商品詳細画面
	public static final String PRODUCTDETAILS = "/productDetails/{id}";
	//商品IDによって商品編集(ADMIN)
	public static final String PROMANAGEREDIT = "/center/productmanger/edit/{id}";
	//商品IDによって商品削除(ADMIN)
	public static final String PROMANAGEREDELETE = "/center/productmanger/deleteProduct/{id}";
	//商品IDによって商品を中止(ADMIN)
	public static final String PROMANAGERSTOP = "/center/productmanger/stop/{id}";
	//商品IDによって中止商品を回復(ADMIN)
	public static final String PROMANAGERRECOVERY = "/center/productmanger/recovery/{id}";
}
