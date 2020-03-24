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

  Scenario: Test subtraction of big integers
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 1000000000
    When big integers are subtracted
    Then math result should be "1000000000"

  Scenario: Test subtraction of big integers is precise
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 1
    When big integers are subtracted
    Then math result should be "1999999999"

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

  Scenario: Test division of big integers
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 100
    When big integers are divided
    Then math result should be "20000000"

  Scenario: Test division of big integers 2
    Given big integer 1 equal to 750
    And big integer 2 equal to 55
    When big integers are divided
    Then math result should be "13"

  Scenario: Test division of big integers 3
    Given big integer 1 equal to 750
    And big integer 2 equal to 750
    When big integers are divided
    Then math result should be "1"

  Scenario: Test division of big integers 4
    Given big integer 1 equal to 750
    And big integer 2 equal to 1000
    When big integers are divided
    Then math result should be "0"

  Scenario: Test exponentiation of big integers
    Given big integer 1 equal to 500
    And big integer 2 equal to 3
    When big integers are exponentiated
    Then math result should be "125000000"