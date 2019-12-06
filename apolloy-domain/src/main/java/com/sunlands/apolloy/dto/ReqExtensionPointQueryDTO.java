package com.sunlands.apolloy.dto;

import com.sunlands.apolloy.entity.PaginationEntity;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:42
 */
@Data
public class ReqExtensionPointQueryDTO extends PaginationEntity{
	private Integer id;
	//appCode
	private String appCode;
	//分类
	private String category;
	//扩展ID
	private Integer extensionId;
}
