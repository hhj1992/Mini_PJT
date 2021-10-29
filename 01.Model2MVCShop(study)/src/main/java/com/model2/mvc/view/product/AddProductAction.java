package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ProductVO productVO = new ProductVO(); 
		
		productVO.setProdName(request.getParameter("prodName")); 
		productVO.setProdDetail(request.getParameter("prodDetail"));  
		productVO.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();  
		
		/*ProductServiceImpl service = new ProductServiceImpl(); 위에랑 다른점이 궁금합니다. */
		/*ProductDao service = new ProductDao();
		 * service.insertUser(productVO);*/
		
		service.addProduct(productVO);
		
		return "redirect:/listProduct.do?menu=manage";
	}
	

}
