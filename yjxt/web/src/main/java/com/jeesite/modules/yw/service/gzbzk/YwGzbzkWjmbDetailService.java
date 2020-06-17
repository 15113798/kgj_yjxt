/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.service.gzbzk;

import java.util.ArrayList;
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

		if(null != ywGzbzkWjmbDetail.getHierarchy()){
			int cj = ywGzbzkWjmbDetail.getHierarchy();
			if (ywGzbzkWjmbDetail.getIsNewRecord()){
				cj = cj+1;
				ywGzbzkWjmbDetail.setId(IdGenerate.uuid());
				ywGzbzkWjmbDetail.setHierarchy(cj);
			}
			//不管是新增还是修改都重新设置一下具体标题编号
			//标题编号规则为当前id往上查父级。查看顶级为止，然后按顺序把父级的排序拼接起来加上本次的排序
			String id = ywGzbzkWjmbDetail.getPid();
			List<YwGzbzkWjmbDetail> resultList = findParent(new ArrayList<YwGzbzkWjmbDetail>(),id);
			String numberCode ="";
			if(null == resultList || resultList.size()==0){
				numberCode = ywGzbzkWjmbDetail.getNumber().toString();
			}else{
				for (int i =resultList.size()-1;i>=0;i--) {
					int enCode = resultList.get(i).getNumber();
					numberCode = numberCode+"."+enCode;
				}
				numberCode = numberCode.substring(1,numberCode.length())+"."+ywGzbzkWjmbDetail.getNumber();
			}
			ywGzbzkWjmbDetail.setNumberCode(numberCode);
			super.save(ywGzbzkWjmbDetail);
		}else{
			if (ywGzbzkWjmbDetail.getIsNewRecord()){
				ywGzbzkWjmbDetail.setId(IdGenerate.uuid());
			}
			super.save(ywGzbzkWjmbDetail);
		}



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




	public List<YwGzbzkWjmbDetail> findParent(List<YwGzbzkWjmbDetail>list,String id){
		List<YwGzbzkWjmbDetail>resultList= findParentById(list,id);
		return resultList;
	}


	public List<YwGzbzkWjmbDetail> findParentById(List<YwGzbzkWjmbDetail>list,String id){
		YwGzbzkWjmbDetail detail = super.get(id);
		if(null == detail||detail.getPid().equals("0")){

		}else{
			list.add(detail);
			findParent(list,detail.getPid());
		}

		return list;
	}


	
}