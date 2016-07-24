package org.ksmart02.fruitmall.orders.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.orders.model.OrdersDao;
import org.ksmart02.fruitmall.orders.model.OrdersRequest;
import org.ksmart02.fruitmall.util.ConnectionPool;

public class OrdersService {
	
	private OrdersDao ordersDao;
	
	//환불요청을 확인하는 메서드입니다
	public List<Map> checkRefund(Member member) throws Exception{
		ordersDao = new OrdersDao();
		
		List<Map> ordersList = ordersDao.selectOrdersByMemberId(member);
		ordersList 			 = ordersDao.selectRefundConfirm(ordersList);

		return ordersList;
	}

	/*
	 * 주문하는 메서드입니다 0717 안소영
	 */
	public void orderItems(OrdersRequest ordersRequest) throws Exception{
		System.out.println("OrdersService의 orderItems 실행..");
		
		//1. 가장 큰 ordersGroup의 값을 구합니다
		int ordersGroup	= 0;
		ordersDao		= new OrdersDao();
		ordersGroup 	= ordersDao.selectLargestOrdersGroupFromOrders();
		
		Connection conn	= ConnectionPool.getConnection();
		conn.setAutoCommit(false);
		
		//2. orders에 insert하는 메서드를 호출합니다
		int insertResult	= ordersDao.insertOrderItems(conn, ordersRequest, ordersGroup);
		System.out.println("insertResult: "+insertResult);
		int deleteResult	= 1;
		
		
		//3. item에서 주문 수량만큼 재고를 차감합니다
		//3.1  재고를 확인합니다
		ArrayList<Integer> stockArr	= ordersDao.selectItemStock(conn, ordersRequest);
		//3.1 재고를 차감합니다
		int updateResult	= ordersDao.updateItemStock(conn, ordersRequest, stockArr);
		System.out.println("updateResult: "+updateResult);
		
		if( ordersRequest.getCart() != null  ){
			//4. cart에서 delete하는 메서드를 호출합니다
			deleteResult	= ordersDao.deleteCart(conn, ordersRequest);
			System.out.println("deleteResult: "+deleteResult);
		}
		
		if(insertResult == 0 || deleteResult == 0 || updateResult == 0){
			System.out.println("orderItems의 transcation 실패");
			conn.rollback();
		}
		
		conn.commit();
		conn.setAutoCommit(true);
		conn.close();
	}
	
}
