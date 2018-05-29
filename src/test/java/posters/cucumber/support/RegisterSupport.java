package posters.cucumber.support;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import posters.dataobjects.User;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.user.AccountOverviewPage;
import posters.pageobjects.pages.user.DeleteAccountPage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.PersonalDataPage;
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
        // set up user for the clean up steps
        storage.user = new User(firstName, lastName, eMail, password);
    };

    @Given("^login page is opened after registration$")
    public void registerUserSetup()
    {
        // use the user coming from dependency injection
        registerUser(storage.user);
    }

    @After("@DeleteUserAfterwards")
    @Step("delete user flow")
    public LoginPage deleteUser()
    {
        HomePage homePage = new HomePage();
        // ensure that the user is logged in
        LoginPage loginPage;
        if (!homePage.userMenu.isLoggedIn())
        {
            loginPage = homePage.userMenu.openLogin();
            homePage = loginPage.sendLoginform(storage.user);
        }

        // goto account page
        AccountOverviewPage accountOverviewPage = homePage.userMenu.openAccountOverview();
        accountOverviewPage.validateStructure();

        // goto personal data page
        PersonalDataPage personalDataPage = accountOverviewPage.openPersonalData();
        personalDataPage.validateStructure();

        // goto account deletion page
        DeleteAccountPage deleteAccountPage = personalDataPage.openDeleteAccount();
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

    public static LoginPage registerUser(User user)
    {
        RegisterPage registerPage = OpenPageFlows.registerPage();
        registerPage.isExpectedPage();
        LoginPage loginPage = registerPage.sendRegisterForm(user, user.getPassword());
        loginPage.isExpectedPage();

        return loginPage;
    }

    @When("^I register a new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    @Step("set up user and register him")
    public void registerUser(String firstName, String lastName, String email, String password)
    {
        RegisterPage registerPage = new RegisterPage();
        registerPage.isExpectedPage();
        storage.user = new User(firstName, lastName, email, password);
        registerPage.sendRegisterForm(firstName, lastName, email, password, password);
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
