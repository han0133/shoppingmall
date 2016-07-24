package org.ksmart02.fruitmall.seller.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksmart02.fruitmall.item.controller.itemDetailController;
import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.util.ConnectionPool;
import org.ksmart02.fruitmall.util.PageHelper;

public class SellerDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * 한명의 판매자 정보를 수정하는 메서드
	 * 매개변수로 seller객체를 받아서
	 * sellerName,sellerAddr,sellerPhone로 
	 * UPDATE 쿼리 완성합니다.
	 * 쿼리 실행 후 int값을 리턴해 줍니다.
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public int updateSeller(Seller seller) throws Exception{
		String sql 	= "UPDATE seller SET seller_addr = ?, seller_phone = ? WHERE seller_no = ?";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setString(1, seller.getSellerAddr());
		pstmt.setString(2, seller.getSellerPhone());
		pstmt.setInt(3, seller.getSellerNo());
		
		int result 	= pstmt.executeUpdate();
		System.out.println(result);
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	/**
	 * 한명의 판매자 정보를 불러오는 메서드
	 * 매개변수로 sellerNo을 받아서
	 * sellerNo,sellerName,sellerAddr,sellerPhone을 
	 * seller 변수에 담아서 리턴합니다
	 * @param sellerNo
	 * @return
	 * @throws Exception
	 */
	public Seller selectSeller(int sellerNo) throws Exception{
		Seller seller = null;
		String sql 	= "SELECT seller_no,seller_name,seller_addr,seller_phone,seller_date FROM seller WHERE seller_no = ?";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setInt(1, sellerNo);
		
		rs 	= pstmt.executeQuery();
		if(rs.next()){
			seller = new Seller();
			seller.setSellerNo(rs.getInt("seller_no"));
			seller.setSellerName(rs.getString("seller_name"));
			seller.setSellerAddr(rs.getString("seller_addr"));
			seller.setSellerPhone(rs.getString("seller_phone"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return seller;
	}
	
	
	/**
	 * 판매자 등록하는 메서드
	 * 
	 * 매개변수로 seller를 받아서
	 * sellerName,sellerAddr,sellerPhone을 꺼내서 쿼리를 완성 시킨후 실행
	 * 실행결과를 int result에 담아서 리턴합니다.
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public int insertSeller(Seller seller) throws Exception{
		String sql 	= "INSERT INTO seller(seller_name,seller_addr,seller_phone,seller_date) VALUES(?,?,?,NOW())";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setString(1, seller.getSellerName());
		pstmt.setString(2, seller.getSellerAddr());
		pstmt.setString(3, seller.getSellerPhone());
		
		int result 	= pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	public List<SellerList> selectSellerList(PageHelper pageHelper) throws Exception {
		SellerList seller 		= null;
		List<SellerList> list 	= new ArrayList<SellerList>();
		String sql 	= "SELECT seller_no, seller_name, seller_addr, seller_phone, seller_date FROM seller "
				+ "ORDER BY seller_no DESC LIMIT ?,?";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		pstmt.setInt(1, pageHelper.getListOne());
		pstmt.setInt(2, pageHelper.getLimitList());
		
		rs 	= pstmt.executeQuery();
		
		while(rs.next()){
			seller = new SellerList();
			seller.setSellerNo(rs.getInt("seller_no"));
			seller.setSellerName(rs.getString("seller_name"));
			seller.setSellerAddr(rs.getString("seller_addr"));
			seller.setSellerPhone(rs.getString("seller_phone"));
			seller.setSellerDate(rs.getTimestamp("seller_date"));
			list.add(seller);
		}
		pstmt.close();
		conn.close();
		rs.close();
		
		return list;
	}
	public int selectSellerItemCount(int sellerNo) throws Exception{
		int result 	= 0;
		conn 		= ConnectionPool.getConnection();
		String 	sql = "SELECT count(*) FROM item WHERE seller_no = ?"; 
		pstmt 		= conn.prepareStatement(sql);
		
		pstmt.setInt(1, sellerNo);
		
		rs 			= pstmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);	
		}
		
		pstmt.close();
		conn.close();
		rs.close();
		
		return result;
	}

	/**
	 * 판매자가 판매하고있는 상품들의 리스트를 가져오는 메서드
	 * 매개변수로 sellerNo과 pageHelper를 받아서 
	 * 리스트 결과물을 추려내고 리턴해줍니다.
	 * @param sellerNo
	 * @return
	 * @throws Exception
	 */
	public List<Item> selectSellerItemList(int sellerNo,PageHelper pageHelper) throws Exception {
		List<Item> list = new ArrayList<Item>();
		conn 		= ConnectionPool.getConnection();
		String 	sql = "SELECT i.item_no, i.item_name, i.item_image, i.item_price, i.item_category, i.item_harvest, i.item_stock,s.seller_name "
				+ "FROM item i inner join seller s ON i.seller_no = s.seller_no WHERE s.seller_no = ? ORDER BY i.item_no DESC LIMIT ?,? "; 
		pstmt 		= conn.prepareStatement(sql);
		System.out.println(pageHelper);
		pstmt.setInt(1, sellerNo);
		pstmt.setInt(2, pageHelper.getListOne());
		pstmt.setInt(3, pageHelper.getLimitList());
		
		
		rs 			= pstmt.executeQuery();
		while(rs.next()){
			
			Item item = new Item();
			item.setItemNo(rs.getInt("i.item_no"));
			item.setItemName(rs.getString("i.item_name"));
			item.setItemImage(rs.getString("i.item_image"));
			item.setItemPrice(rs.getInt("i.item_price"));
			item.setItemCategory(rs.getString("i.item_category"));
			item.setItemHarvest(rs.getString("i.item_harvest"));
			item.setItemStock(rs.getInt("i.item_stock"));
			list.add(item);
		}
		
		pstmt.close();
		conn.close();
		rs.close();
		
		return list;
	}

	/**
	 * 판매자 총 리스트를 구하는 메서드
	 * 페이징에 사용 됩니다.
	 * @return
	 * @throws Exception 
	 */
	public int selectSellerItemListCount(int sellerNo) throws Exception {
		int result 	= 0;
		conn 		= ConnectionPool.getConnection();
		String 	sql = "SELECT count(*) FROM item WHERE seller_no = ?"; 
		pstmt 		= conn.prepareStatement(sql);
		pstmt.setInt(1, sellerNo);
		
		rs 			= pstmt.executeQuery();
		if(rs.next()){
			result 	= rs.getInt(1);
		}
		
		pstmt.close();
		conn.close();
		rs.close();
		return result;
	}
	
	public int selectSellerListTotal() throws Exception{
		int result 	= 0;
		conn 		= ConnectionPool.getConnection();
		String 	sql = "SELECT count(*) FROM seller"; 
		pstmt 		= conn.prepareStatement(sql);
		
		rs 			= pstmt.executeQuery();
		if(rs.next()){
			result 	= rs.getInt(1);
		}
		
		pstmt.close();
		conn.close();
		rs.close();
		return result;
	}

	public String sellerNoCheck(int sellerNo) throws Exception {
		System.out.println("MemberDao의 IdCheck메서드 호출..");
		String re = "";
		ResultSet rs = null;
		Connection conn = ConnectionPool.getConnection();
		
		String sql = "SELECT seller_no FROM seller Where seller_no = ?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, sellerNo);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			re = "unavailable sellerNO";
		}else{
			re = "available sellerNO";
		}
		pstmt.close();
		conn.close();
		return re;
	}
}
