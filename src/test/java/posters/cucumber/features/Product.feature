Feature: Product

  Scenario Outline: Opening  a product detail page from a catalog page
    Given "<browser>" is open
        And homepage is loaded
        And a main category "<categoryName>" is hovered and a sub category "<subCategoryName>" is opened    
    When product "<productName>" is opened
    Then a product detail page shows the headline "<productName>"

    Examples:
      | browser         | categoryName   | subCategoryName | productName            |
      | Chrome_1024x768 | Transportation | Air Travel      | Air Lingus Airbus A320 |
      | Chrome_1024x768 | Dining         | Main Dishes     | Tuna Steak             |

    Examples:
      | browser     | categoryName    | subCategoryName | productName       |
      | FF_1024x768 | World of Nature | Animals         | Grizzly Bear      |
      | FF_1024x768 | Dining          | Sweets          | Colored Sprinkles |
