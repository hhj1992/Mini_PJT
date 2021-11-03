package com.model2.mvc.service.prouduct.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.prouduct.ProductDAO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.UserDao;;


//==> 회원관리 서비스 구현
@Repository("ProductDaoImpl")
public class ProductDaoImpl implements ProductDAO{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("sqlSessions"+sqlSession);
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public int addProduct(Product product) throws Exception{
		return sqlSession.insert("ProductMapper.addProduct", product);
	} 

	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

//	public String makeCurrentPageSql(Search search){
//		        
//	 	 String sql = sqlSession.selectOne("productMapper.getmakeCurrentPageSql",search)
//	 	 return sql;
//	}
	
    public int getTotalCount(Search search) throws Exception{
    	return sqlSession.selectOne("ProductMapper.getTotalCount", search);
    }

	public void updateProduct(Product product) throws Exception{
		sqlSession.update("ProductMapper.updateProduct", product);
	}


	
	
	
	
}