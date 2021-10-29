package com.model2.mvc.service.prouduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

public interface ProductDAO {

	public void addProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;

	public List<Product> getProductList(Search search) throws Exception;
      
//	public String makeCurrentPageSql(String sql , Search search);
//	
    public int getTotalCount(Search search) throws Exception;
     
 	public void updateProduct(Product product) throws Exception; 
     
}
