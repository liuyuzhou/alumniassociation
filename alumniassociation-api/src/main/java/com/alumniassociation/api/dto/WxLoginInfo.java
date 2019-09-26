package com.alumniassociation.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WxLoginInfo {
	
    private String code;
    
    private UserInfo userInfo;

}
