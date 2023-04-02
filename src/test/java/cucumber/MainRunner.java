package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber->  TestNG, junit

@CucumberOptions(features="src/test/java/cucumber",glue="tipico.stepDefinitions")
public class MainRunner extends AbstractTestNGCucumberTests{

	
}
