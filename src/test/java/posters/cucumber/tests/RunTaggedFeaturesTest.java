package posters.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumCucumberRunner;

import cucumber.api.CucumberOptions;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features", glue = "posters", tags = {"@Smoke","~@Skip"}, plugin =
    {
        "pretty", // console output
        "html:target/cucumber-report/", // html report
    })
public class RunTaggedFeaturesTest
{
}
