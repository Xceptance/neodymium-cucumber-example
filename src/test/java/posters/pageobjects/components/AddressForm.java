package posters.pageobjects.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import posters.dataobjects.Address;

public class AddressForm extends AbstractComponent
{
    private final String title;

    private SelenideElement nameField = $("#fullName");

    private SelenideElement companyField = $("#company");

    private SelenideElement addressField = $("#addressLine");

    private SelenideElement cityField = $("#city");

    private SelenideElement stateField = $("#state");

    private SelenideElement zipField = $("#zip");

    private SelenideElement countryField = $("#country");

    private SelenideElement continueButton = $("button.btn");

    public AddressForm(String title)
    {
        this.title = title;
    }

    public void isComponentAvailable()
    {
        $("#fullName").shouldBe(visible);
    }

    public void validateStructure()
    {
        $(".header-container>h2").shouldHave(exactText(title));

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
        continueButton.shouldBe(visible);
    }

    public void fillAddressForm(Address address)
    {
        // Enter the name parameter
        nameField.val(address.getFirstName() + " " + address.getLastName());
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
    }

    public void confirmAddressForm()
    {
        continueButton.click();
    }
}
