@SWAPI @people
Feature: To validate people endpoint

  @validatePeople
  Scenario Outline: Validate Name, Skin_Colour and Films for ID 3 in People
    Given user has access to endpoint "/people/"
    When user makes a request to people with "<peopleId>"
    Then user should get the response code <responseCode>
    And user people name is "<peopleName>"
    And user people skin_color is "<skin_color>"
    And user people films are "<films>"

    Examples:
    |peopleId| responseCode | peopleName  | skin_color   | films |
    | 3       | 200         | R2-D2        | white, blue | A New Hope,The Empire Strikes Back,Return of the Jedi,The Phantom Menace,Attack of the Clones,Revenge of the Sith|