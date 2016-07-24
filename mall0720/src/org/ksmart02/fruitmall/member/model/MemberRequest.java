package org.ksmart02.fruitmall.member.model;

public class MemberRequest {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPost;
	private String memberParcleAddr;
	private String memberRoadAddr;
	private String memberPhone;
	private String memberPhone2;
	private String memberPhone3;
	private String memberOut;
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
	public String getMemberPhone2() {
		return memberPhone2;
	}
	public void setMemberPhone2(String memberPhone2) {
		this.memberPhone2 = memberPhone2;
	}
	public String getMemberPhone3() {
		return memberPhone3;
	}
	public void setMemberPhone3(String memberPhone3) {
		this.memberPhone3 = memberPhone3;
	}
	public String getMemberOut() {
		return memberOut;
	}
	public void setMemberOut(String memberOut) {
		this.memberOut = memberOut;
	}
	@Override
	public String toString() {
		return "MemberRequest [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberPost=" + memberPost + ", memberParcleAddr=" + memberParcleAddr + ", memberRoadAddr="
				+ memberRoadAddr + ", memberPhone=" + memberPhone + ", memberPhone2=" + memberPhone2 + ", memberPhone3="
				+ memberPhone3 + ", memberOut=" + memberOut + "]";
	}
	
}
