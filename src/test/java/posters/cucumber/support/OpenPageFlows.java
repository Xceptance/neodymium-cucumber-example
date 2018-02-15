package posters.cucumber.support;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Context;

import cucumber.api.java.en.Given;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.RegisterPage;

public class OpenPageFlows
{
    @Given("^homepage is loaded$")
    @Step("open home page")
    public static HomePage homepage()
    {
        // clear cookies to ensure a new session
        clearBrowserCookies();

        // open home page
        open(Context.get().configuration.url());
        HomePage homePage = new HomePage();
        homePage.isExpectedPage();
        return homePage;
    };

    @Given("^login page is loaded$")
    @Step("open login page")
    public static LoginPage loginPage()
    {
        // open login page and check for expected page
        LoginPage loginPage = homepage().userMenu.openLogin();
        loginPage.isExpectedPage();
        return loginPage;
    };

    @Given("^register page is loaded$")
    @Step("open register page")
    public static RegisterPage registerPage()
    {
        // open login page and check for expected page
        RegisterPage registerPage = homepage().userMenu.openRegister();
        registerPage.isExpectedPage();
        return registerPage;
    };
}
