package org.ksmart02.fruitmall.member.model;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPost;
	private String memberParcleAddr;
	private String memberRoadAddr;
	private String memberPhone;
	private String memberOut;
	private String memberLevel;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPost() {
		return memberPost;
	}
	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}
	public String getMemberParcleAddr() {
		return memberParcleAddr;
	}
	public void setMemberParcleAddr(String memberParcleAddr) {
		this.memberParcleAddr = memberParcleAddr;
	}
	public String getMemberRoadAddr() {
		return memberRoadAddr;
	}
	public void setMemberRoadAddr(String memberRoadAddr) {
		this.memberRoadAddr = memberRoadAddr;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberOut() {
		return memberOut;
	}
	public void setMemberOut(String memberOut) {
		this.memberOut = memberOut;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberPost=" + memberPost + ", memberParcleAddr=" + memberParcleAddr + ", memberRoadAddr="
				+ memberRoadAddr + ", memberPhone=" + memberPhone + ", memberOut=" + memberOut + "]";
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	

}
