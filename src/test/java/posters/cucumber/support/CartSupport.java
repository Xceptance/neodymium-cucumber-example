package posters.cucumber.support;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.dataobjects.Product;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.utility.PriceHelper;

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
        new ProductDetailPage().miniCart.openCartPage();
    }

    @Then("^I see all the added products in the cart and their properties are correct$")
    public void validateProductsInTheCart()
    {
        double subtotal = 0.0;
        var cartPage = new CartPage();

        for (Product product : storage.products)
        {
            cartPage.validateContainsProduct(product);
            subtotal += product.getTotalPrice();
        }
        cartPage.validateSubtotal(PriceHelper.format(subtotal));
    }

    @Then("^I can change amount of the \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" to (\\d+)$")
    public void updateCountOfProduct(String name, String size, String style, int amount)
    {
        var cartPage = new CartPage();
        cartPage.updateProductCountByName(name, style, size, amount);
        
        var updateProduct = storage.getProductFromArrayList(name, size, style);
        storage.products.remove(updateProduct);
        String unitPrice = updateProduct.getUnitPrice();
        updateProduct = new Product(name, unitPrice, style, size, amount);
        storage.products.add(updateProduct);
    }

    @Then("^I can remove \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void removeProduct(String productName, String size, String style)
    {
        var cartPage = new CartPage();
        cartPage.removeProductByName(productName, style, size);

        storage.removeProduct(productName, style, size);

        validateProductsInTheCart();
    }
}
