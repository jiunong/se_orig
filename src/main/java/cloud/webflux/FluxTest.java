package cloud.webflux;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/11 10:30
 */
public class FluxTest {


    public static void main(String[] args) {
        NumberFunctions.of(10).add(10).subtraction(2).filter(number -> number >10).get().operate(System.out::println);
    }

}
