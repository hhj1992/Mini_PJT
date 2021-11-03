package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO purchase = new PurchaseVO();
		
		purchase.setPaymentOption(request.getParameter("paymentOption")); // get, post는 request.getparameter을 많이 쓴다. 
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		
		
		UserVO user = new UserVO();
		
		user.setUserId(request.getParameter("buyerId"));
		
		purchase.setBuyer(user);
	
		ProductService service = new ProductServiceImpl();
		
		ProductVO product = new ProductVO();
		
		product = service.getProduct(Integer.parseInt(request.getParameter("prodno")));
		
		purchase.setPurchaseProd(product);
		
		request.setAttribute("Purchase",purchase);
		
		PurchaseService purchaseService = new PurchaseServiceImpl();  
		
		purchaseService.addPurchase(purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
