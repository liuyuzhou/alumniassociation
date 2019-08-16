package com.alumniassociation.web.common.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alumniassociation.common.FileUploadBaseController;
import com.alumniassociation.common.exception.MyException;
import com.alumniassociation.common.utils.StringUtil;
import com.alumniassociation.web.common.service.GetDataService;


@Service("getDataService")
@Transactional
public class GetDataServiceImpl  extends FileUploadBaseController implements GetDataService {

	@Value("${com.alumniassociation.img_upload_url}")
	private  String IMG_UPLOAD_URL;
	@Value("${com.alumniassociation.img_upload_dir}")
	private  String IMG_UPLOAD_DIR;
    /**
	 * 上传文件到本地服务器
	 */
	@Override
	public String uploadLocalImg(MultipartFile file){
    	if (null != file) {
            String fileName = StringUtil.getSequenceName(file.getOriginalFilename());
            
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String dir= IMG_UPLOAD_DIR +date+"/"; 

            java.io.File fileDir=new java.io.File(dir);
            if (!fileDir.exists()) { //如果不存在 则创建     
                 fileDir.mkdirs();    
             } 
            java.io.File localFile = new java.io.File(dir + fileName);  
            try {
                file.transferTo(localFile);
                return IMG_UPLOAD_URL + date + "/" + fileName;
            } catch (IllegalStateException e) {
            	throw new MyException("上传失败");
            } catch (IOException e) {
            	throw new MyException("上传失败");
            }
        }else{
        	throw new MyException("上传文件不能为空!");
        }
	}
	
    /**
	 * 上传文件到本地服务器
	 * 此接口不改变源文件名称
	 */
	@Override
	public String uploadFile(MultipartFile file){
    	if (null != file) {
            String fileName = file.getOriginalFilename();
            
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String dir= IMG_UPLOAD_DIR +date+"\\"; 

            java.io.File fileDir=new java.io.File(dir);
            if (!fileDir.exists()) { //如果不存在 则创建     
                 fileDir.mkdirs();    
             } 
            java.io.File localFile = new java.io.File(dir + fileName);  
            try {
                file.transferTo(localFile);
                return IMG_UPLOAD_URL + date + "/" + fileName;
            } catch (IllegalStateException e) {
            	throw new MyException("上传失败");
            } catch (IOException e) {
            	throw new MyException("上传失败");
            }
        }else{
        	throw new MyException("上传文件不能为空!");
        }
	}

    @Override
    public String uploadImages(MultipartFile[] files, HttpServletRequest request) {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            StringBuffer sb = new StringBuffer();
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 重命名上传后的文件名 按照年月日时分秒进行命名
                        String fileName = StringUtil.getSequenceName(file.getOriginalFilename());
                        // 定义上传路径
                        try {
                            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
                            String dir = getStaticDir() + date;
                            java.io.File fileDir = new java.io.File(dir);
                            if (!fileDir.exists()) { // 如果不存在 则创建
                                fileDir.mkdirs();
                            }
                            String path = dir + java.io.File.separator + fileName;
                            // 存文件
                            java.io.File localFile = new java.io.File(path);
                            file.transferTo(localFile);
                            sb.append(getServerHost() + date + "/" + fileName).append(",");
                        } catch (IllegalStateException e) {
                            throw new MyException("上传失败");
                        } catch (IOException e) {
                            throw new MyException("上传失败");
                        }
                    }
                }
            }
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } else {
            throw new MyException("上传文件不能为空!");
        }
    }
}
