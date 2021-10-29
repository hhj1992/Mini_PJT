package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.User;


public class UserDao {
   
   ///Field
   
   ///Constructor
   public UserDao() {
   }

   ///Method
   public void insertUser(User user) throws Exception {
      
      Connection con = DBUtil.getConnection();
      
      String sql =    "INSERT "+ 
                        "INTO USERS "+ 
                        "VALUES (?,?,?,'user',?,?,?,?,SYSDATE)";
      
      PreparedStatement pStmt = con.prepareStatement(sql);
      pStmt.setString(1, user.getUserId());
      pStmt.setString(2, user.getUserName());
      pStmt.setString(3, user.getPassword());
      pStmt.setString(4, user.getSsn());
      pStmt.setString(5, user.getPhone());
      pStmt.setString(6, user.getAddr());
      pStmt.setString(7, user.getEmail());
      pStmt.executeUpdate();
      
      pStmt.close();
      con.close();
   }

   public User findUser(String userId) throws Exception {
      
      Connection con = DBUtil.getConnection();
         
      String sql =    "SELECT "+
                        "user_id ,  user_name ,  password , role , cell_phone ,  addr ,  email , reg_date " + 
                        "FROM users WHERE user_id = ?";
      
      PreparedStatement pStmt = con.prepareStatement(sql);
      pStmt.setString(1, userId);

      ResultSet rs = pStmt.executeQuery();

      User user = null; 

      while (rs.next()) {
         user = new User();
         user.setUserId(rs.getString("user_id"));
         user.setUserName(rs.getString("user_name"));
         user.setPassword(rs.getString("password"));
         user.setRole(rs.getString("role"));
         user.setPhone(rs.getString("cell_phone"));
         user.setAddr(rs.getString("addr"));
         user.setEmail(rs.getString("email"));
         user.setRegDate(rs.getDate("reg_date"));
      }
      
      rs.close();
      pStmt.close();
      con.close();
      
      return user;
   }

   public Map<String , Object> getUserList(Search search) throws Exception {
      
      Map<String , Object>  map = new HashMap<String, Object>(); //ListUserAction으로 반환시킬 map정의
      
      Connection con = DBUtil.getConnection(); // DB연결
      
      // Original Query     
      String sql = "SELECT user_id ,  user_name , email  FROM  users ";
   
      if (search.getSearchCondition() != null) { //검색조건이 null이 아닐경우
         if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) { //회원아이디로 조화할경우
            sql += " WHERE user_id like '%" + search.getSearchKeyword()+"%'"; //userID를  like로 조회
         } else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) { //회원이름으로 조회할경우
            sql += " WHERE user_name like '%" + search.getSearchKeyword()+"%'"; //user_name을 like로 조회
         }
      }
      sql += " ORDER BY user_id"; //user_id로 오름차순
      
      
      System.out.println("UserDAO::Original SQL :: " + sql); 
      
      //==> TotalCount GET
      int totalCount = this.getTotalCount(sql); //위의 만들어진 sql 을 디비에서 조회시에 개수(count)를 알기위해 조회함 ->
      System.out.println("UserDAO :: totalCount  :: " + totalCount);
      
      //==> CurrentPage  Խù     ޵    Query  ٽñ   
      sql = makeCurrentPageSql(sql, search);
      PreparedStatement pStmt = con.prepareStatement(sql);
      ResultSet rs = pStmt.executeQuery();
   
      System.out.println(search);

      List<User> list = new ArrayList<User>();
      
      while(rs.next()){
         User user = new User();
         user.setUserId(rs.getString("user_id"));
         user.setUserName(rs.getString("user_name"));
         user.setEmail(rs.getString("email"));
         list.add(user);
      }
      
      //==> totalCount          
      map.put("totalCount", new Integer(totalCount));
      //==> currentPage     Խù            List     
      map.put("list", list);

      rs.close();
      pStmt.close();
      con.close();

      return map;
   }

   public void updateUser(User vo) throws Exception {

      Connection con = DBUtil.getConnection();

      String sql =    "UPDATE users "+
                        "SET user_name = ?, cell_phone = ? , addr = ? , email = ? "+ 
                        "WHERE user_id = ?";
      
      PreparedStatement pStmt = con.prepareStatement(sql);
      pStmt.setString(1, vo.getUserName());
      pStmt.setString(2, vo.getPhone());
      pStmt.setString(3, vo.getAddr());
      pStmt.setString(4, vo.getEmail());
      pStmt.setString(5, vo.getUserId());
      pStmt.executeUpdate();
      
      pStmt.close();
      con.close();
   }
   
   //  Խ    Page ó            ü Row(totalCount)  return
   private int getTotalCount(String sql) throws Exception {
      
      sql = "SELECT COUNT(*) "+
                "FROM ( " +sql+ ") countTable";
      
      Connection con = DBUtil.getConnection();
      PreparedStatement pStmt = con.prepareStatement(sql);
      ResultSet rs = pStmt.executeQuery();
      
      int totalCount = 0;
      if( rs.next() ){
         totalCount = rs.getInt(1);
      }
      
      pStmt.close();
      con.close();
      rs.close();
      
      return totalCount;
   }
   
   //  Խ    currentPage Row     return 
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
}