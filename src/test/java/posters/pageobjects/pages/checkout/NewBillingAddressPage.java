package posters.pageobjects.pages.checkout;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.dataobjects.Address;
import posters.pageobjects.components.AddressForm;

/**
 * @author pfotenhauer
 */
public class NewBillingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleBillAddr");

    private AddressForm newBillingAddressForm = new AddressForm(Neodymium.localizedText("CheckoutPages.newBillingAddressFormTitle"));

    @Override
    @Step("ensure this is a new billing address page")
    public NewBillingAddressPage isExpectedPage()
    {
        super.isExpectedPage();
        headline.should(exist);
        newBillingAddressForm.isComponentAvailable();
        return this;
    }

    @Override
    @Step("validate new billing address page structure")
    public void validateStructure()
    {
        super.validateStructure();
        newBillingAddressForm.validateStructure();
    }

    /**
     * //
     * 
     * @param address
     *            The address you want to use
     */
    @Step("fill and send new billing address form")
    public NewPaymentPage sendBillingAddressForm(Address address)
    {
        newBillingAddressForm.fillAddressForm(address);
        newBillingAddressForm.confirmAddressForm();
        return new NewPaymentPage().isExpectedPage();
    }
}
