Feature: Add Contact

  @validContactData
  Scenario: Add new Contact with valid data
    Given Navigate to User Account
    When Click on Add tab
    And Enter valid contact data
    And Click on Save Button
    Then Contact is displayed

