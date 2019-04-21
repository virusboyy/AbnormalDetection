package com.pxyph.service;

import com.pxyph.model.*;
import com.pxyph.util.tag.PageModel;

import java.util.List;

/**   
 * @Description: 行人异常行为检测系统服务层接口
 * @version V1.0   
 */
public interface ADService {




	/*****************用户服务接口*************************************/
	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname, String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUsersByKeys(User user, PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param user 用户对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param user 用户对象
	 * */
	void addUser(User user);


	/*****************系统配置接口*************************************/

	/**
	 * 根据id查询默认设置
	 * @param id
	 * @return
	 */
	DefaultSetting findDefaultSettingById(Integer id);

	/**
	 * 根据配置信息分页查找配置信息
	 * @param sysSetting
	 * @param pageModel
	 * @return
	 */
	List<SysSetting> findSysSettingByKeys(SysSetting sysSetting,PageModel pageModel);


	/**
	 * 根据id删除配置信息
	 * @param id
	 * */
	public void removeSysSettingById(Integer id);


	/**
	 * 添加配置信息
	 * @param sysSetting 配置对象
	 * */
	void addSysSetting(SysSetting sysSetting);

	/**
	 * 根据id查询配置信息
	 * @param id
	 * @return 配置对象
	 * */
	SysSetting findSysSettingById(Integer id);

	/**
	 * 修改配置信息
	 * @param sysSetting 对象
	 * */
	void modifySysSetting(SysSetting sysSetting);



    /*****************系统日志接口*************************************/


    /**
     * 获得所有日志信息，分页查询
     * @return logInfo对象的List集合
     * */
    List<LogInfo> findLogInfoByKeys(LogInfo logInfo, PageModel pageModel);

    /**
     * 获得所有日志信息
     * @return logInfo对象的List集合
     * */
    List<LogInfo> findAllLogInfoByUsername(String username);

    /**
     * 根据id删除日志信息
     * @param id
     * */
    public void removeLogInfoById(Integer id);

    /**
     * 添加日志信息
     * @param logInfo 日志对象
     * */
    void addLogInfo(LogInfo logInfo);

    /**
     * 根据id查询日志信息
     * @param id
     * @return logInfo对象
     * */
    LogInfo findLogInfoById(Integer id);

    /**
     * 修改日志信息
     * @param logInfo 对象
     * */
    void modifyLogInfo(LogInfo logInfo);


	/******************************存储管理接口实现*************************************/

	/**
	 * 获得所有存储管理信息，分页查询
	 * @return storageManager对象的List集合
	 * */
	List<StorageManager> findStorageInfoByKeys(StorageManager storageManager, PageModel pageModel);

	/**
	 * 根据id删除存储信息
	 * @param id
	 * */
	void removeStorageInfoById(Integer id);


	/**
	 * 添加存储信息
	 * @param storageManager 存储管理对象
	 * */
	void addStorageInfo(StorageManager storageManager);

	/**
	 * 根据id查询存储信息
	 * @param id
	 * @return 存储管理对象
	 * */
	StorageManager findStorageInfoById(Integer id);

	/**
	 * 修改存储管理信息
	 * @param storageManager 对象
	 * */
	void modifyStorageInfo(StorageManager storageManager);

	/**
	 * 根据id获取视频信息
	 * @param id
	 * @return
	 */
	VideoInfo findVideoInfoById(Integer id);

	/**
	 * 根据用户名查找视频信息
	 * @param file_name
	 * @return
	 */
	VideoInfo findVideoInfoByFileName(String file_name);

	/******************************异常数据检索接口实现*************************************/
	/**
	 * 获得所有信息，分页查询
	 * @return abnormalInfo对象的List集合
	 * */
	List<AbnormalInfo> findAbnormalInfoByKeys(AbnormalInfo abnormalInfo, PageModel pageModel);

	/**
	 * 根据id删除
	 * @param id
	 * */
	void removeAbnormalInfoById(Integer id);


	/**
	 * 添加信息
	 * @param abnormalInfo 存储管理对象
	 * */
	void addAbnormalInfo(AbnormalInfo abnormalInfo);

	/**
	 * 根据id查询信息
	 * @param id
	 * @return 对象
	 * */
	AbnormalInfo findAbnormalInfoById(Integer id);

	/**
	 * 修改信息
	 * @param abnormalInfo 对象
	 * */
	void modifyAbnormalInfo(AbnormalInfo abnormalInfo);

}
