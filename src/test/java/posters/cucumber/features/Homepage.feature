@Smoke
Feature: Homepage

  Scenario Outline: Visting the homepage
    Given "<browser>" is open
    When homepage is loaded
    Then page title should be "Posters - The Ultimate Online Shop"
        And logo, carousel and hot products are visible
        And footer is visible

    @Chrome
    Examples: 
      | browser         |
      | Chrome_1024x768 |

    @Firefox
    Examples: 
      | browser     |
      | FF_1024x768 |