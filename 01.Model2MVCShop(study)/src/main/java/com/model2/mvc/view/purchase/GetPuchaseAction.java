package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.framework.Action;

public class GetPuchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetPuchaseAction 진입");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
					
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO = service.getPurchase(tranNo);
		
		System.out.println(purchaseVO);
		
		request.setAttribute("PurchaseVO",purchaseVO);
		
		return "forward:/purchase/getPurchase.jsp";
	}
}
