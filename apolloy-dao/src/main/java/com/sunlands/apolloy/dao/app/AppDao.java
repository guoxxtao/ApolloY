package com.sunlands.apolloy.dao.app;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunlands.apolloy.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 16:45
 */
@Mapper
public interface AppDao extends BaseMapper<AppEntity> {

	@Select({
			"select count(1) from t_app where app_code = #{appCode,jdbcType = VARCHAR} and delete_flag = 0 "
	})
	int selectCountByAppCode(@Param("appCode") String appCode);

	@Select({
			"select category from t_app where delete_flag = 0 "
	})
	Set<String> getAllCategory();
}
