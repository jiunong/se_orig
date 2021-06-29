package cloud.xmls;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import org.dom4j.*;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * TODO 处理xml文件相关工具
 * @author xuhong.ding
 * @since 2020/9/17 11:04
 */
@Log
public class XmlUtil {


    /**
     * TODO xml 转成 json串
     *
     * @param xml
     * @return java.lang.String
     * @author xuhong.ding
     * @since 2020/9/17 11:08
     */
    public static String xmlToJsonStr(String xml) {
        Document doc;
        try {
            doc = DocumentHelper.parseText(xml);
            JSONObject json = new JSONObject();
            dom4j2Json(doc.getRootElement(), json);
            return json.toJSONString();
        } catch (DocumentException e) {
            log.info("数据解析失败");
        }
        return null;

    }

    /**
     * xml转json
     * @param xmlStr
     * @return JSONObject
     * @throws DocumentException
     */
    public static JSONObject xmlJsonObj(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            /*if (StringUtils.hasText(attr.getValue())) {*/
            if (StringUtils.hasText(attr.getValue())) {
                json.put(attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && StringUtils.hasText(element.getText())) {// 如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {// 有子元素
            if (!e.elements().isEmpty()) {// 子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }

            } else {// 子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (StringUtils.hasText(attr.getValue())) {
                        json.put(attr.getName(), attr.getValue());
                    }
                }
                json.put(e.getName(), e.getText().isEmpty() ? Optional.ofNullable(e.attribute(0)).map(Node::getText).orElse("") : e.getText());
            }
        }
    }


}
