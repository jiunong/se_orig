package cloud.json;

import com.alibaba.fastjson.JSONObject;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/11/1 10:20
 */
public class JsonTest {
    public static void main(String[] args) {
        String s = "{\"date\":\"2021-10-31\",\"code\":\"CODE_1539827726031_11594\",\"method\":\"getPlant\",\"user\":{\"ip\":\"10.21.14.216\",\"roles\":\"ROLE_1571390085343_66671\",\"name\":\"testdc\",\"id\":\"testdc\"}}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        jsonObject.get("user");
    }
}
