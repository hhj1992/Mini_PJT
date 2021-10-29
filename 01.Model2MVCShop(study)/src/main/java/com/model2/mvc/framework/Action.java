package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*ServletContext는 서블릿 컨테이너와 통신하기 위해서 사용되는 메소드를 지원하는 인터페이스입니다. 
웹 서버에 등록된 웹 애플리케이션 단위로 하나의 ServletContext객체가 자동으로 생성됩니다. 
그리고 웹 애플리케이션 서비스가 중지될 때 소멸합니다. 
즉 웹 애플리케이션과 생명주기가 같고, 간단하게 웹 컨텍스트, 컨텍스트라고 부르기도합니다.
*/

public abstract class Action {
	
	private ServletContext servletContext;
	
	public Action(){
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}