package kr.co.itcen.bookmall.dao.test;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import kr.co.itcen.bookmall.dao.BookDao;
import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.vo.BookVo;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class BookDaoTest {
	private static BookDao dao = null;
	static {
		dao = new BookDao();
	}
	
	public static void main(String[] args) {
		System.out.println("================ BookDao Test Start ================");
		
//		deleteTest();
		insertTest();
		selectTest();
		updateTest();
		
		System.out.println("================ BookDao Test End ================");
	}

	public static void updateTest() {
		System.out.println("Update Test ----------------------------");
		String title = "토비의 스프링";
		BookVo bookVo = dao.get(title);
		
		if (bookVo == null) {
			System.out.println(title + "은 존재하지 않는 도서입니다.");
		}
		
		bookVo.setTitle("수정된 토비의 스프링");
		bookVo.setPrice(99000);
	
		boolean rslt = dao.update(bookVo);
		
		System.out.println(bookVo + " ... " + ((rslt) ? "success" : "fail"));
	}

	public static void insertTest() {
		System.out.println("Insert Test ----------------------------");
		
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryVo> categoryList = categoryDao.getList();
		
		if (categoryList.size() == 0) {
			System.out.println("등록 된 카테고리가 없습니다.");
			return;
		}
		
		Random r = new Random(Calendar.getInstance().getTimeInMillis());
		CategoryVo category = null;

		boolean rslt = false;

		category = categoryList.get(r.nextInt(categoryList.size()));
		BookVo vo1 = new BookVo("토비의 스프링", 36000, category.getNo());
		rslt = dao.insert(vo1);
		System.out.println(vo1 + "....." + ((rslt) ? "Succcess" : "Fail"));

		category = categoryList.get(r.nextInt(categoryList.size()));
		BookVo vo2 = new BookVo("Java의 정석", 27000, category.getNo());
		rslt = dao.insert(vo2);
		System.out.println(vo2 + "....." + ((rslt) ? "Succcess" : "Fail"));
		
		category = categoryList.get(r.nextInt(categoryList.size()));
		BookVo vo3 = new BookVo("모바일 웹 개발의 정석", 19800, category.getNo());
		rslt = dao.insert(vo3);
		System.out.println(vo3 + "....." + ((rslt) ? "Succcess" : "Fail"));
	}

	public static void selectTest() {
		System.out.println("Select Test ----------------------------");
		List<BookVo> list = dao.getList();

		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void deleteTest() {
		System.out.println("Delete Test ----------------------------");
		System.out.println("삭제한 데이터 : " + dao.deleteAll());
	}

}
