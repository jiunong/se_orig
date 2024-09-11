package cloud.image;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

public class ImageTest {


    public static void main(String[] args) {


        try {
            // 将base64字符串解码为字节数组
            byte[] imageData = Base64.getDecoder().decode("sd");
            // 创建一个空的输出流
            OutputStream outputStream = new FileOutputStream("output.jpg");
            // 将字节数组写入输出流
            outputStream.write(imageData);
            // 关闭输出流
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
