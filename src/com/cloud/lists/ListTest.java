package com.cloud.lists;

import com.cloud.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/23 13:30
 */
public class ListTest {


    public static void main(String[] args) {
        test2();
    }

    static void test2(){
        List<Model> list = new ArrayList();
        Model model ;
        for (int i = 0; i < 10; i++) {
            model = new Model();
            model.setKey("key"+i);
            model.setValue("value"+i);
            list.add(model);
           // model =null;
            model.setKey("key");
            model.setValue("value");
        }
        for (Model model1 : list) {
            System.out.println(model1.toString());
        }
    }


}
