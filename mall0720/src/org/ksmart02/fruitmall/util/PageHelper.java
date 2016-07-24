package org.ksmart02.fruitmall.util;

import java.util.ArrayList;
import java.util.List;

public class PageHelper {
	
	//페이지 변수
	private int nowPage 	= 1; 	//현재 페이지
	private int linkPage;	//아래 링크 페이지(nowPage/limitLink)+1
	private List<Integer> linkPages;	//링크 1~.....10 
	private int limitLink 	= 5; 	//링크 제한
	private int movePage 	= 1;	//이전 다음 페이지 담는 변수
	
	//리스트 변수
	private int listOne; 	//리스트에서 맨 첫번째글 
	private int limitList 	= 5; 	//리스트 제한
	
	//토탈변수
	private int totalPage; 	//총 페이지수 ((totalList-1)/limitList)+1
	private int totalList; 	//토탈 리스트 
	
	
	
	public PageHelper(){
		
	}
	
	public PageHelper(PageHelper pageHelper){//5,23,10,10,1
		this.totalList 	= pageHelper.getTotalList();
		this.nowPage 	= pageHelper.getNowPage();
		this.limitList 	= pageHelper.getLimitList();
		this.limitLink 	= pageHelper.getLimitLink();

		
		
		
		this.listOne 	= ((nowPage*limitList)-limitList);
		
		this.totalPage 	= ((totalList-1)/limitList)+1;//3

		
		this.linkPages 	= new ArrayList<Integer>();
		this.linkPage 	= ((nowPage/limitLink)*limitLink)+1;
		//링크의 첫 시작점을 구함.. 예 2/10*10+1 = 1  1,2,3....10 
		
		
		//현재페이지가 10으로 나누어 떨어지지않으면..
		if(nowPage%limitLink != 0){
			
			for(int i=0; i<limitLink; i++){
		
				this.linkPages.add(this.linkPage+i);
				//첫링크 부터 계속 add 해줌..
				//만약 마지막페이지와  링크에담긴 값이 같아지면 반복문을 끝내고 나옴.. 예 총페이지가 17  링크17 이면 반복문을 빠져나옴
				if(this.totalPage==this.linkPages.get(i)){
					break;
				}
			}
			this.movePage = ((nowPage/this.limitLink)*this.limitLink)+1;
		//현재페이지가 10으로 나누어 떨어지면..위와 약간 식이 다름
		}else{
			
			for(int i=0; i<limitLink; i++){
				
				this.linkPages.add(this.linkPage+i-limitLink);
				//10으로 나누어떨어지기떄문에 링크의 제한 만큼 빼준다.. 
				//10으로 나누어떨어질경우 위에서 linkPage 구하는식이 약간틀어지게되는데
				//예를 들어 10/10*10+1 을하게되면 11이 나와버린다. 하지만 10페이지에선 1을 보여주고싶었기에 limitLink를 뺏다.
				//결국엔 1이 되어서 식이 완성됨
				//위에 if와 마찬가지로 마지막 페이지와 같으면 반복문을 끝냄
				if(this.totalPage==this.linkPages.get(i)){
					break;
				}
			}
			this.movePage = nowPage-limitLink+1;
		}	

	}
	
	
	
	
	
	
	
	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getLinkPage() {
		return linkPage;
	}
	public void setLinkPage(int linkPage) {
		this.linkPage = linkPage;
	}
	public List<Integer> getLinkPages() {
		return linkPages;
	}
	public void setLinkPages(List<Integer> linkPages) {
		this.linkPages = linkPages;
	}
	public int getLimitLink() {
		return limitLink;
	}
	public void setLimitLink(int limitLink) {
		this.limitLink = limitLink;
	}
	public int getListOne() {
		return listOne;
	}
	public void setListOne(int listOne) {
		this.listOne = listOne;
	}
	public int getLimitList() {
		return limitList;
	}
	public void setLimitList(int limitList) {
		this.limitList = limitList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		this.totalList = totalList;
	}
	public int getMovePage() {
		return movePage;
	}
	public void setMovePage(int movePage) {
		this.movePage = movePage;
	}
}
