Feature: Login

  @validData
  Scenario: Login with valid data
    Given Navigate to Home Page
    When Click on Login tab
    And Enter valid data
    And Click on Login Button
    Then SignOut button displayed

  @invalidPassword
  Scenario Outline: Login with invalid data
    Given Navigate to Home Page
    When Click on Login tab
    And Enter valid email and an invalid password
    |   email   |   password  |
    |  <email>  |  <password> |
    And Click on Login Button
    Then Alert appeared
    Examples:
    |  email            | password   |
    |  sascha@gmail.com | K7100596c  |
    |  sascha@gmail.com | 7100596c_  |