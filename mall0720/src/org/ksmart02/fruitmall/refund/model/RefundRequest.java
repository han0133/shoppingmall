package org.ksmart02.fruitmall.refund.model;

public class RefundRequest {
	private int ordersNo;
	private String memberId;
	private String refundReason;
	private int refundMoney;
	
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
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public int getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(int refundMoney) {
		this.refundMoney = refundMoney;
	}
	@Override
	public String toString() {
		return "RefundRequest [ordersNo=" + ordersNo + ", memberId=" + memberId + ", refundReason=" + refundReason
				+ ", refundMoney=" + refundMoney + "]";
	}
	
}
