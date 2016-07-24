package org.ksmart02.fruitmall.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.ksmart02.fruitmall.util.ConnectionPool;

public class MemberDao {
	private PreparedStatement pstmt;
	private ResultSet rs;

	//기존 정보를 주문 form으로 불러오는 메서드입니다 0716안소영
	public Member selectMember(String memberId) throws Exception{
		System.out.println("memberDao의 selectMember 메서드 호출..");
	//	System.out.println("query의 memberId: "+memberId);
		Connection conn = ConnectionPool.getConnection();
		String sql		= "select member_name, member_post, member_parcle_addr, member_road_addr, member_phone "
				+ "from member where member_id = ?";
		pstmt			= conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		rs				= pstmt.executeQuery();
		
		Member member	= null;
		while(rs.next()){
		//	System.out.println("while문 돌아감");
			member	= new Member();
			member.setMemberName(rs.getString(1));
			member.setMemberPost(rs.getString(2));
			member.setMemberParcleAddr(rs.getString(3));
			member.setMemberRoadAddr(rs.getString(4));
			member.setMemberPhone(rs.getString(5));
		//	System.out.println("member: "+member);
		}
		
	//	System.out.println("member2: "+member);
		rs.close();
		pstmt.close();
		conn.close();
		
		return member;
	}
	
	//비밀번호를 찾는 메서드입니다 0713 안소영
	public String findPw(Member member) throws Exception{
		System.out.println("MemberDao의 findPw 메서드 호출..");
		String pw = null;
		
		Connection conn = ConnectionPool.getConnection();
		String sql	= "select member_pw from member where member_id = ? and member_phone = ?";
		pstmt		=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPhone());
		rs			= pstmt.executeQuery();
		while(rs.next()){
			pw		= rs.getString(1);
		}
		
		return pw;
	}
	
	//아이디를 찾는 메서드입니다 0713 안소영
	public String findId(Member member) throws Exception{
		System.out.println("MemberDao의 findId 메서드 호출..");
		String id = null;
		
		Connection conn = ConnectionPool.getConnection();
		String sql	= "select member_id from member where member_name = ? and member_phone = ?";
		pstmt		=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberName());
		pstmt.setString(2, member.getMemberPhone());
		rs			= pstmt.executeQuery();
		
		if(!rs.next()){
			id		= "fail";
		}else{
			do{
				id		= rs.getString(1);
			}while(rs.next());
		}		
		return id;
	}
	
	//아이디 중복검사 메서드입니다
	public String IdCheck(Member member) throws Exception{
		System.out.println("MemberDao의 IdCheck메서드 호출..");
		String re = "";
		ResultSet rs = null;
		Connection conn = ConnectionPool.getConnection();
		
		String sql = "SELECT member_id FROM member Where member_id = ?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			re = "unavailable ID";
		}else{
			re = "available ID";
		}
		pstmt.close();
		conn.close();
		return re;
	}
		
		
	//로그인 메서드입니다
	public Map<String, Object> selectMemberByIdandPw(String id, String pw) throws Exception{
		System.out.println("MemberDao�쓽 selectMemberByIdandPw �떎�뻾");
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn 		= ConnectionPool.getConnection();
		String sql 				= "select member_id, member_level from member where member_id = ? and member_pw = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		ResultSet rs 			= pstmt.executeQuery();
		
		String result			= "";
		if(rs.next()){
			result = "success";
			map.put("result", result);
			map.put("memberLevel", rs.getString("member_level"));
			System.out.println("1.result : "+result);
			System.out.println("1.memberLevel : "+rs.getString("member_level"));
		}
		
		return map;
	}
	
	//회원가입 메서드입니다
	public void insertMember(Member member) throws Exception{
		System.out.println("MemberDao의 insertMember 실행..");
		Connection conn = ConnectionPool.getConnection();					
			String sql = "INSERT INTO member(member_id, member_pw, member_name, member_post, member_parcle_addr, member_road_addr, member_phone) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPost());
			pstmt.setString(5, member.getMemberParcleAddr());
			pstmt.setString(6, member.getMemberRoadAddr());
			pstmt.setString(7, member.getMemberPhone());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		
	}
	
	//나의 정보를 불러오는 메서드입니다
	//나의 정보 보여주기와 나의 정보 수정에 사용됩니다
	public MemberRequest selectMemberInfoById(String memberId) throws Exception{
		MemberRequest memberRequest = new MemberRequest();
		System.out.println("MemberDao의 insertMember 실행..");
		Connection conn = null;
		
		conn = ConnectionPool.getConnection();
		String sql = "SELECT member_pw, member_name, member_post, member_parcle_addr, member_road_addr, member_phone FROM member where member_id= ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		rs=pstmt.executeQuery();
		while(rs.next()){
			memberRequest.setMemberPw(rs.getString("member_pw"));
			memberRequest.setMemberName(rs.getString("member_name"));
			memberRequest.setMemberPost(rs.getString("member_post"));
			memberRequest.setMemberParcleAddr(rs.getString("member_parcle_addr"));
			memberRequest.setMemberRoadAddr(rs.getString("member_road_addr"));
			memberRequest.setMemberPhone(rs.getString("member_phone"));
			memberRequest.setMemberId(memberId);
		}

		rs.close();
		pstmt.close();
		conn.close();
				
		return memberRequest;	
	} 
	
	//나의 정보를 수정하는 메서드입니다
	public void updateMember(Member member) throws Exception{
		System.out.println("updateMember");
		Connection conn = null;
		
			conn 		= ConnectionPool.getConnection();
			String sql	= "UPDATE member SET member_pw=?, member_name=?, member_post=?, member_parcle_addr=?, member_road_addr=?, member_phone=? WHERE member_id = ?";
			pstmt 		= conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberPost());
			pstmt.setString(4, member.getMemberParcleAddr());
			pstmt.setString(5, member.getMemberRoadAddr());
			pstmt.setString(6, member.getMemberPhone());
			//종무씨 이거 뭐예요 ?? 왜 이렇게 했어요???
			String memberId = (String)member.getMemberId();
			pstmt.setString(7, memberId);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

	}
		
	/**
	 * 회원 탈퇴 하는 메서드입니다
	 * 매개변수로 받은 member로 UPDATE 쿼리문장 완성 시킵니다.
	 * 쿼리 실행 결과를 result 변수에담고 리턴합니다.
	 * @param member
	 * @return
	 * @throws Exception
	 * @author 이한녕
	 */
	public int dropOutUpdateMember(Member member) throws Exception{
		//회원탈퇴는 delete가아닌 update를 통해서 컬럼값을 바꾼다
		System.out.println("MemberDao의 dropOutUpdateMember 실행..");
		
		Connection conn = ConnectionPool.getConnection();					
		String sql 		= "UPDATE member SET member_out='Y' WHERE member_id = ? AND member_pw = ?";
		pstmt 			= conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		
		int result 		= pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();

		return result;
	}
}
