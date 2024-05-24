@Smoke @Homepage @WebDriverSetUpViaBrowserProfileName @Chrome @Firefox
Feature: Homepage

  Scenario Outline: Visting the homepage
    Given homepage is loaded
     Then I see homepage with logo, carousel, hot products and footer and it's title is "<pageTitle>"

    @Chrome_1400x1000
    Examples: 
      | pageTitle                          |
      | The Poster Demo Store - The Ultimate Online Shop |

    @Firefox_1400x1000
    Examples: 
      | pageTitle                          |
      | The Poster Demo Store - The Ultimate Online Shop |
