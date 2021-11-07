package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		
		if(request.getParameter("page") != null) { //���� page���� null�� �ƴϸ� 
			page = Integer.parseInt(request.getParameter("page")); // int�� ��ȯ�ؼ� ���� 			
		}
		
		HttpSession session=request.getSession();
		
		UserVO user = (UserVO)session.getAttribute("user");
		
		searchVO.setPage(page); 
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		PurchaseService service = new PurchaseServiceImpl();
		
		HashMap<String,Object> map = service.getPurchaseList(searchVO,user.getUserId());

		System.out.println("map :"+ map);
		 
	
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
			
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
