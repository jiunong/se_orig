package com.cloud.strings;

/**
 * TODO
 * 字符串测试类
 * @author xuhong.ding
 * @since 2020/9/10 10:47
 */
public class StringTest {


    public static void main(String[] args) {
            testReplace();
    }



    /**
    * TODO 测试replace
    * @author xuhong.ding
    * @since 2020/9/10 10:48
    */
    static void testReplace(){
        String str = "打开【主上符合】开关";
        System.out.println(str.replaceAll("[【】]",""));
        System.out.println(str.replace('【','1').replace("】","2"));

    }

}
