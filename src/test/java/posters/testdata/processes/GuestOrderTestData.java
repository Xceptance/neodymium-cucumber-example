package posters.testdata.processes;

import posters.testdata.dataobjects.Address;
import posters.testdata.dataobjects.CreditCard;

public class GuestOrderTestData
{
    private String topCategory;

    private int resultPosition;

    private String sizeProduct;

    private String styleProduct;

    private int amountChange;

    private Address shippingAddress;

    private Boolean shipAddrEqualBillAddr;

    private Address billingAddress;

    private CreditCard creditCard;

    public String getTopCategory()
    {
        return topCategory;
    }

    public int getResultPosition()
    {
        return resultPosition;
    }

    public String getsSizeProduct()
    {
        return sizeProduct;
    }

    public String getStyleProduct()
    {
        return styleProduct;
    }

    public int getAmountChange()
    {
        return amountChange;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public Boolean getShipAddrEqualBillAddr()
    {
        return shipAddrEqualBillAddr;
    }

    public Address getBillingAddress()
    {
        return billingAddress;
    }

    public CreditCard getCreditCard()
    {
        return creditCard;
    }
}