package com.pxyph.dao;

import com.pxyph.model.VideoInfo;
import org.apache.ibatis.annotations.Select;

import static com.pxyph.util.common.ADConstants.VIDEOINFO;

/**
 * 视频信息Mapper接口
 */
public interface VideoInfoDao {

    //根据id查询视频信息
    @Select("select * from "+VIDEOINFO+" where id = #{id}")
    VideoInfo selectById(Integer id);

    //根据video_id查询视频信息
    @Select("select * from "+VIDEOINFO+" where video_id = #{video_id}")
    VideoInfo selectByVideoId(String video_id);

    //根据用户名查询视频信息
    @Select("select * from "+VIDEOINFO+" where file_name = #{file_name}")
    VideoInfo selectByFileName(String file_name);
}
