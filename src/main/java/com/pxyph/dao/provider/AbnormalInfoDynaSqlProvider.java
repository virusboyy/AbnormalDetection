package com.pxyph.dao.provider;

import com.pxyph.model.AbnormalInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxyph.util.common.ADConstants.ABNORMALINFO;


/**
 * @version V1.0
 * @Description: 系统异常数据检索动态SQL语句提供类
 */
public class AbnormalInfoDynaSqlProvider {
    // 分页动态查询
    public String selectAbnormalInfoByKeys(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(ABNORMALINFO);
                if (params.get("abnormalInfo") != null) {
                    AbnormalInfo abnormalInfo = (AbnormalInfo) params.get("abnormalInfo");
                    if (abnormalInfo.getEvent_type() != null && !abnormalInfo.getEvent_type().equals("")) {
                        WHERE(" event_type LIKE CONCAT ('%',#{abnormalInfo.event_type},'%')");
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
                FROM(ABNORMALINFO);
                if (params.get("abnormalInfo") != null) {
                    AbnormalInfo abnormalInfo = (AbnormalInfo) params.get("abnormalInfo");
                    if (abnormalInfo.getEvent_type() != null && !abnormalInfo.getEvent_type().equals("")) {
                        WHERE(" event_type LIKE CONCAT ('%',#{abnormalInfo.event_type},'%')");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertAbnormalInfo(final AbnormalInfo abnormalInfo) {

        return new SQL() {
            {
                INSERT_INTO(ABNORMALINFO);
                if (abnormalInfo.getEvent_type() !=null&& !abnormalInfo.getEvent_type().equals("")) {
                    VALUES("event_type", "#{event_type}");
                }
                if (abnormalInfo.getCreate_time() !=null) {
                    VALUES("create_time", "#{create_time}");
                }
                if (abnormalInfo.getAnomaly_document() !=null&& !abnormalInfo.getAnomaly_document().equals("")) {
                    VALUES("anomaly_document", "#{anomaly_document}");
                }
                if (abnormalInfo.getStart_time() !=null) {
                    VALUES("start_time", "#{start_time}");
                }
                if (abnormalInfo.getEnd_time() !=null) {
                    VALUES("end_time", "#{end_time}");
                }
                if (abnormalInfo.getVideo_id() != null && !abnormalInfo.getVideo_id().equals("")) {
                    VALUES("video_id", "#{video_id}");
                }
                if (abnormalInfo.getVideo_name() != null && !abnormalInfo.getVideo_name().equals("")) {
                    VALUES("video_name", "#{video_name}");
                }
                if (abnormalInfo.getVideo_path() != null && !abnormalInfo.getVideo_path().equals("")) {
                    VALUES("video_path", "#{video_path}");
                }
                if (abnormalInfo.getUsername() != null && !abnormalInfo.getUsername().equals("")) {
                    VALUES("username", "#{username}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateAbnormalInfo(final AbnormalInfo abnormalInfo) {

        return new SQL() {
            {
                UPDATE(ABNORMALINFO);
                if (abnormalInfo.getEvent_type() != null) {
                    SET(" event_type = #{event_type} ");
                }
                if (abnormalInfo.getCreate_time() != null) {
                    SET(" create_time = #{create_time} ");
                }
                if (abnormalInfo.getAnomaly_document() != null) {
                    SET(" anomaly_document = #{anomaly_document} ");
                }
                if (abnormalInfo.getStart_time() != null) {
                    SET(" start_time = #{start_time} ");
                }
                if (abnormalInfo.getEnd_time() != null) {
                    SET(" end_time = #{end_time} ");
                }
                if (abnormalInfo.getUsername() != null) {
                    SET(" username = #{username} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }


}
