package posters.flows;

import static com.codeborne.selenide.Selenide.page;

import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.user.LoginPage;

public class OpenLoginPageFlow
{

    public LoginPage flow()
    {
        HomePage homePage = new OpenHomePageFlow().flow();
        homePage.userMenu().openLogin();
        return page(LoginPage.class);
    };
}
