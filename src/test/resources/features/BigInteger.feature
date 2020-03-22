Feature: Test correctness of BigInteger

  Scenario: Test creation of big integer
    Given big integer 1 equal to 15000000
    When big integer 1 is converted to string
    Then conversion result should be "15000000"

  Scenario: Test addition of big integers
    Given big integer 1 equal to 15000000
    And big integer 2 equal to 1000000
    When big integers are added together
    Then math result should be "16000000"

  Scenario: Test addition of big integers carry properly
    Given big integer 1 equal to 750
    And big integer 2 equal to 600
    When big integers are added together
    Then math result should be "1350"

  Scenario: Test addition of big integers don't overflow
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 2000000000
    When big integers are added together
    Then math result should be "4000000000"

  Scenario: Test multiplication of big integers
    Given big integer 1 equal to 150
    And big integer 2 equal to 35
    When big integers are multiplied together
    Then math result should be "5250"

  Scenario: Test multiplication of big integers don't overflow
    Given big integer 1 equal to 1375684793
    And big integer 2 equal to 364
    When big integers are multiplied together
    Then math result should be "500749264652"