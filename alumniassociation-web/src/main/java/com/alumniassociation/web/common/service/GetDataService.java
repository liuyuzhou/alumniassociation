package com.alumniassociation.web.common.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 字典管理
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 14:49:28
 */
public interface GetDataService {

	String uploadLocalImg(MultipartFile file);

	String uploadFile(MultipartFile file);

    String uploadImages(MultipartFile[] files, HttpServletRequest request);
}
