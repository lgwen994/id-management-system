/*
 * @(#)StvSimpleAppApplicationTests.java
 * (C)Copyright 2015 Toshiba Solutions Corporation, All rights reserved.
 */
package example.staveware.stvwebstore;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * StvSimpleAppApplicationTests
 * 
 * @author wen-shp
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StvSimpleAppApplicationTests {

    /**
     * WAIT_TIME
     */
    public final static Long DEFAULT_WAIT_TIME_2000L = 2000L;

    /**
     * port
     */
//    @LocalServerPort
    protected int port;
    /**
     * WebDriver
     */
    protected WebDriver driver;
    /**
     * Wait
     */
    protected Wait<WebDriver> wait;

    /**
     * setup
     */
    @Before
    public void setup() {
        // firefox
        // ProfilesIni allProfiles = new ProfilesIni();
        // FirefoxProfile myProfile = allProfiles.getProfile("default");
        // myProfile.setAcceptUntrustedCertificates(true);
        // myProfile.setAssumeUntrustedCertificateIssuer(true);
        // driver = new FirefoxDriver(myProfile);
        // chrome
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        // IE
        // File file = new File(
        // "D:\\tool\\browser\\IEDriverServer_x64_3.9.0\\IEDriverServer.exe");
        // System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        // driver = new InternetExplorerDriver();

        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME_2000L);
    }

    /**
     * destroy
     */
    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }

    protected void pressButton(String buttonText) {
        driver.findElement(
                By.xpath("//button[@type='button']/span[contains(text(), '"
                        + buttonText + "')]"))
                .click();
        sleep(DEFAULT_WAIT_TIME_2000L);
    }

    protected String getAlertMessage() {
        return driver
                .findElement(
                        By.xpath("//div[@class='el-message-box__message']/p"))
                .getText();
    }

    protected void sleep() {
        sleep(DEFAULT_WAIT_TIME_2000L);
    }

    protected void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String getInputTextValue(String elementId) {
        return driver.findElement(By.id(elementId)).getAttribute("value");
    }

    /**
     * gotoTestPage
     */
    protected void gotoRegistPage(String pageUrl) {
        driver.get("http://localhost:" + port + "/#/" + pageUrl);
        sleep(DEFAULT_WAIT_TIME_2000L);
        Assert.assertEquals("IDMFMaintenance", driver.getTitle());
    }

    protected String getHeaderTitle() {
        return driver
                .findElement(By
                        .xpath("//div[@id='main']/section/main/div//div[@class='el-card__header']/div/span"))
                .getText();
    }

    protected void screenshoot(String path, String fileName, Integer caseNo,
            Integer number) {
        if (caseNo == null) {
            caseNo = Integer.valueOf(1);
        }
        if (number == null) {
            number = Integer.valueOf(1);
        }
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("c:\\stv5_3\\");
            sb.append(path);
            sb.append("\\");
            sb.append(fileName);
            sb.append(caseNo);
            sb.append("-");
            sb.append(number);
            sb.append(".png");
            FileUtils.copyFile(scrFile, new File(sb.toString()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
