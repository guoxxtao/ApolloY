package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Bean: 扩展点实体
 * Date: 2019/11/26
 * Time: 11:09
 */
@Data
@Builder
@TableName(value = "t_extension_point")
public class ExtensionPointEntity{
	@TableId(value = "id", type = IdType.AUTO)//指定自增策略
	private Integer id;
	//扩展点位置
	private String position;
	//appID
	private String appCode;
	//扩展ID
	private Integer extensionId;
	//位置信息描述
	private String description;
	//位置图片url
	private String positionImageUrl;

	private String createTime;
	@TableLogic
	private Integer deleteFlag;

	public ExtensionPointEntity(Integer id, String position, String appCode, Integer extensionId, String description, String positionImageUrl, String createTime, int deleteFlag) {
		this.id = id;
		this.position = position;
		this.appCode = appCode;
		this.extensionId = extensionId;
		this.description = description;
		this.positionImageUrl = positionImageUrl;
		this.createTime = createTime;
		this.deleteFlag = deleteFlag;
	}

	public ExtensionPointEntity(Integer id) {
		this.id = id;
	}

	public ExtensionPointEntity() {
	}

	public ExtensionPointEntity(Integer id, String position, String appCode, Integer extensionId, String description, String positionImageUrl) {
		this.id = id;
		this.position = position;
		this.appCode = appCode;
		this.extensionId = extensionId;
		this.description = description;
		this.positionImageUrl = positionImageUrl;
	}

	public ExtensionPointEntity(Integer extensionId, String appCode) {
	}
}
