/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.service.gzbzk;

import java.util.List;

import com.jeesite.common.idgen.IdGenerate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbDetail;
import com.jeesite.modules.yw.dao.gzbzk.YwGzbzkWjmbDetailDao;

/**
 * 规章标准库-文件模板节点明细Service
 * @author wy
 * @version 2020-06-11
 */
@Service
@Transactional(readOnly=true)
public class YwGzbzkWjmbDetailService extends CrudService<YwGzbzkWjmbDetailDao, YwGzbzkWjmbDetail> {
	
	/**
	 * 获取单条数据
	 * @param ywGzbzkWjmbDetail
	 * @return
	 */
	@Override
	public YwGzbzkWjmbDetail get(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		return super.get(ywGzbzkWjmbDetail);
	}
	
	/**
	 * 查询分页数据
	 * @param ywGzbzkWjmbDetail 查询条件
	 * @param ywGzbzkWjmbDetail.page 分页对象
	 * @return
	 */
	@Override
	public Page<YwGzbzkWjmbDetail> findPage(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		return super.findPage(ywGzbzkWjmbDetail);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param ywGzbzkWjmbDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		if (ywGzbzkWjmbDetail.getIsNewRecord()){
			ywGzbzkWjmbDetail.setId(IdGenerate.uuid());
		}
		super.save(ywGzbzkWjmbDetail);
	}
	
	/**
	 * 更新状态
	 * @param ywGzbzkWjmbDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		super.updateStatus(ywGzbzkWjmbDetail);
	}
	
	/**
	 * 删除数据
	 * @param ywGzbzkWjmbDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		super.delete(ywGzbzkWjmbDetail);
	}






	
}