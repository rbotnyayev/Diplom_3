import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver driver;
    private String browserName;

    public Browser() {
        this.browserName = System.getProperty("browser");
    }

    public WebDriver getWebDriver() {
        if (browserName == null) {
            browserName = "chrome";
        }
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("http://stellarburgers.nomoreparties.site/");
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("http://stellarburgers.nomoreparties.site/");
                break;
            default:
                throw new RuntimeException("Неверное название браузера");
        }
        return driver;
    }
}