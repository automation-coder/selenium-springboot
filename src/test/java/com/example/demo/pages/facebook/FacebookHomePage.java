package com.example.demo.pages.facebook;

import com.example.demo.util.SeleniumCommon;
import org.springframework.stereotype.Component;

@Component
public class FacebookHomePage {

    String[] firstFeed = {"XPATH", "//div[@role='feed']/div[1]"};
    String[] statusbaBar = {"XPATH", "//span[contains(text(), 'on your mind')" + "]"};
    String[] statusMessage = {"XPATH", "//div[contains(@aria-label, 'on your " + "mind')]"};
    String[] post = {"XPATH", "//div[@aria-label='Post']"};
    String[] account = {"XPATH", "//div[@aria-label='Account']"};
    String[] logOut = {"XPATH", "//span[contains(text(), 'Log Out')]"};


    public boolean verifyLogin() {
        return SeleniumCommon.isEnabled(statusbaBar);
    }

    public void enterStatusMessage(String text) {
        SeleniumCommon.click(statusbaBar);
        SeleniumCommon.fillText(statusMessage, text);
    }

    public void clickPost() {
        SeleniumCommon.click(post);
    }

    public String getFirstFeed() {
        return SeleniumCommon.getText(firstFeed);
    }

    public void logOut() {
        SeleniumCommon.click(account);
        SeleniumCommon.click(logOut);
    }

}
