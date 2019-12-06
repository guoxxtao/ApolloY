package com.sunlands.apolloy.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:13
 */
@Data
public class ReqAppDTO {
	//ID
	private Integer id;
	//appCode
	private String appCode;
	//appName
	private String appName;
	//描述
	private String description;
	//分类
	private String category;
	//开发负责人姓名
	private String ownerName;
	//开发负责人email
	private String ownerEmail;
	//扩展列表域名，默认为*
	private String domainRestriction;
}
