/**
 * 
 */
package posters.cucumber.dataHelper;

import java.util.ArrayList;

import posters.dataobjects.Address;
import posters.dataobjects.CreditCard;
import posters.dataobjects.Product;
import posters.dataobjects.User;

/**
 * Basic storage object that can be used with dependency injection later on
 * 
 * @author pfotenhauer
 */
public class GlobalStorage
{
    public User user;

    public ArrayList<Product> products;

    public Address shippingAddress;

    public Address billingAddress;

    public CreditCard creditcard;

}
