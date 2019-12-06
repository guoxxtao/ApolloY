package com.sunlands.apolloy.dto;

import com.sunlands.apolloy.entity.PaginationEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:17
 */
@Data
@ToString(callSuper = true)
public class ReqAppQueryDTO extends PaginationEntity {
	//ID
	private Integer id;
	//appCode
	private String appCode;
	//appName
	private String appName;
	//分类
	private List<String> category;
	//开发负责人姓名
	private String ownerName;
	//开发负责人email
	private String ownerEmail;
}
