package com.model2.mvc.service.prouduct;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public interface ProductService { 
	
	//상품 추가
	public void addProduct(Product product) throws Exception;

	//상품 정보가지고 오기
	public Product getProduct(int prodNo) throws Exception;

	//상품 수정
	public void updateProduct(Product product) throws Exception;
	
	//상품 리스트 
	public HashMap<String,Object> getProductList(Search search) throws Exception;
	
}