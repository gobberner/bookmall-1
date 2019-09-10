package kr.co.itcen.bookmall.vo;

import java.util.Date;
import java.util.List;

public class OrderVo {
	private long no;
	private long userNo;
	private String address;
	private Date regDate;
	
	private List<OrderBookVo> orderBookList;
	
	public List<OrderBookVo> getOrderBookList() {
		return orderBookList;
	}
	public void setOrderBookList(List<OrderBookVo> orderBookList) {
		this.orderBookList = orderBookList;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", userNo=" + userNo + ", address=" + address + ", regDate=" + regDate
				+ ", orderBookList=" + orderBookList + "]";
	}
	
	public OrderVo(long userNo, String address) {
		this(0L, userNo, address, null, null);
	}
	
	public OrderVo(long no, long userNo, String address, Date regDate, List<OrderBookVo> orderBookList) {
		super();
		this.no = no;
		this.userNo = userNo;
		this.address = address;
		this.regDate = regDate;
		this.orderBookList = orderBookList;
	}	
	
}
