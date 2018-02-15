/**
 * 
 */
package posters.cucumber.support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import posters.cucumber.dataHelper.GlobalStorage;

/**
 * @author pfotenhauer
 */
public class UserHooks
{
    private GlobalStorage storage;

    public UserHooks(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @After("@Register")
    public void afterRegisterFromUserMenu(Scenario scenario)
    {
        // use the user coming from dependency injection
        UserFlows.deletUser(storage.user);
    }

    @Given("^login page is opened after registration$")
    public void registerUserSetup()
    {
        // use the user coming from dependency injection
        UserFlows.registerUser(storage.user);
    }
}
