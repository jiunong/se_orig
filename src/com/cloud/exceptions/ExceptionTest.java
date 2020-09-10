package com.cloud.exceptions;

/**
 * TODO
 * 异常测试类
 * @author xuhong.ding
 * @since 2020/9/9 9:21
 */
public class ExceptionTest {


    public static void main(String[] args){
            testException1();
    }


    /**
    * TODO
    * <p>try-catch写在循环内部 可以捕获异常后继续执行</p>
    * <p>try-catch写在循环外部 捕获异常后不继续执行</p>
    * @return void
    * @author xuhong.ding
    * @since 2020/9/10 10:54
    */
    static void testException1(){
        String []list = new String[]{"1","2","w","4"};
        for (int i = 0; i < list.length ; i++) {
            try {
                System.out.println(Integer.parseInt(list[i]));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
