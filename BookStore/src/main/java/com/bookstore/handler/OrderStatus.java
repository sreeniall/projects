package com.bookstore.handler;

public enum OrderStatus {

	BASKET("basket"), INPROCESS("inprocess"), DISPATCHED("dispatched");

	private String val;

	private OrderStatus(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
