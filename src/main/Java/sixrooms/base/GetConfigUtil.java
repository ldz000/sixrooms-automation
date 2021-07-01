package sixrooms.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 获取配置文件，键值对方式
 */
public class GetConfigUtil {

    public static String app_path = null;
    public static ResourceBundle testProperties = null;


    public static ResourceBundle getTestProperties(String file) {
        if (testProperties != null) {
            return testProperties;
        }
        testProperties = getProperties("/config/" + file + ".properties");
        return testProperties;
    }

    public static ResourceBundle getProperties(String location) {
        PropertyResourceBundle bundle = null;
        try {
            String path = getBasePath();
            InputStream in = new BufferedInputStream(new FileInputStream(path + location));
            System.out.println("##############" + path);
            System.out.println("##############" + location);
            bundle = new PropertyResourceBundle(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bundle;
    }


    public static String getTestProperty(String file, String key) {
        ResourceBundle bundle = getTestProperties(file);
        return bundle.getString(key);
    }

    public static String getBasePath() {
        if (app_path == null) {
            app_path = System.getProperty("user.dir");
        }
        System.out.println("*****************app_path" + app_path);
        return app_path;
    }
}
