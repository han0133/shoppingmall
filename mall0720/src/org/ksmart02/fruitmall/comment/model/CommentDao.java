package org.ksmart02.fruitmall.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.ksmart02.fruitmall.item.model.Item;
import org.ksmart02.fruitmall.util.ConnectionPool;
import org.ksmart02.fruitmall.util.PageHelper;



public class CommentDao {
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	Item item;
	
	//totalCount 페이징을 위해서 comment의 총갯수를 구하는 메서드
	public int countComment(Map map) throws Exception{
		int totalComment = 0;
		Comment comment = (Comment)map.get("comment");
		int itemNo = comment.getItemNo();
		
			System.out.println("countComment");
			sql="select count(*) from comment where item_no = ?";
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("totalComment : "+rs.getInt(1));
				totalComment = rs.getInt(1);
			}
		
			rs.close();
			pstmt.close();
			conn.close();
		
		return totalComment;
	}
	
	//조건이 itemNo와memberId에 해당하는것만 출력
	//출력할게있으면 1 출력할게 없으면 0을 리턴
	//입력폼을 보여줄건지 말건지를 결정할것
	public int listCommentByItemNoAndMemberId(Comment comment) throws Exception{
		int result = 0;
		conn = ConnectionPool.getConnection();
		String sql="SELECT * FROM comment WHERE item_no=? AND member_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getItemNo());
		pstmt.setString(2, comment.getMemberId());
		rs = pstmt.executeQuery();
		while(rs.next()){
			result = 1;
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		
		
		return result;
	}
	
	//댓글수정처리 메서드
	public int updateComment(Comment comment) throws Exception{
		int result =0;
		conn = ConnectionPool.getConnection();
		String sql = "UPDATE comment SET comment_content=?,comment_date=NOW(),	comment_title=?,comment_star=?,comment_rate=?	WHERE comment_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment.getCommentContent());
		pstmt.setString(2, comment.getCommentTitle());
		pstmt.setString(3, comment.getCommentStar());
		pstmt.setInt(4, comment.getCommentRate());
		pstmt.setInt(5, comment.getCommentNo());
		result = pstmt.executeUpdate();
		
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	//댓글수정폼 메서드
	public Comment updateFormComment(Comment comment) throws Exception{
		Comment comment2 = null;
		
		conn = ConnectionPool.getConnection();
		String sql = "SELECT comment_no,item_no,member_id,comment_content,comment_date,comment_title,comment_star,comment_rate FROM comment WHERE comment_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getCommentNo());
		rs = pstmt.executeQuery();
		while(rs.next()){
			comment2 = new Comment();
			comment2.setCommentNo(rs.getInt("comment_no"));
			comment2.setItemNo(rs.getInt("item_no"));
			comment2.setCommentTitle(rs.getString("comment_title"));
			comment2.setCommentContent(rs.getString("comment_content"));
			comment2.setCommentDate(rs.getDate("comment_date"));
			comment2.setCommentRate(rs.getInt("comment_rate"));
			comment2.setCommentStar(rs.getString("comment_star"));
		
		}
			System.out.println("comment2 : " + comment2);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		return comment2;
	}
	
	

	
	//댓글삭제 메서드
	public int delComment(Comment comment) throws Exception{
		int result = 0;
		conn = ConnectionPool.getConnection();
		String sql = "DELETE FROM comment WHERE comment_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getCommentNo());
		result = pstmt.executeUpdate();
		
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	//펼점평균 메서드
	public int starAvg(Comment comment) throws Exception{
		double avg = 0;
		int avgStar = 0;
		conn = ConnectionPool.getConnection();
		String sql = "SELECT * FROM comment WHERE item_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getItemNo());
		
		rs = pstmt.executeQuery();
		int count = 0;
		int totalStarRate =0;
		while(rs.next()){
			count += 1;
			totalStarRate += rs.getInt("comment_rate");
		}
		avg = (double)totalStarRate/(double)count;
		avgStar = (int)Math.round(avg);
		System.out.println("avg : "+ avg);
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return avgStar;
		
	}
	
	//댓글 입력 메서드
	public int addComment(Comment comment) throws Exception{
		int result = 0;
		conn = ConnectionPool.getConnection();
		String sql = "INSERT INTO comment(item_no, member_id,comment_title, comment_content, comment_date,comment_rate,comment_star) VALUES (?,?,?,?,NOW(),?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getItemNo());
		pstmt.setString(2, comment.getMemberId());
		pstmt.setString(3, comment.getCommentTitle());
		pstmt.setString(4, comment.getCommentContent());
		pstmt.setInt(5, comment.getCommentRate());
		pstmt.setString(6, comment.getCommentStar());
		
		result = pstmt.executeUpdate();
		System.out.println("CommentDao result : "+ result);
		
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	//댓글 리스트 메서드
	public ArrayList<Comment> listComment(PageHelper pageHelper, Map map) throws Exception{
		ArrayList<Comment> listComment = new ArrayList<Comment>();
		//Comment comment;
		Comment comment = (Comment)map.get("comment");
		
		System.out.println("CommentDao comment : " + comment);
		conn = ConnectionPool.getConnection();
		String sql = "SELECT comment_no,item_no,member_id,comment_content,comment_date,comment_title,comment_rate, comment_star FROM comment where item_no = ? order by comment_no desc limit ? ,?  ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getItemNo());
		pstmt.setInt(2, pageHelper.getListOne());
		pstmt.setInt(3, pageHelper.getLimitList());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			comment = new Comment();
			comment.setCommentNo(rs.getInt("comment_no"));
			comment.setItemNo(rs.getInt("item_no"));
			comment.setMemberId(rs.getString("member_id"));
			comment.setCommentTitle(rs.getString("comment_title"));
			comment.setCommentContent(rs.getString("comment_content"));
			comment.setCommentDate(rs.getDate("comment_date"));
			comment.setCommentRate(rs.getInt("comment_rate"));
			comment.setCommentStar(rs.getString("comment_star"));
			
			listComment.add(comment);
		}
	
		rs.close();
		pstmt.close();
		conn.close();
		
		return listComment;
		
	}
}
