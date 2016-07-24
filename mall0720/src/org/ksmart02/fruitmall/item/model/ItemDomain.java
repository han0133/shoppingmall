package org.ksmart02.fruitmall.item.model;

import java.sql.Date;


import org.ksmart02.fruitmall.seller.model.Seller;

public class ItemDomain {
	
	private Seller seller;
	private String itemName;
	private String itemCategory;
	private String itemImagePath;
	private int itemPrice;
	private String itemOrigin;
	private int itemQuantity;
	private Date itemHarvest;
	private int itemStock;

	
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
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
	
	public String getItemImagePath() {
		return itemImagePath;
	}
	public void setItemImagePath(String itemImagePath) {
		this.itemImagePath = itemImagePath;
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
	public Date getItemHarvest() {
		return itemHarvest;
	}
	public void setItemHarvest(Date itemHarvest) {
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
		return "ItemDomain [seller=" + seller + ", itemName=" + itemName + ", itemCategory=" + itemCategory
				+ ", itemImagePath=" + itemImagePath + ", itemPrice=" + itemPrice + ", itemOrigin=" + itemOrigin
				+ ", itemQuantity=" + itemQuantity + ", itemHarvest=" + itemHarvest + ", itemStock=" + itemStock + "]";
	}
}
