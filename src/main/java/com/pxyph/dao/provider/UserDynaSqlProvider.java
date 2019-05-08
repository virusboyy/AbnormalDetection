package com.pxyph.dao.provider;

import com.pxyph.model.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.pxyph.util.common.ADConstants.USERTABLE;

/**
 * @version V1.0
 * @Description: 用户动态SQL语句提供类
 */
public class UserDynaSqlProvider {
    // 分页动态查询
    public String selectUsersByKeys(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUsername() != null && !"".equals(user.getUsername())) {
                        WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
                    }
                    if (user.getUserstatus() != null) {
                        WHERE(" userstatus LIKE CONCAT ('%',#{user.userstatus},'%') ");
                    }
                }
            }
        }.toString();

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
                FROM(USERTABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getUsername() != null && !"".equals(user.getUsername())) {
                        WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
                    }
                    if (user.getUserstatus() != null) {
                        WHERE(" userstatus LIKE CONCAT ('%',#{user.userstatus},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertUser(final User user) {
        return new SQL() {
            {
                INSERT_INTO(USERTABLE);
                if (user.getUsername() != null && !"".equals(user.getUsername())) {
                    VALUES("username", "#{username}");
                }
                if (user.getUserstatus() != null) {
                    VALUES("userstatus", "#{userstatus}");
                }
                if (user.getLoginname() != null && !"".equals(user.getLoginname())) {
                    VALUES("loginname", "#{loginname}");
                }
                if (user.getPassword() != null && !"".equals(user.getPassword())) {
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateUser(final User user) {

        return new SQL() {
            {
                UPDATE(USERTABLE);
                if (user.getUsername() != null && !"".equals(user.getUsername())) {
                    SET(" username = #{username} ");
                }
                if (user.getLoginname() != null && !"".equals(user.getLoginname())) {
                    SET(" loginname = #{loginname} ");
                }
                if (user.getPassword() != null && !"".equals(user.getPassword())) {
                    SET(" password = #{password} ");
                }
                if (user.getUserstatus() != null) {
                    SET(" userstatus = #{userstatus} ");
                }
                if (user.getCreateDate() != null) {
                    SET(" create_date = #{createDate} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
