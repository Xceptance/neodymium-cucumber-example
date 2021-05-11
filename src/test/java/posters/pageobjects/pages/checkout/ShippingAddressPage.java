package posters.pageobjects.pages.checkout;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.dataobjects.Address;
import posters.pageobjects.components.AddressForm;

/**
 * @author pfotenhauer
 */
public class ShippingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleDelAddr");

    private AddressForm shippingAddressForm = new AddressForm(Neodymium.localizedText("CheckoutPages.shippingAddressFormTitle"));

    @Override
    @Step("ensure this is a shipping address page")
    public ShippingAddressPage isExpectedPage()
    {
        super.isExpectedPage();
        headline.should(exist);
        return this;
    }

    @Override
    @Step("validate shipping address page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // Assert the headline is there and starts with a capital letter
        headline.should(matchText("[A-Z].{3,}"));
        // Makes sure at least one address is visible
        $("#delAddr0").shouldBe(visible);
        shippingAddressForm.isComponentAvailable();
    }

    /**
     * @param position
     *            position of the shipping address
     * @return BillingAddressPage
     */
    @Step("select a shipping address")
    public BillingAddressPage selectShippingAddress(int position)
    {
        final int index = position - 1;
        // Checks the radio button belonging to the delivery address with index @{index}
        $("#delAddr" + index + " input").scrollTo().click();
        // Open the billing address page in the checkout process
        // Clicks the continue button
        $("#btnUseAddressContinue").scrollTo().click();

        return new BillingAddressPage().isExpectedPage();
    }

    @Step("fill and send shipping address without different billing address")
    public PaymentPage fillShippingAddressWithSameAsBilling(Address shippingAddress)
    {
        // Fill shipping address
        shippingAddressForm.fillAddressForm(shippingAddress);

        // Click checkbox YES
        $("#billEqualShipp-Yes").scrollTo().click();

        // Send shipping addresses by clicking the button Continue
        $("#btnAddDelAddr").scrollTo().click();

        return new PaymentPage().isExpectedPage();
    }

    @Step("fill and send shipping address with different billing address")
    public BillingAddressPage fillShippingAddressWithDifferentBilling(Address shippingAddress)
    {
        // Fill shipping address
        shippingAddressForm.fillAddressForm(shippingAddress);

        // Click checkbox NO
        $("#billEqualShipp-No").scrollTo().click();

        // Send shipping addresses by clicking the button Continue
        $("#btnAddDelAddr").scrollTo().click();

        return new BillingAddressPage().isExpectedPage();
    }
}
