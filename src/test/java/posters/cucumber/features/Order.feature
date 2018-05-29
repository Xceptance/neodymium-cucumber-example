@Smoke @Order
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

    @Chrome
    Examples: 
      | browser         | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Chrome_1024x768 | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |

    @Firefox
    Examples: 
      | browser     | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | FF_1024x768 | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |

  @Registered
  Scenario Outline: Order some products as User
    Given "<browser>" is open
      And new user with "<firstName>", "<lastName>", "<email>", "<password>" is registered and logged in
     When I add product "<productUrl>" in size "<productSize>" and style "<productStyle>"
      And I specify the shipping address "<name>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" and use it for billing
      And I enter payment data "<name>", "<cardNumber>", "<month>", "<year>"
     Then I see all the products in order overview
      And my shipping and billing addresses as well as payment data are displayed correctly
      And my order is successfully placed
      And all the products are to find in order history

    @Chrome
    Examples: 
      | browser         | firstName | lastName | email        | password  | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Chrome_1024x768 | Jane      | Doe      | jane@doe.com | topsecret | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |

    @Firefox
    Examples: 
      | browser     | firstName | lastName | email       | password  | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | FF_1024x768 | Jim       | Doe      | jim@doe.com | topsecret | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2021 |
