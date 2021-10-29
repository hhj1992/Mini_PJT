package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		Product product = new Product(); 
		
		product.setProdName(request.getParameter("prodName")); 
		product.setProdDetail(request.getParameter("prodDetail"));  
		product.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();  
	
		service.addProduct(product);
		
		return "redirect:/listProduct.do?menu=manage";
	}
	

}
