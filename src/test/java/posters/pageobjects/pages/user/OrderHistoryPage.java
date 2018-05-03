package posters.pageobjects.pages.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import posters.dataobjects.Product;
import posters.pageobjects.pages.browsing.AbstractBrowsingPage;

public class OrderHistoryPage extends AbstractBrowsingPage
{
    private SelenideElement headline = $("#titleOrderHistory");

    @Override
    @Step("ensure this is an order history page")
    public void isExpectedPage()
    {
        headline.should(exist);
    }

    @Step("validate product is in the order")
    public void validateContainsProduct(Product product)
    {
        SelenideElement productContainer = $$(".productInfo").filter(text(product.getName())).first().parent();
        for (int i = 0; i < $$(".productInfo").filter(text(product.getName())).size(); i++)
        {
            SelenideElement productValiant = $$(".productInfo").filter(text(product.getName())).get(i);
            if (productValiant.find(".productStyle").text().equals(product.getStyle()) && productValiant.find(".productSize").text().equals(product.getSize()))
            {
                productContainer = productValiant.parent();
            }
        }
        productContainer.find(".productName").shouldBe(exactText(product.getName()));
        productContainer.find(".productStyle").shouldBe(exactText(product.getStyle()));
        productContainer.find(".productSize").shouldBe(exactText(product.getSize()));
        productContainer.find(".orderCount").shouldBe(exactText(Integer.toString(product.getAmount()) + "x"));

    }

}
