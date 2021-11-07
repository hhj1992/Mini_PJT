package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDAO purchaseDao;
	
	public PurchaseServiceImpl() {
		purchaseDao = new PurchaseDAO();  
	}
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.addPurchase(purchaseVO);
	}

	
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	
	public PurchaseVO getPurchase2(int prodNo) throws Exception {
		return null;
	}

	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
				
		return purchaseDao.getPurchaseList(searchVO,buyerId);
	}

	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		return null;
	}

	
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.updatePurcahse(purchaseVO);
	}

	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		purchaseDao.updateTranCode(purchaseVO);
	} 
	
}
