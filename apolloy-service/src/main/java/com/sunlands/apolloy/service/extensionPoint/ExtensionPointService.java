package com.sunlands.apolloy.service.extensionPoint;

import com.sunlands.apolloy.dto.ReqExtensionPointDTO;
import com.sunlands.apolloy.dto.ReqExtensionPointQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.entity.ExtensionPointEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:56
 */
public interface ExtensionPointService {
	/**
	 * 添加扩展点
	 *
	 * @param reqExtensionPointDTO
	 * @return
	 */
	ResDTO add(ReqExtensionPointDTO reqExtensionPointDTO);

	/**
	 * 更新扩展点
	 *
	 * @param reqExtensionPointDTO
	 * @return
	 */
	ResDTO update(ReqExtensionPointDTO reqExtensionPointDTO);

	/**
	 * 查询扩展点
	 *
	 * @param reqExtensionPointQueryDTO
	 * @return
	 */
	ResDTO query(ReqExtensionPointQueryDTO reqExtensionPointQueryDTO);

	/**
	 * 删除扩展点
	 *
	 * @param extensionPointId
	 * @return
	 */
	ResDTO delete(Integer extensionPointId);

	/**
	 * 获取单个扩展点
	 *
	 * @param extensionPointId
	 * @return
	 */
	ResDTO get(Integer extensionPointId);

	/**
	 * 查询扩展点,跨域
	 *
	 * @param reqExtensionPointQueryDTO
	 * @return
	 */
	ResDTO queryByApp(ReqExtensionPointQueryDTO reqExtensionPointQueryDTO);

	/**
	 * 根据扩展点信息批量删除扩展点
	 * @param reqExtensionPointDTO
	 */
	void deleteByExtPointInfo(ReqExtensionPointDTO reqExtensionPointDTO);

	/**
	 * 根据扩展点信息查询符合条件的扩展点ID
	 * @param entity
	 * @return
	 */
	List<ExtensionPointEntity> queryExtPointsByExtPointInfo(ExtensionPointEntity entity);


}
