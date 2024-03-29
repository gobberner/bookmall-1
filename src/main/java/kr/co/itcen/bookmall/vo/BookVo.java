package kr.co.itcen.bookmall.vo;

public class BookVo {
	private long no;
	private String title;
	private long price;
	private long categoryNo;
	
	public BookVo(String title, long price, long categoryNo) {
		this(0L, title, price, categoryNo);
	}
	
	public BookVo(long no, String title, long price, long categoryNo) {
		this.no = no;
		this.title = title;
		this.price = price;
		this.categoryNo = categoryNo;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", categoryNo=" + categoryNo + "]";
	}
	
	
}
