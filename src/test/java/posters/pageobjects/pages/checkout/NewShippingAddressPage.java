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
public class NewShippingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleDelAddr");

    private AddressForm newShippingAddressForm = new AddressForm(Neodymium.localizedText("CheckoutPages.newShippingAddressFormTitle"));

    @Override
    @Step("ensure this is a new shipping address page")
    public NewShippingAddressPage isExpectedPage()
    {
        super.isExpectedPage();
        headline.should(exist);
        newShippingAddressForm.isComponentAvailable();
        return this;
    }

    @Override
    @Step("validate new shipping address page structure")
    public void validateStructure()
    {
        super.validateStructure();
        newShippingAddressForm.validateStructure();
    }

    /**
     * //
     * 
     * @param address
     *            The address you want to use
     * @param sameBillingAddress
     *            Decision whether or not use the same billing address
     */
    @Step("fill and send new shipping address form")
    public NewBillingAddressPage sendShippingAddressForm(Address address, boolean sameBillingAddress)
    {
        newShippingAddressForm.fillAddressForm(address);

        // Click the radio button for Yes or No
        if (sameBillingAddress)
        {
            $("#billEqualShipp-Yes").scrollTo().click();
        }
        else
        {
            $("#billEqualShipp-No").scrollTo().click();
        }
        // Open the billing addresses or payment options page, depending on which radio button you checked
        // Click on Continue
        newShippingAddressForm.confirmAddressForm();

        return new NewBillingAddressPage().isExpectedPage();
    }
}
