package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		 
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			//path媛믪씠�옉 留ㅼ묶�릺�뒗 actionmapping.properties�쓽 硫뷀��뜲�씠�꽣瑜�
			//action�겢�옒�뒪�뿉 留ㅽ븨 
			Action action = mapper.getAction(path);
			action.setServletContext(getServletContext());  
			
			//�삁瑜쇰뱾�뼱�꽌 ListProduct.do 瑜� �떎�뻾�뻽�쓣寃쎌슦 
			//com.model2.mvc.view.product.ListProductAction媛� �븸�뀡�씠�릺�뒗�뜲,
			//action�뿉 �빐�떦�븯�뒗 excute�븿�닔瑜� �떎�뻾�븳�떎.(�삤踰꾨씪�씠�뵫)
			String resultPage = action.execute(request, response);
			
			
			System.out.println("resultpage : "+resultPage);
			
			//forward: or redirect: �뮘�뿉�삤�뒗 url�쓣 媛��졇�삤湲곗쐞�븿
			String result = resultPage.substring(resultPage.indexOf(":")+1);
			//result - /product/updateProductView.jsp
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}