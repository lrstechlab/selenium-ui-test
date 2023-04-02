package tipico.stepDefinitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tipico.TestComponents.BaseTest;
import tipico.pageobjects.LandingPage;
import tipico.pageobjects.JobsPage;


public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public JobsPage jobsPage;


    //List<String> jobList = new ArrayList<String>();

    @Given("I landed on tipico homePage")
    public void I_landed_on_tipico_homePage() throws IOException {
        landingPage = launchApplication();
        landingPage.acceptCookies();
    }

    @Given("I navigated to the JobsPage")
    public void I_navigated_to_jobsPage() throws IOException {
        jobsPage = landingPage.gotoJobsPage();
    }

    @When("^i fetch all the job")
    public void i_fetchAllJobs() throws InterruptedException {
        List<String> jobList = jobsPage.fetchJobsList();
        jobsPage.setVariable(jobList);
    }

    @Then("I store all the fetched job details in mySQLDB")
    public void I_store_fetched_jobs_in_DB() throws SQLException, IOException {
        List<String> jobList = jobsPage.getVariable();
        System.out.println("printing the value of job list------------********************----------" + jobList);
        jobsPage.writeToDB(jobList);
        driver.close();
    }


}
