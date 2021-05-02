package com.example.demo;

import com.example.demo.pages.facebook.FacebookHomePage;
import com.example.demo.pages.facebook.FacebookLoginPage;
import com.example.demo.util.SeleniumCommon;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FacebookTests {

    @Autowired
    FacebookLoginPage facebookLoginPage;

    @Autowired
    FacebookHomePage facebookHomePage;

    @Autowired
    SeleniumCommon seleniumCommon;

    @BeforeEach
    void initialize() {
        SeleniumCommon.navigateToURL("https://www.facebook.com");
    }

    @Test
    void postStatus() {
        facebookLoginPage.fillEmailAddress("username");
        facebookLoginPage.fillPassword("password");
        facebookLoginPage.clickLogin();
        Assert.assertTrue("Login Unsuccessful", facebookHomePage.verifyLogin());
        facebookHomePage.enterStatusMessage("Hello World");
        facebookHomePage.clickPost();
        String firstFeedMessage = facebookHomePage.getFirstFeed();
        Assert.assertTrue("Status message verification failed", firstFeedMessage.contains("Hello World"));
        facebookHomePage.logOut();
        Assert.assertTrue("Log out unsuccessful", facebookLoginPage.verifyLogOut());
    }

    @AfterEach
    void tearDown() {
        SeleniumCommon.closeBrowser();
    }
}
