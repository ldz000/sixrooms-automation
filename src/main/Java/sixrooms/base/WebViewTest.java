package sixrooms.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;

/**
 * Author: zh
 * Date: 2021/6/23 15:29
 */

public class WebViewTest {

    public static AndroidDriver<?> driver;

    TouchAction<?> tAction;

    @Test
    public void setUp() throws Exception {
        // 设置属性
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "everything");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.v6.sixrooms");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cn.v6.sixrooms.ui.phone.SplashActivity");
        capabilities.setCapability("chromedriverExecutableDir", "D:/automation/chrome/chromedriver83");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
        Thread.sleep(10000);
        Set<String> set = driver.getContextHandles();
        for (String element : set) {
            System.out.println(element);
        }
        driver.context((String) (set.toArray()[1]));
        driver.findElementByXPath("//*[@id=\"eno\"]").click();
        Thread.sleep(10000);
        driver.context((String) (set.toArray()[0]));
        driver.findElementById("cn.v6.sixrooms:id/titlebar_close").click();
    }

    public void webViewCut(String context) {
        Set<String> set = driver.getContextHandles();
        for (String element : set) {
            System.out.println(element);
        }
        if ("webView".equals(context)) {
            driver.context((String) (set.toArray()[1]));
        } else if ("native".equals(context)) {
            driver.context((String) (set.toArray()[0]));
        } else {
            throw new RuntimeException("切换WebView参数不正确");
        }
    }
}
