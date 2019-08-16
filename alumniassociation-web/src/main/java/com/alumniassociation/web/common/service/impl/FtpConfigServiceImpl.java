//package com.alumniassociation.web.common.service.impl;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alumniassociation.common.utils.FtpUtil;
//import com.alumniassociation.web.common.service.FtpConfigService;
//
//
//@Service("ftpConfigService")
//public class FtpConfigServiceImpl implements FtpConfigService {
//	
//	   // ftp服务器ip地址
//    @Value("${ftpAddress}")
//    private String ftpAddress;
//    // 端口号
//    @Value("${ftpPort}")
//    private int ftpPort;
//    // 用户名
//    @Value("${ftpName}")
//    private String ftpName;
//    // 密码
//    @Value("${ftpPassWord}")
//    private String ftpPassWord;
//    // 图片路径
//    @Value("${ftpBasePath}")
//    private String ftpBasePath;
//    // Ftp名称
//    @Value("${ftpUrl}")
//    private String ftpUrl;
//    
//    @Override
//    public String upLoadImg(String fileName,MultipartFile file) {
//        // TODO Auto-generated method stub
//        InputStream input = null;
//		try {
//			input = file.getInputStream();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        Boolean flag = FtpUtil.uploadFile(fileName,file, input,ftpAddress,ftpPort,ftpName,ftpPassWord,ftpBasePath,ftpUrl);
//        if (flag == true) {
//            System.out.println("ftp上传成功！");
//      	  // ftp://192.168.0.196:2121/pic/fbrs1540977292329.jpg;
//            return ftpUrl+ftpAddress+":"+ftpPort+"/"+ftpBasePath+"/"+fileName;
//        }  
//        return null;
//    }
//
//}
