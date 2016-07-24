package org.ksmart02.fruitmall.notice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.util.ConnectionPool;
import org.ksmart02.fruitmall.util.PageHelper;

public class NoticeDao {
	Connection conn;
	String sql;
	PreparedStatement pstmt;
	ResultSet rs;
	Notice notice;
	Member member;
	
	//占썩뫀踰듿뜝�룞�삕亦낆꼻�삕占쎌젷 嶺뚮∥�뾼�땻�슪�삕獄�占�
	public void delNoticeDetail(int noticeNo) throws Exception{
		System.out.println("NoticeDao의 delNoticeDetail호출");
		
		sql = "delete from notice where notice_no=?";
		conn = ConnectionPool.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, noticeNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	//占썩뫀踰듿뜝�룞�삕占쎈빢�뜝�럩�젧 嶺뚮∥�뾼�땻�슪�삕獄�占�
	public void modityNotice(Notice notice) throws Exception{
		System.out.println("NoticeDao의 modityNotice호출");
		
		sql= "UPDATE notice SET notice_title= ?, notice_content=? where notice_no=?";
		conn = ConnectionPool.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, notice.getNoticeTitle());
		pstmt.setString(2, notice.getNoticeContent());
		pstmt.setInt(3, notice.getNoticeNo());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	//占썩뫀踰듿뜝占� �뜝�럩肉��뜝�럩�졑 嶺뚮∥�뾼�땻�슪�삕獄�占�
	public void addNotice(Notice notice) throws Exception {
		System.out.println("NoticeDao의 addNotice호출");

		sql = "insert into notice(notice_title, member_id, notice_content, notice_date) values (?,?,?,now())"; 
		conn		= ConnectionPool.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,notice.getNoticeTitle());
		pstmt.setString(2, notice.getMember().getMemberId());
		pstmt.setString(3,notice.getNoticeContent());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	//TotalCount 구하는 메서드
	public int countList() throws Exception{
		System.out.println("NoticeDao의 countList호출");
		
		int totalList = 0;
		sql			= "select count(*) from notice";
		conn 		= ConnectionPool.getConnection();
		pstmt		= conn.prepareStatement(sql);
		rs 			= pstmt.executeQuery();
		if(rs.next()){
			System.out.println(rs.getInt(1));
			totalList = rs.getInt(1);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return totalList;
	}
	
	//�뜝�럥�냺�뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 嶺뚮∥�뾼�땻�슪�삕獄�占�
	public ArrayList<Notice> listNotice(PageHelper pageHelper) throws Exception{
		System.out.println("NoticeDao의 countList호출");
		
		ArrayList<Notice> list = new ArrayList<Notice>();
		sql = "select notice_no, notice_title, member_id, notice_content, notice_date "
				+ "from notice order by notice_no asc "
				+ "limit ?, ?";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		pstmt.setInt(1, pageHelper.getListOne());
		pstmt.setInt(2, pageHelper.getLimitList());
		rs			= pstmt.executeQuery();
		
		while(rs.next()){
			notice 	= new Notice();
			member	= new Member();
			notice.setNoticeNo(rs.getInt("notice_no"));
			notice.setNoticeTitle(rs.getString("notice_title"));
			Timestamp timestamp= rs.getTimestamp("notice_date");
			notice.setNoticeDate(timestamp);
			member.setMemberId(rs.getString("member_id"));
			notice.setMember(member);
			list.add(notice);
		}
	
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
				
	}
		
	public Notice selecetNoticeDetail(int noticeNo) throws Exception{
		System.out.println("NoticeDao의 selecetNoticeDetail호출");
		
		notice		= new Notice(); 
		member		= new Member();
		sql 		= "select notice_no, member_id, notice_title, notice_content, notice_date from notice where notice_no=?";
		conn 		= ConnectionPool.getConnection();
		pstmt 		= conn.prepareStatement(sql);
		pstmt.setInt(1, noticeNo);
		rs 			= pstmt.executeQuery();
		
		if(rs.next()){
			notice.setNoticeNo(rs.getInt("notice_no"));
			member.setMemberId(rs.getString("member_id"));
			notice.setMember(member);
			notice.setNoticeTitle(rs.getString("notice_title"));
			notice.setNoticeContent(rs.getString("notice_content"));
			notice.setNoticeDate(rs.getTimestamp("notice_date"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return notice;
	}
}
