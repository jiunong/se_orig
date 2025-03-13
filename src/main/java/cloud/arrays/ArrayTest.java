package cloud.arrays;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.List;

public class ArrayTest {

    public static void main(String[] args) {
        List<String> strings = ListUtil.of("111", "121", "311", "14", "151", "161");
        String[] array = strings.toArray(new String[strings.size() + 4]);
        String[] array1 = new String[]{"9039329139123"};
        String[] strings1 = ArrayUtil.addAll(array1, array);
        for (int i = 0; i < strings1.length; i++) {
            System.out.println(strings1[i]);
        }

        System.out.println();
    }

}
