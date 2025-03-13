package cloud.PINYIN;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * @author ding
 * @since 2024年12月24日 17:26
 */
public class PyTest {

    public static void main(String[] args) {
        System.out.println(PinyinUtil.getFirstLetter("风电转商运合计_2023", "").toUpperCase());
    }

}
