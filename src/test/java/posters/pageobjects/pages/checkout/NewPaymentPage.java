package posters.pageobjects.pages.checkout;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import posters.dataobjects.CreditCard;
import posters.pageobjects.components.PaymentForm;

/**
 * @author pfotenhauer
 */
public class NewPaymentPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titlePayment");

    private PaymentForm paymentForm = new PaymentForm();

    @Override
    @Step("ensure this is a new payment page")
    public NewPaymentPage isExpectedPage()
    {
        super.isExpectedPage();
        headline.should(exist);
        return this;
    }

    @Override
    @Step("validate new payment page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // Headline
        // Makes sure the headline is there and starts with a capital letter
        headline.should(matchText("[A-Z].{3,}"));
        // Form
        // Make sure the form is there to begin with
        $("#formAddPayment").should(exist);
        paymentForm.validateStructure();
    }

    /**
     * @param creditcard
     * @return
     */
    @Step("fill and send payment form")
    public PlaceOrderPage sendPaymentForm(CreditCard card)
    {
        paymentForm.sendPaymentForm(card);
        return new PlaceOrderPage().isExpectedPage();
    }
}
