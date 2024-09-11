package cloud.strings;


import cloud.singleton.Mmdd;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 * 字符串测试类
 *
 * @author xuhong.ding
 * @since 2020/9/10 10:47
 */
public class StringTest {


    public static void main(String[] args) throws Exception {

        System.out.println(containEachOther("智慧944智于乙线,智于甲线,智慧944智校线".split(","), "智校线".split(",")));

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        System.out.printf("总计{}条变压器，目前是第{}个", 1, 2);


        //ystem.out.println(ListUtil.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).subList(0, 4));
        //ashMap<String, Object> of = MapUtil.of("a", "b");
        //ystem.out.println(of.get("a") instanceof  Map ? "yes" : "no");
        //ystem.out.println(StrUtil.join("-",StrUtil.split("abc", 1)));
        //ile file = new File("D:\\\\data\\\\朱碌科东线单线图.sln.svg");
        //tring name = file.getName();
        //tring[] split = name.split("\\.");

        //haveChineseChar("123");
        //haveChineseChar("tring.split 以正则分割");
        //testSpilt();
        //testMatch();
        /*testEndsWith();*/
        //testsp();
        //readToStringFileInputStream("F:\\python\\dms_yx_nx.E");
        //readToStringBufferedReader();
        //testContains();
      /*  String sentence = "iD编号\n身份识别码\n实物Id\n上报单位\n地区\n厂站名称\n厂站类型\n厂站电压等级\n一次设备类型\n一次设备名称\n一次设备电压等级\n跳闸关系\n制造厂家\n保护类别\n保护类别细化\n设备功能配置\n所属安控系统调度命名\n安控站点名\n安控站点类型\n信息是否接入调度主站\n保护型号\n保护类别\n保护分类\n软件版本\n是否六统一设备\n六统一标准版本\n保护名称\n通道信息\n设备属性\n投运时间\n定期检验周期\n上次检验时间\n运行单位\n维护单位\n设计单位\n基建单位\n线路长度\n母线条数\n运行状态\n是否退出运行\n退出运行时间\n厂站管理单位\n厂站最高电压等级\n出厂日期\n出厂编号\n直流额定电压\nCT二次额定电流\n所在屏柜\n实际变比\n额定变比\n准确级\n电源插件型号\n电源插件更换日期\n测距形式\n故障录波器类型\n模拟通道数\n数字通道数\n资产性质\n资产单位\n出口方式\n数据采集方式\n名称属性\n保护套别\n资产编号\n上次检验类型\n上次检验单位\n下次检验类型\n下次检验时间\n新投运日期\n是否统计运行时间\nICD文件名称\n板卡数量\n选配\n场站属性\n是否使用国调下发型号\n备注\n标记\n开关信息\n开入量信息\n装置类型\n装置动作信息上送调度端\n装置告警信息上送调度端\n装置软压板信息上送调度端\n计划接入时间\n低频减载轮次\n动作定值\n典型负荷水平\ncutPrimariEquipmentId\ncutPrimariEquipmentName\n重要用户负荷所占比例\n民生负荷所占比例\n属于有序用电方案\n属于01号事故拉闸序位表\n自动化低频减载监视画面\n";
        System.out.println(sentence);
        StringBuilder pinyin = new StringBuilder();
        for (char c : sentence.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) { // 判断是否为汉字
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pinyinArray != null) {
                    pinyin.append(pinyinArray[0].charAt(0));
                }
            } else {
                pinyin.append(c);
            }
        }
        System.out.println(pinyin);*/
        //testPmsUrl();
        //testPmsUrl2("ewogICJhbGciIDogIkhTMjU2Igp9.ewogICJpc3MiIDogIjdkNjg0YmQ1ZmRmNzExZWRhZWUwOGUwZjYyZmNiMzg4IiwKICAic3ViIiA6ICI3ZDY4NGJkNWZkZjcxMWVkYWVlMDhlMGY2MmZjYjM4OCIsCiAgImlhdCIgOiAxNzEwODk5MDM3Mjc2LAogICJleHAiIDogNzIwMCwKICAianRpIiA6IDEyMwp9.s7lpbsVqKRmWahMS+cp3aFDOYMTkM77JBp6SujMeoGs=");
    }


    private static boolean containEachOther(String[] str1, String[] str2) {
        return Arrays.stream(str1).anyMatch(u -> has(str2, u)) || Arrays.stream(str2).anyMatch(u ->has(str1, u));
    }

    private static boolean has(String[] str1, String str){
        return Arrays.stream(str1).anyMatch(s -> s.contains(str));
    }

    static void testPmsUrl(){
        String urlBase = "curl -v -X POST http://25.67.146.223:30020/baseCenter/oauth2/accessToken -H \"Content-Type:application/json\" -d'{ \"password\":\"无\", \"grant_type\":\"client_credentials\", \"client_secret\":\"OGt0nlkjpBeFMvotOEDJgjj8zQ/3/5QsGEwwv4jYC3cWSadcX0we2wffjyTUYGsK\", \"client_id\":\"7d684bd5fdf711edaee08e0f62fcb388\", \"username\":\"无\"}'";
        System.out.println(urlBase);
    }
    static void testPmsUrl2(String tk){
        String urlBase = "curl -v -X POST http://25.67.146.223:30020/PSRCenter/queryServices/listPropertiesByFilters -H \"Content-Type:application/json\" -H \"x-token:"
                + tk
                + "\" -d'{\"params\": {\"current\": 1,\"fields\": \"\",\"filters\": "
                +"[{\"compare\":\"like\",\"fieldName\":\"name\",\"fieldValue\":\"%10kV鹏欣线%\"},{\"compare\":\"=\",\"fieldName\":\"voltageLevel\",\"fieldValue\":\"22\"}],"
                +"\"orderBy\": \"\",\"page\": 1,\"perpage\": 50,\"size\": 2},\"psrType\": \"xl\",\"distribution\":0}'";
        System.out.println(urlBase);
    }
    static void testDecimal() {
        String s = BigDecimal.valueOf((float) 1 * 100 / (2 + 1)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println(s);
    }

    /**
     * TODO 测试replace
     * <p>replaceAll 可以采用正则<p/>
     *
     * @author xuhong.ding
     * @since 2020/9/10 10:48
     */
    static void testReplace() throws Exception {
        String strBak = "打开【主上符合】开关";
        String str = strBak;
        //System.out.println(str.replaceAll("\\n[^#@]","").replaceAll(" ",""));
        System.out.println(str.replaceAll("[【】]", ""));
        System.out.println(str);
        str = str.replaceAll("[【】]", "");
        System.out.println(str);
        System.out.println(str.replace('【', '1').replace("】", "2"));
        System.out.println(strBak);
    }

    static String readToStringFileInputStream(String fileName) {
        String encoding = "GBK";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        try {
            String s = new String(filecontent, encoding);
            return s;
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO <p>String.split 以正则分割</p>
     *
     * @return void
     * @author xuhong.ding
     * @since 2020/9/10 16:24
     */
    static void testSpilt() {
       /* String str = "打开【主上符合】开关";
        String[] split = str.split("[【】]");
        Arrays.stream(split).forEach(splitStr -> System.out.println(splitStr));*/
        String a = "assess_batch*                                                                10kv园区二号线北路分歧4号                                                                                                        *                                                                37999121855938568710*                                                                                                                                PD_10200001_18609567                                                                                                                                                                                                                                             PD_SBID000000A711F3F633B4496C8F29215EF02989E9                                       0            1            0 *                                                                                                                                   0 262176       65545            0        65545        65545        65545                    0                    0                    0                 0.00                 0.00                 0.00    0    0    0 10200001_18609567                                                                                                                -8916725-8916725";
        Arrays.stream(a.split("\\s")).forEach(System.out::println);
    }

    static void testMatch() {
        String str2 = "打开主上符合线路刀闸及123母线开关";
        String pattern1 = "打开(.*)线路刀闸及(.*)母线开关";
        String pattern2 = "合上$1$2母线刀闸及线路刀闸";
        System.out.println(str2.replaceAll(pattern1, pattern2));
    }

    static void testEndsWith() {
        String str1 = "拉开param1线路刀闸及param2母线刀闸";

        String pattern1 = "拉开?线路刀闸及?母线刀闸";
        String pattern2 = "合上?线路?母线刀闸";
        String replace1 = pattern1.replace("?", "(.*)");
        String replace2 = pattern2.replace("?", "(.*)");

        if (str1.matches(replace1)) {
            int i = 1;
            while (i <= 2) {
                pattern2 = pattern2.replaceFirst("\\?", "\\$" + i);
                i++;
            }
            String s = str1.replaceAll(replace1, pattern2);
            System.out.println(s);
        }

        if (str1.matches(replace2)) {
            int i = 1;
            while (i <= 1) {
                pattern1 = pattern1.replaceFirst("\\?", "\\$" + i);
                i++;
            }
            String s = str1.replaceAll(replace2, pattern1);
            System.out.println(s);
        }

    }

    /**
     * TODO 测试tab和空格分隔
     *
     * @param
     * @return void
     * @author xuhong.ding
     * @since 2020/11/24 8:26
     */
    static void testsp() {
        String var = "#\nNULL\n3800756610523992894\n四平 乙线37+1#-四平乙线38#\nPD_10100000_797628\n0\n-8991449\n-8991434\n\n" +
                "112871465660973063\n0\n0.000000\n3799912185593856062";

        String[] split = var.split("\\n+");
        String[] splits = var.split("\\s");
        Arrays.asList(split).stream().forEach(u -> {
            System.out.println(u);
        });
        //Arrays.asList(splits).stream().forEach(u ->{System.out.println(u);});
    }

    static void readToStringBufferedReader() throws Exception {
        int startIndex = 0;
        int thisIndex = 0;
        int endIndex = 0;
        boolean flag = false;
        //文件内容的字符集 UTF8 或 GBK
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\python\\Substation.E"), "GBK"));
        String line = null;
        String lineNext;
        List<String> list = new ArrayList<>();
        while (reader.ready()) {
            if (line == null) {
                line = reader.readLine();
            }
            lineNext = reader.readLine();
            while (lineNext != null && !lineNext.startsWith("#") && !lineNext.startsWith("<") && !lineNext.startsWith("@")) {
                line += lineNext;
                lineNext = reader.readLine();
            }
            list.add(line.replaceAll("\\n[^#@]", "").replaceAll(" ", ""));
            line = lineNext;
        }
        list.add(line);
        reader.close();
        for (String s : list) {
            System.out.println(s);
        }

    }

    static void testContains() {
        String s = "PWYXBW_PWyyyymmddhhmmss.txt";
        System.out.println(s.contains("20201020"));
        System.out.println(s.substring(0, s.length() - 10));
    }

    static void testTF() {
        // 按指定模式在字符串查找
        String line = "eq-abqyoushi一个设备——";
        String pattern = "eq.*[ab].*";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        /* 现在创建 matcher 对象 */
        Matcher m = r.matcher(line);
        int i = 0;
        if (m.find()) {
            System.out.println("Found value: " + m.group(i));
            i++;
        } else {
            System.out.println("NO MATCH");
        }
    }

    static void teststr() {
        System.out.println("堃");
    }

    static void haveChineseChar(String s) {
        char[] chars = s.toCharArray();
        boolean flag = false;
        int i = 0;
        while (!flag && i < chars.length) {
            flag = String.valueOf(chars[i]).matches("[\u4e00-\u9fa5]");
            i++;
        }
        System.out.println(String.valueOf(flag));
    }

    /**
     * TODO 遍历a
     *
     * @param a :
     * @param b :
     * @return void
     * @author xuhong.ding
     * @since 2021/12/14 14:46
     **/
    public static String dmp(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charsA.length; i++) {
            if (ArrayUtil.contains(charsB, charsA[i])) {
                sb.append(charsA[i]).append(':').append(i).append('-');
            }
        }
        return sb.toString();
    }
}
