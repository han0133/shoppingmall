package org.ksmart02.fruitmall.member.service;

import org.ksmart02.fruitmall.member.model.Member;
import org.ksmart02.fruitmall.member.model.MemberDao;
import org.ksmart02.fruitmall.member.model.MemberRequest;


public class MemberService {
	private MemberDao memberDao;
	/**
	 * 회원가입 서비스
	 * @param member
	 */
	public void joinMember(Member member){
		memberDao = new MemberDao();
		try {
			memberDao.insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 회원 탈퇴 서비스
	 * 입력받은 id와 pw를 memeber 변수에담고 memberDao의 dropOutUpdateMember 매개변수로 줍니다.
	 * 결과값을 result에 담고 성공 실패 유무를 조건문을 걸어서 console화면에 출력하고 result를 리턴합니다.
	 * @param memberId
	 * @param memberPw
	 */
	public int dropOutMember(String memberId, String memberPw) {
		int result = 0;
		if(memberPw.length() > 3){
			
			memberDao = new MemberDao();
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			try {
				result = memberDao.dropOutUpdateMember(member);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(result != 0){
			System.out.println("삭제 성공");
		}else{
			System.out.println("삭제 실패");
		}
		return result;
	}
	public MemberRequest modifyForSelectMember(String memberId) {
		MemberRequest memberRequest = new MemberRequest();
		MemberDao memberDao = new MemberDao();
		
		//member수정을 위한 현재정보 select해오는 메서드
		try {
			memberRequest = memberDao.selectMemberInfoById(memberId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberRequest;
		
	}
	public void modifyMember(Member member){
		memberDao=new MemberDao();
		
		try {
			memberDao.updateMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
