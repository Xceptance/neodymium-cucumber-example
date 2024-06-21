package posters.cucumber.support;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import posters.dataobjects.Address;
import posters.dataobjects.CreditCard;
import posters.dataobjects.Product;
import posters.dataobjects.User;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.browsing.ProductDetailPage;
import posters.pageobjects.pages.checkout.ShippingAddressPage;
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
        // the user is saved for later reuse
        storage.user = new User(firstName, lastName, email, password);
        
        var registerPage = OpenPageFlows.registerPage();
        registerPage.sendRegisterForm(storage.user);
        var loginPage = registerPage.userMenu.openLogin();;
        loginPage.sendLoginform(email, password);
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
    
    @When("^I add product \"([^\"]*)\" in size \"([^\"]*)\" and style \"([^\"]*)\"$")
    public void openProductPageAndAddItoTheCart(String productUrl, String size, String style)
    {
        OpenPageFlows.openProductDetailsPage(productUrl);
        addProductToCart(size, style);
    }
    
    @Then("^I specify the shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and use it for billing$")
    public void openFillAndSendShippingFormUseForBilling(String firstName, String lastName, String name, String company, String street, String city, String state, String zip, String country, boolean sameBillingAddress)
    {
        // addresses are saved for later reuse
        storage.shippingAddress = new Address(firstName, lastName, company, street, city, state, zip, country);
        storage.billingAddress = new Address(firstName, lastName, company, street, city, state, zip, country);

        var cartPage = new ProductDetailPage().miniCart.openCartPage();
        ShippingAddressPage shippingPage = cartPage.openShippingPage();
        
        if (sameBillingAddress)
        {
           shippingPage.fillShippingAddressWithSameAsBilling(storage.shippingAddress);
        }
        else 
        {
           var billingPage = shippingPage.fillShippingAddressWithDifferentBilling(storage.shippingAddress);
           billingPage.fillBillingAddress(storage.billingAddress);
        }
    }

    @Then("^I enter payment data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void fillAndSendPaymentForm(String name, String cardNumber, String month, String year)
    {
        // the creditcard is saved for later reuse
        storage.creditcard = new CreditCard(name, cardNumber, "xxxx xxxx xxxx " + cardNumber.substring(12, 16), month, year);
        
        var paymentPage = new PaymentPage().isExpectedPage();
        paymentPage.sendPaymentForm(storage.creditcard);
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
}
