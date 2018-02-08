package posters.pageobjects.pages.browsing;

import com.xceptance.neodymium.util.Context;

import cucumber.api.java.en.Then;
import io.qameta.allure.Step;

public class NoHitsPage extends AbstractBrowsingPage
{

    @Step("validate no hits page structure")
    public void validateStructure()
    {
        super.validateStructure();
    }

    @Step("validate that no products are on no hits page")
    public void validateNoProductsFound()
    {
        errorMessage.validateErrorMessage(Context.localizedText("NoHitsPage.validation.noProductsFound"));
    }

    @Then("^I want to be on a no hits page$")
    @Step("validate no hits page")
    public void validate()
    {
        validateStructure();
        validateNoProductsFound();
    }
}
