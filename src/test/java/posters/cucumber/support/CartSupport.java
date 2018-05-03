package posters.cucumber.support;

import java.text.DecimalFormat;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import posters.cucumber.dataHelper.GlobalStorage;
import posters.pageobjects.pages.browsing.ProductdetailPage;
import posters.pageobjects.pages.checkout.CartPage;

public class CartSupport
{
    private GlobalStorage storage;

    public CartSupport(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @When("^I open the cart$")
    public void openCart()
    {
        CartPage cartPage = new ProductdetailPage().miniCart.openCartPage();
        cartPage.isExpectedPage();
    }

    @Then("^I see all the added products in the cart and their properties are correct$")
    public void validateProductsInTheCart()
    {
        double subtotal = 0.0;
        DecimalFormat format = new DecimalFormat("##0.00");
        CartPage cartPage = new CartPage();
        for (int i = 0; i < storage.products.size(); i++)
        {
            cartPage.validateContainsProduct(storage.products.get(i));
            subtotal += storage.products.get(i).getUnitPriceDouble() * storage.products.get(i).getAmount();
        }
        cartPage.validateSubtotal("$" + format.format(subtotal));

    }

    @Then("^I can change amount of the \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" to (\\d+)$")
    public void updateCountOfProduct(String productName, String size, String style, int amount)
    {
        CartPage cartPage = new CartPage();
        cartPage.updateProductCountByName(productName, style, size, amount);
        for (int i = 0; i < storage.products.size(); i++)
        {
            if (storage.products.get(i).getName().equals(productName) && storage.products.get(i).getSize().equals(size)
                && storage.products.get(i).getStyle().equals(style))
            {
                storage.products.get(i).setAmount(amount);
            }
            cartPage.validateContainsProduct(storage.products.get(i));
        }
    }

    @Then("^I can remove \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void removeProduct(String productName, String size, String style)
    {
        CartPage cartPage = new CartPage();
        cartPage.removeProductByName(productName, style, size);
        for (int i = 0; i < storage.products.size(); i++)
        {
            if (storage.products.get(i).getName().equals(productName) && storage.products.get(i).getSize().equals(size)
                && storage.products.get(i).getStyle().equals(style))
            {
                storage.products.remove(i);
            }
        }
        validateProductsInTheCart();
    }

}
