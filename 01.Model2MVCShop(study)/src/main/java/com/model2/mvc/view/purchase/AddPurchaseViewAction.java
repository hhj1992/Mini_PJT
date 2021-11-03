package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.framework.Action;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("addpurview 진입");
		ProductVO productVO = new ProductVO();
		
		int prod = Integer.parseInt(request.getParameter("prod_no")); // request.getparameter의 반환type은 string이므로 interger로 변환시킨다. 
					
		ProductService service = new ProductServiceImpl();
		
		productVO = service.getProduct(prod);
		
		request.setAttribute("ProductVO",productVO);
		
		return "forword:/purchase/addPurchaseView.jsp";
	}
}
