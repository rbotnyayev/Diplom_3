import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;

public class ConstructorSectionsTransitionsTest {

    MainPage mainPage;
    WebDriver driver;
    Browser browser = new Browser();
    @Before
    public void setUp() {
        driver = browser.getWebDriver();
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход в раздел \"Булки\"")
    @Description("Проверка перехода в раздел \"Булки\" и появления изображения с булкой")
    public void transitionToBunsInConstructorTest() throws InterruptedException {
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        mainPage.checkTextBun();
    }

    @Test
    @DisplayName("Переход в раздел \"Соусы\"")
    @Description("Проверка перехода в раздел \"Соусы\" и появления изображения с соусом")
    public void transitionToSaucesInConstructorTest() throws InterruptedException {
        mainPage.clickOnSaucesButton();
        mainPage.checkTextSauce();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    @Description("Проверка перехода в раздел \"Начинки\" и появления изображения с начинкой")
    public void transitionToFillingsInConstructorTest() throws InterruptedException {
        mainPage.clickOnFillingsButton();
        mainPage.checkTextFillings();
    }

    @After
    public void cleanUp() {
            driver.quit();
    }
}