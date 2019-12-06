package com.sunlands.apolloy.service.app;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * 异步线程执行操作,可执行日志、数据库同步等操作
 * User: guotao
 * Date: 2019/12/4
 * Time: 14:57
 */
@Service
public class AppSyncTask {

	@Async("asyncExecutor")
	public void testApp(){
		System.out.println("test async");
	}

}
