package com.bookstore.handler;

public enum BookAvailableStatus {

	OK(0), NOT_IN_STOCK(1), DOES_NOT_EXIST(2);

	private int val;

	private BookAvailableStatus(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}
