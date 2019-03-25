package com.ruoyi.common.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static synchronized String createAddXml() {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<ap>");
        xmlBuilder.append("<Corp>");
        xmlBuilder.append("<DbLogAccName>多级账簿名称</DbLogAccName>");
        xmlBuilder.append("<IntTyp>计息方式</IntTyp>");
        xmlBuilder.append("<DbIntType>借方利息类型</DbIntType>");
        xmlBuilder.append("<DbRit>借方自定义利率</DbRit>");
        xmlBuilder.append("<DbAddType>借方利率浮动方式</DbAddType>");
        xmlBuilder.append("<DbRatType>借方利率代码</DbRatType>");
        xmlBuilder.append("<DbRatPct>借方浮比</DbRatPct>");
        xmlBuilder.append("<DbRatAdd>借方浮点</DbRatAdd>");
        xmlBuilder.append("<CrIntType>贷方利息类型</CrIntType>");
        xmlBuilder.append("<CrRit>贷方自定义利率</CrRit>");
        xmlBuilder.append("<CrAddType>贷方利率浮动方式</CrAddType>");
        xmlBuilder.append("<CrRatType>贷方利率代码</CrRatType>");
        xmlBuilder.append("<CrRatPct>贷方浮比</CrRatPct>");
        xmlBuilder.append("<CrRatAdd>贷方浮点</CrRatAdd>");
        xmlBuilder.append("<PmtHd>账簿支取方式</PmtHd>");
        xmlBuilder.append("<ActInf>存款动帐通知书生成标识</ActInf>");
        xmlBuilder.append("</Corp>");
        xmlBuilder.append("<Cmp>");
        xmlBuilder.append("<DbProv>省市代码</DbProv>");
        xmlBuilder.append("<DbAccNo>账号</DbAccNo>");
        xmlBuilder.append("<DbCur>币种</DbCur>");
        xmlBuilder.append("<CrLogAccNo>多级账簿上级编号</CrLogAccNo>");
        xmlBuilder.append("<DbLogAccNo>多级账簿编号</DbLogAccNo>");
        xmlBuilder.append("<LogAccBkOInd>多级账簿输出标志</LogAccBkOInd>");
        xmlBuilder.append("<AuthAmt>账簿超额额度</AuthAmt>");
        xmlBuilder.append("<StatInd>统计标识</StatInd>");
        xmlBuilder.append("</Cmp>");
        xmlBuilder.append("</ap>");
        return xmlBuilder.toString();
    }

    public static synchronized String createQueryXml() {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<ap>");
        xmlBuilder.append("<CCTransCode>CQRA14</CCTransCode>");
        xmlBuilder.append("<Corp>");
        xmlBuilder.append("<StartDate>起始日期</StartDate>");
        xmlBuilder.append("<EndDate>终止日期</EndDate>");
        xmlBuilder.append("</Corp>");
        xmlBuilder.append("<Cmp>");
        xmlBuilder.append("<DbProv>借方省市代码</DbProv>");
        xmlBuilder.append("<DbAccNo>借方账号</DbAccNo>");
        xmlBuilder.append("<DbCur>借方货币码</DbCur>");
        xmlBuilder.append("<DbLogAccNo>起始多级账簿号</DbLogAccNo>");
        xmlBuilder.append("<CrLogAccNo>终止多级账簿号</CrLogAccNo>");
        xmlBuilder.append("</Cmp>");
        xmlBuilder.append("</ap>");
        return xmlBuilder.toString();
    }

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator("Cme");
            // 遍历Cme节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String RecordNum = recordEle.elementTextTrim("RecordNum");
                System.out.println("RecordNum:" + RecordNum);
                map.put("RecordNum", RecordNum);
                String fieldNum = recordEle.elementTextTrim("FieldNum");
                System.out.println("FieldNum:" + fieldNum);
                map.put("fieldNum", fieldNum);
            }

            Iterator cmpIter = rootElt.elementIterator("Cmp"); // 获取根节点下的子节点Cmp
            // 遍历Cmp节点
            while (cmpIter.hasNext()) {
                Element recordEle = (Element) cmpIter.next();
                String BatchFileName = recordEle.elementTextTrim("BatchFileName"); // 拿到Cmp节点下的子节点BatchFileName值
                System.out.println("BatchFileName:" + BatchFileName);
                map.put("BatchFileName", BatchFileName);
            }

        } catch (DocumentException e) {
            logger.error("Xml转map错误",e);
        }
        return map;
    }

    public static void main(String[] args) {
        String xml = "<ap>\n" +
                "<Cme>\n" +
                "<RecordNum>记录总数</RecordNum>\n" +
                "<FieldNum>字段数</FieldNum>\n" +
                "</Cme>\n" +
                "<Cmp>   \n" +
                "<BatchFileName>批量文件名</BatchFileName>\n" +
                "</Cmp>\n" +
                "</ap>\n";
        System.out.println(readStringXmlOut(xml));

    }
}
