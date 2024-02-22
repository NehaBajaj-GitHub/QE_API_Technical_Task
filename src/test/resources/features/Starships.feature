@SWAPI @starship
Feature: To validate starship endpoint

  @validateStarship
  Scenario Outline: Validate Name and Crew for ID 9 in Starship
    Given user has access to endpoint "/starships/"
    When user makes a request to starships with "<starshipId>"
    Then user should get the response code <responseCode>
    And user starship name is "<starshipName>"
    And user starship crew count is "<crew>"

    Examples:
    |starshipId| responseCode | starshipName  | crew|
    | 9        | 200         | Death Star   | 342,953|