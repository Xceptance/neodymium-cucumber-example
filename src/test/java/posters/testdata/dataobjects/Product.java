package posters.testdata.dataobjects;

import posters.pageobjects.utility.PriceHelper;

public class Product
{
    String name;

    String unitPrice;

    String style;

    String size;

    int amount;

    public Product(String name, String unitPrice, String style, String size, int amount)
    {
        this.name = name;
        this.unitPrice = unitPrice;
        this.style = style;
        this.size = size;
        this.amount = amount;
    }

    public String getName()
    {
        return name;
    }

    public String getUnitPrice()
    {
        return unitPrice;
    }

    public String getStyle()
    {
        return style;
    }

    public String getSize()
    {
        return size;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    private double getUnitPriceDouble()
    {
        return Double.parseDouble(PriceHelper.removeCurrency(unitPrice));
    }

    public double getTotalPrice()
    {
        return amount * getUnitPriceDouble();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Product)
        {
            Product anotherProduct = (Product) object;
            return anotherProduct.name.equals(name) && anotherProduct.size.equals(size) && anotherProduct.style.equals(style);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Product [name=" + name + ", unitPrice=" + unitPrice + ", style=" + style + ", size=" + size + ", amount=" + amount + "]";
    }
}
