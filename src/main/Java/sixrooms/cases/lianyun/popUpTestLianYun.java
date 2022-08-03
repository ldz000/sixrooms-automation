package sixrooms.cases.lianyun;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sixrooms.base.ApiService;
import sixrooms.base.DriverUtil;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * com.tencent.tmgp.sixrooms:id/tv_agree 个人信息保护指引
 * com.tencent.tmgp.sixrooms:id/close 我知道了/设置青少年模式
 * 964,2213 我的页面坐标
 * 1006,143 设置按钮
 * com.tencent.tmgp.sixrooms:id/smallPopupsBtn 关闭小窗模式
 * com.tencent.tmgp.sixrooms:id/titlebar_right_frame 打卡测试UUID调试
 * com.tencent.tmgp.sixrooms:id/btn_modify_uuid 修改为测试UUID
 * com.tencent.tmgp.sixrooms:id/tv_agree 隐私政策更新
 * 左滑开启附近定位权限
 * com.tencent.tmgp.sixrooms:id/agree 去开启定位权限
 * com.android.permissioncontroller:id/permission_allow_always_button 始终允许
 * com.tencent.tmgp.sixrooms:id/gotoLogin 登录/注册
 * 左滑进行账号登录
 * com.tencent.tmgp.sixrooms:id/et_username 用户名
 * com.tencent.tmgp.sixrooms:id/et_password 密码
 * com.tencent.tmgp.sixrooms:id/registerSelectTag 登录即同意用户协议，和用户隐私政策
 * com.tencent.tmgp.sixrooms:id/follow_num 关注
 * 116,2224 热门坐标
 * 265,524 热门下直播坐标
 * com.tencent.tmgp.sixrooms:id/tv_exit_room 退出房间
 * 联运包弹窗系统自动化
 */
public class popUpTestLianYun {

    public static AndroidDriver<?> driver;
    ApiService http = new ApiService();
    TouchAction<?> tAction;
    DriverUtil driverUtil = new DriverUtil();

    @BeforeTest
    public void setUp() {
        // 设置属性
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities());
            tAction = new TouchAction<>(driver);
            driverUtil.setDriver(driver);
            driverUtil.settAction(tAction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //capabilities.setCapability("deviceName", "192.168.26.16:5555");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true); // 不删除手机数据
        capabilities.setCapability("recreateChromeDriverSessions", true);
        capabilities.setCapability("newCommandTimeout", "30000");
        capabilities.setCapability("appPackage", "com.tencent.tmgp.sixrooms");
        capabilities.setCapability("appActivity", "cn.v6.sixrooms.ui.phone.SplashActivity");
        return capabilities;
    }

    @AfterTest
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
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("首页弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        Point point = driver.findElementById("com.tencent.tmgp.sixrooms:id/view_hot_banner").getLocation();
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.SwipeRight(driver);
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/agree"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/agree").click();
            driverUtil.waitForElement(By.id("com.android.permissioncontroller:id/permission_allow_always_button"));
            driver.findElementById("com.android.permissioncontroller:id/permission_allow_always_button").click();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("右滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("右滑切换Tab后首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.SwipeLeft(driver);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("左滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("左滑切换Tab后首页创可贴点击没响应");
        //隐性等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //隐性等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("1号创可贴没显示出来");
        Point point1 = driver.findElementById("com.tencent.tmgp.sixrooms:id/first_banner").getLocation();
        tAction.press(PointOption.point(point1.x, point1.y)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("1号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/left_top_banner")))
            throw new RuntimeException("3号创可贴没显示");
        Point point3 = driver.findElementById("com.tencent.tmgp.sixrooms:id/left_top_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("3号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //4号创可贴
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/right_top_banner")))
            throw new RuntimeException("4号创可贴没显示");
        Point point4 = driver.findElementById("com.tencent.tmgp.sixrooms:id/right_top_banner").getLocation();
        tAction.press(PointOption.point(point4.x + 10, point4.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("4号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //5号创可贴
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/bottom_center_2_banner")))
            throw new RuntimeException("5号创可贴没显示");
        Point point5 = driver.findElementById("com.tencent.tmgp.sixrooms:id/bottom_center_2_banner").getLocation();
        tAction.press(PointOption.point(point5.x + 10, point5.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("5号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //6号创可贴
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/bottom_center_banner")))
            throw new RuntimeException("6号创可贴没显示");
        Point point6 = driver.findElementById("com.tencent.tmgp.sixrooms:id/bottom_center_banner").getLocation();
        tAction.press(PointOption.point(point6.x + 10, point6.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
            throw new RuntimeException("6号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_exit_room"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_exit_room").click();
        }
        driverUtil.coerceSleep();
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_back").click();
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/ivClearKeyWord"));
        driver.findElement(By.id("com.tencent.tmgp.sixrooms:id/ivClearKeyWord")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            driverUtil.coerceSleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_title_serach_cancle").click();
        driver.pressKeyCode(AndroidKeyCode.ENTER);
        driverUtil.coerceSleep();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"一个核桃仁22\")").click();
        //driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/icon"));
        //driver.findElementById("com.tencent.tmgp.sixrooms:id/icon").click();
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
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
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point2 = driver.findElementById("com.tencent.tmgp.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point2.x + 10, point2.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
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
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
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
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket电台房倒数第二创可贴没显示出来");
        Point point3 = driver.findElementById("com.tencent.tmgp.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/common_web_view")))
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
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/second_banner")))
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
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.coerceSleep();
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房间内弹窗不该弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.coerceSleep();
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("com.tencent.tmgp.sixrooms:id/iv_close_room").click();
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/tv_quit_room"));
        driver.findElementById("com.tencent.tmgp.sixrooms:id/tv_quit_room").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content")))
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
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
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.searchToRoom(232740372, "鱼一吃次啊");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/mainBottomBarButtonImage"));
        driverUtil.searchToRoom(786023, "一个核桃仁22");
        if (driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/live_content"))) {
            driver.findElementById("com.tencent.tmgp.sixrooms:id/live_content").click();
        }
        driverUtil.coerceSleep();
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
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
        driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverUtil.targetClick(973, 2240);
        driverUtil.targetClick(106, 2243);//点击首页
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("319登录弹窗没弹出来");

        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=319&uid=91271306&eventname=test1&show=1&isInitEventPop=1&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverUtil.waitForElement(By.id("com.tencent.tmgp.sixrooms:id/web_view_h5")))
            throw new RuntimeException("319通知型弹窗没弹出来");
    }
}
