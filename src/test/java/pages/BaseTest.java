package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    static WebDriver driver;

    public static WebDriver setupBrowser() throws Exception {
        System.setProperty("webdriver.chrome.driver","/Users/okuhlem/IdeaProjects/wonderlabsautomation/src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        return driver;
    }
}
