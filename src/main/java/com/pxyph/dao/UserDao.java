package com.pxyph.dao;


import com.pxyph.dao.provider.UserDynaSqlProvider;
import com.pxyph.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.pxyph.util.common.ADConstants.USERTABLE;

/**   
 * @Description: UserMapper接口
 * @version V1.0   
 */
public interface UserDao {

	// 根据登录名和密码查询员工
	@Select("select * from "+USERTABLE+" where loginname = #{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(
            @Param("loginname") String loginname,
            @Param("password") String password);
	
	// 根据id查询用户
	@Select("select * from "+USERTABLE+" where ID = #{id}")
	User selectById(Integer id);
	
	// 根据id删除用户
	@Delete(" delete from "+USERTABLE+" where id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);

	
	// 动态插入用户
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);

	//查询用户信息
	@SelectProvider(type = UserDynaSqlProvider.class,method = "selectUsersByKeys")
	List<User> selectUsersByKeys(Map<String, Object> params);

	//条件查询总数量
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
}
