package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    WebDriver driver;

    // Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");

    // Кнопка "Выход"
    private final By exitButton = By.xpath(".//li/button[text()='Выход']");

    // Проверочная надпись для перехода в Личный кабинет
    public final By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");


    @Step("Ожидание загрузки страницы")
    public void waitForPageOpening() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForPageOpening();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickOnExitButton() {
        driver.findElement(exitButton).click();
        waitForPageOpening();
    }

    @Step("Ожидание загрузки страницы личного кабинета с текстом изменения персональных данных")
    public void waitForLoadProfilePage() {
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
}
