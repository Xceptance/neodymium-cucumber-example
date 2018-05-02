/**
 * 
 */
package posters.dataobjects;

/**
 * @author pfotenhauer
 */
public class Product
{
    String name;

    String unitPrice;

    String style;

    String size;

    int amount;

    /**
     * @param name
     * @param unitPrice
     * @param totalUnitPrice
     * @param style
     * @param size
     * @param amount
     */
    public Product(String name, String unitPrice, String style, String size, int amount)
    {
        this.name = name;
        this.unitPrice = unitPrice;
        this.style = style;
        this.size = size;
        this.amount = amount;
    }

    public double getUnitPriceDouble()
    {
        return Double.parseDouble(this.unitPrice.replaceAll("\\$", ""));
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setUnitPrice(String price)
    {
        this.unitPrice = price;
    }

    /**
     * @return the unitPrice
     */
    public String getUnitPrice()
    {
        return this.unitPrice;
    }

    /**
     * @return the style
     */
    public String getStyle()
    {
        return style;
    }

    /**
     * @param style
     *            the style to set
     */
    public void setStyle(String style)
    {
        this.style = style;
    }

    /**
     * @return the size
     */
    public String getSize()
    {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(String size)
    {
        this.size = size;
    }

    /**
     * @return the size
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return String.format("Product [name()=%s, size()=%s, style()=%s, price()=%s]", getName(), getSize(), getStyle(), getUnitPrice(), getAmount());
    }

    @Override
    public boolean equals(Object obj)
    {
        Product other = (Product) obj;
        if (this.name.equals(other.name) && this.unitPrice.equals(other.unitPrice) && this.style.equals(other.style) && this.size.equals(other.size))
        {
            return true;
        }
        return false;
    }

}
