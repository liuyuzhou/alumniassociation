 # 阿里云发短信包可能下载不到，本地安装，jar包在extralib下
 mvn install:install-file -DgroupId=com.aliyun.mns -DartifactId=aliyun-java-sdk-dybaseapi -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/aliyun-java-sdk-dybaseapi-1.0.0.jar
 
 mvn install:install-file -DgroupId=com.aliyun.alicom -DartifactId=alicom-mns-receive-sdk -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/cxx/Downloads/dypls_java/msg_sdk/alicom-mns-receive-sdk-1.0.0.jar
 
 # 请确保安装了lombok插件