/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.entity.gzbzk;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 规章标准库-文件模板节点明细Entity
 * @author wy
 * @version 2020-06-11
 */
@Table(name="yw_gzbzk_wjmb_detail", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="wjmb_id", attrName="wjmbId", label="文件模板id"),
		@Column(name="pid", attrName="pid", label="父级id，如果是第一级。则为0"),
		@Column(name="jd_name", attrName="jdName", label="节点名称", queryType=QueryType.LIKE),
		@Column(name="jd_type", attrName="jdType", label="节点类型"),
		@Column(name="typeface", attrName="typeface", label="字体"),
		@Column(name="fontsize", attrName="fontsize", label="字号"),
		@Column(name="is_bold", attrName="isBold", label="是否加粗"),
		@Column(name="url_img", attrName="urlImg", label="图片路径"),
		@Column(name="content", attrName="content", label="内容"),
	}, orderBy="a.id DESC"
)
public class YwGzbzkWjmbDetail extends DataEntity<YwGzbzkWjmbDetail> {
	
	private static final long serialVersionUID = 1L;
	private String wjmbId;		// 文件模板id
	private String pid;		// 父级id，如果是第一级。则为0
	private String jdName;		// 节点名称
	private String jdType;		// 节点类型
	private String typeface;		// 字体
	private String fontsize;		// 字号
	private Integer isBold;		// 是否加粗
	private String urlImg;		// 图片路径
	private String content;		// 内容
	
	public YwGzbzkWjmbDetail() {
		this(null);
	}

	public YwGzbzkWjmbDetail(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="文件模板id长度不能超过 64 个字符")
	public String getWjmbId() {
		return wjmbId;
	}

	public void setWjmbId(String wjmbId) {
		this.wjmbId = wjmbId;
	}
	
	@Length(min=0, max=64, message="父级id，如果是第一级。则为0长度不能超过 64 个字符")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@NotBlank(message="节点名称不能为空")
	@Length(min=0, max=50, message="节点名称长度不能超过 50 个字符")
	public String getJdName() {
		return jdName;
	}

	public void setJdName(String jdName) {
		this.jdName = jdName;
	}
	
	@Length(min=0, max=50, message="节点类型长度不能超过 50 个字符")
	public String getJdType() {
		return jdType;
	}

	public void setJdType(String jdType) {
		this.jdType = jdType;
	}
	
	@Length(min=0, max=50, message="字体长度不能超过 50 个字符")
	public String getTypeface() {
		return typeface;
	}

	public void setTypeface(String typeface) {
		this.typeface = typeface;
	}
	
	@Length(min=0, max=50, message="字号长度不能超过 50 个字符")
	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}
	
	public Integer getIsBold() {
		return isBold;
	}

	public void setIsBold(Integer isBold) {
		this.isBold = isBold;
	}
	
	@Length(min=0, max=50, message="图片路径长度不能超过 50 个字符")
	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	@Length(min=0, max=1024, message="内容长度不能超过 1024 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}