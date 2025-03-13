package cloud.math;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/12/13 15:19
 */
public class MathTest {


    public static void main(String[] args) {
            test1();
    }

    static void test1(){

        System.out.println(NumberUtil.isNumber("123.123"));
        System.out.println(NumberUtil.isNumber("-123.123"));
        System.out.println(NumberUtil.isNumber("1023.123"));
        System.out.println(NumberUtil.isNumber("0123.123"));
        System.out.println(NumberUtil.isNumber("a123.123"));
        System.out.println(NumberUtil.isNumber(""));
        System.out.println(NumberUtil.isNumber(null));
        System.out.println(NumberUtil.isNumber(String.valueOf(null)));
        System.out.println(Math.sqrt(4));
        System.out.println(Math.pow(4, 2));
    }

}
