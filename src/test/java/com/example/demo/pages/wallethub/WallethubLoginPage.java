package com.example.demo.pages.wallethub;

import com.example.demo.util.SeleniumCommon;
import org.springframework.stereotype.Component;

@Component
public class WallethubLoginPage {

    String[] loginTab = {"XPATH", ".//a[text()='Login']"};
    String[] emailAddress = {"XPATH", "//*[@name='em']"};
    String[] password = {"XPATH", "//*[@name='pw1']"};
    String[] login = {"XPATH", "//button/descendant::span[text()='Login']"};

    public void clickLoginTab() {
        SeleniumCommon.click(loginTab);
    }

    public void fillEmailAddress(String text) {
        SeleniumCommon.fillText(emailAddress, text);
    }

    public void fillPassword(String text) {
        SeleniumCommon.fillText(password, text);
    }

    public void clickLogin() {
        SeleniumCommon.click(login);
    }

}
