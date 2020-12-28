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
    }

     static void saveRemoteToDataBase() {
        HashSet<String> tags = new HashSet<>();
        String yyyyMMdd = "20201222";
         List<File> files = FileUtil.loopFiles("F:\\data\\remote\\",
                (File file) -> file.getName().contains(yyyyMMdd));
         System.out.println(files.size());
         files.stream().parallel().forEach(u->{
            FileReader fileReader = new FileReader(u, CharsetUtil.GBK);
             //System.out.println(fileReader.readString());
             if (fileReader.readString().contains("铁榆甲")
                    /*|| fileReader.readString().contains("重利环网单元")
                    || fileReader.readString().contains("姚千线")
                    || fileReader.readString().contains("钢球分开闭站")
                    || fileReader.readString().contains("铁万分")
                    || fileReader.readString().contains("欧北甲")
                    || fileReader.readString().contains("东化线")
                    || fileReader.readString().contains("安于甲")
                    || fileReader.readString().contains("铁榆乙")
                    || fileReader.readString().contains("孙元线")
                    || fileReader.readString().contains("孙家寨")
                    || fileReader.readString().contains("北顺甲")
                    || fileReader.readString().contains("北顺乙")
                    || fileReader.readString().contains("爱家郦都")*/
                    //|| fileReader.readString().contains("铁榆甲")
             ) {
                System.out.println(u.getName());
            }
             //FileUtil.del(u);
        });
    }

    static void getContainsStr(){
        String testS= "1D@3123142124.text";
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher("123123sad ");
        System.out.println(matcher.group());
    }


}
