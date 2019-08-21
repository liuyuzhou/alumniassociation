package com.alumniassociation.web.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alumniassociation.common.FileUploadBaseController;
import com.alumniassociation.common.exception.MyException;
import com.alumniassociation.common.log.SysLog;
import com.alumniassociation.common.utils.Constant;
import com.alumniassociation.common.utils.EnumBean;
import com.alumniassociation.common.utils.EnumMessage;
import com.alumniassociation.common.utils.FileUtil;
import com.alumniassociation.common.utils.R;
import com.alumniassociation.common.utils.StringUtil;
import com.alumniassociation.commpara.entity.Commpara;
import com.alumniassociation.commpara.service.CommparaService;
import com.alumniassociation.web.common.entity.File;
import com.alumniassociation.web.common.service.FileService;
import com.alumniassociation.web.common.service.GetDataService;
import com.alumniassociation.web.common.service.SysOssService;

/**
 * Created by lewp 2018/10/14
 */
@RestController
@RequestMapping("/getData")
public class GetDataController extends FileUploadBaseController {
	@Autowired
	private CommparaService commparaService;
	@Autowired
	private FileService fileService;
	@Autowired
	private SysOssService ossService;
	@Autowired
	private GetDataService getDataService;
	@Autowired
	private DataSource dataSource;

	/**
	 * @param
	 * @author chenyi
	 * @Description 通过枚举获取数据列表
	 * @date date 2017-7-20
	 */
	@ResponseBody
	@RequestMapping("/getEnum")
	public R getEnum(@RequestParam Map<String, Object> params) throws Exception {
		List<EnumBean> values = new ArrayList<>();
		String enumName = (String) params.get("enumName");
		if (enumName != null && !"".equals(enumName)) {
			Class clzz = null;
			try {
				clzz = Class.forName(Constant.PACKAGE_NAME + "." + enumName);
				Method method = clzz.getMethod("values");
				EnumMessage inter[] = (EnumMessage[]) method.invoke(new Object[] {}, new Object[] {});
				for (EnumMessage enumMessage : inter) {
					EnumBean e = new EnumBean();
					e.setCode(enumMessage.getCode());
					e.setValue(enumMessage.getValue());
					values.add(e);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return R.ok().put("data", values);
	}

	/**
	 * @param
	 * @author chenyi
	 * @Description 通过表码获取数据列表
	 * @date date 2017-7-20
	 */
	@ResponseBody
	@RequestMapping("/getCodeValues")
	public R getCodeValues(@RequestParam Map<String, Object> params) {
		List<Commpara> sysCodeList = null;
		if (params.get("codeName") != null && !"".equals(params.get("codeName"))) {
			sysCodeList = commparaService.getCodeValues(params);
		}
		return R.ok().put("data", sysCodeList);
	}

	/**
	 * @param
	 * @author chenyi
	 * @Description oss文件上传
	 * @date date 2017-7-20
	 */
	public String uploadImage(String fileName, InputStream inputStream) {
		/**
		 * //获取oss Map<String, Object> params = new HashMap<>();
		 * params.put("state", StateEnum.ENABLE.getCode()); List<SysOss> ossList
		 * = ossService.getList(params); if (ossList != null && ossList.size() >
		 * 0) { SysOss oss = ossList.get(0); String fileNameBak = fileName;
		 * String resultImgUrl = oss.getUrl(); String endpoint =
		 * oss.getEndpoint(); String accessKeyId = oss.getAccessKeyId(); String
		 * accessKeySecret = oss.getAccessKeySecret(); String bucket =
		 * oss.getBucket(); OSSClient ossClient = new OSSClient(endpoint,
		 * accessKeyId, accessKeySecret); if
		 * (!ossClient.doesBucketExist(bucket)) {
		 * ossClient.createBucket(bucket); }
		 * 
		 * ObjectMetadata objectMeta = new ObjectMetadata();//oss属性设置
		 * //objectMeta.setContentLength(inputStream.getSize());//标记长度
		 * objectMeta.setContentType("image/jpeg");// 可以在metadata中标记文件类型 try {
		 * //获取上传的图片文件名 Long nanoTime = System.nanoTime();// 防止文件被覆盖，以纳秒生成图片名
		 * String _extName = fileName.substring(fileName.indexOf("."));//获取扩展名
		 * fileName = nanoTime + _extName; fileName = DateUtil.getYmd() + "/" +
		 * fileName + "/" + fileNameBak; ossClient.putObject(bucket, fileName,
		 * inputStream, objectMeta); resultImgUrl += fileName; } catch
		 * (Exception e) { e.printStackTrace(); throw new MyException("上传失败"); }
		 * finally { ossClient.shutdown(); } return resultImgUrl; } throw new
		 * MyException("未启用oss配置");
		 **/
		return null;

	}

	/**
	 * 多文件上传
	 */
	@ResponseBody
	@SysLog("多图片上传")
	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
	public R uploadImages(@RequestParam("uploadFile") MultipartFile[] files, HttpServletRequest request) {
	    String url = getDataService.uploadImages(files,request);
        return R.ok().put("url", url);
	}

	/**
	 * layui文件上传
	 */
	@ResponseBody
	@RequestMapping("/uploadImage")
	public R uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new MyException("上传文件不能为空");
		}
		if (file.getSize() > 500 * 1024 * 1024) {
			throw new MyException("文件不能大于500M");
		}
		String url = null;
		if (FileUploadBaseController.isUseOss()) {
			// 上传文件
			url = uploadImage(file.getOriginalFilename(), file.getInputStream());
			// String url = "/statics/img/timg.jpg";
		} else {
			url = getDataService.uploadLocalImg(file);
		}
		return R.ok().put("url", url);
	}

	/**
	 * layui文件上传
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new MyException("上传文件不能为空");
		}

		String fileName = file.getOriginalFilename();
		String _extName = fileName.substring(fileName.indexOf("."), fileName.length());// 获取扩展名

		/*
		 * if (file.getSize() > 1 * 1024 * 1024) { throw new
		 * MyException("图片不能大于1M"); }
		 */
		String url = null;
		if (FileUploadBaseController.isUseOss()) {
			// 上传文件
			url = uploadImage(file.getOriginalFilename(), file.getInputStream());
			// String url = "/statics/img/timg.jpg";
		} else {
			url = getDataService.uploadLocalImg(file);
		}

		return R.ok().put("url", url);
	}

//	/**
//	 * layui文件上传
//	 */
//	@ResponseBody
//	@RequestMapping("/uploadFtp")
//	public R uploadFtp(@RequestParam("uploadFile") MultipartFile[] file, HttpServletRequest request) throws Exception {
//		if (file[0].isEmpty()) {
//			throw new MyException("上传文件不能为空");
//		}
//		String fileName = file[0].getOriginalFilename();
//		String _extName = fileName.substring(fileName.indexOf("."), fileName.length());// 获取扩展名
//
//		if (file[0].getSize() > 1 * 1024 * 1024) {
//			throw new MyException("图片不能大于1M");
//		}
//		String url = null;
//		if (FileUploadBaseController.isUseOss()) {
//			// 上传文件
//			url = uploadImage(file[0].getOriginalFilename(), file[0].getInputStream());
//
//			// String url = "/statics/img/timg.jpg";
//		} else {
//			url = uploadFtpImg(file[0]);
//		}
//
//		return R.ok().put("url", url);
//	}

	/**
	 * 上传文件到FTP服务器
	 */
//	public String uploadFtpImg(MultipartFile file) {
//		if (null != file) {
//			String fileName = StringUtil.getSequenceName(file.getOriginalFilename());
//
//			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//			String dir = getStaticDir() + date + "\\";
//
//			java.io.File fileDir = new java.io.File(dir);
//			if (!fileDir.exists()) { // 如果不存在 则创建
//				fileDir.mkdirs();
//			}
//			try {
//				String url = ftpConfigService.upLoadImg(fileName, file);
//				if (url.isEmpty()) {
//					throw new MyException("上传失败");
//				}
//				return url;
//			} catch (IllegalStateException e) {
//				throw new MyException("上传失败");
//			}
//		} else {
//			throw new MyException("上传文件不能为空!");
//		}
//	}

	/**
	 * Hupload上传文件
	 */
	@ResponseBody
	@RequestMapping("/uploads")
	public R uploads(@RequestParam("uploadFile") MultipartFile[] file, HttpServletRequest request) throws Exception {

		if (file[0].isEmpty()) {
			throw new MyException("上传文件不能为空");
		}
		String fileName = file[0].getOriginalFilename();
		String _extName = fileName.substring(fileName.indexOf("."), fileName.length());// 获取扩展名
		Long size = file[0].getSize();
		// if (size > 1 * 1024 * 1024) {
		// throw new MyException("图片不能大于1M");
		// }

		// 上传文件
		String url = uploadImage(file[0].getOriginalFilename(), file[0].getInputStream());
		// 存到本地文件
		// String url = "/statics/img/timg.jpg";
		String relationId = request.getParameter("relationId");
		File uploadFile = new File();
		uploadFile.setUploadId(relationId);
		uploadFile.setFileName(fileName);
		uploadFile.setFileSize(size.toString());
		uploadFile.setCreateTime(new Date());
		uploadFile.setUrl(url);
		// 获取文件类型
		boolean isPicture = FileUtil.isPicture(fileName);
		if (isPicture) {
			uploadFile.setFileType("image");
		} else {
			uploadFile.setFileType(_extName);
		}
		fileService.save(uploadFile);

		return R.ok().put("url", url).put("fileId", uploadFile.getId());
	}

	/**
	 * Hupload文件回填
	 **/
	@ResponseBody
	@RequestMapping("/getFile/{relationId}")
	public R getFile(@PathVariable("relationId") String relationId) {
		List<File> list = fileService.getByRelationId(relationId);
		return R.ok().put("fileList", list);
	}

	/**
	 * Hupload删除上传文件
	 */
	@ResponseBody
	@RequestMapping("/deleteFile/{fileId}")
	public R uploadFile(@PathVariable("fileId") String fileId) throws Exception {
		fileService.delete(fileId);
		return R.ok();
	}

	/**
	 * Hupload删除文件
	 */
	@ResponseBody
	@RequestMapping("/deleteByRelationId/{relationId}")
	public R deleteByRelationId(@PathVariable("relationId") String relationId) throws Exception {
		fileService.deleteByRelationId(relationId);
		return R.ok();
	}

	/**
	 * 多文件上传 此接口不改变原有文件名
	 */
	@ResponseBody
	@SysLog("多文件上传")
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public R uploadFiles(@RequestParam("uploadFile") MultipartFile[] files, HttpServletRequest request) {
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
					String myFileName = file.getOriginalFilename().replaceAll(" ", "");
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {

						// 定义上传路径
						try {
							String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
							String dir = getStaticDir() + date;
							java.io.File fileDir = new java.io.File(dir);
							if (!fileDir.exists()) { // 如果不存在 则创建
								fileDir.mkdirs();
							}
							String path = dir + java.io.File.separator + myFileName;
							// 存文件
							java.io.File localFile = new java.io.File(path);
							file.transferTo(localFile);
							sb.append(getServerHost() + date + "/" + myFileName).append(",");
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
			return R.ok().put("url", sb.toString());
		} else {
			throw new MyException("上传文件不能为空!");
		}
	}

	/**
	 * layui文件上传 此接口不改变原有文件名称
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public R uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new MyException("上传文件不能为空");
		}

		String fileName = file.getOriginalFilename();
		String _extName = fileName.substring(fileName.indexOf("."), fileName.length());// 获取扩展名

		String url = null;
		if (FileUploadBaseController.isUseOss()) {
			// 上传文件
			url = uploadImage(file.getOriginalFilename(), file.getInputStream());
		} else {
			url = getDataService.uploadFile(file);
		}

		return R.ok().put("url", url);
	}

	/***********************************************
	 * baidu ueditor
	 ************************************/
	/**
	 * 
	 * SpringMVC 用的是 的MultipartFile来进行文件上传
	 * 
	 * 这里用@RequestParam()来指定上传文件为MultipartFile
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping("ue_uploadImage")
	@ResponseBody // 这里upfile是config.json中图片提交的表单名称
	public Map<String, String> uploadImage(@RequestParam("file")  MultipartFile upfile,
			HttpServletRequest request) throws IOException {
		// 文件原名称
		String fileName = upfile.getOriginalFilename();
		// 返回结果信息(UEditor需要)
		Map<String, String> map = new HashMap<String, String>();
		// 是否上传成功
		map.put("state", "SUCCESS");
		// 文件原名称
		map.put("original", fileName);
		// 文件类型 .+后缀名
		map.put("type", fileName.substring(upfile.getOriginalFilename().lastIndexOf(".")));

		// 文件原名称
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
					    fileName = StringUtil.getSequenceName(file.getOriginalFilename());
						// 定义上传路径
						try {
							String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
							String dir = getStaticDir() + date;
							java.io.File fileDir = new java.io.File(dir);
							if (!fileDir.exists()) { // 如果不存在 则创建
								fileDir.mkdirs();
							}
							String path = dir + java.io.File.separator + fileName;
							map.put("title", path);
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
			// 文件路径

			map.put("url", sb.toString());
		} else {
			throw new MyException("上传文件不能为空!");
		}
		// 文件大小（字节数）

		map.put("size", upfile.getSize() + "");
		
		 //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png

		return map;
	}
	
	/***********************************************
	 * baidu ueditor
	 ************************************/
	/**
	 * 
	 * SpringMVC 用的是 的MultipartFile来进行文件上传
	 * 
	 * 这里用@RequestParam()来指定上传文件为MultipartFile
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping("ue_uploadVideo")
	@ResponseBody // 这里upfile是config.json中图片提交的表单名称
	public Map<String, String> uploadVideo(@RequestParam("file")  MultipartFile upfile,
			HttpServletRequest request) throws IOException {
		// 文件原名称
		String fileName = upfile.getOriginalFilename();
		// 返回结果信息(UEditor需要)
		Map<String, String> map = new HashMap<String, String>();
		// 是否上传成功
		map.put("state", "SUCCESS");
		// 文件原名称
		map.put("original", fileName);
		// 文件类型 .+后缀名
		map.put("type", fileName.substring(upfile.getOriginalFilename().lastIndexOf(".")));

		// 文件原名称
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
					    fileName = StringUtil.getSequenceName(file.getOriginalFilename());
						// 定义上传路径
						try {
							String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
							String dir = getVideoStaticDir() + date;
							java.io.File fileDir = new java.io.File(dir);
							if (!fileDir.exists()) { // 如果不存在 则创建
								fileDir.mkdirs();
							}
							String path = dir + java.io.File.separator + fileName;
							map.put("title", path);
							// 存文件
							java.io.File localFile = new java.io.File(path);
							file.transferTo(localFile);
							sb.append(getVideoServerHost() + date + "/" + fileName).append(",");
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
			// 文件路径

			map.put("url", sb.toString());
		} else {
			throw new MyException("上传文件不能为空!");
		}
		// 文件大小（字节数）

		map.put("size", upfile.getSize() + "");
		
		 //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png

		return map;
	}
	
	@RequestMapping(value = "/ueditor")
	@ResponseBody
	public String ueditor(HttpServletRequest request, HttpServletResponse response) {

	    String s = "{\n"+
	           "            \"imageActionName\": \"uploadimage\",\n" +
	           "                \"imageFieldName\": \"file\", \n" +
	           "                \"imageMaxSize\": 2097152, \n" +
	           "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
	           "                \"imageCompressEnable\": true, \n" +
	           "                \"imageCompressBorder\": 1600, \n" +
	           "                \"imageInsertAlign\": \"none\", \n" +
	           "                \"imageUrlPrefix\": \"\",\n" +
	           "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
	           //对视频上传的支持
               "                \"videoActionName\": \"uploadvideo\",\n" +
               "                \"videoFieldName\": \"file\", \n" +
               "                \"videoMaxSize\": 104857600, \n" +
               "                \"videoAllowFiles\": [\".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\", \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"], \n" +
               "                \"imageInsertAlign\": \"none\", \n" +
               "                \"videoUrlPrefix\": \"\",\n" +
	           "                \"videoFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
	    return s;

	}
	
}
