package posters.cucumber.support;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.pageobjects.pages.browsing.CategoryPage;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;

public class BrowseRandomVisualAssertSupport
{
    private Random random;

    private String productName;

    @Given("^homepage is open")
    public void openHomePageAndValidate()
    {
        var homepage = OpenPageFlows.homepage();
        homepage.validateAndVisualAssert();
    }

    @When("^I choose random sub category with seed \"([^\"]*)\"$")
    public void openRandomSubCategoryAndValidate(String seed)
    {
        if (StringUtils.isEmpty(seed))
        {
            random = new Random();
        }
        else
        {
            random = new Random(Long.valueOf(seed));
        }
        String categoryName = new HomePage().topNav.getRandomSubcategoryName(random);
        var categoryPage = new HomePage().topNav.clickSubcategoryByName(categoryName);
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
        new ProductDetailPage().validateAndVisualAssert(productName);
    }

}
