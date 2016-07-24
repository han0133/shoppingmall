package org.ksmart02.fruitmall.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ksmart02.fruitmall.notice.model.Notice;
import org.ksmart02.fruitmall.notice.model.NoticeDao;
import org.ksmart02.fruitmall.util.PageHelper;

/**
 * 공지사항 Service
 * @author 박종무
 *
 */

public class NoticeService {

	NoticeDao noticeDao;
	Notice notice;
	ArrayList<Notice> list;
	
	//addForm���� ���� ������ ������ Dao�� ȣ���ؼ� ���������� ����ϴ� �޼��� 
	public void insertNotice(Notice notice) throws Exception{
		System.out.println("NoticeService의 insertNotice 호출");
		
		noticeDao = new NoticeDao();
		noticeDao.addNotice(notice);
	}
	
	// 주석 추가해주세요
	public Map<String,Object> selectNotice(PageHelper pageHelper) throws Exception{
		System.out.println("NoticeService의 selectNotice호출");
		
		Map<String,Object> map = new HashMap<String,Object>();
		noticeDao = new NoticeDao();
		pageHelper.setTotalList(noticeDao.countList());
		PageHelper repageHelper = new PageHelper(pageHelper);
		list = new ArrayList<Notice>();
		
	//	System.out.println("listService시작");	 
		list = noticeDao.listNotice(repageHelper);

		map.put("list",list);
		map.put("pageHelper",repageHelper);

		
		return map;
		
	}
	
	
	// 주석 추가해주세요
	public void delNoticeDetail(int noticeNo) throws Exception{
		System.out.println("NoticeService의 delNoticeDetail호출");
		
		NoticeDao noticeDao = new NoticeDao();
		noticeDao.delNoticeDetail(noticeNo);
		
	}
	
	// 주석 추가해주세요
	public Notice selectNoticeDetail(int noticeNo) throws Exception{
		System.out.println("NoticeService의 selectNoticeDetail호출");

		NoticeDao noticeDao = new NoticeDao();
		Notice notice = new Notice();
		notice = noticeDao.selecetNoticeDetail(noticeNo);
		
		return notice;
		
	}
	
	// 주석 추가해주세요
	public void updateNoticeDetail(Notice notice) throws Exception{
		System.out.println("NoticeService의 updateNoticeDetail호출");
		
		NoticeDao noticeDao = new NoticeDao();
		noticeDao.modityNotice(notice);
	}
}

