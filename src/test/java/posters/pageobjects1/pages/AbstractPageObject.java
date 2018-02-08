/**
 * 
 */
package posters.pageobjects1.pages;

import posters.pageobjects1.components.Title;

/**
 * @author pfotenhauer
 */
public abstract class AbstractPageObject
{
    private Title title;

    abstract public void validateStructure();

    public void isExpectedPage()
    {
    }

    public Title title()
    {
        if (title == null)
        {
            title = new Title();
        }
        return title;
    }
}
