package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.goTo().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData().withName("Andrey").withLastName("Terekhov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withGroup("test1"));
            app.goTo().gotoHome();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}