package nju.software;

import nju.software.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public class Main {

    private static CourseService courseService = new CourseServiceImpl();

    public static void main(String[] args){
        String url = "http://localhost:8080/student/chooseCourse";
        int studentid = 10001;
        int courseid = 30001;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            studentid = 10001 + i;
            Set<Integer> map = new HashSet<>();
            for (int j = 0; j < 5; j++) {

                int ci = random.nextInt(10);
                while(map.contains(ci)){
                    ci = random.nextInt(10);
                }
                map.add(ci);
                courseid = 30001 + ci;
                String param = "studentid="+studentid+"&courseid="+courseid;
                System.out.println(param);
                System.out.println(sendPost(url,param,"utf-8"));
            }
        }
    }

    public static String sendPost(String url, String param,String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(3600);
            conn.setReadTimeout(3600);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);

            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常!"+e);
//            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}
