/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.yw.service.gzbzk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jeesite.common.idgen.IdGenerate;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.utils.DictUtils;
import com.jeesite.modules.yw.entity.gzbzk.YwGzbzkWjmbDetail;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private  YwGzbzkWjmbInfoService infoService;
	@Autowired
	private  YwGzbzkWjmbDetailService detailService;


	private static  XWPFDocument doc = null;// 创建Word文件


	/**
	 * word整体样式
	 */
	private static CTStyles wordStyles = null;





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
		if (ywGzbzkWjmbInfo.getIsNewRecord()){
			ywGzbzkWjmbInfo.setId(IdGenerate.uuid());
		}
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



	public  void createWord(String mbId) throws IOException, XmlException {

		doc = new XWPFDocument();

		//获取这个模板最多有多少层级
		int cjCount = findHierarchy(mbId)-1;
		System.out.println("该模板最大层级数为----->"+cjCount);
		//增加自定义标题样式
		for (int i = 1; i <= cjCount; i++) {
			String styleStr = "style"+i;
			addCustomHeadingStyle(doc, styleStr, i);
		}
	/*	addCustomHeadingStyle(doc, "style1", 1);
		addCustomHeadingStyle(doc, "style2", 2);
		addCustomHeadingStyle(doc, "style3", 3);*/



		//通过模板id去获取到明细表中该模板的所有章节及内容
		YwGzbzkWjmbInfo info = infoService.get(mbId);
		//文档头
		XWPFParagraph p = doc.createParagraph();// 新建一个段落
		p.setAlignment(ParagraphAlignment.CENTER);// 设置段落的对齐方式
		p.setBorderBottom(Borders.DOUBLE);//设置下边框
		p.setBorderTop(Borders.DOUBLE);//设置上边框
		p.setBorderRight(Borders.DOUBLE);//设置右边框
		p.setBorderLeft(Borders.DOUBLE);//设置左边框
		XWPFRun r = p.createRun();//创建段落文本
		r.setFontSize(16);
		r.setText(info.getName());
		r.setBold(true);//设置为粗体


		//开始遍历内容。遍历内容的时候注意，poi没有发现能自动生成标题编号的api。只能自己进行拼接在节点名前
		//至于节点层级的话和自定义样式层级关系相对应
		//传入顶级，开始递归
		YwGzbzkWjmbDetail detail = new YwGzbzkWjmbDetail();
		detail.setWjmbId(mbId);
		detail.setPid("0");
		YwGzbzkWjmbDetail DjParent = detailService.findList(detail).get(0);
		startWriteContent(DjParent.getId());



		FileOutputStream out = new FileOutputStream("C:/Users/Administrator/Desktop/testMb.docx");

		//生成页眉
		//doc.createTOC();
		doc.write(out);
		out.close();

	}




	//传入顶级id。获取子级递归
	//数据库中存的序号number字段只是为了ztree的排序。生成模板的标题编号是需要从这里递归获取的
	public void startWriteContent(String mbId){
		//通过以该id为pid去查询子级
		YwGzbzkWjmbDetail detail = new YwGzbzkWjmbDetail();
		detail.setPid(mbId);
		List<YwGzbzkWjmbDetail> list = detailService.findList(detail);

		if(null != list ){
			for (int i = 0; i < list.size(); i++) {
				YwGzbzkWjmbDetail findDetail = list.get(i);

				//如果当前节点是标题，则进入标题方法，如果当前节点是段落。则进入段落方法。
				if("1".equals(findDetail.getJdType())){
					// 标题
					XWPFParagraph paragraph = doc.createParagraph();
					paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
					XWPFRun run = paragraph.createRun();
					String dictVal = DictUtils.getDictLabel("zt",findDetail.getFontsize(),"12");
					run.setFontSize(Integer.parseInt(dictVal));
					if(findDetail.getIsBold() == 1){
						run.setBold(true);
					}else{
						run.setBold(false);
					}
					run.setText(findDetail.getNumberCode()+findDetail.getJdName());
					paragraph.setStyle("style"+findDetail.getHierarchy());

					//如果该标题含有内容，则视为是该标题下面的段落
					if(StringUtils.isNotEmpty(findDetail.getContent())){
						XWPFParagraph pDL = doc.createParagraph();
						pDL.setSpacingLineRule(LineSpacingRule.AT_LEAST);
						XWPFRun dlRun = pDL.createRun();
						dlRun.setFontSize(Integer.parseInt(DictUtils.getDictLabel("zt",findDetail.getFontsize(),"12"))-2);
						dlRun.setBold(false);
						dlRun.setText(findDetail.getContent());
					}
				}else{
					// 段落
					XWPFParagraph paragraph = doc.createParagraph();
					paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
					XWPFRun run = paragraph.createRun();
					run.setFontSize(Integer.parseInt(findDetail.getFontsize()));
					if(findDetail.getIsBold() == 1){
						run.setBold(true);
					}else{
						run.setBold(false);
					}
					run.setText(findDetail.getJdName());
					paragraph.setStyle("style"+findDetail.getHierarchy());
				}

				startWriteContent(findDetail.getId());
			}
		}
	}






	public int findHierarchy(String mbId){
		YwGzbzkWjmbDetail detail = new YwGzbzkWjmbDetail();
		detail.setWjmbId(mbId);
		// 由顶级节点
		List<YwGzbzkWjmbDetail>list = detailService.findList(detail);
		return getMaxContailLevelNum(list);
	}


	/**
	 * 获取当前节点包含多少级,如果没有子节点-默认1级
	 * @param node
	 * @return
	 */
	public int getContainLevelNum(YwGzbzkWjmbDetail node) {

		List<YwGzbzkWjmbDetail> list = findChildNodeList(node);
		if(CollectionUtils.isEmpty(list)) {
			return 1;
		} else {
			return 1 + getMaxContailLevelNum(list);
		}

	}

	/**
	 * 获取当前节点子节点List集合
	 */
	public List<YwGzbzkWjmbDetail> findChildNodeList(YwGzbzkWjmbDetail entity) {
		List<YwGzbzkWjmbDetail>list = detailService.findList(entity);
		return list;
	}

	/**
	 * 获取list中包含层级最多的层级数目
	 * @param list
	 * @return
	 */
	public int getMaxContailLevelNum(List<YwGzbzkWjmbDetail> list) {
		int maxNum = 0;
		for (YwGzbzkWjmbDetail entity : list) {
			YwGzbzkWjmbDetail detail = new YwGzbzkWjmbDetail();
			detail.setPid(entity.getId());
			int currNum = getContainLevelNum(detail);
			maxNum = currNum > maxNum ? currNum : maxNum;
		}


		return maxNum;
	}



	public void startFindChild(String id){
		YwGzbzkWjmbDetail detail = new YwGzbzkWjmbDetail();
		detail.setPid(id);
		List<YwGzbzkWjmbDetail> list = detailService.findList(detail);
		if(null != list){
			for (int i = 0; i < list.size(); i++) {
				//文档段落
				XWPFParagraph p = doc.createParagraph();// 新建一个段落
				XWPFRun r = p.createRun();
				r.setText(list.get(i).getJdName());

				String childId = list.get(i).getId();
				startFindChild(childId);
			}
		}
	}



	/**
	 * 增加自定义标题样式
	 *
	 * @param docxDocument 目标文档
	 * @param strStyleId 样式名称
	 * @param headingLevel 样式级别
	 */
	private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

		CTStyle ctStyle = CTStyle.Factory.newInstance();
		ctStyle.setStyleId(strStyleId);

		CTString styleName = CTString.Factory.newInstance();
		styleName.setVal(strStyleId);
		ctStyle.setName(styleName);

		CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
		indentNumber.setVal(BigInteger.valueOf(headingLevel));

		// lower number > style is more prominent in the formats bar
		ctStyle.setUiPriority(indentNumber);

		CTOnOff onoffnull = CTOnOff.Factory.newInstance();
		ctStyle.setUnhideWhenUsed(onoffnull);

		// style shows up in the formats bar
		ctStyle.setQFormat(onoffnull);

		// style defines a heading of the given level
		CTPPr ppr = CTPPr.Factory.newInstance();
		ppr.setOutlineLvl(indentNumber);
		ctStyle.setPPr(ppr);

		XWPFStyle style = new XWPFStyle(ctStyle);

		// is a null op if already defined
		XWPFStyles styles = docxDocument.createStyles();

		style.setType(STStyleType.PARAGRAPH);
		styles.addStyle(style);

	}
}