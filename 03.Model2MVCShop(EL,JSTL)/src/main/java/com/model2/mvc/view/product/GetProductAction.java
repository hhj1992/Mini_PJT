package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		 
		ProductService service = new ProductServiceImpl(); 
		Product product = service.getProduct(prodNo);

		request.setAttribute("product", product);
	
		String value = null;	
		
		Cookie[] cookies = request.getCookies(); 
		if (cookies!=null && cookies.length > 0) {   
			for (int i = 0; i < cookies.length; i++) {	
				Cookie cookie = cookies[i];	  
				System.out.println(cookie.getName());  
				
				if (cookie.getName().equals("history")) { 
					value = cookie.getValue(); 
				}
			}	
		}
				
		value +=  "," + product.getProdNo(); 
		Cookie cookie = new Cookie("history",value);
		response.addCookie(cookie); 
		
		return "forward:/product/getProduct.jsp";
	}

}
