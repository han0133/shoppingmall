package org.ksmart02.fruitmall.comment.model;

import java.sql.Date;

public class Comment {
	/*
	 * 변수 설명
	 * commentNo:         댓글 번호
	 * itemNo:            상품 번호
	 * memberId:          사용자 id
	 * commentContent:    댓글 내용
	 * commentRate        댓글에 대한 평가(별점)
	 * commentDate        댓글 입력 날짜
	 * commentStar        댓글 평가에 대한 별표시
	 */
	
	private int commentNo;
	private int itemNo;
	private String memberId;
	private String commentContent;
	private int commentRate;	//1~5점
	private Date commentDate;
	private String commentStar;	//별
	private String commentTitle;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getCommentRate() {
		return commentRate;
	}
	public void setCommentRate(int commentRate) {
		this.commentRate = commentRate;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentStar() {
		return commentStar;
	}
	public void setCommentStar(String commentStar) {
		this.commentStar = commentStar;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", itemNo=" + itemNo + ", memberId=" + memberId + ", commentContent="
				+ commentContent + ", commentRate=" + commentRate + ", commentDate=" + commentDate + ", commentStar="
				+ commentStar + ", commentTitle=" + commentTitle + "]";
	}	
	
	
	
}
