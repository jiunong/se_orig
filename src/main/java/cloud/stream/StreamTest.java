package cloud.stream;

import java.util.Arrays;
import java.util.IllegalFormatException;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/15 8:47
 */
public class StreamTest {



    public static void main(String[] args) {
        maxDouble("null","12","13","112");
        //System.out.println(parseDouble(null));
    }


    public static void maxDouble(String... param){
        double asDouble = Arrays.asList(param).stream().mapToDouble(StreamTest::parseDouble).max().getAsDouble();
        System.out.println(asDouble);
    }

    public static Double parseDouble(String param) {
        Double res = 0.0;
        try {
            res = Double.parseDouble(param);
        } catch (IllegalFormatException e1) {
            //info
        } catch (NullPointerException e2){
            //info
        }finally {
            return res;
        }
    }

}
