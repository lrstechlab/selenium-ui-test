package tipico.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tipico.AbstractComponents.AbstractComponent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class JobsPage extends AbstractComponent {

    WebDriver driver;

    public JobsPage(WebDriver driver) {
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    @FindBy(xpath = "//*[@id=\"results\"]/div/section/div[2]/div")
    List<WebElement> activeJobs;

    @FindBy(xpath = "//*[@id='results']/div/section/nav/ul/li")
    List<WebElement> carrierlistpagination;

    @FindBy(xpath = "//*[@id=\"results\"]/div/section/div[2]/div")
    List<WebElement> jobListElement;

    @FindBy(xpath = "//*[@id='results']/div/section/nav/ul/li[3]/a")
    WebElement nextPageButton;


    public WebElement nextPageButton() {
        return nextPageButton;
    }

    public List<WebElement> jobListElement() {
        return jobListElement;
    }

    public List<WebElement> carrierlistpagination() {
        return carrierlistpagination;
    }

    public List<WebElement> activeJobsList() {
        return activeJobs;
    }

    public List<String> fetchJobsList() throws InterruptedException {
        List<String> jobList = fetchjobList(carrierlistpagination, jobListElement, nextPageButton);
        return jobList;
    }

    public void writeToDB(List<String> jobList) throws SQLException, IOException {
        getDbUpdate(jobList);
    }

    private static List<String> neededVariable;

    public List<String> getVariable() {
        return neededVariable;
    }

    public void setVariable(List<String> var) {
        this.neededVariable = var;
    }


}
