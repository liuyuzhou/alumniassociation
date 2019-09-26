package com.alumniassociation.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信用户信息
 * @author lewp
 *
 */
@Data
@Accessors(chain = true)
public class UserInfo {
	
	/**用户昵称**/
    private String nickName;
    
    /**用户头像地址**/
    private String avatarUrl;
    
    /**国家**/
    private String country;
    
    /**省份**/
    private String province;
    
    /**城市**/
    private String city;
    
    /**语言**/
    private String language;
    
    /**性别**/
    private Byte gender;


}
