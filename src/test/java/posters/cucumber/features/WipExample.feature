@Homepage @WebDriverSetUpViaBrowserProfileName @Chrome @Wip
Feature: Homepage

  @Chrome_1400x1000
  Scenario Outline: Visiting the homepage
    Given homepage is loaded
     Then I see homepage with logo, carousel, hot products and footer and it's title is "<pageTitle>"
    
    Examples: 
      | pageTitle                          |
      | The Poster Demo Store - The Ultimate Online Shop |
