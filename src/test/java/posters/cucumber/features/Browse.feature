@Browse @WebDriverSetUpViaBrowserProfileName @Wip
Feature: Browse

  Scenario Outline: Browsing the catalog
    Given homepage is loaded
     When I choose main category "<categoryName>" and sub category "<subCategoryName>"
     Then I see category page with "<subCategoryName>" headline and "<expectedResultCount>" results

    @Chrome
    @Chrome_1400x1000
    Examples: 
      | categoryName    | subCategoryName | expectedResultCount |
      | Transportation  | Air Travel      | 10                  |
      | World of Nature | Animals         | 10                  |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | categoryName | subCategoryName | expectedResultCount |
      | Dining       | Main Dishes     | 10                  |
      | Dining       | Sweets          | 10                  |
