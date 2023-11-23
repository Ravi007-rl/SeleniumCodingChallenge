package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class codingChallenge {

    WebDriver driver;

    @Test
    public void SeleniumCodingChallenge() throws InterruptedException {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.t-mobile.com/tablets");
        driver.manage().window().maximize();

        //Test Case:1 Click on 'Brand' menu and then select 3 different types of brands
        SelectFilter("Brands","Apple","Samsung","TCL");

        //Test Case:2 Click on 'Brands' menu and select only one brand
        driver.navigate().to("https://www.t-mobile.com/tablets");
        SelectFilter("Brands","TCL");

        //Test Case:3 Click on 'Deals' menu and then select deals both filter
        driver.navigate().to("https://www.t-mobile.com/tablets");
        SelectFilter("Deals","New","Special offer");

        //Test Case:4 Click on 'Operating System' menu and then select iPadOS & Android
        driver.navigate().to("https://www.t-mobile.com/tablets");
        SelectFilter("Operating System","iPadOS","Android");

        //Test Case:5 Click on all 3 menus and apply filter for all 3 menus
        driver.navigate().to("https://www.t-mobile.com/tablets");
        SelectFilter("Brands","Apple","Samsung","TCL");
        SelectFilter("Deals","New","Special offer");
        SelectFilter("Operating System","iPadOS","Android");

        //Test Case:6 Click on 'Brand' menu and pass 'all' to select all brands checkbox
        driver.navigate().to("https://www.t-mobile.com/tablets");
        SelectFilter("Brands","all");
    }

    private void SelectFilter(String menuName, String... subMenuName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Wait until page loaded properly
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='placeholder-row']")));


        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//legend[contains(text(),'"+menuName+"')]")));
        menu.click();

        for (String sub:subMenuName) {
            if(sub.equals("all")){
                List<WebElement> subMenus = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//legend[contains(text(),'"+menuName+"')]/ancestor::mat-expansion-panel//div[@role='group']/mat-checkbox//span/span[@class='filter-display-name']")));
                for (WebElement element:subMenus) {
                    element.click();
                }
            }
            else {
                WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//legend[contains(text(),'"+menuName+"')]/ancestor::mat-expansion-panel//div[@role='group']/mat-checkbox//span/span[contains(text(),'"+sub+"')]")));
                subMenu.click();
            }
        }
        //just to check filter is working on not
        Thread.sleep(3000);
    }
}
