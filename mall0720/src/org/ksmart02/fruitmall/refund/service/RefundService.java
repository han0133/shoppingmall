package org.ksmart02.fruitmall.refund.service;

import java.sql.Connection;

import org.ksmart02.fruitmall.refund.model.RefundDao;
import org.ksmart02.fruitmall.refund.model.RefundRequest;
import org.ksmart02.fruitmall.util.ConnectionPool;

public class RefundService {
	private RefundDao refundDao;
	
	// 환불을 처리하는 메서드입니다
	public void refundProcess(RefundRequest refundRequest) throws Exception{
		System.out.println("RefundService의 refundProcess()...");
		refundDao 			= new RefundDao();
		
		//환불신청이 되어 있는지 확인
		String result = refundDao.selectOrdersRefundColumn(refundRequest);
		System.out.println("환불신청여부: "+result);
		//이전에 환불 신청을 하지 않았다면 실행
		if(result.equals("N")){
			System.out.println("환불 service 실행");
			//트랜젝션을 위한 connection pool 연결
			Connection conn		= ConnectionPool.getConnection();
			conn.setAutoCommit(false);
			
			// refund 테이블에 insert  
			int insertResult	= refundDao.insertRefund(refundRequest, conn);
			System.out.println("insert 메서드 결과: "+insertResult);
			// orders 테이블의 해당 row의 orders_refund의 값을 update
			int updateResult	= refundDao.updateOrdersByOrdersNo(refundRequest, conn);
			System.out.println("insert 메서드 결과: "+updateResult);
			if(insertResult == 0 || updateResult == 0 ){
				conn.rollback();
				System.out.println("RefundService의 환불 처리 메서드 실패");
			}else{
				conn.commit();
				System.out.println("RefundService의 환불 처리 메서드 성공");
			}
			
			conn.setAutoCommit(true);
			conn.close();
		}
		
		
	}
}
