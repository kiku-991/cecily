package com.shoping.kiku.until;

public class Url {
	//imgフォルダに画像
		public static final String SRC = "../img/";

		//ProductController

		//商品新規登録
		public static final String CREATEProduct = "/center/productmanger";
		//商品IDによって商品編集
		public static final String UPDATEPROBYID = "/center/product/edit/{id}";
		//商品IDによって商品削除
		public static final String DELETEPROBYID = "/center/productmanger/deleteProduct/{id}";
		//商品IDによって商品を中止
		public static final String STOPSTATUSBYID = "/center/productmanger/stop/{id}";
		//商品IDによって中止商品を回復
		public static final String RECOVERYPROBYID = "/center/productmanger/recovery/{id}";

		//LoginController

		//ログイン画面
		public static final String LOGIN = "/login";

		//ログアウト 
		public static final String LOGINOUT = "/loginout";

		//ShoppingController

		//ホームページ
		public static final String SHOPPING = "/shopping";

		//ホームページ
		public static final String INDEX = "/";

		//TorokuController

		//登録画面
		public static final String TOUROKU = "/touroku";

		//UserCenterController

		//マイページ 
		public static final String CENTER = "/shopping/center";

		//会員基本情報画面 
		public static final String USERINFO = "/center/userInfo";

		//届け住所編集画面
		public static final String USERTODOKE = "/center/userTodoke";

		//パスワード変更画面 
		public static final String PWDCHANGE = "/center/pwdChange";

		//お気に入り
		public static final String FAVORITE = "/center/favorite";

		//買い物かご
		public static final String SHOPCART = "/center/shopcart";

		//マイ注文
		public static final String MYCYOUMON = "/center/mycyumon";

		//出店申込
		public static final String MOSHIKOMI = "/center/moushikomi";

		//注文管理
		public static final String CYOMONMANAGER = "/center/cyumonManager";

		//商品管理(店舗)
		public static final String PRODUCTMANGER = "/center/productmanger";

		//ユーザ一覧
		public static final String USERLIST = "/center/userlist";

		//ユーザ情報
		public static final String USERINFOLIST = "/center/userInfoList";

		//ユーザ届け住所連絡

		public static final String USERTODOKELIST = "/center/userTodokeList";

		//UserController
		
		

		//ユーザ情報登録
		public static final String USERTCREATE = "/center/userInfo/usercrete";

		//該当ユーザ情報更新
		public static final String USERINFOEDIT = "/center/userInfo/edit";

		//該当届け住所編集
		public static final String USERTODOKEEDIT = "/center/userTodoke/edit";

		//パスワード変更
		public static final String PWDEDIT = "/center/pwdChange/edit";
		//Role 変更 店舗申込
		public static final String USERTEDIT = "/center/moushikomi";

		//ユーザ一覧(ADMIN)
		public static final String ALLUSER = "/center/userlist";

		//すべてのユーザー情報操作(ADMIN)
		public static final String ALLUSERINFO = "/center/userInfoList";

		//ユーザー操作(ADMIN)
		public static final String ALLUSEREDIT = "/center/userlist/userEdit";

		//ユーザIDによるユーザ削除(ADMIN)
		public static final String USERDELETEBYID = "/center/userInfoList/deleteUser/{userId}";

		//すべてのユーザー届け住所情報操作(ADMIN)
		public static final String USERTODOKEEDITBYID = "/center/userTodokeList/edit/{userId}";

}
