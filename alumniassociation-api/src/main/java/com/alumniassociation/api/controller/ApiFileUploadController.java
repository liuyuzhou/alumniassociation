package com.alumniassociation.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alumniassociation.api.common.api.annotation.IgnoreAuth;
import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.service.ApiImgUploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangyan
 * @date:   2018年11月19日 上午11:03:59 
 */
@Api(tags = "文件上传", hidden = false)
@RestController
@RequestMapping("/szxyh/v1/img")
public class ApiFileUploadController {
	@Autowired
	private ApiImgUploadService apiImgUploadService;
	
	
	@Value("${com.alumniassociation.img_upload_url}")
	private String IMG_UPLOAD_URL;
	
	@PostMapping("uploadImg")
    @IgnoreAuth
    @ApiOperation(value = "图片上传", notes = "图片转base64code到后台就行文件上传操作")
	@ApiImplicitParams({ @ApiImplicitParam(name = "base64Code", value = "文件base64码", required = true, dataType = "string", paramType = "query")
	})
	public DataMsg uploadImg(String base64Code){
		Map<String, String> resultMap = new HashMap<String, String>();
		String url = "";
		url = apiImgUploadService.uploadImg(base64Code);
		resultMap.put("url", url);
		return DataMsg.ok(resultMap);
	}
	
	@PostMapping("uploadMultImg")
	@IgnoreAuth
	@ApiOperation(value = "图片上传", notes = "传入文件流进行文件上传操作")
	@ApiImplicitParams({ @ApiImplicitParam(name = "imgFile", value = "文件名称", required = true, dataType = "File", paramType = "query")
	})
	public DataMsg uploadMultImg(@RequestParam("imgFile") MultipartFile imgFile){
		return DataMsg.ok(apiImgUploadService.uploadMultImg(imgFile));
	}
	
	@PostMapping("uploadVideo")
	@IgnoreAuth
	@ApiOperation(value = "视频上传", notes = "视频流进行上传")
	public DataMsg uploadVideo(@RequestParam("videoFile") MultipartFile videoFile){
		return DataMsg.ok(apiImgUploadService.uploadVideo(videoFile));
	}

}
