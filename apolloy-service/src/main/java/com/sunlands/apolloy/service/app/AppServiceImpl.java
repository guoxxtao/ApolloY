package com.sunlands.apolloy.service.app;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunlands.apolloy.baseResultJson.ResultJsonManager;
import com.sunlands.apolloy.constans.DBConstans;
import com.sunlands.apolloy.constans.SysConstants;
import com.sunlands.apolloy.dao.app.AppDao;
import com.sunlands.apolloy.dao.app.AppLogDao;
import com.sunlands.apolloy.dao.extension.ExtensionDao;
import com.sunlands.apolloy.dto.ReqAppDTO;
import com.sunlands.apolloy.dto.ReqAppQueryDTO;
import com.sunlands.apolloy.dto.ReqExtensionPointDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.entity.AppEntity;
import com.sunlands.apolloy.entity.AppLogEntity;
import com.sunlands.apolloy.entity.LogEntity;
import com.sunlands.apolloy.exception.ApolloyException;
import com.sunlands.apolloy.service.extensionPoint.ExtensionPointService;
import com.sunlands.apolloy.util.ResultMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 11:31
 */
@Service
public class AppServiceImpl implements AppService {

	private static final Logger logger = LoggerFactory.getLogger(AppService.class);


	//与前端协商的常量字段
	public static final String list = "list";
	public static final Integer appCodeCategory = 0;
	public static final Integer extensionCodeCategory = 1;

	@Autowired
	private AppDao appDao;

	@Autowired
	private ExtensionDao extensionDao;

	@Autowired
	private AppLogDao appLogDao;

	@Autowired
	private ExtensionPointService extensionPointService;

	@Override
	public ResDTO add(ReqAppDTO reqAppDTO) {
		AppEntity appEntity = new AppEntity();
		String message = null;
		//注意在spring包中是A赋值给B 在Apache中是B赋值给A
		BeanUtils.copyProperties(reqAppDTO, appEntity);
		if (appDao.selectCountByAppCode(appEntity.getAppCode()) > 0) {
			throw new ApolloyException("appCode已存在,请改名后重新提交");
		}
		int flag = appDao.insert(appEntity);
		// 记录日志
		insertLog(flag, SysConstants.ADD, appEntity);
		return ResultJsonManager.getResultJson().returnSuccessJson(message);
	}

	@Override
	public ResDTO update(ReqAppDTO reqAppDTO) {
		AppEntity appEntity = new AppEntity();
		BeanUtils.copyProperties(reqAppDTO, appEntity);
		int flag = appDao.update(appEntity, new UpdateWrapper<AppEntity>()
				.eq(DBConstans.APP_CODE, appEntity.getAppCode()));
		// 记录日志
		insertLog(flag, SysConstants.UPDATE, appEntity);
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO query(ReqAppQueryDTO reqAppQueryDTO) {
		Page<AppEntity> page = new Page<>(reqAppQueryDTO.getCurrentPage(), reqAppQueryDTO.getRowsPerPage());
		QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(reqAppQueryDTO.getAppCode()))
			queryWrapper.eq(DBConstans.APP_CODE, reqAppQueryDTO.getAppCode());
		if (!StringUtils.isEmpty(reqAppQueryDTO.getAppName()))
			queryWrapper.eq(DBConstans.APP_NAME, reqAppQueryDTO.getAppName());
		if (!StringUtils.isEmpty(reqAppQueryDTO.getOwnerName()))
			queryWrapper.eq(DBConstans.OWNER_NAME, reqAppQueryDTO.getOwnerName());
		if (!StringUtils.isEmpty(reqAppQueryDTO.getOwnerEmail()))
			queryWrapper.eq(DBConstans.OWNER_EMAIL, reqAppQueryDTO.getOwnerEmail());
		if (null != reqAppQueryDTO.getCategory() && reqAppQueryDTO.getCategory().size() > 0) {
			queryWrapper.in(DBConstans.CATEGORY, reqAppQueryDTO.getCategory());
		}
		queryWrapper.orderByDesc(DBConstans.CREATE_TIME);

		IPage<AppEntity> entityIPage = appDao.selectPage(page, queryWrapper);
		//自动添加分页信息
		Map<String, Object> resultMap = ResultMapUtil.getResultMap(entityIPage);
		resultMap.put(list, entityIPage.getRecords());
		return ResultJsonManager.getResultJson().returnDataSuccessJson(resultMap);
	}

	@Override
	@Transactional
	public ResDTO delete(String appCode) {
		int flag = appDao.delete(new UpdateWrapper<AppEntity>().eq(DBConstans.APP_CODE, appCode));
		// 记录日志
		insertLog(flag, SysConstants.DELETE, new AppEntity(appCode));
		//需要删除对应APPCODE的所有扩展点
		extensionPointService.deleteByExtPointInfo(new ReqExtensionPointDTO(appCode,null));
		return ResultJsonManager.getResultJson().returnSuccessJson();
	}

	@Override
	public ResDTO get(String appCode) {
		AppEntity appEntity = appDao.selectOne(new QueryWrapper<AppEntity>().eq(DBConstans.APP_CODE, appCode));
		if (appEntity == null) {
			appEntity = new AppEntity();
		}
		return ResultJsonManager.getResultJson().returnDataSuccessJson(appEntity);
	}

	@Override
	public ResDTO getAllCategory(Integer categoryType) {
		Set<String> allCategorys;
		if (categoryType == appCodeCategory) {
			allCategorys = appDao.getAllCategory();
		} else if (categoryType == extensionCodeCategory) {
			allCategorys = extensionDao.getAllCategory();
		} else {
			throw new ApolloyException("没有该分类类型");
		}
		Map<String, Object> resultMap = new HashMap();
		resultMap.put(list, allCategorys);
		return ResultJsonManager.getResultJson().returnDataSuccessJson(resultMap);
	}

	/**
	 * 插入日志
	 *
	 * @param flag
	 * @param operationType
	 * @param entity
	 */
	@Async("insertLogExecutor")
	public void insertLog(int flag, String operationType, AppEntity entity) {
		//执行成功才插入日志
		if (flag > 0) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
			AppLogEntity logEntity = new AppLogEntity();
			BeanUtils.copyProperties(entity, logEntity);
			BeanUtils.copyProperties(new LogEntity(operationType, stackTraceElement, null), logEntity);
			appLogDao.insert(logEntity);
		}
		logger.info("操作成功 entity = {},操作类型 operationType = {},操作条数 = {} ", entity.toString(), operationType, flag);
	}
}
