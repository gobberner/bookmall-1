package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.OrderDao;
import kr.co.itcen.bookmall.dao.UserDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.OrderBookVo;
import kr.co.itcen.bookmall.vo.OrderVo;
import kr.co.itcen.bookmall.vo.UserVo;

public class OrderDaoTest {
	private static OrderDao dao = null;
	static {
		dao = new OrderDao();
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
		
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.get("email01@test.com");
		
		if (userVo == null) {
			System.out.println("유저가 존재하지 않습니다.");
			return;
		}
		
		List<OrderVo> orderList = dao.getList(userVo);
		
		if (orderList.size() == 0) {
			System.out.println("카트가 비었습니다.");
			return;
		}
		
		for (OrderVo vo : orderList) {
			List<OrderBookVo> orderBookList = vo.getOrderBookList();
			System.out.println(vo + " == 수정 ==");
			
			for (OrderBookVo orderBookVo : orderBookList) {
				orderBookVo.setCount((int)(Math.random() * 5 + 5));
				
				boolean rslt = dao.updateOrderBook(orderBookVo);
				System.out.println(orderBookVo + " ... " + ((rslt) ? "success" : "fail"));
			}
		}
	}

	public static void insertTest() {
		System.out.println("Insert Test ----------------------------");
		
		UserDao userDao = new UserDao();
		List<UserVo> userList = userDao.getList();
		
		if (userList.size() == 0) {
			System.out.println("유저가 존재하지 않습니다.");
			return;
		}
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = bookDao.getList();
		
		if (bookList.size() == 0) {
			System.out.println("도서가 존재하지 않습니다.");
			return;
		}
		
		String[] address = new String[2];
		address[0] = "도봉구 쌍문동";
		address[1] = "서울 강서구 방화동";
		
		for (int i = 0; i < userList.size(); i++) {
			UserVo userVo = userList.get(i);
			OrderVo orderVo = new OrderVo(userVo.getNo(), address[i]);
			
			long idx = dao.insert(orderVo);
			System.out.println(orderVo + "....." + ((idx > -1) ? "Succcess" : "Fail"));
			
			if (idx == -1) {
				System.out.println("주문 등록중 오류가 발생하였습니다.");
				return;
			}
			
			int randomBookIdx = (int)(Math.random() * bookList.size());
			
			for (int j = 0; j < 2; j++) {
				OrderBookVo orderBookVo = new OrderBookVo(idx, (int)(Math.random() * 4 + 1), userVo.getNo(), bookList.get((j + randomBookIdx) % bookList.size()).getNo());
				boolean rslt = dao.insertOrderBook(orderBookVo);
				System.out.println(orderBookVo + " ... " + ((rslt) ? "Succcess" : "Fail"));
			}
			
		}

	}

	public static void selectTest() {
		System.out.println("Select Test ----------------------------");
		
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.get("email01@test.com");
		
		List<OrderVo> list = dao.getList(userVo);

		for (OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void deleteTest() {
		System.out.println("Delete Test ----------------------------");
		System.out.println("삭제한 데이터 : " + dao.deleteAll());
	}

}
