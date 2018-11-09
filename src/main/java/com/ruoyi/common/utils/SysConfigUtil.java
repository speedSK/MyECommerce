package com.ruoyi.common.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.system.config.service.ConfigServiceImpl;

public class SysConfigUtil {

    public static String getNodeValue(String nodeProperty) {
        ConfigServiceImpl configService = SpringUtils.getBean(ConfigServiceImpl.class);
        return configService.selectConfigByKey(nodeProperty);
    }

    public static String getErrorInfo(String responsecode) {
        String result = "系统异常，请联系管理员";

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(SysConfigUtil.class.getClassLoader().getResourceAsStream("config//global//errormap.xml"));
            NodeList cList = doc.getElementsByTagName("error");

            for (int i = 0; i < cList.getLength(); ++i) {
                Element cElement = (Element) cList.item(i);
                String code = cElement.getAttribute("code");
                if (responsecode.equals(code)) {
                    result = cElement.getAttribute("name");
                }
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return result;
    }

}