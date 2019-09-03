package posters.cucumber.support;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.HomePage;

public class BrowseSupport
{
    @Given("^a main category \"([^\"]*)\" is hovered and a sub category \"([^\"]*)\" is opened$")
    @When("^I choose main category \"([^\"]*)\" and sub category \"([^\"]*)\"$")
    public void openSubcCategory(String categoryName, String subCategoryName)
    {
        new HomePage().topNav.clickSubCategoryByNames(categoryName, subCategoryName);
    }

    @Then("^I see category page with \"([^\"]*)\" headline$")
    public void validateCategoryName(String categoryName)
    {
        new CategoryPage().validateCategoryName(categoryName);
    }
}
