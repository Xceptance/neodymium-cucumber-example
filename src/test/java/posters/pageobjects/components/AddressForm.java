package posters.pageobjects.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.xceptance.neodymium.util.Neodymium;

import posters.dataobjects.Address;

public class AddressForm extends AbstractComponent
{
    private final String title;

    public AddressForm(String title)
    {
        this.title = title;
    }

    public void isComponentAvailable()
    {
        $(".header-container>h2").shouldHave(exactText(title));
    }

    public void fillAddressForm(Address address)
    {
        // Wait for the address form to be visible
        $("#fullName").waitUntil(visible, Neodymium.configuration().selenideTimeout());
        // Enter the name parameter
        $("#fullName").val(address.getFirstName() + " " + address.getLastName());
        // Enter the company parameter
        $("#company").val(address.getCompany());
        // Enter the street parameter
        $("#addressLine").val(address.getStreet());
        // Enter the city parameter
        $("#city").val(address.getCity());
        // Enter the state parameter
        $("#state").val(address.getState());
        // Enter the zip parameter
        $("#zip").val(address.getZip());
        // Select the country whose label equals the parameter
        $("#country").selectOption(address.getCountry());
    }
}
