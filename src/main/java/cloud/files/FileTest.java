package cloud.files;

import cloud.CimGToSvg;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/11/23 15:59
 */
public class FileTest {


    public static void main(String[] args) throws Exception {
        //test1();
       new CimGToSvg().parseGFiles();
    }


    static void test1(){
        String filePath1="D:\\my_rpm.txt";
        String filePath2="D:\\my_rpmAll.txt";
        String s1 = FileUtil.readUtf8String(filePath1);
        String s2 = FileUtil.readUtf8String(filePath2);

        List<String> strings1 = Arrays.asList(s1.split("\n"));
        List<String> strings2 = Arrays.asList(s2.split("\n"));
        List<String> list = ListUtil.list(false);
        List<String> list2 = ListUtil.list(false);
        System.out.println(strings1.size());
        System.out.println(strings2.size());

        strings1.forEach(u->{
            if (!strings2.contains(u)){
                list.add(u);
            }
        });
        strings2.forEach(u->{
            if (!strings1.contains(u)){
                list2.add(u);
            }
        });

        System.out.println("A有b没有"+list.size());
        System.out.println("A没有b有"+list2.size());
        System.out.println(strings1.size());
        System.out.println(strings2.size());
        list2.forEach(System.out::println);

    }

}
