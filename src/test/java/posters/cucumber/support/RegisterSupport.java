package posters.cucumber.support;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.pages.user.*;
import posters.testdata.dataobjects.User;

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
    }

    @Given("^login page is opened after registration$")
    public void registerUserSetup()
    {
        // use the user coming from dependency injection
        registerUser(storage.user);
    }

    @After("@DeleteUserAfterwards")
    @Step("delete user flow")
    public void deleteUser()
    {
        HomePage homePage = new HomePage().openHomePage();
        // ensure that the user is logged in
        LoginPage loginPage;
        AccountOverviewPage accountOverviewPage;

        if (!homePage.header.userMenu.validateIsLoggedIn())
        {
            loginPage = homePage.header.userMenu.openLoginPage();
            accountOverviewPage = loginPage.sendLoginForm(storage.user);
        }

        // needed since there is a bug in posters
        if (homePage.header.miniCart.getTotalCount() > 0)
        {
            CartPage cartPage = homePage.header.miniCart.openCartPage();
            while (cartPage.hasProductsInCart())
            {
                cartPage.removeProduct(1);
            }
        }

        accountOverviewPage = homePage.header.userMenu.openAccountOverviewPage();

        // goto account page
        accountOverviewPage.validateStructure();

        // goto personal data page
        PersonalDataPage personalDataPage = accountOverviewPage.openPersonalData();
        personalDataPage.validateStructure();

        // goto account deletion page
        DeleteAccountPage deleteAccountPage = personalDataPage.openDeleteAccountPage();
        deleteAccountPage.validateStructure();

        // delete the account
        homePage = deleteAccountPage.deleteAccount(storage.user.getPassword());
        homePage.validateSuccessfulDeletedAccount();

        // verify that the account is not available anymore
        loginPage = homePage.header.userMenu.openLoginPage();
        loginPage.validateStructure();
        loginPage.sendFalseLoginForm(storage.user);
        loginPage.validateFalseLogin(storage.user.getEmail());
    }

    public static LoginPage registerUser(User user)
    {
        RegisterPage registerPage = OpenPageFlows.registerPage();
        registerPage.isExpectedPage();
        LoginPage loginPage = registerPage.sendRegisterForm(user);
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
        new HomePage().header.userMenu.validateNotLoggedIn();
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
        new LoginPage().sendLoginForm(new User(null, null, email, password));
    }

    @Then("^login was successful for \"([^\"]*)\"$")
    public void validateSuccessfulLogin(String firstName)
    {
        new HomePage().header.userMenu.validateLoggedInName(firstName);
    }
}
