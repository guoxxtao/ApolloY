package com.sunlands.apolloy.service.extension;

import com.sunlands.apolloy.dto.ReqExtensionDTO;
import com.sunlands.apolloy.dto.ReqExtensionQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:24
 */
public interface ExtensionService {
	/**
	 * 添加扩展
	 *
	 * @param reqExtensionDTO
	 * @return
	 */
	ResDTO add(ReqExtensionDTO reqExtensionDTO);

	/**
	 * 更新扩展
	 *
	 * @param reqExtensionDTO
	 * @return
	 */
	ResDTO update(ReqExtensionDTO reqExtensionDTO);

	/**
	 * 查询扩展
	 *
	 * @param reqExtensionQueryDTO
	 * @return
	 */
	ResDTO query(ReqExtensionQueryDTO reqExtensionQueryDTO);

	/**
	 * 删除扩展
	 *
	 * @param extensionId
	 * @return
	 */
	ResDTO delete(Integer extensionId);

	/**
	 * 获取单个扩展
	 *
	 * @param extensionId
	 * @return
	 */
	ResDTO get(Integer extensionId);
}
