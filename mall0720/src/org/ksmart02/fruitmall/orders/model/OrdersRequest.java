package org.ksmart02.fruitmall.orders.model;

import java.sql.Timestamp;
import java.util.Arrays;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.cart.model.Cart;
import org.ksmart02.fruitmall.item.model.ItemRequest;

public class OrdersRequest {
	private int[] cart;
	private int ordersNo;
	private ItemRequest itemRequest;
	private Member member;
	private int ordersGroup;
	private int[] ordersTotalPrice;
	
	private String ordersDeliveryConfirm;
	private Timestamp ordersDeliveryDate;
	private int[] ordersCount;
	private Timestamp ordersDate;
	private String ordersRecipientName;
	private String ordersRecipientPhone;
	private int ordersRecipientPost;
	private String ordersRecipientParceladdr;
	private String ordersRecipientRoadAddr;
	private String ordersConfirm;
	private Timestamp ordersConfirmDate;
	private String ordersRefund;
	private String ordersPaymentMethod;
	
	
	public int[] getOrdersTotalPrice() {
		return ordersTotalPrice;
	}
	public void setOrdersTotalPrice(int[] ordersTotalPrice) {
		this.ordersTotalPrice = ordersTotalPrice;
	}

	public int[] getCart() {
		return cart;
	}
	public void setCart(int[] cart) {
		this.cart = cart;
	}
	public int getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}
	public ItemRequest getItemRequest() {
		return itemRequest;
	}
	public void setItemRequest(ItemRequest itemRequest) {
		this.itemRequest = itemRequest;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getOrdersGroup() {
		return ordersGroup;
	}
	public void setOrdersGroup(int ordersGroup) {
		this.ordersGroup = ordersGroup;
	}
	public String getOrdersDeliveryConfirm() {
		return ordersDeliveryConfirm;
	}
	public void setOrdersDeliveryConfirm(String ordersDeliveryConfirm) {
		this.ordersDeliveryConfirm = ordersDeliveryConfirm;
	}
	public Timestamp getOrdersDeliveryDate() {
		return ordersDeliveryDate;
	}
	public void setOrdersDeliveryDate(Timestamp ordersDeliveryDate) {
		this.ordersDeliveryDate = ordersDeliveryDate;
	}
	public int[] getOrdersCount() {
		return ordersCount;
	}
	public void setOrdersCount(int[] ordersCount) {
		this.ordersCount = ordersCount;
	}
	public Timestamp getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(Timestamp ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getOrdersRecipientName() {
		return ordersRecipientName;
	}
	public void setOrdersRecipientName(String ordersRecipientName) {
		this.ordersRecipientName = ordersRecipientName;
	}
	public String getOrdersRecipientPhone() {
		return ordersRecipientPhone;
	}
	public void setOrdersRecipientPhone(String ordersRecipientPhone) {
		this.ordersRecipientPhone = ordersRecipientPhone;
	}
	public int getOrdersRecipientPost() {
		return ordersRecipientPost;
	}
	public void setOrdersRecipientPost(int ordersRecipientPost) {
		this.ordersRecipientPost = ordersRecipientPost;
	}
	public String getOrdersRecipientParceladdr() {
		return ordersRecipientParceladdr;
	}
	public void setOrdersRecipientParceladdr(String ordersRecipientParceladdr) {
		this.ordersRecipientParceladdr = ordersRecipientParceladdr;
	}
	public String getOrdersRecipientRoadAddr() {
		return ordersRecipientRoadAddr;
	}
	public void setOrdersRecipientRoadAddr(String ordersRecipientRoadAddr) {
		this.ordersRecipientRoadAddr = ordersRecipientRoadAddr;
	}
	public String getOrdersConfirm() {
		return ordersConfirm;
	}
	public void setOrdersConfirm(String ordersConfirm) {
		this.ordersConfirm = ordersConfirm;
	}
	public Timestamp getOrdersConfirmDate() {
		return ordersConfirmDate;
	}
	public void setOrdersConfirmDate(Timestamp ordersConfirmDate) {
		this.ordersConfirmDate = ordersConfirmDate;
	}
	public String getOrdersRefund() {
		return ordersRefund;
	}
	public void setOrdersRefund(String ordersRefund) {
		this.ordersRefund = ordersRefund;
	}
	public String getOrdersPaymentMethod() {
		return ordersPaymentMethod;
	}
	public void setOrdersPaymentMethod(String ordersPaymentMethod) {
		this.ordersPaymentMethod = ordersPaymentMethod;
	}
	
	
}
