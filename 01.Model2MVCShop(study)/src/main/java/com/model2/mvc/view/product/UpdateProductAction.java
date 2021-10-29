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

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		
		ProductVO productVO = new ProductVO(); 
		
		//form을 첨부파일을 전송하기위해 multipart로 설정했기때문에 multipartRequest로 받아야함
		//cos.jar파일이 필요해서 lib에 넣어놓음
		//String savePath =  "C:\\Users\\CEO\\Desktop\\upload\\"; 
		//파일을 저장할 위치 -> 바탕화면으로 지정했음 -> 바탕화면에 upload폴더를 새로 만들어야함.
		 
		//MultipartRequest mr = new MultipartRequest(request,savePath,"euc-kr");
		//String fileName = mr.getFilesystemName("fileName");
		
		//수정시에 이미지 업로드 안했을시에 기존 파일위치 그대로 적용하기위해 기존데이터를 가져옴.
		/*if(fileName == null || fileName.equals("")) {		
			String originFileName = mr.getParameter("originFileName");
			productVO.setFileName(originFileName);
		}*/
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		productVO.setProdName(request.getParameter("prodName")); 
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		 
		ProductService service = new ProductServiceImpl();  
		service.updateProduct(productVO);
		
		return "forward:/listProduct.do?menu=manage";
	}
	

}
