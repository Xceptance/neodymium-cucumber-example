@Browse
Feature: Browse

  Scenario Outline: Browsing the catalog
    Given "<browser>" is open
      And homepage is loaded
     When I choose main category "<categoryName>" and sub category "<subCategoryName>"
     Then I see category page with "<subCategoryName>" headline

    @Chrome
    Examples: 
      | browser         | categoryName    | subCategoryName |
      | Chrome_1024x768 | Transportation  | Air Travel      |
      | Chrome_1024x768 | World of Nature | Animals         |

    @Firefox
    Examples: 
      | browser          | categoryName | subCategoryName |
      | Firefox_1024x768 | Dining       | Main Dishes     |
      | Firefox_1024x768 | Dining       | Sweets          |
