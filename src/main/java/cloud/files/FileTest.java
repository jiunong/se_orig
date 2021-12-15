package cloud.files;

import cloud.model.FeederEnergyModel;
import cloud.strings.StringTest;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import javafx.scene.control.Tab;
import lombok.extern.java.Log;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/11/23 15:59
 */
@Log
public class FileTest {


    public static void main(String[] args) throws Exception {
        feederEnergy("E:\\Result_81.html");
    }


    static void test1() {
        String filePath1 = "D:\\my_rpm.txt";
        String filePath2 = "D:\\my_rpmAll.txt";
        String s1 = FileUtil.readUtf8String(filePath1);
        String s2 = FileUtil.readUtf8String(filePath2);

        List<String> strings1 = Arrays.asList(s1.split("\n"));
        List<String> strings2 = Arrays.asList(s2.split("\n"));
        List<String> list = ListUtil.list(false);
        List<String> list2 = ListUtil.list(false);
        System.out.println(strings1.size());
        System.out.println(strings2.size());

        strings1.forEach(u -> {
            if (!strings2.contains(u)) {
                list.add(u);
            }
        });
        strings2.forEach(u -> {
            if (!strings1.contains(u)) {
                list2.add(u);
            }
        });

        System.out.println("A有b没有" + list.size());
        System.out.println("A没有b有" + list2.size());
        System.out.println(strings1.size());
        System.out.println(strings2.size());
        list2.forEach(System.out::println);

    }

    static void test2(String name) {

        String svgNr = "F:\\data\\svgNr\\";
        String svgTomcat = "F:\\data\\svgTomcat\\";
        String svgBak = "F:\\data\\svgBak\\";
        List<File> files = FileUtil.loopFiles(svgNr, u -> u.getName().contains(name));

        files.forEach(v -> {
            log.info("找到图并插入数据库，接下来做对比");
            System.out.println(v.lastModified());//修改时间
            FileUtil.move(FileUtil.file(svgTomcat + v.getName()), FileUtil.file(svgBak + v.getName()), true);
            log.info("svgTomcat->svgBak");

            FileUtil.move(v, FileUtil.file(svgTomcat + v.getName()), true);
            log.info("svgNr->svgTomcat");
        });
        FileUtil.del(svgNr + name);
    }

    static void test3() {
        System.out.println(FileUtil.getWebRoot());
        System.out.println(FileUtil.getLineSeparator());
        System.out.println(PathUtil.TOMCAT_WEBAPP_PATH());
    }

    static void test4() {
        String svgNr = "F:\\data\\svgNr\\";
        String svgTomcat = "F:\\data\\svgTomcat\\";
        String svgNrBak = "F:\\data\\svgNrBak\\";
        FileUtil.copyFilesFromDir(FileUtil.file(svgNr), FileUtil.file(svgNrBak), true);
        /*List<File> files = FileUtil.loopFiles(Paths.get(svgNr), 1, null);
        files.forEach(u->{
            FileUtil.copy(u,FileUtil.file(svgNrBak),true);

        });*/
    }

    static void test5() {
        List<File> files = FileUtil.loopFiles("F:\\data\\svg20210730", u -> !u.getName().contains("task"));
        //List<File> files = FileUtil.loopFiles("F:\\programSoft\\tomcat-all\\tomcat-all\\apache-tomcat-7.0.96\\webapps\\omspdjx_upload\\svg", u -> u.getName().contains("svg"));
        files.forEach(v -> {
            FileUtil.copy(v, FileUtil.file("F:\\programSoft\\tomcat-all\\tomcat-all\\apache-tomcat-7.0.96\\webapps\\omspdjx_upload\\svg"), true);
        });
    }

    static void test6() {
        File file = FileUtil.file("C:\\svg\\response.json");
        boolean json = JSONUtil.isJson(FileUtil.readString(file, CharsetUtil.UTF_8));
        JSONObject jsonObject = JSONUtil.parseObj(FileUtil.readString(file, CharsetUtil.UTF_8));
        JSONArray list = (JSONArray) JSONUtil.parseObj(FileUtil.readString(file, CharsetUtil.UTF_8)).get("content");
        System.out.println("a");
    }

    static void feederEnergy(String filePath) {
        String tab = "\t";
        String fake = "";
        File touch = FileUtil.touch("E:\\result.txt");
        String s = FileUtil.readString(filePath, "UTF-8");
        Document doc = Jsoup.parse(s);
        Elements trs = doc.select("tr");
        for (int i = 1; i < trs.size(); i++) {
            String energyId = trs.get(i).select("td").get(0).text();
            if (!fake.equals(energyId)) {
                fake = energyId;
                FileUtil.appendUtf8String("\n", touch);
            }
            String energyName = trs.get(i).select("td").get(1).text();
            String stationId = trs.get(i).select("td").get(2).text();
            String feederId = trs.get(i).select("td").get(3).text();
            String feederName = trs.get(i).select("td").get(4).text();
            String stationIdB = trs.get(i).select("td").get(5).text();
            String contain = StringTest.dmp(feederName, energyName);
            FileUtil.appendUtf8String(energyId.concat(tab).concat(energyName)
                            .concat(tab).concat(stationId)
                            .concat(tab).concat(feederId)
                            .concat(tab).concat(feederName)
                            .concat(tab).concat(stationIdB)
                            .concat(tab).concat(contain).concat("\n")
                    , touch);
        }
    }
}
