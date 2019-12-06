package com.sunlands.apolloy.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * DTO: 返回基类
 * Date: 2019/11/26
 * Time: 11:13
 */
@Data
public class ResDTO {

	private int flag;
	private String message;
	private Object data;

	public ResDTO() {
	}

	public ResDTO(Integer flag, String message, Object data) {
		this.flag = flag;
		this.message = message;
		this.data = data;
	}

	public ResDTO(Integer flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public ResDTO(Integer flag, Object data) {
		this.flag = flag;
		this.data = data;
	}

	public ResDTO(boolean flag) {
		if (flag) {
			this.flag = 1;
			this.message = "ok";
		} else {
			this.flag = 0;
			this.message = "fail";
		}
	}
}
