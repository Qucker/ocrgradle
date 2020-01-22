package aliyunpost;

import java.net.*;
import java.util.Base64;
import com.sun.deploy.net.HttpRequest;
import org.json.JSONObject;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
public class AliyunPost {
    static public String url=
            "https://ocrapi-invoice.taobao.com/ocrservice/invoice";
    static private URL realUrl;
    static private String appCode = "1034b77c9e14441691bb05113c281331";
    static public void InitUrl(String urlinput) {
        url=urlinput;
        try{
            realUrl = new URL(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void InitUrl() {
        try{
            realUrl = new URL(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void SetAppCode(String appcode) {
        appCode=appcode;
    }

    static public JSONObject post(String path){
        String result = "";
        try{
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();

            connection.addRequestProperty("Content-Type"
                    ,"application/json");

            connection.addRequestProperty("charset"
                    ,"UTF-8");

            connection.addRequestProperty("Authorization"
                    ,"APPCODE "+appCode);

            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            JSONObject json = new JSONObject();
            Base64.Encoder encoder = Base64.getEncoder();
            File file = new File(path);
            BufferedImage bufferedImage = ImageIO.read(file);

            OutputStream outputStream= new ByteArrayOutputStream();
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);

            //System.out.print(outputStream.toString());
            //System.out.print(encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));
            json.append("img",encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));

            //connection.connect();
            if (json.toString() != null) {
                byte[] writebytes = json.toString().getBytes();
                // 设置文件长度
                connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                try{
                    OutputStream out = connection.getOutputStream();
                    out.write(json.toString().getBytes());
                    out.flush();
                    out.close();
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                        result = reader.readLine();
                        //System.out.print(result);

                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode()); }


        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new JSONObject(result);
    }
    static public JSONObject post(InputStream input){
        String result = "";
        try{
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();

            connection.addRequestProperty("Content-Type"
                    ,"application/json");

            connection.addRequestProperty("charset"
                    ,"UTF-8");

            connection.addRequestProperty("Authorization"
                    ,"APPCODE "+appCode);

            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            JSONObject json = new JSONObject();
            Base64.Encoder encoder = Base64.getEncoder();
            BufferedImage bufferedImage = ImageIO.read(input);

            OutputStream outputStream= new ByteArrayOutputStream();
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);

            //System.out.print(outputStream.toString());
            //System.out.print(encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));
            json.append("img",encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));

            //connection.connect();
            if (json.toString() != null) {
                byte[] writebytes = json.toString().getBytes();
                // 设置文件长度
                //connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                try{
                    OutputStream out = connection.getOutputStream();
                    out.write(json.toString().getBytes());
                    out.flush();
                    out.close();
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                        result = reader.readLine();
                        //System.out.print(result);

                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode()); }


        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new JSONObject(result);
    }


    static public String postReturnString(InputStream input){
        String result = "";
        try{
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();

            connection.addRequestProperty("Content-Type"
                    ,"application/json");

            connection.addRequestProperty("charset"
                    ,"UTF-8");

            connection.addRequestProperty("Authorization"
                    ,"APPCODE "+appCode);

            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            JSONObject json = new JSONObject();
            Base64.Encoder encoder = Base64.getEncoder();
            BufferedImage bufferedImage = ImageIO.read(input);

            OutputStream outputStream= new ByteArrayOutputStream();
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);

            //System.out.print(outputStream.toString());
            //System.out.print(encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));
            json.append("img",encoder.encodeToString(((ByteArrayOutputStream) outputStream).toByteArray()));

            //connection.connect();
            if (json.toString() != null) {
                byte[] writebytes = json.toString().getBytes();
                // 设置文件长度
                //connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                try{
                    OutputStream out = connection.getOutputStream();
                    out.write(json.toString().getBytes());
                    out.flush();
                    out.close();
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                        result = reader.readLine();
                        //System.out.print(result);

                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode()); }


        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

