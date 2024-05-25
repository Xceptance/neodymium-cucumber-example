@Smoke @Order @WebDriverSetUpViaBrowserProfileName
Feature: Order

  Scenario Outline: Order some products as a Guest
    Given product page "<productUrl>" is open
     When I add this product with size "<productSize>" and style "<productStyle>" to the cart
      And I as guest user specify the shipping address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>"
      And I as guest user specify the billing address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>"
      And I as guest user enter payment data "<firstName>", "<lastName>", "<cardNumber>", "<month>", "<year>"
     Then I see all the products in order overview
      And my shipping and billing addresses as well as payment data are displayed correctly
      And my order is successfully placed
    
    @Chrome
    @Chrome_1400x1000
    Examples: 
      | productUrl                               | productSize | productStyle | firstName | lastName | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | Jakobi    | Doe      | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2030 |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | productUrl                               | productSize | productStyle | firstName | lastName |  company          | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | Jakobi    | Doe      |Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2030 |

  @DeleteUserAfterwards
  Scenario Outline: Order some products as User
    Given new user with "<firstName>", "<lastName>", "<email>", "<password>" is registered and logged in
     And new shipping address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" is added to account
     And new billing address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" is added to account
     And new credit card "<firstName>", "<lastName>", "<cardNumber>", "<month>", "<year>" is added to account
     When I add product "<productUrl>" in size "<productSize>" and style "<productStyle>"
      And I as registered user select the shipping address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" and use it for billing
      And I as registered user select the billing address "<firstName>", "<lastName>", "<company>", "<address>", "<city>", "<state>", "<zip>", "<country>" and use it for billing
      And I as registered user select payment "<firstName>", "<lastName>", "<cardNumber>", "<month>", "<year>"
     Then I see all the products in order overview
      And my shipping and billing addresses as well as payment data are displayed correctly
      And my order is successfully placed
      And all the products are to find in order history

    @Chrome
    @Chrome_1400x1000
    Examples: 
      | firstName | lastName | email          | password  | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Jakobi    | Doe      | jakobi@doe.com | topsecret | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2030 |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | firstName | lastName | email          | password  | productUrl                               | productSize | productStyle | name  | company             | address     | city           | state    | zip   | country       | cardNumber       | month | year |
      | Justus    | Doe      | justus@doe.com | topsecret | productDetail/Grizzly%20Bear?productId=1 | 32 x 24 in  | gloss        | James | Monster Corporation | 621 Wall St | North Tonawada | New York | 14120 | United States | 4111111111111111 |    05 | 2030 |
