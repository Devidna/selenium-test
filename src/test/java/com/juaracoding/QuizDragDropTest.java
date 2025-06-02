package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class QuizDragDropTest {
    WebDriver driver;

    @BeforeClass
    @Parameters({ "url" })
    public void init(String url) {
        driver = DriverSingleton.createOrGetDriver();
        driver.get(url);
    }

    @Test(enabled = false)
    public void testStep01() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy({top: 300, behavior: 'smooth'})");
        Thread.sleep(2000);
        WebElement draggable = driver.findElement(By.xpath("//div[@id='box1']"));
        WebElement drop = driver.findElement(By.id("box101"));
        Actions builder = new Actions(driver);

        Action dragger = builder.clickAndHold(draggable)
                .pause(Duration.ofSeconds(2))
                .moveToElement(drop, 0, 2)
                .pause(Duration.ofSeconds(2))
                .release()
                .pause(Duration.ofSeconds(3))
                .build();
        dragger.perform();

        Thread.sleep(1000);
        String bgColor = draggable.getCssValue("background-color");
        String expected = "rgba(0, 255, 0, 1)";
        // jse.executeScript("alert('uhuyyy')");
        // jse.executeScript("document.querySelector('#box1').style.color = 'white'");
        Thread.sleep(5000);
        Assert.assertEquals(bgColor, expected);
    }

    private void dragAndDrop(String idDrag, String idDrop) {
        WebElement draggable = driver.findElement(By.id(idDrag));
        WebElement drop = driver.findElement(By.id(idDrop));

        Actions builder = new Actions(driver);
        Action dragger = builder.clickAndHold(draggable)
                .pause(Duration.ofSeconds(2))
                .moveToElement(drop, 0, 2)
                .pause(Duration.ofSeconds(2))
                .release()
                .pause(Duration.ofSeconds(3))
                .build();
        dragger.perform();
    }

    private void draggableSantui(String idDrag, String idDrop) {
        WebElement draggable = driver.findElement(By.id(idDrag));
        WebElement drop = driver.findElement(By.id(idDrop));
        Actions builder = new Actions(driver);
        builder.dragAndDrop(draggable, drop).perform();
    }

    private void customDragableSantui(String idDrag, String idDrop) {
        // todo: tweakerrr :DDDD
        // WebElement draggable = driver.findElement(By.id(idDrag));
        // WebElement drop = driver.findElement(By.id(idDrop));
        // Actions builder = new Actions(driver);
        // builder.moveInTicks(source, 0, 0)
        // .tick(builder.ActiongetActivePointer().createPointerDown(LEFT.asArg()))
        // .moveInTicks(target, 0, 0)
        // .tick(getActivePointer().createPointerUp(LEFT.asArg()));
    }

    @Test
    public void mingguO2Test() throws InterruptedException {
        // box1 (oslo) - box101 (norway)
        // box2 (stockholm) - box102 (Sweden)
        // actions.dragAndDrop(source, target).perform();

        String[][] keyElements = {
                { "box1", "box101" }, // (oslo - norway)
                { "box2", "box102" }, // (stockholm - sweden)
                { "box3", "box103" }, // (stockholm - sweden)
                { "box4", "box104" }, // (stockholm - sweden)
                { "box5", "box105" }, // (stockholm - sweden)
                { "box6", "box106" }, // (stockholm - sweden)
                { "box7", "box107" }, // (stockholm - sweden)
        };

        for (int row = 0; row < keyElements.length; row++) {
            draggableSantui(keyElements[row][0], keyElements[row][1]);
            Thread.sleep(2000);
        }

        // TODO: balikin lagi ke semula!! yaach...
        for (int i = 0; i < keyElements.length; i++) {
            draggableSantui(keyElements[i][0], "dropContent");
            Thread.sleep(1000);
        }
    }
}
