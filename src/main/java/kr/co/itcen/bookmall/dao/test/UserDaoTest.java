package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.UserDao;
import kr.co.itcen.bookmall.vo.UserVo;

public class UserDaoTest {
	private static UserDao dao;
	static {
		dao = new UserDao();
	}
	
	public static void main(String[] args) {
		System.out.println("================ UserDao Test Start ================");
		
//		deleteTest();
		insertTest();
		selectTest();
		updateTest();
		
		System.out.println("================ UserDao Test End ================");
	}
	
	public static void updateTest() {
		System.out.println("Update Test ----------------------------");
		
		String email = "email01@test.com";
		UserVo vo = dao.get(email);
		
		if (vo == null) {
			System.out.println("[" + email + "]은 존재하지 않는 유저입니다.");
			return;
		}
		
		vo.setName("수정된 둘리");
		
		boolean rslt = dao.update(vo);
		
		System.out.println(vo + " ... " + ((rslt) ? "success" : "fail"));
	}

	public static void insertTest() {
		System.out.println("Insert Test ----------------------------");
		
		boolean rslt = false;
		
		UserVo vo1 = new UserVo("둘리", "01011111111", "email01@test.com", "asdfasdf");
		rslt = dao.insert(vo1);
		System.out.println(vo1 + "....." + ((rslt) ? "Succcess" : "Fail"));
		
		UserVo vo2 = new UserVo("또치", "01022222222", "email02@test.com", "12341234");
		rslt = dao.insert(vo2);
		System.out.println(vo2 + "....." + ((rslt) ? "Succcess" : "Fail"));
	}
	
	public static void selectTest() {
		System.out.println("Select Test ----------------------------");
		
		List<UserVo> list = dao.getList();
		
		for (UserVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void deleteTest() {
		System.out.println("Delete Test ----------------------------");
		System.out.println("삭제한 데이터 : " + dao.deleteAll());
	}

}
