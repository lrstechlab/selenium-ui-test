package tipico.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tipico.AbstractComponents.AbstractComponent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
		
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory

	@FindBy(id="_evidon-accept-button")
	WebElement acceptCookies;

	@FindBy(xpath= "//*[@id='js-nav']/ul/li[1]/a")
	WebElement jobsButton;

	@FindBy(xpath="//*[@id=\"results\"]/div/section/div[2]/div")
	List<WebElement> activeJobs;

	@FindBy(xpath = "//*[@id=\"results\"]/div/section/nav/ul/li[4]/a")
	WebElement carrierlistpagination;

	@FindBy(xpath ="//*[@id=\"results\"]/div/section/div[2]/div")
	List<WebElement> jobListElement;

	@FindBy(xpath ="//*[@id='results']/div/section/nav/ul/li[3]/a")
	WebElement nextPageButton;


	public WebElement nextPageButton(){
		return nextPageButton;
	}

	public List<WebElement> jobListElement(){
		return jobListElement;
	}

	public WebElement carrierlistpagination(){
		return carrierlistpagination;
	}

	public List<WebElement> activeJobsList(){
		return activeJobs;
	}


	public void acceptCookies(){
		acceptCookies.click();
	}

//one resouce pfolder path is fixed this will also get parameterised. i will refactor this later.
	public void goTo()
	{
		driver.get("https://www.tipico-careers.com/en/jobs/");
	}




	public List<String> fetchjobList2() throws InterruptedException {
		List<String> jobList = new ArrayList<String>();
		for (int page = 1; page <= 3; page++) {
			// Wait for the page to load
			System.out.println("the pagination started");
			Thread.sleep(1000);
			// Find all the job listings on the current page
			List<WebElement> jobElements = jobListElement();
			// List<WebElement> jobElements = driver.findElements(By.xpath("//*[@id=\"results\"]/div/section/div[2]/div"));
			// Add the job titles to the jobList
			for (WebElement jobElement : jobElements) {
				String jobTitle = jobElement.getText();
				jobList.add(jobTitle);
			}
			//WebElement nextPageButton = driver.findElement(By.xpath("//*[@id='results']/div/section/nav/ul/li[3]/a"));
			WebElement nextPageButton = nextPageButton();
			String url = nextPageButton.getAttribute("href");
			driver.get(url);
		}

		return jobList;
	}

	public void writeToDB(List<String> jobList) throws SQLException, IOException {
		getDbUpdate(jobList);
	}

	private static List<String> neededVariable;

	public List<String> getVariable(){
		return neededVariable;
	}

	public void setVariable(List<String> var){
		this.neededVariable = var;
	}
















}
