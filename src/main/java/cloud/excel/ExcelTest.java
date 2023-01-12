package cloud.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2023/1/10 11:57
 */
public class ExcelTest {


    public static void main(String[] args) throws Exception {
        //testCC("票数需求");
        //testCC("新提拔");
        //testCC("升格");
        //testCC("挂职");）
        //testCC("董事");
        //writeExcel("工作联系");
        //copy2Excel("G:\\ccc\\各单位测评信息收集汇总表\\2.2022.01.01-2022.12.31新提拔（含提拔助理副总师）领导人员名单汇总表=3.xlsx");
    }

    static void writeExcel(String key) throws Exception {
        LinkedList<ExcelModel> res = new LinkedList<ExcelModel>();
        String dir = "G:\\ccc\\"; //文件所在文件夹路径
        String resultFile = "G:\\ccc\\result"+ StrUtil.join("-",StrUtil.split(key, 1)) + DateUtil.current() + ".xls";//生成结果路径
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


    static List<Map<String, Object>> readExcel(File v) {
        ExcelListener<Map<String, Object>> listener = new ExcelListener<Map<String, Object>>();
        ExcelReader excelReader = EasyExcelFactory.read(v, null, listener).headRowNumber(0).build();
        excelReader.readAll();
        List<Map<String, Object>> datas = listener.getDatas();
        excelReader.finish();
        return datas;
    }


    static void copy2Excel(String path ) {
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
