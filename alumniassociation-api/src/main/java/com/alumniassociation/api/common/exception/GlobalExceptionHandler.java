package com.alumniassociation.api.common.exception;


import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alumniassociation.api.common.auxiliarymodel.DataMsg;
import com.alumniassociation.api.common.utils.ExceptionUtil;
import com.alumniassociation.common.exception.MyException;

/**
 * @author wangyan
 * 统一异常处理
 * @date 2017/12/29
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	protected static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 系统异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ResponseBody
    @ExceptionHandler
    public DataMsg handleMyException(HttpServletRequest req,Exception e){
    	if (Objects.nonNull(e)) {
            LOG.error("XYH-SystemException:" + ExceptionUtil.getStackTrace(e));
            //return DataMsg.error(e.getMessage());//该返回为开发期间，方便调试，生产环境中注释掉
        }
    	return DataMsg.error("系统繁忙!稍后重试");//生产环境，所有系统Exception统一返回
    }
    
    /**
     * 自定义异常
     * 返回给用户看的
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public DataMsg handleMyException(HttpServletRequest req, MyException e){
    	if (Objects.nonNull(e)) {
            LOG.info("XYH-MyException:" + e.getMessage());
        }
        return DataMsg.error(e.getMessage());
    }
}
