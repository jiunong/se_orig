package cloud.lists;

import cloud.Model;
import cn.hutool.core.collection.ListUtil;
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
        test5();
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
                Info.builder().count(1).pId("1").build(),
                Info.builder().count(2).pId("2").build(),
                Info.builder().count(3).pId("3").build(),
                Info.builder().count(4).pId("4").build(),
                Info.builder().count(4).pId("4").build(),
                Info.builder().count(5).pId("5").build(),
                Info.builder().count(6).pId("6").build(),
                Info.builder().count(7).pId("7").build(),
                Info.builder().count(8).pId("8").build(),
                Info.builder().count(9).pId("9").build(),
                Info.builder().count(10).pId("10").build(),
                Info.builder().count(11).pId("11").build()
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
                Info.builder().count(1).pId("1").build(),
                Info.builder().count(2).pId("2").build(),
                Info.builder().count(3).pId("3").build(),
                Info.builder().count(4).pId("4").build(),
                Info.builder().count(4).pId("4").build(),
                Info.builder().count(5).pId("5").build(),
                Info.builder().count(6).pId("6").build(),
                Info.builder().count(7).pId("7").build(),
                Info.builder().count(8).pId("8").build(),
                Info.builder().count(9).pId("9").build(),
                Info.builder().count(10).pId("10").build(),
                Info.builder().count(11).pId("11").build()
        );
        List<Info> o2f = ListUtil.of(
                Info.builder().count(1).pId("1").build(),
                Info.builder().count(1).pId("2").build(),
                Info.builder().count(1).pId("3").build(),
                Info.builder().count(9).pId("9").build(),
                Info.builder().count(10).pId("10").build(),
                Info.builder().count(1).pId("10").build(),
                Info.builder().count(11).pId("11").build()
        );
        List<Info> collect = of.parallelStream().filter(o1 -> o2f.stream().noneMatch(o1::accept)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    static void test4(){
        List<Info> o2f = ListUtil.of(
                Info.builder().limitType("1").count(1).pId("1").build(),
                Info.builder().limitType("1").count(1).pId("2").build(),
                Info.builder().limitType("12").count(1).pId("2").build(),
                Info.builder().limitType("12").count(9).pId("1").build(),
                Info.builder().limitType("13").count(10).pId("1").build(),
                Info.builder().limitType("14").count(1).pId("1").build(),
                Info.builder().limitType("13").count(11).pId("1").build()
        );

        Map<List<String>, List<Info>> collect = o2f.stream().collect(Collectors.groupingBy(m -> Arrays.asList(m.getLimitType(), m.getPId())));
        collect.forEach((k,v)->{
            System.out.print(k.get(0));
            System.out.print("----");
            System.out.print(k.get(1));
            System.out.print("----");
            System.out.print(v.size());
            System.out.println();
        });
    }

    static void test5(){
        List<String> list = ListUtil.list(false);
        for (int i = 1; i < 154; i++) {
            list.add("key" + i);
        }
        List<List<String>> split = ListUtil.split(list, 10);
        split.forEach(u->{
            System.out.println(u.get(0));
            System.out.println(u.get(u.size()-1));
        });


    }
}

@Data
@Builder
class Info {
    private String pId;
    private String limitType;
    private int count;

    public boolean accept(Info t) {
        return this.count == t.getCount() && this.pId.equals(t.getPId());
    }
}
