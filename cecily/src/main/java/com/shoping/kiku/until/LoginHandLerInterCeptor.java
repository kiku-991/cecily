package com.shoping.kiku.until;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginHandLerInterCeptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ログイン成功したら、ユーザのセッションを保存する
		Object loginUser = request.getSession().getAttribute("userLogin");

		if (loginUser == null) {
			request.setAttribute("ms", "ログインしてください。");
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		} else {
			return true;
		}
	}

}
