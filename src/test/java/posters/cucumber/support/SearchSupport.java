package posters.cucumber.support;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.NoHitsPage;

public class SearchSupport
{
    @Then("^I see category page$")
    public void validateCategoryPage()
    {
        new CategoryPage().validateStructure();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void search(String searchTerm)
    {
        new HomePage().search.search(searchTerm);
    }

    @Then("^result page contains searchterm \"([^\"]*)\" and shows \"([^\"]*)\" products$")
    public void validateSearchHits(String searchTerm, int searchTermExpectedCount)
    {
        new CategoryPage().validateSearchHits(searchTerm, searchTermExpectedCount);
    }

    @Then("^product \"([^\"]*)\" is visible$")
    public void validateProductVisible(String productName)
    {
        new CategoryPage().validateProductVisible(productName);
    }

    @Then("^I see no hits page$")
    public void valiadteNoHits()
    {
        new NoHitsPage().validate();
    }
}
