package com.alumniassociation.common.utils;

import lombok.Data;

/**
 * 功能描述:
 * 分页参数接收类
 * @author zenghaohui
 * @date 2019/2/28 16:16
 */
@Data
public class PageParams {

    private Integer limit;

    private Integer page;

}
