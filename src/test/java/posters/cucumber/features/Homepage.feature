@Smoke @Homepage
Feature: Homepage

  Scenario Outline: Visting the homepage
    Given "<browser>" is open
     When homepage is loaded
     Then I see homepage with logo, carousel, hot products and footer and it's title is "<pageTitle>"

    @Chrome
    Examples: 
      | browser         | pageTitle                          |
      | Chrome_1024x768 | Posters - The Ultimate Online Shop |

    @Firefox
    Examples: 
      | browser     | pageTitle                          |
      | FF_1024x768 | Posters - The Ultimate Online Shop |
