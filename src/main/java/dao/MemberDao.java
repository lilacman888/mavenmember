package dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import model.Member;

public class MemberDao {
	
	//SqlSession 객체 색성
	private SqlSession getSession() {
		SqlSession session = null;
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(reader);
			session = sf.openSession(true);				// auto commit
		}catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	
	//회원 가입
	public int insert(Member member) {
		int result = 0;
		SqlSession session = null;
		
		try {
			session = getSession();
			result = session.insert("insert", member);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 회원인증
	public int chk(Member member) {
		int result = 0;
		SqlSession session = null;
		
		try {
			session = getSession();
//			Member mem = session.selectOne("select", member);
			Member mem = (Member)session.selectOne("select", member);
			if(mem != null) {			//인증 성공
				result = 1;
			}else {						//인증 실패
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원 목록
	public List<Member> list(){
		List list = new ArrayList<Member>();
		SqlSession session = null;
		
		try {
			session = getSession();
			list = session.selectList("list");
			System.out.println("list(주소값) : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 회원 정보 구하기
	public Member select(String id) {
		Member member = null;
		SqlSession session = null;
		
		try {
			session = getSession();
			member = (Member)session.selectOne("member", id);
			System.out.println("member : " + member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return member;
	}
	
	// 회원 정보 수정
	public int update(Member member) {
		int result = 0;
		SqlSession session = null;
		
		try {
			session = getSession();
			result = session.update("update", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원 탈퇴
	public int delete(String id) {
		int result = 0;
		SqlSession session = null;
		
		try {
			session = getSession();
			result = session.delete("delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
