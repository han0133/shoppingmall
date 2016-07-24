package org.ksmart02.fruitmall.cart.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.util.ConnectionPool;

public class CartDao {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	Cart cart;
	CartRequest cartRequest;
	ArrayList<Cart> cartList;
	ArrayList<CartRequest> cartarr;
	
	//장바구니에 담긴 상품목록을 보여주는 메서드 입니다. 0709  박효민
	public ArrayList<CartRequest> selectCartListByMemberId(String memberId) throws Exception{
		System.out.println("CartDao의 selectCartListByMemberId실행");
		
		conn 	= ConnectionPool.getConnection();
		sql 	= "select i.item_price itemPrice, c.cart_no cartNo, i.item_no itemNo, i.item_image itemImage,c.member_id memberId, c.cart_count cartCount, "
				+ "i.item_stock itemStock from cart c inner join item i on c.item_no = i.item_no where c.member_id = ?";
		pstmt 	= conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		rs		= pstmt.executeQuery();

		
		cartarr		= new ArrayList<CartRequest>();
		while(rs.next()) {
			
			
			Item item	= new Item();
			item.setItemNo(rs.getInt("itemNo"));
			item.setItemStock(rs.getInt("itemStock"));
			item.setItemPrice(rs.getInt("itemPrice"));
			item.setItemImage(rs.getString("itemImage"));
			
			cartRequest	= new CartRequest();
			cartRequest.setCartNo(rs.getInt("cartNo"));
			cartRequest.setItem(item);
			cartRequest.setMemberId(rs.getString("memberId"));
			cartRequest.setCartCount(rs.getInt("cartCount"));
			
		//	System.out.println(cartRequest);
			/*cartList.put("cartRequest",	cartRequest);*/
			cartarr.add(cartRequest);
		}
		
		
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return cartarr;
	}
	
	//장바구니에 아이템을 담는 메서드입니다  0710 안소영
	public void insertCart(Cart cart) throws Exception{
		System.out.println("CartDao의 insertCart실행");
		
		conn 	= ConnectionPool.getConnection();
		sql 	= "INSERT INTO cart (item_no, member_id, cart_count) VALUES (?, ?, ?)";
		pstmt 	= conn.prepareStatement(sql);
		pstmt.setInt(1, cart.getItemNo());
		pstmt.setString(2, cart.getMemberId());
		pstmt.setInt(3, cart.getCartCount());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	//장바구니에 담긴 상품을 삭제하는 메서드입니다
	public ArrayList<Cart> deleteCart(int cartNo) throws Exception{
		System.out.println("CartDao deleteCart실행");
		
		conn 	= ConnectionPool.getConnection();
		sql 	= "DELETE FROM cart WHERE cart_no=?";
		pstmt 	= conn.prepareStatement(sql);
		pstmt.setInt(1, cartNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return cartList;
	}
}
