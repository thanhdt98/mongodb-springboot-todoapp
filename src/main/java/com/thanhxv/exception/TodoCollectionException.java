package com.thanhxv.exception;

public class TodoCollectionException extends Exception {

	public TodoCollectionException(String message) {
		super(message);
	}

	public static String notFoundException(String id) {
		return "Todo with " + id + " not found!";
	}

	public static String todoAlreadyExists() {
		return "Todo with given name already exists";
	}

}
