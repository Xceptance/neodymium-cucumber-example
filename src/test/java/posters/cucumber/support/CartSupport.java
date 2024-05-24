package posters.cucumber.support;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.utility.PriceHelper;
import posters.testdata.dataobjects.Product;

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
        CartPage cartPage = new ProductDetailPage().header.miniCart.openCartPage();
        cartPage.isExpectedPage();
    }

    @Then("^I see all the added products in the cart and their properties are correct$")
    public void validateProductsInTheCart()
    {
        double subtotal = 0.0;
        CartPage cartPage = new CartPage();
        for (Product product : storage.products)
        {
            cartPage.validateCartItem(product);
            subtotal += product.getTotalPrice();
        }
        cartPage.validateSubtotal(PriceHelper.format(subtotal));
    }

    @Then("^I can change amount of the product \"([^\"]*)\" number (\\d+) with \"([^\"]*)\" and \"([^\"]*)\" to (\\d+)$")
    public void updateCountOfProduct(String productName, String style, String size, int amount)
    {
        CartPage cartPage = new CartPage();
        cartPage.updateProductCount(productName, productName, productName, amount);
        for (Product product : storage.products)
        {
            if (product.getName().equals(productName) && product.getSize().equals(size)
                && product.getStyle().equals(style))
            {
                product.setAmount(amount);
            }
            cartPage.validateCartItem(product);
        }
    }

    @Then("^I can remove product number (\\d+) \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void removeProduct(int productNumber, String productName, String size, String style)
    {
        CartPage cartPage = new CartPage();
        cartPage.removeProduct(productNumber);

        storage.removeProduct(productName, style, size);

        validateProductsInTheCart();
    }
}
