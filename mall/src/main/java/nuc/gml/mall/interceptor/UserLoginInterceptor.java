package nuc.gml.mall.interceptor;


import nuc.gml.mall.consts.MallConst;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserLoginInterceptor implements HandlerInterceptor {

	/**
	 * 用户登录拦截器
	 * true 表示继续流程，false表示中断
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws BusinessException  {

		Boolean isLogin = (Boolean) request.getSession().getAttribute(MallConst.IS_LOGIN);
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN, "用户还未登陆");
        }
		return true;
	}
}
