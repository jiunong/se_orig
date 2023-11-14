package cloud.util;

import java.util.Arrays;

public class Utils {

    public static void main(String[] args) {
        System.out.println("alias spa='sshpass'");
        String file = "listRbbj.jsp";
        String path = "jsp/pwrz/rbbj";
        sy(file,path);
        as(file,path);
        fs(file,path);
        fx(file,path);
        cy(file,path);
    }


    static void sum(int ...args){
        System.out.println(Arrays.stream(args).sum());
    }
    static void sy(String file,String path){
        System.out.println(
                "spa -p root.2020 scp /home/sict/"+file+" root@10.160.49.90:/home/data/apache-tomcat-7.0.109/webapps/omspdjx_sy_plus/WEB-INF/classes/cn/ac/sict/"+path
        );
        System.out.println(
                "spa -p root.2020 scp /home/sict/"+file+" root@10.160.49.90:/home/"+path
        );
    }
    static void as(String file,String path){
        System.out.println(
                "spa -p root.2020 scp /home/sict/"+file+" root@10.21.195.40:/home/pdjx/apache-tomcat-7.0.109/webapps/omspdjx_as_plus/WEB-INF/classes/cn/ac/sict/"+path
        );
        System.out.println(
                "spa -p root.2020 scp /home/sict/"+file+" root@10.21.195.40:/home/"+path
        );
    }
    static void cy(String file,String path){
        System.out.println(
                "spa -p Sict.2020 scp -P 20022 /home/sict/"+file+" root@10.21.191.52:/application/apache-tomcat-7.0.109/webapps/omspdjx_cy/WEB-INF/classes/cn/ac/sict/"+path
        );
        System.out.println(
                "spa -p Sict.2020 scp -P 20022 /home/sict/"+file+" root@10.21.191.52:/home/"+path
        );
    }
    static void fx(String file,String path){
        System.out.println(
                "spa -p Sict.2020 scp -P 20022  /home/sict/"+file+" root@10.21.192.16:/application/apache-tomcat-7.0.109/webapps/omspdjx_fx/WEB-INF/classes/cn/ac/sict/"+path
        );
        System.out.println(
                "spa -p Sict.2020 scp -P 20022  /home/sict/"+file+" root@10.21.192.16:/home/"+path
        );
    }
    static void fs(String file,String path){
        System.out.println(
                "spa -p NQXFc407@ scp -P 10022  /home/sict/"+file+" root@10.161.146.172:/home/pdjx/apache-tomcat-7.0.109/webapps/omspdjx_fs_plus/WEB-INF/classes/cn/ac/sict/"+path
        );
        System.out.println(
                "spa -p NQXFc407@ scp -P 10022  /home/sict/"+file+" root@10.161.146.172:/home/"+path
        );
    }

}
