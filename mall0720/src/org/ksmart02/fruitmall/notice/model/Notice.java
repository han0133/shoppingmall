package org.ksmart02.fruitmall.notice.model;


import java.sql.Timestamp;

import org.ksmart02.fruitmall.member.model.Member;
/* 蹂��닔 �꽕紐�
 * noticeNo            怨듭� 踰덊샇
 * memberId            寃뚯떆�옄 �븘�씠�뵒
 * noticeContent       怨듭� �궡�슜
 * notice_date         怨듭�(寃뚯떆) �궇吏�
 * notice_title        怨듭� �젣紐�  
 */
public class Notice {
	private int noticeNo;
	private String noticeContent;
	private Timestamp noticeDate;
	private String noticeTitle; 
	private Member member;
	
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Timestamp getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Timestamp noticeDate) {
		this.noticeDate = noticeDate;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate
				+ ", noticeTitle=" + noticeTitle + ", member=" + member + "]";
	}

	
}
