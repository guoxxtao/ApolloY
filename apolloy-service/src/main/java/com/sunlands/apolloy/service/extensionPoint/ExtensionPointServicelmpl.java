package com.sunlands.apolloy.service.extensionPoint;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunlands.apolloy.baseResultJson.ResultJsonManager;
import com.sunlands.apolloy.constans.DBConstans;
import com.sunlands.apolloy.constans.SysConstants;
import com.sunlands.apolloy.dao.extension.ExtensionDao;
import com.sunlands.apolloy.dao.extensionPoint.ExtensionPointDao;
import com.sunlands.apolloy.dao.extensionPoint.ExtensionPointLogDao;
import com.sunlands.apolloy.dto.ReqExtensionPointDTO;
import com.sunlands.apolloy.dto.ReqExtensionPointQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.dto.ResExtensionPointQueryDTO;
import com.sunlands.apolloy.entity.ExtensionEntity;
import com.sunlands.apolloy.entity.ExtensionPointEntity;
import com.sunlands.apolloy.entity.ExtensionPointLogEntity;
import com.sunlands.apolloy.entity.LogEntity;
import com.sunlands.apolloy.exception.ApolloyException;
import com.sunlands.apolloy.util.ResultMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:58
 */
@Service
public class ExtensionPointServicelmpl implements ExtensionPointService {

	private static final Logger logger = LoggerFactory.getLogger(ExtensionPointService.class);

	//与前端协商的常量字段
	public static final String list = "list";
	public static final String extensionInfo = "ext";
	public static final String extensionPointInfo = "extPoint";

	@Autowired
	private ExtensionPointDao extensionPointDao;

	@Autowired
	private ExtensionPointLogDao extensionPointLogDao;

	@Autowired
	private ExtensionDao extensionDao;

	@Override
	public ResDTO add(ReqExtensionPointDTO reqDTO) {
		//需要判断appCode与position不能重复
		judgeAppCodeAndPosition(reqDTO);
		ExtensionPointEntity entity = new ExtensionPointEntity();
		BeanUtils.copyProperties(reqDTO, entity);
		int flag = extensionPointDao.insert(entity);
		insertLog(flag, SysConstants.ADD, entity);
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO update(ReqExtensionPointDTO reqDTO) {
		//需要判断appCode与position不能重复
		judgeAppCodeAndPosition(reqDTO);
		ExtensionPointEntity entity = new ExtensionPointEntity();
		BeanUtils.copyProperties(reqDTO, entity);
		int flag = extensionPointDao.updateById(entity);
		insertLog(flag, SysConstants.UPDATE, entity);
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	/**
	 * 需要判断appCode与position不能重复
	 *
	 * @param reqDTO
	 */
	public void judgeAppCodeAndPosition(ReqExtensionPointDTO reqDTO) {
		if (!StringUtils.isEmpty(reqDTO.getAppCode())
				&& !StringUtils.isEmpty(reqDTO.getPosition())) {
			ExtensionPointEntity pointEntity = extensionPointDao.selectOne(new QueryWrapper<ExtensionPointEntity>()
					.eq(DBConstans.APP_CODE, reqDTO.getAppCode())
					.eq(DBConstans.POSITION, reqDTO.getPosition()));
			if (pointEntity != null && pointEntity.getId() != null) {
				throw new ApolloyException("新增扩展点失败,appCode与position已存在!");
			}
		}
	}

	@Override
	public ResDTO query(ReqExtensionPointQueryDTO reqDTO) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		//查询出总条数
		Long total = extensionPointDao.selectExtensionPointInfoCount(reqDTO.getAppCode());
		//查询出分页List
		if (total > 0) {
			List<ResExtensionPointQueryDTO> queryDTOS = extensionPointDao.selectExtensionPointInfo(
					reqDTO.getAppCode(),
					(reqDTO.getCurrentPage() - 1) * reqDTO.getRowsPerPage(),
					reqDTO.getCurrentPage() * reqDTO.getRowsPerPage());
			//添加分页信息
			//拆分ResExtensionPointQueryDTO为两个对象
			//把返回的dto封装回传
			for (ResExtensionPointQueryDTO queryDTO : queryDTOS) {
				Map<String, Object> sonResultMap = new HashMap<>();
				if (queryDTO.getExtensionId() != null) {
					sonResultMap.put(extensionInfo, new ExtensionEntity(
							queryDTO.getExtensionId(),
							queryDTO.getExtensionName(),
							queryDTO.getExtensionDescription(),
							queryDTO.getCategory(),
							queryDTO.getOwnerName(),
							queryDTO.getOwnerEmail(),
							queryDTO.getDevEntryUrl(),
							queryDTO.getTestEntryUrl(),
							queryDTO.getProdEntryUrl()));
				} else {
					sonResultMap.put(extensionInfo, null);
				}
				sonResultMap.put(extensionPointInfo, new ExtensionPointEntity(
						queryDTO.getId(),
						queryDTO.getPosition(),
						queryDTO.getAppCode(),
						queryDTO.getExtensionId(),
						queryDTO.getExtensionPointDescription(),
						queryDTO.getPositionImageUrl()));
				resultList.add(sonResultMap);
			}
		}
		Map<String, Object> resultMap = ResultMapUtil.getResultMap(total, reqDTO.getCurrentPage(), reqDTO.getRowsPerPage());
		resultMap.put(list, resultList);
		return ResultJsonManager.getResultJson().returnDataSuccessJson(resultMap);
	}

	@Override
	public ResDTO delete(Integer extensionPointId) {
		int flag = extensionPointDao.deleteById(extensionPointId);
		//插入日志
		insertLog(flag, SysConstants.DELETE, new ExtensionPointEntity(extensionPointId));
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO get(Integer extensionPointId) {
		//查询扩展点实体
		ExtensionPointEntity extensionPointEntity = extensionPointDao.selectById(extensionPointId);
		//通过扩展ID查询扩展实体
		ExtensionEntity extensionEntity = new ExtensionEntity();
		if (extensionPointEntity.getExtensionId() != null) {
			extensionEntity = extensionDao.selectById(extensionPointEntity.getExtensionId());
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(extensionInfo, extensionEntity);
		resultMap.put(extensionPointInfo, extensionPointEntity);
		return ResultJsonManager.getResultJson().returnDataSuccessJson(resultMap);
	}

	@Override
	public ResDTO queryByApp(ReqExtensionPointQueryDTO reqDTO) {
		return query(reqDTO);
	}

	@Override
	public void deleteByExtPointInfo(ReqExtensionPointDTO reqDTO) {
		ExtensionPointEntity entity = new ExtensionPointEntity();

		List<ExtensionPointEntity> extPoints = queryExtPointsByExtPointInfo(entity);
		if (extPoints.size() > 0) {
			List<Integer> extPointIds = extPoints.stream().map(ExtensionPointEntity::getId).collect(Collectors.toList());
			int flag = extensionPointDao.deleteBatchIds(extPointIds);
			//  插入日志,循环插入
			extPoints.stream().forEach(extPoint -> insertLog(flag, SysConstants.DELETE, entity));
		}
	}

	@Override
	public List<ExtensionPointEntity> queryExtPointsByExtPointInfo(ExtensionPointEntity entity) {
		QueryWrapper<ExtensionPointEntity> queryWrapper = new QueryWrapper<>();
		if (entity.getAppCode() != null)
			queryWrapper.eq(DBConstans.APP_CODE, entity.getAppCode());
		if (entity.getExtensionId() != null)
			queryWrapper.eq(DBConstans.EXTENSION_ID, entity.getExtensionId());
		List<ExtensionPointEntity> extPoints = extensionPointDao.selectList(queryWrapper);
		return extPoints;
	}

	/**
	 * 插入日志
	 *
	 * @param flag
	 * @param operationType
	 * @param entity
	 */
	@Async("insertLogExecutor")
	public void insertLog(int flag, String operationType, ExtensionPointEntity entity) {
		if (flag > 0 && !ObjectUtils.isEmpty(entity)) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
			ExtensionPointLogEntity logEntity = new ExtensionPointLogEntity();
			BeanUtils.copyProperties(entity, logEntity);
			BeanUtils.copyProperties(new LogEntity(operationType, stackTraceElement, null), logEntity);
			logEntity.setExtensionPointId(entity.getId());
			extensionPointLogDao.insert(logEntity);
		}
		logger.info("操作成功 entity = {},操作类型 operationType = {},操作条数 = {} ", entity.toString(), operationType, flag);
	}
}
