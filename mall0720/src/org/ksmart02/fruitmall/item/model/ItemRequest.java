package org.ksmart02.fruitmall.item.model;

import java.util.Arrays;

public class ItemRequest {

	private int[] itemNo;
	private String[] itemName;
	private String[] itemCategory;
	private String[] itemImage;
	private String[] itemPrice;
	private String[] itemOrigin;
	private String[] itemQuantity;
	private String[] itemSeller;
	private String[] itemDate;
	private String[] itemHarvest;
	private String[] itemStock;
	public int[] getItemNo() {
		return itemNo;
	}
	public void setItemNo(int[] itemNo) {
		this.itemNo = itemNo;
	}
	public String[] getItemName() {
		return itemName;
	}
	public void setItemName(String[] itemName) {
		this.itemName = itemName;
	}
	public String[] getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String[] itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String[] getItemImage() {
		return itemImage;
	}
	public void setItemImage(String[] itemImage) {
		this.itemImage = itemImage;
	}
	public String[] getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String[] itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String[] getItemOrigin() {
		return itemOrigin;
	}
	public void setItemOrigin(String[] itemOrigin) {
		this.itemOrigin = itemOrigin;
	}
	public String[] getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String[] itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String[] getItemSeller() {
		return itemSeller;
	}
	public void setItemSeller(String[] itemSeller) {
		this.itemSeller = itemSeller;
	}
	public String[] getItemDate() {
		return itemDate;
	}
	public void setItemDate(String[] itemDate) {
		this.itemDate = itemDate;
	}
	public String[] getItemHarvest() {
		return itemHarvest;
	}
	public void setItemHarvest(String[] itemHarvest) {
		this.itemHarvest = itemHarvest;
	}
	public String[] getItemStock() {
		return itemStock;
	}
	public void setItemStock(String[] itemStock) {
		this.itemStock = itemStock;
	}
	@Override
	public String toString() {
		return "ItemRequest [itemNo=" + Arrays.toString(itemNo) + ", itemName=" + Arrays.toString(itemName)
				+ ", itemCategory=" + Arrays.toString(itemCategory) + ", itemImage=" + Arrays.toString(itemImage)
				+ ", itemPrice=" + Arrays.toString(itemPrice) + ", itemOrigin=" + Arrays.toString(itemOrigin)
				+ ", itemQuantity=" + Arrays.toString(itemQuantity) + ", itemSeller=" + Arrays.toString(itemSeller)
				+ ", itemDate=" + Arrays.toString(itemDate) + ", itemHarvest=" + Arrays.toString(itemHarvest)
				+ ", itemStock=" + Arrays.toString(itemStock) + "]";
	}
	
	
}
