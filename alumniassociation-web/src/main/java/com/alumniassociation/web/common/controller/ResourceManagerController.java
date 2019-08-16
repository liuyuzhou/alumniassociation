package com.alumniassociation.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alumniassociation.common.FileUploadBaseController;

@Controller
@RequestMapping("resourceManager")
public class ResourceManagerController  extends FileUploadBaseController{
	 /**
     * 跳转到列表页
     */
    @RequestMapping("/image")
    public String list() {
        return "resmanager/image/main";
    }

}
