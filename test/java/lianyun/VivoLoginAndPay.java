package java.lianyun;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

@Slf4j
@Deprecated
public class VivoLoginAndPay {

    public static AndroidDriver<?> driver;

    public static TouchAction<?> tAction;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Test(description = "vivo联运包登录与支付case")
    public void testVivoLoginAndPay() {
        try {
            //点击同意个人信息
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            //手机存储权限
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            //获取电话权限
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/account_login_button"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/account_login_button").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/et_username"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/et_username").sendKeys("谜雾迷雾麋了鹿");
            driver.findElementById("com.tencent.tmgp.sixrooms:id/et_password").sendKeys("1234qwer");
            driver.findElementById("com.tencent.tmgp.sixrooms:id/but_login").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
            driver.findElementById("com.tencent.tmgp.sixrooms:id/close").click();
            driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"六间房直播\"])[5]").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/username"));
            String value_roomId = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/roomId")).getText();
            org.junit.Assert.assertNotNull(value_roomId);
            String value_init = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            //点击充值
            driver.findElementById("com.tencent.tmgp.sixrooms:id/iV_coin_recharge").click();
            //点击支付
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            //driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            //vivo支付会记录上次支付的方式，选择微信支付后下次要注释掉代码
            //waitForElement(By.id("com.vivo.sdkplugin:id/payment_activity_submit"));
            //androidDriver.findElementById("com.vivo.sdkplugin:id/payment_activity_submit").click();
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"立即支付\"]"));
            driver.findElementByXPath("//android.widget.TextView[@content-desc=\"立即支付\"]").click();
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByXPath("//android.widget.TextView[@content-desc=\"返回商家\"]").click();
            waitForElement(By.id("com.vivo.sdkplugin:id/vivo_payment_result_btn"));
            driver.findElementById("com.vivo.sdkplugin:id/vivo_payment_result_btn").click();
            int value_expect = Integer.parseInt(value_init.replaceAll(",", "")) + 100;
            Thread.sleep(13000);
            targetClick(57, 169);
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num"));
            String value_changed = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            int value_change = Integer.parseInt(value_changed.replaceAll(",", ""));
            Assert.assertEquals(value_expect, value_change);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("vivo联运包登录与支付case执行失败" + e.getMessage());
            Assert.fail();
        }
    }

    @BeforeMethod
    public void setup() throws Exception {
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("deviceName", "192.168.26.16:5555");
        capabilities.setCapability("newCommandTimeout", "30");
        capabilities.setCapability("appPackage", "com.tencent.tmgp.sixrooms");
        capabilities.setCapability("appActivity", "cn.v6.sixrooms.ui.phone.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2232) * y2))).release().perform();
    }

    /*
      判断元素定位后是否可见
     */
    public void waitForElement(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 25);
        try {
            webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
        } catch (Exception e) {
            log.info("元素没有在页面出现:" + e.getMessage());
        }
    }
}
