@Smoke
Feature: Searching for products

  Scenario Outline: Searching for existing products
    Given "<browser>" is open
      And homepage is loaded
     When I search for "<searchTerm>"
     Then I see category page
      And result page contains searchterm "<searchTerm>" and shows "<expectedCount>" products
      And product "<productName>" is visible

    @Chrome
    Examples: 
      | browser         | searchTerm | expectedCount | productName  |
      | Chrome_1024x768 | bear       |             3 | Grizzly Bear |

    @Firefox
    Examples: 
      | browser     | searchTerm | expectedCount | productName                 |
      | FF_1024x768 | bee        |             9 | Indian Summer: Orange Beech |

  Scenario Outline: Searching for a non-existing product
    Given "<browser>" is open
      And homepage is loaded
     When I search for "<searchTerm>"
     Then I see no hits page

    @Chrome
    Examples: 
      | browser         | searchTerm |
      | Chrome_1024x768 | Foobar     |

    @Firefox
    Examples: 
      | browser     | searchTerm |
      | FF_1024x768 | Manbat     |
