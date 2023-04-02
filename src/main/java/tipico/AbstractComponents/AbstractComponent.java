package tipico.AbstractComponents;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Statement getDbUpdate(List<String> jobList) throws IOException, SQLException {


        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//tipico//resources//GlobalData.properties");
        prop.load(fis);

        // **** this part need refactor with proper resource folder path estabished and calling funtion getGlobalValue. There was error in reading .properties file from resource filter and this is why this way it is writte.
        String host = System.getProperty("host") != null ? System.getProperty("host") : prop.getProperty("host");
        String port = System.getProperty("port") != null ? System.getProperty("port") : prop.getProperty("port");
        String user = System.getProperty("user") != null ? System.getProperty("user") : prop.getProperty("user");
        String password = System.getProperty("password") != null ? System.getProperty("password") : prop.getProperty("password");

        Connection conmsql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/tipicoCar", user, password);
        Statement sqlSt = conmsql.createStatement();

        for (int i = 0; i < jobList.size(); i++) {
            System.out.println(jobList.get(i));
            String[] items = jobList.get(i).split("\\r?\\n");
            System.out.println("printing the value of jobslist Aftre split**********" + items[0] + items[1] + items[2]);
            String querry = jobListSql();
            System.out.println("preparing sql*******************");
            PreparedStatement pstmt = conmsql.prepareStatement(querry);
            pstmt.setString(1, items[0]);
            pstmt.setString(2, items[1]);
            pstmt.setString(3, items[2]);
            System.out.println("the querry is ---" + pstmt.toString());
            int numRows = pstmt.executeUpdate();
        }

        return sqlSt;
    }


    public List<String> fetchjobList(List<WebElement> carrierlistPage, List<WebElement> jobsListElement, WebElement nextButton) throws InterruptedException {
        List<String> jobList = new ArrayList<String>();
        int pagesLen = carrierlistPage.size();
        System.out.println("the lenght of the pages is----------" + pagesLen);

        for (int page = 1; page < pagesLen; page++) {
            // Wait for the page to load
            System.out.println("the pagination started");
            Thread.sleep(1000);
            // Find all the job listings on the current page
            List<WebElement> jobElements = jobsListElement;
            // List<WebElement> jobElements = driver.findElements(By.xpath("//*[@id=\"results\"]/div/section/div[2]/div"));
            // Add the job titles to the jobList
            for (WebElement jobElement : jobElements) {
                String jobTitle = jobElement.getText();
                jobList.add(jobTitle);
            }
            //WebElement nextPageButton = driver.findElement(By.xpath("//*[@id='results']/div/section/nav/ul/li[3]/a"));
            WebElement nextPageButton = nextButton;
            String url = nextPageButton.getAttribute("href");
            driver.get(url);
        }

        return jobList;
    }


    public String jobListSql() {

        String query = "INSERT IGNORE INTO activeJobs(Department, JobTitle, Location)VALUES(?, ?, ?)";
        return query;
    }

    public static String getGlobalValue(String key) throws IOException {

        InputStream inputStream = null;
        String result = null;
        Properties props = new Properties();

        try {

            String propFileName = "global.properties";
            inputStream = AbstractComponent.class.getClassLoader().getResourceAsStream(propFileName);
            if (null != inputStream) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the resources");
            }

            result = props.getProperty(key);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }


}
