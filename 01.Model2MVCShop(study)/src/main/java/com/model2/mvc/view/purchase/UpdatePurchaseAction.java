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

public class UpdatePurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO purchase = new PurchaseVO();
		UserVO userVO = new UserVO();
		ProductVO productVO = new ProductVO();
		
		userVO.setUserId(request.getParameter("buyerId"));
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setBuyer(userVO);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		
		request.setAttribute("Purchase",purchase);
		
		PurchaseService purchaseService = new PurchaseServiceImpl();  
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		
		request.setAttribute("PurchaseVO",purchase);
				
		return "forward:/purchase/updatePurchase.jsp";
	}

}
