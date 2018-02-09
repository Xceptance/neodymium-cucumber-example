package posters.cucumber.support;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Context;

import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;

public class OpenHomePageFlow
{
    @When("^I am on the homepage of the Posters shop$")
    @Step("open home page flow")
    public static HomePage flow()
    {
        // clear cookies to ensure a new session
        clearBrowserCookies();

        // open home page
        open(Context.get().configuration.url());
        HomePage homePage = new HomePage();
        homePage.isExpectedPage();
        return homePage;
    };
}
