import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome");
  }

  @Test
  public void stylistsNewPage() {
    goTo("http://localhost:4567/");
    click("a", withText("Click Here to Add a Stylist"));
    fill("#name").with("Jane");
    submit(".btn");
    assertThat(pageSource()).contains("Jane");
  }

  @Test
  public void StylistNameIsDisplayedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Click Here to Add a Stylist"));
    fill("#name").with("Jane");
    fill("#phone").with("1235551234");
    submit(".btn");
    assertThat(pageSource()).contains("Jane");
    click("a", withText("Jane"));
    assertThat(pageSource()).contains("Stylist Info:");
  }

  @Test
  public void stylistClientFormIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("Click Here to Add a Stylist"));
    fill("#name").with("Jane");
    submit(".btn");
    assertThat(pageSource()).contains("Jane");
    click("a", withText("Jane"));
    assertThat(pageSource()).contains("Stylist Info:");
    click("a", withText("Click Here to Add a Client"));
    fill("#name").with("Jenna");
    fill("#phone").with("4445556666");
    submit(".btn");
    assertThat(pageSource()).contains("Jenna");
  }

}
