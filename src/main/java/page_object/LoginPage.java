package page_object;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    @Getter
    private final By centerTitle = By.xpath(".//main/div/h2[text()='Вход']");

    //Поле "Email"
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");

    //Поле "Пароль"
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");

    //Логотип
    public final By enterButton = By.xpath(".//div/a[@href='/']");

    //Кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    //Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");

    //Ссылка "Зарегистрироваться"
    private final By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");

    //Ссылка "Восстановить пароль"
    private final By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы")
    public void waitForPageOpening() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }

    @Step("Заполнение поля \"Email\"")
    public void setEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля \"Пароль\"")
    public void setPasswordField(String passsword){
        driver.findElement(passwordField).sendKeys(passsword);
    }

    @Step("Клик по кнопке \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitForPageOpening();
    }

    @Step("Клик по ссылке \"Зарегистрироваться\"")
    public void clickOnRegister() {
        driver.findElement(registerLink).click();
        waitForPageOpening();
    }

    @Step("Клик по ссылке \"Восстановить пароль\"")
    public void clickOnForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        waitForPageOpening();
    }

    @Step("Клик по кнопке \"Конструктор\"")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForPageOpening();
    }

    @Step("Клик по кнопке \"Stellar Burgers\"")
    public void clickOnLogo() {
        driver.findElement(enterButton).click();
        waitForPageOpening();
    }

    @Step("Авторизация пользователя")
    public void authorize(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        clickOnLoginButton();
    }

    @Step("Ожидание загрузки страницы с текстом 'Вход'")
    public void waitForLoadEntrance() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(centerTitle));
    }
}
