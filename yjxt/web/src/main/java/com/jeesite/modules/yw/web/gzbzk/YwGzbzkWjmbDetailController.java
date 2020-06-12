/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.web.gzbzk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.StringUtils;
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
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbDetail;
import com.jeesite.modules.yw.service.gzbzk.YwGzbzkWjmbDetailService;

/**
 * 规章标准库-文件模板节点明细Controller
 * @author wy
 * @version 2020-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/yw/gzbzk/ywGzbzkWjmbDetail")
public class YwGzbzkWjmbDetailController extends BaseController {

	@Autowired
	private YwGzbzkWjmbDetailService ywGzbzkWjmbDetailService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public YwGzbzkWjmbDetail get(String id, boolean isNewRecord) {
		return ywGzbzkWjmbDetailService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	/*@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbDetail:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(YwGzbzkWjmbDetail ywGzbzkWjmbDetail, Model model) {
		model.addAttribute("ywGzbzkWjmbDetail", ywGzbzkWjmbDetail);
		return "modules/yw/gzbzk/ywGzbzkWjmbDetailList";
	}
	
	/**
	 * 查询列表数据
	 */
/*
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbDetail:view")
*/
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<YwGzbzkWjmbDetail> listData(YwGzbzkWjmbDetail ywGzbzkWjmbDetail, HttpServletRequest request, HttpServletResponse response) {
		ywGzbzkWjmbDetail.setPage(new Page<>(request, response));
		Page<YwGzbzkWjmbDetail> page = ywGzbzkWjmbDetailService.findPage(ywGzbzkWjmbDetail);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
/*
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbDetail:view")
*/
	@RequestMapping(value = "form")
	public String form(YwGzbzkWjmbDetail ywGzbzkWjmbDetail, Model model) {
		model.addAttribute("ywGzbzkWjmbDetail", ywGzbzkWjmbDetail);
		return "modules/yw/gzbzk/ywGzbzkWjmbDetailForm";
	}

	/**
	 * 保存规章标准库-文件模板节点明细
	 */
/*
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbDetail:edit")
*/
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		if(StringUtils.isBlank(ywGzbzkWjmbDetail.getPid())){
			return renderResult(Global.FALSE, text("请选择对应的节点进行操作！未获取到对应的节点信息"));
		}
		ywGzbzkWjmbDetailService.save(ywGzbzkWjmbDetail);
		return renderResult(Global.TRUE, text("保存成功！"));
	}
	
	/**
	 * 删除规章标准库-文件模板节点明细
	 */
/*
	@RequiresPermissions("yw:gzbzk:ywGzbzkWjmbDetail:edit")
*/
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(YwGzbzkWjmbDetail ywGzbzkWjmbDetail) {
		ywGzbzkWjmbDetailService.delete(ywGzbzkWjmbDetail);
		return renderResult(Global.TRUE, text("删除规章标准库-文件模板节点明细成功！"));
	}
	
}