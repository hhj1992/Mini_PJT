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
		if(request.getParameter("page") != null) { //���� page���� null�� �ƴϸ� 
			page=Integer.parseInt(request.getParameter("page")); // int�� ��ȯ�ؼ� ���� 			
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
	
		
//		ProductService service = new ProductServiceImpl();   // productserviceImpl�� ��� �����. 
//		
//		//ProductServiceImpl �������Լ��� ���� service�� ��´�.
//		//�׷��� service�� �ִ� method�� �������̵���..
//		//A a = new B(); -> implements, extend -> a�� B�� �Լ��� ���� ��� B�� ����.    
// 		 
//		SearchVO searchVO = new SearchVO();
//		HashMap<String,Object> hashMap = new HashMap<String,Object>();
//		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
//		  
//		int page=1;
//		if(request.getParameter("page") != null) {
//			page=Integer.parseInt(request.getParameter("page"));			
//		}
//		searchVO.setPage(page); //<--���� ���� �����ִ� �������� ����
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
//		//list[0] = > ù��° ���ڵ��� productVO ���� list[11] => ���ι�° ���ڵ��� productVo�� �������־�.
//		
//		request.setAttribute("page", page);
//		request.setAttribute("count", hashMap.get("count"));
//		request.setAttribute("list", list);
//		request.setAttribute("searchVO", searchVO);
//		
//		String menu = request.getParameter("menu");
//		//menu �� manage�� �������־�.
//		
//	
//	
//		String rtUrl = "";
//		if(menu != null && !menu.equals("")) {
//			//��ǰ����������(�����ڿ�)
//			if(menu.equals("manage")) {
//					rtUrl = "forward:/product/updateProductView.jsp";					
//			}//��ǰ�˻� ������ �ϰ��(ȸ����)
//			else {
//				rtUrl = "forward:/product/listProduct.jsp";				
//			}
//		} 
//		return rtUrl;
//	}

}
