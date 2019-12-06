package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/29
 * Time: 14:33
 */
@Data
@TableName(value = "t_extension_log")
public class ExtensionLogEntity extends LogEntity{

	@TableField(value = "extension_id")
	private Integer extensionId;
	//扩展名
	@TableField(value = "extension_name")
	private String extensionName;
	//扩展描述
	private String description;
	//扩展分类
	private String category;
	//开发负责人姓名
	@TableField(value = "owner_name")
	private String ownerName;
	//开发负责人email
	@TableField(value = "owner_email")
	private String ownerEmail;
	//开发环境url
	@TableField(value = "dev_entry_url")
	private String devEntryUrl;
	//测试环境url
	@TableField(value = "test_entry_url")
	private String testEntryUrl;
	//线上环境url
	@TableField(value = "prod_entry_url")
	private String prodEntryUrl;

	public ExtensionLogEntity() {
	}
}
