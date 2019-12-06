package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * bean：扩展实体
 * Date: 2019/11/26
 * Time: 10:59
 */
@Data
@Builder
@ToString
@TableName(value = "t_extension")
public class ExtensionEntity {
	//扩展ID
	@TableId(value = "id", type = IdType.AUTO)//指定自增策略
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
	@TableLogic
	private int deleteFlag;
	private String createTime;

	public ExtensionEntity(Integer id, String extensionName, String description, String category, String ownerName, String ownerEmail, String devEntryUrl, String testEntryUrl, String prodEntryUrl, int deleteFlag, String createTime) {
		this.id = id;
		this.extensionName = extensionName;
		this.description = description;
		this.category = category;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.devEntryUrl = devEntryUrl;
		this.testEntryUrl = testEntryUrl;
		this.prodEntryUrl = prodEntryUrl;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
	}

	public ExtensionEntity() {
	}

	public ExtensionEntity(Integer id) {
		this.id = id;
	}

	public ExtensionEntity(Integer id, String extensionName, String description, String category, String ownerName, String ownerEmail, String devEntryUrl, String testEntryUrl, String prodEntryUrl) {
		this.id = id;
		this.extensionName = extensionName;
		this.description = description;
		this.category = category;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.devEntryUrl = devEntryUrl;
		this.testEntryUrl = testEntryUrl;
		this.prodEntryUrl = prodEntryUrl;
	}
}
