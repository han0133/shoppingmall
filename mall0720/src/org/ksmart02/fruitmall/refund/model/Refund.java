package org.ksmart02.fruitmall.refund.model;

import java.sql.Date;

public class Refund {
	
	/* 변수 설명
	 * refundNo            환불 번호
	 * ordersNo            주문 번호 
	 * memberId            사용자 아이디 
	 * refundDate          환불요청 날짜(시간)
	 * refundReason        환불 사유
	 * refundConfirm       환불 확정 여부
	 * refundConfirmDate   환불 확정 날짜(시간)
	 * refundMoney         환불 금액
	 */
	private int refundNo;
	private int ordersNo;
	private String memberId;
	private String refundDate;
	private String refundReason;
	private String refundConfirm;
	private String refundConfirmDate;
	private int refundMoney;
	
	public int getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(int refundNo) {
		this.refundNo = refundNo;
	}
	public int getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getRefundConfirm() {
		return refundConfirm;
	}
	public void setRefundConfirm(String refundConfirm) {
		this.refundConfirm = refundConfirm;
	}
	public String getRefundConfirmDate() {
		return refundConfirmDate;
	}
	public void setRefundConfirmDate(String refundConfirmDate) {
		this.refundConfirmDate = refundConfirmDate;
	}
	public int getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(int refundMoney) {
		this.refundMoney = refundMoney;
	}
	
	@Override
	public String toString() {
		return "Refund [refundNo=" + refundNo + ", ordersNo=" + ordersNo + ", memberId=" + memberId + ", refundDate="
				+ refundDate + ", refundReason=" + refundReason + ", refundConfirm=" + refundConfirm
				+ ", refundConfirmDate=" + refundConfirmDate + ", refundMoney=" + refundMoney + "]";
	}
}
