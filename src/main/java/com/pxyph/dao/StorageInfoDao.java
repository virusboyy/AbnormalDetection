package com.pxyph.dao;

import com.pxyph.dao.provider.StorageInfoDynaSqlProvider;
import com.pxyph.model.StorageManager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.pxyph.util.common.ADConstants.STORAGEMANAGER;

/**
 * 系统配置Mapper接口
 */
public interface StorageInfoDao {

    //查询配置信息
    @SelectProvider(type = StorageInfoDynaSqlProvider.class,method = "selectStorageInfoByKeys")
    List<StorageManager> selectStorageInfoByKeys(Map<String, Object> params);

    //条件查询总数量
    @SelectProvider(type=StorageInfoDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);


    //根据id查询系统配置
    @Select("select * from "+STORAGEMANAGER+" where ID = #{id}")
    StorageManager selectById(int id);

    // 根据id删除系统配置
    @Delete(" delete from "+STORAGEMANAGER+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入配置信息
    @SelectProvider(type=StorageInfoDynaSqlProvider.class,method="insertStorageInfo")
    void save(StorageManager storageManager);

    // 动态修改配置信息
    @SelectProvider(type=StorageInfoDynaSqlProvider.class,method="updateStorageInfo")
    void update(StorageManager storageManager);


}
