package com.cloud.strings;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * TODO
 * 字符串测试类
 * @author xuhong.ding
 * @since 2020/9/10 10:47
 */
public class StringTest {


    public static void main(String[] args) {

        //testReplace();
        //testSpilt();
        //testMatch();
        testEndsWith();
    }



    /**
    * TODO 测试replace
    *<p>replaceAll 可以采用正则<p/>
    * @author xuhong.ding
    * @since 2020/9/10 10:48
    */
    static void testReplace(){
        String str = "打开【主上符合】开关";
        System.out.println(str.replaceAll("[【】]",""));
        System.out.println(str.replace('【','1').replace("】","2"));

    }

    /**
    * TODO <p>String.split 以正则分割</p>
    * @return void
    * @author xuhong.ding
    * @since 2020/9/10 16:24
    */
    static void testSpilt(){
        String str = "打开【主上符合】开关";
        String[] split = str.split("[【】]");
        Arrays.stream(split).forEach(splitStr -> System.out.println(splitStr));
    }

    static void testMatch(){
        String str2 = "打开【主上符合】线路刀闸及123母线开关";
        String pattern1_begin =  "打开(.*)线路刀闸及(.*)母线开关";
        String pattern2 =  "合上(.*)(.*)母线刀闸及线路刀闸";
        String replace1 = pattern2.replace("(.*)", "$1");
        String replace2 = pattern2.replace("(.*)", "$1").replace("(.*)", "$2");
        //String pattern1_end =  "线路刀闸(.*)母线开关";
        //System.out.println(str2.matches(pattern1_begin));
        //System.out.println(str2.matches(pattern1_end));
        System.out.println( str2.replaceAll(pattern1_begin, pattern2.replace("(.*)","$1").replace("(.*)","$2")));

    }

    static void testEndsWith(){
        String str1 =  "拉开param1线路刀闸及param2母线刀闸";

        String pattern1 =  "拉开?线路刀闸及?母线刀闸";
        String pattern2 =  "合上?线路?母线刀闸";
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

}
