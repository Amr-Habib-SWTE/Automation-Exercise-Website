Feature: Checkout and Invoice

  Scenario: Search Product, Checkout and Download Invoice
    Given I navigate to Automation Exercise
    # Pre-condition: User must be logged in for the checkout steps to work seamlessly as per script
    When I click Signup Login button
    And I enter email "amrhabib1@example.com" and password "password123"
    And I click Login button
    # Start Product flow
    When I click Products button
    And I search for "T-shirt"
    And I add first product to cart
    And I click Continue Shopping
    And I view second product and add 4 quantity
    And I click View Cart
    And I proceed to checkout
    And I place order with comment "Deliver fast"
    And I enter payment details
    Then I verify order success message
    And I download invoice and take screenshot