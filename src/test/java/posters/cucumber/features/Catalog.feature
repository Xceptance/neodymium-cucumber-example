Feature: Catalog

  Scenario Outline: Browsing the catalog
    Given "<browser>" is open
        And homepage is loaded
    When a main category "<categoryName>" is hovered and a sub category "<subCategoryName>" is opened   
    Then a category page with "<subCategoryName>" headline is visible
  
    @Chrome
    Examples: 
      | browser         | categoryName    | subCategoryName |
      | Chrome_1024x768 | Transportation  | Air Travel      |
      | Chrome_1024x768 | World of Nature | Animals         |

    @Firefox
    Examples: 
      | browser     | categoryName | subCategoryName |
      | FF_1024x768 | Dining       | Main Dishes     |
      | FF_1024x768 | Dining       | Sweets          |
