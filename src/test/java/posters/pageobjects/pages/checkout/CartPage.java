package posters.pageobjects.pages.checkout;

import com.codeborne.selenide.*;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import posters.pageobjects.pages.browsing.AbstractBrowsingPage;
import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.utility.PostersHelper;
import posters.pageobjects.utility.PriceHelper;
import posters.testdata.dataobjects.Product;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage extends AbstractBrowsingPage
{

    private ElementsCollection cartProducts = $$(".js-cart-product");

    private SelenideElement title = $("#cart-title");

    private SelenideElement cartTable = $(".table");

    private ElementsCollection tableHead = $$(".column-name th");

    private SelenideElement subTotal = $("#order-sub-total-value");

    private SelenideElement checkoutButton = $("#btn-start-checkout");

    @Override
    @Step("ensure this is a cart page")
    public CartPage isExpectedPage()
    {
        super.isExpectedPage();
        title.shouldBe(visible);
        return this;
    }

    /// ========== validate content cart page ========== ///

    @Step("validate product table head")
    public void validateTableHead()
    {
        tableHead.findBy(exactText(Neodymium.localizedText("cartPage.number"))).shouldBe(visible);
        tableHead.findBy(exactText(Neodymium.localizedText("product.title"))).shouldBe(visible);
        tableHead.findBy(exactText(Neodymium.localizedText("product.unitPrice"))).shouldBe(visible);
        tableHead.findBy(exactText(Neodymium.localizedText("product.quantity"))).shouldBe(visible);
        tableHead.findBy(exactText(Neodymium.localizedText("cartPage.update"))).shouldBe(visible);
        tableHead.findBy(exactText(Neodymium.localizedText("product.totalPrice"))).shouldBe(visible);
    }

    @Override
    @Step("validate cart page structure")
    public void validateStructure()
    {
        super.validateStructure();

        // validate title
        title.shouldHave(exactText(Neodymium.localizedText("cartPage.title"))).shouldBe(visible);

        // check if cart contains items, validate
        if (header.miniCart.getTotalCount() == 0)
        {
            $("#error-cart-message").shouldHave(exactText(Neodymium.localizedText("errorMessage.emptyCart"))).shouldBe(visible);
        }
        else
        {
            // validate product table head
            validateTableHead();

            // validate product list
            cartTable.shouldBe(visible);

            // validate at least 1 product in product list
            $("#product-0").shouldBe(visible);

            // validate cart summary list
            $(".price-summary-row").shouldBe(visible);

            // validate checkout button
            checkoutButton.should(visible);
        }
    }

    @Step("validate shipping costs '{shippingCosts}' on cart page")
    public void validateShippingCosts(String shippingCosts)
    {
        $("#price-shipping-value").shouldHave(exactText(shippingCosts));
    }

    @Step("validate tax on cart page")
    public void validateTax(String shippingCosts, String subtotal)
    {
        String tax = PriceHelper.calculateTax(shippingCosts, subtotal);
        $("#order-sub-total-tax-value").shouldHave(exactText(tax));
    }

    @Step("validate subtotal on cart page")
    public void validateSubtotal(String subtotal)
    {
        subTotal.shouldHave(exactText(subtotal));
    }

    @Step("validate cart page")
    public void validate(String shippingCosts, String subtotal)
    {
        validateStructure();
        validateShippingCosts(shippingCosts);
        validateTax(shippingCosts, subtotal);
    }

    @Step("validate empty cart")
    public void validateEmptyCartPage()
    {
        validateStructure();
        $("#error-cart-message").shouldHave(exactText(Neodymium.localizedText("errorMessage.emptyCart"))).shouldBe(visible);
    }

    /**
     * It checks if the price change of subtotal is equal to the price change of the product.
     *
     * @param position
     *     of specific product in cart
     * @param oldSubTotal
     *     subtotal before adding new product to cart/ increasing product quantity
     * @param oldTotalProductPrice
     *     product price before adding/ increasing product quantity
     */
    @Step("validate sub total and line item total after adding on the cart page")
    public void validateTotalAfterAdd(int position, String oldSubTotal, double oldTotalProductPrice)
    {
        /// ========== validate total unit price of specified product ========== ///

        SelenideElement productContainer = $("#product-" + (position - 1));

        // store product unit price (without $ sign) for 1 unit
        String unitPrice = productContainer.find(".product-unit-price").text();

        // store product count
        String quantity = $(".product-count").val();

        // calculate price of specified product
        String newTotalProductPrice = PriceHelper.calculateTotalProductPrice(unitPrice, quantity);

        // verify calculated unit price equals the displayed total unit price
        productContainer.find(".product-total-unit-price").shouldHave(exactText(newTotalProductPrice));

        /// ========== validate sub total ========== ///

        String newSubTotal = subTotal.text();

        // new total - old total = price of item you just added
        String productPrice = PriceHelper.substract(newSubTotal, oldSubTotal);

        // price difference for specific product after changing product amount
        String totalProductPriceChange = PriceHelper.substract(newTotalProductPrice, PriceHelper.format(oldTotalProductPrice));

        Assert.assertEquals(productPrice, totalProductPriceChange);
    }

    @Step("validate sub total and line item total after removing on the cart page")
    public void validateTotalAfterRemove(String oldSubTotal, String oldTotalProductPrice)
    {
        String newSubTotal = PriceHelper.substract(oldSubTotal, oldTotalProductPrice);
        subTotal.shouldHave(exactText(newSubTotal));
    }

    private void validateCartItem(String productName, String productStyle, String productSize, int productAmount, String productPrice)
    {
        SelenideElement productContainer = getProductItem(productName, productStyle, productSize);
        // selector for product
        // SelenideElement productContainer = $("#product-" + (position - 1));

        // validate product image
        productContainer.find(".img-thumbnail").shouldBe(visible);

        // validate parameters
        productContainer.find(".product-name").shouldHave(exactText(productName));
        productContainer.find(".product-style").shouldHave(exactText(productStyle));
        productContainer.find(".product-size").shouldHave(exactText(productSize));
        productContainer.find(".product-unit-price").shouldHave(exactText(productPrice));
        productContainer.find(".product-count").shouldHave(exactValue(Integer.toString(productAmount)));

        // validate remove and update button
        productContainer.find(".btn-update-product").shouldBe(visible);
        productContainer.find(".btn-remove-product").shouldBe(visible);
    }

    public SelenideElement getProductItem(String productName, String productStyle, String productSize)
    {
        return cartProducts.findBy(new WebElementCondition("has name " + productName + ", style " + productStyle
                                                               + " and size " + productSize)
        {
            @Override
            public CheckResult check(Driver driver, WebElement element)
            {
                boolean matchesName = element.findElement(By.cssSelector(".product-name")).getText().contains(productName);
                boolean matchesStyle = element.findElement(By.cssSelector(".product-style")).getText().contains(productStyle);
                boolean matchesSize = element.findElement(By.cssSelector(".product-size")).getText().contains(productSize);
                return new CheckResult(matchesName && matchesStyle && matchesSize, element);
            }
        });
    }

    @Step("validate '{product}' on the cart page")
    public void validateCartItem(Product product)
    {
        validateCartItem(product.getName(), product.getStyle(), product.getSize(), product.getAmount(), product.getUnitPrice());
    }

    @Step("validate '{product}' on the cart page after changing it's quantity")
    public void validateCartItem(Product product, int productAmount)
    {
        validateCartItem(product.getName(), product.getStyle(), product.getSize(), productAmount, product.getUnitPrice());
    }

    /// ========== get product data ========== ///

    @Step("get product name from line item on position '{position}' on the cart page")
    public String getProductName(int position)
    {
        return $("#product-" + (position - 1) + " .product-name").text();
    }

    @Step("get product style from line item on position '{position}' on the cart page")
    public String getProductStyle(int position)
    {
        return $("#product-" + (position - 1) + " .product-style").text();
    }

    @Step("get product size from line item on position '{position}' on the cart page")
    public String getProductSize(int position)
    {
        return $("#product-" + (position - 1) + " .product-size").text();
    }

    @Step("get product count from line item on position '{position}' on the cart page")
    public String getProductCount(int position)
    {
        return $("#product-" + (position - 1) + " .product-count").val();
    }

    @Step("get product unit price from line item on position '{position}' on the cart page")
    public String getProductUnitPrice(int position)
    {
        return $("#product-" + (position - 1) + " .product-unit-price").text();
    }

    @Step("get product total price from line item on position '{position}' on the cart page")
    public String getProductTotalPrice(int position)
    {
        return $("#product-" + (position - 1) + " .product-total-unit-price").text();
    }

    @Step("get product from line item on position '{position}' on the cart page")
    public Product getProduct(int position)
    {
        return new Product(getProductName(position), getProductUnitPrice(position), getProductStyle(position), getProductSize(position),
                           Integer.parseInt(getProductCount(position)));
    }

    /// ========== update product data ========== ///

    @Step("update product count of product on position '{position}' on the cart page to '{amount}'")
    public void updateProductCount(String productName, String productStyle, String productSize, int amount)
    {
        SelenideElement productContainer = getProductItem(productName, productStyle, productSize);
        String priceBeforeProductCountUpdate = productContainer.find(".product-total-unit-price").text();

        // type new amount
        productContainer.find(".product-count").setValue(Integer.toString(amount));

        // click update button
        productContainer.find(".btn-update-product").click(ClickOptions.usingJavaScript());

        // wait until the product amount is updated
        productContainer.find(".product-count").shouldHave(exactValue(Integer.toString(amount)), Duration.ofSeconds(5));

        // wait for the product price to be updated
        productContainer.find(".product-total-unit-price").shouldNotHave(exactText(priceBeforeProductCountUpdate));
    }

    @Step("remove product on position '{position}' on the cart page")
    public void removeProduct(int position)
    {
        int numberOfProducts = cartProducts.size();

        // click delete button
        $("#btn-remove-prod-count-" + (position - 1)).click(ClickOptions.usingJavaScript());

        // click delete confirmation button
        $("#button-delete").click(ClickOptions.usingJavaScript());

        // wait until the product is deleted
        PostersHelper.optionalWaitUntilConditionSizeIs(cartProducts, numberOfProducts - 1, Duration.ofMillis(3000));
    }

    @Step("wait for update")
    public void waitForProductUpdate(String subtotalBeforeUpdate)
    {
        // wait for subtotal to update
        subTotal.shouldNotHave(exactText(subtotalBeforeUpdate));
    }

    /// ========== cart page navigation ========== ///

    @Step("open homepage from cart page")
    public HomePage openHomePage()
    {
        $("#header-brand").click(ClickOptions.usingJavaScript());
        return new HomePage().isExpectedPage();
    }

    @Step("open guest shipping address from the cart page")
    public GuestShippingAddressPage openGuestShippingAddressPage()
    {
        checkoutButton.click(ClickOptions.usingJavaScript());
        return new GuestShippingAddressPage().isExpectedPage();
    }

    @Step("open returning customer shipping address from the cart page")
    public ReturningCustomerShippingAddressPage openReturningCustomerShippingAddressPage()
    {
        checkoutButton.click(ClickOptions.usingJavaScript());
        return new ReturningCustomerShippingAddressPage().isExpectedPage();
    }

    @Step("check if there are products in cart")
    public boolean hasProductsInCart()
    {
        return !PostersHelper.optionalWaitUntilConditionSizeIs(cartProducts, 0, Duration.ofMillis(3000));
    }
}
