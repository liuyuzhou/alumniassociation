package com.alumniassociation.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface ApiImgUploadService {

	String uploadImg(String base64Code);
	
	void createFileByBase64Code(String imgDir, String fileName, String base64Code);
	
	String uploadVideo(MultipartFile file);
	
	String uploadMultImg(MultipartFile file);
	
	boolean deleteUploadImg(String deleteUrl);
	
	boolean deleteUploadVideo(String deleteUrl);
	
}
