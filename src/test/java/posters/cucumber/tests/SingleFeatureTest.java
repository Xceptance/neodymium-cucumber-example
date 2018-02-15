package posters.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumCucumberRunner;

import cucumber.api.CucumberOptions;

//This runner is used for development purposes. So you can run a single cucumber test case over and over again
@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features/Register.feature", glue = "posters", tags = "", monochrome = true, plugin =
{
  "pretty", // console output
  "html:target/cucumber-report/", // html report
})
public class SingleFeatureTest
{
}
