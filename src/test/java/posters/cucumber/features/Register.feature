@Smoke @Register @WebDriverSetUpViaBrowserProfileName
Feature: Register
  Description: Show case clean up steps implementation using dependency injection for passing data between steps

  @DeleteUserAfterwards
  Scenario Outline: Register a new customer
    Given register page is loaded
      And I'm not logged in
     When I register a new user with "<firstName>", "<lastName>", "<email>", "<password>"
     Then register was successful
      And login page is opened

    @Chrome
    @Chrome_1400x1000
    Examples: 
      | firstName | lastName | email        | password  |
      | Jane      | Doe      | jane@doe.com | topsecret |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | firstName | lastName | email          | password  |
      | Jim       | Doe      | jim@doe.com    | topsecret |
      | Jeremy    | Doe      | jeremy@doe.com | topsecret |

  @DeleteUserAfterwards
  Scenario Outline: Login a newly registered  customer
    Given new user with "<firstName>", "<lastName>", "<email>", "<password>" is registered
      And I'm not logged in
     When I log in with "<email>" and "<password>"
     Then login was successful for "<firstName>"

    @Chrome
    @Chrome_1400x1000
    Examples: 
      | firstName | lastName | email          | password  |
      | Joseph    | Doe      | joseph@doe.com | topsecret |

    @Firefox
    @Firefox_1400x1000
    Examples: 
      | firstName | lastName | email          | password  |
      | Julia     | Doe      | julia@doe.com  | topsecret |
      | Jin       | Doe      | jin@doe.com    | topsecret |
