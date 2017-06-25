package config;

import nju.software.config.ServerConfig;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * 测试读取配置文件
 */
public class ConfigReadTest {

    public static void main(String[] args) throws Exception {
        System.out.println(ServerConfig.JAVA_DB_INSTITUTION);
    }
}
