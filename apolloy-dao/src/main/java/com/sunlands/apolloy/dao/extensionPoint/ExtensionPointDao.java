package com.sunlands.apolloy.dao.extensionPoint;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunlands.apolloy.dto.ResExtensionPointQueryDTO;
import com.sunlands.apolloy.entity.ExtensionPointEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/27
 * Time: 14:48
 */
public interface ExtensionPointDao extends BaseMapper<ExtensionPointEntity> {

	@Select({
			"select tep.id id,tep.extension_id extensionId," +
					"tep.app_code appCode,tep.position position," +
					"tep.description extensionPointDescription,tep.position_image_url positionImageUrl," +
					"te.extension_name extensionName,te.description extensionDescription," +
					"te.category category,te.owner_name ownerName," +
					"te.owner_email ownerEmail,te.dev_entry_url devEntryUrl," +
					"te.test_entry_url testEntryUrl,te.prod_entry_url prodEntryUrl " +
					"from t_extension_point tep " +
					"left join t_extension te " +
					"on te.id = tep.extension_id  and te.delete_flag = 0  " +
					"where " +
					"tep.app_code =#{appCode,jdbcType = VARCHAR} " +
					"and tep.delete_flag = 0 " +
					"order by tep.create_time desc "+
					"LIMIT #{currentPage,jdbcType = BIGINT},#{rowsPerPage,jdbcType = BIGINT} "
	})
	@ResultType(ResExtensionPointQueryDTO.class)
	List<ResExtensionPointQueryDTO> selectExtensionPointInfo(
			@Param("appCode") String appCode,
			@Param("currentPage") Long currentPage,
			@Param("rowsPerPage") Long rowsPerPage);

	@Select({
			"select count(1) " +
					"from t_extension_point tep " +
					"where " +
					"tep.app_code =#{appCode,jdbcType = VARCHAR} " +
					"and tep.delete_flag = 0 "
	})
	@ResultType(ResExtensionPointQueryDTO.class)
	Long selectExtensionPointInfoCount(
			@Param("appCode") String appCode);

}
