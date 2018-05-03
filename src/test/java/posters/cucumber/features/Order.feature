@Smoke
Feature: Order

  Scenario Outline: Order some products as a Guest
    Given "<browser>" is open
      And product page "<productUrl>" is open
     When I add this product with size "<productSize>" and style "<productStyle>" to the cart
      And I specify the shipping address "<name>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" and use it for billing
      And I enter payment data "<name>", "<cardNumber>", "<month>", "<year>"
     Then I see all the products in order overview
      And my shipping and billing addresses as well as payment data are displayed correctly
      And my order is successfully placed

    @Chrome @WIP
    Examples: 
      | browser         | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Chrome_1024x768 | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |

    @Firefox @WIP
    Examples: 
      | browser     | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | FF_1024x768 | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |
