package com.sunlands.apolloy.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/12/2
 * Time: 17:49
 */
@Data
public class ApolloyException extends RuntimeException {
	private String message;

	public ApolloyException() {
		super();
	}

	public ApolloyException(String message) {
		super(message);
		this.message = message;
	}
}
