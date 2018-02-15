@Smoke
Feature: Searching for products

  Scenario Outline: Searching for an existing product
    Given "<browser>" is open
        And homepage is loaded    
    When searched for "<searchTerm>"
    Then a category page is opened 
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
    When searched for "<searchTerm>"
    Then no hits page is opened

    @Chrome
    Examples: 
      | browser         | searchTerm |
      | Chrome_1024x768 | Foobar     |

    @Firefox
    Examples: 
      | browser     | searchTerm |
      | FF_1024x768 | Manbat     |