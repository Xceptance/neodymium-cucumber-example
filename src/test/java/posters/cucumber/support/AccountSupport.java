package posters.cucumber.support;

import io.cucumber.java.en.And;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.user.PaymentOverviewPage;
import posters.testdata.dataobjects.Address;
import posters.testdata.dataobjects.CreditCard;

public class AccountSupport
{
    private GlobalStorage storage;

    public AccountSupport(GlobalStorage storage)
    {
        // The storage is passed via dependency injection
        this.storage = storage;
    }

    @And("new shipping address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is added to account$")
    public void addShippingAddressToAccount(String firstname, String lastname, String company, String address, String city, String state, String zip,
                                            String country)
    {
        var addressOverviewPage = new HomePage().header.userMenu.openAccountOverviewPage().openMyAddresses();

        // add new addresses
        var addNewShippingAddressPage = addressOverviewPage.openAddNewShippingAddressPage();
        storage.shippingAddress = new Address(firstname, lastname, company, address, city, state, zip, country);
        addressOverviewPage = addNewShippingAddressPage.addressForm.addNewAddress(storage.shippingAddress);
        addressOverviewPage.validateSuccessfulSave();
    }

    @And("new billing address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is added to account$")
    public void addBillingAddressToAccount(String firstname, String lastname, String company, String address, String city, String state, String zip,
                                           String country)
    {
        var addressOverviewPage = new HomePage().header.userMenu.openAccountOverviewPage().openMyAddresses();

        // add new addresses
        var addNewBillingAddressPage = addressOverviewPage.openAddNewBillingAddressPage();
        storage.billingAddress = new Address(firstname, lastname, company, address, city, state, zip, country);
        addressOverviewPage = addNewBillingAddressPage.addressForm.addNewAddress(storage.billingAddress);
        addressOverviewPage.validateSuccessfulSave();
    }

    @And("new credit card \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" is added to account$")
    public void addCreditCardToAccount(String firstname, String lastname, String cardNumber, String month, String year)
    {
        var addNewCreditCardPage = new HomePage().header.userMenu.openAccountOverviewPage().openPaymentSettings().openAddNewCreditCardPage();
        storage.creditcard = new CreditCard(firstname + " " + lastname, cardNumber, cardNumber, month, year);
        PaymentOverviewPage paymentOverviewPage = addNewCreditCardPage.addNewCreditCard(storage.creditcard);
        paymentOverviewPage.validateSuccessfulSave();
    }
}
