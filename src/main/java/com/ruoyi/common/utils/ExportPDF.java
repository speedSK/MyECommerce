package com.ruoyi.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ExportPDF {

    private static final Logger log = LoggerFactory.getLogger(ExportPDF.class);

    /**
     * 利用模板生成pdf
     * @param is 模板流
     * @param newPDFPath 生成的新文件路径
     * @param str 参数数组
     */
    public static void createPDF(InputStream is, String newPDFPath, String[] str) {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            File desc = new File(File.separator + newPDFPath);
            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(is);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                // 设置支持中文
                form.setFieldProperty(name, "textfont", baseFont, null);
                form.setField(name, str[i++]);
            }

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            log.error("创建pdfIO异常", e);
        } catch (DocumentException e) {
            log.error("创建pdfDocument异常", e);
        }

    }
}
