package com.example.modules;

import com.example.base.DriverManager;
import com.example.base.UiBaseModule;
import com.example.objects.TestCrewObjects;
import com.example.utils.DataReader;
import com.example.utils.Helper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestContext;

import static com.example.objects.TestCrewObjects.*;
import static org.testng.Assert.assertTrue;

public class TestCrewModule extends UiBaseModule {

    @Step(value = "Launch TestCrew WebSite")
    public void launchTestCrewWebSite(String url) {
        openUrl(url);
    }

    @Step(value = "About Us Page Navigation")
    public void AboutUsPage(String tcId) {
        highlightElement(TESTCREW_ABOUT_US);
        click(TESTCREW_ABOUT_US);
        ScrollToElement(TESTCREW_ABOUTUS_EMAIL_TEXT);
        highlightElement(TESTCREW_ABOUTUS_EMAIL_TEXT);
        ElementTextContains(TESTCREW_ABOUTUS_EMAIL_TEXT,TESTCREW_ABOUTUS_EMAIL_TEXT_DATA);
        takeScreenshot();
    }

    @Step(value = "Services Menu - Test Advisory Page - Verification")
    public void Test_Advisory_Verify(String tcID){
        highlightElement(TESTCREW_HOMEPAGE_SERVICES_LINK);
        click(TESTCREW_HOMEPAGE_SERVICES_LINK);
        highlightElement(TESTCREW_HOMEPAGE_SERVICES_TESTADVISORY_LINK);
        click(TESTCREW_HOMEPAGE_SERVICES_TESTADVISORY_LINK);
        ElementTextContains(TESTCREW_ADVISORY_PAGE_TEXT_ELEMENT,TESTCREW_ADVISORY_PAGE_TEXT_DATA);
        sleep(2);
        takeScreenshot();

    }

    }
