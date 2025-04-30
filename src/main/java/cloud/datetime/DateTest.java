package cloud.datetime;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


public class DateTest {
    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);


    public static void main(String[] args) {
        //logger.info("{}开始执行给省调传分布式承载力数据",new Date());
        //test2();
        System.out.println(DateUtil.format(DateUtil.offsetMonth(DateUtil.date(), -1), "yyyy-MM"));
    }


    static void test1(){
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
    }








    static void test2(){
        String s = "2024-02-01";
        String e = "2024-12-31";

        LocalDate parse = LocalDate.parse(s);
        LocalDate parse2 = LocalDate.parse(e);
        List<String> list = ListUtil.list(false);
        while (!parse2.isBefore(parse)){
            list.add(parse.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            parse=  parse.plus(1, ChronoUnit.DAYS);

        }
        list.forEach(System.out::println);
    }
}
