Feature: inventory

  Scenario: Deatils product and back to inventory page
    Given I am on the inventory page
    When I click the Product button
    And I shold redirected to the details page
    And I click the Back button
    Then I should redirected to the inventory page
