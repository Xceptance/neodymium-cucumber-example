package posters.cucumber.support;

import com.xceptance.neodymium.util.Driver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;

public class DriverHooks
{

    @Given("^\"([^\"]*)\" is open$")
    public static void setUp(final String browserProfileName)
    {
        Driver.setUp(browserProfileName);
    }

    // have a lower order number than default in order to shut down the driver after
    // the test case specific after hooks
    @After(order = 100)
    public void tearDown()
    {
        Driver.tearDown();
    }
}
