package cloud.datetime;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/21 16:10
 */
public class DateTimeTest {


    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        /*String date= "2020-10-30T18:17:33";*/
        String date1= "20201030181733811";
        String date= "PWEWIEJ_asd20201030181733811.text";
        System.out.println(date.substring(date.length() - date1.length()-5, date.lastIndexOf(".")));
    }


}
