package com.ruoyi.project.business.person.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.BaseFont;

public class ExportPDF {
    // 利用模板生成pdf
    public static void fillTemplate() {
        // 模板路径
        String templatePath = "E:/销户模板.pdf";
        // 生成的新文件路径
        String newPDFPath = "E:/ceshi.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            String[] str = {"123456789", "刘宁", "测试部", "100.00", "admin", "192.168.0.1", "2018-11-14 16:39:53"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                System.out.println(name);
                // 设置支持中文
                form.setFieldProperty(name, "textfont", baseFont, null);
                form.setField(name, str[i++]);
            }

//            form.setField("user_number", "1");
//            form.setField("user_name", "大傻子");
//            form.setField("refund_amount", "1");
//            form.setField("user_dept", "1");
//            form.setField("oper_time", "1");
//            form.setField("oper_device", "1");
//            form.setField("oper_user", "1");

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    public static void main(String[] args) {
        fillTemplate();
    }
}
