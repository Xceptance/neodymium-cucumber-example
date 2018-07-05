@Smoke @Search @WebDriverSetUpViaBrowserProfileName
Feature: Searching for products

  Scenario Outline: Searching for existing products
    Given homepage is loaded
     When I search for "<searchTerm>"
     Then I see category page
      And result page contains searchterm "<searchTerm>" and shows "<expectedCount>" products
      And product "<productName>" is visible

    @Chrome_1024x768 
    Examples: 
      | searchTerm | expectedCount | productName  |
      | bear       |             3 | Grizzly Bear |

    @FF_1024x768
    Examples: 
      | searchTerm | expectedCount | productName                 |
      | bee        |             9 | Indian Summer: Orange Beech |

  Scenario Outline: Searching for a non-existing product
    Given homepage is loaded
     When I search for "<searchTerm>"
     Then I see no hits page

    @Chrome_1024x768
    Examples: 
      | searchTerm |
      | Foobar     |

    @FF_1024x768
    Examples: 
      | searchTerm |
      | Manbat     |
