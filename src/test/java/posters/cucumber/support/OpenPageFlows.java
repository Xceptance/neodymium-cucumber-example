package posters.cucumber.support;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Neodymium;

import io.cucumber.java.en.Given;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.RegisterPage;

public class OpenPageFlows
{
    @Given("^homepage is loaded$")
    public static HomePage homePage()
    {
        // clear cookies to ensure a new session
        clearBrowserCookies();
        // open home page
        open(Neodymium.configuration().url());
        return new HomePage().isExpectedPage();
    }

    @Given("^login page is loaded$")
    public static LoginPage loginPage()
    {
        // open login page and check for expected page
        return homePage().userMenu.openLogin();
    }

    @Given("^register page is loaded$")
    public static RegisterPage registerPage()
    {
        // open login page and check for expected page
        return homePage().userMenu.openRegister();
    }

    @Given("^product page \"([^\"]*)\" is open$")
    public static ProductDetailPage openProductDetailsPageWithClearedCookes(String url)
    {
        clearBrowserCookies();
        open(Neodymium.configuration().url() + url);
        return new ProductDetailPage().isExpectedPage();
    }

    // TODO check if needed - else delete
    public static ProductDetailPage openProductDetailsPage(String url)
    {
        open(Neodymium.configuration().url() + url);
        return new ProductDetailPage().isExpectedPage();
    }
}
