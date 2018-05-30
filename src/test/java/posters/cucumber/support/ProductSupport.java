package posters.cucumber.support;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.ProductdetailPage;

public class ProductSupport
{
    @When("^product \"([^\"]*)\" is opened$")
    public ProductdetailPage clickProductByName(String productName)
    {
        return new CategoryPage().clickProductByName(productName);
    }

    @Then("^a product detail page shows the headline \"([^\"]*)\"$")
    public void validateProductName(String name)
    {
        new ProductdetailPage().validateProductName(name);
    }
}
