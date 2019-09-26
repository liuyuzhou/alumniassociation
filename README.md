# 开发指南
### 各种材料地址
1.页面原型：https://www.xiaopiu.com/h5/byId?type=project&id=5ce3a151d8c9673c03f42e13

2.校友会公众号需求说明书：https://shimo.im/docs/JhzmOzI5QOEhhpLY/ 

3.校友公众平台需求思维导图：https://shimo.im/mindmaps/Qz3YTUbiX3Q1IiwS/ 

### 阿里云发短信包可能下载不到需要本地安装，jar包在extralib下
mvn install:install-file -DgroupId=com.aliyun.mns -DartifactId=aliyun-java-sdk-dybaseapi -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/aliyun-java-sdk-dybaseapi-1.0.0.jar

mvn install:install-file -DgroupId=com.aliyun.alicom -DartifactId=alicom-mns-receive-sdk -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/alicom-mns-receive-sdk-1.0.0.jar

### 请确保安装了lombok插件
