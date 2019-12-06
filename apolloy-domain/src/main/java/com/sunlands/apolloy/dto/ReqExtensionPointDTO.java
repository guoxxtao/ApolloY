package com.sunlands.apolloy.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:42
 */
@Data
public class ReqExtensionPointDTO {
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

	public ReqExtensionPointDTO(String appCode, Integer extensionId) {
		this.appCode = appCode;
		this.extensionId = extensionId;
	}
}
