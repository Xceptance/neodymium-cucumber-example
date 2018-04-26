package posters.cucumber.support;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import posters.cucumber.dataHelper.GlobalStorage;
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

    @When("^I register a new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    @Step("set up user and register him")
    public void registerUser(String firstName, String lastName, String email, String password)
    {
        RegisterPage registerPage = new RegisterPage();
        registerPage.isExpectedPage();
        storage.user = new User(firstName, lastName, email, password);
        registerPage.sendRegisterForm(firstName, lastName, email, password, password);
    }

    @Given("^new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is registered")
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
