package com.sunlands.apolloy.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Bean: 分页用
 * Date: 2019/11/26
 * Time: 14:33
 */
@Data
public class PaginationEntity {

	//总条数
	private Long total;
	//当前⻚数
	private Long currentPage;
	//当前⻚数条数
	private Long rowsPerPage;
}
