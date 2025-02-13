package posters.pageobjects.pages.browsing;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.Step;
import posters.pageobjects.components.Pagination;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage extends AbstractBrowsingPage
{
    public Pagination pagination = new Pagination();

    private SelenideElement productOverview = $("#product-overview");

    private SelenideElement titleCategoryName = $("#title-category-name");

    private SelenideElement titleSearchText = $("#title-search-text");

    @Override
    @Step("ensure this is a category page")
    public CategoryPage isExpectedPage()
    {
        super.isExpectedPage();
        productOverview.should(exist);
        return this;
    }

    /// ========== validate content category page ========== ///

    @Override
    @Step("validate category page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // validate poster count in headline is not 0
        $("#total-product-count").shouldNotBe(exactText("0")).shouldBe(visible);

        // validate at least 1 poster is displayed
        $("#product-0").shouldBe(visible);
    }

    /**
     * If {categoryName} contains a ".", it's a localization string, localized by Neodymium. Else a search term was used. Both cases have a different headline
     * to validate.
     *
     * @param categoryName
     *     name of specific category of top navigation
     * @param expectedResultCount
     *     number of results for specific category/search
     */
    @Step("validate category name '{categoryName}' and amount results '{expectedResultCount}' on category page")
    public void validateCategoryHeadline(String categoryName, int expectedResultCount)
    {
        if (titleSearchText.exists())
        {
            // if {categoryName} is search input
            titleSearchText.should(matchText(Neodymium.localizedText("categoryPage.searchResultText"))).shouldBe(visible);
            $("#search-text-value").shouldHave(exactText(categoryName)).shouldBe(visible);
            $("#total-product-count").shouldHave(exactText(Integer.toString(expectedResultCount))).shouldBe(visible);
        }
        else
        {
            // if {categoryName} contains Neodymium localization
            validateCategoryName(categoryName);
            titleCategoryName.should(matchText(Integer.toString(expectedResultCount))).shouldBe(visible);
        }
    }

    @Step("validate category name")
    public void validateCategoryName(String categoryName)
    {
        titleCategoryName.should(matchText(categoryName)).shouldBe(visible);
    }

    @Step("validate category page of category '{categoryName}'")
    public void validate(String categoryName, int expectedResultCount)
    {
        validateStructure();
        validateCategoryHeadline(categoryName, expectedResultCount);
    }

    @Step("validate category page contains product '{productName}'")
    public void validateProductVisible(String productName)
    {
        $$(".card-title").findBy(exactText(productName)).shouldBe(visible);
    }

    /// ========== product by position ========== ///

    @Step("get number of products on page")
    public int getNumberOfProductsOnPage()
    {
        return $$("card-title").size();
    }

    @Step("get a product name by position '{position}'")
    public String getProductNameByPosition(int position)
    {
        return $("#product-" + (position - 1) + " h5").text();
    }

    @Step("click on a product by position '{position}'")
    public ProductDetailPage clickProductByPosition(int position)
    {
        $("#product-" + (position - 1) + " .btn.btn-primary").click(ClickOptions.usingJavaScript());
        return new ProductDetailPage().isExpectedPage();
    }

    @Step("click on a product by name '{productName}'")
    public ProductDetailPage clickProductByName(String productName)
    {
        $$(".card-title").findBy(exactText(productName)).closest(".product-tile").find("img").scrollTo().click();
        return new ProductDetailPage().isExpectedPage();
    }

    /// ========== category page navigation ========== ///

    @Step("open homepage from category page")
    public HomePage openHomePage()
    {
        $("#header-brand").click(ClickOptions.usingJavaScript());
        return new HomePage().isExpectedPage();
    }
}
