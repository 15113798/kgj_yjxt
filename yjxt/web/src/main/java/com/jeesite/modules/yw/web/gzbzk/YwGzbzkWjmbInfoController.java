/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.web.gzbzk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.entity.Area;
import com.jeesite.modules.sys.utils.AreaUtils;
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbDetail;
import com.jeesite.modules.yw.service.gzbzk.YwGzbzkWjmbDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbInfo;
import com.jeesite.modules.yw.service.gzbzk.YwGzbzkWjmbInfoService;

import java.util.List;
import java.util.Map;

/**
 * 规章标准库-文件模板Controller
 * @author wy
 * @version 2020-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/yw/gzbzk/ywGzbzkWjmbInfo")
public class YwGzbzkWjmbInfoController extends BaseController {

	@Autowired
	private YwGzbzkWjmbInfoService ywGzbzkWjmbInfoService;
	@Autowired
	private YwGzbzkWjmbDetailService ywGzbzkWjmbDetailService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public YwGzbzkWjmbInfo get(String id, boolean isNewRecord) {
		return ywGzbzkWjmbInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(YwGzbzkWjmbInfo ywGzbzkWjmbInfo, Model model) {
		model.addAttribute("ywGzbzkWjmbInfo", ywGzbzkWjmbInfo);
		return "modules/yw/gzbzk/ywGzbzkWjmbInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<YwGzbzkWjmbInfo> listData(YwGzbzkWjmbInfo ywGzbzkWjmbInfo, HttpServletRequest request, HttpServletResponse response) {
		ywGzbzkWjmbInfo.setPage(new Page<>(request, response));
		Page<YwGzbzkWjmbInfo> page = ywGzbzkWjmbInfoService.findPage(ywGzbzkWjmbInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbInfo:view")
	@RequestMapping(value = "form")
	public String form(YwGzbzkWjmbInfo ywGzbzkWjmbInfo, Model model) {
		model.addAttribute("ywGzbzkWjmbInfo", ywGzbzkWjmbInfo);
		return "modules/yw/gzbzk/ywGzbzkWjmbInfoForm";
	}

	/**
	 * 保存规章标准库-文件模板
	 */
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		ywGzbzkWjmbInfoService.save(ywGzbzkWjmbInfo);
		return renderResult(Global.TRUE, text("保存规章标准库-文件模板成功！"));
	}
	
	/**
	 * 删除规章标准库-文件模板
	 */
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(YwGzbzkWjmbInfo ywGzbzkWjmbInfo) {
		ywGzbzkWjmbInfoService.delete(ywGzbzkWjmbInfo);
		return renderResult(Global.TRUE, text("删除规章标准库-文件模板成功！"));
	}



	/**
	 * 跳转到编辑节点界面
	 */
	@RequestMapping(value = "index")
	public String index(YwGzbzkWjmbInfo ywGzbzkWjmbInfo, Model model) {
		return "modules/yw/gzbzk/ywGzbzkWjmbIndex";
	}


	/*
		提供章节的tree结构
		入参 规章标准库的主键id
	 */
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String id) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<YwGzbzkWjmbDetail> list = null;
		if (StringUtils.isNotBlank(id)){
			// 获取到规章标准库主表的id,通过id去规章目录表中获取到对应的一条记录。
			YwGzbzkWjmbDetail detail = new  YwGzbzkWjmbDetail();
			detail.setWjmbId(id);
			list = ywGzbzkWjmbDetailService.findList(detail);
/*			YwGzbzkWjmbDetail detailEntity = pidList.get(0);
			String pid = detailEntity.getId();

			//拿到第一个的id作为pid去查询子节点
			YwGzbzkWjmbDetail findByPid = new  YwGzbzkWjmbDetail();
			findByPid.setPid(pid);
			list = ywGzbzkWjmbDetailService.findList(findByPid);*/

		}
		for (int i=0; i<list.size(); i++){
			YwGzbzkWjmbDetail e = list.get(i);
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getPid());
			map.put("name", StringUtils.getTreeNodeName(null, e.getId(), e.getJdName()));
			map.put("title", e.getJdName());
			mapList.add(map);
		}
		return mapList;
	}

	
}