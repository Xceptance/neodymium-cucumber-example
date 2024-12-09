@Cart @WebDriverSetUpViaBrowserProfileName
Feature: Cart

  Scenario Outline: Add to cart
    Given product page "<firstProductUrl>" is open
    When I add this product with size "<firstProductSize>" and style "<firstProductStyle>" to the cart
    And I add product "<firstProductUrl>" in size "<firstProductSize>" and style "<firstProductStyle>"
    And I add product "<secondProductUrl>" in size "<secondProductSize>" and style "<secondProductStyle>"
    And I open the cart
    Then I see all the added products in the cart and their properties are correct
    And I can change amount of the product "<firstProductName>" number 1 with "<firstProductSize>" and "<firstProductStyle>" to 1
    And I can remove product number 1 "<firstProductName>" with "<firstProductSize>" and "<firstProductStyle>"

    @Chrome
    @Chrome_1400x1000
    Examples:
      | firstProductUrl                          | firstProductName | secondProductUrl                                     | firstProductSize | secondProductSize | firstProductStyle | secondProductStyle |
      | productDetail/Grizzly%20Bear?productId=1 | Grizzly Bear     | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | matte             | gloss              |

    @Firefox
    @Firefox_1400x1000
    Examples:
      | firstProductUrl                          | firstProductName | secondProductUrl                                     | firstProductSize | secondProductSize | firstProductStyle | secondProductStyle |
      | productDetail/Grizzly%20Bear?productId=1 | Grizzly Bear     | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | matte             | gloss              |
