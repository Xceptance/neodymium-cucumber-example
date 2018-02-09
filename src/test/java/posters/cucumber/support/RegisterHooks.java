/**
 * 
 */
package posters.cucumber.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import posters.cucumber.dataHelper.GlobalStorage;
import posters.dataobjects.User;

/**
 * @author pfotenhauer
 */
public class RegisterHooks
{
    private GlobalStorage storage;

    public RegisterHooks(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @After("@RegisterFromUserMenu")
    public void afterRegisterFromUserMenu(Scenario scenario)
    {
        // use the user coming from dependency injection
        DeleteUserFlow.flow(storage.user);
    }

    @After("@Register")
    public void afterRegister(Scenario scenario)
    {
        Matcher matcher = Pattern.compile("\"([^\"]*)\" and \"([^\"]*)\"").matcher(scenario.getName());
        matcher.find();
        User user = new User("", "", matcher.group(1), matcher.group(2));
        DeleteUserFlow.flow(user);
    }
}
