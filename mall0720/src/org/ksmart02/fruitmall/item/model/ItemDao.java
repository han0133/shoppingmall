package org.ksmart02.fruitmall.item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.ksmart02.fruitmall.util.ConnectionPool;
import org.ksmart02.fruitmall.util.PageHelper;

public class ItemDao {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	Item item;
	
	//0717 총 아이템숫자구하는 메서드(최초 0717박종무)
		public int countList() {
			System.out.println("itemDao의 countList실행");
			int totalList = 0;
			try {
				System.out.println("itemDao.countList");
				sql= "select count(*) from item";
				conn 		= ConnectionPool.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					totalList = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
					pstmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return totalList;
		}
	
	public void updateItem(Item item) throws Exception{
		conn 		= ConnectionPool.getConnection();
		String 	sql = "UPDATE item"
				+ " SET seller_no=?, item_image=?, item_name=?, item_category=?, item_detail=?, item_price=?,"
				+ " item_origin=?, item_quantity=?, item_date=NOW(), item_harvest=?, item_stock=?"
				+ " WHERE item_no = ? AND item_out= ?"; 
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setInt		(1, item.getSellerNo());
		pstmt.setString		(2, item.getItemImage());
		pstmt.setString		(3, item.getItemName());
		pstmt.setString		(4, item.getItemCategory());
		pstmt.setString		(5, item.getItemDetail());
		pstmt.setInt		(6, item.getItemPrice());
		pstmt.setString		(7, item.getItemOrigin());
		pstmt.setInt		(8, item.getItemQuantity());
		pstmt.setString		(9, item.getItemHarvest());
		pstmt.setInt		(10, item.getItemStock());
		pstmt.setInt		(11, item.getItemNo());
		pstmt.setString		(12, item.getItemOut());
		
		int result = pstmt.executeUpdate();
	//	System.out.println(result+"실행됫다잉");
		pstmt.close();
		conn.close();
	}
	
	public int insertItem(Item item) throws Exception{
		conn 		= ConnectionPool.getConnection();
		String 	sql = "INSERT INTO item"
				+ "(seller_no, item_image, item_name, item_category, item_detail,item_price, item_origin, item_quantity, item_date, item_harvest, item_stock) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?)"; 
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setInt		(1, item.getSellerNo());
		pstmt.setString		(2, item.getItemImage());
		pstmt.setString		(3, item.getItemName());
		pstmt.setString		(4, item.getItemCategory());
		pstmt.setString		(5, item.getItemDetail());
		pstmt.setInt		(6, item.getItemPrice());
		pstmt.setString		(7, item.getItemOrigin());
		pstmt.setInt		(8, item.getItemQuantity());
		pstmt.setString		(9, item.getItemHarvest());
		pstmt.setInt		(10, item.getItemStock());
		
		int result = pstmt.executeUpdate();
	//	System.out.println(result+"실행됫다잉");
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
//상품목록을 보여주는 쿼리(최초 이한녕)//카테고리 선택과 검색을 합침  +페이징추가(0717박종무)
/*	public ArrayList<Item> selectItemAll(String categoryKeyWord,String searchKeyWord, PageHelper pageHelper) throws Exception{*/
	public ArrayList<Item> selectItemAll(String categoryKeyWord,String searchKeyWord) throws Exception{
		System.out.println("ItemDao의 selectItemAll실행");
		
		ArrayList<Item> itemList = new ArrayList<Item>();
	
		conn 		= ConnectionPool.getConnection();
		/*String 	sql = "SELECT item_no, item_name, item_image, item_price, item_origin,  item_stock FROM item WHERE item_category LIKE ? AND item_name LIKE  ? ORDER BY item_no DESC limit ?, ?";*/ 
		String 	sql = "SELECT item_no, item_name, item_image, item_price, item_origin,  item_stock FROM item WHERE item_category LIKE ? AND item_name LIKE  ? ORDER BY item_no DESC"; 
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setString(1, "%"+categoryKeyWord+"%");
		pstmt.setString(2, "%"+searchKeyWord+"%");
		/*pstmt.setInt(3, pageHelper.getListOne());
		pstmt.setInt(4, pageHelper.getLimitList());*/
		
		rs 			= pstmt.executeQuery();
		
		while(rs.next()){
			item = new Item();
			
			item.setItemNo		(rs.getInt("item_no"));
			item.setItemName	(rs.getString("item_name"));
			item.setItemImage	(rs.getString("item_image"));
			item.setItemPrice	(rs.getInt("item_price"));
			item.setItemOrigin	(rs.getString("item_origin"));
			
			itemList.add		(item);
		}
		pstmt.close();
		conn.close();
		rs.close();
		
		return itemList;
	}

	//상품 한 개의 상세를 보여주는 메서드(최초 박종무)
	public Item selectItemByItemNo(int itemNo) throws Exception{
		conn 	= ConnectionPool.getConnection();
		sql	 	= "SELECT * FROM item WHERE item_no=?";
		pstmt 	= (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setInt(1, itemNo);
		rs		= pstmt.executeQuery();
		while(rs.next()){
			item = new Item();
			item.setItemNo		(rs.getInt("item_no"));
			item.setItemName	(rs.getString("item_name"));
			item.setItemImage	(rs.getString("item_Image"));
			item.setItemPrice	(rs.getInt("item_price"));
			item.setItemOrigin	(rs.getString("item_origin"));
			item.setItemQuantity(rs.getInt("item_quantity"));
			item.setSellerNo	(rs.getInt("seller_no"));
			item.setItemDate	(rs.getTimestamp("item_date"));
			item.setItemHarvest	(rs.getString("item_harvest"));
			item.setItemStock	(rs.getInt("item_stock"));
			
		//	System.out.println	(item);
		}
		pstmt.close();
		conn.close();
		rs.close();
		return item;
		 
	 }
	
	public void updateItemByItemOut(Item item) throws Exception {
		conn 		= ConnectionPool.getConnection();
		String 	sql = "UPDATE item SET item_out = ? WHERE item_no = ?"; 
		pstmt 		= conn.prepareStatement(sql);
	//	System.out.println(item);
		
		pstmt.setString	(1, item.getItemOut());
		pstmt.setInt	(2, item.getItemNo());
	
		
		int result = pstmt.executeUpdate();
		System.out.println(result+"실행됫다잉여기냐?");
		pstmt.close();
		conn.close();
	}

}
