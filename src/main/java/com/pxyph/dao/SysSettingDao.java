package com.pxyph.dao;

import com.pxyph.dao.provider.SysSettingDynaSqlProvider;
import com.pxyph.model.SysSetting;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.pxyph.util.common.ADConstants.SYSSETTING;

/**
 * 系统配置Mapper接口
 */
public interface SysSettingDao {

    //查询配置信息
    @SelectProvider(type = SysSettingDynaSqlProvider.class,method = "selectSysSettings")
    List<SysSetting> selectSysSettingByKeys(Map<String, Object> params);

    //条件查询总数量
    @SelectProvider(type=SysSettingDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);


    //根据id查询系统配置
    @Select("select * from "+SYSSETTING+" where ID = #{id}")
    SysSetting selectById(int id);

    // 根据id删除系统配置
    @Delete(" delete from "+SYSSETTING+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入配置信息
    @SelectProvider(type=SysSettingDynaSqlProvider.class,method="insertSysSetting")
    void save(SysSetting sysSetting);

    // 动态修改配置信息
    @SelectProvider(type=SysSettingDynaSqlProvider.class,method="updateSysSetting")
    void update(SysSetting sysSetting);


}
