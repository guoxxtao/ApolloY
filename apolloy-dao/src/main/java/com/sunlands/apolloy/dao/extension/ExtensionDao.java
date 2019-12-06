package com.sunlands.apolloy.dao.extension;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunlands.apolloy.entity.ExtensionEntity;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/27
 * Time: 14:48
 */
public interface ExtensionDao extends BaseMapper<ExtensionEntity> {

	@Select({
			"select category from t_extension where delete_flag = 0 "
	})
	Set<String> getAllCategory();
}
