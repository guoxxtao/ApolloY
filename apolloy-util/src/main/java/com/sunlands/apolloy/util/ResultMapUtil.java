package com.sunlands.apolloy.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/28
 * Time: 18:14
 */
public abstract class ResultMapUtil {

	private static final String TOTAL = "total";
	private static final String CURRENTPAGE = "currentPage";
	private static final String ROWSPERPAGE = "rowsPerPage";

	public static Map<String, Object> getResultMap(Long total, Long currentPage, Long rowsPerPage) {
		Map<String, Object> resultMap = new HashMap();
		if (total != null)
			resultMap.put(TOTAL, total);
		if (currentPage != null)
			resultMap.put(CURRENTPAGE, currentPage);
		if (rowsPerPage != null)
			resultMap.put(ROWSPERPAGE, rowsPerPage);
		return resultMap;
	}

	public static Map<String, Object> getResultMap(IPage iPage) {
		Map<String, Object> resultMap = new HashMap();
		resultMap.put(TOTAL, iPage.getTotal());
		resultMap.put(CURRENTPAGE, iPage.getCurrent());
		resultMap.put(ROWSPERPAGE, iPage.getSize());
		return resultMap;
	}

	public static Map<String, Object> getResultMap(){
		Map<String, Object> resultMap = new HashMap();
		return resultMap;
	}
}
