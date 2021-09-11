package cloud.files;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/11/23 15:59
 */
@Log
public class FileTest {


    public static void main(String[] args) throws Exception {
        test5();

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

    static void test2(String name){

        String svgNr = "F:\\data\\svgNr\\";
        String svgTomcat = "F:\\data\\svgTomcat\\";
        String svgBak = "F:\\data\\svgBak\\";
        List<File> files = FileUtil.loopFiles(svgNr,u->u.getName().contains(name));

        files.forEach(v->{
            log.info("找到图并插入数据库，接下来做对比");
            System.out.println(v.lastModified());//修改时间
            FileUtil.move(FileUtil.file(svgTomcat+v.getName()),FileUtil.file(svgBak+v.getName()),true);
            log.info("svgTomcat->svgBak");

            FileUtil.move(v,FileUtil.file(svgTomcat+v.getName()),true);
            log.info("svgNr->svgTomcat");
        });
        FileUtil.del(svgNr+name);
    }
    static void test3(){
        System.out.println(FileUtil.getWebRoot());
        System.out.println(FileUtil.getLineSeparator());
        System.out.println(PathUtil.TOMCAT_WEBAPP_PATH());
    }

    static void test4(){
        String svgNr = "F:\\data\\svgNr\\";
        String svgTomcat = "F:\\data\\svgTomcat\\";
        String svgNrBak = "F:\\data\\svgNrBak\\";
       FileUtil.copyFilesFromDir(FileUtil.file(svgNr),FileUtil.file(svgNrBak),true);
        /*List<File> files = FileUtil.loopFiles(Paths.get(svgNr), 1, null);
        files.forEach(u->{
            FileUtil.copy(u,FileUtil.file(svgNrBak),true);

        });*/
    }

    static void test5(){
        List<File> files = FileUtil.loopFiles("F:\\data\\svg20210730", u -> !u.getName().contains("task"));
        //List<File> files = FileUtil.loopFiles("F:\\programSoft\\tomcat-all\\tomcat-all\\apache-tomcat-7.0.96\\webapps\\omspdjx_upload\\svg", u -> u.getName().contains("svg"));
        files.forEach(v->{
            FileUtil.copy(v,FileUtil.file("F:\\programSoft\\tomcat-all\\tomcat-all\\apache-tomcat-7.0.96\\webapps\\omspdjx_upload\\svg"),true);
        });
    }
}
