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
public class XiaomiLoginAndPay {

    public static AndroidDriver<?> driver;

    public static TouchAction<?> tAction;

    DesiredCapabilities capabilities = new DesiredCapabilities();

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

    @Test(description = "小米联运包登录与支付case")
    public void testXiaomiLoginAndPay() {
        try {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/third_login_text"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/third_login_text").click();
            waitForElement(By.id("username"));
            //输入账户和密码
            driver.findElementById("username").sendKeys("18944633508");
            driver.findElementById("pwd").sendKeys("1234qwer");
            //点击登录
            driver.findElementById("login-button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
            driver.findElementById("com.tencent.tmgp.sixrooms:id/close").click();
            driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"六间房直播\"])[5]").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/roomId"));
            String value = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/roomId")).getText();
            org.junit.Assert.assertNotNull(value);
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/iV_coin_recharge"));
            //点击充值
            String value_init = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            driver.findElementById("com.tencent.tmgp.sixrooms:id/iV_coin_recharge").click();
            //点击支付
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            //driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mio_tv_pay"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/mio_tv_pay").click();
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"立即支付\"]"));
            driver.findElementByAccessibilityId("立即支付").click();
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"完成\"]"));
            driver.findElementByAccessibilityId("完成").click();
            int value_expect = Integer.parseInt(value_init.replaceAll(",", "")) + 100;
            Thread.sleep(13000);
            targetClick(57, 169);
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num"));
            String value_changed = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            int value_change = Integer.parseInt(value_changed.replaceAll(",", ""));
            Assert.assertEquals(value_expect, value_change);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("小米联运包登录与支付case执行失败" + e.getMessage());
            Assert.fail();
        }
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
