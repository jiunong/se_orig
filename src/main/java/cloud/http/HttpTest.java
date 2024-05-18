package cloud.http;

import cloud.model.FeederEnergyModel;
import cloud.model.Te;
import cloud.singleton.User;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.Charset;

public class HttpTest {

    public static void main(String[] args) {
        testHt();
    }

    static void testHt() {
        String s = FileUtil.readString("D:\\PRO\\se_orig\\src\\main\\java\\cloud\\http\\attern.txt", "GBK");
        //String url = "http://10.21.14.216:18080/omspdjx_sy_plus/PdjxDdmmInterfaceDataController/receiveDispatchNamingJSONData";
        String url = "http://10.21.19.71:8080/omspdjx_as_plus/PdjxDdmmInterfaceDataController/receiveDispatchNamingJSONData";
        String body = HttpUtil.createPost(url).contentType("application/json;charset=UTF-8;").body(s).execute().body();
        System.out.println(body);
    }

}
