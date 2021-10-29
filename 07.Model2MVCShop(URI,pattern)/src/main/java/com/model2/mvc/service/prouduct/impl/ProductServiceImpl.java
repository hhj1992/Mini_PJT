package com.model2.mvc.service.prouduct.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.prouduct.ProductDAO;
import com.model2.mvc.service.prouduct.ProductService;
import com.model2.mvc.service.user.UserDao;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService { 
	
	@Autowired
	@Qualifier("ProductDaoImpl")
	private ProductDAO productDao;	
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}

	///Constructor
		public ProductServiceImpl() {
			System.out.println(this.getClass());
		}

	///method
	
	//상품 추가
	public void addProduct(Product product) throws Exception{
	productDao.addProduct(product);
	}	
	//상품 정보가지고 오기
	public Product getProduct(int prodNo) throws Exception{
	 return productDao.getProduct(prodNo);
	}

	//상품 수정
	public void updateProduct(Product product) throws Exception{
		productDao.updateProduct(product);
	}
	
	//상품 리스트 
	public HashMap<String,Object> getProductList(Search search) throws Exception{
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("list",list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
				
	}
	
}