package com.cloud.singleton;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/19 15:24
 */
public class SingletonTest {


    public static void main(String[] args) {

        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
        System.out.println(User.getInstance() == User.getInstance());


    }
}
