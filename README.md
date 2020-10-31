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

### 所有状态
User Order状态:
```text
 NEW = 1;
 APPROVED = 2;
 CLOSED = 3;
 PROCESSING = 4;
 FINISH = 5;

```


Parcel状态:
```text
waiting 
problem
verify
```

用户权限：
```text
admin
user
```

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
创建order
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

用id找到对应order
GET /api/order/find/{orderId}
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
	"senderPostCode":712099，
	"issueMessage":#####,
	"pathInfo":***,
	....
}
```
找到用户ID对应的所有包裹
GET /api/order/findAll/${userId}

找到该用户所有包裹
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

approve一个订单

PUT /api/order/approve/user/{userId}/userorder/{userOrderId}

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

提交一个订单
PUT /api/order/submit/user/{userId}/userorder/{userOrderId}

拒绝一个订单

PUT /api/order/close/user/{userId}/userorder/{userOrderId}

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
开始处理一个订单

PUT /api/order/processing/user/{userId}/userorder/{userOrderId}

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
结束一个订单

PUT /api/order/finish/user/{userId}/userorder/{userOrderId}

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

报错一个订单

PUT /api/order/issue/user/{userId}/userorder/{userOrderId}

```json
{
    "id": 1,
    "userId": 2,
    "statusId": 7,
    "price": null,
    "description": "111",
    "receiverName": "1111",
    "receiverPhone": "11111",
    "receiverAddress": "???/Quebec/1111111/1111",
    "receiverPostCode": "1111",
    "orderId": "YSfb0bdd4c-cb56-4a28-b6b7-21f098f2bf5c",
    "trackNumber": null,
    "senderName": null,
    "senderAddress": null,
    "senderPhone": null,
    "senderPostCode": null,
    "orderComment": null,
    "pathInfo": null,
    "weight": null,
    "volumn": null,
    "expectDeliveryDate": null,
    "createAt": "2020-09-20T14:16:46.000+0000",
    "modifiedAt": "2020-09-20T15:30:22.000+0000",
    "paymentInfo": null
}
```
Issue一个报错

PUT /update/issue/message

```json
{
   "id": 1,
      "userId": 1,
      "statusId": 2,
      "price": null,
      "description": "asdasda",
      "receiverName": "asdasd",
      "receiverPhone": "asdasd",
      "receiverAddress": "????/ontario/dasd/asdasd",
      "receiverPostCode": "asdasd",
      "orderId": "YSeca87866-d292-4395-a802-b97a3109c0f2",
      "trackNumber": null,
      "senderName": null,
      "senderAddress": null,
      "senderPhone": null,
      "senderPostCode": null,
      "orderComment": null,
      "pathInfo": null,
      "weight": null,
      "volumn": null,
      "expectDeliveryDate": null,
      "createAt": "2020-10-03T20:13:02.000+0000",
      "modifiedAt": "2020-10-03T20:17:46.000+0000",
      "paymentInfo": null,
      "issueMessage": "123"
}
```
找到附带所有包裹信息的订单信息  格式啊咋调整？？？？
GET /api/order/find/user/{userId}

[
{
        "id": 60,
        "userId": 15,
        "statusId": 3,
        "price": 356.00,
        "description": "",
        "receiverName": "???",
        "receiverPhone": "3452647836",
        "receiverAddress": "Canada/Quebec/1411 du fort street/",
        "receiverPostCode": "H3H2N7",
        "orderId": "YS2bbfc39b-43aa-42ba-b3d1-43972a6d42cc",
        "trackNumber": null,
        "senderName": null,
        "senderAddress": null,
        "senderPhone": null,
        "senderPostCode": null,
        "parcels": [
            {
                "id": 20,
                "orderNumber": "EMS 1224536799",
                "senderName": "mama",
                "senderAddress": "2345 brossard",
                "senderPhone": "qq",
                "senderPostCode": "N4H2M7",
                "userId": 15,
                "receiverName": "",
                "receiverAddress": "",
                "receiverPhone": "",
                "receiverPostCode": "",
                "contentType": "??",
                "description": "jumpjump",
                "parcelStatus": "verify",
                "userOrderId": 60,
                "createAt": "2020-07-05T21:03:12.000+0000",
                "modifiedAt": "2020-10-13T23:33:05.000+0000",
                "comment": null
            },
            {
                "id": 41,
                "orderNumber": "SF?? 2345678",
                "senderName": "liuqingqing",
                "senderAddress": "1411 du fort street",
                "senderPhone": "2345768",
                "senderPostCode": "H3H2N7",
                "userId": 15,
                "receiverName": "",
                "receiverAddress": "",
                "receiverPhone": "",
                "receiverPostCode": "",
                "contentType": "????",
                "description": "erftgyiphnoe",
                "parcelStatus": "verify",
                "userOrderId": 60,
                "createAt": "2020-09-30T15:23:07.000+0000",
                "modifiedAt": "2020-10-13T23:33:05.000+0000",
                "comment": null
            }
        ]
    },]

#### Parcel API
创建包裹
POST /api/parcel/create

body:
```json
{
	"senderName":"qinwei luo",
	"senderAddress":"montreal canada",
	"senderPhone":"5148399106",
	"senderPostCode":"H3H1K6",
	"userId":7,
	"receiverName":"xxx",
	"receiverAddress":"montreal canada",
	"receiverPhone":"xxxx",
	"receiverPostCode":"xxxxxx",
	"contentType":"cloth",
	"description":"some cloth and some book",
	"userOrderId":4
}
```

return
```json
{
    "id": 2,
    "orderNumber": null,
    "senderName": "qinwei luo",
    "senderAddress": "montreal canada",
    "senderPhone": "5148399106",
    "senderPostCode": "H3H1K6",
    "userId": 7,
    "receiverName": "xxx",
    "receiverAddress": "montreal canada",
    "receiverPhone": "xxxx",
    "receiverPostCode": "xxxxxx",
    "contentType": "cloth",
    "description": "some cloth and some book",
    "parcelStatus": "posted",
    "userOrderId": 4
}
```

删除包裹
DELETE /api/parcel/delete/parcel/{parcelId}/userorder/{parcelUserOrderId}

无返回值

移除包裹
PUT /api/parcel/moveout/parcel/{parcelId}/userorder/{parcelUserOrderId}

无返回值

返回值:
```json
{
    "id": 1,
    "orderNumber": "111",
    "senderName": "qinwei luo(change)",
    "senderAddress": "montreal canada",
    "senderPhone": "5148399106",
    "senderPostCode": "H3H1K6",
    "userId": 7,
    "receiverName": "xxx",
    "receiverAddress": "montreal canada",
    "receiverPhone": "xxxx",
    "receiverPostCode": "xxxxxx",
    "contentType": "cloth",
    "description": "some cloth and some book",
    "parcelStatus": "waiting",
    "userOrderId": -1
}
```

找到用户order下所有包裹：
GET /api/parcel/findAll/{userOrderId}

返回值：
```json
[
    {
        "id": 1,
        "orderNumber": null,
        "senderName": "qinwei luo",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "posted",
        "userOrderId": 1
    }
]
```

找到用户所有包裹：
GET /api/parcel/findAll/userid/{userId}

返回值：
```json
[
    {
        "id": 1,
        "orderNumber": "111",
        "senderName": "qinwei luo(change)",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "waiting",
        "userOrderId": -1
    },
    {
        "id": 3,
        "orderNumber": "xxxxxx(快递单号+公司)",
        "senderName": "qinwei luo",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "waiting",
        "userOrderId": -1
    }
]
```

找到用户所有包裹：
GET /api/parcel/findAll/username/{userName}

返回值：
```json
[
    {
        "id": 1,
        "orderNumber": "111",
        "senderName": "qinwei luo(change)",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "waiting",
        "userOrderId": -1
    },
    {
        "id": 3,
        "orderNumber": "xxxxxx(快递单号+公司)",
        "senderName": "qinwei luo",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "waiting",
        "userOrderId": -1
    }
]
```


更新包裹信息：
PUT /api/parcel/update

body:
```json
   {
           "id": 1,
           "orderNumber": "111",
           "senderName": "qinwei luo(change)",
           "senderAddress": "montreal canada",
           "senderPhone": "5148399106",
           "senderPostCode": "H3H1K6",
           "userId": 7,
           "receiverName": "xxx",
           "receiverAddress": "montreal canada",
           "receiverPhone": "xxxx",
           "receiverPostCode": "xxxxxx",
           "contentType": "cloth",
           "description": "some cloth and some book",
           "parcelStatus": "posted",
           "userOrderId": 4
       }
```

返回值：
```json
{
    "id": 1,
    "orderNumber": "111",
    "senderName": "qinwei luo(change)",
    "senderAddress": "montreal canada",
    "senderPhone": "5148399106",
    "senderPostCode": "H3H1K6",
    "userId": 7,
    "receiverName": "xxx",
    "receiverAddress": "montreal canada",
    "receiverPhone": "xxxx",
    "receiverPostCode": "xxxxxx",
    "contentType": "cloth",
    "description": "some cloth and some book",
    "parcelStatus": "posted",
    "userOrderId": 4
}
```
 
 在order中移动包裹
 /api/parcel/move?newOrderId=3&orginOrderId=1
 
 body:
 [1,2,3]
 
 返回值：
 ```json
[
    {
        "id": 1,
        "orderNumber": "111",
        "senderName": "qinwei luo(change)",
        "senderAddress": "montreal canada",
        "senderPhone": "5148399106",
        "senderPostCode": "H3H1K6",
        "userId": 7,
        "receiverName": "xxx",
        "receiverAddress": "montreal canada",
        "receiverPhone": "xxxx",
        "receiverPostCode": "xxxxxx",
        "contentType": "cloth",
        "description": "some cloth and some book",
        "parcelStatus": "posted",
        "userOrderId": 3
    }
]
```


更改包裹Parcel状态                            
PUT /api/parcel/{parcelId}/waiting    
                                      
PUT /api/parcel/{parcelId}/problem                  

PUT /api/parcel/{parcelId}/verify


#### 历史订单 order history

创建历史订单:
POST /api/orderhistory/create

body：
```json
{
	"userId":7,
	"userOrderId":4,
	"comment":"excellent",
	"score":10
}
```

return

```json
{
    "id": 1,
    "userId": 7,
    "userOrderId": 4,
    "finishTime": null,
    "comment": "excellent",
    "score": 10
}
```

获取 仅仅评论 不需要权限即可取得
GET /api/orderhistory/findAll/info


```json
[ 
   {
	"score": 10,
	"comment": "ugugugugu ulishey shejchsg bhyfge khsue bvhfndge shiwejc baklalalabalablablab",
	"id": 21,
	"userName": "lele",
	"createAt": "2020-08-05T17:23:50.000+0000"
    },
    {
	"score": 8,
	"comment": "very goood",
	"id": 23,
	"userName": "lele",
	"createAt": "2020-08-05T17:28:30.000+0000"
    },

]
```

GET /orderhistory
获取所有 没有排序

获取所有history和order 可以调整排序
GET /api/orderhistory/findAll?sort=asc&sortBy=created
<br>
参数列表 sortBy(created、score) |   sort(asc, des)  
```json
[
    {
        "orderHistory": {
            "id": 1,
            "userId": 7,
            "userOrderId": 4,
            "finishTime": null,
            "comment": "1",
            "score": 10,
            "createAt": "2020-07-19T15:42:25.000+0000",
            "modifiedAt": "2020-07-19T15:42:51.000+0000",
            "deleted": false
        },
        "order": {
            "id": 4,
            "userId": 7,
            "statusId": 1,
            "price": null,
            "description": "append some thing and i want to modify",
            "receiverName": "number 7 lqw",
            "receiverPhone": "5148399106",
            "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
            "receiverPostCode": "H3H1K6",
            "orderId": null,
            "trackNumber": null,
            "senderName": "lao7",
            "senderAddress": "china",
            "senderPhone": "300",
            "senderPostCode": "712099",
            "orderComment": null,
            "pathInfo": null,
            "weight": null,
            "volumn": null,
            "expectDeliveryDate": null,
            "modifiedAt": "2020-07-19T15:42:26.000+0000",
            "paymentInfo": null,
            "deleted": false
        }
    },
    {
        "orderHistory": {
            "id": 3,
            "userId": 7,
            "userOrderId": 4,
            "finishTime": null,
            "comment": "excellent",
            "score": 10,
            "createAt": "2020-07-19T20:35:04.000+0000",
            "modifiedAt": "2020-07-19T20:35:04.000+0000",
            "deleted": false
        },
        "order": {
            "id": 4,
            "userId": 7,
            "statusId": 1,
            "price": null,
            "description": "append some thing and i want to modify",
            "receiverName": "number 7 lqw",
            "receiverPhone": "5148399106",
            "receiverAddress": "2100 Boulevard De Maisonneu,Montreal,Quebec,Canada",
            "receiverPostCode": "H3H1K6",
            "orderId": null,
            "trackNumber": null,
            "senderName": "lao7",
            "senderAddress": "china",
            "senderPhone": "300",
            "senderPostCode": "712099",
            "orderComment": null,
            "pathInfo": null,
            "weight": null,
            "volumn": null,
            "expectDeliveryDate": null,
            "modifiedAt": "2020-07-19T15:42:26.000+0000",
            "paymentInfo": null,
            "deleted": false
        }
    },
]
```

PUT /orderhistory/update/1  （暂未使用）
```json
{
    "id": 1,
    "userId": 7,
    "userOrderId": 4,
    "finishTime": null,
    "comment": "finish",
    "score": 10
}
```

删除（一旦删除 这整条History都会消失 所以要谨慎 是否可以设置屏蔽功能？选择隐藏这条评价，然后findAll的时候将无法拿到这条评价去给客户看到 以免有恶意评价出现）
DELETE /api/orderhistory/delete/{historyId}

按用户查找（可以直接搜索 暂时没使用）
GET /api/orderhistory/findAll/info/{userId}

### 新增 2020.7.25
找到所有parcel并按照modified排序

GET /api/parcel

按状态找parcel

GET /api/parcel/status/waiting

GET /api/parcel/status/problem

GET /api/parcel/status/verify

按照状态找order

GET /api/order

GET /api/order/status/{statusId}

### 新增 2020.09.01

忘记密码，更新密码

POST /api/forget/password?userEmail=qinwei@livebarn.com
<br>

表单提交,发送邮件


POST /api/restpassword
```json
{
    "username":"lqw",
    "email":"qinwei@livebarn.com",
    "oldPassword":"12345",
    "newPassword":"123456"
}
```

Response:
```json
{
    "code": 200,
    "message": "Update Success"
}
```


无效Session:

POST /api/session/invalidate


### 新增 2020.09.17

计算器

POST /api/calculate

输入
```json
{
    "countryId":115,
    "length":15.00,
    "hight":15.00,
    "width":20.00,
    "weight":2.600
}
```

返回
```json
[
    {
        "route": "燕文航空挂号-普货",
        "price": "425.00"
    },
    {
        "route": "燕文专线追踪小包-普货",
        "price": "638.00"
    },
    {
        "route": "燕文专线追踪小包-特货",
        "price": "670.00"
    },
    {
        "route": "中邮华南E特快",
        "price": "435.00"
    },
    {
        "route": "燕文专线快递-普货",
        "price": "730.00"
    }
]
```


国家查询

按照中文名查询（支持模糊查询）

GET /api/country/chinesename/{chineseName}

例子：
/api/country/chinesename/美

返回
```json
[
    {
        "id": 115,
        "twoLetterCode": "US",
        "name": "America",
        "chineseName": "美国"
    },
    {
        "id": 263,
        "twoLetterCode": "AM",
        "name": "Armenia",
        "chineseName": "亚美尼亚"
    },
    {
        "id": 2788,
        "twoLetterCode": "ST",
        "name": "Sao Tome and Princip",
        "chineseName": "圣多美和普林西比"
    },
    {
        "id": 2834,
        "twoLetterCode": "UM",
        "name": "United States Minor ",
        "chineseName": "美国本土外小岛屿"
    },
    {
        "id": 373,
        "twoLetterCode": "AS",
        "name": "Amercian Samoa",
        "chineseName": "美属萨摩亚"
    },
    {
        "id": 374,
        "twoLetterCode": "VI",
        "name": "The United States Vi",
        "chineseName": "美属维尔京群岛"
    }
]
```

按照英文名查询（支持模糊查询）

GET /api/country/name/{name}

例子：
/api/country/name/ab

返回
```json
[
    {
        "id": 354,
        "twoLetterCode": "AE",
        "name": "United Arab Emirates",
        "chineseName": "阿联酋"
    },
    {
        "id": 377,
        "twoLetterCode": "SA",
        "name": "Saudi Arabia",
        "chineseName": "沙特阿拉伯"
    },
    {
        "id": 50,
        "twoLetterCode": "GA",
        "name": "Gabon",
        "chineseName": "加蓬"
    },
    {
        "id": 56,
        "twoLetterCode": "ZW",
        "name": "Zimbabwe",
        "chineseName": "津巴布韦"
    }
]
```


查询所有

GET /api/country

路线信息

GET /api/routeinfo

```json
[
    {
        "id": 1,
        "name": "燕文航空挂号-普货"
    },
    {
        "id": 2,
        "name": "燕邮宝挂号-特货"
    },
    {
        "id": 3,
        "name": "燕文专线追踪小包-普货"
    },
    {
        "id": 4,
        "name": "燕文专线追踪小包-特货"
    },
    {
        "id": 5,
        "name": "中邮华南E特快"
    },
    {
        "id": 6,
        "name": "燕文专线快递-普货"
    }
]
```

### 新增 2020.10.03
查询该用户所有优惠券

GET  /api/promotion/user/{userId}

return:
```json
[
    {
        "id": 1,
        "promotionCode": "8daa74d4-52b9-44cd-8fb3-204e7575ed3c",
        "userId": 21,
        "discount": null,
        "price": 5,
        "promotionTypeId": 1,
        "expireDate": null,
        "validate": true,
        "createAt": "2020-10-04T19:29:34.000+0000"
    },
    {
        "id": 2,
        "promotionCode": "39b4dfb7-07aa-4454-9584-d8e87b4a8cef",
        "userId": 21,
        "discount": null,
        "price": 5,
        "promotionTypeId": 2,
        "expireDate": null,
        "validate": true,
        "createAt": "2020-10-04T19:29:51.000+0000"
    }
]
```

创建现金优惠券（管理员使用）

POST /api/promotion/price/user/{userId}/price/{price}

创建折扣优惠券（管理员使用）

POST /api/promotion/discount/user/{userId}/discount/{price}


支付

POST /api/payment
```json
{
    "userId":7,
    "userEmail":"qinwei@livebarn.com",
    "userName":"lqw",
    "orderId":3,
    "promotionCode":"aba12ec6-5572-4aed-9e74-b31ec4484186",
    "paymentTypeId":1,
    "price":21.00,
    "paid":21.00,
    "actualPaid":null
}
```

返回
```json
{
    "id": 4,
    "userId": 7,
    "userEmail": "qinwei@livebarn.com",
    "userName": "lqw",
    "orderId": 3,
    "promotionCode": "aba12ec6-5572-4aed-9e74-b31ec4484186",
    "price": 21.00,
    "paid": 21.00,
    "actualPaid": null,
    "validate": false,
    "paidAt": "2020-10-04T20:22:38.000+0000"
}
```
查询用户支付:

GET /api/payment/user/{userId}
```json
[
    {
        "id": 1,
        "userId": 7,
        "userEmail": "qinwei@livebarn.com",
        "userName": "lqw",
        "orderId": 3,
        "promotionCode": null,
        "price": 21.00,
        "paid": 21.00,
        "actualPaid": null,
        "validate": false,
        "paidAt": "2020-10-04T20:17:23.000+0000"
    }
]
```

设置实际付钱数

POST /api/payment/paid/{acutalPaid}/payment/{paymentId}

actualPaid: 保留两位小数

```json
{
    "id": 1,
    "userId": 7,
    "userEmail": "qinwei@livebarn.com",
    "userName": "lqw",
    "orderId": 3,
    "promotionCode": null,
    "price": 21.00,
    "paid": 21.00,
    "actualPaid": 21.00,
    "validate": false,
    "paidAt": "2020-10-04T20:17:23.000+0000"
}
```

验证支付

POST /api/payment/validate/{paymentId}

```json
{
    "id": 1,
    "userId": 7,
    "userEmail": "qinwei@livebarn.com",
    "userName": "lqw",
    "orderId": 3,
    "promotionCode": null,
    "price": 21.00,
    "paid": 21.00,
    "actualPaid": 21.00,
    "validate": true,
    "paidAt": "2020-10-04T20:17:23.000+0000"
}
```

查询支付方式：

GET /api/paymenttype

```json
[
    {
        "id": 1,
        "name": "wechat"
    },
    {
        "id": 2,
        "name": "alipay"
    }
]
```

### 新增 2020.10.17

现在所有订单在支付验证之后，订单状态会变为processing，相应的包裹状态会变为shipping

在用户确认收到订单之后，订单状态会变成finish，相应的包裹状态会变成finish

PUT /api/parcel/{parcelId}/shipping
```json
{
    "id": 8,
    "orderNumber": "SF顺丰 SF123113",
    "senderName": "Supreme",
    "senderAddress": "陕西省 西安市",
    "senderPhone": "",
    "senderPostCode": "1234",
    "userId": 21,
    "receiverName": "",
    "receiverAddress": "",
    "receiverPhone": "",
    "receiverPostCode": "",
    "contentType": "普货",
    "description": "衣服",
    "parcelStatus": "shipping",
    "userOrderId": 11,
    "createAt": "2020-10-11T23:02:07.000+0000",
    "modifiedAt": "2020-10-17T22:24:28.000+0000",
    "comment": null
}
```

查找左右状态为shipping的包裹

GET /api/parcel/status/shipping

设置包裹状态为finish

PUT /api/parcel/{parcelId}/finish

查找左右状态为finish的包裹

GET /api/parcel/status/shipping

用户订单统计

GET /api/order/statistics/user/{userId}
```json
{
    "NEW": 0,
    "ISSUE": 0,
    "CLOSED": 0,
    "SUBMIT": 0,
    "PROCESSING": 1,
    "APPROVED": 1,
    "FINISH": 2
}
```

GET /api/parcel/statistics/user/{userId}
```json
{
    "problem": 0,
    "waiting": 0,
    "shipping": 1,
    "verify": 1,
    "finish": 0
}
```
