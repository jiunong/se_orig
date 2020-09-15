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
        testMatch();
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
        String pattern1_begin =  "打开(.*)线路刀闸(.*)母线开关";
        String pattern2 =  "合上(.*)(.*)母线刀闸及线路刀闸";
        String pattern1_end =  "线路刀闸(.*)母线开关";
        System.out.println(str2.matches(pattern1_begin));
        System.out.println(str2.matches(pattern1_end));

    }

    static void testEndsWith(){

        String pattern1 =  "打开?线路刀闸及?母线开关";
        String pattern2 =  "合上??母线刀闸及线路刀闸";

        String str1 =  "打开param1线路刀闸及param2母线开关";
        String str2 ="合上param1param2母线刀闸及线路刀闸";
    }

}
