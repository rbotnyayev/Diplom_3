import model.User;
import model.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import page_object.*;

public class JumpToPersonalAccountTest extends MainRule {
    static MainPage mainPage;
    static LoginPage loginPage;
    static RegisterPage registerPage;
    ProfilePage profilePage;
    private static String accessToken;
    private User user;


    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);
        user = User.createRandomUser();
        UserSteps.postCreateNewUser(user);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода по клику на \"Личный кабинет\"")
    public void transitionToProfilePageTest() {
        mainPage.clickOnAccountButton();
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Ошибка! Страница не отображается",
                driver.findElement(loginPage.getCenterTitle()).isDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета")
    @Description("Проверка перехода на вкладку \"Конструктор\" со страницы авторизации пользователя")
    public void transitionToConstructorFromProfilePageTest() {
        mainPage.waitForFullPageLoading();
        mainPage.clickOnAccountButton();
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Ошибка! Переход  в конструктор не осуществлён",
                driver.findElement(mainPage.getTextBurgerMainPage()).isDisplayed());
    }

    @Test
    @DisplayName("Клик по логотипу \"Stellar Burgers\"")
    @Description("Проверка перехода в конструктор при нажатии на логотип \"Stellar Burgers\"")
    public void transitionToStellarBurgersFromProfilePageTest() {
        mainPage.clickOnAccountButton();
        loginPage.waitForLoadEntrance();
        loginPage.clickOnLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Ошибка! Страница с конструктором не отображается",
                driver.findElement(mainPage.getTextBurgerMainPage()).isDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода по кнопке 'Выйти' в личном кабинете")
    public void exitFromProfileTest() {
        mainPage.clickOnAccountButton();
        loginPage.waitForLoadEntrance();
        loginPage.authorize(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.clickOnAccountButton();
        profilePage.waitForLoadProfilePage();
        profilePage.clickOnExitButton();
        mainPage.waitForFullPageLoading();
        Assert.assertTrue("Не удалось выйти из аккаунта",
                driver.findElement(loginPage.getCenterTitle()).isDisplayed());
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
    }
}
