package cloud.json;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/11/1 10:20
 */
public class JsonTest {


    public static void main(String[] args) {
        test1();
    }
    static void test1(){
        List< HashMap<String, Object>> list = ListUtil.list(false);

        HashMap<String, Object> objectObjectHashMap = MapUtil.newHashMap(false);
        objectObjectHashMap.put("A","1");
        objectObjectHashMap.put("A1","2");
        objectObjectHashMap.put("A2",null);
        list.add(objectObjectHashMap);

        HashMap<String, Object> objectObjectHashMap2 = MapUtil.newHashMap(false);
        objectObjectHashMap2.put("A","1");
        objectObjectHashMap2.put("A1",null);
        objectObjectHashMap2.put("A2",null);
        list.add(objectObjectHashMap2);


        list.forEach(li->{
            li.keySet().forEach(key->{
                li.putIfAbsent(key, "");
            });
        });
        System.out.println(JSONUtil.toJsonStr(list));


    }




     static void main2(String[] args) {
        String s = "{\"date\":\"2021-10-31\",\"code\":\"CODE_1539827726031_11594\",\"method\":\"getPlant\",\"user\":{\"ip\":\"10.21.14.216\",\"roles\":\"ROLE_1571390085343_66671\",\"name\":\"testdc\",\"id\":\"testdc\"}}";
        String s2 = "{\n" +
                "\t\"errors\":\"成功\",\n" +
                "\t\"message\":\"成功\",\n" +
                "\t\"result\":{\n" +
                "\t\t\"xl\":{\n" +
                "\t\t\t\"current\":1,\n" +
                "\t\t\t\"orders\":[],\n" +
                "\t\t\t\"pages\":2292,\n" +
                "\t\t\t\"records\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"assets\":[\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"astNature\":\"03\",\n" +
                "\t\t\t\t\t\t\t\"source#Name\":\"设备增加-技术改造\",\n" +
                "\t\t\t\t\t\t\t\"source\":\"02\",\n" +
                "\t\t\t\t\t\t\t\"astOrg#Name\":\"国网铁岭供电公司\",\n" +
                "\t\t\t\t\t\t\t\"astOrg\":\"14383AB1BA50422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\t\"astOrgName\":\"国网铁岭供电公司\",\n" +
                "\t\t\t\t\t\t\t\"matchingState\":\"01\",\n" +
                "\t\t\t\t\t\t\t\"ctime\":\"2017-05-24 00:00:00\",\n" +
                "\t\t\t\t\t\t\t\"astNature#Name\":\"省（直辖市、自治区）公司\",\n" +
                "\t\t\t\t\t\t\t\"equipCode\":\"22M00000296947459\",\n" +
                "\t\t\t\t\t\t\t\"isPrint\":\"0\",\n" +
                "\t\t\t\t\t\t\t\"astId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" +
                "\t\t\t\t\t\t\t\"deployState#Name\":\"退役\",\n" +
                "\t\t\t\t\t\t\t\"projectNum\":\"20170524\",\n" +
                "\t\t\t\t\t\t\t\"projectName\":\"分段线路\",\n" +
                "\t\t\t\t\t\t\t\"deployState\":\"30\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"distribution\":0,\n" +
                "\t\t\t\t\t\"id\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" +
                "\t\t\t\t\t\"modelId\":\"xl\",\n" +
                "\t\t\t\t\t\"resource\":{\n" +
                "\t\t\t\t\t\t\"erectionMethod#Name\":\"架空\",\n" +
                "\t\t\t\t\t\t\"maintOrg\":\"14383AB1BA2C422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"feeder\":\"22DKX-77441\",\n" +
                "\t\t\t\t\t\t\"voltageLevel#Name\":\"交流10kV\",\n" +
                "\t\t\t\t\t\t\"supplyArea\":\"05\",\n" +
                "\t\t\t\t\t\t\"overheadMethod\":\"1\",\n" +
                "\t\t\t\t\t\t\"supplyArea#Name\":\"D\",\n" +
                "\t\t\t\t\t\t\"startPosition\":\"5D23F3C0-144E-4A53-8613-486E99B58BD4-73733\",\n" +
                "\t\t\t\t\t\t\"pubPrivFlag#Name\":\"运检\",\n" +
                "\t\t\t\t\t\t\"isCommission#Name\":\"否\",\n" +
                "\t\t\t\t\t\t\"regionalism#Name\":\"农村\",\n" +
                "\t\t\t\t\t\t\"erectionMethod\":\"1\",\n" +
                "\t\t\t\t\t\t\"colorCode#Name\":\"黄色\",\n" +
                "\t\t\t\t\t\t\"dispatchLevel\":\"06\",\n" +
                "\t\t\t\t\t\t\"isRural\":\"1\",\n" +
                "\t\t\t\t\t\t\"dispatchLevel#Name\":\"县调\",\n" +
                "\t\t\t\t\t\t\"isRural#Name\":\"是\",\n" +
                "\t\t\t\t\t\t\"ctime\":\"2017-05-24 00:00:00\",\n" +
                "\t\t\t\t\t\t\"feeder#Name\":\"坝墙子变A43010kV坝长线\",\n" +
                "\t\t\t\t\t\t\"relateLineId\":\"26447FDC-45F7-4F96-ACD5-0925DCF9235A-09023\",\n" +
                "\t\t\t\t\t\t\"equipmentOwner\":\"E4AB99E4F8C46FF6E0430100007F1CB9\",\n" +
                "\t\t\t\t\t\t\"isCommission\":\"0\",\n" +
                "\t\t\t\t\t\t\"startType\":\"2\",\n" +
                "\t\t\t\t\t\t\"overheadMethod#Name\":\"单辐射\",\n" +
                "\t\t\t\t\t\t\"psrId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" +
                "\t\t\t\t\t\t\"astId\":\"3936088f608a2081335b91b69b015c3933e07628d0\",\n" +
                "\t\t\t\t\t\t\"maintOrg#Name\":\"国网盘山县供电公司\",\n" +
                "\t\t\t\t\t\t\"mainLine\":\"26447FDC-45F7-4F96-ACD5-0925DCF9235A-09023\",\n" +
                "\t\t\t\t\t\t\"name\":\"10kV坝长线环网分\",\n" +
                "\t\t\t\t\t\t\"lineNature#Name\":\"分段线路\",\n" +
                "\t\t\t\t\t\t\"overheadLength\":0.1397,\n" +
                "\t\t\t\t\t\t\"cableLength\":0.0000,\n" +
                "\t\t\t\t\t\t\"psrState\":\"30\",\n" +
                "\t\t\t\t\t\t\"regionalism\":\"05\",\n" +
                "\t\t\t\t\t\t\"city\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"importance\":\"1\",\n" +
                "\t\t\t\t\t\t\"importance#Name\":\"一般\",\n" +
                "\t\t\t\t\t\t\"runDevName\":\"10kV坝长线环网分\",\n" +
                "\t\t\t\t\t\t\"psrState#Name\":\"退役\",\n" +
                "\t\t\t\t\t\t\"startTime\":\"2017-05-24 00:00:00\",\n" +
                "\t\t\t\t\t\t\"pubPrivFlag\":\"0\",\n" +
                "\t\t\t\t\t\t\"startSwitch\":\"0\",\n" +
                "\t\t\t\t\t\t\"startType#Name\":\"杆塔\",\n" +
                "\t\t\t\t\t\t\"maintGroup#Name\":\"配电运检班\",\n" +
                "\t\t\t\t\t\t\"maintGroup\":\"14383AB1BA32422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"length\":0.1397,\n" +
                "\t\t\t\t\t\t\"lineNature\":\"4\",\n" +
                "\t\t\t\t\t\t\"city#Name\":\"国网盘锦供电公司\",\n" +
                "\t\t\t\t\t\t\"voltageLevel\":\"22\",\n" +
                "\t\t\t\t\t\t\"colorCode\":\"01\",\n" +
                "\t\t\t\t\t\t\"equipmentOwner#Name\":\"张艳华\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"assets\":[\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"astNature\":\"03\",\n" +
                "\t\t\t\t\t\t\t\"source#Name\":\"设备增加-基本建设\",\n" +
                "\t\t\t\t\t\t\t\"remark\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07711\",\n" +
                "\t\t\t\t\t\t\t\"source\":\"01\",\n" +
                "\t\t\t\t\t\t\t\"astOrg#Name\":\"国网盘锦供电公司\",\n" +
                "\t\t\t\t\t\t\t\"astOrg\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\t\"astOrgName\":\"国网盘锦供电公司\",\n" +
                "\t\t\t\t\t\t\t\"matchingState\":\"01\",\n" +
                "\t\t\t\t\t\t\t\"ctime\":\"1999-11-14 00:00:00\",\n" +
                "\t\t\t\t\t\t\t\"astNature#Name\":\"省（直辖市、自治区）公司\",\n" +
                "\t\t\t\t\t\t\t\"equipCode\":\"22M00000050865018\",\n" +
                "\t\t\t\t\t\t\t\"isPrint\":\"0\",\n" +
                "\t\t\t\t\t\t\t\"astId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" +
                "\t\t\t\t\t\t\t\"astNum\":\"160100006588\",\n" +
                "\t\t\t\t\t\t\t\"deployState#Name\":\"在运\",\n" +
                "\t\t\t\t\t\t\t\"deployState\":\"20\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"distribution\":0,\n" +
                "\t\t\t\t\t\"id\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" +
                "\t\t\t\t\t\"modelId\":\"xl\",\n" +
                "\t\t\t\t\t\"resource\":{\n" +
                "\t\t\t\t\t\t\"erectionMethod#Name\":\"架空\",\n" +
                "\t\t\t\t\t\t\"dispatchOrg\":\"8a2081325740817101574553fc220bc1\",\n" +
                "\t\t\t\t\t\t\"maintOrg\":\"14383AB1BA2C422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"feeder\":\"22DKX-90625\",\n" +
                "\t\t\t\t\t\t\"voltageLevel#Name\":\"交流10kV\",\n" +
                "\t\t\t\t\t\t\"supplyArea\":\"05\",\n" +
                "\t\t\t\t\t\t\"overheadMethod\":\"2\",\n" +
                "\t\t\t\t\t\t\"supplyArea#Name\":\"D\",\n" +
                "\t\t\t\t\t\t\"startPosition\":\"BA4D2309-98D7-4A16-8C5D-D8CDEECD5B49-00043\",\n" +
                "\t\t\t\t\t\t\"pubPrivFlag#Name\":\"运检\",\n" +
                "\t\t\t\t\t\t\"isCommission#Name\":\"否\",\n" +
                "\t\t\t\t\t\t\"regionalism#Name\":\"乡镇\",\n" +
                "\t\t\t\t\t\t\"erectionMethod\":\"1\",\n" +
                "\t\t\t\t\t\t\"colorCode#Name\":\"黄色\",\n" +
                "\t\t\t\t\t\t\"dispatchLevel\":\"03\",\n" +
                "\t\t\t\t\t\t\"isRural\":\"1\",\n" +
                "\t\t\t\t\t\t\"dispatchLevel#Name\":\"省（直辖市、自治区）调\",\n" +
                "\t\t\t\t\t\t\"isRural#Name\":\"是\",\n" +
                "\t\t\t\t\t\t\"ctime\":\"1999-11-14 00:00:00\",\n" +
                "\t\t\t\t\t\t\"startStation\":\"46BE93E3-9601-475D-9F08-A3F2F5E3EFC6-00015\",\n" +
                "\t\t\t\t\t\t\"feeder#Name\":\"陈家变F73410kV陈四线\",\n" +
                "\t\t\t\t\t\t\"equipmentOwner\":\"E4AB99E4F8C46FF6E0430100007F1CB9\",\n" +
                "\t\t\t\t\t\t\"isCommission\":\"0\",\n" +
                "\t\t\t\t\t\t\"startType\":\"1\",\n" +
                "\t\t\t\t\t\t\"overheadMethod#Name\":\"单联络\",\n" +
                "\t\t\t\t\t\t\"psrId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" +
                "\t\t\t\t\t\t\"startStation#Name\":\"陈家66kV变电站\",\n" +
                "\t\t\t\t\t\t\"astId\":\"98280B26-DA9E-4805-97F7-DE3401F2B620-07710\",\n" +
                "\t\t\t\t\t\t\"maintOrg#Name\":\"国网盘山县供电公司\",\n" +
                "\t\t\t\t\t\t\"name\":\"陈家变F734 10kV陈四线\",\n" +
                "\t\t\t\t\t\t\"lineNature#Name\":\"馈线\",\n" +
                "\t\t\t\t\t\t\"overheadLength\":12.2873,\n" +
                "\t\t\t\t\t\t\"cableLength\":0.0000,\n" +
                "\t\t\t\t\t\t\"psrState\":\"20\",\n" +
                "\t\t\t\t\t\t\"regionalism\":\"06\",\n" +
                "\t\t\t\t\t\t\"city\":\"14383AB1B9D0422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"runDevName\":\"1232\",\n" +
                "\t\t\t\t\t\t\"psrState#Name\":\"在运\",\n" +
                "\t\t\t\t\t\t\"startTime\":\"2015-07-11 00:00:00\",\n" +
                "\t\t\t\t\t\t\"pubPrivFlag\":\"0\",\n" +
                "\t\t\t\t\t\t\"startSwitch\":\"陈四线F734断路器\",\n" +
                "\t\t\t\t\t\t\"transferMark\":\"3\",\n" +
                "\t\t\t\t\t\t\"startType#Name\":\"间隔\",\n" +
                "\t\t\t\t\t\t\"maintGroup#Name\":\"配电运检班\",\n" +
                "\t\t\t\t\t\t\"maintGroup\":\"14383AB1BA32422DE0541CC1DE1077D5\",\n" +
                "\t\t\t\t\t\t\"length\":12.2873,\n" +
                "\t\t\t\t\t\t\"dispatchOrg#Name\":\"国网盘锦供电公司电力调度控制中心\",\n" +
                "\t\t\t\t\t\t\"lineNature\":\"5\",\n" +
                "\t\t\t\t\t\t\"city#Name\":\"国网盘锦供电公司\",\n" +
                "\t\t\t\t\t\t\"voltageLevel\":\"22\",\n" +
                "\t\t\t\t\t\t\"colorCode\":\"01\",\n" +
                "\t\t\t\t\t\t\"equipmentOwner#Name\":\"张艳华\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"searchCount\":true,\n" +
                "\t\t\t\"size\":20,\n" +
                "\t\t\t\"total\":45833\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"status\":\"000000\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(s2);
        JSONArray dataList = jsonObject.getJSONObject("result").getJSONObject("xl").getJSONArray("records");
        for (int i = 0; i < dataList.size(); i++) {
            int finalI = i;
            dataList.getJSONObject(i).getJSONObject("resource").forEach((k, v) -> {
                if (k.contains("#")) {
                    dataList.getJSONObject(finalI).remove(k);
                    dataList.getJSONObject(finalI).put(k.replaceAll("#", ""), v);
                }
            });
        }
        System.out.println(jsonObject.get("access_token"));
    }


}
