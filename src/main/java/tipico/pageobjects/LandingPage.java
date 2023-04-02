package tipico.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tipico.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    @FindBy(id = "_evidon-accept-button")
    WebElement acceptCookies;

    @FindBy(xpath = "//*[@id='js-nav']/ul/li[1]/a")
    WebElement jobsButton;

    public WebElement getJobsButton() {
        return jobsButton;
    }


    public JobsPage gotoJobsPage() {
        getJobsButton().click();

        JobsPage jobsPage = new JobsPage(driver);
        return jobsPage;
    }

    public void acceptCookies() {
        acceptCookies.click();
    }

    public void goTo(String url) {
        driver.get(url);
    }


}
