/**
 * 
 */
package posters.flows;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import posters.pageobjects.pages.browsing.HomePage;
import posters.pageobjects.pages.checkout.CartPage;

/**
 * @author pfotenhauer
 */
public class CartCleanUpFlow
{

    /**
     * 
     */
    public void flow()
    {
        $("#globalNavigation #brand").click();

        HomePage homePage = page(HomePage.class);
        homePage.miniCart().openMiniCart();

        CartPage cartPage = homePage.miniCart().openCartPage();
        while (cartPage.hasProductsInCart())
        {
            cartPage.removeProduct(1);
        }
    }
}
