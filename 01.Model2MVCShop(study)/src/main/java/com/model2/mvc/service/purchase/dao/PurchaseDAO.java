package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {
	
	public PurchaseDAO() {
	}

	public void addPurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into transaction values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo()); // 1���� ù��° ����ǥ!
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setDate(9, purchaseVO.getOrderDate());
		
		
		stmt.executeUpdate();
		con.close();
	}

	public HashMap<String, Object> getPurchasetList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT tran_no, BUYER_ID, RECEIVER_NAME, RECEIVER_PHONE, tran_status_code from transaction";
//		if (searchVO.getSearchCondition() != null) {
//			if (searchVO.getSearchCondition().equals("0")) {
//				sql += " where prod_no Like'%" + searchVO.getSearchKeyword()
//						+ "%'";
//			} else if (searchVO.getSearchCondition().equals("1")) {
//				sql += " where prod_name Like'%" + searchVO.getSearchKeyword()
//						+ "%'";
//			}else if (searchVO.getSearchCondition().equals("2")) {
//				sql += " where price Like '%" + searchVO.getSearchKeyword()
//				+ "%'";
//			}
//		}
//		sql += " order by PROD_NO";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last(); //������� �Ǹ��������� Ŀ���̵���
		int total = rs.getRow(); //�׷����� ���° �ο������ -> �Ǹ��������� �ο���� ������
		//
		
		System.out.println("�ο��ټ�^^ :" + total);


//		HashMap<String,Object> map = new HashMap<String,Object>();
//		map.put("count", new Integer(total));
//                      
//		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
//		System.out.println("searchVO.getPage():" + searchVO.getPage());
//		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
//
//		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < 3; i++) {
//				ProductVO vo = new ProductVO();
//				vo.setProdName(rs.getString("PROD_NAME"));
//				vo.setProdNo(rs.getInt("PROD_NO"));
//				vo.setPrice(rs.getInt("PRICE"));
//				vo.setRegDate(rs.getDate("REG_DATE"));
//				vo.setProTranCode("�Ǹ���");
//
//				// Ŀ���� �˾Ƽ� ������ ��������? ��. 
//				
//				list.add(vo); //list[0],[1],[2]
				
				System.out.println(rs.getString("BUYER_ID"));
				if (!rs.next())
					break;
				
			}
		}
//		System.out.println("list.size() : "+ list.size());
//		map.put("list", list);
//		System.out.println("map().size() : "+ map.size());
//
//		con.close();
			
//		return map;
		return null;
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
		
		System.out.println(productVO);
		
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
