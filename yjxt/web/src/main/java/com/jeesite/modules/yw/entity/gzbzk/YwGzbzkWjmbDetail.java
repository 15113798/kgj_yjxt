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
		@Column(name="number", attrName="number", label="排序"),
		@Column(name="hierarchy", attrName="hierarchy", label="层级"),
		@Column(name="order_number", attrName="numberCode", label="具体编号"),
	}, orderBy="a.number "
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
	private Integer number;	//排序序号
	private Integer hierarchy;		//层级
	private String numberCode;			//具体编号


	private String typeface1;		// 字体
	private String fontsize1;		// 字号
	private Integer isBold1;		// 是否加粗
	private Integer number1;	//排序序号
	private String jdName1;		// 节点名称


	public String getJdName1() {
		return jdName1;
	}

	public void setJdName1(String jdName1) {
		this.jdName1 = jdName1;
	}

	public Integer getNumber1() {
		return number1;
	}

	public void setNumber1(Integer number1) {
		this.number1 = number1;
	}

	public String getTypeface1() {
		return typeface1;
	}

	public void setTypeface1(String typeface1) {
		this.typeface1 = typeface1;
	}

	public String getFontsize1() {
		return fontsize1;
	}

	public void setFontsize1(String fontsize1) {
		this.fontsize1 = fontsize1;
	}

	public Integer getIsBold1() {
		return isBold1;
	}

	public void setIsBold1(Integer isBold1) {
		this.isBold1 = isBold1;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNumberCode() {
		return numberCode;
	}

	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

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