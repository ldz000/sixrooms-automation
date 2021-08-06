package java.xiuchang;

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
public class XiuChangLoginAndPay {

    private static AndroidDriver<?> driver;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    TouchAction<?> tAction;

    @BeforeMethod
    public void setup() throws Exception {
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("deviceName", "192.168.26.16:5555");
        capabilities.setCapability("newCommandTimeout", "30");
        capabilities.setCapability("appPackage", "cn.v6.xiuchang");
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

    private boolean waitForElement(final By elementLocator) {
        WebDriverWait w = new WebDriverWait(driver, 25);
        boolean flag = true;
        try {
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Test
    public void testXiuChangPay() {
        try {
            waitForElement(By.id("cn.v6.xiuchang:id/tv_agree"));
            driver.findElementById("cn.v6.xiuchang:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            if (waitForElement(By.id("cn.v6.xiuchang:id/account_login_button"))) {
                driver.findElementById("cn.v6.xiuchang:id/account_login_button").click();
                waitForElement(By.id("cn.v6.sixrooms:id/but_login"));
                driver.findElementById("cn.v6.xiuchang:id/et_username").sendKeys("谜雾迷雾麋了鹿");
                driver.findElementById("cn.v6.xiuchang:id/et_password").sendKeys("1234qwer");
                driver.findElementById("cn.v6.xiuchang:id/but_login").click();
                waitForElement(By.id("android:id/button3"));
                driver.findElementById("android:id/button3").click();
                waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
                driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
                driver.findElementById("cn.v6.xiuchang:id/close").click();
                driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"石榴直播\"])[5]").click();
            } else {
                waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
                driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
                Thread.sleep(15000);
                targetClick(542, 258);
                driver.findElementById("cn.v6.xiuchang:id/close").click();
                targetClick(964, 2224);
                //driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"石榴直播\"])[5]").click();
                waitForElement(By.id("cn.v6.xiuchang:id/gotoLogin"));
                driver.findElementById("cn.v6.xiuchang:id/gotoLogin").click();
                waitForElement(By.id("cn.v6.xiuchang:id/account_login_button"));
                driver.findElementById("cn.v6.xiuchang:id/account_login_button").click();
                waitForElement(By.id("cn.v6.xiuchang:id/but_login"));
                driver.findElementById("cn.v6.xiuchang:id/et_username").sendKeys("谜雾迷雾麋了鹿");
                driver.findElementById("cn.v6.xiuchang:id/et_password").sendKeys("1234qwer");
                driver.findElementById("cn.v6.xiuchang:id/but_login").click();
                waitForElement(By.id("android:id/button3"));
                driver.findElementById("android:id/button3").click();
            }
            waitForElement(By.id("cn.v6.xiuchang:id/tv_six_coin_num"));
            String value_init = driver.findElementById("cn.v6.xiuchang:id/tv_six_coin_num").getText();
            int value1 = Integer.parseInt(value_init.replaceAll(",", "")) + 100;
            driver.findElementById("cn.v6.xiuchang:id/iV_coin_recharge").click();
            waitForElement(By.xpath("//android.view.View[@text='确认支付']"));
            targetClick(907, 1682);
            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
//            waitForElement(By.xpath("//android.view.View[@text='充值详情']"));
//            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='立即支付']"));
            driver.findElementByXPath("//android.widget.TextView[@text='立即支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='请输入支付密码']"));
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            Thread.sleep(10000);
            tAction.press(PointOption.point(57, 169)).release().perform();
            String value_change = driver.findElementById("cn.v6.xiuchang:id/tv_six_coin_num").getText();
            int value2 = Integer.parseInt(value_change.replaceAll(",", ""));
            Assert.assertEquals(value1, value2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
            log.info("六间房秀场充值失败：" + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
