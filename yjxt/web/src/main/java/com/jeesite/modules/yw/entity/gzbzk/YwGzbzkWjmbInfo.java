/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.entity.gzbzk;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 规章标准库-文件模板Entity
 * @author wy
 * @version 2020-06-11
 */
@Table(name="yw_gzbzk_wjmb_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="模板名称", queryType=QueryType.LIKE),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="修改人", isInsert=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="修改时间", isInsert=false, isQuery=false),
		@Column(name="model_type", attrName="modelType", label="模板类型"),
	}, orderBy="a.update_date DESC"
)
public class YwGzbzkWjmbInfo extends DataEntity<YwGzbzkWjmbInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模板名称
	private String modelType;


	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public YwGzbzkWjmbInfo() {
		this(null);
	}

	public YwGzbzkWjmbInfo(String id){
		super(id);
	}
	
	@NotBlank(message="模板名称不能为空")
	@Length(min=0, max=50, message="模板名称长度不能超过 50 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}