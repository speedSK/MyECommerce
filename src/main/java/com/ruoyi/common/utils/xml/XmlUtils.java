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
        xmlBuilder.append("<CCTransCode>CMLT40</CCTransCode>");
        xmlBuilder.append("<ProductID>ICC</ProductID>");
        xmlBuilder.append("<ChannelType>ERP</ChannelType>");
        xmlBuilder.append("<CorpNo>企业技监局号码/客户号</CorpNo>");
        xmlBuilder.append("<OpNo>企业操作员编号</OpNo>");
        xmlBuilder.append("<AuthNo>认证码</AuthNo>");
        xmlBuilder.append("<ReqSeqNo>请求方流水号</ReqSeqNo>");
        xmlBuilder.append("<ReqDate>请求日期</ReqDate>");
        xmlBuilder.append("<ReqTime>请求时间</ReqTime>");
        xmlBuilder.append("<Sign>数字签名</Sign>");

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
        xmlBuilder.append("<CCTransCode>CQRD02</CCTransCode>");
        xmlBuilder.append("<ProductID>ICC</ProductID>");
        xmlBuilder.append("<ChannelType>ERP</ChannelType>");
        xmlBuilder.append("<CorpNo>企业技监局号码/客户号</CorpNo>");
        xmlBuilder.append("<OpNo>企业操作员编号</OpNo>");
        xmlBuilder.append("<AuthNo>认证码</AuthNo>");
        xmlBuilder.append("<ReqSeqNo>请求方流水号</ReqSeqNo>");
        xmlBuilder.append("<ReqDate>请求日期</ReqDate>");
        xmlBuilder.append("<ReqTime>请求时间</ReqTime>");
        xmlBuilder.append("<Sign>数字签名</Sign>");

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
            String CCTransCode = rootElt.elementTextTrim("CCTransCode");
            map.put("CCTransCode", CCTransCode);
            String ReqSeqNo = rootElt.elementTextTrim("ReqSeqNo");
            map.put("ReqSeqNo", ReqSeqNo);
            String RespSeqNo = rootElt.elementTextTrim("RespSeqNo");
            map.put("RespSeqNo", RespSeqNo);
            String RespCode = rootElt.elementTextTrim("RespCode");
            map.put("RespCode", RespCode);
            String RespInfo = rootElt.elementTextTrim("RespInfo");
            map.put("RespInfo", RespInfo);
            String RxtInfo = rootElt.elementTextTrim("RxtInfo");
            map.put("RxtInfo", RxtInfo);
            String FileFlag = rootElt.elementTextTrim("FileFlag");
            map.put("FileFlag", FileFlag);
            Iterator iter = rootElt.elementIterator("Cme");
            // 遍历Cme节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String RecordNum = recordEle.elementTextTrim("RecordNum");
                map.put("RecordNum", RecordNum);
                String FieldNum = recordEle.elementTextTrim("FieldNum");
                map.put("FieldNum", FieldNum);
            }

            Iterator cmpIter = rootElt.elementIterator("Cmp"); // 获取根节点下的子节点Cmp
            // 遍历Cmp节点
            while (cmpIter.hasNext()) {
                Element recordEle = (Element) cmpIter.next();
                String RespPrvData = recordEle.elementTextTrim("RespPrvData"); // 拿到Cmp节点下的子节点BatchFileName值
                map.put("RespPrvData", RespPrvData);
                String BatchFileName = recordEle.elementTextTrim("BatchFileName"); // 拿到Cmp节点下的子节点BatchFileName值
                map.put("BatchFileName", BatchFileName);
            }

        } catch (DocumentException e) {
            logger.error("Xml转map错误",e);
        }
        return map;
    }

    public static void main(String[] args) {
        String xml = "<ap>\n" +
                "<CCTransCode>内部交易代码</CCTransCode>\n" +
                "\t<ReqSeqNo>请求方流水号</ReqSeqNo>\n" +
                "\t<RespSource>返回来源</RespSource>\n" +
                "\t<RespSeqNo>应答流水号</RespSeqNo>\n" +
                "\t<RespDate>返回日期</RespDate>\n" +
                "<RespTime>返回时间</RespTime>\n" +
                "\t<RespCode>返回码</RespCode>\n" +
                "\t<RespInfo>返回信息</RespInfo>\n" +
                "\t<RxtInfo>返回扩展信息</RxtInfo>\n" +
                "\t<FileFlag>数据文件标识</FileFlag>\n" +
                "<Cme>\n" +
                "<RecordNum>记录总数</RecordNum>\n" +
                "<FieldNum>字段数</FieldNum>\n" +
                "</Cme>\n" +
                "<Cmp>\n" +
                "<BatchFileName>批量文件名</BatchFileName>\n" +
                "</Cmp>\n" +
                "</ap>\n";
        System.out.println(readStringXmlOut(xml));

    }
}
