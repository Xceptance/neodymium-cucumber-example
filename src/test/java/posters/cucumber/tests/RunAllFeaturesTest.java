package posters.cucumber.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import com.xceptance.neodymium.NeodymiumCucumberRunner;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features", glue = "posters", tags = {"~@Skip"}, plugin =
{
  "pretty", // console output
  "html:target/cucumber-report/", // html report
})
public class RunAllFeaturesTest
{
}
