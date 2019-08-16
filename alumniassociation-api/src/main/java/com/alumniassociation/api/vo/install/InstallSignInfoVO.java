package com.alumniassociation.api.vo.install;

import lombok.Builder;
import lombok.Data;


/**
 * 功能描述:
 * 获取签到信息VO
 * @author zenghaohui
 * @date 2019/2/28 16:19
 */
@Data
@Builder
public class InstallSignInfoVO {

    /**头像**/
    private String portrait;

    /**预约安装时间**/
    private String bookInstallTime;

    /**安装师傅名称**/
    private String installerName;



}
