Feature: Get user info

  @userInfo
  Scenario Outline: Get user details
    Given the API is up and running
    When send post request "<username>", "<password>"
    And the response status code should be <statusCode>
    And get auth token
    Then send get request for user info
    And the response status code should be <statusCode>
    And Check if user info data is correct
    Examples:
      | statusCode |username |password  |
      | 200        |testUser |Churchxela|
  @userInfo
  Scenario Outline: Get user details when session is invalid
    Given the API is up and running
    When send post request "<username>", "<password>"
    And the response status code should be <statusCode>
    Then send get request for user info when session is invalid
    And the response status code should be <statusCode>
    Examples:
      | statusCode |username |password  |
      | 498        |testUser |Churchxela|