package com.model2.mvc.service.prouduct;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public interface ProductService { 
	
	//��ǰ �߰�
	public void addProduct(Product product) throws Exception;

	//��ǰ ���������� ����
	public Product getProduct(int prodNo) throws Exception;

	//��ǰ ����
	public void updateProduct(Product product) throws Exception;
	
	//��ǰ ����Ʈ 
	public HashMap<String,Object> getProductList(Search search) throws Exception;
	
}