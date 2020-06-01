# Server part
### Tool
Maven 3<br>
MySQL 8<br>
SpringBoot<br>

### Setup
[mysql安装]https://www.cnblogs.com/yinzhengjie/p/10125609.html<br>
[mysql下载地址]https://dev.mysql.com/downloads/mysql/<br>

mysql配置<br>(可以自己定义自己的用户名密码，只要表一样就可以了，因为我使用的自动migration，所以不用管，直接run指令就行)<br>
连接url：jdbc:mysql://localhost:3306/logistic?serverTimezone=UTC<br>
username: root<br>
password: 3773032<br>

1、clone 项目<br>
2、进入之后,输入 mvn flyway:migrate<br>
3、mvn spring-boot:run 启动项目

#### 登陆
POST - /api/doLogin 
body{
username: (自己插入),
password: (自己插入),
}

一开始使用 GET - /hello 会返回error，没有登陆，这时候向/doLogin，发送post请求，带着用户名密码去请求。<br>
请求成功会，会收到用户的个人信息，这时候，你就可以根据用户的权限，作出调整，显示不同的页面，目前只有两个角色<br>
admin和customer。

自动登出

POST /api/logout




#### User api:
POST /api/register
body
```json
{
    ("uid":"xxx"),
    "username":"xxx",
    "password":"xxx",
    "phone":"xxx",
    "email":"xxx",
    "address":"xxx"
}
```

return:
```json
{
    "uid":"xxx",
    "username":"xxx",
    "password":"xxx",
    "phone":"xxx",
    "email":"xxx",
    "address":"xxx"
}
```

PUT /api/user/update
```json
{
    ("uid":"xxx"),
    "username":"xxx",
    "password":"xxx",
    "phone":"xxx",
    "email":"xxx",
    "address":"xxx"
}
```

return : 
```json
{
      "uid":"xxx",
      "username":"xxx",
      "password":"xxx",
      "phone":"xxx",
      "email":"xxx",
      "address":"xxx"
 }
```


GET /api/user/findAll
return : 
```json
[
  {
      "uid":"xxx",
      "username":"xxx",
      "password":"xxx",
      "phone":"xxx",
      "email":"xxx",
      "address":"xxx"
  }
]
```

GET /api/user/{username}
<br>return
```json
{
    "uid": 7,
    "username": "lqw",
    "password": null,
    "role": "user",
    "phone": "5148399106",
    "email": "369487836luo@gmail.com",
    "address": "2100 avneue"
}
```

备注：（）中的内容代表可以有也可以没有

#### UserOrder Api
POST /api/order/create
```json
{
	"userId":1,
	"description":"some cloth",
	"receiverName":"luo qin wei",
	"receiverPhone":"5148399106",
	"receiverAddress":"2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
	"receiverPostCode":"H3H1K6",
	"senderName":"zhangsan",
	"senderAddress":"china",
	"senderPhone":"300",
	"senderPostCode":712099
}
```

GET /api/order/findAll/{userId}

return:
```json
[
    {
        "id": 3,
        "userId": 7,
        "statusId": 1,
        "price": null,
        "description": "some book and cloth to canada",
        "receiverName": "number 7 lqw",
        "receiverPhone": "5148399106",
        "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
        "receiverPostCode": "H3H1K6",
        "orderId": null,
        "trackNumber": null,
        "senderName": "lao7",
        "senderAddress": "china",
        "senderPhone": "300",
        "senderPostCode": "712099"
    },
    {
        "id": 4,
        "userId": 7,
        "statusId": 1,
        "price": null,
        "description": "append some thing",
        "receiverName": "number 7 lqw",
        "receiverPhone": "5148399106",
        "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
        "receiverPostCode": "H3H1K6",
        "orderId": null,
        "trackNumber": null,
        "senderName": "lao7",
        "senderAddress": "china",
        "senderPhone": "300",
        "senderPostCode": "712099"
    }
]
```

PUT /api/order/approve/{userId}/{userOrderId}

```json
{
    "id": 3,
    "userId": 7,
    "statusId": 2,
    "price": null,
    "description": "some book and cloth to canada",
    "receiverName": "number 7 lqw",
    "receiverPhone": "5148399106",
    "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
    "receiverPostCode": "H3H1K6",
    "orderId": null,
    "trackNumber": null,
    "senderName": "lao7",
    "senderAddress": "china",
    "senderPhone": "300",
    "senderPostCode": "712099"
}
```

PUT /api/order/close/{userId}/{userOrderId}

```json
{
    "id": 3,
    "userId": 7,
    "statusId": 3,
    "price": null,
    "description": "some book and cloth to canada",
    "receiverName": "number 7 lqw",
    "receiverPhone": "5148399106",
    "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
    "receiverPostCode": "H3H1K6",
    "orderId": null,
    "trackNumber": null,
    "senderName": "lao7",
    "senderAddress": "china",
    "senderPhone": "300",
    "senderPostCode": "712099"
}
```

PUT /api/order/processing/{userId}/{userOrderId}

```json
{
    "id": 3,
    "userId": 7,
    "statusId": 4,
    "price": null,
    "description": "some book and cloth to canada",
    "receiverName": "number 7 lqw",
    "receiverPhone": "5148399106",
    "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
    "receiverPostCode": "H3H1K6",
    "orderId": null,
    "trackNumber": null,
    "senderName": "lao7",
    "senderAddress": "china",
    "senderPhone": "300",
    "senderPostCode": "712099"
}
```

PUT /api/order/finish/{userId}/{userOrderId}

```json
{
    "id": 3,
    "userId": 7,
    "statusId": 5,
    "price": null,
    "description": "some book and cloth to canada",
    "receiverName": "number 7 lqw",
    "receiverPhone": "5148399106",
    "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
    "receiverPostCode": "H3H1K6",
    "orderId": null,
    "trackNumber": null,
    "senderName": "lao7",
    "senderAddress": "china",
    "senderPhone": "300",
    "senderPostCode": "712099"
}
```
