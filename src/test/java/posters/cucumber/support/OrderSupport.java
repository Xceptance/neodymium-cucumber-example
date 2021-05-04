package posters.cucumber.support;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import posters.dataobjects.Address;
import posters.dataobjects.CreditCard;
import posters.dataobjects.Product;
import posters.dataobjects.User;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.checkout.PaymentPage;
import posters.pageobjects.pages.checkout.PlaceOrderPage;
import posters.pageobjects.utility.PriceHelper;

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
        var registerPage = OpenPageFlows.registerPage();
        registerPage.isExpectedPage();
        storage.user = new User(firstName, lastName, email, password);
        registerPage.sendRegisterForm(firstName, lastName, email, password, password);
        var loginPage = registerPage.userMenu.openLogin();;
        loginPage.sendLoginform(email, password);
    }

    @Then("^all the products are to find in order history$")
    public void validateOrderInOrderHistory()
    {
        var accountOverviewPage = new HomePage().userMenu.openAccountOverview();
        var orderHistoryPage = accountOverviewPage.openOrderHistory();
        for (Product product : storage.products)
        {
            orderHistoryPage.validateContainsProduct(product);
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
        var productDetailPage = new ProductDetailPage();
        productDetailPage.setSize(size);
        productDetailPage.setStyle(style);

        var product = storage.addProduct(productDetailPage.getProduct());

        productDetailPage.addToCart();
        productDetailPage.miniCart.openMiniCart();
        productDetailPage.miniCart.validateMiniCartByProduct(product);
    }

    @When("^I specify the shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and use it for billing$")
    public void openFillAndSendShippingFormUseForBilling(String name, String company, String street, String city, String state, String zip, String country)
    {
        var cartPage = new ProductDetailPage().miniCart.openCartPage();
        var shippingAddressPage = cartPage.openShippingPage();
        shippingAddressPage.sendShippingAddressForm(name, company, street, city, state, zip, country, true);
        
        storage.shippingAddress = new Address(storage.user.getFirstName(), storage.user.getLastName(), company, street, city, state, zip, country);
        storage.billingAddress = new Address(storage.user.getFirstName(), storage.user.getLastName(), company, street, city, state, zip, country);
        new PaymentPage().isExpectedPage();
    }

    @When("^I enter payment data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void fillAndSendPaymentForm(String name, String cardNumber, String month, String year)
    {
        var paymentPage = new PaymentPage();
        paymentPage.isExpectedPage();
        paymentPage.sendPaymentForm(cardNumber, name, month, year);
        
        storage.creditcard = new CreditCard(name, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
    }

    @Then("^I see all the products in order overview$")
    public void validateContainsAllProductsWithCorrectPricesAndAmount()
    {
        double subtotal = 0.0;
        var placeOrderPage = new PlaceOrderPage();
        for (Product product : storage.products)
        {
            placeOrderPage.validateContainsProduct(product);
            subtotal += product.getTotalPrice();
        }
        placeOrderPage.validateSubtotal(PriceHelper.format(subtotal));
    }

    @Then("^my shipping and billing addresses as well as payment data are displayed correctly")
    public void validateAddressesAndPaymentData()
    {
        var placeOrderPage = new PlaceOrderPage();
        placeOrderPage.validateAddressesAndPayment(storage.shippingAddress, storage.billingAddress, storage.creditcard);
    }

    @Then("^my order is successfully placed$")
    public void placeOrder()
    {
        HomePage succssefulOrder = new PlaceOrderPage().placeOrder();
        succssefulOrder.validateSuccessfulOrder();
    }
}
