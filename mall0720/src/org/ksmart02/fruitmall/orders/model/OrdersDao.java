package org.ksmart02.fruitmall.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksmart02.fruitmall.item.model.ItemRequest;
import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.util.ConnectionPool;

public class OrdersDao {
	
	//환불확정을 확인하는 메서드 입니다 0717
	public List<Map> selectRefundConfirm(List<Map> ordersList) throws Exception{
		System.out.println("OrdersDao의 selectRefundConfirm 실행");

		if(ordersList.size() != 0){
			ResultSet rs 			= null;
			Connection conn			= ConnectionPool.getConnection();
			String sql				= "select r.refund_confirm, r.refund_confirm_date, o.orders_no from orders o inner join refund r "
					+ "on o.orders_no = r.orders_no where o.orders_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(int i = 0; i<ordersList.size(); i++){
				pstmt.setInt(1, (int)ordersList.get(i).get("ordersNo"));
				rs	= pstmt.executeQuery();
				
				while(rs.next()){
					
					ordersList.get(i).put("refundConfirm", rs.getString(1));
					ordersList.get(i).put("refundConfirmDate", rs.getString(2));
					ordersList.get(i).put("ordersNo", rs.getInt(3));
				
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		
		return ordersList;
	}
	
	//현재 주문 상태인 내역을 가져오는 메서드입니다 0710 안소영
	public ArrayList<Map> selectOrdersByMemberId(Member member) throws Exception{
		System.out.println("OrdersDao의 selectOrdersByMemberId 메서드 실행 ");
		
		ArrayList<Map> list = new ArrayList<Map>();
		
		Connection conn				= ConnectionPool.getConnection();
		String sql					= "SELECT i.item_image itemImage, i.item_name itemName, o.orders_group ordersGroup, "
				+ "o.orders_count ordersCount, o.orders_total_price ordersTotalPrice, o.orders_date ordersDate, o.orders_no ordersNo, "
				+ "o.orders_refund ordersRefund, o.orders_delivery_confirm deliveryConfirm , i.item_no itemNo FROM orders o inner join item i "
				+ "ON o.item_no = i.item_no where o.member_id = ? and o.orders_confirm = 'N' order by o.orders_date desc";
		PreparedStatement pstmt		 = conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		ResultSet rs			= pstmt.executeQuery();
		while(rs.next()){
			
			Map<String, Object> smallMap	= new HashMap<String, Object>();
			
			smallMap.put("itemImage", rs.getString(1));
			smallMap.put("itemName", rs.getString(2));
			smallMap.put("ordersGroup", rs.getString(3));
			smallMap.put("ordersCount", rs.getInt(4));
			smallMap.put("ordersTotalPrice", rs.getInt(5));
			smallMap.put("ordersDate", rs.getString(6));
			smallMap.put("ordersNo", rs.getInt(7));
			smallMap.put("ordersRefund", rs.getString(8));
			smallMap.put("deliveryConfirm", rs.getString(9));
			smallMap.put("itemNo",rs.getInt(10));
			list.add(smallMap);
		}
		
		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	
	//구매 수량만큼 재고를 차감하는 메서드 입니다 0717 안소영
	public int updateItemStock(Connection conn, OrdersRequest ordersRequest, ArrayList<Integer> stockArr) throws Exception{
		System.out.println("OrdersDao의 updateItemStock 실행");
		
		int result				= 0;
		String sql				= "UPDATE item SET item_stock= ? WHERE item_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for(int i = 0; i<ordersRequest.getItemRequest().getItemNo().length; i++){
			pstmt.setInt(1, (stockArr.get(i)) - (ordersRequest.getOrdersCount()[i]) );
			pstmt.setInt(2, ordersRequest.getItemRequest().getItemNo()[i]);
			result	= pstmt.executeUpdate();
		}
		
		pstmt.close();
		
		return result;
	}
	
	//재고를 확인하는 메서드입니다 0717 안소영
	public ArrayList<Integer> selectItemStock(Connection conn, OrdersRequest ordersRequest) throws SQLException{
		System.out.println("OrdersDao의 selectItemStock 실행");
		
		int stock				= 0;
		ArrayList<Integer> stockArr	= new ArrayList<Integer>();
		String sql				= "select item_stock from item where item_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for(int i = 0; i<ordersRequest.getItemRequest().getItemNo().length; i++){
			pstmt.setInt(1, ordersRequest.getItemRequest().getItemNo()[i]);
			ResultSet rs	= pstmt.executeQuery();
			
			while(rs.next()){
				stock		= rs.getInt(1);
				stockArr.add(stock);
			}
		}
		
		pstmt.close();
		
		return stockArr;
		
	}
	
	//구매 확정을 하는 메서드입니다 0713 안소영
	public void updateOrdersConfirm(int ordersNo) throws Exception{
		System.out.println("OrdersDao의 updateOrdersConfirm 실행");
		
		Connection conn			= ConnectionPool.getConnection();
		String sql				= "UPDATE orders SET orders_confirm='Y', orders_confirm_date=NOW() WHERE orders_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ordersNo);
		pstmt.executeUpdate();
	}
	
	//주문 상세를 보여주는 메서드 입니다 0713 안소영
	public Map<String, Object> selectOrdersByOrdersNo(int ordersNo) throws Exception{
		System.out.println("OrdersDao의 selectOrdersByOrdersNo 실행");
		
		Map<String, Object> orderDetailMap = new HashMap<String, Object>();
		Connection conn			= ConnectionPool.getConnection();
		String sql				= "select i.item_name, i.item_image, i.item_origin, i.seller_no, o.orders_payment_method, o.orders_delivery_date, "
				+ "o.orders_recipient_name, o.orders_recipient_phone, o.orders_recipient_parceladdr, o.orders_confirm_date "
				+ "from item i inner join orders o on i.item_no = o.item_no where o.orders_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ordersNo);
		ResultSet rs			= pstmt.executeQuery();
		while(rs.next()){
			orderDetailMap.put("itemName", rs.getString(1));
			orderDetailMap.put("itemImage", rs.getString(2));
			orderDetailMap.put("itemOrigin", rs.getString(3));
			orderDetailMap.put("sellerNo", rs.getInt(4));
			orderDetailMap.put("PaymentMethod", rs.getString(5));
			orderDetailMap.put("deliveryDate", rs.getString(6));
			orderDetailMap.put("recipientName", rs.getString(7));
			orderDetailMap.put("recipientPhone", rs.getString(8));
			orderDetailMap.put("recipientAddr", rs.getString(9));
			orderDetailMap.put("recipientConfirmDate", rs.getString(10));
		}
		
		return orderDetailMap;
	}
	
	//cart에서 delete하는 메서드입니다 0717 안소영
	public int deleteCart(Connection conn, OrdersRequest ordersRequest) throws Exception{
		System.out.println("OrdersDao의 deleteCart 실행");
		int result				= 0;
		
		String sql				= "DELETE FROM cart WHERE cart_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for(int i = 0; i<ordersRequest.getCart().length; i++){
			pstmt.setInt(1, ordersRequest.getCart()[i]);
			result				= pstmt.executeUpdate();
		}
	
		pstmt.close();
		
		return result;
	}
	
	//아이템 여러 개를 주문하는 메서드입니다  0710 안소영
	public int insertOrderItems(Connection conn, OrdersRequest ordersRequest, int ordersGroup) throws Exception{
		System.out.println("OrdersDao의 insertOrderItems 실행");
		int result = 0;
		
		//아이템 no의 배열을 받습니다
		ItemRequest itemRequest = new ItemRequest();
		itemRequest = ordersRequest.getItemRequest();
		int[] itemNoList		= new int[itemRequest.getItemNo().length];
		itemNoList				= itemRequest.getItemNo();	
	//	System.out.println("여기: "+itemNoList.toString());
		int itemNoLength		= itemNoList.length;

		//ordersCount의 배열을 받습니다
		int[] ordersCountList = ordersRequest.getOrdersCount();
		
		//ordersGroup을 만듭니다
		String sql			= "INSERT INTO orders "
							+ "(item_no, member_id, orders_group, orders_total_price, orders_count, orders_date, orders_recipient_name, "
							+ "orders_recipient_phone, orders_recipient_post, orders_recipient_parceladdr, orders_recipient_roadaddr, orders_confirm, orders_refund, "
							+ "orders_payment_method) VALUES (?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, 'N', 'N', ?)";
		
		PreparedStatement pstmt	= conn.prepareStatement(sql);
		
		for(int i = 0; i<itemNoLength; i++){
			//for문에서 itemNoList와 ordersCountList의 값을 뽑아 각각 itemNo와 ordersCount에 set하고, ordersGroup을 이전 값+1 한다
			
			pstmt.setInt(1, itemNoList[i]);
			pstmt.setString(2, ordersRequest.getMember().getMemberId());
			pstmt.setInt(3, ordersGroup);
			pstmt.setInt(4, ordersRequest.getOrdersTotalPrice()[i]*ordersCountList[i]);
			pstmt.setInt(5, ordersCountList[i]);
			pstmt.setString(6, ordersRequest.getOrdersRecipientName());
			pstmt.setString(7, ordersRequest.getOrdersRecipientPhone());
			pstmt.setInt(8, ordersRequest.getOrdersRecipientPost());
			pstmt.setString(9, ordersRequest.getOrdersRecipientParceladdr());
			pstmt.setString(10, ordersRequest.getOrdersRecipientRoadAddr());
			pstmt.setString(11, ordersRequest.getOrdersPaymentMethod());
			
			result					= pstmt.executeUpdate();
		}
		
		pstmt.close();
		
		return result;
	}

	// ordersGroup을 생성하는 메서드입니다 0710 안소영
	public int selectLargestOrdersGroupFromOrders() throws Exception{
		System.out.println("OrdersDao의 selectLargestOrdersGroupFromOrders 실행");
		
		int maxOrdersGroupNo = 0;
		
		Connection conn			= ConnectionPool.getConnection();
		String sql				= "select max(orders_group) from orders";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs			= pstmt.executeQuery();
		while(rs.next()){
			maxOrdersGroupNo = rs.getInt(1)+1;
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return maxOrdersGroupNo;
	}
	
	
	//과거 주문내역을 가져오는 메서드입니다 0710 안소영
		public ArrayList<Map> selectOrdersPastByMemberId(Member member) throws Exception{
			System.out.println("OrdersDao의 selectOrdersPastByMemberId 메서드 실행 ");
			
			ArrayList<Map> list = new ArrayList<Map>();
			
			Connection conn				= ConnectionPool.getConnection();
			String sql					= "SELECT i.item_image, i.item_name, o.orders_group, o.orders_count, o.orders_total_price, o.orders_date, i.item_no, "
										+ "o.orders_no ordersNo "
										+ "FROM orders o inner join item i ON o.item_no = i.item_no where o.member_id = ? and "
										+ "o.orders_confirm = 'Y' order by o.orders_date desc";
			PreparedStatement pstmt		= conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			ResultSet rs				= pstmt.executeQuery();
			while(rs.next()){
				
				Map<String, Object> smallMap	= new HashMap<String, Object>();
				
				smallMap.put("itemImage", rs.getString(1));
				smallMap.put("itemName", rs.getString(2));
				smallMap.put("ordersGroup", rs.getInt(3));
				smallMap.put("ordersCount", rs.getInt(4));
				smallMap.put("ordersTotalPrice", rs.getInt(5));
				smallMap.put("ordersDate", rs.getObject(6));
				smallMap.put("itemNo", rs.getInt(7));
				smallMap.put("ordersNo", rs.getInt(8));
				list.add(smallMap);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return list;
		}

}
