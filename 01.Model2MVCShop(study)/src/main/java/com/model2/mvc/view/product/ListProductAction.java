package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListProductAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null) { //받은 page값이 null이 아니면 
			page=Integer.parseInt(request.getParameter("page")); // int로 변환해서 저장 			
		}
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service = new ProductServiceImpl();
		
		HashMap<String,Object> map=service.getProductList(searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		 
	
		String menu = request.getParameter("menu");
		request.setAttribute("menu", menu);
		String rtUrl ="";
		if(menu.equals("manage")) {
			rtUrl = "forward:/product/updateProductView.jsp";
		}else {
			rtUrl = "forward:/product/listProduct.jsp";			
		}
		return rtUrl;
	}
	
		
//		ProductService service = new ProductServiceImpl();   // productserviceImpl을 덮어서 만든다. 
//		
//		//ProductServiceImpl 생성자함수를 만들어서 service에 담는다.
//		//그러면 service에 있는 method가 오버라이딩됨..
//		//A a = new B(); -> implements, extend -> a와 B의 함수가 같을 경우 B를 쓴다.    
// 		 
//		SearchVO searchVO = new SearchVO();
//		HashMap<String,Object> hashMap = new HashMap<String,Object>();
//		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
//		  
//		int page=1;
//		if(request.getParameter("page") != null) {
//			page=Integer.parseInt(request.getParameter("page"));			
//		}
//		searchVO.setPage(page); //<--현재 내가 보고있는 페이지를 세팅
//		
//	
//		 searchVO.setSearchCondition(request.getParameter("searchCondition"));
//		 searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
//	
//		 
//		
//		String pageUnit=getServletContext().getInitParameter("pageSize");
//		searchVO.setPageUnit(Integer.parseInt(pageUnit)); 
//		
//		 
//		hashMap = service.getProductList(searchVO);
//		
//		list = (ArrayList<ProductVO>) hashMap.get("productList");
//		//list[0] = > 첫번째 레코드의 productVO 부터 list[11] => 열두번째 레코드의 productVo를 가지고있어.
//		
//		request.setAttribute("page", page);
//		request.setAttribute("count", hashMap.get("count"));
//		request.setAttribute("list", list);
//		request.setAttribute("searchVO", searchVO);
//		
//		String menu = request.getParameter("menu");
//		//menu 는 manage를 가지고있어.
//		
//	
//	
//		String rtUrl = "";
//		if(menu != null && !menu.equals("")) {
//			//상품관리페이지(관리자용)
//			if(menu.equals("manage")) {
//					rtUrl = "forward:/product/updateProductView.jsp";					
//			}//상품검색 페이지 일경우(회원용)
//			else {
//				rtUrl = "forward:/product/listProduct.jsp";				
//			}
//		} 
//		return rtUrl;
//	}

}
