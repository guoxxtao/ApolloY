package com.sunlands.apolloy.advice;

import com.sunlands.apolloy.baseResultJson.ResultJsonManager;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.exception.ApolloyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/12/2
 * Time: 17:52
 */
@RestControllerAdvice
public class ApolloyExceptionAdvice {

	@ExceptionHandler({ApolloyException.class})    //申明捕获那个异常类
	public ResDTO apolloyExceptionHandler(Exception e) {
		e.printStackTrace();
		return ResultJsonManager.getResultJson().returnErrorJson(e.getMessage());
	}


	@ExceptionHandler({Exception.class})    //申明捕获那个异常类
	public ResDTO globalExceptionHandler(Exception e) {
		e.printStackTrace();
		return ResultJsonManager.getResultJson().returnErrorJson("未知错误");
	}

}
