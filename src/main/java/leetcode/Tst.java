package leetcode;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/11/17 15:32
 */
public class Tst {

    public int[] twoSum(int[] nums, int target) {
        int a = 0, b = 0;
        boolean flag = true;
        while (a < nums.length - 1 && flag) {
            for (int i = a + 1; i < nums.length; i++) {
                if (nums[a] + nums[i] == target) {
                    b = i;
                    flag = false;
                }
            }
        }
        return new int[]{a, b};
    }

}
