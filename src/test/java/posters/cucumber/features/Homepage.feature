@Smoke @Homepage @WebDriverSetUpViaBrowserProfileName
Feature: Homepage

  Scenario Outline: Visiting the homepage
    Given homepage is loaded
     Then I see homepage with logo, carousel, hot products and footer and it's title is "<pageTitle>"
    
    @Chrome
    @Chrome_1400x1000
    Examples: 
      | pageTitle                          |
      | The Poster Demo Store - The Ultimate Online Shop |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | pageTitle                          |
      | The Poster Demo Store - The Ultimate Online Shop |
