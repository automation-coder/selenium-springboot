package com.example.demo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SeleniumCommon {
    private static WebDriver webDriver;
    private static final int MAX_TIME_OUT_IN_SECS = 30;

    public static WebElement getWebElementFromLocator(String[] locator) {
        String locatorType = locator[0];
        String locatorValue = locator[1];
        By searchCriteria;
        switch (locatorType.toUpperCase()) {
            case "XPATH":
                searchCriteria = By.xpath(locatorValue);
                break;
            case "CSS":
                searchCriteria = By.cssSelector(locatorValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locatorType.toUpperCase());
        }
        WebDriverWait wait = new WebDriverWait(webDriver, MAX_TIME_OUT_IN_SECS);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchCriteria));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDisplayed(String[] locator) {
        if (getWebElementFromLocator(locator) != null) {
            return getWebElementFromLocator(locator).isDisplayed();
        }
        return false;
    }

    @Autowired
    public void setWebDriver(WebDriver webDriver) {
        SeleniumCommon.webDriver = webDriver;
    }

    public static boolean isEnabled(String[] locator) {
        if (getWebElementFromLocator(locator) != null) {
            return getWebElementFromLocator(locator).isEnabled();
        }
        return false;
    }

    public static String getText(String[] locator) {
        return getWebElementFromLocator(locator).getText();
    }

    public static void navigateToURL(String url) {
        webDriver.navigate().to(url);
    }

    public static void fillText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void fillText(String[] locator, String text) {
        fillText(getWebElementFromLocator(locator), text);
    }

    public static void click(WebElement element) {
        element.click();
    }

    public static void click(String[] locator) {
        click(getWebElementFromLocator(locator));
    }

    public static void hoverToElement(String[] locator) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(getWebElementFromLocator(locator)).build().perform();
    }

    public static void closeBrowser() {
        webDriver.close();
        webDriver.quit();
    }
}
