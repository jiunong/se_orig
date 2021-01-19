package com.cloud.singleton;

/**
 * TODO 枚举单例
 *
 * @author xuhong.ding
 * @since 2021/1/19 15:23
 */

public class User {
    //私有化构造函数
    private User() {
    }

    //定义一个静态枚举类
    private enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private User user;

        //私有化枚举的构造函数
        SingletonEnum() {
            user = new User();
        }

        private User getInstnce() {
            return user;
        }
    }

    //对外暴露一个获取User对象的静态方法
    public static User getInstance() {
        return SingletonEnum.INSTANCE.getInstnce();
    }
}

