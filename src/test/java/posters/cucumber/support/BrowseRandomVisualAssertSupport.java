package posters.cucumber.support;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.xceptance.neodymium.util.Neodymium;

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
        HomePage homepage = OpenPageFlows.homepage();
        homepage.validateStructure();
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
        String categoryName = new HomePage().header.topNav.getRandomCategoryName();
        String subCategoryName = new HomePage().header.topNav.getRandomSubCategoryName(categoryName);
        CategoryPage categoryPage = new HomePage().header.topNav.clickSubCategory(categoryName, subCategoryName);
        categoryPage.isExpectedPage();
        categoryPage.validateCategoryName(subCategoryName);
    }

    @And("^I choose some random product")
    public void openRandomProduct()
    {
        int position = Neodymium.getRandom().nextInt(1, new CategoryPage().getNumberOfProductsOnPage());
        productName = new CategoryPage().getProductNameByPosition(position);
        new CategoryPage().clickProductByPosition(position);
    }

    @Then("^I see correct product")
    public void validateProduct()
    {
        new ProductDetailPage().validateProductName(productName);
    }

}
