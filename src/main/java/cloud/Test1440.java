package cloud;

import java.util.*;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/4/12 16:03
 */
public class Test1440 {


    public static void main(String[] args) {
        test();
    }


    public static void test() {
        Set<Integer> set = new TreeSet<Integer>(Integer::compareTo);

        //构造数据
        int length = 1440;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) Math.floor(Math.random() * 100 - 60);
        }
        System.out.println("转换前数据");
        Arrays.stream(array).forEach(u -> System.out.print(u+":"));

        for (int i = 0; i < length; i++) {
            array[i] = array[i] > 0 ? 1 : 0;
        }
        System.out.println("");
        System.out.println("转换后数据");
        Arrays.stream(array).forEach(u -> System.out.print(u+":"));



        for (int i = 0; i < length; i++) {
            int count = 0;
            if (array[i] > 0) { //从大于0的第一个数开始统计
                ArrayList<Integer> list = new ArrayList<Integer>();
                int end = Math.min(i + 5, length);
                for (int j = i; j < end; j++) {
                    if (array[j] > 0) {
                        list.add(j);
                        count++;
                    }
                }
                if (count >= 3) {
                    set.addAll(list);
                }
            }
        }
        System.out.println("");
        System.out.println("下标");
        set.forEach(u -> System.out.print(u+":"));
        /*int currentCount = 0; //当前连续大于0
        int totalCount = 0;  // 总共检查了几个数据
        int current = 0; // 当前判断数据指针
        int start = 0; // 符合数据的 第一个数据指针

        for (int i = start; i < array.length; i++) {
            if (array[i] > 0) {
                currentCount++;
                current = i;
                totalCount++;
            }
            if (array[i] < 0) {
                start = i + 1;
            }
            // 检查数据大于等于5
            if (totalCount >= 5 && currentCount <3){
                i = start;
                currentCount=0;
                totalCount=0;
            }
        }*/


    }

}
