package com.jeesite.modules.test.web;/* ====================================================================
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements. See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
==================================================================== */

import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * A simple WOrdprocessingML document created by POI XWPF API
 *
 * @author Yegor Kozlov
 */
public class WordTest {


    public static void main(String[] args) throws Exception {

        writeSimpleDocxFile();
    }



    /**
     * 自定义样式方式写word，参考statckoverflow的源码
     *
     * @throws IOException
     */
    public static void writeSimpleDocxFile() throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // 增加自定义标题样式
        addCustomHeadingStyle(doc, "style1", 1);
        addCustomHeadingStyle(doc, "style2", 2);
        addCustomHeadingStyle(doc, "style3", 3);



        //文档头
        XWPFParagraph p = doc.createParagraph();// 新建一个段落
        p.setAlignment(ParagraphAlignment.CENTER);// 设置段落的对齐方式
        p.setBorderBottom(Borders.DOUBLE);//设置下边框
        p.setBorderTop(Borders.DOUBLE);//设置上边框
        p.setBorderRight(Borders.DOUBLE);//设置右边框
        p.setBorderLeft(Borders.DOUBLE);//设置左边框
        XWPFRun r = p.createRun();//创建段落文本
        r.setFontSize(16);
        r.setText("上海地区管理信息系统故障现场应急处置程序");
        r.setBold(true);//设置为粗体


        // 标题1
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setBold(true);
        run.setText("1 目的");
        paragraph.setStyle("style1");

        // 段落
        XWPFParagraph pDetail = doc.createParagraph();// 新建一个段落
        pDetail.setIndentationFirstLine(2);
        //pDetail.setStyle("style2");
        XWPFRun runDetail = pDetail.createRun();
        runDetail.setBold(false);
        runDetail.setFontSize(12);
        runDetail.setText("为保证上海地区管理信息系统故障时，IT信息管理部运行维护岗人员能及时对故障进行应急处置，特制定本程序。");


        // 标题2
        XWPFParagraph paragraph2 = doc.createParagraph();
        paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("2 范围");
        run2.setFontSize(12);
        run2.setBold(true);
        paragraph2.setStyle("style1");

        // 段落
        XWPFParagraph pDetail2 = doc.createParagraph();// 新建一个段落
        pDetail2.setIndentationFirstLine(2);
        //pDetail.setStyle("style2");
        XWPFRun runDetail2 = pDetail2.createRun();
        runDetail2.setBold(false);
        runDetail2.setFontSize(12);
        runDetail2.setText("本程序适用于上海地区管理信息系统故障时，IT信息管理部运行维护岗人员所进行的应急处置工作。");


        // 标题3
        XWPFParagraph paragraph3 = doc.createParagraph();
        XWPFRun run3 = paragraph3.createRun();
        run3.setText("3 现场应急处置程序");
        run3.setFontSize(12);
        run3.setBold(true);
        paragraph3.setStyle("style1");

        //标题3.1
        XWPFParagraph paragraph31 = doc.createParagraph();
        XWPFRun run31 = paragraph31.createRun();
        run31.setText("3.1 用户管理平台失效");
        run31.setFontSize(12);
        run31.setBold(true);
        paragraph31.setStyle("style2");

        //标题3.1.1
        XWPFParagraph paragraph311 = doc.createParagraph();
        XWPFRun run311 = paragraph311.createRun();
        run311.setText("3.1.1 启动条件");
        run311.setFontSize(12);
        run311.setBold(true);
        paragraph311.setStyle("style3");

        // 3.1.1段落
        XWPFParagraph pDetail31 = doc.createParagraph();// 新建一个段落
        pDetail31.setIndentationFirstLine(2);
        //pDetail.setStyle("style2");
        XWPFRun runDetail31 = pDetail31.createRun();
        runDetail31.setBold(false);
        runDetail31.setFontSize(12);
        runDetail31.setText("当遇到以下情况时，启动该现场应急处置程序：\n" +"无法访问系统，且重启应用服务无效，根据《上海地区管理信息系统故障处理程序》判断为系统失效。");


        //标题3.2
        XWPFParagraph paragraph32 = doc.createParagraph();
        XWPFRun run32 = paragraph32.createRun();
        run32.setText("3.2 IT信息管理室上海地区管理信息系统设备主管");
        run32.setFontSize(12);
        run32.setBold(true);
        paragraph32.setStyle("style2");

        // 3.2段落
        XWPFParagraph pDetail32 = doc.createParagraph();// 新建一个段落
        pDetail32.setIndentationFirstLine(2);
        //pDetail.setStyle("style2");
        XWPFRun runDetail32 = pDetail32.createRun();
        runDetail32.setBold(false);
        runDetail32.setFontSize(12);
        runDetail32.setText("信息通报，设备配置。");




        // word写入到文件
        FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/1.docx");
        doc.write(fos);
        fos.close();
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

