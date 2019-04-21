package com.pxyph.dao;

import com.pxyph.dao.provider.LogInfoDynaSqlProvider;
import com.pxyph.model.LogInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.pxyph.util.common.ADConstants.LOGINFO;

public interface LogInfoDao {

    // 动态查询
    @SelectProvider(type=LogInfoDynaSqlProvider.class,method="selectLogInfoByKeys")
    List<LogInfo> selectLogInfoByKeys(Map<String, Object> params);

    @SelectProvider(type=LogInfoDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    //查询所有系统配置
    @Select("select * from "+LOGINFO+" "+"where username = #{username}"+"ORDER BY id DESC")
    List<LogInfo> selectAllLogInfoByUsername(String username);

    //根据id查询系统配置
    @Select("select * from "+LOGINFO+" where id = #{id}")
    LogInfo selectById(int id);

    // 根据id删除系统配置
    @Delete(" delete from "+LOGINFO+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入配置信息
    @SelectProvider(type=LogInfoDynaSqlProvider.class,method="insertLogInfo")
    void save(LogInfo logInfo);

    // 动态修改配置信息
    @SelectProvider(type=LogInfoDynaSqlProvider.class,method="updateLogInfo")
    void update(LogInfo logInfo);
}
