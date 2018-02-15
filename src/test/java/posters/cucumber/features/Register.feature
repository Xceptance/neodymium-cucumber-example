@Smoke
Feature: Register
  Description: Show case clean up steps implementation using dependency injection for passing data between steps

  @Register
  Scenario Outline: Register a new customer with
    Given "<browser>" is open
        And user setup: "<firstName>", "<lastName>", "<email>", "<password>"
        And register page is loaded
        And not logged in
    When register form is filled with "<firstName>", "<lastName>", "<email>", "<password>", "<password>" and send
    Then register was successful
        And login page is opened

    @Chrome
    Examples: 
      | browser         | firstName | lastName | email          | password  |
      | Chrome_1024x768 | Jane      | Doe      | jane@doe.com   | topsecret |

    @Firefox
    Examples: 
      | browser     | firstName | lastName | email          | password  |
      | FF_1024x768 | Jim       | Doe      | jim@doe.com    | topsecret |
      | FF_1024x768 | Jeremy    | Doe      | jeremy@doe.com | topsecret |

  @Register
  Scenario Outline: Login a newly registered  customer
    Given "<browser>" is open
        And user setup: "<firstName>", "<lastName>", "<email>", "<password>"
        And login page is opened after registration
        And not logged in   
    When login form is filled with "<email>" and "<password>" and send
    Then login was successful for "<firstName>"

    @Chrome
    Examples: 
      | browser         | firstName | lastName | email          | password  |
      | Chrome_1024x768 | Jane      | Doe      | jane@doe.com   | topsecret |

    @Firefox
    Examples: 
      | browser     | firstName | lastName | email          | password  |
      | FF_1024x768 | Jim       | Doe      | jim@doe.com    | topsecret |
      | FF_1024x768 | Jeremy    | Doe      | jeremy@doe.com | topsecret |
      