package com.cloud.hutools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/12/1 15:01
 */
public class HuToolTest {
    /**主网遥信遥测开关变位关键字*/
    private static final String ZW_TEXT_KEY = "<Breaker>";
    private static final String CHARSET_GBK = "GBK";
    /**配网网遥信遥测开关变位关键字*/
    private static final String PW_TEXT_KEY = "<dms_cb_device_yx>";
    public static void main(String[] args) {

        saveRemoteToDataBase();
/*
        getContainsStr();
*/
    }

     static void saveRemoteToDataBase() {
        HashSet<String> tags = new HashSet<>();
        //String yyyyMMdd = DateTime.now().toString(DatePattern.PURE_DATE_PATTERN);
        String yyyyMMdd = "20201030";
        /*获取日期文件夹下所有文件*/
        //File[] ls = FileUtil.ls(SYSTEM.isWindows() ? PROPS.getStr(WINDOWS_REMOTE_DIR).concat(yyyyMMdd) : PROPS.getStr(LINUX_REMOTE_DIR).concat(yyyyMMdd));
        /*获取文件夹下所有符合日期得文件*/
        List<File> files = FileUtil.loopFiles("F:\\data\\remote\\" + yyyyMMdd + "\\",
                (File file) -> file.getName().substring(0, file.getName().length() - 10).contains(yyyyMMdd));
        files.stream().parallel().forEach(u->{
            FileReader fileReader = new FileReader(u, CharsetUtil.GBK);
            if (fileReader.readString().contains("3809200859825387520")) {
//           if (fileReader.readString().contains("<dms_cb_device_yx>")) {
                System.out.print(u.getName());
                //fileReader.readLines().forEach(System.out::println);
                //System.out.println(fileReader.readLines().stream().findFirst().map(t -> t.split("\t")[0]).map(p-> p.split("=")[1]).orElse(""));
                //System.out.println(fileReader.readLines().stream().findFirst().map(t -> t.split("\t")[0]));
                //System.out.println(fileReader.readLines().stream().findFirst().map(p-> p.split("=")[3]));
            }
           /* if (fileReader.readString().contains(PW_TEXT_KEY)) {
                String s = fileReader.readString();
                System.out.print(u.getName());
                String substring = s.substring(s.indexOf(PW_TEXT_KEY) + PW_TEXT_KEY.length(), s.indexOf("</dms_cb_device_yx>"));
                String[] split = substring.split("\n");
                Arrays.stream(split).forEach(t->System.out.println(Arrays.toString(t.split("\t"))));
            }*/
        });
         /*   fileReader.readLines().forEach(p->{
                if (p.contains("<") && !p.contains("!")) {
                    tags.add(p);
                }
            });*/
        /*tags.forEach(System.out::println);*/
    }

    static void getContainsStr(){
        String testS= "1D@3123142124.text";
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher("123123sad ");
        System.out.println(matcher.group());
    }

}
