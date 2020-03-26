Feature: Test correctness of BigInteger

  #####################
  # Basic Functionality
  #####################

  @PositiveTest
  @Creation
  Scenario: Test creation of big integer
    Given big integer 1 equal to 15000000
    When big integer 1 is converted to string
    Then conversion result should be "15000000"

  @PositiveTest
  @Creation
  Scenario: Test creation of negative big integer
    Given big integer 1 equal to -15000000
    When big integer 1 is converted to string
    Then conversion result should be "-15000000"

  @NegativeTest
  @Creation
  Scenario: Test creation of bad big integer is rejected
    Given numeric string equal to "hello world"
    When big integer should not be convertible into a BigInteger

  @NegativeTest
  @Creation
  Scenario: Test creation of a float is rejected
    Given numeric string equal to "123.56"
    When big integer should not be convertible into a BigInteger

  #####################
  # Comparisons
  #####################

  @PositiveTest
  @Equals
  Scenario: Test equals method
    Given big integer 1 equal to 500
    And big integer 2 equal to 500
    Then big integers should be equal

  @PositiveTest
  @Equals
  Scenario: Test equals method works for negatives
    Given big integer 1 equal to -500
    And big integer 2 equal to -500
    Then big integers should be equal

  @PositiveTest
  @Equals
  Scenario: Test equals method for numbers that are not the same
    Given big integer 1 equal to 501
    And big integer 2 equal to 500
    Then big integers should not be equal

  @PositiveTest
  @Equals
  Scenario: Test equals method checks for negative and positive correctly
    Given big integer 1 equal to 500
    And big integer 2 equal to -500
    Then big integers should not be equal

  @PositiveTest
  @Equals
  Scenario: Test equals method works for nulls
    Given big integer 1 equal to 500
    Then big integer should not be equal to null

  @PositiveTest
  @Equals
  Scenario: Test equals method works for incompatible types
    Given big integer 1 equal to 500
    Then big integer should not be equal to "500"

  @PositiveTest
  @Equals
  Scenario: Test equals method works for same reference
    Given big integer 1 equal to 500
    Then big integer should be equal to itself

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly
    Given big integer 1 equal to 5
    And big integer 2 equal to 4
    Then big integer should be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 2
    Given big integer 1 equal to 5
    And big integer 2 equal to 5
    Then big integer should not be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 3
    Given big integer 1 equal to 5
    And big integer 2 equal to 6
    Then big integer should not be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 4
    Given big integer 1 equal to 50
    And big integer 2 equal to 5
    Then big integer should be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 5
    Given big integer 1 equal to 5
    And big integer 2 equal to 50
    Then big integer should not be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 5
    Given big integer 1 equal to -50
    And big integer 2 equal to 5
    Then big integer should not be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 5
    Given big integer 1 equal to 5
    And big integer 2 equal to -50
    Then big integer should be larger than other

  @PositiveTest
  @LargerThan
  Scenario: Test larger than works properly 5
    Given big integer 1 equal to -5
    And big integer 2 equal to -50
    Then big integer should be larger than other

  @PositiveTest
  @SmallerThan
  Scenario: Test smaller than works properly
    Given big integer 1 equal to 5
    And big integer 2 equal to 4
    Then big integer should not be smaller than other

  @PositiveTest
  @SmallerThan
  Scenario: Test smaller than works properly 2
    Given big integer 1 equal to 5
    And big integer 2 equal to 5
    Then big integer should not be smaller than other

  @PositiveTest
  @SmallerThan
  Scenario: Test smaller than works properly 3
    Given big integer 1 equal to 5
    And big integer 2 equal to 6
    Then big integer should be smaller than other

  @PositiveTest
  @IsEven
  Scenario: Test even check for an even number
    Given big integer 1 equal to 12
    Then the number should be even

  @PositiveTest
  @IsEven
  Scenario: Test even check for an odd number
    Given big integer 1 equal to 13
    Then the number should not be even

  @PositiveTest
  @IsEven
  Scenario: Test zero check for big integer zero
    Given big integer 1 equal to 0
    Then the number should be zero

  @PositiveTest
  @IsEven
  Scenario: Test zero check for big integer one
    Given big integer 1 equal to 1
    Then the number should not be zero

  @PositiveTest
  @IsEven
  Scenario: Test greater than zero for negative numbers
    Given big integer 1 equal to -1
    Then the number should not be greater than zero

  @PositiveTest
  @IsEven
  Scenario: Test greater than zero for zero
    Given big integer 1 equal to 0
    Then the number should not be greater than zero

  @PositiveTest
  @IsEven
  Scenario: Test greater than zero for positive numbers
    Given big integer 1 equal to 1
    Then the number should be greater than zero

  #####################
  # Addition
  #####################

  @PositiveTest
  @Addition
  Scenario: Test addition of big integers
    Given big integer 1 equal to 15000000
    And big integer 2 equal to 1000000
    When big integers are added together
    Then math result should be "16000000"

  @PositiveTest
  @Addition
  Scenario: Test addition of big integers carry properly
    Given big integer 1 equal to 750
    And big integer 2 equal to 600
    When big integers are added together
    Then math result should be "1350"

  @PositiveTest
  @Addition
  Scenario: Test addition of big integers don't overflow
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 2000000000
    When big integers are added together
    Then math result should be "4000000000"

  @PositiveTest
  @Addition
  Scenario: Test addition of negative big integers 1
    Given big integer 1 equal to 15000000
    And big integer 2 equal to -1000000
    When big integers are added together
    Then math result should be "14000000"

  @PositiveTest
  @Addition
  Scenario: Test addition of negative big integers 2
    Given big integer 1 equal to -15000000
    And big integer 2 equal to -1000000
    When big integers are added together
    Then math result should be "-16000000"

  @PositiveTest
  @Addition
  Scenario: Test addition of negative big integers 3
    Given big integer 1 equal to -15000000
    And big integer 2 equal to 1000000
    When big integers are added together
    Then math result should be "-14000000"

  #####################
  # Subtraction
  #####################

  @PositiveTest
  @Subtraction
  Scenario: Test subtraction of big integers
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 1000000000
    When big integers are subtracted
    Then math result should be "1000000000"

  @PositiveTest
  @Subtraction
  Scenario: Test subtraction of big integers can make a negative number
    Given big integer 1 equal to 1000000000
    And big integer 2 equal to 2000000000
    When big integers are subtracted
    Then math result should be "-1000000000"

  @PositiveTest
  @Subtraction
  Scenario: Test subtraction of big integers is precise
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 1
    When big integers are subtracted
    Then math result should be "1999999999"

  #####################
  # Multiplication
  #####################

  @PositiveTest
  @Multiplication
  Scenario: Test multiplication of big integers
    Given big integer 1 equal to 150
    And big integer 2 equal to 35
    When big integers are multiplied together
    Then math result should be "5250"

  @PositiveTest
  @Multiplication
  Scenario: Test multiplication of big integers don't overflow
    Given big integer 1 equal to 1375684793
    And big integer 2 equal to 364
    When big integers are multiplied together
    Then math result should be "500749264652"

  @PositiveTest
  @Multiplication
  Scenario: Test multiplication of big integers signed 1
    Given big integer 1 equal to 150
    And big integer 2 equal to -35
    When big integers are multiplied together
    Then math result should be "-5250"

  @PositiveTest
  @Multiplication
  Scenario: Test multiplication of big integers signed 2
    Given big integer 1 equal to -150
    And big integer 2 equal to -35
    When big integers are multiplied together
    Then math result should be "5250"

  @PositiveTest
  @Multiplication
  Scenario: Test multiplication of big integers signed 4
    Given big integer 1 equal to -150
    And big integer 2 equal to 35
    When big integers are multiplied together
    Then math result should be "-5250"

  #####################
  # Division
  #####################

  @PositiveTest
  @Division
  Scenario: Test division of big integers
    Given big integer 1 equal to 2000000000
    And big integer 2 equal to 100
    When big integers are divided
    Then math result should be "20000000"

  @PositiveTest
  @Division
  Scenario: Test division of big integers 2
    Given big integer 1 equal to 750
    And big integer 2 equal to 55
    When big integers are divided
    Then math result should be "13"

  @PositiveTest
  @Division
  Scenario: Test division of big integers 3
    Given big integer 1 equal to 750
    And big integer 2 equal to 750
    When big integers are divided
    Then math result should be "1"

  @PositiveTest
  @Division
  Scenario: Test division of big integers 4
    Given big integer 1 equal to 750
    And big integer 2 equal to 1000
    When big integers are divided
    Then math result should be "0"

  @PositiveTest
  @Division
  Scenario: Test division of big integers signed 1
    Given big integer 1 equal to 750
    And big integer 2 equal to -5
    When big integers are divided
    Then math result should be "-150"

  @PositiveTest
  @Division
  Scenario: Test division of big integers signed 2
    Given big integer 1 equal to -750
    And big integer 2 equal to -5
    When big integers are divided
    Then math result should be "150"

  @PositiveTest
  @Division
  Scenario: Test division of big integers signed 3
    Given big integer 1 equal to -750
    And big integer 2 equal to 5
    When big integers are divided
    Then math result should be "-150"

  #####################
  # Power
  #####################

  @PositiveTest
  @Power
  Scenario: Test exponentiation of big integers to zero power
    Given big integer 1 equal to 500
    And big integer 2 equal to 0
    When big integers are exponentiated
    Then math result should be "1"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of big integers to negative power
    Given big integer 1 equal to 500
    And big integer 2 equal to -2
    When big integers are exponentiated
    Then math result should be "0"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of big integers
    Given big integer 1 equal to 500
    And big integer 2 equal to 3
    When big integers are exponentiated
    Then math result should be "125000000"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of big integers to zero power
    Given big integer 1 equal to 500
    And big integer 2 equal to 0
    When big integers are exponentiated
    Then math result should be "1"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of negative big integers
    Given big integer 1 equal to -500
    And big integer 2 equal to 3
    When big integers are exponentiated
    Then math result should be "-125000000"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of negative big integers 2
    Given big integer 1 equal to -500
    And big integer 2 equal to 2
    When big integers are exponentiated
    Then math result should be "250000"

  @PositiveTest
  @Power
  Scenario: Test exponentiation of negative big integers 3
    Given big integer 1 equal to -500
    And big integer 2 equal to 4
    When big integers are exponentiated
    Then math result should be "62500000000"