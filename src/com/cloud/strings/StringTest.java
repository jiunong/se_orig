package com.cloud.strings;

import java.util.Arrays;

/**
 * TODO
 * 字符串测试类
 * @author xuhong.ding
 * @since 2020/9/10 10:47
 */
public class StringTest {


    public static void main(String[] args) {

        //testReplace();
        testSpilt();
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

}
