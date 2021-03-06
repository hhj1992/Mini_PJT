package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {
	public ProductDAO() {
	}

	public void insertUser(ProductVO productVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into product values ((select max(prod_no)+1 from product),?,?,?,?,?,sysdate)";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, productVO.getProdName()); // 1번은 첫번째 물음표!
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();

		con.close();
	}

	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//
//		Connection con = DBUtil.getConnection();
//
//		String sql = "select * from product order by reg_date desc";
//		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
//				ResultSet.CONCUR_UPDATABLE);
//
//		ResultSet rs = stmt.executeQuery();
//		// rs에서 1번부터 10번까지의 데이터가 나왔어
//		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
//
//		rs.last(); // 1번~10번까지 -> 커서를 10번 줄로감
//		int total = rs.getRow(); // ->10
//
//		// 2페이지를 눌렀을때, ListProductAction에서 setpage한 결과
//		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
//		System.out.println("searchVO.getPage():" + searchVO.getPage());
//		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
//
//		if (total > 0) {
//			for (int i = 0; i < searchVO.getPageUnit(); i++) {
//				ProductVO productVO = new ProductVO();
//				productVO.setProdNo(rs.getInt("PROD_NO"));
//				productVO.setProdName(rs.getString("PROD_NAME"));
//				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
//				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
//				productVO.setPrice(rs.getInt("PRICE"));
//				productVO.setFileName(rs.getString("IMAGE_FILE"));
//				productVO.setRegDate(rs.getDate("REG_DATE"));
//				productList.add(productVO);
//			}
//		}
//		
//		
//
//		hashMap.put("count", total);
//		con.close();
//		hashMap.put("productList", productList);
//		return hashMap;
//	}
		Connection con = DBUtil.getConnection();
		
		String sql = "Select p.*,NVL((Select tran_status_code from transaction t Where p.prod_no = t.prod_no),0) trans_code,(Select tran_no from transaction t Where p.prod_no = t.prod_no) tran_no from product p";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where prod_no Like'%" + searchVO.getSearchKeyword()
						+ "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where prod_name Like'%" + searchVO.getSearchKeyword()
						+ "%'";
			}else if (searchVO.getSearchCondition().equals("2")) {
				sql += " where price Like '%" + searchVO.getSearchKeyword()
				+ "%'";
			}
		}
		sql += " order by PROD_NO"; 

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last(); //결과값의 맨마지막으로 커서이동해
		int total = rs.getRow(); //그럴때의 몇번째 로우수인지 -> 맨마지막값의 로우수를 가져옴
		//
		System.out.println("로우줄수^^ :" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
                      
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < 3; i++) {
				ProductVO vo = new ProductVO();
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				vo.setProTranCode(rs.getString("trans_code").trim());
				vo.setTransNo(rs.getString("tran_no"));

				list.add(vo); //list[0],[1],[2] 
				if (!rs.next())
					break;
			}
		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}	
		

	public ProductVO getProduct(int prodNo) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "select * from product where PROD_NO = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ProductVO productVO = new ProductVO();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}

		return productVO;

	}

	public void updateProduct(ProductVO productVO) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "update product set PROD_NAME=?, PROD_DETAIL=?, MANUFACTURE_DAY=?, PRICE=?, IMAGE_FILE=? where PROD_NO = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());

		stmt.executeUpdate();

		con.close();

	}
}
