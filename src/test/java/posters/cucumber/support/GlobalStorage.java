package posters.cucumber.support;

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
            var updatedProduct = products.get(products.indexOf(product));
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
        for (var product : products)
        {
            if (product.getName().equals(productName) && product.getSize().equals(size)
                && product.getStyle().equals(style))
            {
                products.remove(product);
            }
        }
    }
}
