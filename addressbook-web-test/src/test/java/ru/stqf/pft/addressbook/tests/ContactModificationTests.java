package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Andrey", "Terekhov", "89651237160", "terehovandrej@gmail.com", null, "test1"));
            app.getNavigationHelper().gotoHome();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectedContacts();
        app.getContactHelper().fillContactForm(new ContactData("Sergey", "Ivanov", "89651237160", "terehovandrej@gmail.com", "pushkina 47", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
    }
}