package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import ru.stqf.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().gotoContactCreation();
            app.contact().createContact(new ContactData().withName("Andrey").withLastName("Terekhov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com"));
            app.goTo().gotoHome();
        }
    }
    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().gotoHome();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}