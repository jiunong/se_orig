package cloud.hutools;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/12/1 15:01
 */
public class HuToolTest {
    /**
     * 主网遥信遥测开关变位关键字
     */
    private static final String ZW_TEXT_KEY = "<Breaker>";
    private static final String CHARSET_GBK = "GBK";
    /**
     * 配网网遥信遥测开关变位关键字
     */
    private static final String PW_TEXT_KEY = "<dms_cb_device_yx>";

    public static void main(String[] args) {
        //saveRemoteToDataBase();
        //test2("19:30");
        test3();
    }

    static void saveRemoteToDataBase() {
        HashSet<String> tags = new HashSet<>();
        String yyyyMMdd = "20201222";
        List<File> files = FileUtil.loopFiles("F:\\data\\remote\\",
                (File file) -> file.getName().contains(yyyyMMdd));
        System.out.println(files.size());
        files.stream().parallel().forEach(u -> {
            FileReader fileReader = new FileReader(u, CharsetUtil.GBK);
            //System.out.println(fileReader.readString());
            if (fileReader.readString().contains("铁榆甲")
                    /*|| fileReader.readString().contains("重利环网单元")
                    || fileReader.readString().contains("姚千线")
                    || fileReader.readString().contains("钢球分开闭站")
                    || fileReader.readString().contains("铁万分")
                    || fileReader.readString().contains("欧北甲")
                    || fileReader.readString().contains("东化线")
                    || fileReader.readString().contains("安于甲")
                    || fileReader.readString().contains("铁榆乙")
                    || fileReader.readString().contains("孙元线")
                    || fileReader.readString().contains("孙家寨")
                    || fileReader.readString().contains("北顺甲")
                    || fileReader.readString().contains("北顺乙")
                    || fileReader.readString().contains("爱家郦都")*/
                //|| fileReader.readString().contains("铁榆甲")
            ) {
                System.out.println(u.getName());
            }
            //FileUtil.del(u);
        });
    }

    static void getContainsStr() {
        String testS = "1D@3123142124.text";
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher("123123sad ");
        System.out.println(matcher.group());
    }


    static void dateTime() {
        List<Map<String, Object>> list = ListUtil.list(false);
        String year = "2021";
        Calendar calendar = Calendar.getInstance();
        DateTime beginDayOfYear = DateTime.of(year + "0101", DatePattern.PURE_DATE_PATTERN);

        while (DateTime.of(beginDayOfYear).dayOfWeek() != 2) {
            calendar.setTime(beginDayOfYear);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            beginDayOfYear = DateTime.of(calendar.getTime());
        }

        int i = 1;
        while (beginDayOfYear.before(DateTime.of(year + "1231", DatePattern.PURE_DATE_PATTERN))
        ) {
            String weekStart = DateTime.of(beginDayOfYear).toString("MM-dd");
            calendar.setTime(beginDayOfYear);
            calendar.add(Calendar.DAY_OF_YEAR, 6);
            String weekEnd = DateTime.of(calendar.getTime()).toString("MM-dd");

            HashMap<String, Object> map = MapUtil.newHashMap(false);
            map.put("CODE", i < 10 ? "0" + i : i);
            map.put("NOTE", "(" + weekStart + "~" + weekEnd + ")");
            list.add(map);

            i++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            beginDayOfYear = DateTime.of(calendar.getTime());
        }
        list.forEach(u -> System.out.println(u.toString()));
    }

    static String H = "HH";  // 9 || 09
    static String HH = "HH时"; // 9时 || 09时
    static String HHmm = "HH时mm分"; // 9时30分
    static String HH_mm = "HH:mm"; //09:22

    static void test1(String time){
            if(time.contains("分")){
                System.out.println(DateTime.of(time, HHmm).toString("HH:mm"));
            }else if(time.contains("时")){
                System.out.println(DateTime.of(time, HH).toString("HH:mm"));
            }else if(time.contains(":")){
                System.out.println(DateTime.of(time, HH_mm).toString("HH:mm"));
            }else if(time.length()<=2){
                System.out.println(DateTime.of(time, H).toString("HH:mm"));
            }else {
                System.out.println(DateTime.of(time, H).toString("HH:mm"));
            }
    }
    static void test2(String time){
        System.out.println(DateUtil.parse(time, "H").toString("H"));
    }
    static void test3(){
        char a = (char)65;

        System.out.println(String.valueOf(a));
    }
}
