package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.CartDao;
import kr.co.itcen.bookmall.dao.UserDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CartVo;
import kr.co.itcen.bookmall.vo.UserVo;

public class CartDaoTest {
	private static CartDao dao = null;
	static {
		dao = new CartDao();
	}
	
	public static void main(String[] args) {
		System.out.println("================ CartDao Test Start ================");
		
//		deleteTest();
		insertTest();
		selectTest();
		updateTest();
		
		System.out.println("================ CartDao Test End ================");
	}

	public static void updateTest() {
		System.out.println("Update Test ----------------------------");
		
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.get("email01@test.com");
		
		if (userVo == null) {
			System.out.println("유저가 존재하지 않습니다.");
			return;
		}
		
		List<CartVo> cartList = dao.getList(userVo);
		
		if (cartList.size() == 0) {
			System.out.println("카트가 비었습니다.");
			return;
		}
		
		for (CartVo vo : cartList) {
			vo.setCount((int)(Math.random() * 5) + 5);
			
			boolean rslt = dao.update(vo);
			System.out.println(vo + " ... " + ((rslt) ? "success" : "fail"));
		}
	}

	public static void insertTest() {
		System.out.println("Insert Test ----------------------------");

		boolean rslt = false;

		UserDao userDao = new UserDao();
		List<UserVo> userList = userDao.getList();
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = bookDao.getList();
		
		for (UserVo userVo : userList) {
			System.out.println("[" + userVo.getName() + "(" + userVo.getEmail() + ")]");
			CartVo vo1 = new CartVo((int)((Math.random() * 4) + 1)
								, userVo.getNo()
								, bookList.get((int)(Math.random() * bookList.size())).getNo());
			rslt = dao.insert(vo1);
			System.out.println(vo1 + "....." + ((rslt) ? "Succcess" : "Fail"));
			
			CartVo vo2 = new CartVo((int)((Math.random() * 4) + 1)
								, userVo.getNo()
								, bookList.get((int)(Math.random() * bookList.size())).getNo());
			rslt = dao.insert(vo2);
			System.out.println(vo2 + "....." + ((rslt) ? "Succcess" : "Fail"));
		}
	}

	public static void selectTest() {
		System.out.println("Select Test ----------------------------");
		
		UserDao userDao = new UserDao();
		List<UserVo> userList = userDao.getList();
		
		if (userList.size() == 0) {
			System.out.println("유저가 존재하지 않습니다.");
			return;
		}
		
		List<CartVo> list = dao.getList(userList.get((int)(Math.random() * userList.size())));

		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void deleteTest() {
		System.out.println("Delete Test ----------------------------");
		System.out.println("삭제한 데이터 : " + dao.deleteAll());
	}
}
