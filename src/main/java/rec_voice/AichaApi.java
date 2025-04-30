package rec_voice;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AichaApi {


    private final static String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcHBEZElEIjo2OTksIkFwcElEIjoiYzY1MDU4NjNmYjZmNGU1NzlkZmQwYzg5MWVkNzVlODgiLCJNYXhDb25jdXJyZW50Ijo1MDAsIlVzZXJJRCI6MTA2NiwiZXhwIjoxNzQxNjYyNDk2LCJpYXQiOjE3NDE2NTUyOTYsIm1vZGUiOjR9.BtEfUo07Z0mAmWjfQtD7HpKNdch3sddQuwDwgF7qka8";

    public static void main(String[] args) throws Exception {
        //auth();
        String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "dingxuhong_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "chenyufeng_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "songci_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "tangrui_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "limeihui_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "yangshuyuan_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "liuyan_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "liujun_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "wanghongyu_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "ludi_ti";
        //String sessionId =String.valueOf( new Date().getTime()), text_type = "ti", sample_rate = "16000", skpId = "lixiaoyu_ti";

       /* System.out.println("声纹接口查存");
        checkExist(skpId, text_type, sample_rate);

        ////语音文件
        System.out.println("开始上传音频文件");
        uploadVoice(sessionId, text_type, sample_rate, skpId+".wav");

        System.out.println("开始注册声纹");
        createVoice(skpId, sessionId, text_type, sample_rate);

        System.out.println("开始查询列表");
        getList(text_type, sample_rate);*/

        identify("录音 (7).wav", text_type, sample_rate);
    }


    public static void auth() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("app_id", "c6505863fb6f4e579dfd0c891ed75e88");
        json.put("app_secret", "000cbb6a61a24cfba430cdf7762ddc69");
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Request request = new Request.Builder().url("https://aihc.shengwenyun.com/aihc/auth").method("POST", body).addHeader("Content-Type", "application/json").build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
    }

    public static boolean uploadVoice(String sessionId, String text_type, String sample_rate, String fileName) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("session_id", sessionId)
                .addFormDataPart("text_type", text_type).addFormDataPart("sample_rate", sample_rate)
                .addFormDataPart("step", "1")
                .addFormDataPart("wav_file", fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("D:\\PRO\\se_orig\\src\\main\\java\\rec_voice\\"+fileName)))
                .addFormDataPart("asrEnabled", "false").build();
        Request request = new Request.Builder().url("https://aihc.shengwenyun.com/aihc/v1/vpr/upload")
                .method("POST", body).addHeader("Authorization", TOKEN).build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
        return true;
    }

    public static boolean createVoice(String skpId, String sessionId, String text_type, String sample_rate) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("session_id", sessionId);
        json.put("spkid", skpId);
        json.put("text_type", text_type);
        json.put("sample_rate", 16000);
        json.put("return_feature", true);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Request request = new Request.Builder().url("https://aihc.shengwenyun.com/aihc/v1/vpr/enroll")
                .method("POST", body).addHeader("Authorization", TOKEN)
                .addHeader("Content-Type", "application/json").build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
        return true;
    }

    public static void getList(String text_type, String sample_rate) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().followRedirects(true).followSslRedirects(true).build();
        Request request = new Request.Builder().url("https://aihc.shengwenyun.com/aihc/v1/vpr?text_type=" + text_type + "&sample_rate=" + sample_rate).method("GET", null).addHeader("Authorization", TOKEN).build();
        Response response = client.newCall(request).execute();
        // 读取响应体内容
        String responseBody = response.body().string();
        System.out.println(responseBody);
    }

    public static boolean checkExist(String spkid, String text_type, String sample_rate) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("https://aihc.shengwenyun.com/aihc/v1/vpr/exist?spkid=" + spkid + "&text_type=" + text_type + "&sample_rate=" + sample_rate).method("GET", null).addHeader("Authorization", TOKEN).build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
        return true;
    }

    public static void identify(String fileName, String text_type, String sample_rate) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("topn", "10")
                .addFormDataPart("sample_rate", sample_rate)
                .addFormDataPart("text_type", text_type)
                .addFormDataPart("file_identify", fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("D:\\PRO\\se_orig\\src\\main\\java\\rec_voice\\" + fileName)))
                .build();
        Request request = new Request.Builder()
                .url("https://aihc.shengwenyun.com/aihc/v1/vpr/identify")
                .method("POST", body)
                .addHeader("Authorization", TOKEN)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
    }


    public static void  speakerSplit(String fileName,String sample_rate) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("D:\\PRO\\se_orig\\src\\main\\java\\rec_voice\\" + fileName)))
                .addFormDataPart("sample_rate",sample_rate)
                .build();
        Request request = new Request.Builder()
                .url("https://aihc.shengwenyun.com/aihc/v1/speaker/ksd")
                .method("POST", body)
                .addHeader("Authorization", TOKEN)
                .build();
        Response response = client.newCall(request).execute();
    }

    public static void getSpeakerSplitFile(String fileName,String sample_rate) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://aihc.shengwenyun.com/aihc/v1/speaker/download?file=20220107/36c1c3d6bd0609baa6ee9149da83ec14_485764388184236032.zip")
                .method("GET", null)
                .addHeader("Authorization", TOKEN)
                .build();
        Response response = client.newCall(request).execute();
    }
}
