package com.cloud.strings;

import java.io.*;
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
        /*testEndsWith();*/
        //testsp();
       readToString("F:\\python\\dms_yx_nx.E");
    }



    /**
    * TODO 测试replace
    *<p>replaceAll 可以采用正则<p/>
    * @author xuhong.ding
    * @since 2020/9/10 10:48
    */
    static void testReplace() throws Exception{
/*
        String str = "打开【主上符合】开关";
        System.out.println(str.replaceAll("[【】]",""));
        System.out.println(str.replace('【','1').replace("】","2"));
*/
    }

    static String readToString(String fileName) {
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

    static  void testsp(){
        String var = "#\tNULL\t3800756610523992894\t四平 乙线37+1#-四平乙线38#\tPD_10100000_797628\t0\t-8991449\t-8991434\t\n" +
                "112871465660973063\t0\t0.000000\t3799912185593856062";

        String[] split = var.split("\\t+");
        String[] splits = var.split("\\s");
        Arrays.asList(split).stream().forEach(u ->{System.out.println(u);});
        //Arrays.asList(splits).stream().forEach(u ->{System.out.println(u);});
    }

}
