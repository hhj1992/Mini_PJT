package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

public class ProductDAO {

	public void addProduct(Product product) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO product values ((select max(prod_no)+1 from product),?,?,?,?,?,sysdate)";
		// INSER 공식 INSERT INTO TABLE명 values ( ) 안에 , 기준으로 table 컬럼 갯수만큼 ?
		PreparedStatement sms = con.prepareStatement(sql);

		sms.setString(1, product.getProdName());
		sms.setString(2, product.getProdDetail());
		sms.setString(3, product.getManuDate());
		sms.setInt(4, product.getPrice());
		sms.setString(5, product.getFileName());
		sms.executeUpdate();

		con.close();

	}
	
	public Product getProduct(int prodNo) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "select * from product where PROD_NO = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		Product product = new Product();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
		}

		return product;
	}

			

	public HashMap<String, Object> getProductList(Search search) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        Connection con = DBUtil.getConnection();

        String sql = "SELECT * FROM product ";

        if (search.getSearchCondition() != null) {
           if (search.getSearchCondition().equals("0") && search.getSearchKeyword() != null) {
              sql += "Where Prod_no Like '%" + search.getSearchKeyword() + "%'";
           }
           if (search.getSearchCondition().equals("1") && search.getSearchKeyword() != null) {
              sql += "Where Prod_name Like '%" + search.getSearchKeyword() + "%'";
           }
           if (search.getSearchCondition().equals("2") && search.getSearchKeyword() != null) {
              sql += "Where price Like '%" + search.getSearchKeyword() + "%'";
           }
        }

        sql += " ORDER BY Prod_no";

        int totalcount = this.getTotalCount(sql);
        
        System.out.println("PRODUCTDAO :: totalCount ::" + totalcount);
        
        sql = makeCurrentPageSql(sql, search); // search는 페이징때문에 넘긴다. 
        PreparedStatement sms = con.prepareStatement(sql);
        ResultSet rs = sms.executeQuery();
        
        System.out.println(search);
        
        List<Product> list = new ArrayList<Product>();
           
           while(rs.next()){
              Product product = new Product();
              product.setProdNo(rs.getInt("PROD_NO"));
              product.setProdName(rs.getString("PROD_NAME"));
              product.setPrice(rs.getInt("PRICE"));
              product.setRegDate(rs.getDate("REG_DATE"));
              list.add(product);
           }
           
          map.put("totalCount", new Integer(totalcount));
          map.put("list", list);
          rs.close();
          con.close();
          sms.close();

        return map;
     }

        private String makeCurrentPageSql(String sql , Search search){ //페이징을 처리하기위해 ROWENUM을 사용했다
              sql =    "SELECT * "+ 
                       "FROM (SELECT inner_table. * ,  ROWNUM AS row_seq " +
                         "    FROM (   "+sql+" ) inner_table "+
                         "   WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +// 현재열린페이지 * 3개씩 ? 1 2 3 4 5 6 * 3 ?  = ??? 뭘 원하는거야? 
                         // DB의 부하를 줄이기위해서 처음 SELECT할때 저렇게 불러오는 듯 ^^ ㅋ 아 쫌 그렇네 열 받 네 
             "WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
    
              System.out.println("UserDAO :: make SQL :: "+ sql);   
              
              //★★1페이지일경우 
              /*SELECT *
              FROM   (SELECT inner_table. * ,
                             ROWNUM AS row_seq
                      FROM   (SELECT user_id ,
                                     user_name ,
                                     email
                              FROM   users
                              WHERE  user_id like '%user%'
                              ORDER BY user_id ) inner_table
                      WHERE  ROWNUM <=3 )
              WHERE  row_seq BETWEEN 1 AND 3 ;*/
              
              //★★2페이지일경우
              /*SELECT *
              FROM   (SELECT inner_table. * ,
                             ROWNUM AS row_seq
                      FROM   (SELECT user_id ,
                                     user_name ,
                                     email
                              FROM   users
                              WHERE  user_id like '%user%'
                              ORDER BY user_id ) inner_table
                      WHERE  ROWNUM <=6 )
              WHERE  row_seq BETWEEN 4 AND 6;*/

              //위와 같은형태로  
              
              return sql;
           }

     private int getTotalCount(String sql) throws Exception {

        sql = "SELECT COUNT(*) " + "FROM ( " + sql + ") countTable";

        Connection con = DBUtil.getConnection();
        PreparedStatement pStmt = con.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();

        int totalCount = 0;
        if (rs.next()) {
           totalCount = rs.getInt(1);
        }

        pStmt.close();
        con.close();
        rs.close();

        return totalCount;
     }
     
 	public void updateProduct(Product product) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "update product set PROD_NAME=?, PROD_DETAIL=?, MANUFACTURE_DAY=?, PRICE=?, IMAGE_FILE=? where PROD_NO = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());

		stmt.executeUpdate();

		con.close();

	}
     
     
     
     
     
}
