package org.ksmart02.fruitmall.item.model;

import java.sql.Timestamp;


public class Item {
	/*변수 설명
	 * itemNo            상품 번호
	 * sellerNo			  판매자 번호
	 * itemName          상품 이름   
	 * itemCategory      상품 상위 카테고리
	 * itemImage         상품 이미지
	 * itemDetail		  상품 정보   
	 * itemPrice         상품 가격
	 * itemOrigin        상품 원산지
	 * itemQuantity      상품 수량
	 * itemSeller        상품 판매자
	 * itemDate          상품 등록일 
	 * itemHarvest       상품 수확시기
	 * itemStock         상품 재고
	 * itemOut			  상품 판매상태 여부
	 */
	
	private int itemNo;
	private int sellerNo;
	private String itemName;
	private String itemCategory;
	private String itemDetail;
	private String itemImage;
	private int itemPrice;
	private String itemOrigin;
	private int itemQuantity;
	private Timestamp itemDate;
	private String itemHarvest;
	private int itemStock;
	private String itemOut;
	
	public String getItemOut() {
		return itemOut;
	}
	public void setItemOut(String itemOut) {
		this.itemOut = itemOut;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(int sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemOrigin() {
		return itemOrigin;
	}
	public void setItemOrigin(String itemOrigin) {
		this.itemOrigin = itemOrigin;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public Timestamp getItemDate() {
		return itemDate;
	}
	public void setItemDate(Timestamp itemDate) {
		this.itemDate = itemDate;
	}
	public String getItemHarvest() {
		return itemHarvest;
	}
	public void setItemHarvest(String itemHarvest) {
		this.itemHarvest = itemHarvest;
	}
	public int getItemStock() {
		return itemStock;
	}
	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}
	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", sellerNo=" + sellerNo + ", itemName=" + itemName + ", itemCategory="
				+ itemCategory + ", itemDetail=" + itemDetail + ", itemImage=" + itemImage + ", itemPrice=" + itemPrice
				+ ", itemOrigin=" + itemOrigin + ", itemQuantity=" + itemQuantity + ", itemDate=" + itemDate
				+ ", itemHarvest=" + itemHarvest + ", itemStock=" + itemStock + ", itemOut=" + itemOut + "]";
	}
}
