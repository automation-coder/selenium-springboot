package com.example.demo.pages.wallethub;

import com.example.demo.util.SeleniumCommon;
import org.springframework.stereotype.Component;

@Component
public class WallethubHomePage {

    String[] loggedInUser = {"XPATH", ".//div[contains(@class, 'brgm-user')" + "]/span"};
    String[] logout = {"XPATH", "//span[contains(text(),'Logout')]"};
    String[] profile = {"XPATH", "//a[contains(text(),'Profile')]"};
    String[] confirmReview = {"XPATH", ".//h4[text()='Your review has been " + "posted.']"};
    String[] postedReview = {"XPATH", ".//section/descendant::a[contains(text" + "()," + " " + "'@COMPANY_NAME@')]"};

    public boolean verifyLogin() {
        return SeleniumCommon.isEnabled(loggedInUser);
    }

    public boolean verifyReviewPostSuccess() {
        return SeleniumCommon.isDisplayed(confirmReview);
    }

    public void logout() {
        SeleniumCommon.hoverToElement(loggedInUser);
        SeleniumCommon.click(logout);
    }

    public void gotoProfile() {
        SeleniumCommon.hoverToElement(loggedInUser);
        SeleniumCommon.click(profile);
    }

    public boolean verifyReviewInProfile(String companyName) {
        postedReview[1] = postedReview[1].replace("@COMPANY_NAME@", companyName);
        return SeleniumCommon.isDisplayed(postedReview);
    }

}
