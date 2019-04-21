package com.pxyph.dao.provider;

import com.pxyph.model.LogInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxyph.util.common.ADConstants.LOGINFO;


/**
 * @version V1.0
 * @Description: 系统日志动态SQL语句提供类
 */
public class LogInfoDynaSqlProvider {
    // 分页动态查询
    public String selectLogInfoByKeys(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(LOGINFO);
                if (params.get("logInfo") != null) {
                    LogInfo logInfo = (LogInfo) params.get("logInfo");
                    if (logInfo.getUsername() != null && !logInfo.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT ('%',#{logInfo.username},'%')");
                    }
                    if (logInfo.getEvent_type() != null && !logInfo.getEvent_type().equals("")) {
                        WHERE("  event_type LIKE CONCAT ('%',#{logInfo.event_type},'%') ");
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
                FROM(LOGINFO);
                if (params.get("logInfo") != null) {
                    LogInfo logInfo = (LogInfo) params.get("logInfo");
                    if (logInfo.getUsername() != null && !logInfo.getUsername().equals("")) {
                        WHERE(" username LIKE CONCAT ('%',#{logInfo.username},'%')");
                    }
                    if (logInfo.getEvent_type() != null && !logInfo.getEvent_type().equals("")) {
                        WHERE("  event_type LIKE CONCAT ('%',#{logInfo.event_type},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertLogInfo(final LogInfo logInfo) {

        return new SQL() {
            {
                INSERT_INTO(LOGINFO);
                if (logInfo.getEvent_type() != null && !logInfo.getEvent_type().equals("")) {
                    VALUES("event_type", "#{event_type}");
                }
                if (logInfo.getCreate_time() !=null) {
                    VALUES("create_time", "#{create_time}");
                }
                if (logInfo.getLog_document() != null && !logInfo.getLog_document().equals("")) {
                    VALUES("log_document", "#{log_document}");
                }
                if (logInfo.getUsername() != null && !logInfo.getUsername().equals("")) {
                    VALUES("username", "#{username}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateLogInfo(final LogInfo logInfo) {

        return new SQL() {
            {
                UPDATE(LOGINFO);
                if (logInfo.getEvent_type() != null) {
                    SET(" event_type = #{event_type} ");
                }
                if (logInfo.getCreate_time() != null) {
                    SET(" create_time = #{create_time} ");
                }
                if (logInfo.getLog_document() != null) {
                    SET(" log_document = #{log_document} ");
                }
                if (logInfo.getUsername() != null) {
                    SET(" username = #{username} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }


}
