package sixrooms.cases.chromeAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 删除用户的粉丝牌
 */

public class DeleteFansCard {

    public static void main(String[] args) throws InterruptedException {
        String driverPath = System.getProperty("user.dir") + "/src/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://v.6.cn");
        //窗口设定
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > div.new-people-trip > span")).click();
        driver.findElement(By.cssSelector("#userPanel_index > ul > li.login > a")).click();
        driver.findElement(By.cssSelector("#member-login-un")).sendKeys("zhouh1");
        driver.findElement(By.cssSelector("#member-login-pd")).sendKeys("1234qwer");
        driver.findElement(By.cssSelector("#loginFrame_quick > dl > dt > input")).click();
        WebElement tools = driver.findElement(By.cssSelector("#myAccountTool > a > img"));
        actions.moveToElement(tools).perform();
        driver.findElement(By.cssSelector("#controlCenter > div.pop-inner.fix > div.control > ul > li.u-prop > a")).click();
        String mainWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.findElement(By.cssSelector("#iBody > div.col2.myprop > div.friend-tab > a:nth-child(7)")).click();
        driver.findElement(By.cssSelector("#iBody > div.col2.myprop > div.fansetDiv > div.fansListB > div > table > tbody > tr:nth-child(2) > td:nth-child(4) > span")).click();
        driver.switchTo().alert().accept();
        driver.quit();
    }
}
