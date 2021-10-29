package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDao;
	
	public ProductServiceImpl() {
		productDao = new ProductDAO();  
	}
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
	}
	public HashMap<String,Object> getProductList(Search search) throws Exception{
		return productDao.getProductList(search);
	}
	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
}
