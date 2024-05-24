/**
 * 
 */
package posters.cucumber.support;

import java.util.ArrayList;

import posters.testdata.dataobjects.Address;
import posters.testdata.dataobjects.CreditCard;
import posters.testdata.dataobjects.Product;
import posters.testdata.dataobjects.User;

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

    public String orderTotal;

    public Product addProduct(Product product)
    {
        // set up product array if needed
        if (products == null)
        {
            products = new ArrayList<Product>();
        }

        // increase amount of product if already there or add the whole product
        if (products.contains(product))
        {
            Product updatedProduct = products.get(products.indexOf(product));
            updatedProduct.setAmount(updatedProduct.getAmount() + 1);
            return updatedProduct;
        }
        else
        {
            products.add(product);
            return product;
        }
    }

    public void removeProduct(String productName, String style, String size)
    {
        for (Product product : products)
        {
            if (product.getName().equals(productName) && product.getSize().equals(size)
                && product.getStyle().equals(style))
            {
                products.remove(product);
            }
        }
    }
}
