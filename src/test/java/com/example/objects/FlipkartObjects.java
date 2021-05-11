package com.example.objects;

import org.openqa.selenium.By;
public class FlipkartObjects {

    public static final By FLIPKART_SEARCH_BOX = By.name("q");
    public static final By FLIPKART_GO_BUTTON = By.xpath("//button[@type='submit']//*[local-name()='svg']");
    public static final By FLIPKART_LOGIN_POPUP = By.xpath("//a[@class='_14Me7y'][contains(.,'New to Flipkart? Create an account')]");
    public static final By FLIPKART_USERNAME_TEXT = By.xpath("//input[contains(@class,'_2IX_2- VJZDxU')]");
    public static final By FLIPKART_PASSWORD_TEXT = By.xpath("//input[contains(@type,'password')]");
    public static final By FLIPKART_LOGIN_BUTTON = By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL'][contains(.,'Login')]");
    public static final By FLIPKART_HOMEPAGE_ADHOC_CLICK = By.xpath("(//div[contains(@class,'_1TmfNK')])[1]");

    //  ******** Searched Item to be clicked   ********
    public static final By FLIPKART_SEARCHED_RESULT_ITEM = By.xpath("//div[text()='SAMSUNG Galaxy S20 Ultra (Cosmic Gray, 128 GB)']");
    public static final String FLIPKART_RIGHT_CHROME_TITLE_TEXT = "SAMSUNG Galaxy S20 Ultra ( 128 GB Storage, 12 GB RAM ) Online at Best Price On Flipkart.com";
    public static final By FLIPKART_SEARCHED_RESULT_ITEM_ADDING_TO_WISHLIST = By.xpath("//div[contains(@class,'_2hVSre _25_uYi')]");
}

