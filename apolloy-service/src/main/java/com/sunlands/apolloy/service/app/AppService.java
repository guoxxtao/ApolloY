package com.sunlands.apolloy.service.app;

import com.sunlands.apolloy.dto.ReqAppDTO;
import com.sunlands.apolloy.dto.ReqAppQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 11:18
 */
public interface AppService {
	/**
	 * 添加APP
	 *
	 * @param reqAppDTO
	 * @return
	 */
	ResDTO add(ReqAppDTO reqAppDTO);

	/**
	 * 更新APP
	 *
	 * @param reqAppDTO
	 * @return
	 */
	ResDTO update(ReqAppDTO reqAppDTO);

	/**
	 * 查询APP
	 *
	 * @param reqAppQueryDTO
	 * @return
	 */
	ResDTO query(ReqAppQueryDTO reqAppQueryDTO);

	/**
	 * 删除APP
	 *
	 * @param appCode
	 * @return
	 */
	ResDTO delete(String appCode);

	/**
	 * 获取单个APP
	 *
	 * @param appCode
	 * @return
	 */
	ResDTO get(String appCode);

	/**
	 * @param categoryType
	 * @return
	 */
	ResDTO getAllCategory(Integer categoryType);
}
