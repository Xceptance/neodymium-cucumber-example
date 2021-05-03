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
            String name = updatedProduct.getName();
            String unitPrice = updatedProduct.getUnitPrice();
            String style = updatedProduct.getStyle();
            String size = updatedProduct.getSize();
            int amount = updatedProduct.getAmount();
            products.remove(updatedProduct);
            updatedProduct = new Product(name, unitPrice, style, size, amount + 1); 
            products.add(updatedProduct);
            return updatedProduct;
        }
        else
        {
            products.add(product);
            return product;
        }
    }
    
    public Product getProductFromArrayList(String name, String size, String style)
    {
        int i = 0;
        for (Product product : products)
        {
            if (product.getName().equals(name) && product.getSize().equals(size)
                && product.getStyle().equals(style))
            {
            i = products.indexOf(product);
            }
        }
        return products.get(i);
    }
    
    public void updateCountOfProduct(String name, String size, String style, int amount)
    {
        var updateProducht = getProductFromArrayList(name, size, style);
        String unitPrice = updateProducht.getUnitPrice();
        products.remove(products.indexOf(updateProducht));
        updateProducht = new Product(name, unitPrice, style, size, amount);
        products.add(updateProducht);
    }
    
    public void removeProduct(String name, String style, String size)
    {
        var updateProducht = getProductFromArrayList(name, size, style);
        products.remove(products.indexOf(updateProducht));
    }
}
