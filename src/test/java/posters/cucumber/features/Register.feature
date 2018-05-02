@Smoke
Feature: Register
  Description: Show case clean up steps implementation using dependency injection for passing data between steps

  @Register
  Scenario Outline: Register a new customer
    Given "<browser>" is open
      And register page is loaded
      And I'm not logged in
     When I register a new user with "<firstName>", "<lastName>", "<email>", "<password>"
     Then register was successful
      And login page is opened

    @Chrome
    Examples: 
      | browser         | firstName | lastName | email        | password  |
      | Chrome_1024x768 | Jane      | Doe      | jane@doe.com | topsecret |

    @Firefox
    Examples: 
      | browser     | firstName | lastName | email          | password  |
      | FF_1024x768 | Jim       | Doe      | jim@doe.com    | topsecret |
      | FF_1024x768 | Jeremy    | Doe      | jeremy@doe.com | topsecret |

  @Register
  Scenario Outline: Login a newly registered  customer
    Given "<browser>" is open
      And new user with "<firstName>", "<lastName>", "<email>", "<password>" is registered
      And I'm not logged in
     When I log in with "<email>" and "<password>"
     Then login was successful for "<firstName>"

    @Chrome
    Examples: 
      | browser         | firstName | lastName | email        | password  |
      | Chrome_1024x768 | Jane      | Doe      | jane@doe.com | topsecret |

    @Firefox
    Examples: 
      | browser     | firstName | lastName | email          | password  |
      | FF_1024x768 | Jim       | Doe      | jim@doe.com    | topsecret |
      | FF_1024x768 | Jeremy    | Doe      | jeremy@doe.com | topsecret |
