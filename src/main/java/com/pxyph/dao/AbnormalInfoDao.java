package com.pxyph.dao;

import com.pxyph.dao.provider.AbnormalInfoDynaSqlProvider;
import com.pxyph.model.AbnormalInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.pxyph.util.common.ADConstants.ABNORMALINFO;

/**
 * 系统异常检测Mapper接口
 */
public interface AbnormalInfoDao {

    //查询信息
    @SelectProvider(type = AbnormalInfoDynaSqlProvider.class,method = "selectAbnormalInfoByKeys")
    List<AbnormalInfo> selectAbnormalInfoByKeys(Map<String, Object> params);

    //条件查询总数量
    @SelectProvider(type=AbnormalInfoDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);


    //根据id查询
    @Select("select * from "+ABNORMALINFO+" where ID = #{id}")
    AbnormalInfo selectById(int id);

    // 根据id删除
    @Delete(" delete from "+ABNORMALINFO+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入信息
    @SelectProvider(type=AbnormalInfoDynaSqlProvider.class,method="insertAbnormalInfo")
    void save(AbnormalInfo abnormalInfo);

    // 动态修改信息
    @SelectProvider(type=AbnormalInfoDynaSqlProvider.class,method="updateAbnormalInfo")
    void update(AbnormalInfo abnormalInfo);


}
