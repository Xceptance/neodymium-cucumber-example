package posters.cucumber.support;

import java.util.Random;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductdetailPage;

public class BrowseRandomVisualAssertSupport
{
    private Random random;

    private String productName;

    @Given("^homepage is open")
    public void openHomePageAndValidate()
    {
        HomePage homepage = OpenPageFlows.homepage();
        homepage.validateAndVisualAssert();
    }

    @When("^I choose random sub category with seed \"([^\"]*)\"$")
    public void openRandomSubCategoryAndValidate(String seed)
    {
        random = new Random(Long.valueOf(seed));
        String categoryName = new HomePage().topNav.getRandomSubcategoryName(random);
        CategoryPage categoryPage = new HomePage().topNav.clickSubcategoryByName(categoryName);
        categoryPage.isExpectedPage();
        categoryPage.validateAndVisualAssert(categoryName);
    }

    @And("^I choose some random product")
    public void openRandomProduct()
    {
        productName = new CategoryPage().getRandomProductDetailName(random);
        new CategoryPage().clickProductByName(productName);
    }

    @Then("^I see correct product")
    public void validateProduct()
    {
        new ProductdetailPage().validateAndVisualAssert(productName);
    }

}
