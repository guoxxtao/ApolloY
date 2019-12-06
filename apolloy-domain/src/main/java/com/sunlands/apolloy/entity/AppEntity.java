package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Bean: app应用实体
 * Date: 2019/11/26
 * Time: 11:06
 */
@Data
@Builder
@TableName(value = "t_app")
public class AppEntity {

	@TableId(value = "id",type = IdType.AUTO)//指定自增策略
	private Integer id;

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

	private String createTime;
	@TableLogic
	private Integer deleteFlag;

	public AppEntity(Integer id, String appCode, String appName, String description, String category, String ownerName, String ownerEmail, String domainRestriction, String createTime, Integer deleteFlag) {
		this.id = id;
		this.appCode = appCode;
		this.appName = appName;
		this.description = description;
		this.category = category;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.domainRestriction = domainRestriction;
		this.createTime = createTime;
		this.deleteFlag = deleteFlag;
	}

	public AppEntity() {
	}

	public AppEntity(String appCode) {
		this.appCode = appCode;
	}
}
