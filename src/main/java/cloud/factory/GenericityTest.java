package cloud.factory;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/5/7 10:08
 */
public class GenericityTest {


    public static void main(String[] args) {
        Generality name = Generality.builder().age(1).name("name").build();
        test( t -> name);

    }


    static void test(GeneralityInterface<Object> t) {
        System.out.println(t);
    }

}
