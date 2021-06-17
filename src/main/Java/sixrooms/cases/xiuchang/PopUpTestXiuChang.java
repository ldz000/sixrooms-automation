package sixrooms.cases.xiuchang;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sixrooms.base.ApiService;

import java.net.URL;

/**
 * 1.登录用户：houcp 密码：1234qwer；
 * 2.将设备id改成测试uid；
 * 3.点击热门下附近，获取定位权限；
 * 4.随机点进直播间，上下滑动房间点击一下；
 * 5.设置中关闭小窗模式；
 */

public class PopUpTestXiuChang {
    public static AndroidDriver<?> driver;
    ApiService http = new ApiService();
    TouchAction<?> tAction;

    @BeforeTest
    public void setUp() throws Exception {
        // 设置属性
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "everything");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3000");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cn.v6.xiuchang");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "cn.v6.sixrooms.ui.phone.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void initialApi() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("首页弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        Point point = driver.findElementById("cn.v6.xiuchang:id/view_hot_banner").getLocation();
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        SwipeRight(driver);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        targetClick(958, 2018);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        SwipeLeft(driver);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        targetClick(958, 2018);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(973, 2240);
        if (waitForElement(By.id("cn.v6.xiuchang:id/gotoLogin"))) {
            driver.findElementById("cn.v6.xiuchang:id/gotoLogin").click();
            waitForElement(By.id("cn.v6.xiuchang:id/account_login_button"));
            driver.findElementById("cn.v6.xiuchang:id/account_login_button").click();
            try {
                Runtime.getRuntime().exec("adb shell input text houcp");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("cn.v6.xiuchang:id/et_password").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            try {
                Runtime.getRuntime().exec("adb shell input text 1234qwer");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("cn.v6.xiuchang:id/but_login").click();
            waitForElement(By.id("cn.v6.xiuchang:id/mainBottomBarButtonImage"));
        }
        targetClick(106, 2243);//点击首页
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(63, 174);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
            throw new RuntimeException("1号创可贴没显示出来");
        Point point1 = driver.findElementById("cn.v6.xiuchang:id/first_banner").getLocation();
        tAction.press(PointOption.point(point1.x, point1.y)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("1号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/left_top_banner")))
            throw new RuntimeException("3号创可贴没显示");
        Point point3 = driver.findElementById("cn.v6.xiuchang:id/left_top_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("3号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //4号创可贴
        if (!waitForElement(By.id("cn.v6.xiuchang:id/right_top_banner")))
            throw new RuntimeException("4号创可贴没显示");
        Point point4 = driver.findElementById("cn.v6.xiuchang:id/right_top_banner").getLocation();
        tAction.press(PointOption.point(point4.x + 10, point4.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("4号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //5号创可贴
        if (!waitForElement(By.id("cn.v6.xiuchang:id/bottom_center_2_banner")))
            throw new RuntimeException("5号创可贴没显示");
        Point point5 = driver.findElementById("cn.v6.xiuchang:id/bottom_center_2_banner").getLocation();
        tAction.press(PointOption.point(point5.x + 10, point5.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("5号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //6号创可贴
        if (!waitForElement(By.id("cn.v6.xiuchang:id/bottom_center_banner")))
            throw new RuntimeException("6号创可贴没显示");
        Point point6 = driver.findElementById("cn.v6.xiuchang:id/bottom_center_banner").getLocation();
        tAction.press(PointOption.point(point6.x + 10, point6.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("6号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.xiuchang:id/iv_close_room").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_back").click();
        waitForElement(By.id("cn.v6.xiuchang:id/ivClearKeyWord"));
        driver.findElement(By.id("cn.v6.xiuchang:id/ivClearKeyWord")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
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
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElementById("cn.v6.xiuchang:id/search_editText_content").click();
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!waitForElement(By.id("cn.v6.xiuchang:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point2 = driver.findElementById("cn.v6.xiuchang:id/second_banner").getLocation();
        tAction.press(PointOption.point(point2.x + 10, point2.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("2号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=320&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (waitForElement(By.id("cn.v6.xiuchang:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴不该显示出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElementById("cn.v6.xiuchang:id/search_editText_content").click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!waitForElement(By.id("cn.v6.xiuchang:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point3 = driver.findElementById("cn.v6.xiuchang:id/second_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/common_web_view")))
            throw new RuntimeException("2号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=320&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (waitForElement(By.id("cn.v6.xiuchang:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴不该显示出来");
    }

    @Test
    public void php322_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/mainBottomBarButtonImage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElement(By.id("cn.v6.xiuchang:id/search_editText_content")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.xiuchang:id/iv_close_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗不该弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/mainBottomBarButtonImage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElement(By.id("cn.v6.xiuchang:id/search_editText_content")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.xiuchang:id/iv_close_room").click();
        waitForElement(By.id("cn.v6.xiuchang:id/cancel"));
        driver.findElementById("cn.v6.xiuchang:id/cancel").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗不该弹出来");
    }

    @Test
    public void php323_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/mainBottomBarButtonImage"));
        targetClick(973, 2240);
        targetClick(106, 2243);//点击首页
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElementById("cn.v6.xiuchang:id/search_editText_content").click();
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/mainBottomBarButtonImage"));
        targetClick(973, 2240);
        targetClick(106, 2243);//点击首页
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(63, 174);
        waitForElement(By.id("cn.v6.xiuchang:id/search_editText_content"));
        driver.findElementById("cn.v6.xiuchang:id/search_editText_content").click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.xiuchang:id/iv_title_serach_cancle").click();
        waitForElement(By.id("cn.v6.xiuchang:id/icon"));
        driver.findElementById("cn.v6.xiuchang:id/icon").click();
        if (waitForElement(By.id("cn.v6.xiuchang:id/tv_living")))
            driver.findElementById("cn.v6.xiuchang:id/tv_living").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
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
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(973, 2240);
        if (waitForElement(By.id("cn.v6.xiuchang:id/gotoLogin"))) {
            driver.findElementById("cn.v6.xiuchang:id/gotoLogin").click();
            waitForElement(By.id("cn.v6.xiuchang:id/account_login_button"));
            driver.findElementById("cn.v6.xiuchang:id/account_login_button").click();
            try {
                Runtime.getRuntime().exec("adb shell input text houcp");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("cn.v6.xiuchang:id/et_password").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            try {
                Runtime.getRuntime().exec("adb shell input text 1234qwer");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("cn.v6.xiuchang:id/but_login").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tAction.press(PointOption.point(1083, 795)).release().perform();// 点击登录后的提示
            waitForElement(By.id("cn.v6.xiuchang:id/username"));
            targetClick(106, 2243);//点击首页
            if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
                throw new RuntimeException("319登录弹窗没弹出来");
            driver.pressKeyCode(AndroidKeyCode.BACK);
        }
        targetClick(106, 2243);//点击首页
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=319&uid=91271306&eventname=test1&show=1&isInitEventPop=1&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("cn.v6.xiuchang:id/web_view_h5")))
            throw new RuntimeException("319通知型弹窗没弹出来");
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

    public void SwipeRight(AndroidDriver<?> driver) {
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        new TouchAction<>(driver).longPress(PointOption.point(100, height / 2))
                .moveTo(PointOption.point(width - 100, height / 2)).release()
                .perform();
    }

    public void SwipeLeft(AndroidDriver<?> driver) {
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        new TouchAction<>(driver)
                .longPress(PointOption.point(width - 100, height / 2))
                .moveTo(PointOption.point(100, height / 2)).release().perform();
    }

    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2250) * y2))).release().perform();
    }
}
