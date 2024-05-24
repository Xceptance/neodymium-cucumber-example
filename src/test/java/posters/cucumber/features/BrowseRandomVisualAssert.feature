@BrowseRandomVisualAssert @WebDriverSetUpViaBrowserProfileName @Firefox
Feature: BrowseRandomVisualAssert

  @Skip
  Scenario Outline: Browsing the catalog
    Given homepage is open
     When I choose random sub category with seed "<seed>"
      And I choose some random product
     Then I see correct product

    @Firefox_headless
    Examples: 
      | seed          |
      | 8645363546353 |
