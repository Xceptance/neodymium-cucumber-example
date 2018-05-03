Feature: Cart

  Scenario Outline: Add to cart
    Given "<browser>" is open
      And product page "<firstProductUrl>" is open
     When I add this product with size "<firtsProductSize>" and style "<firtsProductStyle>" to the cart
      And I add product "<firstProductUrl>" in size "<firtsProductSize>" and style "<firtsProductStyle>"
      And I add product "<secondProductUrl>" in size "<secondProductSize>" and style "<secondProductStyle>"
     And I open the cart
     Then I see all the added products in the cart and their properties are correct
     And I can change amount of the "<firstProductName>" with "<firtsProductSize>" and "<firtsProductStyle>" to 1
     And I can remove "<firstProductName>" with "<firtsProductSize>" and "<firtsProductStyle>"
     

    @Chrome
    Examples: 
      | browser         | firstProductUrl                          | firstProductName | secondProductUrl                                     | firtsProductSize | secondProductSize | secondProductStyle | firtsProductStyle |
      | Chrome_1024x768 | productDetail/Grizzly%20Bear?productId=1 | Grizzly Bear     | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | gloss              | matte             |

    @Firefox
    Examples: 
      | browser     | firstProductUrl                          | firstProductName | secondProductUrl                                     | firtsProductSize | secondProductSize | secondProductStyle | firtsProductStyle |
      | FF_1024x768 | productDetail/Grizzly%20Bear?productId=1 | Grizzly Bear     | productDetail/Gummy%20Bears%20in%20Bowl?productId=57 | 32 x 24 in       | 16 x 12 in        | gloss              | matte             |
