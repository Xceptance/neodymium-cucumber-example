/**
 * 
 */
package posters.pageobjects1.pages.checkout;

import posters.pageobjects1.components.CheckoutHeader;
import posters.pageobjects1.components.Footer;
import posters.pageobjects1.components.UserMenu;
import posters.pageobjects1.pages.AbstractPageObject;

/**
 * @author pfotenhauer
 */
public abstract class AbstractCheckoutPage extends AbstractPageObject
{

    private CheckoutHeader header;

    private Footer footer;

    private UserMenu userMenu;

    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.neodymium.scripting.template.selenide.page.AbstractPageObject#validateStructure()
     */
    @Override
    public void validateStructure()
    {
        isExpectedPage();
    }

    /**
     * @return
     */
    public UserMenu userMenu()
    {
        if (userMenu == null)
        {
            userMenu = new UserMenu();
        }
        userMenu.isComponentAvailable();
        return userMenu;
    }

    public Footer footer()
    {
        if (footer == null)
        {
            footer = new Footer();
        }
        footer.isComponentAvailable();
        return footer;
    }

    public CheckoutHeader header()
    {
        if (header == null)
        {
            header = new CheckoutHeader();
        }
        header.isComponentAvailable();
        return header;
    }
}
