import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AllergiesTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void allergyArray_addsElements_true() {
    Allergies a = new Allergies();
    String expValue = "[cat]";
    assertEquals(expValue, a.allergyArray(128));
  }

  @Test
  public void allergyArray_addsElements1_true() {
    Allergies a = new Allergies();
    String expValue = "[cat, pollen]";
    assertEquals(expValue, a.allergyArray(192));
  }

  @Test
  public void allergyArray_addsElements2_true() {
    Allergies a = new Allergies();
    String expValue = "[pollen, peanuts]";
    assertEquals(expValue, a.allergyArray(66));
  }

  @Test
  public void allergyArray_addsAllElements_true() {
    Allergies a = new Allergies();
    String expValue = "[cat, pollen, chocolate, tomatoes, strawberries, shellfish, peanuts, eggs]";
    assertEquals(expValue, a.allergyArray(255));
  }

  @Test
  public void checkInput_numberInRange_true() {
    Allergies a = new Allergies();
    Boolean expValue = true;
    assertEquals(expValue, a.checkInput("255"));
  }

  @Test
  public void checkInput_numberInRange_false() {
    Allergies a = new Allergies();
    Boolean expValue = false;
    assertEquals(expValue, a.checkInput("-3"));
  }

  @Test
  public void checkInput_isNumber_false() {
    Allergies a = new Allergies();
    Boolean expValue = false;
    assertEquals(expValue, a.checkInput("kangaroo"));
  }

}
