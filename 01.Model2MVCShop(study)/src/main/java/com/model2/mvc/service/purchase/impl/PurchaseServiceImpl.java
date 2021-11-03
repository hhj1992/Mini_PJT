package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService{
	private ProductDAO productDao;
	
	public PurchaseServiceImpl() {
		productDao = new ProductDAO();  
	}
	public void addProduct(ProductVO productVO) throws Exception {
		productDao.insertUser(productVO);
	}
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception{
		return productDao.getProductList(searchVO);
	}
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	public void updateProduct(ProductVO productVO) throws Exception {
		productDao.updateProduct(productVO);
	}
	
	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PurchaseVO getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
