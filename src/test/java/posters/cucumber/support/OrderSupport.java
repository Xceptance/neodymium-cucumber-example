package posters.cucumber.support;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.checkout.CartPage;
import posters.pageobjects.pages.checkout.GuestBillingAddressPage;
import posters.pageobjects.pages.checkout.GuestPaymentPage;
import posters.pageobjects.pages.checkout.GuestShippingAddressPage;
import posters.pageobjects.pages.checkout.OrderConfirmationPage;
import posters.pageobjects.pages.checkout.PlaceOrderPage;
import posters.pageobjects.pages.checkout.ReturningCustomerBillingAddressPage;
import posters.pageobjects.pages.checkout.ReturningCustomerPaymentPage;
import posters.pageobjects.pages.checkout.ReturningCustomerShippingAddressPage;
import posters.pageobjects.pages.user.AccountOverviewPage;
import posters.pageobjects.pages.user.LoginPage;
import posters.pageobjects.pages.user.OrderHistoryPage;
import posters.pageobjects.pages.user.RegisterPage;
import posters.pageobjects.utility.PriceHelper;
import posters.testdata.dataobjects.Address;
import posters.testdata.dataobjects.CreditCard;
import posters.testdata.dataobjects.Product;
import posters.testdata.dataobjects.User;

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
        registerPage.sendRegisterForm(storage.user);
        LoginPage loginPage = registerPage.header.userMenu.openLoginPage();
        loginPage.isExpectedPage();
        loginPage.sendLoginForm(storage.user);
    }

    @Then("^all the products are to find in order history$")
    public void validateOrderInOrderHistory()
    {
        AccountOverviewPage accountOverview = new HomePage().header.userMenu.openAccountOverviewPage();
        accountOverview.isExpectedPage();
        OrderHistoryPage orderHistory = accountOverview.openOrderHistory();
        orderHistory.validateOrder(1, storage.orderTotal, storage.products);
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
        ProductDetailPage productPage = new ProductDetailPage();
        productPage.setSize(size);
        productPage.setStyle(style);

        productPage.addToCart(size, style);
        Product product = storage.addProduct(productPage.getProduct());
        productPage.header.miniCart.openMiniCart();
        productPage.header.miniCart.validateMiniCartItem(product);
    }

    @When("^I as guest user specify the shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterShippingAddressAsGuestUser(String firstName, String lastName, String company, String address, String city,
                                                String state, String zip,
                                                String country)
    {
        CartPage cartPage = new ProductDetailPage().header.miniCart.openCartPage();
        cartPage.isExpectedPage();
        GuestShippingAddressPage shippingPage = cartPage.openGuestShippingAddressPage();
        shippingPage.isExpectedPage();
        storage.shippingAddress = new Address(firstName, lastName, company, address, city, state, zip, country);
        shippingPage.addressForm.goToGuestBillingAddressPage(storage.shippingAddress).isExpectedPage();
    }

    @When("^I as guest user specify the billing address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void enterBillingAddressAsGuestUser(String firstName, String lastName, String company, String address, String city,
                                               String state, String zip,
                                               String country)
    {
        GuestBillingAddressPage shippingPage = new GuestBillingAddressPage();
        shippingPage.isExpectedPage();
        storage.billingAddress = new Address(firstName, lastName, company, address, city, state, zip, country);
        shippingPage.addressForm.goToGuestPaymentPage(storage.billingAddress).isExpectedPage();
    }

    @When("^I as registered user select the shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and use it for billing$")
    public void selectShippingForRegisteredUser(String firstName, String lastName, String company, String address, String city, String state, String zip,
                                                String country)
    {
        CartPage cartPage = new ProductDetailPage().header.miniCart.openCartPage();
        cartPage.isExpectedPage();
        ReturningCustomerShippingAddressPage shippingPage = cartPage.openReturningCustomerShippingAddressPage();
        shippingPage.isExpectedPage();
        storage.shippingAddress = new Address(firstName, lastName, company, address, city, state, zip, country);
        storage.billingAddress = new Address(firstName, lastName, company, address, city, state, zip, country);
        shippingPage.selectShippingAddress(1).isExpectedPage();
    }

    @When("^I as registered user select the billing address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and use it for billing$")
    public void selectBillingForRegisteredUser(String firstName, String lastName, String company, String address, String city, String state, String zip,
                                               String country)
    {
        ReturningCustomerBillingAddressPage shippingPage = new ReturningCustomerBillingAddressPage();
        shippingPage.isExpectedPage();
        storage.billingAddress = new Address(firstName, lastName, company, address, city, state, zip, country);
        shippingPage.selectBillingAddress(1).isExpectedPage();
    }

    @When("^I as guest user enter payment data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void fillAndSendPaymentFormAsGuestUser(String firstName, String lastName, String cardNumber, String month, String year)
    {
        GuestPaymentPage paymentPage = new GuestPaymentPage();
        paymentPage.isExpectedPage();
        storage.creditcard = new CreditCard(firstName + " " + lastName, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
        PlaceOrderPage placeOrder = paymentPage.goToPlaceOrderPage(storage.creditcard);
        placeOrder.isExpectedPage();
    }

    @When("^I as registered user select payment \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void fillAndSendPaymentFormAsRegisteredUser(String firstName, String lastName, String cardNumber, String month, String year)
    {
        ReturningCustomerPaymentPage paymentPage = new ReturningCustomerPaymentPage();
        paymentPage.isExpectedPage();
        storage.creditcard = new CreditCard(firstName + " " + lastName, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
        paymentPage.selectCreditCard(1).isExpectedPage();
    }

    @Then("^I see all the products in order overview$")
    public void validateContainsAllProductsWithCorrectPricesAndAmount()
    {
        double subtotal = 0.0;
        PlaceOrderPage placeOrder = new PlaceOrderPage();
        for (Product product : storage.products)
        {
            placeOrder.validateProduct(product);
            subtotal += product.getTotalPrice();
        }
        placeOrder.validateSubtotal(PriceHelper.format(subtotal));
    }

    @Then("^my shipping and billing addresses as well as payment data are displayed correctly")
    public void validateAddressesAndPaymentData()
    {
        PlaceOrderPage placeOrder = new PlaceOrderPage();
        placeOrder.validateOrderOverview(storage.shippingAddress, storage.billingAddress, storage.creditcard);
    }

    @Then("^my order is successfully placed$")
    public void placeOrder()
    {
        PlaceOrderPage placeOrderPage = new PlaceOrderPage();
        storage.orderTotal = placeOrderPage.getTotalOrderPrice();
        OrderConfirmationPage succssefulOrder = placeOrderPage.placeOrder();
        succssefulOrder.isExpectedPage();
        succssefulOrder.validateSuccessfulOrder();
    }
}
