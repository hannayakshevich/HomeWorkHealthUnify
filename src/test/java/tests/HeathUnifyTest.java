package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeathUnifyTest {
    private WebDriver driver;
    private String webSite = "https://healthunify.com/bmicalculator/";
    private String weight = "70";
    private String overweightCategory = "Your category is Overweight";
    private String normalCategory = "Your category is Normal";

    @BeforeClass
    public void runDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void weightInKilogramsTest(){
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);
        driver.findElement(By.xpath("//select[@name='opt2']")).sendKeys("5");
        driver.findElement(By.xpath("//select[@name='opt3']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']")).getText();
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']")).getText();
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']")).getText();
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']")).getText();
        Assert.assertEquals(actualSiUnits, "29.14");
        Assert.assertEquals(actualUsUnits, "29.63");
        Assert.assertEquals(actualUkUnits, "185.04");
        Assert.assertEquals(actualResultText, overweightCategory);
    }

    @Test
    public void weightInPoundsTest(){
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);
        driver.findElement(By.xpath("//select[@name='opt1']")).sendKeys("pounds");
        driver.findElement(By.xpath("//select[@name='opt2']")).sendKeys("5");
        driver.findElement(By.xpath("//select[@name='opt3']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']")).getText();
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']")).getText();
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']")).getText();
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']")).getText();
        Assert.assertEquals(actualSiUnits, "29.14");
        Assert.assertEquals(actualUsUnits, "29.63");
        Assert.assertEquals(actualUkUnits, "185.02");
        Assert.assertEquals(actualResultText, overweightCategory);
    }

    @Test
    public void heightInCsmTest(){
        driver.get(webSite);
        driver.findElement(By.xpath("//input[@name='wg']")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@name='ht']")).sendKeys("170");
        driver.findElement(By.xpath("//input[@name='cc']")).click();
        String actualSiUnits = driver.findElement(By.xpath("//input[@class='resform']")).getText();
        String actualUsUnits = driver.findElement(By.xpath("//input[@name='us']")).getText();
        String actualUkUnits = driver.findElement(By.xpath("//input[@name='uk']")).getText();
        String actualResultText = driver.findElement(By.xpath("//input[@class='content']")).getText();
        Assert.assertEquals(actualSiUnits, "24.22");
        Assert.assertEquals(actualUsUnits, "24.63");
        Assert.assertEquals(actualUkUnits, "153.8");
        Assert.assertEquals(actualResultText, normalCategory);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
