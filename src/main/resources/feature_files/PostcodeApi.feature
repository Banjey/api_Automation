Feature: Postcode api tests

  Scenario:
    Given I have a postcode
    When I send get request
    Then I should get postcode