package cloud.threads;

import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaticModel {

    public  List<String> list = new ArrayList<String>();

    public  void add() {
        list.add(String.valueOf(new Date().getTime()));
        list.stream().forEach(System.out::print);
        System.out.println();
    }

}
