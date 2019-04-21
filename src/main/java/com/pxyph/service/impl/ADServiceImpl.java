package com.pxyph.service.impl;

import com.pxyph.dao.*;
import com.pxyph.model.*;
import com.pxyph.service.ADService;
import com.pxyph.util.tag.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description: 行人异常行为检测系统服务层接口实现类
 * @version V1.0   
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("adService")
public class ADServiceImpl implements ADService {

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Resource
	private UserDao userDao;

	@Resource
	private DefaultSettingDao defaultSettingDao;

	@Resource
	private SysSettingDao sysSettingDao;

	@Resource
	private LogInfoDao logInfoDao;

	@Resource
	private StorageInfoDao storageInfoDao;

	@Resource
	private VideoInfoDao videoInfoDao;

	@Resource
	private AbnormalInfoDao abnormalInfoDao;



	/*****************用户服务接口实现*************************************/
	/**
	 * adServiceImpl接口login方法实现
	 *  @see { ADService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("ADServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	/**
	 * adServiceImpl接口findUser方法实现
	 * @see { ADService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUsersByKeys(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<User> users = userDao.selectUsersByKeys(params);
		 
		return users;
	}
	
	/**
	 * adServiceImpl接口findUserById方法实现
	 * @see { ADService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * adServiceImpl接口removeUserById方法实现
	 * @see { ADService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
		
	}
	
	/**
	 * adServiceImpl接口addUser方法实现
	 * @see { ADService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
		
	}
	
	/**
	 * adServiceImpl接口modifyUser方法实现
	 * @see { ADService }
	 * */
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}


	/*******************************系统配置接口实现*************************************/

	/**
	 * 根据id查找默认设置
	 * @param id
	 * @return
	 */
	@Override
	public DefaultSetting findDefaultSettingById(Integer id) {
		DefaultSetting defaultSetting = defaultSettingDao.selectById(id);
		return defaultSetting;
	}

	@Transactional(readOnly=true)
	@Override
	public List<SysSetting> findSysSettingByKeys(SysSetting sysSetting,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("sysSetting",sysSetting);
		int recordCount = sysSettingDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<SysSetting> sysSettings = sysSettingDao.selectSysSettingByKeys(params);

		return sysSettings;
	}

	@Transactional(readOnly=true)
	@Override
	public void removeSysSettingById(Integer id) {
		sysSettingDao.deleteById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public void addSysSetting(SysSetting sysSetting) {
		sysSettingDao.save(sysSetting);
	}

    @Transactional(readOnly = true)
	@Override
	public SysSetting findSysSettingById(Integer id) {
		return sysSettingDao.selectById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public void modifySysSetting(SysSetting sysSetting) {
		sysSettingDao.update(sysSetting);
	}




	/*****************************日志信息接口实现*************************************/

	@Transactional(readOnly = true)
    @Override
    public List<LogInfo> findLogInfoByKeys(LogInfo logInfo, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("logInfo", logInfo);
        int recordCount = logInfoDao.count(params);
        pageModel.setRecordCount(recordCount);

        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }

        List<LogInfo> logInfos = logInfoDao.selectLogInfoByKeys(params);
        return logInfos;
    }

    @Transactional(readOnly = true)
    @Override
    public List<LogInfo> findAllLogInfoByUsername(String username) {
        return logInfoDao.selectAllLogInfoByUsername(username);
    }

    @Override
    public void removeLogInfoById(Integer id) {
        logInfoDao.deleteById(id);
    }

    @Override
    public void addLogInfo(LogInfo logInfo) {
        logInfoDao.save(logInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public LogInfo findLogInfoById(Integer id) {
        return logInfoDao.selectById(id);
    }

    @Override
    public void modifyLogInfo(LogInfo logInfo) {
        logInfoDao.update(logInfo);
    }



	/******************************存储管理接口实现*************************************/
	@Override
	public List<StorageManager> findStorageInfoByKeys(StorageManager storageManager, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("storageManager", storageManager);
		int recordCount = storageInfoDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}

		List<StorageManager> storageManagers = storageInfoDao.selectStorageInfoByKeys(params);
		return storageManagers;
	}

	@Override
	public void removeStorageInfoById(Integer id) {
		storageInfoDao.deleteById(id);
	}

	@Override
	public void addStorageInfo(StorageManager storageManager) {
		storageInfoDao.save(storageManager);
	}

	@Override
	public StorageManager findStorageInfoById(Integer id) {
		return storageInfoDao.selectById(id);
	}

	@Override
	public void modifyStorageInfo(StorageManager storageManager) {
		storageInfoDao.update(storageManager);
	}

	@Transactional(readOnly = true)
	@Override
	public VideoInfo findVideoInfoById(Integer id) {
		return videoInfoDao.selectById(id);
	}
	@Transactional(readOnly = true)
	@Override
	public VideoInfo findVideoInfoByFileName(String file_name) {
		return videoInfoDao.selectByFileName(file_name);
	}


	/******************************异常数据检索接口实现*************************************/

	@Override
	public List<AbnormalInfo> findAbnormalInfoByKeys(AbnormalInfo abnormalInfo, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("abnormalInfo", abnormalInfo);
		int recordCount = abnormalInfoDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}

		List<AbnormalInfo> abnormalInfos = abnormalInfoDao.selectAbnormalInfoByKeys(params);
		return abnormalInfos;
	}

	@Override
	public void removeAbnormalInfoById(Integer id) {
		abnormalInfoDao.deleteById(id);
	}

	@Override
	public void addAbnormalInfo(AbnormalInfo abnormalInfo) {
		abnormalInfoDao.save(abnormalInfo);
	}

	@Override
	public AbnormalInfo findAbnormalInfoById(Integer id) {
		return abnormalInfoDao.selectById(id);
	}

	@Override
	public void modifyAbnormalInfo(AbnormalInfo abnormalInfo) {
		abnormalInfoDao.update(abnormalInfo);
	}





}
