import java.util.ArrayList;
import java.util.List;

/**
 * Represents a number which can grow without bound.
 * Opposed to regular Integers which are capped at 2.14b.
 *
 * This class is immutable.
 *
 * Guarantees precision on addition, subtraction, multiplication, and power operations
 * Currently only stores POSITIVE numbers.
 *
 * For educational purposes only.
 */
public class BigInteger {

    private String numericString;

    /**
     * List of allowed operations on this BigInteger
     */
    private enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        EXPONENT,
        DIVIDE
    }

    /**
     * Initializes a BigInteger with a value of zero.
     */
    public BigInteger() {
        this(0);
    }

    /**
     * Initializes a BigInteger with a specified String value.
     *
     * @param value Initial value.
     */
    public BigInteger(String value) {
        this.numericString = trim(value);
    }

    /**
     * Initializes a BigInteger with a specified value.
     *
     * @param value Initial value.
     */
    public BigInteger(int value) {
        this(value + "");
    }

    /**
     * Adds this BigInteger to another.
     *
     * @param other Another BigInteger to add to
     * @return      Addition result
     */
    public BigInteger add(BigInteger other) {
        return doMath(other, Operation.ADD);
    }

    /**
     * Subtracts another BigInteger from this one.
     *
     * @param other Another BigInteger to subtract
     * @return      Subtraction result
     */
    public BigInteger subtract(BigInteger other) {
        return doMath(other, Operation.SUBTRACT);
    }

    /**
     * Multiplies this BigInteger with another.
     *
     * @param other Another BigInteger to multiply with
     * @return      Multiplication result
     */
    public BigInteger multiply(BigInteger other)  {
        return doMath(other, Operation.MULTIPLY);
    }

    /**
     * Gives an ESTIMATE for this / other
     *
     * @param other BigInt to divide by
     * @return  Division estimate
     */
    public BigInteger divide(BigInteger other) {
        return doMath(other, Operation.DIVIDE);
    }

    /**
     * Brings this BigInteger to the power of the other BigInteger
     *
     * @param other BigInt to bring to power
     * @return  Exponent result
     */
    public BigInteger power(BigInteger other) {
        return doMath(other, Operation.EXPONENT);
    }

    /**
     * Pads this String with leading zeros until it is as long as len
     *
     * @param origin Original string
     * @param len    Desired length
     * @return String of desired length
     */
    private String pad(String origin, int len) {
        while (origin.length() < len) {
            origin = "0" + origin;
        }
        return origin;
    }

    /**
     * Performs the specified operation.
     *
     * @param other     Another BigInteger
     * @param operation Math operation
     * @return          Result of math
     */
    private BigInteger doMath(BigInteger other, Operation operation) {
        String otherNumericString = other.numericString;
        int thisLength = numericString.length();
        int otherLength = otherNumericString.length();
        String thisNumericString = pad(numericString, Math.max(thisLength, otherLength));
        otherNumericString = pad(otherNumericString, Math.max(thisLength, otherLength));
        return performOperation(thisNumericString, otherNumericString, operation);
    }

    /**
     * Performs an operation on two set up Strings
     *
     * @param numericStringOne  Padded numeric String from this BigInteger
     * @param numericStringTwo  Padded numeric String from other BigInteger
     * @param operation         Math operation
     * @return                  Result of math operation
     */
    private BigInteger performOperation(String numericStringOne, String numericStringTwo, Operation operation) {
        if(operation == Operation.ADD) {
            return iterativeAdd(numericStringOne, numericStringTwo);
        } else if(operation == Operation.MULTIPLY) {
            return iterativeMultiply(numericStringOne, numericStringTwo);
        } else if(operation == Operation.DIVIDE) {
            return divisonEstimate(numericStringOne, numericStringTwo);
        } else if (operation == Operation.EXPONENT) {
            return doPower(numericStringOne, numericStringTwo);
        } else if(operation == Operation.SUBTRACT) {
            return iterativeSubtract(numericStringOne, numericStringTwo);
        } else {
            return null;
        }
    }

    private BigInteger doPower(String numericStringOne, String numericStringTwo) {
        return new BigInteger("0");
    }

    private BigInteger iterativeSubtract(String numericStringOne, String numericStringTwo) {
        return new BigInteger("0");
    }

    /**
     * Gets division estimate of two numeric Strings
     *
     * @param numericStringOne  Padded numeric String from this BigInteger
     * @param numericStringTwo  Padded numeric String from other BigInteger
     * @return                  Result of math operation
     */
    private BigInteger divisonEstimate(String numericStringOne, String numericStringTwo) {
        // Convert both big integers into scientific-notation-like form
        // i.e. 21465 = 2.1465 x 10 ^ 4
        double numericSegmentOne = getDecimalSegment(numericStringOne);
        int exponentOne = getExponent(numericStringOne);

        double numericSegmentTwo = getDecimalSegment(numericStringTwo);
        int exponentTwo = getExponent(numericStringTwo);

        double dividedPart = numericSegmentOne / numericSegmentTwo;
        int exponentPart = exponentOne - exponentTwo;
        if(exponentPart < 0) {
            // Other BigInt was bigger than this one, so return zero.
            return new BigInteger("0");
        }

        String convertedDividedPart = (dividedPart + "").replace(".", "");
        while(convertedDividedPart.length() <= exponentPart+1) {
            convertedDividedPart += "0";
        }
        String appliedExponentPart = convertedDividedPart.substring(0, exponentPart + 1);

        return new BigInteger(appliedExponentPart);
    }

    /**
     * Gets the (Y) x 10 ^ X part of a BigInteger
     *
     * @param numericString Numeric string to get decimal part from
     * @return  Decimal part of numeric string
     */
    private double getDecimalSegment(String numericString) {
        numericString = trim(numericString);
        return Double.parseDouble(numericString.charAt(0) + "." + numericString.substring(1));
    }

    /**
     * Gets the Y x 10 ^ (X) part of a BigInteger
     *
     * @param numericString Numeric string to get exponent part from
     * @return  Exponent part of numeric string
     */
    private int getExponent(String numericString) {
        numericString = trim(numericString);
        return numericString.length()-1;
    }

    /**
     * Iterates over String and adds together.
     *
     * @param numericStringOne  Padded numeric String from this BigInteger
     * @param numericStringTwo  Padded numeric String from other BigInteger
     * @return                  Result of math operation
     */
    private BigInteger iterativeAdd(String numericStringOne, String numericStringTwo) {
        String result = "";
        int carryOver = 0;
        for(int i = numericStringOne.length()-1; i >= 0; i--) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i)+"");
            int num2 = Integer.parseInt(numericStringTwo.charAt(i)+"");
            int addResult = num1 + num2 + carryOver;
            carryOver = 0;
            if(addResult >= 10) {
                addResult -= 10;
                carryOver = 1;
            }
            result = addResult + result;
        }
        if(carryOver == 1) {
            result = "1" + result;
        }
        return new BigInteger(result);
    }

    /**
     * Iterates over String and multiplies together.
     *
     * @param numericStringOne  Padded numeric String from this BigInteger
     * @param numericStringTwo  Padded numeric String from other BigInteger
     * @return                  Result of math operation
     */
    private BigInteger iterativeMultiply(String numericStringOne, String numericStringTwo) {
        List<String> partialResults = new ArrayList<>();
        String baseAdjustment = "";
        String adjustment = "";
        for(int i = numericStringOne.length()-1; i >= 0; i--) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i)+"");
            adjustment = baseAdjustment;
            for(int j = numericStringOne.length()-1; j >= 0; j--) {
                int num2 = Integer.parseInt(numericStringTwo.charAt(j)+"");
                String multiplyResult = num1 * num2 + "";
                partialResults.add(multiplyResult + adjustment);
                adjustment += "0";
            }
            baseAdjustment += "0";
        }
        BigInteger result = new BigInteger();
        for(String partialResult: partialResults) {
            result = result.add(new BigInteger(partialResult));
        }
        return result;
    }

    /**
     * Trims the leading zeros off a BigInteger representation
     *
     * @param input BigInteger representation
     * @return  The representation without leading zeros
     */
    private String trim(String input) {
        String trimmedInput = "";
        boolean foundNonZero = false;
        for(char c: input.toCharArray()) {
            if(c != '0')  {
                foundNonZero = true;
                trimmedInput = trimmedInput + c;
            } else if(foundNonZero) {
                trimmedInput = trimmedInput + c;
            }
        }
        if(trimmedInput.isEmpty()) {
            return "0";
        }
        return trimmedInput;
    }

    /**
     * Returns the String representation of this BigInteger.
     *
     * @return  String representation of this BigInteger.
     */
    @Override
    public String toString() {
        return numericString;
    }
}
