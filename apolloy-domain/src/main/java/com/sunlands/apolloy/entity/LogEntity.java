package com.sunlands.apolloy.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/29
 * Time: 14:28
 */
@Data
public class LogEntity {

	private String operationType;

	private String operationFunction;

	private String operator;

	private String operationTime;

	public LogEntity() {
	}

	public LogEntity(String operationType, String operationFunction, String operator, String operationTime) {
		this.operationType = operationType;
		this.operationFunction = operationFunction;
		this.operator = operator;
		this.operationTime = operationTime;
	}

	public LogEntity(String operationType, String operationFunction, String operator) {
		this.operationType = operationType;
		this.operationFunction = operationFunction;
		this.operator = operator;
	}

	public LogEntity(String operationType,StackTraceElement stackTraceElement, String operator) {
		this.operationType = operationType;
		this.operationFunction = stackTraceElement.getClassName()+"-"+stackTraceElement.getMethodName();
		this.operator = operator;
	}
}
