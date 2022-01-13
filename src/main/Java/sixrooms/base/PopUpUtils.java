package sixrooms.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 针对移动端出现的弹窗进行处理
 * com.tencent.tmgp.sixrooms:id/tv_agree 个人信息保护指引
 * com.tencent.tmgp.sixrooms:id/close 我知道了/设置青少年模式
 * com.tencent.tmgp.sixrooms:id/tv_agree 隐私政策更新
 * com.tencent.tmgp.sixrooms:id/agree 去开启定位权限
 * com.android.permissioncontroller:id/permission_allow_always_button 始终允许
 */

public class PopUpUtils {

    public static AndroidDriver<?> driver;

    public void popUpDel(String element) {
        if (element.equals("com.tencent.tmgp.sixrooms:id/tv_agree") ||
                element.equals("com.tencent.tmgp.sixrooms:id/close") ||
                element.equals("com.tencent.tmgp.sixrooms:id/agree") ||
                element.equals("com.android.permissioncontroller:id/permission_allow_always_button") ||
                element.equals("com.tencent.tmgp.sixrooms:id/tv_exit_room")) {
            driver.findElement(By.id(element)).click();
        }
    }



}
