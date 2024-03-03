package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
public class MainPage {

    private final WebDriver driver;

    //Кнопка логин
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //Логотип сайта
    private final By logo = By.xpath(".//div/a[@href='/']");

    //Кнопка личного кабинета
    private final By accountButton = By.xpath(".//a[@href='/account']");

    //Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    //Вкладка "Булки"
    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");

    //Вкладка "Соусы"
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");

    //Вкладка "Начинки"
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");

    //Локатор активной вкладки
    private final By activeTab = By.xpath("//div[starts-with(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span");

    //картинка с "Булкой" для проверки видимости раздела
    public By bunsImg = By.xpath(".//img[@alt='Краторная булка N-200i']");

    //локатор текста заголовка "Булки" для реализации ожидания
    public By bunsText = By.xpath(".//h2[text()='Булки']");

    //картинка с "Соусом" для реализации ожидания
    public By saucesImg = By.xpath(".//p[text()='Соус с шипами Антарианского плоскоходца']");

    //картинка с "Начинкой" для реализации ожидания
    public By fillingsImg = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");

    //локатор текста "Соберите бургер" для реализации ожидания
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }

    @Step("Ожидание загрузки страницы, document.readyState")
    public void waitDocReady() {
        new WebDriverWait(driver, 15)
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
    @Step("Ожидание загрузки страницы полностью, анимация исчезает")
    public void waitForFullPageLoading() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
        waitDocReady();
    }

    @Step("Ожидание загрузки текста и картинки с булкой на главной странице")
    public void waitForLoadBunsHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(bunsImg));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(bunsText));
    }

    @Step("Ожидание загрузки картинки с соусом на главной странице")
    public void waitForLoadSaucesHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(saucesImg));
    }

    @Step("Ожидание загрузки с начинкой на главной странице")
    public void waitForLoadFillingsHeader() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsImg));
    }



    @Step("Клик по кнопке \"Личный кабинет\"")
    public void clickOnAccountButton(){
        driver.findElement(accountButton).click();
        waitForFullPageLoading();
    }

    @Step("Клик по кнопке \"Войти в аккаунт\"")
    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
        waitForFullPageLoading();
    }

    @Step("Клик по логотипу")
    public void clickOnLogo(){
        driver.findElement(logo).click();
        waitForFullPageLoading();
    }

    @Step("Клик по кнопке \"Конструктор\"")
    public void clickOnConstructorButton(){
        driver.findElement(constructorButton).click();
        waitForFullPageLoading();
    }

    @Step("Клик по кнопке \"Булки\"")
    public void clickOnBunsButton(){
        driver.findElement(bunsButton).click();
        waitForFullPageLoading();
    }

    @Step("Клик по кнопке \"Соусы\"")
    public void clickOnSaucesButton(){
        driver.findElement(saucesButton).click();
        waitForFullPageLoading();
    }

    @Step("Клик по кнопке \"Начинки\"")
    public void clickOnFillingsButton(){
        driver.findElement(fillingsButton).click();
        waitForFullPageLoading();
    }

    public void checkTextBun() {
        String countActivity = driver.findElement(activeTab).getText();
        assertEquals("Булки", countActivity);
    }
    public void checkTextSauce() throws InterruptedException {
        String countActivity = driver.findElement(activeTab).getText();
        assertEquals(countActivity,"Соусы");
    }
    public void checkTextFillings() throws InterruptedException {
        String countActivity = driver.findElement(activeTab).getText();
        assertEquals(countActivity,"Начинки");
    }

}
