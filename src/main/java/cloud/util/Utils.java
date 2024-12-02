package cloud.util;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.transport.http.DeploymentDescriptorParser;

import java.io.IOException;
import java.util.Arrays;

public class Utils {
    private final static String SIG_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALgRBDP5sUjVlz+hg7xqVnQfk6xk"
            + "KeeY7DgxP9BDCpRSKmU6gHpaVSI7vJ7uSB5vVhcquencIETQaC7+lNC1VSrWaoIzCoD4Sba2hMWG"
            + "JpiwXS/40my+ikKYwvmstNDw2Yi0ZyAJ+6KMZhygg7fMXUWpa3tK1c6adN0y1iYJfhZLAgMBAAEC"
            + "gYAoiJbEW1gPEfocqRS5iTFAoTOD4wrXVXWvaikDU8AgqXdsk1V3nCzjPQRse0ymZx4uO9p1BWSV"
            + "ws8uiiF7VHNMe2AvP1U4ci90QrTNVFE66HRC22tN5YKFqWMspSWhK8Zt8/+fSPb7Zg2oCOXDiXOp"
            + "aajKelEkQeCyLGW5fouCAQJBAPS2MxFM/Vv17lb6FtX7bcE3xq25HMP9ADj/KEvfV6KNcVOgGjFA"
            + "kDjDYmgxOd9dnLTX5EcMpBZXvXWe5/peZUMCQQDAjqhCBAu1uvoAllFvfBGAweA3jGAkQ9ZQSEB8"
            + "Us61eHo+ep5i1j2E287SeN64XY0wbBD/496vKmQwln/UAHZZAkEAv80685l7j6OP/t4gHfHm0aXM"
            + "9Ib9s+POU94yEF3qyz6/j4MSZH4tTEBgFjhXGuq9k8UPaFzRE64LknBNdKCeDwJAO3EOYeqrzrrc"
            + "iCffFcJACPZQh/VOjINgekIDnh1V8FEzs2vYzvwB26ybKP/lCRbgCe2iRcwLJxm3+gPLjB9kaQJA"
            + "Im6kusGbv1+U6QKQhdiu/rUOQTqekMOg6rR+c0oTScYIEBzMtyrnlhC7QgOowKAHLFiP0FTTkhQE"
            + "FEPKr1flKw==";

    public static void main(String[] args) throws  IOException {

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
