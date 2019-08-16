package com.alumniassociation.api.common.api.resolver;


import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alumniassociation.api.common.api.annotation.LoginUser;
import com.alumniassociation.api.entity.Token;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	public static final String LOGIN_TOKEN_KEY = "X-Alumniassociation-Token";
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    /**
     * 返回用户userId
     */
    @Override
    public Integer resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //return (String)request.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST); 
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            return null;
        }
        Token tokenInfo = (Token)request.getAttribute("loginUserInfo", RequestAttributes.SCOPE_REQUEST);
        //return UserTokenManager.getUserId(token);    
        return tokenInfo.getUserId();
    }
}
