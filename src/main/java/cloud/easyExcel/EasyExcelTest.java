package cloud.easyExcel;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.io.File;
import java.util.List;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/10/9 14:57
 */
public class EasyExcelTest {


    public static void main(String[] args) {
        quanshehui();
    }


    /**
    * TODO 全社会
    * @return void
    * @author xuhong.ding
    * @since 2021/10/9 14:58
    */
    public static void quanshehui(){
        ExcelWriter write = null;
        String folder = "C:\\Users\\76052\\Desktop\\D5000风光出力keyid需求\\朝阳阜新风电";
        String filePath = "C:\\Users\\76052\\Desktop\\D5000风光出力keyid需求\\朝阳阜新风电\\test.xlsx";
        List<File> files = FileUtil.loopFiles(folder);
        for (int i = 0; i < files.size(); i++) {
            System.out.println(i + ":" + files.size());
            ExcelReader reader = ExcelUtil.getReader(files.get(i));
             write = ExcelUtil.getBigWriter(filePath).write(reader.read());
        }
        write.close();

    }


}
