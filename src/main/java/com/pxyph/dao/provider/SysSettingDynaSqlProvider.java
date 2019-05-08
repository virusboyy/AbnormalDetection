package com.pxyph.dao.provider;

import com.pxyph.model.SysSetting;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxyph.util.common.ADConstants.SYSSETTING;


/**
 * @version V1.0
 * @Description: 系统配置动态SQL语句提供类
 */
public class SysSettingDynaSqlProvider {

    // 动态插入
    public String insertSysSetting(final SysSetting sysSetting) {

        return new SQL() {
            {
                INSERT_INTO(SYSSETTING);
                if (sysSetting.getInput_type() != null && !"".equals(sysSetting.getInput_type())) {
                    VALUES("input_type", "#{input_type}");
                }
                if (sysSetting.getVideo_id() != null && !"".equals(sysSetting.getVideo_id())) {
                    VALUES("video_id", "#{video_id}");
                }
                if (sysSetting.getVideo() != null && !"".equals(sysSetting.getVideo())) {
                    VALUES("video", "#{video}");
                }
                if (sysSetting.getSave_path() != null && !"".equals(sysSetting.getSave_path())) {
                    VALUES("save_path", "#{save_path}");
                }
                if (sysSetting.getModel() != null && !"".equals(sysSetting.getModel())) {
                    VALUES("model", "#{model}");
                }
                if (sysSetting.getPlay_set() > 0) {
                    VALUES("play_set", "#{play_set}");
                }
                if (sysSetting.getUsername() != null && !"".equals(sysSetting.getUsername())) {
                    VALUES("username", "#{username}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateSysSetting(final SysSetting sysSetting) {

        return new SQL() {
            {
                UPDATE(SYSSETTING);
                if (sysSetting.getInput_type() != null&&!"".equals(sysSetting.getInput_type())) {
                    SET(" input_type = #{input_type} ");
                }
                if (sysSetting.getVideo_id() != null&&!"".equals(sysSetting.getVideo_id())) {
                    SET(" video_id = #{video_id} ");
                }
                if (sysSetting.getVideo() != null&&!"".equals(sysSetting.getVideo())) {
                    SET(" video = #{video} ");
                }
                if (sysSetting.getSave_path() != null&&!"".equals(sysSetting.getSave_path())) {
                    SET(" save_path = #{save_path} ");
                }
                if (sysSetting.getModel() != null&&!"".equals(sysSetting.getModel())) {
                    SET(" model = #{model} ");
                }
                if (sysSetting.getPlay_set() > 0) {
                    SET(" play_set = #{play_set} ");
                }
                if (sysSetting.getUsername() != null&&!"".equals(sysSetting.getUsername())) {
                    SET(" username=#{username} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

    // 分页动态查询
    public String selectSysSettings(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(SYSSETTING);
                if (params.get("sysSetting") != null) {
                    SysSetting sysSetting = (SysSetting) params.get("sysSetting");
                    if (sysSetting.getUsername() != null && !"".equals(sysSetting.getUsername())) {
                        WHERE(" username LIKE CONCAT ('%',#{sysSetting.username},'%')");
                    }
                    //if (sysSetting.getInput_type() != null && !sysSetting.getInput_type().equals("")) {
                    //    WHERE(" input_type LIKE CONCAT ('%',#{sysSetting.input_type},'%')");
                    //}
                }
                ORDER_BY("id");
            }
        }.toString() + " DESC";

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }

        return sql;
    }

    // 动态条件查询总数量
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(SYSSETTING);
                if (params.get("sysSetting") != null) {
                    SysSetting sysSetting = (SysSetting) params.get("sysSetting");
                    if (sysSetting.getUsername() != null && !"".equals(sysSetting.getUsername())) {
                        WHERE(" username LIKE CONCAT ('%',#{sysSetting.username},'%')");
                    }
                    //if (sysSetting.getInput_type() != null && !sysSetting.getInput_type().equals("")) {
                    //    WHERE(" input_type LIKE CONCAT ('%',#{sysSetting.input_type},'%')");
                    //}
                }
            }
        }.toString();
    }

}
