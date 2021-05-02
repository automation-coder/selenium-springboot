package com.example.demo.pages.wallethub;

import com.example.demo.util.SeleniumCommon;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class InsuranceCompanyPage {

    String[] reviewsSection = {"XPATH", "//span[contains(text(),'Overview')" + "]/parent::*/following-sibling::a[1]"};
    String[] ratingStar = {"CSS", "#reviews-section .review-action>review-star" + " " + "svg:nth-child(@NO_OF_STARS@)"};
    String[] selectedRatingStar = {"CSS", ">g>path:nth-child(2)"};
    String[] policyList = {"XPATH", ".//div[contains(@class,'dropdown second')" + "]"};
    String[] policyInList = {"XPATH", ".//li[contains(text(),'@POLICY@')]"};
    String[] review = {"XPATH", ".//textarea[contains(@placeholder,'Write your" + " " + "review')]"};
    String[] submit = {"XPATH", ".//div[text()='Submit']"};

    public void clickReviews() {
        SeleniumCommon.click(reviewsSection);
    }

    public void hoverToRatingStar(int rating) {
        ratingStar[1] = ratingStar[1].replace("@NO_OF_STARS@", String.valueOf(rating));
        if (SeleniumCommon.isEnabled(ratingStar)) {
            SeleniumCommon.hoverToElement(ratingStar);
        }
    }

    public String getChangedColorCodeOfRatingStarOnHover() {
        selectedRatingStar[1] = ratingStar[1] + selectedRatingStar[1];
        WebElement selectedStarElement = SeleniumCommon.getWebElementFromLocator(selectedRatingStar);
        return selectedStarElement.getAttribute("stroke");
    }

    public void clickRatingStar() {
        SeleniumCommon.click(ratingStar);
    }

    public void selectPolicyFromList(String policy) {
        SeleniumCommon.click(policyList);
        policyInList[1] = policyInList[1].replaceAll("@POLICY@", policy);
        SeleniumCommon.click(policyInList);
    }

    public void writeReview(String text) {
        SeleniumCommon.fillText(review, text);
    }

    public void clickSubmit() {
        SeleniumCommon.click(submit);
    }
}
