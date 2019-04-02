package com.ruoyi.common.utils.xml;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SysConfigUtil;
import com.ruoyi.project.bank.domain.TransVo;
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

    private static String CorpNo = SysConfigUtil.getNodeValue("bank.server.CorpNo");
    private static String DbProv = SysConfigUtil.getNodeValue("bank.server.DbProv");
    private static String DbAccNo = SysConfigUtil.getNodeValue("bank.server.DbAccNo");
    private static String DbCur = SysConfigUtil.getNodeValue("bank.server.DbCur");
    private static String OpNo = SysConfigUtil.getNodeValue("bank.server.OpNo");
    private static String CrLogAccNo = SysConfigUtil.getNodeValue("bank.server.CrLogAccNo");

    public static String createAddXml(TransVo vo) {
        StringBuilder xmlBuilder = new StringBuilder();
        String dateTimeNow = DateUtils.dateTimeNow();
        xmlBuilder.append("<ap>");
        xmlBuilder.append("<CCTransCode>CMLT40</CCTransCode>");
        xmlBuilder.append("<ProductID>ICC</ProductID>");
        xmlBuilder.append("<ChannelType>ERP</ChannelType>");
        xmlBuilder.append("<CorpNo>" + CorpNo + "</CorpNo>");
        xmlBuilder.append("<OpNo>" + OpNo + "</OpNo>");
        xmlBuilder.append("<AuthNo></AuthNo>");
        xmlBuilder.append("<ReqSeqNo>" + vo.getJourno() + "</ReqSeqNo>");
        xmlBuilder.append("<ReqDate>" + dateTimeNow.substring(0, 8) + "</ReqDate>");
        xmlBuilder.append("<ReqTime>" + dateTimeNow.substring(8, 14) + "</ReqTime>");
        xmlBuilder.append("<Sign></Sign>");

        xmlBuilder.append("<Corp>");
        xmlBuilder.append("<DbLogAccName>" + vo.getAccName() + "</DbLogAccName>");
        xmlBuilder.append("<IntTyp>0</IntTyp>");
        xmlBuilder.append("<DbIntType>F</DbIntType>");
        xmlBuilder.append("<DbRit></DbRit>");
        xmlBuilder.append("<DbAddType></DbAddType>");
        xmlBuilder.append("<DbRatType></DbRatType>");
        xmlBuilder.append("<DbRatPct></DbRatPct>");
        xmlBuilder.append("<DbRatAdd></DbRatAdd>");
        xmlBuilder.append("<CrIntType>F</CrIntType>");
        xmlBuilder.append("<CrRit></CrRit>");
        xmlBuilder.append("<CrAddType></CrAddType>");
        xmlBuilder.append("<CrRatType></CrRatType>");
        xmlBuilder.append("<CrRatPct></CrRatPct>");
        xmlBuilder.append("<CrRatAdd></CrRatAdd>");
        xmlBuilder.append("<PmtHd>01</PmtHd>");
        xmlBuilder.append("<ActInf>1</ActInf>");
        xmlBuilder.append("</Corp>");
        xmlBuilder.append("<Cmp>");
        xmlBuilder.append("<DbProv>" + DbProv + "</DbProv>");
        xmlBuilder.append("<DbAccNo>" + DbAccNo + "</DbAccNo>");
        xmlBuilder.append("<DbCur>" + DbCur + "</DbCur>");
        xmlBuilder.append("<CrLogAccNo>" + CrLogAccNo + "</CrLogAccNo>");
        xmlBuilder.append("<DbLogAccNo>" + vo.getAccNumber() + "</DbLogAccNo>");
        xmlBuilder.append("<LogAccBkOInd>1</LogAccBkOInd>");
        xmlBuilder.append("<AuthAmt></AuthAmt>");
        xmlBuilder.append("<StatInd></StatInd>");
        xmlBuilder.append("</Cmp>");
        xmlBuilder.append("</ap>");
        return xmlBuilder.toString();
    }

    public static String createQueryXml(TransVo vo) {
        StringBuilder xmlBuilder = new StringBuilder();
        String dateTimeNow = DateUtils.dateTimeNow();
        xmlBuilder.append("<ap>");
        xmlBuilder.append("<CCTransCode>CQRD01</CCTransCode>");
        xmlBuilder.append("<ProductID>ICC</ProductID>");
        xmlBuilder.append("<ChannelType>ERP</ChannelType>");
        xmlBuilder.append("<CorpNo>" + CorpNo + "</CorpNo>");
        xmlBuilder.append("<OpNo>" + OpNo + "</OpNo>");
        xmlBuilder.append("<AuthNo></AuthNo>");
        xmlBuilder.append("<ReqSeqNo>" + vo.getJourno() + "</ReqSeqNo>");
        xmlBuilder.append("<ReqDate>" + dateTimeNow.substring(0, 8) + "</ReqDate>");
        xmlBuilder.append("<ReqTime>" + dateTimeNow.substring(8, 14) + "</ReqTime>");
        xmlBuilder.append("<Sign></Sign>");
        xmlBuilder.append("<Cmp>");
        xmlBuilder.append("<DbProv>" + DbProv + "</DbProv>");
        xmlBuilder.append("<DbAccNo>" + DbAccNo + "</DbAccNo>");
        xmlBuilder.append("<DbCur>" + DbCur + "</DbCur>");
        xmlBuilder.append("<DbLogAccNo>" + vo.getStartNumber() + "</DbLogAccNo>");
        xmlBuilder.append("<CrLogAccNo>" + vo.getEndNumber() + "</CrLogAccNo>");
        xmlBuilder.append("</Cmp>");
        xmlBuilder.append("<Corp>");
//        xmlBuilder.append("<StartDate>" + vo.getStartTime() + "</StartDate>");
//        xmlBuilder.append("<EndDate>" + vo.getEndTime() + "</EndDate>");
        xmlBuilder.append("<StartDate>20181016</StartDate>");
        xmlBuilder.append("<EndDate>20181016</EndDate>");
        xmlBuilder.append("</Corp>");
        xmlBuilder.append("<Cme>");
        xmlBuilder.append("<ContLast>" + vo.getContLast() + "</ContLast>");
        xmlBuilder.append("</Cme>");
        xmlBuilder.append("</ap>");
        return xmlBuilder.toString();
    }

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<>();
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
            String RespSource = rootElt.elementTextTrim("RespSource");
            map.put("RespSource", RespSource);
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
                String ContLast = recordEle.elementTextTrim("ContLast");
                map.put("ContLast", ContLast);
                String FieldNum = recordEle.elementTextTrim("FieldNum");
                map.put("FieldNum", FieldNum);
            }

            Iterator cmpIter = rootElt.elementIterator("Cmp"); // 获取根节点下的子节点Cmp
            // 遍历Cmp节点
            while (cmpIter.hasNext()) {
                Element recordEle = (Element) cmpIter.next();
                String RespPrvData = recordEle.elementTextTrim("RespPrvData"); // 拿到Cmp节点下的子节点BatchFileName值
                map.put("RespPrvData", RespPrvData);
                String ContFlag = recordEle.elementTextTrim("ContFlag"); // 拿到Cmp节点下的子节点BatchFileName值
                map.put("ContFlag", ContFlag);
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
                "\t<RespTime>返回时间</RespTime>\n" +
                "\t<RespCode>返回码</RespCode>\n" +
                "\t<RespInfo>返回信息</RespInfo>\n" +
                "\t<RxtInfo>返回扩展信息</RxtInfo>\n" +
                "\t<FileFlag>数据文件标识</FileFlag>\n" +
                "\t<Cme>\n" +
                "\t<RecordNum>记录总数</RecordNum>\n" +
                "\t<FieldNum>字段数</FieldNum>\n" +
                "\t</Cme>\n" +
                "\t<Cmp>\n" +
                "\t<BatchFileName>批量文件名</BatchFileName>\n" +
                "\t</Cmp>\n" +
                "\t</ap>\n";
        System.out.println(new TransVo());

//        System.out.println(readStringXmlOut(xml));

    }
}
