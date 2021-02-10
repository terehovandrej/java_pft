package ru.stqf.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqf.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Andrey", "Terekhov", "89651237160", "terehovandrej@gmail.com", "pushkina 47"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHome();
  }









}
