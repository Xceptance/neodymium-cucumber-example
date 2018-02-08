package posters.pageobjects1.pages.browsing;

import posters.pageobjects1.components.ErrorMessage;
import posters.pageobjects1.components.Footer;
import posters.pageobjects1.components.Header;
import posters.pageobjects1.components.MiniCart;
import posters.pageobjects1.components.Search;
import posters.pageobjects1.components.SuccessMessage;
import posters.pageobjects1.components.TopNavigation;
import posters.pageobjects1.components.UserMenu;
import posters.pageobjects1.pages.AbstractPageObject;

public abstract class AbstractBrowsingPage extends AbstractPageObject
{

    private Header header;

    private Footer footer;

    private MiniCart miniCart;

    private Search search;

    private TopNavigation topNav;

    private UserMenu userMenu;

    private SuccessMessage successMessage;

    private ErrorMessage errorMessage;

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

    public Footer footer()
    {
        if (footer == null)
        {
            footer = new Footer();
        }
        footer.isComponentAvailable();
        return footer;
    }

    public Header header()
    {
        if (header == null)
        {
            header = new Header();
        }
        header.isComponentAvailable();
        return header;
    }

    public MiniCart miniCart()
    {
        if (miniCart == null)
        {
            miniCart = new MiniCart();
        }
        miniCart.isComponentAvailable();
        return miniCart;
    }

    public Search search()
    {
        if (search == null)
        {
            search = new Search();
        }
        search.isComponentAvailable();
        return search;
    }

    public TopNavigation topNav()
    {
        if (topNav == null)
        {
            topNav = new TopNavigation();
        }
        topNav.isComponentAvailable();
        return topNav;
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

    public SuccessMessage successMessage()
    {
        if (successMessage == null)
        {
            successMessage = new SuccessMessage();
        }
        successMessage.isComponentAvailable();
        return successMessage;
    }

    public ErrorMessage errorMessage()
    {
        if (errorMessage == null)
        {
            errorMessage = new ErrorMessage();
        }
        errorMessage.isComponentAvailable();
        return errorMessage;
    }
}
