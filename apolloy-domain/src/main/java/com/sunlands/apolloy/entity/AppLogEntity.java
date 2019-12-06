package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/29
 * Time: 14:33
 */
@Data
@Builder
@TableName(value = "t_app_log")
public class AppLogEntity extends LogEntity{

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

	public AppLogEntity(String appCode, String appName, String description, String category, String ownerName, String ownerEmail, String domainRestriction) {
		this.appCode = appCode;
		this.appName = appName;
		this.description = description;
		this.category = category;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.domainRestriction = domainRestriction;
	}

	public AppLogEntity() {

	}

}
