package posters.cucumber.support;

import java.util.ArrayList;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import posters.cucumber.dataHelper.GlobalStorage;
import posters.dataobjects.Address;
import posters.dataobjects.CreditCard;
import posters.dataobjects.Product;
import posters.pageobjects.pages.browsing.ProductdetailPage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.pages.checkout.PaymentPage;
import posters.pageobjects.pages.checkout.PlaceOrderPlace;
import posters.pageobjects.pages.checkout.ShippingAddressPage;

public class OrderSupport
{
    private GlobalStorage storage;

    public OrderSupport(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @When("^I add product \"([^\"]*)\" in size \"([^\"]*)\" and style \"([^\"]*)\"$")
    @Step("open product page and add product to the cart")
    public void openProductPageAndAddItoTheCart(String productUrl, String size, String style)
    {
        OpenPageFlows.openProductdetailsPage(productUrl);
        addProductToTheCart(size, style);
    }

    @When("I add this product with size \"([^\"]*)\" and style \"([^\"]*)\" to the cart$")
    public void addProductToTheCart(String size, String style)
    {
        ProductdetailPage productPage = new ProductdetailPage();
        productPage.setSize(size);
        productPage.setStyle(style);
        if (storage.products == null)
        {
            storage.products = new ArrayList<Product>();
        }
        Product product = new Product(productPage.getProductName(), productPage.getProductPrice(), style, size, 1);
        if (storage.products.contains(product))
        {
            Product newProduct = storage.products.get(storage.products.indexOf(product));
            newProduct.setAmount(newProduct.getAmount() + 1);
        }
        else
        {
            storage.products.add(product);
        }
        productPage.addToCart();
    }

    @When("^I specify the shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and use it for billing$")
    public void openFillAndSendShippingFormUseForBilling(String name, String company, String address, String city, String state, String zip, String country)
    {
        CartPage cartPage = new ProductdetailPage().miniCart.openCartPage();
        cartPage.isExpectedPage();
        ShippingAddressPage shippingPage = cartPage.openShippingPage();
        shippingPage.isExpectedPage();
        shippingPage.sendShippingAddressForm(name, company, address, city, state, zip, country, true);
        storage.shippingAddress = new Address(name, company, address, city, state, zip, country);
        storage.billingAddress = new Address(name, company, address, city, state, zip, country);
        new PaymentPage().isExpectedPage();
    }

    @When("^I enter payment data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void fillAndSendPaymentForm(String name, String cardNumber, String month, String year)
    {
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.isExpectedPage();
        PlaceOrderPlace placeOrder = paymentPage.sendPaymentForm(cardNumber, name, month, year);
        storage.creditcard = new CreditCard(name, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
        placeOrder.isExpectedPage();
    }

    @Then("^I see all the products in order overview$")
    public void validateContainsAllProductsWithCorrectPricesAndAmount()
    {
        PlaceOrderPlace placeOrder = new PlaceOrderPlace();
        for (int i = 0; i < storage.products.size(); i++)
        {
            placeOrder.validateContainsProduct(storage.products.get(i));
        }

    }

    @Then("^my shipping and billing addresses as well as payment data are displayed correctly")
    public void validateAddressesAndPaymentData()
    {
        PlaceOrderPlace placeOrder = new PlaceOrderPlace();
        placeOrder.validateAddressesAndPayment(storage.shippingAddress, storage.billingAddress, storage.creditcard);
    }
}
