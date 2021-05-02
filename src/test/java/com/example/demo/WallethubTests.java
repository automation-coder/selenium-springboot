package com.example.demo;

import com.example.demo.pages.wallethub.InsuranceCompanyPage;
import com.example.demo.pages.wallethub.WallethubHomePage;
import com.example.demo.pages.wallethub.WallethubLoginPage;
import com.example.demo.util.SeleniumCommon;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class WallethubTests {

    @Autowired
    Environment environment;

    @Autowired
    InsuranceCompanyPage insuranceCompanyPage;

    @Autowired
    WallethubLoginPage wallethubLoginPage;

    @Autowired
    WallethubHomePage wallethubHomePage;

    @Autowired
    SeleniumCommon seleniumCommon;

    @BeforeEach
    void initialize() {
        SeleniumCommon.navigateToURL("https://wallethub.com/join/light");
        wallethubLoginPage.clickLoginTab();
        wallethubLoginPage.fillEmailAddress("email");
        wallethubLoginPage.fillPassword("password");
        wallethubLoginPage.clickLogin();
        Assert.assertTrue("Login unsuccessful", wallethubHomePage.verifyLogin());
    }

    @Test
    void postReview() {
        /***All the input data used in the Tests can be read from files
         * corresponding to each Test class. It is not implemented here
         * because of lesser no of data***/
        SeleniumCommon.navigateToURL("http://wallethub.com/profile/test_insurance_company/");
        insuranceCompanyPage.clickReviews();
        insuranceCompanyPage.hoverToRatingStar(4);
        Assert.assertEquals("Color is not changed on Hover",
                "#4ae0e1", insuranceCompanyPage.getChangedColorCodeOfRatingStarOnHover());
        insuranceCompanyPage.clickRatingStar();
        insuranceCompanyPage.selectPolicyFromList("Health Insurance");
        String reviewText = "This review is added by Automation " +
                "This review is added by Automation " +
                "This review is added by Automation " +
                "This review is added by Automation " +
                "This review is added by Automation " +
                "This review is added by Automation ";
        insuranceCompanyPage.writeReview(reviewText);
        insuranceCompanyPage.clickSubmit();
        Assert.assertTrue("Review hasn't been posted successfully",
                wallethubHomePage.verifyReviewPostSuccess());
        wallethubHomePage.gotoProfile();
        Assert.assertTrue("Review is not found on the Profile page",
                wallethubHomePage.verifyReviewInProfile("Test Insurance Company"));
    }

    @AfterEach
    void tearDown() {
        wallethubHomePage.logout();
        SeleniumCommon.closeBrowser();
    }
}
