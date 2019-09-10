package kr.co.itcen.bookmall.vo;

public class OrderBookVo {
	private long no;
	private long count;
	private long orderNo;
	private long bookNo;
	
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
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	
	@Override
	public String toString() {
		return "OrderBookVo [no=" + no + ", count=" + count + ", orderNo=" + orderNo + ", bookNo=" + bookNo + "]";
	}
	
	public OrderBookVo(long no, long count, long orderNo, long bookNo) {
		super();
		this.no = no;
		this.count = count;
		this.orderNo = orderNo;
		this.bookNo = bookNo;
	}
}
