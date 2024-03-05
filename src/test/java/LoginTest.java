import model.User;
import model.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegisterPage;
import page_object.ResetPasswordPage;
public class LoginTest extends MainRule {
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ResetPasswordPage resetPasswordPage;
    private String accessToken;
    private User user;


    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        user = User.createRandomUser();
        UserSteps.postCreateNewUser(user);
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной.")
    @Description("Проверка кнопки \"Войти в аккаунт\" на главной странице лендинга.")
    public void enterByLoginButtonTest() {
        mainPage.clickOnLoginButton();
        loginPage.authorize(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный Кабинет\".")
    @Description("Проверка кнопки \"Личный Кабинет\" в хедере главной страницы.")
    public void enterByPersonalAccountButtonTest() {
        mainPage.clickOnAccountButton();
        loginPage.authorize(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации.")
    @Description("Проверка входа через форму регистрации.")
    public void enterByRegistrationFormTest() {
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegister();
        registerPage.waitForLoadRegisterPage();
        registerPage.clickOnLoginButton();
        loginPage.waitForLoadEntrance();
        loginPage.authorize(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    @Description("Проверка входа через форму восстановления пароля.")
    public void enterByPasswordRecoveryFormatTest() {
        mainPage.clickOnAccountButton();
        loginPage.clickOnForgotPasswordLink();
        resetPasswordPage.waitForLoadedResetPassword();
        resetPasswordPage.clickOnLoginLink();
        loginPage.authorize(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
    }

    @After
    public void cleanUp() {
        UserSteps.deleteUser(accessToken);
    }

}
