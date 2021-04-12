package posters.cucumber.support;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;

public class HomePageSupport
{
    @Then("^I see homepage with logo, carousel, hot products and footer and it's title is \"([^\"]*)\"$")
    @Step("validate homepage")
    public void validateHomePage(String title)
    {
        var homePage = new HomePage();
        homePage.isExpectedPage();
        homePage.title.validateTitle(title);
        homePage.validateStructure();
        homePage.footer.validate();
    }
}
