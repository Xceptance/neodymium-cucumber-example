package posters.cucumber.support;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.ProductDetailPage;

public class ProductSupport
{
    @When("^product \"([^\"]*)\" is opened$")
    public ProductDetailPage clickProductByName(String productName)
    {
        return new CategoryPage().clickProductByName(productName);
    }

    @Then("^a product detail page shows the headline \"([^\"]*)\"$")
    public void validateProductName(String name)
    {
        new ProductDetailPage().validateProductName(name);
    }
}
