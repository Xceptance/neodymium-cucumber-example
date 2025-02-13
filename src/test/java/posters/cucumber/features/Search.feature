@Smoke @Search @WebDriverSetUpViaBrowserProfileName
Feature: Searching for products

  Scenario Outline: Searching for existing products
    Given homepage is loaded
    When I search for "<searchTerm>"
    Then I see category page
    And result page contains searchterm "<searchTerm>" and shows "<expectedCount>" products
    And product "<productName>" is visible

    @Chrome
    @Chrome_1400x1000
    Examples:
      | searchTerm | expectedCount | productName  |
      | bear       | 3             | Grizzly Bear |

    @Firefox
    @Firefox_1400x1000
    Examples:
      | searchTerm | expectedCount | productName                |
      | beech      | 3             | Indian Summer Orange Beech |

  Scenario Outline: Searching for a non-existing product
    Given homepage is loaded
    When I search for "<searchTerm>"
    Then I see no hits page

    @Chrome
    @Chrome_1400x1000
    Examples:
      | searchTerm |
      | Foobar     |

    @Firefox
    @Firefox_1400x1000
    Examples:
      | searchTerm |
      | Manbat     |
