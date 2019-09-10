package kr.co.itcen.bookmall.vo;

public class CartVo {
	private long no;
	private long count;
	private long userNo;
	private long bookNo;
	
	private long price;
	
	public CartVo(long count, long userNo, long bookNo) {
		this(0L, count, userNo, bookNo, 0L);
	}
	
	public CartVo(long no, long count, long userNo, long bookNo, long price) {
		super();
		this.no = no;
		this.count = count;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.price = price;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}

	@Override
	public String toString() {
		return "CartVo [no=" + no + ", count=" + count + ", userNo=" + userNo + ", bookNo=" + bookNo + ", price="
				+ price + "]";
	}
	
}
