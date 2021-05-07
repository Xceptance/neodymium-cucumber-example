package posters.pageobjects.pages.checkout;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.dataobjects.Address;

/**
 * @author pfotenhauer
 */
public class NewShippingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleDelAddr");

    private SelenideElement nameField = $("#fullName");

    private SelenideElement companyField = $("#company");

    private SelenideElement addressField = $("#addressLine");

    private SelenideElement cityField = $("#city");

    private SelenideElement stateField = $("#state");

    private SelenideElement zipField = $("#zip");

    private SelenideElement countryField = $("#country");

    private SelenideElement addShippingButton = $("#btnAddDelAddr");

    @Override
    @Step("ensure this is a new shipping address page")
    public NewShippingAddressPage isExpectedPage()
    {
        super.isExpectedPage();
        headline.should(exist);
        return this;
    }

    @Override
    @Step("validate new shipping address page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // Headline
        // Assert the headline is there and starts with a capital letter
        headline.should(matchText("[A-Z].{3,}"));
        // Form
        // Asserts the form is there at all
        $("#formAddDelAddr").shouldBe(visible);
        // Name
        // Asserts the label next to the name field shows the right text
        $("label[for='fullName']").shouldHave(exactText(Neodymium.localizedText("General.addresses.fullname")));
        // Asserts the name field is there
        nameField.shouldBe(visible);
        // Company
        // Asserts the label next to the company field shows the right text
        $("label[for='company']").shouldHave(exactText(Neodymium.localizedText("General.addresses.company")));
        // Asserts the company field is there
        companyField.shouldBe(visible);
        // Address
        // Asserts the label next to the address field shows the right text
        $("label[for='addressLine']").shouldHave(exactText(Neodymium.localizedText("General.addresses.address")));
        // Asserts the address field is there
        addressField.shouldBe(visible);
        // City
        // Asserts the label next to the city field shows the right text
        $("label[for='city']").shouldHave(exactText(Neodymium.localizedText("General.addresses.city")));
        // Asserts the city field is there
        cityField.shouldBe(visible);
        // State
        // Asserts the label next to the state field shows the right text
        $("label[for='state']").shouldHave(exactText(Neodymium.localizedText("General.addresses.state")));
        // Asserts the state field is there
        stateField.shouldBe(visible);
        // Zip
        // Asserts the label next to the zip field shows the right text
        $("label[for='zip']").shouldHave(exactText(Neodymium.localizedText("General.addresses.zip")));
        // Asserts the zip field is there
        zipField.shouldBe(visible);
        // Country
        // Asserts the label next to the country field shows the right text
        $("label[for='country']").shouldHave(exactText(Neodymium.localizedText("General.addresses.country")));
        // Asserts the country field is there
        countryField.shouldBe(visible);
        // Radio Button
        // Assert the radio buttons are there
        $$("input[name='billEqualShipp']").shouldHaveSize(2);
        // Continue Button
        // Asserts the Continue button is there
        addShippingButton.shouldBe(visible);
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
        // Enter the name parameter
        nameField.val(address.getFirstName());
        // Enter the company parameter
        companyField.val(address.getCompany());
        // Enter the street parameter
        addressField.val(address.getStreet());
        // Enter the city parameter
        cityField.val(address.getCity());
        // Enter the state parameter
        stateField.val(address.getState());
        // Enter the zip parameter
        zipField.val(address.getZip());
        // Select the country whose label equals the parameter
        countryField.selectOption(address.getCountry());
        
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
        addShippingButton.scrollTo().click();

        return new NewBillingAddressPage().isExpectedPage();
    }
}
