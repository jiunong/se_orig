package cloud.json;

import cloud.xmls.XmlUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentException;

import java.nio.charset.StandardCharsets;

public class SvgTest {


    public static void main(String[] args) throws DocumentException {
        testSvg();
    }


    static void testSvg() throws DocumentException {
        String  svg = "C:\\svg\\anshan_高新区_金属线单线图.sln.svg";
        JSONObject jsonObject = XmlUtil.xmlJsonObj(FileUtil.readString(svg, StandardCharsets.UTF_8));
        System.out.println(jsonObject);
    }

}
