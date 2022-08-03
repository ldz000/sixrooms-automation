package sixrooms.cases.shiliu;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sixrooms.base.ApiService;
import sixrooms.base.DriverUtil;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 1.登录用户：houcp 密码：1234qwer；
 * 2.将设备id改成测试uid；
 * 3.设置中关闭小窗模式；
 */
public class PopUpTestShiLiu {

    public static AndroidDriver<?> driver;

    TouchAction<?> tAction;

    ApiService http = new ApiService();

    DriverUtil driverUtil = new DriverUtil();

    @BeforeMethod
    public void setUp() {
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities());
            tAction = new TouchAction<>(driver);
            driverUtil.setDriver(driver);
            driverUtil.settAction(tAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void initApi() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("首页弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        Point point = driver.findElementById("cn.v6.sixrooms:id/view_hot_banner").getLocation();
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.SwipeRight(driver);
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/agree"))) {
            driver.findElementById("cn.v6.sixrooms:id/agree").click();
            driverUtil.waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("右滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("右滑切换Tab后首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.SwipeLeft(driver);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("左滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        //隐性等待，查询不到元素会不断刷新DOM
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("左滑切换Tab后首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //隐性等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content"))) {
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("1号创可贴没显示出来");
        Point point1 = driver.findElementById("cn.v6.sixrooms:id/first_banner").getLocation();
        tAction.press(PointOption.point(point1.x, point1.y)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("1号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/left_top_banner")))
            throw new RuntimeException("3号创可贴没显示");
        Point point3 = driver.findElementById("cn.v6.sixrooms:id/left_top_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("3号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //4号创可贴
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/right_top_banner")))
            throw new RuntimeException("4号创可贴没显示");
        Point point4 = driver.findElementById("cn.v6.sixrooms:id/right_top_banner").getLocation();
        tAction.press(PointOption.point(point4.x + 10, point4.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("4号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //5号创可贴
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/bottom_center_2_banner")))
            throw new RuntimeException("5号创可贴没显示");
        Point point5 = driver.findElementById("cn.v6.sixrooms:id/bottom_center_2_banner").getLocation();
        tAction.press(PointOption.point(point5.x + 10, point5.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("5号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //6号创可贴
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/bottom_center_banner")))
            throw new RuntimeException("6号创可贴没显示");
        Point point6 = driver.findElementById("cn.v6.sixrooms:id/bottom_center_banner").getLocation();
        tAction.press(PointOption.point(point6.x + 10, point6.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("6号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.sixrooms:id/iv_close_room").click();
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/tv_exit_room"))) {
            driver.findElementById("cn.v6.sixrooms:id/tv_exit_room").click();
        }
        driverUtil.coerceSleep();
        driver.findElementById("cn.v6.sixrooms:id/iv_back").click();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/ivClearKeyWord"));
        driver.findElement(By.id("cn.v6.sixrooms:id/ivClearKeyWord")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            driverUtil.coerceSleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driver.pressKeyCode(AndroidKeyCode.ENTER);
        driverUtil.coerceSleep();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"一个核桃仁22\")").click();
        //driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        //driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("电台房倒数第一创可贴没显示出来");
    }

    @Test
    public void php320_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point2 = driver.findElementById("cn.v6.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point2.x + 10, point2.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("2号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=320&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴不该显示出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket电台房倒数第二创可贴没显示出来");
        Point point3 = driver.findElementById("cn.v6.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("2号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=320&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket电台房倒数第二创可贴不该显示出来");
    }

    @Test
    public void php322_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        //driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        //driverUtil.coerceSleep();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.sixrooms:id/iv_close_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房间内弹窗不该弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.coerceSleep();
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.sixrooms:id/iv_close_room").click();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/tv_quit_room"));
        driver.findElementById("cn.v6.sixrooms:id/tv_quit_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content")))
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房间内弹窗不该弹出来");
    }

    @Test
    public void php323_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        //driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content"))) {
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/live_content"))) {
            driver.findElementById("cn.v6.sixrooms:id/live_content").click();
        }
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
    }

    @Test
    public void php319Socket() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.targetClick(27, 422);
        driverUtil.targetClick(354, 749);//点击首页
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("319登录弹窗没弹出来");

        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=319&uid=91271306&eventname=test1&show=1&isInitEventPop=1&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("319通知型弹窗没弹出来");
    }


    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.26.16:5555");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.v6.sixrooms");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cn.v6.sixrooms.ui.phone.SplashActivity");
        return capabilities;
    }
}
