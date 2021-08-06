package java.loverooms;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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

public class LoveRoomsLoginAndPay {

    DesiredCapabilities capabilities = new DesiredCapabilities();

    private static AndroidDriver<?> driver;

    private static TouchAction<?> tAction;

    @BeforeTest
    public void setup() throws Exception {
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("deviceName", "192.168.26.16:5555");
        capabilities.setCapability("newCommandTimeout", "30");
        capabilities.setCapability("appPackage", "cn.v6.loverooms");
        capabilities.setCapability("appActivity", "cn.v6.sixrooms.ui.phone.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "测试充值流程，自己充值")
    public void testLoveRoomsLoginAndPay() {
        try {
            waitForElement(By.id("cn.v6.loverooms:id/tv_agree"));
            driver.findElementById("cn.v6.loverooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_other_login"));
            driver.findElementById("cn.v6.loverooms:id/tv_other_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/but_login"));
            driver.findElementById("cn.v6.loverooms:id/et_username").sendKeys("testying36");
            driver.findElementById("cn.v6.loverooms:id/et_password").sendKeys("12345678ying");
            driver.findElementById("cn.v6.loverooms:id/but_login").click();
            waitForElement(By.xpath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]"));
            driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_multi_recharge"));
            String value1 = driver.findElementById("cn.v6.loverooms:id/tv_multi_six_zuan_balance").getText();
            int value_init = Integer.parseInt(value1.replaceAll(",", "").replaceAll("个", "")) + 10;
            driver.findElementById("cn.v6.loverooms:id/tv_multi_recharge").click();
            waitForElement(By.xpath("//android.view.View[@text='确认支付']"));
            targetClick(884, 1567);
            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
//            waitForElement(By.xpath("//android.view.View[@text='充值详情']"));
//            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='立即支付']"));
            driver.findElementByAccessibilityId("立即支付").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='请输入支付密码']"));
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            waitForElement(By.xpath("//android.view.View[@text='充值']"));
            Thread.sleep(13000);
            targetClick(53, 191);
            waitForElement(By.id("cn.v6.loverooms:id/tv_multi_six_zuan_balance"));
            String value2 = driver.findElementById("cn.v6.loverooms:id/tv_multi_six_zuan_balance").getText();
            int value_expect = Integer.parseInt(value2.replaceAll(",", "").replaceAll("个", ""));
            Assert.assertEquals(value_init, value_expect);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(description = "测试充值流程，代理充值")
    public void testLoveRoomsReplaceLoginAndPay() {
        try {
            int value3 = getTextCoin("testying16");
            driver.quit();
            setup();
            waitForElement(By.id("cn.v6.loverooms:id/tv_agree"));
            driver.findElementById("cn.v6.loverooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_other_login"));
            driver.findElementById("cn.v6.loverooms:id/tv_other_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/but_login"));
            driver.findElementById("cn.v6.loverooms:id/et_username").sendKeys("testying36");
            driver.findElementById("cn.v6.loverooms:id/et_password").sendKeys("12345678ying");
            driver.findElementById("cn.v6.loverooms:id/but_login").click();
            waitForElement(By.xpath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]"));
            driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_multi_recharge"));
            String value1 = driver.findElementById("cn.v6.loverooms:id/tv_multi_six_zuan_balance").getText();
            int value_init = Integer.parseInt(value1.replaceAll(",", "").replaceAll("个", ""));
            driver.findElementById("cn.v6.loverooms:id/tv_multi_recharge").click();
            waitForElement(By.xpath("//android.view.View[@text='确认支付']"));
            targetClick(884, 1567);
            driver.findElementByXPath("//android.view.View[@text='784063']").click();
            driver.findElementByClassName("android.widget.EditText").sendKeys("715805");
            driver.findElementByXPath("//android.widget.Button[@text='确定']").click();
            Thread.sleep(5000);
            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
//            waitForElement(By.xpath("//android.view.View[@text='充值详情']"));
//            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='立即支付']"));
            driver.findElementByAccessibilityId("立即支付").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='请输入支付密码']"));
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            waitForElement(By.xpath("//android.view.View[@text='充值']"));
            Thread.sleep(13000);
            targetClick(53, 191);
            waitForElement(By.id("cn.v6.loverooms:id/tv_multi_six_zuan_balance"));
            String value2 = driver.findElementById("cn.v6.loverooms:id/tv_multi_six_zuan_balance").getText();
            int value_expect = Integer.parseInt(value2.replaceAll(",", "").replaceAll("个", ""));
            Assert.assertEquals(value_init, value_expect);
            driver.quit();
            setup();
            int value4 = getTextCoin("testying16");
            Assert.assertEquals(value3 + 10, value4);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(description = "测试充值流程，交友房自己充值")
    public void testLoveRoomsLiveLoginAndPay() {
        try {
            int value1 = getTextCoin("testying36");
            driver.quit();
            setup();
            waitForElement(By.id("cn.v6.loverooms:id/tv_agree"));
            driver.findElementById("cn.v6.loverooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_other_login"));
            driver.findElementById("cn.v6.loverooms:id/tv_other_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/but_login"));
            driver.findElementById("cn.v6.loverooms:id/et_username").sendKeys("testying36");
            driver.findElementById("cn.v6.loverooms:id/et_password").sendKeys("12345678ying");
            driver.findElementById("cn.v6.loverooms:id/but_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/iv_search"));
            driver.findElementById("cn.v6.loverooms:id/iv_search").click();
            waitForElement(By.id("cn.v6.loverooms:id/et_search"));
            driver.findElementById("cn.v6.loverooms:id/et_search").sendKeys("714141");
            driver.findElementById("cn.v6.loverooms:id/tv_search").click();
            waitForElement(By.id("cn.v6.loverooms:id/iv_user_icon"));
            driver.findElementById("cn.v6.loverooms:id/iv_user_icon").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            targetClick(450, 180);
            waitForElement(By.xpath("//android.view.View[@text='充值']"));
            targetClick(895, 1774);
            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='立即支付']"));
            driver.findElementByAccessibilityId("立即支付").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='请输入支付密码']"));
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            driver.quit();
            setup();
            int value2 = getTextCoin("testying36");
            Assert.assertEquals(value1 + 10, value2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(description = "测试充值流程，交友房代理充值")
    public void testLoveRoomsLiveReplaceLoginAndPay() {
        try {
            int value1 = getTextCoin("testying16");
            driver.quit();
            setup();
            waitForElement(By.id("cn.v6.loverooms:id/tv_agree"));
            driver.findElementById("cn.v6.loverooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("cn.v6.loverooms:id/tv_other_login"));
            driver.findElementById("cn.v6.loverooms:id/tv_other_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/but_login"));
            driver.findElementById("cn.v6.loverooms:id/et_username").sendKeys("testying36");
            driver.findElementById("cn.v6.loverooms:id/et_password").sendKeys("12345678ying");
            driver.findElementById("cn.v6.loverooms:id/but_login").click();
            waitForElement(By.id("cn.v6.loverooms:id/iv_search"));
            driver.findElementById("cn.v6.loverooms:id/iv_search").click();
            waitForElement(By.id("cn.v6.loverooms:id/et_search"));
            driver.findElementById("cn.v6.loverooms:id/et_search").sendKeys("714141");
            driver.findElementById("cn.v6.loverooms:id/tv_search").click();
            waitForElement(By.id("cn.v6.loverooms:id/iv_user_icon"));
            driver.findElementById("cn.v6.loverooms:id/iv_user_icon").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            targetClick(450, 180);
            waitForElement(By.xpath("//android.view.View[@text='充值']"));
            targetClick(895, 1774);
            driver.findElementByXPath("//android.view.View[@text='784063']").click();
            driver.findElementByClassName("android.widget.EditText").sendKeys("715805");
            driver.findElementByXPath("//android.widget.Button[@text='确定']").click();
            Thread.sleep(5000);
            driver.findElementByXPath("//android.view.View[@text='确认支付']").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='立即支付']"));
            driver.findElementByAccessibilityId("立即支付").click();
            waitForElement(By.xpath("//android.widget.TextView[@text='请输入支付密码']"));
            driver.findElementByClassName("android.widget.RelativeLayout").sendKeys("159251");
            waitForElement(By.xpath("//android.widget.TextView[@content-desc=\"返回商家\"]"));
            driver.findElementByAccessibilityId("返回商家").click();
            driver.quit();
            setup();
            int value2 = getTextCoin("testying16");
            Assert.assertEquals(value1 + 10, value2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public Integer getTextCoin(String name) {
        waitForElement(By.id("cn.v6.loverooms:id/tv_agree"));
        driver.findElementById("cn.v6.loverooms:id/tv_agree").click();
        waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
        driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
        waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
        driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
        waitForElement(By.id("cn.v6.loverooms:id/tv_other_login"));
        driver.findElementById("cn.v6.loverooms:id/tv_other_login").click();
        waitForElement(By.id("cn.v6.loverooms:id/but_login"));
        driver.findElementById("cn.v6.loverooms:id/et_username").sendKeys(name);
        driver.findElementById("cn.v6.loverooms:id/et_password").sendKeys("12345678ying");
        driver.findElementById("cn.v6.loverooms:id/but_login").click();
        waitForElement(By.xpath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]"));
        driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"伊伴视频相亲\"])[5]").click();
        waitForElement(By.id("cn.v6.loverooms:id/tv_multi_recharge"));
        String value4 = driver.findElementById("cn.v6.loverooms:id/tv_multi_six_zuan_balance").getText();
        return Integer.parseInt(value4.replaceAll(",", "").replaceAll("个", ""));
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
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2232) * y2))).release().perform();
    }
}
