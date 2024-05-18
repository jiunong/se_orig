package cloud.files;

import cloud.strings.StringTest;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.java.Log;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/11/23 15:59
 */
@Log
public class FileTest {


    public static void main(String[] args) throws Exception {
        test4();
    }


    /**
     *  TODO 文件内容转汉语拼音
     */
    static void changeContentPinyin() {
        String file = "C:\\Users\\76052\\Desktop\\st.txt";
        List<String> strings = FileUtil.readLines(file, CharsetUtil.UTF_8);
        for (int i = 0; i < strings.size(); i++) {
            pinyin(strings.get(i), i + 12);
        }
    }


    /**
     * TODO 文件夹下所有文件名称
     */
    static void getFileNameList(){
        String fileDir = "C:\\Users\\76052\\Desktop\\检查\\计算所\\8-营口\\（营口四季度）配网调控制度落实情况\\配调\\设备异动\\";
        List<File> files = FileUtil.loopFiles(fileDir);
        for (File file : files) {
            String name = file.getName();
            int index = name.indexOf("线");
            System.out.println(file.getName());
            System.out.println(index>0 ? name.substring(0,index): file.getName());
        }
    }

    static void pinyin(String sentence, int i) {
        StringBuilder pinyin = new StringBuilder();
        for (char c : sentence.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) { // 判断是否为汉字
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pinyinArray != null) {
                    pinyin.append(pinyinArray[0].charAt(0));
                }
            } else {
                pinyin.append(c);
            }
        }

        System.out.println(pinyin.toString()
                + '\t' + sentence
                + '\t' + i
                + '\t' + "null"
                + '\t' + "root"
                + '\t' + "2022-10-10 14:24:39.935000"
                + '\t' + "0"
                + '\t' + sentence
                + '\t' + 3
                + '\t' + "PD"
                + '\t' + "0"
                + '\t' + "Y"
                + '\t' + "Y"
                + '\t' + "null"
                + '\t' + "0"
        );
    }

    static void testNr2() {
        List<File> files = FileUtil.loopFiles("D:/data/", u -> u.getName().startsWith("102道杨线单线图"));
        String s = "D:/data/bydate/20230712/150544-TM-102道杨线单线图.sln.svg".replaceAll("/", "s");
        System.out.printf("", s);
    }

    static void testNr() {
        String sp = "-TM-";
        String key = "anshan_海城市_民政二线单线图.sln.svg";
        String nrSvgDir = "D:\\data\\";
        List<File> files = FileUtil.loopFiles(nrSvgDir, f -> !f.getName().contains(sp));
        //遍历每个文件 按照文件最后编辑时间 存储至当天文件夹
        files.forEach(f -> {
            DateTime fileDate = DateTime.of(f.lastModified());
            String date = nrSvgDir.concat("bydate\\").concat(fileDate.toString(DatePattern.PURE_DATE_PATTERN)).concat("\\");
            if (!FileUtil.exist(date)) {
                FileUtil.mkdir(date);
            }
            FileUtil.copy(f, FileUtil.file(date.concat(fileDate.toString(DatePattern.PURE_TIME_FORMAT)).concat(sp).concat(f.getName())), true);
        });
        String file2 = FileUtil.loopFiles(nrSvgDir, f -> f.getName().contains(key)).stream().sorted(Comparator.comparing(File::lastModified).reversed()).sorted(Comparator.comparing(File::getName)).findFirst().map(File::getAbsolutePath).orElse("");
        System.out.println(file2);
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
        String svgNr = "E:\\apache-tomcat-7.0.109-windows-x64\\apache-tomcat-7.0.109\\webapps\\omspdjx_upload\\gis_data\\gis_data\\";
        String svgTomcat = "E:\\apache-tomcat-7.0.109-windows-x64\\apache-tomcat-7.0.109\\webapps\\omspdjx_upload\\svg\\";
        FileUtil.copyFilesFromDir(FileUtil.file(svgNr), FileUtil.file(svgTomcat), true);
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

    static void test7() {
        List<File> files = FileUtil.loopFiles("C:\\Users\\Administrator\\Desktop\\yxbw");
        files.stream().parallel().forEach(u -> {
            FileReader fileReader = new FileReader(u, CharsetUtil.GBK);
            String text = fileReader.readString();
            if (text.contains("<dms_cb_device_yx>")) {
                System.out.println(u.getName() + "包含");
            } else {
                FileUtil.del(u);
            }
            ;
        });
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

    /**
     *
     */
    static void file50() {
        String[] industryTerms = {"云计算", "人工智能", "生物技术", "电子工程", "医学影像", "量子计算", "区块链", "环境科学", "金融风控", "材料科学",
                "电子商务", "人工智能", "机器学习", "区块链", "物联网", "云计算", "虚拟现实", "生物技术", "智能制造", "新能源"};
        String fileName = "";
        int fileCount = 0; // 已生成文件数
        Random random = new Random();
        while (fileCount < 50) { // 随机选取一个行业专用名词
            int termIndex = random.nextInt(industryTerms.length);
            int termIndex2 = random.nextInt(industryTerms.length);
            String term = industryTerms[termIndex]; // 构造文件名（随机选取一个字作为前缀，保证文件名大于6个汉字）
            String term2 = industryTerms[termIndex2]; // 构造文件名（随机选取一个字作为前缀，保证文件名大于6个汉字）
            String prefix = "";
            if (term.length() > 6) {
                prefix = term.substring(random.nextInt(term.length() - 6), random.nextInt(term.length() - 1));
            } else {
                prefix = term;
            }
            fileName = prefix+term2 + ".doc";
            File file = new File(fileName);
            try { // 创建文件并写入内容（内容为行业专用名词）
                FileWriter writer = new FileWriter(file);
                writer.write(term);
                writer.close();
                System.out.println("Created file: " + fileName);
                fileCount++;
            } catch (IOException e) {
                System.out.println("Error creating file: " + fileName);
                e.printStackTrace();
            }
        }
        System.out.println("Generated 50 files.");
    }
}



