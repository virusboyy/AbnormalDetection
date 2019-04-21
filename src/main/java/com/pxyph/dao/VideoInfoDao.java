package com.pxyph.dao;

import com.pxyph.model.VideoInfo;
import org.apache.ibatis.annotations.Select;

import static com.pxyph.util.common.ADConstants.VIDEOINFO;

/**
 * DefaultSettingMapper接口
 */
public interface VideoInfoDao {

    //根据id查询默认配置信息
    @Select("select * from "+VIDEOINFO+" where id = #{id}")
    VideoInfo selectById(Integer id);

    //根据用户名查询默认配置信息
    @Select("select * from "+VIDEOINFO+" where file_name = #{file_name}")
    VideoInfo selectByFileName(String file_name);
}
