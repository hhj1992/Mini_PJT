package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDao;
	
	public ProductServiceImpl() {
		productDao = new ProductDAO();  
	}
	
	public void addProduct(ProductVO productVO) throws Exception {
		productDao.insertUser(productVO);
	}
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception{
		return productDao.getProductList(searchVO);
	}
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	public void updateProduct(ProductVO productVO) throws Exception {
		productDao.updateProduct(productVO);
	}
}
