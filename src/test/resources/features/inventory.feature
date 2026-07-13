Feature: inventory

  Scenario: Deatils product and back to inventory page
    Given I am on the inventory page
    When I click the Product button
    And I shold redirected to the details page
    And I click the Back button
    Then I should redirected to the inventory page


  Scenario: Valid logout button
    Given I am on the inventory page
    When I click the menu button
    And I click the logout button
    Then I should redirected to the login page


  Scenario: Valid about button
    Given I am on the inventory page
    When I click the menu button
    And I click the about button
    Then I should redirected to the about page
