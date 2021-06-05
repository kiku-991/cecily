# 通販サイト開発
## 「KIKUSHOP」
SpringBootフレームワークを用いた「KIKUSHOP」という通販サイトを自ら開発しました。
当サイトは商品管理、ユーザ管理、注文管理などの機能が付いています。 開発の期間は約2ヶ月でした。
機能は以下となります。






<h3>機能一覧</h3>

<table>
    <thead>
        <tr>
            <th>区分</th>
            <th colspan=2>機能名</th>      
            <th>機能概要</th>
        </tr>
    </thead>
    
    <tbody>
        <tr>
            <td rowspan=5>共通</td>
            <td colspan=2>登録</td>
            <td>アカウント作成できます</td>
        </tr>
        <tr>
             <td colspan=2>ログイン</td>
            <td>ログインする、セッション作成</td>
        </tr>
        <tr>
            <td colspan=2>パスワード忘れ</td>
            <td>パスワードを忘れる場合、検証を行う、再設定ができます</td>
        </tr>
        <tr>
            <td colspan=2>ログアウト</td>
            <td>ログアウトをする、セッションをRemoveする</td>
        </tr>
        <tr>
            <td colspan=2>インターセプター</td>
            <td>未ログインの場合、マイページにアクセスできません</td>
        </tr>
          </tbody>
         <tbody>
         <tr>
            <td  rowspan=15>ユーザ側</td>
            <td colspan=2>商品一覧</td>
            <td>商品一覧確認できます</td>
        </tr>
        <tr>
            <td colspan=2>商品詳細</td>
            <td>商品の詳細情報を表示する</td>  
        </tr>
        <tr>
            <td colspan=2>商品検索</td>
            <td>商品名、商品価格、更新時間によって検索できます</td>  
        </tr>
        <tr>
            <td colspan=2>買い物かご</td>
            <td>商品を買い物かごに入れ、数量増加減少できます、買い物かご一覧確認できます</td>  
        </tr>
        <tr>
            <td colspan=2>お気に入り</td>
            <td>商品をお気に入りできます、お気に入り一覧確認できます</td>  
        </tr>
        <tr>
            <td colspan=2>注文キャンセル</td>
            <td>注文をキャンセルできます</td>  
        </tr>
        <tr>
            <td colspan=2>決済</td>
            <td>AriPay、クレジットカード支払い</td>  
        </tr>
        <tr>
            <td rowspan=8>マイページ</td>
            <td>会員情報登録</td>  
            <td>お客様の情報を登録できます</td>            
        </tr> 
        <tr>
            <td>会員情報編集</td>
            <td>お客様の情報を編集できます</td>  
        </tr>
                           <tr>
            <td>お届け先情報登録、追加</td>
            <td>お届け先情報登録、多数追加できます</td>  
        </tr>
                      <tr>
            <td>お届け先情報編集</td>
            <td>お届け先情報編集できます</td>  
        </tr>
                      <tr>
            <td>お届け先情報削除</td>
            <td>お届け先情報削除できます</td>  
        </tr>
                      <tr>
            <td>パスワード変更</td>
            <td>パスワード変更できます</td>  
        </tr>
                                           <tr>
            <td>注文履歴</td>
            <td>注文一覧確認できます</td>  
        </tr>
                                           <tr>
            <td>出店申込</td>
            <td>店舗になる申請ができます</td>  
        </tr>
    </tbody>
    <tbody>
      <tr>
            <td rowspan=10>店舗側</td>
           <td colspan=2>注文管理</td>
            <td>注文一覧、注文明細、配達先の確認できます</td>
        </tr>
                <tr>
            <td colspan=2>発送管理</td>
            <td>運送会社選択、商品を発送できます</td>
                </tr>
     <tr>
            <td rowspan=2>店舗管理</td>
            <td>店舗情報を登録</td>  
            <td>店舗情報を登録できます</td>            
        </tr> 
       <tr>
            <td>店舗情報を編集</td>  
            <td>店舗情報を登録できます</td>            
        </tr> 
                  <tr>
            <td rowspan=4>商品管理</td>
            <td>商品を新規登録</td>  
            <td>商品を新規登録できます</td>            
        </tr> 
       <tr>
            <td>商品を削除</td>  
            <td>商品を削除できます</td>            
        </tr> 
                  <tr>
            <td>商品の表示非表示</td>  
            <td>イベント商品の表示・非表示</td>            
        </tr> 
                  <tr>
            <td>商品情報編集</td>  
            <td>商品情報編集できます</td>            
        </tr> 
     </tbody>
      <tbody>
              <tr>
            <td rowspan=13>管理員</td>
           <td colspan=2>注文管理</td>
            <td>注文一覧、注文明細、配達先の確認できます</td>
        </tr>  
            <tr>
            <td rowspan=3>店舗管理</td>
            <td>店舗申込</td>  
            <td>店舗申請を承認する</td>            
        </tr> 
       <tr>
            <td>店舗状態変更</td>  
            <td>店舗状態を変更できます</td>            
        </tr> 
                <tr>
            <td>店舗情報を編集</td>  
            <td>店舗情報を編集できます</td>            
        </tr>          
        <tr>
            <td rowspan=3>商品管理</td>
            <td>商品を削除</td>  
            <td>商品を削除できます</td>             
        </tr> 
       <tr>
            <td>商品の表示非表示</td>  
            <td>イベント商品の表示・非表示</td>            
        </tr> 
        <tr>
            <td>商品情報編集</td>  
            <td>商品情報編集できます</td>            
        </tr> 
           <tr>
            <td rowspan=2>ユーザ管理</td>
            <td>ユーザ一覧</td>  
            <td>ユーザ一覧確認できます</td>            
        </tr> 
       <tr>
            <td>ユーザ状態変更</td>  
            <td>ユーザ状態を変更できます</td>            
        </tr>    
                   <tr>
            <td rowspan=2>ユーザ情報管理</td>
            <td>ユーザ情報一覧</td>  
            <td>ユーザ情報一覧確認できます</td>            
        </tr> 
       <tr>
            <td>ユーザ情報編集</td>  
            <td>ユーザ情報を編集できます</td>            
        </tr>           
         <tr>
            <td rowspan=2>ユーザ届け住所管理</td>
            <td>ユーザ届け住所一覧</td>  
            <td>ユーザ届け住所一覧できます</td>            
        </tr> 
       <tr>
            <td>ユーザ届け住所編集</td>  
            <td>ユーザ届け住所編集できます</td>            
        </tr>                          
        </tbody>
       
            
            
</table>






# 1. 開発環境
#### 1-1　IDE：Eclipse

#### 1-2　OS：Windows10

#### 1-3　JDK：Java11

#### 1-4　フレームワーク：SpringBoot2.4.3

#### 1-5　データベース：PostgreSQL

#### 1-6　ソース管理：Github

#### 1-7　テンプレートエンジン：Thymeleaf

#### 1-8　画面：Html、JavaScript、jQuery、Ajax、Bootstrap

#### 1-9　テスト：Junit

# 2. 構成
#### 2-1　ER図


![ER図](https://github.com/kiku-991/cecily/blob/master/image/ER%E5%9B%B3.png)



#### 2-2　システム構成

![システム構成](https://github.com/kiku-991/cecily/blob/master/image/MVC.png)


#### 2-3　Role構成

1. 一般ユーザ：
     - 個人情報編集
     - パスワード変更
     - 届け住所追加
     - アイコンをアップロードする
     - 商品名、商品価格、時間によって商品検索
     - 商品を気に入りに追加、削除
     - 商品を買い物かごに追加、削除
     - オーダーする、キャンセル
     - 支払い
     - 荷物確認
2. 店舗：
     - 店舗情報編集
     - 商品管理
     - 注文管理
3. 管理員：
     - ユーザ管理
     - 店舗管理
     - 商品管理
     - 注文管理

# 3. 画面

![画面遷移図](https://github.com/kiku-991/cecily/blob/master/image/%E7%94%BB%E9%9D%A2%E9%81%B7%E7%A7%BB%E5%9B%B3.png)
　
　
#### 3-1　メイン画面

![メイン画面](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%9B%E3%83%BC%E3%83%A0%E3%83%9A%E3%83%BC%E3%82%B8.png)

#### 3-2　登録
![登録](https://github.com/kiku-991/cecily/blob/master/image/%E7%99%BB%E9%8C%B2.png)

#### 3-3　ログイン画面
![ログイン](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3.png)

#### 3-3　個人センター
![個人センター](https://github.com/kiku-991/cecily/blob/master/image/%E5%80%8B%E4%BA%BA%E3%82%BB%E3%83%B3%E3%82%BF%E3%83%BC.png)

#### 3-4　会員情報登録・変更
![会員情報登録・変更](https://github.com/kiku-991/cecily/blob/master/image/%E4%BC%9A%E5%93%A1%E6%83%85%E5%A0%B1%E7%99%BB%E9%8C%B2%E5%A4%89%E6%9B%B4.png)

#### 3-5　届け住所登録・変更
![届け住所登録・変更](https://github.com/kiku-991/cecily/blob/master/image/%E5%B1%8A%E3%81%91%E4%BD%8F%E6%89%80%E7%99%BB%E9%8C%B2%E5%A4%89%E6%9B%B4.png)

#### 3-6　パスワード変更
![パスワード変更](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%91%E3%82%B9%E3%83%AF%E3%83%BC%E3%83%89%E5%A4%89%E6%9B%B41.png)
![パスワード変更](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%91%E3%82%B9%E3%83%AF%E3%83%BC%E3%83%89%E7%A2%BA%E8%AA%8D.png)

#### 3-7　お気に入り一覧
![お気に入り一覧](https://github.com/kiku-991/cecily/blob/master/image/%E3%81%8A%E6%B0%97%E3%81%AB%E5%85%A5%E3%82%8A%E4%B8%80%E8%A6%A7.png)

#### 3-8　マイ注文
![注文一覧](https://github.com/kiku-991/cecily/blob/master/image/myOrder.png)

#### 3-9　出店申込
![出店申込](https://github.com/kiku-991/cecily/blob/master/image/%E5%87%BA%E5%BA%97%E7%94%B3%E8%BE%BC.png)

#### 3-10　パスワード忘れ
![パスワード忘れ](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%91%E3%82%B9%E3%83%AF%E3%83%BC%E3%83%89%E5%BF%98%E3%82%8C.png)

#### 3-11　商品一覧表示
![商品一覧](https://github.com/kiku-991/cecily/blob/master/image/%E5%95%86%E5%93%81%E4%B8%80%E8%A6%A7.png)

#### 3-12　商品検索
![商品検索](https://github.com/kiku-991/cecily/blob/master/image/%E6%A4%9C%E7%B4%A2.png)

#### 3-13　商品詳細
![商品詳細](https://github.com/kiku-991/cecily/blob/master/image/%E5%95%86%E5%93%81%E8%A9%B3%E7%B4%B0.png)

#### 3-13　ユーザ一覧表示
![ユーザー一覧](https://github.com/kiku-991/cecily/blob/master/image/%E3%83%A6%E3%83%BC%E3%82%B6%E4%B8%80%E8%A6%A7.png)

#### 3-14　店舗一覧表示
![店舗一覧表示](https://github.com/kiku-991/cecily/blob/master/image/%E5%BA%97%E8%88%97%E7%AE%A1%E7%90%86.png)

#### 3-15　買い物かご
![買い物かご](https://github.com/kiku-991/cecily/blob/master/image/mycart.png)

....続きの画面はプロジェクトにて確認してください

# 4. テスト
Junitを使って、テストを行う
![テスト](https://github.com/kiku-991/cecily/blob/master/image/%E7%B0%A1%E5%8D%98%E3%81%AA%E3%83%86%E3%82%B9%E3%83%88.png)

# 5. 後ろ書き
自分で要件定義からテストまでを設計しましたので、不合理的な部分がありますが，これから開発に頑張りたいと思います。


