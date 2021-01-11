package com.cloud.webflux;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/11 10:27
 */
@FunctionalInterface
public interface OperateNumberFunctions {

        void operate(Integer num);

        default void print(){

        }

}
