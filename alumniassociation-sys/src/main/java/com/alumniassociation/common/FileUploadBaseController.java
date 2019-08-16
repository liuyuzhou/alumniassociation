package com.alumniassociation.common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alumniassociation.common.dao.AntInstBaseDao;

/**
 * 系统属性配置获取
 * @author lewp
 *
 */
public abstract class FileUploadBaseController {
	
	@Value("${com.alumniassociation.img_upload_url}")
	private  String IMG_UPLOAD_URL;
	@Value("${com.alumniassociation.img_upload_dir}")
	private  String IMG_UPLOAD_DIR;
	@Value("${com.alumniassociation.video_upload_url}")
	private  String VIDEO_UPLOAD_URL;
	@Value("${com.alumniassociation.video_upload_dir}")
	private  String VIDEO_UPLOAD_DIR;
	@Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    
    @Autowired
    private AntInstBaseDao antInstBaseDao;

    public HttpServletRequest getRequest(){
    	return this.request;
    }
    
    public HttpServletResponse getResponse(){
    	return this.response;
    }
    
    public AntInstBaseDao getDao(){
    	return this.antInstBaseDao;
    }
	/**
	 * 
	 * 是否启用了oos文件上传
	 * @return
	 */
	public static boolean isUseOss(){
		return false;
	}

	/****************************************/
	public  String getServerHost() {
		return IMG_UPLOAD_URL;
	}
	
	public  String getVideoServerHost() {
		return VIDEO_UPLOAD_URL;
	}
	
	/**
	 * 静态资源文件存放路径映射
	 * 
	 * @return
	 */
	public  String getStaticDir() {
		return IMG_UPLOAD_DIR;
	}
	
	public  String getVideoStaticDir() {
		return VIDEO_UPLOAD_DIR;
	}
	
  
    private void accessControlAllow(HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 响应类型
		response.setHeader("Access-Control-Allow-Methods", "POST");
		// 响应头设置
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
	}

    
    public void writeJsonZipToClient(HttpServletResponse response, Object paramObject) {
		accessControlAllow(response);
		if (paramObject == null)
			return;
		String str = "";
		if (((paramObject instanceof String)) || ((paramObject instanceof StringBuffer)) || ((paramObject instanceof StringBuilder))) {
			str = paramObject.toString();
		} else {
			str = JSON.toJSONString(paramObject);
		}
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Encoding", "gzip, deflate, sdch");
		GZIPOutputStream gzipout = null;
		try {
			gzipout = new GZIPOutputStream(response.getOutputStream());
			gzipout.write(str.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzipout != null) {
				try {
					gzipout.flush();
					gzipout.finish();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    public void writeJsonZipObjToClient(HttpServletResponse response, Object obj) {
		accessControlAllow(response);
		response.setContentType("application/ostet-stream");
		response.setHeader("Content-Encoding", "gzip, deflate, sdch");
		GZIPOutputStream gzipout = null;
		try {
			gzipout = new GZIPOutputStream(response.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(gzipout);
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzipout != null) {
				try {
					gzipout.flush();
					gzipout.finish();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
