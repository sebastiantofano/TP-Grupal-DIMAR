package ar.edu.unlam.tallerweb1.configuracion;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			Authorized methodAnnotation = method.getAnnotation(Authorized.class);
			Authorized controllerAnnotation = method.getDeclaringClass().getAnnotation(Authorized.class);

			if (methodAnnotation != null || controllerAnnotation != null) {
				if (request.getSession().getAttribute("usuario") != null) {
					return true;
				} else {
					response.sendRedirect("./login?msg=1");
					return false;
				}
			}
		}
		return true;
	}
}
