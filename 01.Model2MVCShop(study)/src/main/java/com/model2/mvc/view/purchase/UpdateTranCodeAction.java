package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
			int TranNo = Integer.parseInt(request.getParameter("tran_no"));
			
			System.out.println("TranNo:"+TranNo);
			
			PurchaseService service = new PurchaseServiceImpl();
			
			PurchaseVO purchaseVO = new PurchaseVO();
			
			
			purchaseVO.setTranCode("2");
			purchaseVO.setTranNo(TranNo);
			
			service.updateTranCode(purchaseVO);
								
			
		return "forward:/listProduct.do?menu=manage";
	}
}