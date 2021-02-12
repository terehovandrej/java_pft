package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Andrey", "Terekhov", "89651237160", "terehovandrej@gmail.com", null, "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHome();
  }









}
