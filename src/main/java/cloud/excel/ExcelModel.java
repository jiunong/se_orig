package cloud.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * TODO
 *	""		""
 *
 *
 *
 *
 *
 * @author xuhong.ding
 * @since 2023/1/10 13:57
 */
@Data
@Builder
public class ExcelModel {

    @ExcelProperty("单位名称")
    private String column0;

    @ExcelProperty("序号")
    private String column1;

    @ExcelProperty("省公司二级单位名称（规范简称）")
    private String column2;

    @ExcelProperty("新提拔领导人员名单")
    private String column3;

    @ExcelProperty("新提拔领导人员身份证号（与梯队系统一致）")
    private String column4;


    @ExcelProperty("新提拔领导人员出生年月")
    private String column5;



    @ExcelProperty("新提拔领导人员原任职务")
    private String column6;


    @ExcelProperty("新提拔领导人员现任职务")
    private String column7;

    @ExcelProperty("新提拔领导人员任职时间")
    private String column8;

    @ExcelProperty("column9")
    private String column9;


    private String column10;

    private String column11;

    private String column12;

    private String column13;

    private String column14;
    private String column15;
    private String column16;
    private String column17;
    private String column18;
    private String column19;
    private String column20;

}
