package com.sunlands.apolloy.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/27
 * Time: 14:17
 */
//Spring boot方式

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

	/**
	 * SQL输出拦截器,日志不全 没有必要打开
	 */
//	@Bean
//	public PerformanceInterceptor performanceInterceptor() {
//		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//		//sql格式化
//		performanceInterceptor.setFormat(true);
//		return performanceInterceptor;
//	}

	//逻辑删除会自动加上
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	/**
	 * 分页查询拦截器
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}


}