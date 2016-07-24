package org.ksmart02.fruitmall.cart.service;

import java.util.ArrayList;
import java.util.Map;

import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.cart.model.CartDao;
import org.ksmart02.fruitmall.cart.model.CartRequest;

public class CartService {
	Cart cart;
	CartDao cartDao;
	ArrayList<CartRequest> cartList;
	ArrayList<Cart> cartListByDel;
	
	//주석 써주세요
	public ArrayList<CartRequest> cartList(String memberId) throws Exception {
		System.out.println("CartService의 cartList 실행..");
		
		cartDao 		= new CartDao();
		cartList 		= cartDao.selectCartListByMemberId(memberId);
	//	System.out.println("size1: "+cartList.size());
		
		return cartList;
	}
	
	//deleteCart 서비스 박효민 0716
	public ArrayList<Cart> cartDelService(String[] checkedItems) {
		System.out.println("CartService의 cartDelService 실행..");
		
		cartDao = new CartDao();
		int[] cartNos = new int[checkedItems.length];
		
		for(int i=0; i<cartNos.length; i++) {
			cartNos[i] = Integer.parseInt(checkedItems[i]);
			try {
				cartListByDel = cartDao.deleteCart(cartNos[i]);
			} catch (Exception e) {
				System.out.println("cartDelService에 예외 던져짐");
				e.printStackTrace();
			}
		}
		return cartListByDel; 
	}
	
}
