# 通販システム開発
## 「KIKUSHOP」

SpringBootフレームワークを用いた「KIKUSHOP」という女性向け通販システムを自ら開発しました。<br>
当システムは商品管理、ユーザ管理、注文管理などの機能が付いております。 開発の期間は約2ヶ月でした。<br>

<hr/>

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
            <td rowspan=9>共通</td>
            <td colspan=2>登録</td>
            <td>新規アカウント作成＆ログイン画面に遷移する</td>
        </tr>
        <tr>
             <td colspan=2>ログイン</td>
            <td>ログインする＆ホームページに遷移する</td>
        </tr>
        <tr>
            <td colspan=2>パスワード忘れ</td>
            <td>パスワードを忘れる場合、検証を行う、再設定ができる</td>
        </tr>
                 <tr>
            <td colspan=2>パスワード変更</td>
            <td>パスワードの変更ができる</td>
        </tr>
                 <tr>
            <td colspan=2>パスワード暗号化</td>
            <td>パスワードを暗号化する</td>
        </tr>
        <tr>
            <td colspan=2>ログアウト</td>
            <td>ログアウトすると、ホームページへ遷移する</td>
        </tr>
        <tr>
            <td colspan=2>インターセプター</td>
            <td>未ログインまたは未登録の場合、マイページアクセス制御、買い物制御</td>
        </tr>
        <tr>
            <td colspan=2>会員情報登録</td>  
            <td>お客様の情報を新規登録する</td>                         
        </tr>       
        <tr>
            <td colspan=2>会員情報編集</td>
            <td>お客様の情報を編集する</td>  
        </tr>
          </tbody>
         <tbody>
         <tr>
            <td rowspan=16>ユーザ側</td>
            <td colspan=2>商品一覧</td>
            <td>ホームページにて商品一覧を表示する(商品発表時間順によってソート)</td>
        </tr>
        <tr>
            <td colspan=2>商品詳細</td>
            <td>商品の詳細情報を表示する</td>  
        </tr>
        <tr>
            <td colspan=2>商品検索</td>
            <td>キーワード、商品名、商品価格、更新時間によって検索、その条件に応じる商品が検索される</td>  
        </tr>
        <tr>
            <td colspan=2>買い物かご</td>
            <td>商品を買い物かごに入れ、数量増加減少、在庫数量確認、買い物かご一覧確認できる</td>  
        </tr>
        <tr>
            <td colspan=2>お気に入り</td>
            <td>商品をお気に入り、お気に入り一覧表示できる</td>  
        </tr>
        <tr>
            <td colspan=2>注文</td>
            <td>注文する</td>  
        </tr>
         <tr>
            <td colspan=2>注文キャンセル</td>
            <td>注文をキャンセルできる</td>  
        </tr>            
        <tr>
            <td colspan=2>決済</td>
            <td>Alipay、クレジットカード支払い選択できる</td>  
        </tr>
        <tr>
            <td colspan=2>お届け先情報登録、追加</td>
            <td>お届け先情報登録、多数追加できる</td>            
        </tr>          
        <tr>
            <td colspan=2>お届け先情報編集</td>
            <td>お届け先情報編集する</td>  
        </tr>
        <tr>
            <td colspan=2>お届け先情報削除</td>
            <td>お届け先情報削除する</td>  
        </tr>             
        <tr>
            <td colspan=2>注文履歴</td>
            <td>注文情報一覧確認できる</td>  
        </tr>
        <tr>
            <td colspan=2>出店申込</td>
            <td>店舗申請を出し、申請状態の確認メッセージが表示される</td>  
        </tr>
    </tbody>
    <tbody>
      <tr>
           <td rowspan=10>店舗側</td>
           <td colspan=2>注文管理</td>
            <td>注文一覧を表示、注文明細、配達先の確認できる</td>
      </tr>
      <tr>
            <td colspan=2>発送管理</td>
            <td>運送会社選択、商品を発送できる、商品発送後状態変更（発送待ち、発送済み）</td>
      </tr>
       <tr>
            <td rowspan=2>店舗管理</td>
            <td>店舗情報を登録</td>  
            <td>店舗情報を新規登録する</td>            
        </tr> 
       <tr>
            <td>店舗情報を編集</td>  
            <td>店舗情報を編集できる</td>            
        </tr> 
        <tr>
            <td colspan=2>在庫管理</td>
            <td>商品の在庫数量確認できる</td>         
        </tr> 
         <tr>
            <td rowspan=4>商品管理</td>
            <td>商品を新規登録</td>  
            <td>商品を新規登録する</td>            
        </tr> 
       <tr>
            <td>商品を削除</td>  
            <td>商品を削除する</td>            
        </tr> 
        <tr>
            <td>イベント商品の表示・非表示</td>  
            <td>イベント商品の表示・非表示する</td>            
        </tr> 
        <tr>
            <td>商品情報編集</td>  
            <td>商品情報を編集する</td>            
        </tr> 
     </tbody>
      <tbody>
      <tr>
           <td rowspan=14>管理員</td>
           <td colspan=2>注文管理</td>
           <td>注文一覧、注文明細、配達先の確認できる</td>
       </tr>  
            <tr>
            <td rowspan=3>店舗管理</td>
            <td>店舗申込</td>  
            <td>店舗申請を承認する</td>            
        </tr> 
       <tr>
            <td>店舗状態変更</td>  
            <td>店舗状態を変更できる （状態　出店中　出店中止）</td>            
        </tr> 
        <tr>
            <td>店舗情報を編集</td>  
            <td>店舗情報を編集できる</td>            
        </tr>          
        <tr>
            <td rowspan=3>商品管理</td>
            <td>商品を削除</td>  
            <td>商品を削除できる</td>             
        </tr> 
       <tr>
            <td>商品の表示非表示</td>  
            <td>イベント商品の表示・非表示する</td>            
        </tr> 
        <tr>
            <td>商品情報編集</td>  
            <td>商品情報編集できる</td>            
        </tr>
        <tr>
            <td colspan=2>在庫管理</td>
            <td>商品の在庫数量確認できる</td>         
        </tr>          
           <tr>
            <td rowspan=2>ユーザ管理</td>
            <td>ユーザ一覧</td>  
            <td>ユーザ一覧を表示する</td>            
        </tr> 
       <tr>
            <td>ユーザ状態変更</td>  
            <td>ユーザ状態を変更できる （状態　正常　ブロック）</td>            
        </tr>    
        <tr>
            <td rowspan=2>ユーザ情報管理</td>
            <td>ユーザ情報一覧</td>  
            <td>ユーザ情報一覧を表示する</td>            
        </tr> 
       <tr>
            <td>ユーザ情報編集</td>  
            <td>ユーザ情報を編集できる</td>            
        </tr>           
         <tr>
            <td rowspan=2>ユーザ届け住所管理</td>
            <td>ユーザ届け住所一覧</td>  
            <td>ユーザ届け住所一覧を表示する</td>            
        </tr> 
       <tr>
            <td>ユーザ届け住所編集</td>  
            <td>ユーザ届け住所編集できる</td>            
        </tr>  
        </tbody>
      
            
</table>

<br>

# 1. 開発環境
<table>
   <thead>
        <tr>
            <th>　</th>      
            <th>ツール・環境・言語</th>
        </tr>
    </thead> 
      <tbody>
        <tr>
            <td>IDE</td>
            <td>Eclipse</td>
        </tr>
        <tr>
            <td>OS</td>
            <td>Windows10  [正式環境：Linux centOS 7（リリースつもりです）]</td>
        </tr>  
        <tr>
            <td>JDK</td>
            <td>Java11</td>
        </tr>
        <tr>
            <td>フレームワーク</td>
            <td>SpringBoot2.4.5 (Spring 5.x)</td>
        </tr>                    
        <tr>
            <td>データベース</td>
            <td>PostgreSQL</td>
        </tr>
        <tr>
            <td>ソース管理</td>
            <td>Github </td>
        </tr>                    
        <tr>
            <td>テンプレートエンジン</td>
            <td>Thymeleaf</td>
        </tr>
        <tr>
            <td>画面</td>
            <td>Html、JavaScript、jQuery、Ajax、Bootstrap </td>
        </tr>                    
        <tr>
            <td>テスト</td>
            <td>Junit </td>
        </tr>                     
        </tbody>            
            
</table>       




# 2. 構成
#### 2-1　ER図 (A5M2を使い)


![ER図](https://github.com/kiku-991/cecily/blob/master/image/ER%E5%9B%B3.png)






#### 2-2　Role構成

1. 一般ユーザ：
     - 個人情報編集
     - パスワード変更
     - アイコンをアップロードする
     - 届け住所追加
     - キーワード検索、商品価格、時間によって商品検索
     - 商品を気に入りに追加、削除
     - 商品を買い物かごに追加、削除
     - 注文する、キャンセル
     - 出店申込
     - 支払い
     - 荷物確認
2. 店舗：
     - 個人情報編集
     - パスワード変更
     - アイコンをアップロードする
     - 店舗情報編集
     - 商品管理
     - 在庫管理
     - 注文管理
     - 発送管理
3. 管理員：
     - 個人情報編集
     - パスワード変更
     - アイコンをアップロードする
     - ユーザ管理
     - 店舗管理
     - 在庫管理
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

#### 3-16　注文確認
![注文確認](https://github.com/kiku-991/cecily/blob/master/image/%E6%B3%A8%E6%96%87%E7%A2%BA%E8%AA%8D.png)

#### 3-17　提出成功画面
![提出成功画面](https://github.com/kiku-991/cecily/blob/master/image/%E6%8F%90%E5%87%BA%E6%88%90%E5%8A%9F.png)

....続きの画面はプロジェクトにて確認してください

モバイル端末にも対応できています。

![個人センターモバイル](https://github.com/kiku-991/cecily/blob/master/image/%E5%80%8B%E4%BA%BA%E3%82%BB%E3%83%B3%E3%82%BF%E3%83%BC%E3%83%A2%E3%83%90%E3%82%A4%E3%83%AB%E7%AB%AF%E6%9C%AB.png)


![テスト](https://github.com/kiku-991/cecily/blob/master/image/%E3%82%BF%E3%83%83%E3%82%B0%E3%83%A2%E3%83%90%E3%82%A4%E3%83%AB%E7%AB%AF%E6%9C%AB.png)

# 4. テスト
Junitを使って、テストを行う
![テスト](https://github.com/kiku-991/cecily/blob/master/image/%E7%B0%A1%E5%8D%98%E3%81%AA%E3%83%86%E3%82%B9%E3%83%88.png)

# 5. 後ろ書き
自分で要件定義からテストまでを設計しましたので、不合理的な部分がありますが，これから開発に頑張りたいと思います。<br>
私自身は鞠婧祎のファンであり、且つアイドルのためにプロジェクトを開発したいという思いから、<br>
このプロジェクトが誕生しました!<br>
プロジェクトに記載されているすべての写真の引用は中国のアイドル「鞠婧祎」が公表された写真でございます。<br>
但し、今の段階でこちらのプロジェクトは商用利用ではございません。<br>
今後はリリースつもりで、写真なとを切り替えます。

