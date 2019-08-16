package com.alumniassociation.web.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-08-10 16:00:04
 */
public interface FtpConfigService {

	String upLoadImg(String path, MultipartFile file);

}
