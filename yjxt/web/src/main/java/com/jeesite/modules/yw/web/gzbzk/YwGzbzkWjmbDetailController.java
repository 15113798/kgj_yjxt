/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.web.gzbzk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.xmlbeans.XmlException;
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

import java.io.IOException;

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
		YwGzbzkWjmbDetail detail = ywGzbzkWjmbDetailService.get(id, isNewRecord);
		if("2".equals(detail.getJdType())){
			detail.setFontsize1(detail.getFontsize());
			detail.setIsBold1(detail.getIsBold());
			detail.setJdName1(detail.getJdName());
			detail.setTypeface1(detail.getTypeface());
			detail.setNumber1(detail.getNumber());
		}
		return detail;
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
		if(StringUtils.isBlank(ywGzbzkWjmbDetail.getJdType())){
			return renderResult(Global.FALSE, text("节点类型必填"));
		}
		if("1".equals(ywGzbzkWjmbDetail.getJdType())){
			if(null == ywGzbzkWjmbDetail.getNumber()){
				return renderResult(Global.FALSE, text("序号必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getJdName())){
				return renderResult(Global.FALSE, text("节点名称必填"));
			}
			if(null == ywGzbzkWjmbDetail.getIsBold()){
				return renderResult(Global.FALSE, text("是否加粗字段必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getTypeface())){
				return renderResult(Global.FALSE, text("字体必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getFontsize())){
				return renderResult(Global.FALSE, text("字号字段必填"));
			}
		}else{
			if(null == ywGzbzkWjmbDetail.getNumber1()){
				return renderResult(Global.FALSE, text("排序号必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getFontsize1())){
				return renderResult(Global.FALSE, text("字号字段必填"));
			}
			if(null == ywGzbzkWjmbDetail.getIsBold1()){
				return renderResult(Global.FALSE, text("是否加粗字段必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getTypeface1())){
				return renderResult(Global.FALSE, text("字体必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getContent())){
				return renderResult(Global.FALSE, text("内容必填"));
			}
			if(StringUtils.isBlank(ywGzbzkWjmbDetail.getJdName1())){
				return renderResult(Global.FALSE, text("节点名称必填"));
			}


		}


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