@SWAPI @planets
Feature: Validate API response format is Wookiee

  @validateplanets
  Scenario Outline: Validate API response is
    Given user has access to endpoint "/planets/"
    When user makes a request to planets with "<planetsId>" with format set to Wookiee
    Then user should get the response code <responseCode>
    And user sees "whrascwo" in response body

    Examples:
      |planetsId| responseCode |
      | 14       | 200         |