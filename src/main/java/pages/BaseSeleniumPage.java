package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaseSeleniumPage {
    protected static final Logger LOGGER = LoggerFactory.getLogger("Page");
    public final  int DEFAULT_TIMEOUT_SECONDS = 30;
    public static WebDriver driver;
    public final int DEFAULT_TIMEOUT_SECONDS_UNTIL_BUSY = 1;
    public final int DEFAULT_TIMEOUT_SECONDS_UNTIL_READY = 120;
    public  BaseSeleniumPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilVisible (WebElement element){ waitUntilVisible(DEFAULT_TIMEOUT_SECONDS, element);}

    public void waitUntilVisible (int timeoutInSeconds, WebElement element){
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void setValue(WebElement element, String value) {
        element.clear();
        if (value.contains(Keys.TAB)) {
            waitPageBusy();
        }
        element.sendKeys(value);
    }

    public void waitPageBusy() {
        waitPageBusy(DEFAULT_TIMEOUT_SECONDS_UNTIL_BUSY, DEFAULT_TIMEOUT_SECONDS_UNTIL_READY);
    }

    public void waitPageBusy(int timeoutInSecondsUntilBusy, int timeoutInSecondsUntilReady) {
        WebElement currentFrame = (WebElement) ((JavascriptExecutor) driver).executeScript("return window.frameElement");
        if (currentFrame != null) {
            driver.switchTo().defaultContent();
        }
    }
}
