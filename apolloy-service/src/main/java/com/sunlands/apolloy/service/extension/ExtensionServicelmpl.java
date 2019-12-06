package com.sunlands.apolloy.service.extension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunlands.apolloy.baseResultJson.ResultJsonManager;
import com.sunlands.apolloy.constans.DBConstans;
import com.sunlands.apolloy.constans.SysConstants;
import com.sunlands.apolloy.dao.extension.ExtensionDao;
import com.sunlands.apolloy.dao.extension.ExtensionLogDao;
import com.sunlands.apolloy.dto.ReqExtensionDTO;
import com.sunlands.apolloy.dto.ReqExtensionPointDTO;
import com.sunlands.apolloy.dto.ReqExtensionQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.entity.ExtensionEntity;
import com.sunlands.apolloy.entity.ExtensionLogEntity;
import com.sunlands.apolloy.entity.LogEntity;
import com.sunlands.apolloy.service.app.AppSyncTask;
import com.sunlands.apolloy.service.extensionPoint.ExtensionPointService;
import com.sunlands.apolloy.util.ResultMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:54
 */
@Service
public class ExtensionServicelmpl implements ExtensionService {

	private static final Logger logger = LoggerFactory.getLogger(ExtensionService.class);

	//与前端协商的常量字段
	public static final String list = "list";

	@Autowired
	private ExtensionDao extensionDao;

	@Autowired
	private ExtensionLogDao extensionLogDao;

	@Autowired
	private ExtensionPointService extensionPointService;

	@Autowired
	private AppSyncTask appSyncTask;

	@Override
	public ResDTO add(ReqExtensionDTO reqDTO) {
		ExtensionEntity entity = new ExtensionEntity();
		BeanUtils.copyProperties(reqDTO, entity);
		int flag = extensionDao.insert(entity);
		//插入日志
		insertLog(flag, SysConstants.ADD, entity);
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO update(ReqExtensionDTO reqDTO) {
		ExtensionEntity entity = new ExtensionEntity();
		BeanUtils.copyProperties(reqDTO,entity);
		int flag = extensionDao.updateById(entity);
		//插入日志
		insertLog(flag, SysConstants.UPDATE, entity);
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO query(ReqExtensionQueryDTO reqDTO) {
		Page<ExtensionEntity> page = new Page<>(reqDTO.getCurrentPage(), reqDTO.getRowsPerPage());
		QueryWrapper<ExtensionEntity> queryWrapper = new QueryWrapper<>();
		if (reqDTO.getId() != null)
			queryWrapper.eq(DBConstans.ID, reqDTO.getId());
		if (!StringUtils.isEmpty(reqDTO.getExtensionName()))
			queryWrapper.like(DBConstans.EXTENSION_NAME, reqDTO.getExtensionName());
		if (!StringUtils.isEmpty(reqDTO.getOwnerName()))
			queryWrapper.eq(DBConstans.OWNER_NAME, reqDTO.getOwnerName());
		if (!StringUtils.isEmpty(reqDTO.getOwnerEmail()))
			queryWrapper.eq(DBConstans.OWNER_EMAIL, reqDTO.getOwnerEmail());
		if (!CollectionUtils.isEmpty(reqDTO.getCategory())) {
			queryWrapper.in(DBConstans.CATEGORY, reqDTO.getCategory());
		}
		queryWrapper.orderByDesc(DBConstans.CREATE_TIME);
		IPage<ExtensionEntity> entityIPage = extensionDao.selectPage(page, queryWrapper);

		//自动添加分页信息
		Map<String, Object> resultMap = ResultMapUtil.getResultMap(entityIPage);
		resultMap.put(list, entityIPage.getRecords());
		return ResultJsonManager.getResultJson().returnDataSuccessJson(resultMap);
	}

	@Override
	@Transactional
	public ResDTO delete(Integer extensionId) {
		int flag = extensionDao.deleteById(extensionId);
		//插入日志
		insertLog(flag, SysConstants.DELETE, new ExtensionEntity(extensionId));
		//需要删除对应EXTENSIONId的所有扩展点
		extensionPointService.deleteByExtPointInfo(new ReqExtensionPointDTO(null,extensionId));
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO get(Integer extensionId) {
		ExtensionEntity entity = extensionDao.selectById(extensionId);
		return ResultJsonManager.getResultJson().returnDataSuccessJson(entity);
	}

	/**
	 * 插入日志
	 *
	 * @param flag
	 * @param operationType
	 * @param entity
	 */
	@Async("insertLogExecutor")
	public void insertLog(int flag, String operationType, ExtensionEntity entity) {
		if (flag > 0) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
			ExtensionLogEntity logEntity = new ExtensionLogEntity();
			BeanUtils.copyProperties(entity, logEntity);
			BeanUtils.copyProperties(new LogEntity(operationType, stackTraceElement, null), logEntity);
			logEntity.setExtensionId(entity.getId());
			extensionLogDao.insert(logEntity);
		}
		logger.info("操作成功 entity = {},操作类型 operationType = {},操作条数 = {} ", entity.toString(), operationType, flag);
	}
}
