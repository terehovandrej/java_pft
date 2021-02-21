package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData("Andrey", "Terekhov", "89651237160", "terehovandrej@gmail.com", null, "test1"));
            app.getNavigationHelper().gotoHome();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}