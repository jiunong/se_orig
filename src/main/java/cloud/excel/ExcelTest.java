package cloud.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2023/1/10 11:57
 */
public class ExcelTest {
    static File file1 = FileUtil.file("C:\\Users\\76052\\Desktop\\检查\\测试线路\\o.txt");
    static File file2 = FileUtil.file("C:\\Users\\76052\\Desktop\\检查\\测试线路\\p.txt");
    static File file3 = FileUtil.file("C:\\Users\\76052\\Desktop\\检查\\测试线路\\op.txt");

    public static void main(String[] args) throws Exception {
//        DateTime parse1 = DateUtil.parse("20230101", DatePattern.PURE_DATE_PATTERN);
//        DateTime parse2 = DateUtil.parse("20231231", DatePattern.PURE_DATE_PATTERN);
//        while (parse1.before(parse2)){
//            System.out.println("rm -rf /home/putfile/remote/YXBW*"+parse1.toString( DatePattern.PURE_DATE_PATTERN) + "*");
//            System.out.println("echo "+"rm -rf /home/putfile/remote/YXBW"+parse1.toString( DatePattern.PURE_DATE_PATTERN) + "*");
//            parse1 = DateUtil.offsetDay(parse1, 1);
//
//        }

        //testCC("票数需求");
        //testCC("新提拔");
        //testCC("升格");
        //testCC("挂职");）
        //testCC("董事");
        //writeExcel("工作联系");
        //copy2Excel("G:\\ccc\\各单位测评信息收集汇总表\\2.2022.01.01-2022.12.31新提拔（含提拔助理副总师）领导人员名单汇总表=3.xlsx");
        //readExcelByColum("C:\\Users\\76052\\Desktop\\检查\\测试线路\\");
        readSingleExcel(FileUtil.file("D:\\data\\eqp.xls"));
    }

    static void writeExcel(String key) throws Exception {
        LinkedList<ExcelModel> res = new LinkedList<ExcelModel>();
        String dir = "G:\\ccc\\"; //文件所在文件夹路径
        String resultFile = "G:\\ccc\\result" + StrUtil.join("-", StrUtil.split(key, 1)) + DateUtil.current() + ".xls";//生成结果路径
        List<File> newFile = FileUtil.loopFiles(dir, u -> u.getName().contains(key));

        List<File> s = newFile.stream().sorted(Comparator.comparingInt(v -> Integer.parseInt(v.getAbsolutePath().split("\\\\")[3].split("\\.")[0]))).collect(Collectors.toList());

        s.forEach(v -> {
            System.out.println(v.getAbsolutePath());
            List<Map<String, Object>> maps = readExcel(v);//maps.get(2)开始
            boolean find = false;
            for (int j = 0; j < maps.size(); j++) {
                try {
                    int ooo = Integer.parseInt((String) maps.get(j).get(0));
                    find = true;
                    ExcelModel build = ExcelModel.builder()
                            .column0(v.getAbsolutePath().split("\\\\")[3].split("\\.")[0] + v.getAbsolutePath().split("\\\\")[3].split("-")[1])
                            .column1(Optional.ofNullable(maps.get(j).get(0)).map(String::valueOf).orElse(""))
                            .column2(Optional.ofNullable(maps.get(j).get(1)).map(String::valueOf).orElse(""))
                            .column3(Optional.ofNullable(maps.get(j).get(2)).map(String::valueOf).orElse(""))
                            .column4(Optional.ofNullable(maps.get(j).get(3)).map(String::valueOf).orElse(""))
                            .column5(Optional.ofNullable(maps.get(j).get(4)).map(String::valueOf).orElse(""))
                            .column6(Optional.ofNullable(maps.get(j).get(5)).map(String::valueOf).orElse(""))
                            .column7(Optional.ofNullable(maps.get(j).get(6)).map(String::valueOf).orElse(""))
                            .column8(Optional.ofNullable(maps.get(j).get(7)).map(String::valueOf).orElse(""))
                            .column9(Optional.ofNullable(maps.get(j).get(8)).map(String::valueOf).orElse(""))
                            .column10(Optional.ofNullable(maps.get(j).get(9)).map(String::valueOf).orElse(""))
                            .column11(Optional.ofNullable(maps.get(j).get(10)).map(String::valueOf).orElse(""))
                            .column12(Optional.ofNullable(maps.get(j).get(11)).map(String::valueOf).orElse(""))
                            .column13(Optional.ofNullable(maps.get(j).get(12)).map(String::valueOf).orElse(""))
                            .column14(Optional.ofNullable(maps.get(j).get(13)).map(String::valueOf).orElse(""))
                            .column15(Optional.ofNullable(maps.get(j).get(14)).map(String::valueOf).orElse(""))
                            .column16(Optional.ofNullable(maps.get(j).get(15)).map(String::valueOf).orElse(""))
                            .column17(Optional.ofNullable(maps.get(j).get(16)).map(String::valueOf).orElse(""))
                            .column18(Optional.ofNullable(maps.get(j).get(17)).map(String::valueOf).orElse(""))
                            .column19(Optional.ofNullable(maps.get(j).get(18)).map(String::valueOf).orElse(""))
                            .column20(Optional.ofNullable(maps.get(j).get(19)).map(String::valueOf).orElse(""))
                            .build();

                    res.add(build);
                } catch (Exception e) {
                }
            }
            if (!find) {
                ExcelModel build = ExcelModel.builder()
                        .column0(v.getAbsolutePath().split("\\\\")[3].split("\\.")[0] + v.getAbsolutePath().split("\\\\")[3].split("-")[1])
                        .column1("无")
                        .column2("无")
                        .column3("无")
                        .column4("无")
                        .column5("无")
                        .column6("无")
                        .column7("无")
                        .column9("无")
                        .column8("无").build();

                res.add(build);
            }
        });
        EasyExcel.write(resultFile, ExcelModel.class)
                .sheet("模板")
                .doWrite(res);
        System.out.println("成功生成数据" + res.size() + resultFile);

    }


    /**
     * TODO 读取文件夹下所有excel 指定名称列的所有内容
     */
    static void readExcelByColum(String fileDir) {
        List<File> files = FileUtil.loopFiles(fileDir, f -> f.getName().endsWith(".xlsx"));
        files.forEach(ExcelTest::readExcel);

    }


    static List<Map<String, Object>> readExcel(File v) {
        String t1 = "检修线路";
        String t2 = "线路名称";
        String t3 = "供电单位";
        AtomicReference<String> xlIndex = new AtomicReference<>("");
        AtomicReference<String> dwIndex = new AtomicReference<>("");
        ExcelListener<Map<String, Object>> listener = new ExcelListener<Map<String, Object>>();
        ExcelReader excelReader = EasyExcelFactory.read(v, null, listener).headRowNumber(0).build();
        excelReader.readAll();
        List<Map<String, Object>> datas = listener.getDatas();
        Map<String, Object> map = datas.get(0);
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            if (stringObjectEntry.getValue().equals(t1) || stringObjectEntry.getValue().equals(t2)) {
                xlIndex.set(stringObjectEntry.getKey());
            }
            if (stringObjectEntry.getValue().equals(t3)) {
                dwIndex.set(stringObjectEntry.getKey());
            }
        }
        for (int i = 1; i < datas.size(); i++) {
            String line = Optional.ofNullable(datas.get(i).get(xlIndex.get())).map(String::valueOf).orElse("");
            String dw = Optional.ofNullable(datas.get(i).get(dwIndex.get())).map(String::valueOf).orElse("");
            String[] split = line.split("、|，|\n|；|,");
            for (String s : split) {
                String sbak = "";
                s = s.toLowerCase();
                int kv = s.lastIndexOf("kv");
                int qianfu = s.indexOf("千伏");
                int xl = s.indexOf("线");
                FileUtil.appendUtf8String(dw + "\t" + s + "\n", file1);
                if ((kv > -1 || qianfu > -1) && xl > -1) {
                    if (kv > -1 && qianfu > -1) {
                        sbak = s.substring(kv + 2, qianfu);
                    } else {
                        sbak = s.substring(kv > -1 ? kv + 2 : qianfu + 2, xl);
                    }

                } else {
                    sbak = s;
                }
                FileUtil.appendUtf8String(dw + "\t" + sbak + "\n", file2);
            }
        }
        excelReader.finish();
        return datas;
    }

    static List<Map<String, Object>> readSingleExcel(File v) {
        ExcelListener<Map<String, Object>> listener = new ExcelListener<Map<String, Object>>();
        ExcelReader excelReader = EasyExcelFactory.read(v, null, listener).headRowNumber(0).build();
        excelReader.readAll();
        List<Map<String, Object>> datas = listener.getDatas();
        datas.forEach(d -> {
            d.entrySet().forEach(e->{
                FileUtil.appendUtf8String(   e.getValue().toString().replace("\n","") + "\t", file3);
            });
            FileUtil.appendUtf8String("\n", file3);
        });
        excelReader.finish();
        return datas;
    }

    static void copy2Excel(String path) {
        LinkedList<ExcelModel> res = new LinkedList<ExcelModel>();
        String resultFile = "G:\\ccc\\result" + DateUtil.current() + ".xls";
        File v = FileUtil.file(path);
        List<Map<String, Object>> maps = readExcel(v);//maps.get(2)开始
        for (int j = 0; j < maps.size(); j++) {
            try {
                int ooo = Integer.parseInt((String) maps.get(j).get(1));
                ExcelModel build = ExcelModel.builder()
                        .column0(Optional.ofNullable(maps.get(j).get(0)).map(String::valueOf).orElse(""))
                        .column1(Optional.ofNullable(maps.get(j).get(1)).map(String::valueOf).orElse(""))
                        .column2(Optional.ofNullable(maps.get(j).get(2)).map(String::valueOf).orElse(""))
                        .column3(Optional.ofNullable(maps.get(j).get(3)).map(String::valueOf).orElse(""))
                        .column4(Optional.ofNullable(maps.get(j).get(4)).map(String::valueOf).orElse(""))
                        .column5(Optional.ofNullable(maps.get(j).get(4)).map(String::valueOf).orElse(""))
                        .column6(Optional.ofNullable(maps.get(j).get(6)).map(String::valueOf).orElse(""))
                        .column7(Optional.ofNullable(maps.get(j).get(7)).map(String::valueOf).orElse(""))


                        .column8(Optional.ofNullable(maps.get(j).get(8)).map(String::valueOf).orElse(""))
                        .column9(Optional.ofNullable(maps.get(j).get(9)).map(String::valueOf).orElse(""))
                        .column10(Optional.ofNullable(maps.get(j).get(10)).map(String::valueOf).orElse(""))
                        .column11(Optional.ofNullable(maps.get(j).get(11)).map(String::valueOf).orElse(""))
                        .column12(Optional.ofNullable(maps.get(j).get(12)).map(String::valueOf).orElse(""))
                        .column13(Optional.ofNullable(maps.get(j).get(13)).map(String::valueOf).orElse(""))
                        .column14(Optional.ofNullable(maps.get(j).get(14)).map(String::valueOf).orElse(""))
                        .column15(Optional.ofNullable(maps.get(j).get(15)).map(String::valueOf).orElse(""))
                        .column16(Optional.ofNullable(maps.get(j).get(16)).map(String::valueOf).orElse(""))
                        .column17(Optional.ofNullable(maps.get(j).get(17)).map(String::valueOf).orElse(""))
                        .column18(Optional.ofNullable(maps.get(j).get(18)).map(String::valueOf).orElse(""))
                        .column19(Optional.ofNullable(maps.get(j).get(19)).map(String::valueOf).orElse(""))
                        .build();

                res.add(build);
            } catch (Exception e) {
            }
        }
        EasyExcel.write(resultFile, ExcelModel.class)
                .sheet("模板")
                .doWrite(res);
        System.out.println("成功生成数据" + res.size() + resultFile);


    }

}
