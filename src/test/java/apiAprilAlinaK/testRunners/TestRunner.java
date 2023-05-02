package apiAprilAlinaK.testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// mac - option + enter
// win - alt + enter
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/report.xml"},
        features = {"src/test/resources/features"},
        glue = {"apiAprilAlinaK/stepdefinitions"}
)
public class TestRunner {

}
