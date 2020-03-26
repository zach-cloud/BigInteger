import java.util.ArrayList;
import java.util.List;

/**
 * Represents a number which can grow without bound.
 * Opposed to regular Integers which are capped at 2.14b.
 * <p>
 * This class is immutable.
 * <p>
 * Guarantees precision on addition, subtraction, multiplication, and power operations
 * <p>
 * For educational purposes only.
 */
public class BigInteger {

    /**
     * Stores this BigInteger's number as a String.
     */
    private String numericString;
    /**
     * True if negative. False if positive.
     */
    private boolean negative = false;

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
        if (value.startsWith("-")) {
            value = value.substring(1);
            negative = true;
        }
        this.numericString = trim(value);
        if(!numericString.matches("[0-9]+")) {
            throw new NumberFormatException("Not an integer.");
        }
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
     * Makes a negative version of this BigInteger
     *
     * @return  -(this) as a BigInteger
     */
    public BigInteger makeNegative() {
        return new BigInteger("-" + numericString);
    }

    /**
     * Adds this BigInteger to another.
     *
     * @param other Another BigInteger to add to
     * @return Addition result
     */
    public BigInteger add(BigInteger other) {
        if (!negative && !other.negative) {
            return doMath(other, Operation.ADD);
        } else if (!negative && other.negative) {
            return doMath(other, Operation.SUBTRACT);
        } else if (negative && !other.negative) {
            return doMath(other, Operation.SUBTRACT).makeNegative();
        } else if (negative && other.negative) {
            return doMath(other, Operation.ADD).makeNegative();
        }
        return null;
    }

    /**
     * Subtracts another BigInteger from this one.
     *
     * @param other Another BigInteger to subtract
     * @return Subtraction result
     */
    public BigInteger subtract(BigInteger other) {
        if (this.largerThan(other)) {
            return doMath(other, Operation.SUBTRACT);
        } else if (other.largerThan(this)) {
            return other.doMath(this, Operation.SUBTRACT).makeNegative();
        }
        return new BigInteger("0");
    }

    /**
     * Multiplies this BigInteger with another.
     *
     * @param other Another BigInteger to multiply with
     * @return Multiplication result
     */
    public BigInteger multiply(BigInteger other) {
        BigInteger result = doMath(other, Operation.MULTIPLY);
        if(this.negative != other.negative ) {
            result = result.makeNegative();
        }
        return result;
    }

    /**
     * Gives an ESTIMATE for this / other
     *
     * @param other BigInt to divide by
     * @return Division estimate
     */
    public BigInteger divide(BigInteger other) {
        BigInteger result = doMath(other, Operation.DIVIDE);
        if(this.negative != other.negative ) {
            result = result.makeNegative();
        }
        return result;
    }

    /**
     * Brings this BigInteger to the power of the other BigInteger
     *
     * @param other BigInt to bring to power
     * @return Exponent result
     */
    public BigInteger power(BigInteger other) {
        if(other.isZero()) {
            return new BigInteger("1");
        }
        if(other.largerThanZero()) {
            BigInteger result = doMath(other, Operation.EXPONENT);
            if(negative) {
                if(!other.isEven()) {
                    result = result.makeNegative();
                }
            }
            return result;
        } else {
            // We don't support decimals so this rounds to zero.
            return new BigInteger("0");
        }
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
     * @return Result of math
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
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @param operation        Math operation
     * @return Result of math operation
     */
    private BigInteger performOperation(String numericStringOne, String numericStringTwo, Operation operation) {
        if (operation == Operation.ADD) {
            return iterativeAdd(numericStringOne, numericStringTwo);
        } else if (operation == Operation.MULTIPLY) {
            return iterativeMultiply(numericStringOne, numericStringTwo);
        } else if (operation == Operation.DIVIDE) {
            return divisonEstimate(numericStringOne, numericStringTwo);
        } else if (operation == Operation.EXPONENT) {
            return doPower(numericStringOne, numericStringTwo);
        } else if (operation == Operation.SUBTRACT) {
            return iterativeSubtract(numericStringOne, numericStringTwo);
        } else {
            return null;
        }
    }

    /**
     * Performs exponent operation/
     *
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @return Result of power operation
     */
    private BigInteger doPower(String numericStringOne, String numericStringTwo) {
        BigInteger storedValue = new BigInteger(numericStringOne);
        BigInteger bigIntegerOne = new BigInteger("1");
        BigInteger bigIntegerTwo = new BigInteger(numericStringTwo);
        while (!bigIntegerTwo.toString().equals("0")) {
            bigIntegerOne = bigIntegerOne.multiply(storedValue);
            bigIntegerTwo = bigIntegerTwo.subtract(new BigInteger("1"));
        }
        return bigIntegerOne;
    }

    /**
     * Loops over the numeric digits and subtracts them.
     *
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @return Result of subtraction
     */
    private BigInteger iterativeSubtract(String numericStringOne, String numericStringTwo) {
        String result = "";
        boolean carried = false;
        for (int i = numericStringOne.length() - 1; i >= 0; i--) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i) + "");
            int num2 = Integer.parseInt(numericStringTwo.charAt(i) + "");
            if (carried) {
                num1--;
                carried = false;
            }
            if (num1 < num2) {
                num1 += 10;
                carried = true;
            }
            int subtractResult = num1 - num2;
            result = subtractResult + result;
        }
        return new BigInteger(result);
    }

    /**
     * Gets division estimate of two numeric Strings
     *
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @return Result of math operation
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
        if (exponentPart < 0) {
            // Other BigInt was bigger than this one, so return zero.
            return new BigInteger("0");
        }

        String convertedDividedPart = (dividedPart + "").replace(".", "");
        while (convertedDividedPart.length() <= exponentPart + 1) {
            convertedDividedPart += "0";
        }
        String appliedExponentPart = convertedDividedPart.substring(0, exponentPart + 1);

        return new BigInteger(appliedExponentPart);
    }

    /**
     * Gets the (Y) x 10 ^ X part of a BigInteger
     *
     * @param numericString Numeric string to get decimal part from
     * @return Decimal part of numeric string
     */
    private double getDecimalSegment(String numericString) {
        numericString = trim(numericString);
        return Double.parseDouble(numericString.charAt(0) + "." + numericString.substring(1));
    }

    /**
     * Gets the Y x 10 ^ (X) part of a BigInteger
     *
     * @param numericString Numeric string to get exponent part from
     * @return Exponent part of numeric string
     */
    private int getExponent(String numericString) {
        numericString = trim(numericString);
        return numericString.length() - 1;
    }

    /**
     * Iterates over String and adds together.
     *
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @return Result of math operation
     */
    private BigInteger iterativeAdd(String numericStringOne, String numericStringTwo) {
        String result = "";
        int carryOver = 0;
        for (int i = numericStringOne.length() - 1; i >= 0; i--) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i) + "");
            int num2 = Integer.parseInt(numericStringTwo.charAt(i) + "");
            int addResult = num1 + num2 + carryOver;
            carryOver = 0;
            if (addResult >= 10) {
                addResult -= 10;
                carryOver = 1;
            }
            result = addResult + result;
        }
        if (carryOver == 1) {
            result = "1" + result;
        }
        return new BigInteger(result);
    }

    /**
     * Iterates over String and multiplies together.
     *
     * @param numericStringOne Padded numeric String from this BigInteger
     * @param numericStringTwo Padded numeric String from other BigInteger
     * @return Result of math operation
     */
    private BigInteger iterativeMultiply(String numericStringOne, String numericStringTwo) {
        List<String> partialResults = new ArrayList<>();
        String baseAdjustment = "";
        String adjustment = "";
        for (int i = numericStringOne.length() - 1; i >= 0; i--) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i) + "");
            adjustment = baseAdjustment;
            for (int j = numericStringOne.length() - 1; j >= 0; j--) {
                int num2 = Integer.parseInt(numericStringTwo.charAt(j) + "");
                String multiplyResult = num1 * num2 + "";
                partialResults.add(multiplyResult + adjustment);
                adjustment += "0";
            }
            baseAdjustment += "0";
        }
        BigInteger result = new BigInteger();
        for (String partialResult : partialResults) {
            result = result.add(new BigInteger(partialResult));
        }
        return result;
    }

    /**
     * Trims the leading zeros off a BigInteger representation
     *
     * @param input BigInteger representation
     * @return The representation without leading zeros
     */
    private String trim(String input) {
        String trimmedInput = "";
        boolean foundNonZero = false;
        for (char c : input.toCharArray()) {
            if (c != '0') {
                foundNonZero = true;
                trimmedInput = trimmedInput + c;
            } else if (foundNonZero) {
                trimmedInput = trimmedInput + c;
            }
        }
        if (trimmedInput.isEmpty()) {
            return "0";
        }
        return trimmedInput;
    }

    /**
     * Returns the String representation of this BigInteger.
     *
     * @return String representation of this BigInteger.
     */
    @Override
    public String toString() {
        if (negative) {
            return "-" + numericString;
        }
        return numericString;
    }

    /**
     * Generates HashCode. Implemented to satisfy
     * the contract for equals().
     *
     * @return Hashcode for this BigInteger
     */
    @Override
    public int hashCode() {
        int modification = 0;
        if (negative) {
            modification = 3;
        }
        return numericString.hashCode() + modification;
    }

    /**
     * Compares this and another BigInteger
     *
     * @param obj Other BigInteger to compare
     * @return True if equals. False if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BigInteger)) {
            return false;
        }
        BigInteger other = (BigInteger) obj;
        return this.numericString.equals(other.numericString) && this.negative == other.negative;
    }

    /**
     * Compares digits of number.
     *
     * @param numericStringOne  This numeric string
     * @param numericStringTwo  Other numeric string
     * @return                  True if one > two. False if not.
     */
    private boolean iterativeCompare(String numericStringOne, String numericStringTwo) {
        numericStringOne = trim(numericStringOne);
        numericStringTwo = trim(numericStringTwo);
        if(numericStringOne.length() > numericStringTwo.length()) {
            return true;
        }
        if(numericStringTwo.length() > numericStringOne.length()) {
            return false;
        }
        for(int i = 0; i < numericStringOne.length(); i++) {
            int num1 = Integer.parseInt(numericStringOne.charAt(i) + "");
            int num2 = Integer.parseInt(numericStringTwo.charAt(i) + "");
            if(num1 > num2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if this BigInt is larger than another.
     *
     * @param other Another BigInt to compare.
     * @return True if larger. False if equal or lesser.
     */
    public boolean largerThan(BigInteger other) {
        // If equal, then it's not larger than the other.
        if (this.equals(other)) {
            return false;
        }
        // If this is negative and the other is positive, then it's not larger.
        if (this.negative && !other.negative) {
            return false;
        }
        // If the other is negative and this is positive, then it's larger
        if (!this.negative && other.negative) {
            return true;
        }
        // If both are positive, then do iterative compare.
        if (!this.negative && !other.negative) {
            return iterativeCompare(this.numericString, other.numericString);
        }
        // If both are negative, it's the reversed logic from above.
        return !iterativeCompare(this.numericString, other.numericString);
    }

    /**
     * Determines if this BigInt is smaller than another.
     *
     * @param other Another BigInt to compare.
     * @return True if smaller. False if equal or larger.
     */
    public boolean smallerThan(BigInteger other) {
        if(this.equals(other)) {
            return false;
        }
        return !largerThan(other);
    }

    /**
     * Determines if this BigInteger is greater than zero.
     *
     * @return  False if greater
     */
    public boolean largerThanZero() {
        return !negative && !isZero();
    }

    /**
     * Determines if this BigInteger is equal to zero.
     *
     * @return  True if zero. False if not.
     */
    public boolean isZero() {
        return trim(numericString).equals("0");
    }

    /**
     * Determines whether this is even or odd.
     *
     * @return  True if even. False if odd.
     */
    public boolean isEven() {
        int lastDigit = Integer.parseInt(""+numericString.charAt(numericString.length()-1));
        return (lastDigit % 2) == 0;
    }
}
