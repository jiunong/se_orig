package cloud.lists;

import cloud.Model;
import cn.hutool.core.collection.ListUtil;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/23 13:30
 */
public class ListTest {


    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        List<Info> of = ListUtil.of(
                Info.builder().count(1).dept("1").build(),
                Info.builder().count(2).dept("2").build(),
                Info.builder().count(3).dept("3").build(),
                Info.builder().count(4).dept("4").build(),
                Info.builder().count(4).dept("4").build(),
                Info.builder().count(5).dept("5").build(),
                Info.builder().count(6).dept("6").build(),
                Info.builder().count(7).dept("7").build(),
                Info.builder().count(8).dept("8").build(),
                Info.builder().count(9).dept("9").build(),
                Info.builder().count(10).dept("10").build(),
                Info.builder().count(11).dept("11").build()
        );

        List<String> collect = of.stream().sorted(Comparator.comparing(Info::getCount).reversed()).limit(5).map(Info::toString).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
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

@Data
@Builder
class Info {
    private String dept;
    private int count;
}