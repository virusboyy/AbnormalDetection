package com.pxyph.dao;

import com.pxyph.model.DefaultSetting;
import org.apache.ibatis.annotations.Select;

import static com.pxyph.util.common.ADConstants.DEFAULTSETTING;

/**
 * DefaultSettingMapper接口
 */
public interface DefaultSettingDao {

    //根据id查询默认配置信息
    @Select("select * from "+DEFAULTSETTING+" where ID = #{id}")
    DefaultSetting selectById(Integer id);
}
