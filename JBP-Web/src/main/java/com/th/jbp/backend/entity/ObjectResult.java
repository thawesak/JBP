package com.th.jbp.backend.entity;

import java.util.ArrayList;

public class ObjectResult<T> {

	public static final int SUCCESS = 500;
	public static final int FAIL = 501;
	
	public static final int DATA_NOT_FOUND = 901;
	public static final int DUPLICATE_CARD_ID = 902;
	public static int INVALID_DATE_TIME = 903;
	public static int INVALID_AMOUNT = 904;

	private int code;
	private String message;
	private T object;
	private ArrayList<T> objects;
	private String detail;

	public ObjectResult() {
		super();
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ObjectResult(int code) {
		this.code = code;
	}

	public ObjectResult(int code, T object) {
		this.code = code;
		this.object = object;
	}

	public ObjectResult(int code, T object, String message) {
		this.code = code;
		this.object = object;
		this.message = message;
	}

	public ObjectResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public ArrayList<T> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<T> objects) {
		this.objects = objects;
	}

}
