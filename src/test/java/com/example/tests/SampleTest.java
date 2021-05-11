package com.example.tests;

import com.example.base.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SampleTest {
    public static final By element2 = By.xpath("//a[@class='nav-link'][normalize-space()='Contact Us']");

    @Test
    public void SampleFlipkart() throws InterruptedException {
        System.out.println("Hey Rafi");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://testcrew.sa/");
        driver.manage().window().maximize();
        Thread.sleep(3);
        WebElement element1 = driver.findElement(By.xpath("//a[@class='nav-link'][normalize-space()='Contact Us']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.background='Yellow'", new Object[]{element1});


    }

    }


