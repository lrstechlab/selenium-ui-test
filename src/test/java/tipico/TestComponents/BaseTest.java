package tipico.TestComponents;

import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import tipico.pageobjects.JobsPage;
import tipico.pageobjects.LandingPage;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public JobsPage jobsPage;


    Properties prop = new Properties();


    public WebDriver initializeDriver() throws IOException {
        // properties class

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//tipico//resources//GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        //prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            //driver.manage().window().setSize(new Dimension(1440,900));//full screen

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "/Users/rahulshetty//documents//geckodriver");
            driver = new FirefoxDriver();
            // Firefox
        } else if (browserName.equalsIgnoreCase("edge")) {
            // Edge
            System.setProperty("webdriver.edge.driver", "edge.exe");
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//tipico//resources//GlobalData.properties");
        prop.load(fis);
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo(prop.getProperty("homePage"));
        return landingPage;


    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {
        driver.close();
    }


}
