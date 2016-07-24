package org.ksmart02.fruitmall.refund.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.ksmart02.fruitmall.util.ConnectionPool;

public class RefundDao {
	
	//orders_no를 기준으로 환불 신청이 되어 있는지 확인하는 메서드
	public String selectOrdersRefundColumn(RefundRequest refundRequest) throws Exception{
		System.out.println("RefundDao의 selectOrdersRefundColumn 실행");
		
		String result = "";
		
		Connection conn			= ConnectionPool.getConnection();
		String sql				= "select orders_refund from orders where orders_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, refundRequest.getOrdersNo());
		ResultSet rs			= pstmt.executeQuery();
		while(rs.next()){
			result = rs.getString(1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	//환불 입력 메서드
	public int insertRefund(RefundRequest refundRequest, Connection conn) throws Exception{
		System.out.println("RefundDao의 insertRefund 실행");
		
		String sql				= "INSERT INTO refund (orders_no, member_id, refund_date, refund_reason, refund_confirm, refund_money) "
								+ "VALUES (?, ?, NOW(), ?, 'N', ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, refundRequest.getOrdersNo());
		pstmt.setString(2, refundRequest.getMemberId());
		pstmt.setString(3, refundRequest.getRefundReason());
		pstmt.setInt(4, refundRequest.getRefundMoney());
		int result 				= pstmt.executeUpdate();
		
		return result;
	}
	
	//orders 테이블의 환불 column을 Update하는 메서드
	public int updateOrdersByOrdersNo(RefundRequest refundRequest, Connection conn) throws Exception{
		System.out.println("RefundDao의 updateOrdersByOrdersNo 실행");
		
		String sql				= "UPDATE orders SET orders_refund='Y' WHERE orders_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, refundRequest.getOrdersNo());
		int result 				= pstmt.executeUpdate();
		
		return result;
	}

}
