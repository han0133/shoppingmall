package org.ksmart02.fruitmall.seller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.seller.model.Seller;
import org.ksmart02.fruitmall.seller.model.SellerDao;
import org.ksmart02.fruitmall.seller.model.SellerList;
import org.ksmart02.fruitmall.util.PageHelper;

public class SellerService {
	private SellerDao sellerDao;
	

	public int modifySeller(Seller seller) {
		sellerDao = new SellerDao();
		int result = 0;
		try {
			result = sellerDao.updateSeller(seller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	} 
	
	/**
	 * 판매자의 1명정보를 가져오는 서비스 메서드
	 * 매개변수로 sellserNo을 받아서 selectSeller에 매개변수로 주고
	 * 리턴 값을 seller 변수에저장하고 리턴 합니다

	 * try catch로 예외처리를 해줍니다
	 * 
	 * @param sellerNo
	 * @return
	 */
	public Seller selectSeller(int sellerNo){
		sellerDao = new SellerDao();
		Seller seller = null;
		try {
			seller = sellerDao.selectSeller(sellerNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
	}
	
	/**
	 * 판매자를 등록하는 서비스 메서드
	 * 매개변수로 받은 seller를 insertSeller메서드에 매개변수로 주고
	 * try catch로 예외 처리를 해줍니다.
	 * @param seller
	 * @return
	 */
	public int addSeller(Seller seller){
		
		sellerDao = new SellerDao();
		int result = 0;
		try {
			result = sellerDao.insertSeller(seller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 판매자 리스트를 불러오는 메서드
	 * 매개변수로 받는(페이징 미구현 종무형 파이팅!)int 값으로 페이징 값 만들고 
	 * sellerDao의 selectSellerList 호출합니다
	 * try catch로 예외처리를 해줍니다.
	 */
	public Map<String, Object> getSellerList(PageHelper pageHelper) {
		Map<String, Object> map = new HashMap<String, Object>();
		sellerDao = new SellerDao();
		List<SellerList> list = null;
		PageHelper rePageHelper = null;
		try {
			pageHelper.setTotalList(sellerDao.selectSellerListTotal());
			rePageHelper = new PageHelper(pageHelper);
			list = sellerDao.selectSellerList(pageHelper);
			for(int i = 0; i < list.size(); i++){
				list.get(i).setQuantity(sellerDao.selectSellerItemCount(list.get(i).getSellerNo()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("pageHelper", rePageHelper);
		return map;
		
	}

	
	public Map<String, Object> sellerItemList(int sellerNo,PageHelper pageHelper) {
		sellerDao = new SellerDao();
		
		
		List<Item> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pageHelper.setTotalList(sellerDao.selectSellerItemListCount(sellerNo));
			PageHelper rePageHelper = new PageHelper(pageHelper);
			list = sellerDao.selectSellerItemList(sellerNo,rePageHelper);
			map.put("list", list);
			map.put("pageHelper", rePageHelper);
			map.put("sellerNo", sellerNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
