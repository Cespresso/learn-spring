# Todoy-サーバー
もともとSpring bootの勉強用に使っていたものなので余分なものが含まれています。
(クライアントを作るために急造したのもなのでバリデーションやエラーメッセージ等の整備がしてないです)

### 現在デプロイしてある環境について
||--|
|:------------:|:------------:|
|エンドポイント|https://espresso-dev-api.site|
|サーバー|GCP(Compute Engine f1-micro us-west1-a)|
|OS|centos7|
|データベース|postgresql(インスタンス内インストール)|
|アプリケーションサーバー|tomcat(spring boot組み込み)|
|リバースプロキシ|nginx|
|サーバー証明書|lets encrypt|

### API例
https://espresso-dev-api.site/todo
#### Request
- method

get
- header

authorization : Bearer 認証Token
#### Result
```
[
  {
    "id": 125,
    "user_id": "DSV03ZpHvhXggfRYNTARKXnqr0i1",
    "title": "アイスを買う",
    "completed": false,
    "body": ""
  },
  {
    "id": 127,
    "user_id": "DSV03ZpHvhXggfRYNTARKXnqr0i1",
    "title": "コーヒーを買う",
    "completed": true,
    "body": "豆のまま"
  }
],
```

### 技術等
- Spring boot
- Kotlin
- FirebaseAdmin
