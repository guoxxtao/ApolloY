package com.sunlands.apolloy.dto;

import com.sunlands.apolloy.entity.PaginationEntity;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:31
 */
@Data
public class ReqExtensionQueryDTO extends PaginationEntity {

	//扩展ID
	private Integer id;
	//扩展名
	private String extensionName;
	//扩展分类
	private List<String> category;
	//开发负责人姓名
	private String ownerName;
	//开发负责人email
	private String ownerEmail;

}
