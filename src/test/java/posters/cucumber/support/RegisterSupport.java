package posters.cucumber.support;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.dataobjects.User;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.RegisterPage;

public class RegisterSupport
{
    private GlobalStorage storage;

    public RegisterSupport(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @Given("^user setup: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void setUpUser(String firstName, String lastName, String eMail, String password)
    {
        // create a user for later use (e.g. register or delete)
        storage.user = new User(firstName, lastName, eMail, password);
    };

    @Given("^login page is opened after registration$")
    public LoginPage registerUserSetup()
    {
        // use the user coming from dependency injection
//        registerUser(storage.user);
        var registerPage = OpenPageFlows.registerPage();
        registerPage.isExpectedPage();
        var loginPage = registerPage.sendRegisterForm(storage.user);
        loginPage.isExpectedPage();

        return loginPage;
    }

    @After("@DeleteUserAfterwards")
    public LoginPage deleteUser()
    {
        var homePage = new HomePage();
        // ensure that the user is logged in
        var loginPage = new LoginPage();
        if (!homePage.userMenu.isLoggedIn())
        {
            loginPage = homePage.userMenu.openLogin();
            homePage = loginPage.sendLoginform(storage.user);
        }

        // needed since there is a bug in posters
        if (homePage.miniCart.getTotalCount() > 0)
        {
            var cartPage = homePage.miniCart.openCartPage();
            while (cartPage.hasProductsInCart())
            {
                cartPage.removeProduct(1);
            }
        }

        // goto account page
        var accountOverviewPage = homePage.userMenu.openAccountOverview();
        accountOverviewPage.validateStructure();

        // goto personal data page
        var personalDataPage = accountOverviewPage.openPersonalData();
        personalDataPage.validateStructure();

        // goto account deletion page
        var deleteAccountPage = personalDataPage.openDeleteAccount();
        deleteAccountPage.validateStructure();

        // delete the account
        homePage = deleteAccountPage.deleteAccount(storage.user.getPassword());
        homePage.validateSuccessfulDeletedAccount();

        // verify that the account is not available anymore
        loginPage = homePage.userMenu.openLogin();
        loginPage.validateStructure();
        loginPage.sendFalseLoginform(storage.user);
        loginPage.validateWrongEmail(storage.user.getEmail());

        return loginPage;
    }

    @When("^I register a new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void registerUser(String firstName, String lastName, String email, String password)
    {
        var registerPage = new RegisterPage();
        registerPage.isExpectedPage();
        storage.user = new User(firstName, lastName, email, password);
        registerPage.sendRegisterForm(storage.user);
    }

    @Given("^new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is registered$")
    public void openRegisterPageAndRegisterUser(String firstName, String lastName, String email, String password)
    {
        OpenPageFlows.registerPage();
        registerUser(firstName, lastName, email, password);
    }

    @Given("^I'm not logged in$")
    public void validateNotLoggedIn()
    {
        new HomePage().userMenu.validateNotLoggedIn();
    }

    @Then("^register was successful$")
    public void validateSuccessfulRegistration()
    {
        new LoginPage().validateSuccessfulRegistration();
    }

    @Then("^login page is opened$")
    public void validateLoginPage()
    {
        new LoginPage().validateStructure();
    }

    @When("^I log in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void sendLoginform(String email, String password)
    {
        new LoginPage().sendLoginform(email, password);
    }

    @Then("^login was successful for \"([^\"]*)\"$")
    public void validateSuccessfulLogin(String firstName)
    {
        new HomePage().validateSuccessfulLogin(firstName);
    }
}
