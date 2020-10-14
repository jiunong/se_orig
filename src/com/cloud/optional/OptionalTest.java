package com.cloud.optional;

import java.util.Optional;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/14 10:40
 */
public class OptionalTest {


    public static void main(String[] args) throws InterruptedException {
        test1(null);
      /*  test2(null);
        test3("null33##444");*/

        test1("null1#11&&1");
        /*test2("null2#222");*/
    }

    private static void test1(String ori) {
        String s = Optional.ofNullable(ori).map(u -> u.replace("#", "")).map(u -> u.replace("&", "")).orElse("");
        System.out.println(s);
    }

    private static void test2(String ori) {
        String s = Optional.ofNullable(ori).map(u -> u.replace("#", "")).orElseGet(()->"null2");
        System.out.println(s);
    }

    private static void test3(String ori) throws InterruptedException {
        String s = Optional.ofNullable(ori).map(u -> u.replace("#", "")).orElseThrow(() -> new InterruptedException());
        System.out.println(s);
    }


}
