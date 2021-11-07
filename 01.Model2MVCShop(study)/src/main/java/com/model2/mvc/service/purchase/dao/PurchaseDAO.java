package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo()); // 1번은 첫번째 물음표!
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

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO,String buyerId) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT tran_no, BUYER_ID, RECEIVER_NAME, RECEIVER_PHONE, tran_status_code from transaction where buyer_id =?";
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1, buyerId);
		
		ResultSet rs = stmt.executeQuery();

		rs.last(); //결과값의 맨마지막으로 커서이동해
		int total = rs.getRow(); //그럴때의 몇번째 로우수인지 -> 맨마지막값의 로우수를 가져옴
		//
		
		System.out.println("로우줄수^^ :" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			
			for (int i = 0; i < 3; i++) {
				System.out.println("i");
				PurchaseVO vo = new PurchaseVO();
				UserVO user = new UserVO();
				
				vo.setTranNo(rs.getInt("tran_no"));
				user.setUserId(rs.getString("BUYER_ID"));
				vo.setBuyer(user);
				vo.setReceiverName(rs.getString("RECEIVER_NAME"));
				vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				
				if(rs.getString("tran_status_code")==null) {
					vo.setTranCode("0");			
				}else {
					vo.setTranCode(rs.getString("tran_status_code").trim());
				}
				
				


				// 커서가 알아서 밑으로 내려오냐? 모름. 
				
				list.add(vo); //list[0],[1],[2] 
				if (!rs.next())
					break;
			}
		}
		System.out.println("listSize : "+list.size());
		System.out.println("list : "+list);
		map.put("list",list);
		return map;
	}	
	
	
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "Select * from transaction where tran_no = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			productVO.setProdNo(rs.getInt("PROD_NO"));
			purchaseVO.setPurchaseProd(productVO);		
			userVO.setUserId(rs.getString("BUYER_ID"));
			purchaseVO.setBuyer(userVO);			
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));			
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));			
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));			
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));			
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE")); //가지고 오는 컬럼의 Data type으로 get하는게 아닌듯. 			
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA")); 
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			/* purchaseVO.setTranNo(rs.getInt("RECEIVER_NAME")); */
		}		
		System.out.println(purchaseVO);		
		return purchaseVO;
	}
	

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "update transaction set tran_status_code = ? where tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getTranNo());


		stmt.executeUpdate();

		con.close();

	}
	
	/*
	 * public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
	 * 
	 * Connection con = DBUtil.getConnection();
	 * 
	 * String sql = "update transaction set tran_status_code = ? where tran_no = ?";
	 * 
	 * PreparedStatement stmt = con.prepareStatement(sql);
	 * 
	 * 
	 * userVO.setUserId(request.getParameter("buyerId"));
	 * purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));// 쿼리조건.
	 * purchase.setPaymentOption(request.getParameter("paymentOption"));
	 * purchase.setReceiverName(request.getParameter("receiverName"));
	 * purchase.setReceiverPhone(request.getParameter("receiverPhone"));
	 * purchase.setDivyAddr(request.getParameter("receiverAddr"));
	 * purchase.setDivyRequest(request.getParameter("receiverRequest"));
	 * purchase.setDivyDate(request.getParameter("divyDate"));
	 * 
	 * 
	 * 
	 * stmt.setString(1, purchaseVO.getTranCode()); stmt.setInt(2,
	 * purchaseVO.getTranNo());
	 * 
	 * 
	 * stmt.executeUpdate();
	 * 
	 * con.close();
	 * 
	 * }
	 */
	

}
