@Product @WebDriverSetUpViaBrowserProfileName
Feature: Product

  Scenario Outline: Opening  a product detail page from a catalog page
    Given homepage is loaded
      And a main category "<categoryName>" is hovered and a sub category "<subCategoryName>" is opened
     When product "<productName>" is opened
     Then a product detail page shows the headline "<productName>"

    @Chrome
    @Chrome_1400x1000
    Examples: 
      | categoryName   | subCategoryName | productName            |
      | Transportation | Air Travel      | Air Lingus Airbus A320 |
      | Dining         | Main Dishes     | Tuna Steak             |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | categoryName    | subCategoryName | productName       |
      | World of Nature | Animals         | Grizzly Bear      |
      | Dining          | Sweets          | Colored Sprinkles |
