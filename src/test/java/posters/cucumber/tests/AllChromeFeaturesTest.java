package posters.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumCucumberRunner;

import io.cucumber.junit.CucumberOptions;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/posters/cucumber/features/Order.feature", //
    glue = "posters", //
    tags = "@Chrome and not @Skip and not @Blocked and not @Wip", //
    monochrome = true)
public class AllChromeFeaturesTest
{
}
