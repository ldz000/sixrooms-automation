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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

/**
 * 执行完case后执行DeleteFansCard
 */

public class OpenFCShiLiu {

    public static AndroidDriver<?> driver;
    TouchAction<?> tAction;
    String roomId = "851980";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "everything");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.v6.sixrooms");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cn.v6.sixrooms.ui.phone.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBackOpenFC() {
        sleepForElement(6);
        targetClick(65, 173);
        try {
            Runtime.getRuntime().exec("adb shell input text " + roomId);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (waitForElement(By.id("cn.v6.sixrooms:id/tv_living"))) {
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        }
        targetClick(533, 1183);
        waitForElement(By.id("cn.v6.sixrooms:id/tv_host_name"));
        for (int i = 1; i <= 5; i++) {
            System.out.println("应用准备切换到后台:第" + i + "次");
            driver.runAppInBackground(Duration.ofSeconds(5));
            sleepForElement(5);
        }
        sleepForElement(3);
        targetClick(1005, 2248);
        waitForElement(By.id("cn.v6.sixrooms:id/tv_name"));
        driver.findElementByXPath("//*[@text='粉丝团']").click();
        waitForElement(By.id("cn.v6.sixrooms:id/tv_fans_open"));
        driver.findElementById("cn.v6.sixrooms:id/tv_fans_open").click();
        waitForElement(By.xpath("//*[@text='恭喜你获得了粉丝牌']"));
        sleepForElement(3);
        targetClick(522, 1617);
        if (waitForElement(By.xpath("//*[@text='恭喜你获得了粉丝牌']"))) {
            throw new RuntimeException("获取粉丝牌弹窗多次弹出");
        }
        waitForElement(By.id("cn.v6.sixrooms:id/tv_host_name"));
        String name = driver.findElementById("cn.v6.sixrooms:id/tv_host_name").getText();
        System.out.println(name);
        Assert.assertNotNull(name);
    }

    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2250) * y2))).release().perform();
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

    private void sleepForElement(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
