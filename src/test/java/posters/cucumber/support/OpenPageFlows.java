package posters.cucumber.support;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Neodymium;

import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.RegisterPage;

public class OpenPageFlows
{
    @Given("^homepage is loaded$")
    @Step("open home page")
    public static HomePage homePage()
    {
        // clear cookies to ensure a new session
        clearBrowserCookies();
        // open home page
        open(Neodymium.configuration().url());
        return new HomePage().isExpectedPage();
    };

    @Given("^login page is loaded$")
    @Step("open login page")
    public static LoginPage loginPage()
    {
        // open login page and check for expected page
        return homePage().userMenu.openLogin();
    };

    @Given("^register page is loaded$")
    @Step("open register page")
    public static RegisterPage registerPage()
    {
        // open login page and check for expected page
        return homePage().userMenu.openRegister();
    };

    @Given("^product page \"([^\"]*)\" is open$")
    @Step("open product page with cleared cookes")
    public static ProductDetailPage openProductdetailsPageWithClearedCookes(String url)
    {
        clearBrowserCookies();
        open(Neodymium.configuration().url() + url);
        return new ProductDetailPage();
    }

    // TODO check if needed - else delete
    @Step("open product page without cleared cookes")
    public static ProductDetailPage openProductdetailsPage(String url)
    {
        open(Neodymium.configuration().url() + url);
        return new ProductDetailPage();
    }
}
