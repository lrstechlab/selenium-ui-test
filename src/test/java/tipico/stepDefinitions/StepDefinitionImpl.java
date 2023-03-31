package tipico.stepDefinitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tipico.TestComponents.BaseTest;
import tipico.pageobjects.LandingPage;


public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;

	//List<String> jobList = new ArrayList<String>();

	@Given("I landed on tipico homePage")
	public void I_landed_on_tipico_homePage() throws IOException {
		landingPage= launchApplication();
		landingPage.acceptCookies();
	}

	@When("^i fetch all the job")
	public void i_fetchAllJobs() throws InterruptedException {
		List<String> jobList = landingPage.fetchjobList2();
		landingPage.setVariable(jobList);
		//System.out.println(jobList);
	}

    @Then("I store all the fetched job details in mySQLDB")
    public void I_store_fetched_jobs_in_DB() throws SQLException, IOException {
		List<String> jobList = landingPage.getVariable();
		System.out.println("printing the value of job list------------********************----------"+jobList);
		landingPage.writeToDB(jobList);
		driver.close();
    }




	

    
	
	
	
	
	
}
