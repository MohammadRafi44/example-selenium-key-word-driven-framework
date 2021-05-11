package com.example.objects;

import org.openqa.selenium.By;

public class TestCrewObjects {
    public static final By TESTCREW_ABOUT_US = By.xpath("//a[@class='nav-link'][contains(.,'About Us')]");
    public static final By TESTCREW_ABOUTUS_EMAIL_TEXT = By.cssSelector("div[class='col-md-4'] div:nth-child(3)");
    public static final String TESTCREW_ABOUTUS_EMAIL_TEXT_DATA = "info@testcrew.sa";

    public static final By TESTCREW_CONTACT_US_LINK = By.xpath("//a[@class='nav-link'][normalize-space()='Contact Us']");
    public static final By TESTCREW_CONTACT_US_WHATSAPP_LINK = By.cssSelector("a[class='q8c6tt-0 jvFDcV']");
    public static final String TESTCREW_WHATSAPP_SHARE_PAGE_TITLE="Share on WhatsApp";

    public static final By TESTCREW_HOMEPAGE_SERVICES_LINK = By.xpath("(//a[text()[normalize-space()='Services']])[2]");
    public static final By TESTCREW_HOMEPAGE_SERVICES_TESTADVISORY_LINK = By.xpath("(//a[text()[normalize-space()='Test Advisory & Consulting Services']])[2]");
    public static final By TESTCREW_ADVISORY_PAGE_TEXT_ELEMENT= By.xpath("//span[@class='color2 font-weight-semibold']");
    public static final String TESTCREW_ADVISORY_PAGE_TEXT_DATA="Test Advisory & Consulting";

    // ********  Rest API - Expected Result Addition for calculator service ************
    public static final String REST_ExpectedResultString = "80";

    // ********  SOAP API - Expected Result Addition for calculator service ************
    public static final String SOAP_ExpectedResultString = "110";






}
