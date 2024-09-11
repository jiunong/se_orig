package cloud.lists;

import cloud.Model;
import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/23 13:30
 */
public class ListTest {


    public static void main(String[] args) {
        //testDistinct();
        test3();
    }


    static void testDistinct() {

        List<String> a = ListUtil.of("a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> a1 = ListUtil.of("a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> b = ListUtil.of("b", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> c = ListUtil.of("c", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> d = ListUtil.of("d", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> e = ListUtil.of("e", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<String> f = ListUtil.of("f", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        List<List<String>> of = ListUtil.of(a, a1, b, c, d, e, f);

        JSONObject data = new JSONObject();
        data.put("data",of);


        List<List<String>> collect = of.stream().distinct().filter(distinctByKey(u -> u.get(0))).collect(Collectors.toList());
        System.out.println(collect);


    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    static void test1() {
        Map map = new HashMap() {{
            put("name", new HashMap<String, String>() {{
                put("value", "");
                put("value", "");
            }});
            put("name2", new HashMap<String, String>() {{
                put("value", "");
                put("value", "");
            }});
            put("name3", new HashMap<String, String>() {{
                put("value", "");
                put("value", "");
            }});
            put("name4", new HashMap<String, String>() {{
                put("value", "");
                put("value", "");
            }});
        }};
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

    static void test2() {
        List<Model> list = new ArrayList();
        Model model;
        for (int i = 0; i < 10; i++) {
            model = new Model();
            model.setKey("key" + i);
            model.setValue("value" + i);
            list.add(model);
            // model =null;
            model.setKey("key");
            model.setValue("value");
        }
        for (Model model1 : list) {
            System.out.println(model1.toString());
        }
    }


    static void test3() {
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
        List<Info> o2f = ListUtil.of(
                Info.builder().count(1).dept("1").build(),
                Info.builder().count(1).dept("2").build(),
                Info.builder().count(1).dept("3").build(),
                Info.builder().count(9).dept("9").build(),
                Info.builder().count(10).dept("10").build(),
                Info.builder().count(1).dept("10").build(),
                Info.builder().count(11).dept("11").build()
        );
        List<Info> collect = of.parallelStream().filter(o1 -> o2f.stream().noneMatch(o1::accept)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

}

@Data
@Builder
class Info {
    private String dept;
    private int count;

    public boolean accept(Info t) {
        return this.count == t.getCount() && this.dept.equals(t.getDept());
    }
}
