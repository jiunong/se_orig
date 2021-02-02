package cloud.map;

import java.util.HashMap;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/14 14:45
 */
public class MapTest {


    public static void main(String[] args) {
        test1();
    }


    static void test1(){
        HashMap<String, String> kv = new HashMap<>();
        kv.put("1","1s");
        kv.put("2","2s");
        kv.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }


}
