package posters.cucumber.support;

import io.cucumber.java.en.Then;
import posters.pageobjects.pages.browsing.HomePage;

public class HomePageSupport
{
    @Then("^I see homepage with logo, carousel, hot products and footer and it's title is \"([^\"]*)\"$")
    public void validateHomePage(String title)
    {
        var homePage = new HomePage();
        homePage.title.validateTitle(title);
        homePage.validateStructure();
        homePage.footer.validate();
    }
}
