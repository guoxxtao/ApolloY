package com.sunlands.apolloy.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:26
 */
@Data
public class ReqExtensionDTO {
	//扩展ID
	private Integer id;
	//扩展名
	private String extensionName;
	//扩展描述
	private String description;
	//扩展分类
	private String category;
	//开发负责人姓名
	private String ownerName;
	//开发负责人email
	private String ownerEmail;
	//开发环境url
	private String devEntryUrl;
	//测试环境url
	private String testEntryUrl;
	//线上环境url
	private String prodEntryUrl;
}
