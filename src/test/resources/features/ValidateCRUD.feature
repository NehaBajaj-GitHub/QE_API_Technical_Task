@SWAPI @validateCRUD
Feature: Verify SWAPI Endpoints Status Code

  Scenario Outline: Verify status code of the endpoints
    Given user has access to endpoint "<endpoint>"
    When user sends "<httpMethod>" HTTP request to endpoint
    Then user receives valid HTTP response code <responseCode>

    Examples:
      | endpoint   | httpMethod | responseCode |
      | /people    | GET        | 200          |
      | /people    | PUT        | 405          |
      | /people    | POST       | 405          |
      | /people    | DELETE     | 405          |
      | /people    | PATCH      | 405          |
      | /species   | GET        | 200          |
      | /species   | PUT        | 405          |
      | /species   | POST       | 405          |
      | /species   | DELETE     | 405          |
      | /species   | PATCH      | 405          |
      | /starships | GET        | 200          |
      | /starships | PUT        | 405          |
      | /starships | POST       | 405          |
      | /starships | DELETE     | 405          |
      | /starships | PATCH      | 405          |