package com.alumniassociation.api.common.api.annotation;

import java.lang.annotation.*;

/**
 * 数据加密
 * @author lewp
 * @email 228112142@qq.com
 * @date 2017-03-23 15:44
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}
