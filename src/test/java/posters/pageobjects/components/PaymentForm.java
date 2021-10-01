package posters.pageobjects.components;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import posters.dataobjects.CreditCard;

public class PaymentForm extends AbstractComponent
{
    private SelenideElement creditCardNumber = $("#creditCardNumber");

    private SelenideElement creditCardName = $("#name");

    private SelenideElement expirationMonth = $("#expirationDateMonth");

    private SelenideElement expirationYear = $("#expirationDateYear");

    private SelenideElement addPaymentButton = $("#btnAddPayment");

    @Override
    @Step("ensure payment form is available on the page")
    public void isComponentAvailable()
    {
        creditCardNumber.shouldBe(visible);
    }

    @Step("validate payment form structure")
    public void validateStructure()
    {
        creditCardName.shouldBe(visible);
        expirationMonth.shouldBe(visible);
        expirationYear.shouldBe(visible);
        addPaymentButton.shouldBe(visible);
    }

    @Step("fill and send payment form")
    public void sendPaymentForm(CreditCard card)
    {
        // Credit Card Number
        // Fills the card number field with the parameter
        creditCardNumber.val(card.getCardNumber());
        // Name
        // Fills the card holder field with the parameter
        creditCardName.val(card.getFullName());
        // Expiration
        // Chooses the expiration month matching the parameter
        expirationMonth.selectOption(card.getExpDateMonth());
        // Chooses the expiration year matching the parameter
        expirationYear.selectOption(card.getExpDateYear());
        // Opens the order overview page
        // Clicks the Continue button
        addPaymentButton.scrollTo().click();
    }
}
