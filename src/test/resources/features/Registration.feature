Feature: Registration Functionality

  Scenario: Register New Account
    Given I navigate to Automation Exercise
#    Then I verify that the home page is visible successfully
    When I click Signup Login button
#    Then I verify 'New User Signup!' is visible
    When I enter new name "Amr" and email "amrhabib20@example.com"
    And I click Signup button
    Then I see "ENTER ACCOUNT INFORMATION" header
    When I fill account details with password "password123"
    And I fill personal details
    And I click Create Account
    Then I see "ACCOUNT CREATED!" message
#    When I click 'Continue' button
#    Then I verify that 'Logged in as username' is visible

  Scenario: Register with existing email
    Given I navigate to Automation Exercise
#    Then I verify that the home page is visible successfully
    When I click Signup Login button
#    Then I verify 'New User Signup!' is visible
    When I enter new name "Amr" and existing email "amrhabib1@example.com"
    And I click Signup button
    Then I see email exist error "Email Address already exist!"