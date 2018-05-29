package posters.cucumber.support;

import java.text.DecimalFormat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import posters.dataobjects.Address;
import posters.dataobjects.CreditCard;
import posters.dataobjects.Product;
import posters.dataobjects.User;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductdetailPage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.pages.checkout.PaymentPage;
import posters.pageobjects.pages.checkout.PlaceOrderPage;
import posters.pageobjects.pages.checkout.ShippingAddressPage;
import posters.pageobjects.pages.user.AccountOverviewPage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.OrderHistoryPage;
import posters.pageobjects.pages.user.RegisterPage;

public class OrderSupport
{
    private GlobalStorage storage;

    public OrderSupport(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @Given("^new user with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is registered and logged in$")
    public void registerAndLogIn(String firstName, String lastName, String email, String password)
    {
        RegisterPage registerPage = OpenPageFlows.registerPage();
        registerPage.isExpectedPage();
        storage.user = new User(firstName, lastName, email, password);
        registerPage.sendRegisterForm(firstName, lastName, email, password, password);
        LoginPage loginPage = registerPage.userMenu.openLogin();
        loginPage.isExpectedPage();
        loginPage.sendLoginform(email, password);
    }

    @Then("^all the products are to find in order history$")
    public void validateOrderInOrderHistory()
    {
        AccountOverviewPage accountOverview = new HomePage().userMenu.openAccountOverview();
        accountOverview.isExpectedPage();
        OrderHistoryPage orderHistory = accountOverview.openOrderHistory();
        for (Product product : storage.products)
        {
            orderHistory.validateContainsProduct(product);
        }
    }

    @When("^I add product \"([^\"]*)\" in size \"([^\"]*)\" and style \"([^\"]*)\"$")
    @Step("open product page and add product to the cart")
    public void openProductPageAndAddItoTheCart(String productUrl, String size, String style)
    {
        OpenPageFlows.openProductdetailsPage(productUrl);
        addProductToCart(size, style);
    }

    @When("I add this product with size \"([^\"]*)\" and style \"([^\"]*)\" to the cart$")
    public void addProductToCart(String size, String style)
    {
        ProductdetailPage productPage = new ProductdetailPage();
        productPage.setSize(size);
        productPage.setStyle(style);

        Product product = storage.addProduct(productPage.getProduct());

        productPage.addToCart();
        productPage.miniCart.openMiniCart();
        productPage.miniCart.validateMiniCartByProduct(product);
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
        PlaceOrderPage placeOrder = paymentPage.sendPaymentForm(cardNumber, name, month, year);
        storage.creditcard = new CreditCard(name, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
        placeOrder.isExpectedPage();
    }

    @Then("^I see all the products in order overview$")
    public void validateContainsAllProductsWithCorrectPricesAndAmount()
    {
        double subtotal = 0.0;
        DecimalFormat format = new DecimalFormat("##0.00");
        PlaceOrderPage placeOrder = new PlaceOrderPage();
        for (Product product : storage.products)
        {
            placeOrder.validateContainsProduct(product);
            subtotal += product.getUnitPriceDouble() * product.getAmount();
        }
        placeOrder.validateSubtotal("$" + format.format(subtotal));

    }

    @Then("^my shipping and billing addresses as well as payment data are displayed correctly")
    public void validateAddressesAndPaymentData()
    {
        PlaceOrderPage placeOrder = new PlaceOrderPage();
        placeOrder.validateAddressesAndPayment(storage.shippingAddress, storage.billingAddress, storage.creditcard);
    }

    @Then("^my order is successfully placed$")
    public void placeOrder()
    {
        HomePage succssefulOrder = new PlaceOrderPage().placeOrder();
        succssefulOrder.isExpectedPage();
        succssefulOrder.validateSuccessfulOrder();
    }
}
