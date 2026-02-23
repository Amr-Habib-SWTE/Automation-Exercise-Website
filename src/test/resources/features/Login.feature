Feature: Login Functionality

  Scenario: Login with invalid credentials
    Given I navigate to Automation Exercise
    When I click Signup Login button
    And I enter email "incorrect@test.com" and password "wrong"
    And I click Login button
    Then I see error message "Your email or password is incorrect!"

  Scenario: Login with valid credentials
    Given I navigate to Automation Exercise
    When I click Signup Login button
    And I enter email "amrhabib1@example.com" and password "password123"
    And I click Login button
    Then I verify user is logged in