package cloud.strings;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        teststr();
        //testSpilt();
        //testMatch();
        /*testEndsWith();*/
        //testsp();
        //readToStringFileInputStream("F:\\python\\dms_yx_nx.E");
        //readToStringBufferedReader();
        //testContains();
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
        String var = "#\tNULL\t3800756610523992894\t四平 乙线37+1#-四平乙线38#\tPD_10100000_797628\t0\t-8991449\t-8991434\t\n" +
                "112871465660973063\t0\t0.000000\t3799912185593856062";

        String[] split = var.split("\\t+");
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

    static void testContains(){
        String s = "PWYXBW_PWyyyymmddhhmmss.txt";
        System.out.println(s.contains("20201020"));
        System.out.println(s.substring(0,s.length()-10));
    }

    static void testTF(){
        String a = "ete_ad";
        String par2 = "$1.toUpperCase()";
        System.out.println(a.replaceAll("(_[a-z]?)", par2));

    }

    static void teststr(){
        System.out.println("堃");
    }
}
