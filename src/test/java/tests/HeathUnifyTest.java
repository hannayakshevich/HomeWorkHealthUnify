package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeathUnifyTest {
    private WebDriver driver;
    private String webSite = "https://healthunify.com/bmicalculator/";
    private String weight = "70";
    private String overweightCategory = "Your category is Overweight";
    private String normalCategory = "Your category is Normal";
    private String obeseCategory = "Your category is Obese";
    private SoftAssert softAssert;
    private String heightInFt = "5";
    private String heightInInches = "1";

    @BeforeClass
    public void runDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void enterWeightInKilogramsTest(){
        softAssert= new SoftAssert();
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);

        driver.findElement(By.xpath("//select[@name='opt2']")).sendKeys(heightInFt);
        driver.findElement(By.xpath("//select[@name='opt3']")).sendKeys(heightInInches);
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']"))
                .getAttribute("value");
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']"))
                .getAttribute("value");
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']"))
                .getAttribute("value");
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']"))
                .getAttribute("value");
        String expectedSiUnits = "29.14";
        String expectedUsUnits = "29.63";
        String expectedUkUnits = "185.04";
        softAssert.assertEquals(actualSiUnits, expectedSiUnits,"First soft assert failed");
        softAssert.assertEquals(actualUsUnits, expectedUsUnits,"Second soft assert failed");
        softAssert.assertEquals(actualUkUnits, expectedUkUnits,"Third soft assert failed");
        softAssert.assertEquals(actualResultText, overweightCategory,"Fourth soft assert failed");
        softAssert.assertAll();
    }

    @Test
    public void enterWeightInPoundsTest(){
        softAssert= new SoftAssert();
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);
        driver.findElement(By.xpath("//select[@name='opt1']")).sendKeys("pounds");
        driver.findElement(By.xpath("//select[@name='opt2']")).sendKeys(heightInFt);
        driver.findElement(By.xpath("//select[@name='opt3']")).sendKeys(heightInInches);
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']"))
                .getAttribute("value");
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']"))
                .getAttribute("value");
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']"))
                .getAttribute("value");
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']"))
                .getAttribute("value");
        String expectedSiUnits = "29.14";
        String expectedUsUnits = "29.63";
        String expectedUkUnits = "185.02";
        softAssert.assertEquals(actualSiUnits, expectedSiUnits, "First soft assert failed");
        softAssert.assertEquals(actualUsUnits, expectedUsUnits, "Second soft assert failed");
        softAssert.assertEquals(actualUkUnits, expectedUkUnits,"Third soft assert failed");
        softAssert.assertEquals(actualResultText, obeseCategory,"Fourth soft assert failed");
        softAssert.assertAll();
    }

    @Test
    public void enterHeightInCsmTest(){
        softAssert= new SoftAssert();
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@name='ht']")).sendKeys("170");
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']"))
                .getAttribute("value");
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']"))
                .getAttribute("value");
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']"))
                .getAttribute("value");
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']"))
                .getAttribute("value");
        String expectedSiUnits = "24.22";
        String expectedUsUnits = "24.63";
        String expectedUkUnits = "153.8";
        Assert.assertEquals(actualSiUnits, expectedSiUnits,"First soft assert failed");
        Assert.assertEquals(actualUsUnits, expectedUsUnits,"Second soft assert failed");
        Assert.assertEquals(actualUkUnits, expectedUkUnits,"Third soft assert failed");
        Assert.assertEquals(actualResultText, normalCategory,"Fourth soft assert failed");
        softAssert.assertAll();
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
