package posters.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.junit4.NeodymiumCucumberRunner;

import io.cucumber.junit.CucumberOptions;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features/", //
    glue = "posters", //
    tags = "@Firefox and not @Skip and not @Blocked and not @Wip", //
    monochrome = true)
public class AllFirefoxFeaturesTest
{
}
