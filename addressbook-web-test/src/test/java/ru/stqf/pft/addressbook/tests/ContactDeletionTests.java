package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Andrey", "Terekhov", "89651237160", "terehovandrej@gmail.com", null, "test1"));
            app.getNavigationHelper().gotoHome();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().gotoHome();
    }
}