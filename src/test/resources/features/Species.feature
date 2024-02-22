@SWAPI @species
Feature: To validate species endpoint

  @validateSpecies
  Scenario Outline: Validate Name, Classification and Homeworld for ID 3 in Species
    Given user has access to endpoint "/species/"
    When user makes a request to species with "<speciesId>"
    Then user should get the response code <responseCode>
    And user species name is "<speciesName>"
    And user species classification is "<classification>"
    And user species homeworld name is "<homeworld>"

    Examples:
    |speciesId| responseCode | speciesName  | classification| homeworld |
    | 3       | 200         | Wookie   | mammal|    Kashyyyk            |