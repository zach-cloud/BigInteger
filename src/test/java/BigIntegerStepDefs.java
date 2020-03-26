import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BigIntegerStepDefs {

    private BigInteger bigInt1;
    private BigInteger bigInt2;
    private String conversionResult;
    private BigInteger mathResult;
    private String numericString;

    @Given("big integer 1 equal to {int}")
    public void big_integer1_equal_to(Integer value) {
        this.bigInt1 = new BigInteger(value);
    }

    @Given("big integer 2 equal to {int}")
    public void big_integer2_equal_to(Integer value) {
        this.bigInt2 = new BigInteger(value);
    }

    @When("big integer 1 is converted to string")
    public void big_integer1_is_converted_to_string() {
        conversionResult = bigInt1.toString();
    }

    @Then("conversion result should be {string}")
    public void conversion_result_should_be(String result) {
        Assert.assertEquals(result, conversionResult);
    }

    @When("big integers are added together")
    public void big_integers_are_added_together() {
        mathResult = bigInt1.add(bigInt2);
    }

    @When("big integers are subtracted")
    public void big_integers_are_subtracted() {
        mathResult = bigInt1.subtract(bigInt2);
    }

    @When("big integers are multiplied together")
    public void big_integers_are_multiplied_together() {
        mathResult = bigInt1.multiply(bigInt2);
    }

    @When("big integers are divided")
    public void big_integers_are_divided() {
        mathResult = bigInt1.divide(bigInt2);
    }

    @When("big integers are exponentiated")
    public void big_integers_are_exp() {
        mathResult = bigInt1.power(bigInt2);
    }

    @Then("math result should be {string}")
    public void math_result_should_be(String result) {
        Assert.assertEquals(result, mathResult.toString());
    }

    @Then("big integers should be equal")
    public void bigIntegersShouldBeEqual() {
        Assert.assertEquals(bigInt1, bigInt2);
        Assert.assertEquals(bigInt1.hashCode(), bigInt2.hashCode());
    }

    @Then("big integers should not be equal")
    public void bigIntegersShouldNotBeEqual() {
        Assert.assertNotEquals(bigInt1, bigInt2);
        Assert.assertNotEquals(bigInt1.hashCode(), bigInt2.hashCode());
    }

    @Then("big integer should be larger than other")
    public void bigIntegerShouldBeLargerThanOther() {
        Assert.assertTrue(bigInt1.largerThan(bigInt2));
    }

    @Then("big integer should not be larger than other")
    public void bigIntegerShouldNotBeLargerThanOther() {
        Assert.assertFalse(bigInt1.largerThan(bigInt2));
    }

    @When("big integer should not be convertible into a BigInteger")
    public void bigIntegerShouldNotBeConvertibleIntoABigInteger() {
        try {
            BigInteger test = new BigInteger(numericString);
            Assert.fail();
        } catch (Exception ex) {
            // Pass test
        }
    }

    @Given("numeric string equal to {string}")
    public void numericStringEqualTo(String numericString) {
        this.numericString = numericString;
    }

    @Then("big integer should be smaller than other")
    public void bigIntegerShouldBeSmallerThanOther() {
        Assert.assertTrue(bigInt1.smallerThan(bigInt2));
    }

    @Then("big integer should not be smaller than other")
    public void bigIntegerShouldNotBeSmallerThanOther() {
        Assert.assertFalse(bigInt1.smallerThan(bigInt2));
    }

    @Then("big integer should not be equal to null")
    public void bigIntegerShouldNotBeEqualToNull() {
        Assert.assertFalse(bigInt1.equals(null));
    }

    @Then("big integer should not be equal to {string}")
    public void bigIntegerShouldNotBeEqualTo(String input) {
        Assert.assertFalse(bigInt1.equals(input));
    }

    @Then("big integer should be equal to itself")
    public void bigIntegerShouldBeEqualToItself() {
        Assert.assertTrue(bigInt1.equals(bigInt1));
    }

    @Then("the number should be even")
    public void theNumberShouldBeEven() {
        Assert.assertTrue(bigInt1.isEven());
    }

    @Then("the number should not be even")
    public void theNumberShouldNotBeEven() {
        Assert.assertFalse(bigInt1.isEven());
    }

    @Then("the number should be zero")
    public void theNumberShouldBeZero() {
        Assert.assertTrue(bigInt1.isZero());
    }

    @Then("the number should not be zero")
    public void theNumberShouldNotBeZero() {
        Assert.assertFalse(bigInt1.isZero());
    }

    @Then("the number should not be greater than zero")
    public void theNumberShouldNotBeGreaterThanZero() {
        Assert.assertFalse(bigInt1.largerThanZero());
    }

    @Then("the number should be greater than zero")
    public void theNumberShouldBeGreaterThanZero() {
        Assert.assertTrue(bigInt1.largerThanZero());
    }
}
