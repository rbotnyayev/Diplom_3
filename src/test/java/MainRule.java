import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class MainRule {
    public static WebDriver driver;

    @Before
    public void setMainRule() {
        if ("Yandex".equals(System.getProperty("browser")))
            setUpYandexBrowser();
        else
            setUpChromeBrowser();

        driver = new ChromeDriver();
        String URL = "https://stellarburgers.nomoreparties.site/";
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setUpChromeBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");

        driver = new ChromeDriver(options);
    }

    public void setUpYandexBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}