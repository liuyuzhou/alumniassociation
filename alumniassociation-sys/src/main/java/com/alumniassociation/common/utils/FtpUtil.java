package com.alumniassociation.common.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

public class FtpUtil {
    // ftp服务器ip地址
    private static String FTP_ADDRESS;
    // 端口号
    private static int FTP_PORT;
    // 用户名
    private static String FTP_USERNAME;
    // 密码
    private static String FTP_PASSWORD;
    // 图片路径
    private static String FTP_BASEPATH;
    // FTP名
    private static String FTP_URL;

    private static boolean uploadFile(String fileName,MultipartFile file, InputStream input) {
    	 boolean result = false;  
         FTPClient ftp = new FTPClient(); 
         try {  
             int reply;  
             ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器  
             // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
             ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录  
             reply = ftp.getReplyCode();  
             if (!FTPReply.isPositiveCompletion(reply)) {  
                 ftp.disconnect();  
                 return result;  
             }  
             ftp.setControlEncoding("UTF-8");
             ftp.enterLocalPassiveMode();
             ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
             //切换到上传目录  
             if (!ftp.changeWorkingDirectory(FTP_BASEPATH)) {  
                 //如果目录不存在创建目录  
                 //String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
                 String tempPath = FTP_BASEPATH;  
                 if (!ftp.changeWorkingDirectory(tempPath)) {  
                     if (!ftp.makeDirectory(tempPath)) {  
                         return result;  
                     } else {  
                         ftp.changeWorkingDirectory(tempPath);  
                     }  
                 }  
                   
             }  
             //设置上传文件的类型为二进制类型  
             ftp.setFileType(FTP.BINARY_FILE_TYPE);  
             //上传文件  
             if (!ftp.storeFile(new String(fileName.getBytes("UTF-8"),"iso-8859-1"), input)) {  
                 return result;  
             }  
            
             input.close();  
             ftp.logout();  
             result = true;  
         } catch (IOException e) {  
             e.printStackTrace();  
         } finally {  
             if (ftp.isConnected()) {  
                 try {  
                     ftp.disconnect();  
                 } catch (IOException ioe) {  
                 }  
             }  
         }  
         return result;  
    }

    public static Boolean uploadFile(String path , MultipartFile file, InputStream inputStream, String ftpAddress, int ftpPort,
            String ftpName, String ftpPassWord, String ftpBasePath,String ftpUrl) {
        FTP_ADDRESS = ftpAddress;
        FTP_PORT = ftpPort;
        FTP_USERNAME = ftpName;
        FTP_PASSWORD = ftpPassWord;
        FTP_BASEPATH = ftpBasePath;
        FTP_URL = ftpUrl;
        uploadFile(path,file,inputStream);
        return true;
    }
}