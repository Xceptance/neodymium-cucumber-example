package posters.pageobjects.pages.checkout;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.pageobjects.components.AddressForm;
import posters.dataobjects.Address;

/**
 * @author pfotenhauer
 */
public class BillingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleBillAddr");

    private AddressForm billingAddressForm = new AddressForm(Neodymium.localizedText("CheckoutPages.billingAddressFormTitle"));

    @Override
    @Step("ensure this is a billing address page")
    public BillingAddressPage isExpectedPage()
    {
        headline.should(exist);
        return this;
    }

    @Override
    @Step("validate billing address page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // Headline
        // Assert the headline is there and starts with a capital letter
        headline.should(matchText("[A-Z].{3,}"));
        // First address
        // Makes sure at least one address is visible
        $("#billAddr0").shouldBe(visible);
        billingAddressForm.isComponentAvailable();
    }

    /**
     * @param position
     *            The position of the billing address you want to choose
     * @return PaymentPage
     */
    @Step("select a billing address")
    public PaymentPage selectBillingAddress(int position)
    {
        final int index = position - 1;
        // Select address
        // Checks the radio button belonging to the delivery address with index @{index}
        $("#billAddr" + index + " input").scrollTo().click();
        // Open the billing address page in the checkout process
        // Clicks the continue button
        $("#btnUseBillAddress").scrollTo().click();

        return new PaymentPage().isExpectedPage();
    }

    @Step("fill the billing address")
    public PaymentPage fillBillingAddress(Address billingAddress)
    {
        // Fill billing address
        billingAddressForm.fillAddressForm(billingAddress);

        // Send billing addresses by clicking the button Continue
        $("#btnAddBillAddr").scrollTo().click();

        return new PaymentPage().isExpectedPage();
    }
}
