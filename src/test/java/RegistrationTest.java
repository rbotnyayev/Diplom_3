import model.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserSteps;
import org.junit.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegisterPage;

import static org.junit.Assert.assertTrue;


public class RegistrationTest extends MainRule {
    private String accessToken;
    private User user;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        user = User.createRandomUser();
    }


    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка возможности успешной регистрации")
    public void successfulRegistrationTest() {
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegister();
        registerPage.waitForLoadRegisterPage();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitForLoadEntrance();
        loginPage.authorize(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @Test
    @DisplayName("Проверка на ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    @Description("При вводе пароля меньше 6 символов, появляется сообщение 'Некорректный пароль'")
    public void failedPasswordRegistrationTest() {
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegister();
        registerPage.waitForLoadRegisterPage();
        registerPage.register(user.getName(), user.getEmail(), "ggg");
        //Проверка появление текста "Некорректный пароль"
        assertTrue("Текст об ошибке отсутствует", driver.findElement(registerPage.errorPasswordText).isDisplayed());
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
    }
}