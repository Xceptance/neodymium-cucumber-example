package posters.pageobjects.pages.user;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.AbstractBrowsingPage;
import posters.pageobjects.pages.browsing.HomePage;
import posters.testdata.dataobjects.Product;

public class OrderHistoryPage extends AbstractBrowsingPage
{
    private SelenideElement title = $("#title-order-history");

    private SelenideElement goBackButton = $(".btn-sm");

    @Override
    @Step("ensure this is an order history page")
    public OrderHistoryPage isExpectedPage()
    {
        super.isExpectedPage();
        title.should(exist);
        return this;
    }

    /// ========== validate content order history page ========== ///

    @Override
    @Step("validate personal data page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // validate title
        title.shouldHave(exactText(Neodymium.localizedText("orderHistoryPage.title"))).shouldBe(visible);

        // validate table Head
        $("#order-content-col").shouldHave(exactText(Neodymium.localizedText("orderHistoryPage.purchasedPosters"))).shouldBe(visible);
        $("#order-details-col").shouldHave(exactText(Neodymium.localizedText("orderHistoryPage.orderDetails"))).shouldBe(visible);
        $("#order-quantity-col").shouldHave(exactText(Neodymium.localizedText("product.quantity"))).shouldBe(visible);

        // validate button
        goBackButton.shouldHave(exactText(Neodymium.localizedText("button.back"))).shouldBe(visible);
    }

    @Step("validate order")
    public void validateOrder(int numberOfOrder, String totalOrderPrice, List<Product> products)
    {
        ElementsCollection productItems = $$(".order-product");
        productItems.first().find("#order-detail-date").shouldHave(exactText(Neodymium.localizedText("orderHistoryPage.orderDate"))).shouldBe(visible);
        productItems.first().find("#order-detail-date-value").shouldHave(exactText(LocalDate.now().toString())).shouldBe(visible);
        productItems.first().find("#order-detail-total").shouldHave(exactText(Neodymium.localizedText("orderHistoryPage.orderTotal"))).shouldBe(visible);
        productItems.first().find("#order-detail-total-value").shouldHave(exactText(totalOrderPrice)).shouldBe(visible);

        for (Product product : products)
        {
            SelenideElement productItem = productItems.shouldHave(sizeGreaterThan(0))
                                                      .findBy(new WebElementCondition("has name " + product.getName() + ", style " + product.getStyle()
                                                                                      + " and size " + product.getSize())
                                                      {
                                                          @Override
                                                          public CheckResult check(Driver driver, WebElement element)
                                                          {
                                                              boolean matchesName = element.findElement(By.cssSelector(".product-name")).getText()
                                                                                           .equals(product.getName());
                                                              boolean matchesStyle = element.findElement(By.cssSelector(".product-style span"))
                                                                                            .getText()
                                                                                            .equals(product.getStyle());
                                                              boolean matchesSize = element.findElement(By.cssSelector(".product-size span")).getText()
                                                                                           .equals(product.getSize());
                                                              return new CheckResult(matchesName && matchesStyle && matchesSize, "");
                                                          }
                                                      }).shouldBe(visible, Duration.ofMillis(9000));
            productItem.find(".img-thumbnail").shouldBe(visible);
            productItem.find(".product-name").shouldHave(exactText(product.getName()));
            productItem.find(".product-style").should(matchText(product.getStyle()));
            productItem.find(".product-size").should(matchText(product.getSize()));
            productItem.find(".prod-quantity").shouldHave(exactText(Integer.toString(product.getAmount()) + "x"));
        }
    }

    /// ========== order history page navigation ========== ///

    @Step("open homepage")
    public HomePage openHomePage()
    {
        $("#header-brand").click(ClickOptions.usingJavaScript());
        return new HomePage().isExpectedPage();
    }

    @Step("open account overview page")
    public AccountOverviewPage openAccountOverviewPage()
    {
        goBackButton.click(ClickOptions.usingJavaScript());
        return new AccountOverviewPage().isExpectedPage();
    }
}