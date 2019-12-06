package com.sunlands.apolloy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/29
 * Time: 14:41
 */
@Data
@TableName(value = "t_extension_point_log")
public class ExtensionPointLogEntity extends LogEntity{

	private Integer extensionPointId;
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

	public ExtensionPointLogEntity() {

	}
}
