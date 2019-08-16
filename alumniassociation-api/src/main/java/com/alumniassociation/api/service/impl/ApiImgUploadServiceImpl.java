package com.alumniassociation.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.alumniassociation.api.service.ApiImgUploadService;
import com.alumniassociation.common.exception.MyException;
import com.alumniassociation.common.utils.StringUtil;

/**
 * @author wangyan
 * @date:   2018年11月19日 上午11:33:00 
 */
@Service("apiImgUploadService")
public class ApiImgUploadServiceImpl implements ApiImgUploadService {
	protected static final Logger LOG = LoggerFactory.getLogger(ApiImgUploadServiceImpl.class);
	@Value("${com.alumniassociation.img_upload_url}")
	private String IMG_UPLOAD_URL;
	@Value("${com.alumniassociation.video_upload_url}")
	private String VIDEO_UPLOAD_URL;
	@Value("${com.alumniassociation.img_upload_dir}")
	private String IMG_UPLOAD_DIR;
	@Value("${com.alumniassociation.video_upload_dir}")
	private String VIDEO_UPLOAD_DIR;

	/* (non-Javadoc)
	 * @see com.alumniassociation.api.service.APiImgUpload#uploadImg(java.lang.String)
	 */
	@Override
	public String uploadImg(String base64Code) {
		if (!StringUtil.isEmpty(base64Code) && base64Code.length() != 0) {
            //js调用函数把base64传给安卓或ios把base64传下来的时候会把+换成空格,所以要替换 把空格替换成+
            base64Code = base64Code.replaceAll(" ", "+");
        }
		String path = "";
		String serverPath = "";
        try {
            if (base64Code != null && base64Code.length() > 0) {
            	String dataPrix = "";
            	String data = "";
            	
            	String [] d = base64Code.split("base64,");
            	if(d != null && d.length == 2){
            		dataPrix = d[0];
            		data = d[1];
            	}else{
            		throw new Exception("上传失败，数据不合法");
            	}
            	
            	String suffix = "";
            	if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            		//data:image/jpeg;base64,base64编码的jpeg图片数据 
            		suffix = ".jpg";
            	} else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            		//data:image/x-icon;base64,base64编码的icon图片数据
            		suffix = ".ico";
            	} else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            		//data:image/gif;base64,base64编码的gif图片数据
            		suffix = ".gif";
            	} else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            		//data:image/png;base64,base64编码的png图片数据
            		suffix = ".png";            
            	}else{
            		throw new Exception("上传图片格式不合法");
            	}
              
            	String datePattrn = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
                path = IMG_UPLOAD_DIR + datePattrn;
                File file = new File(path);
                if (!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                //图片存储路径
                String fileName = UUID.randomUUID().toString() + suffix;
                path = path + fileName;
                //图片访问路径
                serverPath = IMG_UPLOAD_URL  + datePattrn + fileName;
                //调用spring框架Base64 JDK base64 解码出现异常
                byte[] bytes = Base64Utils.decodeFromString(data);
                for (int index = 0; index < bytes.length; ++index) {
                    if (bytes[index] < 0) {// 调整异常数据
                        bytes[index] += 256;
                    }
                }
                FileUtils.writeByteArrayToFile(new File(path), bytes);
            }
            return serverPath;
        } catch (Exception e) {
            throw new MyException("图片上传失败");
        }
	}
	
	/**
	 * return file name
	 */
	@Override
	public void createFileByBase64Code(String imgDir, String fileName, String base64Code) {
		if (!StringUtil.isEmpty(base64Code) && base64Code.length() != 0) {
            //js调用函数把base64传给安卓或ios把base64传下来的时候会把+换成空格,所以要替换 把空格替换成+
            base64Code = base64Code.replaceAll(" ", "+");
        }
		
		String fileId = null;
        try {
            if (base64Code != null && base64Code.length() > 0) {
            	String dataPrix = "";
            	String data = "";
            	
            	String [] d = base64Code.split("base64,");
            	if(d != null && d.length == 2){
            		dataPrix = d[0];
            		data = d[1];
            	}else{
            		throw new Exception("上传失败，数据不合法");
            	}
            	
            	String suffix = "";
            	if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            		//data:image/jpeg;base64,base64编码的jpeg图片数据 
            		suffix = ".jpg";
            	} else{
            		throw new Exception("上传图片格式不合法");
            	}
                File file = new File(imgDir);
                if (!file.exists() && !file.isDirectory()) {
                    file.mkdir();
                }
                String path = imgDir + fileName;
                //调用spring框架Base64 JDK base64 解码出现异常
                byte[] bytes = Base64Utils.decodeFromString(data);
                for (int index = 0; index < bytes.length; ++index) {
                    if (bytes[index] < 0) {// 调整异常数据
                        bytes[index] += 256;
                    }
                }
                FileUtils.writeByteArrayToFile(new File(path), bytes);
            }
        } catch (Exception e) {
            throw new MyException("图片上传失败");
        }
	}

	@Override
	public boolean deleteUploadImg(String deleteUrl) {
		String localPath = deleteUrl.replace(IMG_UPLOAD_URL, IMG_UPLOAD_DIR);
		File file = new File(localPath);
		if(file.exists() && file.isFile()){
			LOG.info("开始删除IMG：" + localPath);
			return file.delete();
		}
		LOG.info("删除IMG失败：" + localPath);
		return false;
	}
	
	@Override
	public String uploadMultImg(MultipartFile file) {
		String path = "";
		String serverPath = "";
            String datePattrn = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
            path = IMG_UPLOAD_DIR + datePattrn;
            
            File videoFile = new File(path);
            if (!videoFile.exists() && !videoFile.isDirectory()) {
            	videoFile.mkdir();
            }
            String fileName = StringUtil.getSequenceName(file.getOriginalFilename());
            path = path + fileName;
            serverPath = IMG_UPLOAD_URL  + datePattrn + fileName;
            File localFile = new File(path);  
            try {
                file.transferTo(localFile);
                return serverPath;
            }  catch (IOException e) {
            	throw new MyException("上传图片失败");
            }
        
	}

	@Override
	public String uploadVideo(MultipartFile file) {
		String path = "";
		String serverPath = "";
            String datePattrn = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
            path = VIDEO_UPLOAD_DIR + datePattrn;
            
            File videoFile = new File(path);
            if (!videoFile.exists() && !videoFile.isDirectory()) {
            	videoFile.mkdir();
            }
            String fileName = StringUtil.getSequenceName(file.getOriginalFilename());
            path = path + fileName;
            serverPath = VIDEO_UPLOAD_URL  + datePattrn + fileName;
            File localFile = new File(path);  
            try {
                file.transferTo(localFile);
                return serverPath;
            }  catch (IOException e) {
            	throw new MyException("上传视频失败");
            }
        
	}

	@Override
	public boolean deleteUploadVideo(String deleteUrl) {
		String localPath = deleteUrl.replace(VIDEO_UPLOAD_URL, VIDEO_UPLOAD_DIR);
		File file = new File(localPath);
		if(file.exists() && file.isFile()){
			LOG.info("开始删除Video：" + localPath);
			return file.delete();
		}
		LOG.info("删除Video失败：" + localPath);
		return false;
	}

}
