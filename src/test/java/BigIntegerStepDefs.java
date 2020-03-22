import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class BigIntegerStepDefs {

    private BigInteger bigInt1;
    private BigInteger bigInt2;
    private String conversionResult;
    private BigInteger mathResult;

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

    @When("big integers are multiplied together")
    public void big_integers_are_multiplied_together() {
        mathResult = bigInt1.multiply(bigInt2);
    }

    @Then("math result should be {string}")
    public void math_result_should_be(String result) {
        Assert.assertEquals(result, mathResult.toString());
    }
}
