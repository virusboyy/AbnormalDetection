package com.pxyph.dao.provider;

import com.pxyph.model.StorageManager;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxyph.util.common.ADConstants.STORAGEMANAGER;


/**
 * @version V1.0
 * @Description: 系统存储管理动态SQL语句提供类
 */
public class StorageInfoDynaSqlProvider {
    // 分页动态查询
    public String selectStorageInfoByKeys(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(STORAGEMANAGER);
                if (params.get("storageManager") != null) {
                    StorageManager storageManager = (StorageManager) params.get("storageManager");
                    if (storageManager.getUsername() != null && !"".equals(storageManager.getUsername())) {
                        WHERE(" username LIKE CONCAT ('%',#{storageManager.username},'%')");
                    }
                    if (storageManager.getFile_name() != null && !"".equals(storageManager.getFile_name())) {
                        WHERE("  file_name LIKE CONCAT ('%',#{storageManager.file_name},'%') ");
                    }
                }
                ORDER_BY("id");
            }
        }.toString()+" DESC";

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        return sql;
    }

    // 动态查询总数量
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(STORAGEMANAGER);
                if (params.get("storageManager") != null) {
                    StorageManager storageManager = (StorageManager) params.get("storageManager");
                    if (storageManager.getUsername() != null && !"".equals(storageManager.getUsername())) {
                        WHERE(" username LIKE CONCAT ('%',#{storageManager.username},'%')");
                    }
                    if (storageManager.getFile_name() != null && !"".equals(storageManager.getFile_name())) {
                        WHERE("  file_name LIKE CONCAT ('%',#{storageManager.file_name},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertStorageInfo(final StorageManager storageManager) {

        return new SQL() {
            {
                INSERT_INTO(STORAGEMANAGER);
                if (storageManager.getVideo_id() != null && !"".equals(storageManager.getVideo_id())) {
                    VALUES("video_id", "#{video_id}");
                }
                if (storageManager.getFile_name() != null && !"".equals(storageManager.getFile_name())) {
                    VALUES("file_name", "#{file_name}");
                }
                VALUES("file_size","#{file_size}");
                VALUES("video_width","#{video_width}");
                VALUES("video_height","#{video_height}");
                if (storageManager.getCreate_time() !=null&&!"".equals(storageManager.getCreate_time())) {
                    VALUES("create_time", "#{create_time}");
                }
                VALUES("frame_num","#{frame_num}");
                VALUES("fps","#{fps}");
                if (storageManager.getUsername() != null && !"".equals(storageManager.getUsername())) {
                    VALUES("username", "#{username}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateStorageInfo(final StorageManager storageManager) {

        return new SQL() {
            {
                UPDATE(STORAGEMANAGER);
                if (storageManager.getFile_size() != null) {
                    SET(" file_size = #{file_size} ");
                }
                if (storageManager.getVideo_width() != null) {
                    SET(" video_width = #{video_width} ");
                }
                if (storageManager.getVideo_height() != null) {
                    SET(" video_height = #{video_height} ");
                }
                if (storageManager.getCreate_time() != null&&!"".equals(storageManager.getCreate_time())) {
                    SET(" create_time = #{create_time} ");
                }
                if (storageManager.getFrame_num() != null) {
                    SET(" frame_num = #{frame_num} ");
                }
                if (storageManager.getFps() != null) {
                    SET(" fps = #{fps} ");
                }
                if (storageManager.getUsername() != null&&!"".equals(storageManager.getUsername())) {
                    SET(" username = #{username} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }


}
