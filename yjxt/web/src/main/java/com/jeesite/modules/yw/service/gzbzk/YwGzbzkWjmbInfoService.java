/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.service.gzbzk;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbInfo;
import com.jeesite.modules.yw.dao.gzbzk.YwGzbzkWjmbInfoDao;

/**
 * 规章标准库-文件模板Service
 * @author wy
 * @version 2020-06-11
 */
@Service
@Transactional(readOnly=true)
public class YwGzbzkWjmbInfoService extends CrudService<YwGzbzkWjmbInfoDao, YwGzbzkWjmbInfo> {
	
	/**
	 * 获取单条数据
	 * @param ywGzbzkWjmbInfo
	 * @return
	 */
	@Override
	public YwGzbzkWjmbInfo get(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		return super.get(ywGzbzkWjmbInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param ywGzbzkWjmbInfo 查询条件
	 * @param ywGzbzkWjmbInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<YwGzbzkWjmbInfo> findPage(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		return super.findPage(ywGzbzkWjmbInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param ywGzbzkWjmbInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		super.save(ywGzbzkWjmbInfo);
	}
	
	/**
	 * 更新状态
	 * @param ywGzbzkWjmbInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		super.updateStatus(ywGzbzkWjmbInfo);
	}
	
	/**
	 * 删除数据
	 * @param ywGzbzkWjmbInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		super.delete(ywGzbzkWjmbInfo);
	}
	
}