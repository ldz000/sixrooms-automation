package sixrooms.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 移动端UI自动化常用方法的封装
 */

public class DriverUtil {

    public AndroidDriver<?> getDriver() {
        return driver;
    }

    public void setDriver(AndroidDriver<?> driver) {
        this.driver = driver;
    }

    AndroidDriver<?> driver;

    public TouchAction<?> gettAction() {
        return tAction;
    }

    public void settAction(TouchAction<?> tAction) {
        this.tAction = tAction;
    }

    TouchAction<?> tAction;

    /*
        元素显示等待方法
     */
    public boolean waitForElement(final By elementLocator) {
        WebDriverWait w = new WebDriverWait(driver, 25);
        boolean flag = true;
        try {
            //until 直到某个条件为真才会往下执行，默认超时是10s
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator));
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /*
        右滑
     */
    public void SwipeRight(AndroidDriver<?> driver) {
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        new TouchAction<>(driver).longPress(PointOption.point(100, height / 2))
                .moveTo(PointOption.point(width - 100, height / 2)).release()
                .perform();
    }

    /*
        左滑
     */
    public void SwipeLeft(AndroidDriver<?> driver) {
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        new TouchAction<>(driver)
                .longPress(PointOption.point(width - 100, height / 2))
                .moveTo(PointOption.point(100, height / 2)).release().perform();
    }

    /*
        使用相对坐标进行点击事件
     */
    public void targetClick(double x, double y) {
        double x2 = driver.manage().window().getSize().width;
        double y2 = driver.manage().window().getSize().height;
        tAction.press(PointOption.point((int) ((x / (double) 1080) * x2), (int) ((y / (double) 2250) * y2))).release().perform();
    }

    /*
        强制等待
     */
    public void coerceSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        点击搜索进入直播间
     */
    public void searchToRoom(Integer roomID, String roomName) {
        targetClick(516, 117);//点击搜索框 石榴
        //targetClick(90, 100);//点击搜索框 秀场
        //targetClick(42,100);//点击搜索框 联运
        //driver.findElementById("cn.v6.sixrooms:id/tv_content").click();//点击查找元素ID
        try {
            Runtime.getRuntime().exec("adb shell input text " + roomID);
            coerceSleep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.pressKeyCode(AndroidKeyCode.ENTER);
        coerceSleep();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(" + '"' + roomName + '"' + ")").click();
        //driver.findElementById("cn.v6.sixrooms:id/iv_title_serach_cancle").click();
        //driverDao.waitForElement(By.id("cn.v6.sixrooms:id/icon"));
        //driver.findElementById("cn.v6.sixrooms:id/icon").click();
    }
}
