package cloud.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/5/6 18:17
 */
public class EnumTest {

    public static void main(String[] args) {
        Arrays.stream(XmlCacheEnum.values()).map(XmlCacheEnum::getLabel).distinct().forEach(System.out::println);
        System.out.println(AreaEnum.getByTypeId("3").getaId());
    }

}
