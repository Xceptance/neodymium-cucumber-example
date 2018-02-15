package posters.pageobjects.components;

import static com.codeborne.selenide.Selenide.title;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import io.qameta.allure.Step;

public class Title extends AbstractComponent
{

    public void isComponentAvailable()
    {
    }

    @Then("^page title should be \"([^\"]*)\"$")
    @Step("validate that the page title matches {title}")
    public void validateTitle(String title)
    {
        Assert.assertEquals(title, title());
    }
}
