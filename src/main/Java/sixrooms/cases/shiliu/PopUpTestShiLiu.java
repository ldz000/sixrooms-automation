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
import sixrooms.base.DriverDao;

import java.net.URL;

/**
 * 1.登录用户：houcp 密码：1234qwer；
 * 2.将设备id改成测试uid；
 * 3.点击热门下附近，获取定位权限；
 * 4.随机点进直播间，上下滑动房间点击一下；
 * 5.设置中关闭小窗模式；
 */
public class PopUpTestShiLiu {

    public static AndroidDriver<?> driver;

    TouchAction<?> tAction;

    ApiService http = new ApiService();

    DriverDao driverDao = new DriverDao();

    @BeforeMethod
    public void setUp() {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), getCapabilities());
            tAction = new TouchAction<>(driver);
            driverDao.setDriver(driver);
            driverDao.settAction(tAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
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
        setUp();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("首页弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("首页创可贴没显示出来");
        Point point = driver.findElementById("cn.v6.sixrooms:id/view_hot_banner").getLocation();
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverDao.SwipeRight(driver);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("右滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("右滑切换Tab后首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverDao.SwipeLeft(driver);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/view_hot_banner")))
            throw new RuntimeException("左滑切换Tab后首页创可贴没显示出来");
        tAction.press(PointOption.point(point.x + 10, point.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("左滑切换Tab后首页创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //driverDao.targetClick(148, 186);//点击查找按钮
        driverDao.targetClick(148,186);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
    }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        // targetClick(930,1010);
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living"))) {
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        }
        driverDao.targetClick(533, 1183);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("1号创可贴没显示出来");
        Point point1 = driver.findElementById("cn.v6.sixrooms:id/first_banner").getLocation();
        tAction.press(PointOption.point(point1.x, point1.y)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("1号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/left_top_banner")))
            throw new RuntimeException("3号创可贴没显示");
        Point point3 = driver.findElementById("cn.v6.sixrooms:id/left_top_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("3号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //4号创可贴
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/right_top_banner")))
            throw new RuntimeException("4号创可贴没显示");
        Point point4 = driver.findElementById("cn.v6.sixrooms:id/right_top_banner").getLocation();
        tAction.press(PointOption.point(point4.x + 10, point4.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("4号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //5号创可贴
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/bottom_center_2_banner")))
            throw new RuntimeException("5号创可贴没显示");
        Point point5 = driver.findElementById("cn.v6.sixrooms:id/bottom_center_2_banner").getLocation();
        tAction.press(PointOption.point(point5.x + 10, point5.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("5号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        //6号创可贴
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/bottom_center_banner")))
            throw new RuntimeException("6号创可贴没显示");
        Point point6 = driver.findElementById("cn.v6.sixrooms:id/bottom_center_banner").getLocation();
        tAction.press(PointOption.point(point6.x + 10, point6.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
            throw new RuntimeException("6号创可贴点击没响应");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.sixrooms:id/iv_close_room").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_back").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/ivClearKeyWord"));
        driver.findElement(By.id("cn.v6.sixrooms:id/ivClearKeyWord")).click();
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
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
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverDao.targetClick(148, 186);//点击查找按钮
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("视频房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket视频房倒数第二创可贴没显示出来");
        Point point2 = driver.findElementById("cn.v6.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point2.x + 10, point2.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
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
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket视频房倒数第一创可贴没显示出来");
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
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
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverDao.targetClick(148, 186);//点击查找按钮
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("电台房主动弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
            throw new RuntimeException("320socket电台房倒数第二创可贴没显示出来");
        Point point3 = driver.findElementById("cn.v6.sixrooms:id/second_banner").getLocation();
        tAction.press(PointOption.point(point3.x + 10, point3.y + 10)).release().perform();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/common_web_view")))
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
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/first_banner")))
            throw new RuntimeException("320socket电台房倒数第一创可贴没显示出来");
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/second_banner")))
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
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverDao.targetClick(148, 186);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
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
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房间内弹窗不该弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverDao.targetClick(148, 186);
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房间内弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElementById("cn.v6.sixrooms:id/iv_close_room").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/radioroom_exit_linearlayout"));
        driver.findElementById("cn.v6.sixrooms:id/radioroom_exit_linearlayout").click();
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=322&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living")))
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
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
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        driverDao.targetClick(148, 186);
        try {
            Runtime.getRuntime().exec("adb shell input text 232740372");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living"))) {
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket视频房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=86823842&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("321socket通知型弹窗没弹出来");
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUp();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/mainBottomBarButtonImage"));
        driverDao.targetClick(148, 186);
        try {
            Runtime.getRuntime().exec("adb shell input text 786023");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        driver.findElementById("cn.v6.sixrooms:id/icon").click();
        if (driverDao.waitForElement(By.id("cn.v6.sixrooms:id/tv_living"))) {
            driver.findElementById("cn.v6.sixrooms:id/tv_living").click();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("322socket电台房弹窗没弹出来");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test3&status=2");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=321&show=1&eventname=test3&uid=91271306&ruid=82354127&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
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
        driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5"));
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driverDao.targetClick(973, 2240);
        driverDao.targetClick(106, 2243);//点击首页
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
            throw new RuntimeException("319登录弹窗没弹出来");

        try {
            http.doGet("http://v.6.cn/api/doTestPop.php?act=update&eventname=test1&status=0");
            http.doGet(
                    "http://v.6.cn/api/doTestPop.php?act=send&typeid=319&uid=91271306&eventname=test1&show=1&isInitEventPop=1&uuid=655228ca720f1b21dd915421ff209068&from=0&ctype=2&ver=");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!driverDao.waitForElement(By.id("cn.v6.sixrooms:id/web_view_h5")))
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
