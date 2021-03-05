package posters.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumCucumberRunner;

import io.cucumber.junit.CucumberOptions;

// This test case is used for development purposes. So you can run a single cucumber test case over and over again
// It should not be executed in automatic test runs (CI / CD).
// Therefore it is excluded in the pom.xml in the line: <include>posters/cucumber/tests/All*FeaturesTest.java</include>.
@RunWith(NeodymiumCucumberRunner.class)
// features - the paths of the *.feature files
// glue - the paths to load step definitions, hooks and plugins
// tags - annotations in the step definition files can be used to define which scenarios and steps should be carried out
// - - -- they can be freely defined (e.g.: @Wip - work in progress)
// monochrome - false produces colored console output if console supports it
// - - - - - -- in Eclipse use plugin "ANSI Escape in Console"
// - - - - - -- default value is false
// - - - - - -- therefore you have to set it to true for more readable console messages,
// - - - - - -- if the console does not support colored output
// dryRun - checks if all the steps have the step definition without completely running the tests
// - - - -- is equivalent to cucumber.execution.dry-run in cucumber.properties
// - - - -- use it here for single test cases
// publish - publish the report online to https://reports.cucumber.io
// - - - - - is equivalent to cucumber.publish.enabled in cucumber.properties
// plugin - register plugins

// Recommendation for experts:
// For more information look at class io.cucumber.junit.CucumberOptions.
@CucumberOptions(features = "src/test/java/posters/cucumber/features", //
    glue = "posters.cucumber.support", //
    tags = "@Wip", //
    monochrome = true, //
    // dryRun = true, //
    // publish = true, //
    plugin =
    {
      // Plugins for generating additional JSON and XML reports.
      // Is equivalent to cucumber.plugin in cucumber.properties.
      // The plugin for generating an html report is included in the cucumber.properties file for all test cases.
      "json:target/cucumber-report/cucumber.json",
      "junit:target/cucumber-report/cucumber.xml"
    })
public class SingleFeatureTest
{
}
