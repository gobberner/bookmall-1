package kr.co.itcen.bookmall.dao.test;

import java.util.List;

import kr.co.itcen.bookmall.dao.CategoryDao;
import kr.co.itcen.bookmall.vo.CategoryVo;

public class CategoryDaoTest {
	private static CategoryDao dao = null;
	static {
		dao = new CategoryDao();
	}
	
	public static void main(String[] args) {
		System.out.println("================ CategoryDao Test Start ================");

//		deleteTest();
		insertTest();
		selectTest();
		updateTest();
		
		System.out.println("================ CategoryDao Test End ================");
	}

	public static void updateTest() {
		System.out.println("Update Test ----------------------------");
		
		String name = "IT";
		CategoryVo categoryVo = dao.get(name);
		
		if (categoryVo == null) {
			System.out.println(name + "은 존재하지 않는 카테고리입니다.");
		}
		
		categoryVo.setName("수정된 IT");
		boolean rslt = dao.update(categoryVo);
		
		System.out.println(categoryVo + " ... " + ((rslt) ? "success" : "fail"));
	}

	public static void insertTest() {
		System.out.println("Insert Test ----------------------------");
		
		boolean rslt = false;

		CategoryVo vo1 = new CategoryVo("IT");
		rslt = dao.insert(vo1);
		System.out.println(vo1 + "....." + ((rslt) ? "Succcess" : "Fail"));

		CategoryVo vo2 = new CategoryVo("자기계발");
		rslt = dao.insert(vo2);
		System.out.println(vo2 + "....." + ((rslt) ? "Succcess" : "Fail"));
		
		CategoryVo vo3 = new CategoryVo("자연과학");
		rslt = dao.insert(vo3);
		System.out.println(vo3 + "....." + ((rslt) ? "Succcess" : "Fail"));
	}

	public static void selectTest() {
		System.out.println("Select Test ----------------------------");
		List<CategoryVo> list = dao.getList();
		
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void deleteTest() {
		System.out.println("Delete Test ----------------------------");
		System.out.println("삭제한 데이터 : " + dao.deleteAll());
	}

}
