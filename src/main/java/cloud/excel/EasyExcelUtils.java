package cloud.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2023/1/10 12:41
 */
public class EasyExcelUtils {



    public static List<List<String>> head(String[] array) {
        List<List<String>> list = new ArrayList<>();
        for (String s : array) {
            List<String> head = new ArrayList<>();
            head.add(s);
            list.add(head);
        }
        return list;
    }

    public static List<List<Object>> dataList(List<Map<String, Object>> list, String[] listKey) throws Exception {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for (Map<String, Object> map : list) {
            List<Object> data = new ArrayList<Object>();
            for (String s : listKey) {
                if (map.get(s) == null) {
                    data.add("");
                } else {
                    //数据格式处理 发现包含showImg字段就展示网络图片（简单的判断）
                    //也可以根据自己的需求进行格式化操作都放在这里
                    Object obj = map.get(s);
                    if(s.contains("showImg")  && obj.toString().contains("http")){
                        data.add(new URL(obj.toString()));
                    }else {
                        data.add(obj.toString());
                    }
                }
            }
            dataList.add(data);
        }
        return dataList;
    }


}
