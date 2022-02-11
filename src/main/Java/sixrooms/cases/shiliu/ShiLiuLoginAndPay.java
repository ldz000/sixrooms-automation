package sixrooms.cases.shiliu;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * 微信8.0（微信版本强制必须更新到8.0以上）以上版本不支持获取微信支付密码的页面
 * 涉及充值的业务无法自动化
 */

@Deprecated
public class ShiLiuLoginAndPay {

    private static AndroidDriver<?> driver;

    private static TouchAction<?> tAction;

    @BeforeMethod
    public void setUp() {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), getCapabilities());
            tAction = new TouchAction<>(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testShiLiuPay() {
        waitForElement(By.id("cn.v6.sixrooms:id/tv_agree"));
        driver.findElementById("cn.v6.sixrooms:id/tv_agree").click();
        waitForElement(By.id("eno"));
        for (int i = 0; i < 4; i++) {
            if (!waitForElement(By.id("cn.v6.sixrooms:id/close"))) {
                targetClick(964, 2243);
            }
        }
        driver.findElementById("cn.v6.sixrooms:id/close").click();
        driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"石榴直播\"])[6]").click();
        waitForElement(By.id("cn.v6.sixrooms:id/gotoLogin"));
        driver.findElementById("cn.v6.sixrooms:id/gotoLogin").click();
        waitForElement(By.id("cn.v6.sixrooms:id/agreement_select_tag"));
        driver.findElementById("cn.v6.sixrooms:id/agreement_select_tag").click();
        driver.findElementById("cn.v6.sixrooms:id/account_login_button").click();
        waitForElement(By.id("cn.v6.sixrooms:id/but_login"));
        driver.findElementById("cn.v6.sixrooms:id/et_username").sendKeys("谜雾迷雾麋了鹿");
        driver.findElementById("cn.v6.sixrooms:id/et_password").sendKeys("1234qwer");
        driver.findElementById("cn.v6.sixrooms:id/registerSelectTag").click();
        driver.findElementById("cn.v6.sixrooms:id/but_login").click();
        waitForElement(By.id("cn.v6.sixrooms:id/username"));
        String num1 = driver.findElementById("cn.v6.sixrooms:id/tv_six_coin_num").getText().replaceAll(",", "");
        int coin_num1 = Integer.parseInt(num1);
        driver.findElementById("cn.v6.sixrooms:id/iv_coin_recharge").click();
        waitForElement(By.id("cn.v6.sixrooms:id/coinTv"));
        targetClick(201, 831);
        driver.findElementById("cn.v6.sixrooms:id/rechargeBtn").click();
        waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"立即支付\"]"));
        driver.findElementByAccessibilityId("立即支付").click();
        try {
            Thread.sleep(10000);
            targetClick(191, 1800);
            Thread.sleep(10000);
            targetClick(530, 1985);
            Thread.sleep(10000);
            targetClick(894, 2126);
            Thread.sleep(10000);
            targetClick(561, 1808);
            Thread.sleep(10000);
            targetClick(548, 1958);
            Thread.sleep(10000);
            targetClick(199, 1806);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
        driver.findElementByAccessibilityId("返回商家").click();
        waitForElement(By.id("cn.v6.sixrooms:id/banlanceTv"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String num2 = driver.findElementById("cn.v6.sixrooms:id/banlanceTv").getText().replaceAll(",", "");
        int coin_num2 = Integer.parseInt(num2);
        Assert.assertEquals(coin_num1+100, coin_num2);
        waitForElement(By.id("cn.v6.sixrooms:id/backIv"));
        driver.findElementById("cn.v6.sixrooms:id/backIv").click();
        waitForElement(By.id("cn.v6.sixrooms:id/tv_six_coin_num"));
        String num3 = driver.findElementById("cn.v6.sixrooms:id/tv_six_coin_num").getText().replaceAll(",", "");
        int coin_num3 = Integer.parseInt(num3);
        Assert.assertEquals(coin_num2, coin_num3);
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

    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2250) * y2))).release().perform();
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.26.16:5555");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.v6.sixrooms");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cn.v6.sixrooms.ui.phone.SplashActivity");
        return capabilities;
    }

}
