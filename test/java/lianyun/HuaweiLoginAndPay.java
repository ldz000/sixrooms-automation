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
public class HuaweiLoginAndPay {

    public static AndroidDriver<?> driver;

    public static TouchAction<?> tAction;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Test(description = "华为联运包登录与支付case")
    public void testHuaweiLoginAndPay() {
        try {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/third_login_text"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/third_login_text").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            //允许位置权限
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
            driver.findElementById("com.tencent.tmgp.sixrooms:id/close").click();
            //点击我的页面
            driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"六间房直播\"])[5]").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/username"));
            String value = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/roomId")).getText();
            Assert.assertNotNull(value);
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/iV_coin_recharge"));
            String value_init = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            //点击充值
            driver.findElementById("com.tencent.tmgp.sixrooms:id/iV_coin_recharge").click();
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            //driver.findElementByAndroidUIAutomator("new UiSelector().text(\"确认支付\")").click();
            waitForElement(By.id("com.huawei.hwid:id/huaweipay_recharge"));
            driver.findElementById("com.huawei.hwid:id/huaweipay_recharge").click();
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"立即支付\"]"));
            driver.findElementByAccessibilityId("立即支付").click();
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            waitForElement(By.id("com.huawei.hwid:id/pay_result_ok"));
            driver.findElementById("com.huawei.hwid:id/pay_result_ok").click();
            int value_expect = Integer.parseInt(value_init.replaceAll(",", "")) + 500;
            Thread.sleep(13000);
            targetClick(57, 169);
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num"));
            String value_changed = driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/tv_six_coin_num")).getText();
            int value_change = Integer.parseInt(value_changed.replaceAll(",", ""));
            Assert.assertEquals(value_expect, value_change);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("华为联运包登录与支付case执行失败" + e.getMessage());
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
