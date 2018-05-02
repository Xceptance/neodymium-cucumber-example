@Smoke
Feature: Order

  Scenario Outline: Order some products as a Guest
    Given "<browser>" is open
      And product page "<firstProductUrl>" is open
     When I add this product with size "<firtsProductSize>" and style "<firtsProductStyle>" to the cart
      And I add product "<firstProductUrl>" in size "<firtsProductSize>" and style "<firtsProductStyle>"
      And I add product "<secondProductUrl>" in size "<secondProductSize>" and style "<secondProductStyle>"
      And I specify the shipping address "<name>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" and use it for billing
      And I enter payment data "<name>", "<cardNumber>", "<month>", "<year>"
     Then I see all the products in order overview
     And my shipping and billing addresses as well as payment data are displayed correctly

    @WIP @Chrome
    Examples: 
      | browser         | firstProductUrl                          | secondProductUrl                                     | firtsProductSize | secondProductSize | secondProductStyle | firtsProductStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Chrome_1024x768 | productDetail/Grizzly%20Bear?productId=1 | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | gloss              | matte             | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |

    @Firefox
    Examples: 
      | browser     | firstProductUrl                          | secondProductUrl                                     | firtsProductSize | secondProductSize | secondProductStyle | firtsProductStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | FF_1024x768 | productDetail/Grizzly%20Bear?productId=1 | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | gloss              | matte             | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |
