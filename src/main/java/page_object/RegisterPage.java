package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;

    // Поле "Имя"
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");

    // Поле "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");

    // Поле "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");

    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Текст "Некорректный пароль"
    public final By errorPasswordText = By.className("input__error");

    // Текст заголовка "Регистрация" для проверки перехода на страницу регистрации
    public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");

    //Кнопка "Логин"
    public final By loginButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Ожидание загрузки страницы")
    public void waitForPageOpening() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }

    //Метод ввода email
    public void setEmailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    //Метод ввода пароля
    public void setPasswordField(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    //Метод ввода имени
    public void setNameField(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    //Метод клика по кнопке "Зарегистрироваться"
    public void clickOnRegisterButton(){
        driver.findElement(registerButton).click();
        waitForPageOpening();
    }

    //Метод клика по кнопке "Логин"
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
        waitForPageOpening();
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        clickOnRegisterButton();
    }

    @Step("Ожидание загрузки страницы по ожиданию текста \"Регистрация\"")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }

}
