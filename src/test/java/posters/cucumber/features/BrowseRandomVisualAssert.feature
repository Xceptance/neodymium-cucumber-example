@BrowseRandomVisualAssert
Feature: BrowseRandomVisualAssert

  @Skip
  Scenario Outline: Browsing the catalog
    Given "<browser>" is open
      And homepage is open
     When I choose random sub category with seed "<seed>"
      And I choose some random product
     Then I see correct product

  @Firefox
    Examples: 
      | browser          | seed          |
      | Firefox_headless | 8645363546353 |
