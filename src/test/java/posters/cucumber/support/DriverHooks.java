package posters.cucumber.support;

import com.xceptance.neodymium.util.WebDriverUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class DriverHooks
{
    @Before("@WebDriverSetUpViaBrowserProfileName")
    public static void setUp(Scenario scenario)
    {
        WebDriverUtils.setUpWithBrowserTag(scenario);
    }

    // have a lower order number than default in order to shut down the driver after
    // the test case specific after hooks
    @After(order = 100)
    public void tearDown(Scenario scenario)
    {
        WebDriverUtils.tearDown(scenario);
    }
}
