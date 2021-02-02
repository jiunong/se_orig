package cloud.singleton;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/19 15:24
 */
public class SingletonTest {


    public static void main(String[] args) {
        User user1 = User.getInstance("user1");
        //user1.setName("1");
        User user2 = User.getInstance("user2");
        //user2.setName("2");
        User user3 = User.getInstance("user3");
        user3=null;
        //user3.setName("3");
        User user4 = User.getInstance("user4");
        System.out.println(user1.getClass());
        System.out.println(user2);
        System.out.println(user3);

        System.out.println(user1== user2);
        System.out.println(user1== user4);

    }
}
