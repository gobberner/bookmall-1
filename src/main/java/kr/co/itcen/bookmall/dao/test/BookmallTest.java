package kr.co.itcen.bookmall.dao.test;

public class BookmallTest {

	public static void main(String[] args) {
		insertTest();
		selectTest();
		updateTest();
		deleteTest();
	}
	
	// insert
	public static void insertTest() {
		UserDaoTest.insertTest();
		CategoryDaoTest.insertTest();
		BookDaoTest.insertTest();
		CartDaoTest.insertTest();
		OrderDaoTest.insertTest();
	}
	
	// select
	public static void selectTest() {
		UserDaoTest.selectTest();
		CategoryDaoTest.selectTest();
		BookDaoTest.selectTest();
		CartDaoTest.selectTest();
		OrderDaoTest.selectTest();
	}
	
	// update
	public static void updateTest() {
		UserDaoTest.updateTest();
		CategoryDaoTest.updateTest();
		BookDaoTest.updateTest();
		CartDaoTest.updateTest();
		OrderDaoTest.updateTest();
	}
	
	// delete
	public static void deleteTest() {
		OrderDaoTest.deleteTest();
		CartDaoTest.deleteTest();
		BookDaoTest.deleteTest();
		CategoryDaoTest.deleteTest();
		UserDaoTest.deleteTest();
	}
}
