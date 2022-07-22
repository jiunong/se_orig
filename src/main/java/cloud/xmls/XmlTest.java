package cloud.xmls;

import cloud.enums.XmlCacheEnum;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import org.dom4j.DocumentException;

import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/6/9 9:28
 */
@Log
public class XmlTest {


    public static void main(String[] args) throws Exception {
        test1();
    }

    static void test1() throws Exception{
        JSONObject jsonObject = XmlUtil.xmlJsonObj(FileUtil.readUtf8String("/Users/mac/Desktop/人员信息表_邓厚军.doc"));
        System.out.println(1);

    }


    static void test() throws DocumentException {
        String url1 = "F:\\data\\112北骆变城内线单线图.sln.xml";
        String url2 = "F:\\data\\112北骆变城内线单线图2.sln.xml";
        JSONObject xml1 = XmlUtil.xmlJsonObj(FileUtil.readUtf8String(url1));
        JSONObject xml2 = XmlUtil.xmlJsonObj(FileUtil.readUtf8String(url2));
        XmlCacheEnum.getLabels().forEach(u -> {
            List<JSONObject> list = ListUtil.list(false);
            List<JSONObject> list2 = ListUtil.list(false);
            Object o = xml1.get(u);
            Object o2 = xml2.get(u);
            if (o == null) {

            } else if (o instanceof JSONObject) {
                list.add((JSONObject) o);
            } else {
                list.addAll((List<JSONObject>) o);
            }
            if (o2 == null) {

            } else if (o2 instanceof JSONObject) {
                list2.add((JSONObject) o2);
            } else {
                list2.addAll((List<JSONObject>) o2);
            }

            Iterator<JSONObject> iterator = list.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = iterator.next();
                    for (int i = 0; i < list2.size(); i++) {
                        if (next.toString().equals(list2.get(i).toString())) {
                            iterator.remove();
                            list2.remove(i);
                            i--;
                        }
                    }
            }
            System.out.println(u);
            System.out.println((":list1-").concat(CollectionUtil.isEmpty(list) ? "null" : list.toString()));
            System.out.println((":list2-").concat(CollectionUtil.isEmpty(list2) ? "null" : list2.toString()));
        });

    }

}
