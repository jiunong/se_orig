package cloud.datetime;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/21 16:10`
 */
public class DateTimeTest {


    public static void main(String[] args) {
       test6();
       //now.format(DateTimeFormatter.ofPattern("yyyyMMddhh1500"))
        //now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        //now.getMinute()
    }


    static void test6() {
        LocalDate now1 = LocalDate.now();
        String yyyyMMdd = LocalDate.now().plus(-1, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yyyyMMdd2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime now = LocalDateTime.now();
        LocalTime now2 = LocalTime.now();
        Instant now3 = Instant.now();
        System.out.println("Now is now " + now1);
    }

    static void test1(){
        /*String date= "2020-10-30T18:17:33";*/
        String date1= "20201030181733811";
        String date= "PWEWIEJ_asd20201030181733811.text";
        System.out.println(date.substring(date.length() - date1.length()-5, date.lastIndexOf(".")));
    }

    static void test2() throws Exception{
        String date = "22";
        Calendar calendar = DateUtil.parse(date, "yyyy-MM").toCalendar();
        calendar.add(Calendar.MONTH,1);
        System.out.println(DateUtil.format(calendar.getTime(), "yyyy-MM"));
    }

    static void test3(){
        System.out.println(DateTime.now().toString("yyyyMMddHH"));

        System.out.println();
  /*      System.out.println(DateTime.now().toString(DatePattern.PURE_DATE_PATTERN));

        Calendar calendar = CalendarUtil.calendar(DateTime.now());
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println(DateUtil.format(calendar.getTime(), DatePattern.PURE_DATE_PATTERN));*/

        //String a = "#\t1\t3809200859825364433\t2020-12-15T00:04:24\t南五乙F环网单元102负荷开关事故总\t0\t方太乙线\t0\n"

    }

    static void test4(Date date) {

        "DATA_".concat(DateUtil.format(date, "H"))
                .concat("_")
                .concat(String.valueOf(StrictMath.floorDiv(Integer.parseInt(DateUtil.format(date, "mm")), 15)*15));
    }

    static void test5(){
        System.out.println(DateUtil.dayOfWeek(new Date()));
    }

}
