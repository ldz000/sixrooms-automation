package sixrooms.cases.lianyun;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sixrooms.base.ApiService;

import java.net.URL;

public class popUpTestLianYun {

    public static AndroidDriver<?> driver;
    ApiService http = new ApiService();
    TouchAction<?> tAction;

    @BeforeTest
    public void setUp() throws Exception {
        // 设置属性
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        //capabilities.setCapability("deviceName", "192.168.26.16:5555");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true); // 不删除手机数据
        capabilities.setCapability("recreateChromeDriverSessions", true);
        capabilities.setCapability("newCommandTimeout", "30000");
        capabilities.setCapability("appPackage", "com.tencent.tmgp.sixrooms");
        capabilities.setCapability("appActivity", "cn.v6.sixrooms.ui.phone.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        tAction = new TouchAction<>(driver);
    }

    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2250) * y2))).release().perform();
    }

    @Test
    public void initialApi() {
        try {
            waitForElement(By.id("cn.v6.sixrooms:id/tv_agree"));
            driver.findElementById("cn.v6.sixrooms:id/tv_agree").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            Thread.sleep(1000);
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_button").click();
            waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
            Thread.sleep(3000);
            waitForElement(By.id("cn.v6.sixrooms:id/close"));
            driver.findElementById("cn.v6.sixrooms:id/close").click();
        } catch (Exception ignored) {
        }
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/account_login_button"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/account_login_button").click();
            try {
                Runtime.getRuntime().exec("adb shell input text houcp");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/et_password").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            try {
                Runtime.getRuntime().exec("adb shell input text 1234qwer");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/but_login").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
            targetClick(107, 2247);//点击首页
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("首页弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        Point point = driver.findElementById("com.tencent.tmgp.sixrooms:id/view_hot_banner").getLocation();
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        SwipeRight(driver);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        targetClick(958, 2018);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        SwipeLeft(driver);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        targetClick(958, 2018);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(1013, 170);//点击查找
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("房间内倒数第一创可贴没显示出来");
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/left_top_banner")))
            throw new RuntimeException("左上角创可贴没显示");
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/right_top_banner")))
            throw new RuntimeException("右上角创可贴没显示");
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/bottom_center_banner")))
            throw new RuntimeException("房间正下方右起第一创可贴没显示");
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/bottom_center_2_banner")))
            throw new RuntimeException("房间正下方右起第二创可贴没显示");
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_back").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/ivClearKeyWord"));
        driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/ivClearKeyWord")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("电台房倒数第一创可贴没显示出来");
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
    }

    @Test
    public void php320_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test4&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5"));
        tAction = new TouchAction<>(driver);
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(1013, 170);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point2 = driver.findElementById("com.tencent.tmgp.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point2.x + 10, point2.y + 10)).release().perform();
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("2号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test2&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=320&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴不该显示出来");
    }

    @Test
    public void php322_Test() {
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        tAction = new TouchAction<>(driver);
        targetClick(1013, 170);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            try {
                Thread.sleep(1000);
                driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket房间内弹窗不该弹出来");
    }

    @Test
    public void php323_Test() {
        tAction = new TouchAction<>(driver);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        targetClick(968, 2240);//点击我
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/gotoLogin"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/gotoLogin").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/account_login_button"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/account_login_button").click();
            tAction.press(PointOption.point(190, 505)).release().perform();// 点击输入用户名编辑框
            try {
                Runtime.getRuntime().exec("adb shell input text houcp");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/et_password").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            try {
                Runtime.getRuntime().exec("adb shell input text 1234qwer");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/but_login").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        }
        targetClick(107, 2247);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetClick(1013, 170);//点击查找
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_living")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_living").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
    }

    @Test
    public void php319Socket() {
        tAction = new TouchAction<>(driver);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=2");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            setUp();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        targetClick(968, 2240);//点击我
        if (waitForElement(By.id("com.tencent.tmgp.sixrooms:id/gotoLogin"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/gotoLogin").click();
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/account_login_button"));
            driver.findElementById("com.tencent.tmgp.sixrooms:id/account_login_button").click();
            tAction.press(PointOption.point(190, 505)).release().perform();// 点击用户名输入框
            try {
                Runtime.getRuntime().exec("adb shell input text houcp");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/et_password").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            try {
                Runtime.getRuntime().exec("adb shell input text 1234qwer");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementById("com.tencent.tmgp.sixrooms:id/but_login").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitForElement(By.id("com.tencent.tmgp.sixrooms:id/username"));
            targetClick(107, 2247);
            if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
                throw new RuntimeException("319登录弹窗没弹出来");
            driver.pressKeyCode(AndroidKeyCode.BACK);
        }
        targetClick(107, 2247);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=319&uid=91271306&eventname=test1&show=1&isInitEventPop=1&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
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
}
