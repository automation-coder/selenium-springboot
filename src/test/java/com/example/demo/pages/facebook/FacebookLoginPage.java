package com.example.demo.pages.facebook;

import com.example.demo.util.SeleniumCommon;
import org.springframework.stereotype.Component;

@Component
public class FacebookLoginPage {

    String[] emailAddress = {"XPATH", "//*[@name='email']"};
    String[] password = {"XPATH", "//*[@name='pass']"};
    String[] login = {"XPATH", "//*[@name='login']"};


    public void fillEmailAddress(String text) {
        SeleniumCommon.fillText(emailAddress, text);
    }

    public void fillPassword(String text) {
        SeleniumCommon.fillText(password, text);
    }

    public void clickLogin() {
        SeleniumCommon.click(login);
    }

    public boolean verifyLogOut() {
        return SeleniumCommon.isEnabled(login);
    }

}
