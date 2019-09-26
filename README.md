# 开发指南
### 各种材料地址
1.页面原型：https://www.xiaopiu.com/h5/byId?type=project&id=5ce3a151d8c9673c03f42e13

2.校友会公众号需求说明书：https://shimo.im/docs/JhzmOzI5QOEhhpLY/ 

3.校友公众平台需求思维导图：https://shimo.im/mindmaps/Qz3YTUbiX3Q1IiwS/ 

### 阿里云发短信包可能下载不到需要本地安装，jar包在extralib下
mvn install:install-file -DgroupId=com.aliyun.mns -DartifactId=aliyun-java-sdk-dybaseapi -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/aliyun-java-sdk-dybaseapi-1.0.0.jar

mvn install:install-file -DgroupId=com.aliyun.alicom -DartifactId=alicom-mns-receive-sdk -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/alicom-mns-receive-sdk-1.0.0.jar

### 请确保安装了lombok插件


### 为了方便后端快速开发和测试，增加鉴权mock类
> 使用方法 打开配置文件中的 mock.open=true 既可以

### 打开登录模拟后，使用postman等接口测试工具进行接口测试
> 模拟登录后返回数据,拿到token，放入要测试的请求头部，X-Alumniassociation-Token=[这里放token，括号不要]
```$xslt
{
    "code": 1,
    "msg": "OK",
    "data": {
        "userInfo": {
            "id": 11,
            "realName": null,
            "nickName": "江理小星",
            "birthday": null,
            "sex": 1,
            "phoneNum": null,
            "entranceTime": null,
            "graduationTime": null,
            "college": null,
            "major": null,
            "currLocation": null,
            "industrySkill": null,
            "hobby": null,
            "personalProfile": null,
            "userStatus": false,
            "createTime": null,
            "updateTime": null,
            "wechatImage": "/mock/pic",
            "openId": "mockOpenIdabf1b608-e134-436f-9006-e008f3bc2c60",
            "score": 0.0,
            "sessionKey": "mockSessionKeye15b9862-5485-404c-9799-ae1fa3392590",
            "lastLoginTime": null,
            "lastLoginIp": "0:0:0:0:0:0:0:1"
        },
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0aGlzIGlzIEFsdW1uaWFzc29jaWF0aW9uIHRva2VuIiwiYXVkIjoiTUlOSUFQUCIsImlzcyI6IkFsdW1uaWFzc29jaWF0aW9uIiwiZXhwIjoxNTY5NTE2MDAwLCJ1c2VySWQiOjExLCJpYXQiOjE1Njk1MDg4MDB9.GzHslX36g64pXs0KmTRj8r0bJYi4o0rcRik_cBwejkA"
    }
}
```
