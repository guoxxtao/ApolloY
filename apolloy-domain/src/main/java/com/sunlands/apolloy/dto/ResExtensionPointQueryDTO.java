package com.sunlands.apolloy.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/28
 * Time: 19:08
 */
@Data
public class ResExtensionPointQueryDTO {

	//扩展名
	private String extensionName;
	//扩展描述
	private String extensionDescription;
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

	//扩展点ID
	private Integer id;
	//扩展点位置
	private String position;
	//appID
	private String appCode;
	//扩展ID
	private Integer extensionId;
	//位置信息描述
	private String extensionPointDescription;
	//位置图片url
	private String positionImageUrl;

	public ResExtensionPointQueryDTO() {
	}

	public ResExtensionPointQueryDTO(String extensionName, String extensionDescription, String category, String ownerName, String ownerEmail, String devEntryUrl, String testEntryUrl, String prodEntryUrl, Integer id, String position, String appCode, Integer extensionId, String extensionPointDescription, String positionImageUrl) {
		this.extensionName = extensionName;
		this.extensionDescription = extensionDescription;
		this.category = category;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.devEntryUrl = devEntryUrl;
		this.testEntryUrl = testEntryUrl;
		this.prodEntryUrl = prodEntryUrl;
		this.id = id;
		this.position = position;
		this.appCode = appCode;
		this.extensionId = extensionId;
		this.extensionPointDescription = extensionPointDescription;
		this.positionImageUrl = positionImageUrl;
	}


}
